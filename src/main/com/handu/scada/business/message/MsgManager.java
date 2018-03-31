package main.com.handu.scada.business.message;

import main.com.handu.scada.business.message.client.rpt.Scdl_sms_rpt_wsLocator;
import main.com.handu.scada.business.message.client.rpt.Scdl_sms_rpt_wsSoap_BindingStub;
import main.com.handu.scada.business.message.client.send.Scdl_sms_send_wsLocator;
import main.com.handu.scada.business.message.client.send.Scdl_sms_send_wsSoap_BindingStub;
import main.com.handu.scada.db.bean.*;
import main.com.handu.scada.db.mapper.BaseSmssendMapper;
import main.com.handu.scada.db.mapper.BaseUserMapper;
import main.com.handu.scada.db.mapper.DeviceCommunicationgroupMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;
import main.com.handu.scada.utils.UUIDUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.rpc.ServiceException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2017/12/29.
 * 短信发送
 */
public class MsgManager {

    private BlockingQueue<Msg> queue;
    private static MsgManager singleton;
    private Timer timer;

    private MsgManager(boolean isLoop) {
        timer = new Timer();
        //开始循环短信入库
        if (isLoop) {
            queue = new LinkedBlockingDeque<>(1000);
            //每一段时间从队列中读取
            timer.schedule(new LoopThread(), 5000, 10000);
        }
    }

    public static MsgManager getInstance(boolean isLoop) {
        if (singleton == null) {
            synchronized (MsgManager.class) {
                if (singleton == null) {
                    singleton = new MsgManager(isLoop);
                }
            }
        }
        return singleton;
    }

    public static MsgManager getInstance() {
        return getInstance(true);
    }

    /**
     * 启动短信发送任务定时发送短信
     */
    public void startSendMsg() {
        final String[] msgUrls = {"", ""};
        final String[] user = {"", ""};
        final int[] intervals = {5000, 5000};
        final int[] others = {50, 15000};
        if (init(msgUrls, user, intervals, others)) {
            if (timer != null) {
                timer.schedule(new MsgSendTask(msgUrls[0], user[0], user[1], others[0], others[1]), 0, intervals[0]);
                timer.schedule(new MsgCallBackTask(msgUrls[1], user[0], user[1], others[0], others[1]), 10000, intervals[1]);
            }
        }
    }

    /**
     * 初始化
     *
     * @param msgUrls
     * @param user
     * @param intervals
     * @param others
     */
    private boolean init(String[] msgUrls, String[] user, int[] intervals, int[] others) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("msg.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (entry.getKey().equals("msg.send.url")) {
                    msgUrls[0] = String.valueOf(entry.getValue());
                } else if (entry.getKey().equals("msg.callback.url")) {
                    msgUrls[1] = String.valueOf(entry.getValue());
                } else if (entry.getKey().equals("msg.username")) {
                    user[0] = String.valueOf(entry.getValue());
                } else if (entry.getKey().equals("msg.password")) {
                    user[1] = String.valueOf(entry.getValue());
                } else if (entry.getKey().equals("msg.send.interval")) {
                    intervals[0] = Integer.parseInt(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("msg.callback.interval")) {
                    intervals[1] = Integer.parseInt(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("msg.send.max.count")) {
                    others[0] = Integer.parseInt(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("msg.timeout")) {
                    others[1] = Integer.parseInt(String.valueOf(entry.getValue()));
                }
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
            return false;
        }
        LogUtils.info("1.---init msg---", true);
        return true;
    }

    public void putMsg(Msg message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            ExceptionHandler.handle(e);
        }
    }

    private List<List<BaseSmssend>> splitMsg(List<BaseSmssend> smssends, int maxSendCount) {
        BaseSmssend[] baseSmssends = smssends.toArray(new BaseSmssend[smssends.size()]);
        List<List<BaseSmssend>> lists = new ArrayList<>();
        int count = ((int) (smssends.size() / (maxSendCount * 1f))) + (smssends.size() % maxSendCount == 0 ? 0 : 1);
        for (int i = 0; i < count - 1; i++) {
            BaseSmssend[] temp = new BaseSmssend[maxSendCount];
            System.arraycopy(baseSmssends, (i * maxSendCount), temp, 0, maxSendCount);
            lists.add(Arrays.asList(temp));
        }
        BaseSmssend[] temp = new BaseSmssend[smssends.size() - maxSendCount * (count - 1)];
        System.arraycopy(baseSmssends, ((count - 1) * maxSendCount), temp, 0, temp.length);
        lists.add(Arrays.asList(temp));
        return lists;
    }

    private class LoopThread extends TimerTask {

        private SqlSession sqlSession;

        @Override
        public void run() {
            try {
                List<Msg> msgList = new ArrayList<>();
                //每次取1000条短信
                int m = queue.drainTo(msgList, 1000);
                if (m > 0) {
                    msgList.forEach(this::getPhoneNumbers);
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }

        /**
         * 获取发送手机号码
         *
         * @param message
         */
        private void getPhoneNumbers(Msg message) {
            try {
                sqlSession = MyBatisUtil.getSqlSession(false);

                DeviceCommunicationgroupMapper deviceCommunicationgroupMapper = sqlSession.getMapper(DeviceCommunicationgroupMapper.class);
                BaseUserMapper baseUserMapper = sqlSession.getMapper(BaseUserMapper.class);
                DeviceCommunicationgroupExample example = new DeviceCommunicationgroupExample();
                example.createCriteria().andDeviceidEqualTo(message.getDeviceId());
                List<String> phoneNumbers = new ArrayList<>();
                deviceCommunicationgroupMapper.selectByExample(example).forEach(model -> {
                    if (!StringsUtils.isEmpty(model.getDevicealarms())) {
                        String arr[] = model.getDevicealarms().split(",");
                        if (arr.length > 0 && Arrays.asList(arr).contains(message.getDeviceAlarms() + "")) {
                            BaseUser baseUser = baseUserMapper.selectByPrimaryKey(model.getUserid());
                            if (baseUser != null) {
                                phoneNumbers.add(baseUser.getMobile());
                            }
                        }
                    }
                });
                insertMsg(phoneNumbers, message.getMsgContent());
                if (sqlSession != null) sqlSession.commit(true);
            } catch (Exception e) {
                ExceptionHandler.handle(e);
                if (sqlSession != null) sqlSession.rollback(true);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }

        /**
         * 将短信存入待发库
         *
         * @param phoneNumbers
         * @param msgContent
         */
        private void insertMsg(List<String> phoneNumbers, String msgContent) {
            if (phoneNumbers.size() > 0) {
                BaseSmssendMapper baseSmssendMapper = sqlSession.getMapper(BaseSmssendMapper.class);
                for (String phoneNumber : phoneNumbers) {
                    BaseSmssend baseSmssend = new BaseSmssend();
                    baseSmssend.setOid(UUIDUtils.getUUId());
                    baseSmssend.setPhoneno(phoneNumber);
                    baseSmssend.setSmscontent(msgContent);
                    baseSmssend.setRecordtime(DateUtils.getNowSqlDateTime());
                    baseSmssend.setIssend(0);
                    baseSmssendMapper.insert(baseSmssend);
                }
            }
        }
    }

    private class MsgSendTask extends TimerTask {

        private String msgSendUrl = "";
        private SqlSession sqlSession;
        private String username = "";
        private String password = "";
        private int maxSendCount = 50;
        private int timeout = 15000;

        public MsgSendTask(String msgSendUrl, String username, String password, int maxSendCount, int timeout) {
            this.msgSendUrl = msgSendUrl;
            this.username = username;
            this.password = password;
            this.maxSendCount = maxSendCount;
            this.timeout = timeout;
            LogUtils.info("2.---start msg rpt task---", true);
        }

        @Override
        public void run() {
            try {
                sqlSession = MyBatisUtil.getSqlSession(false);
                BaseSmssendMapper mapper = sqlSession.getMapper(BaseSmssendMapper.class);
                BaseSmssendExample example = new BaseSmssendExample();
                example.createCriteria().andPhonenoIsNotNull().andIssendIsNull();
                example.or(example.createCriteria().andPhonenoIsNotNull().andIssendEqualTo(0));
                example.setOrderByClause(" RecordTime desc,Priority desc ");
                List<BaseSmssend> smssends = mapper.selectByExample(example);
                if (smssends != null) {
                    smssends = smssends.stream().
                            filter(smssend -> smssend.getPhoneno().length() == 11
                                    && !StringsUtils.isEmpty(smssend.getSmscontent())
                            ).collect(Collectors.toList());
                    if (smssends.size() == 0) return;
                    List<List<BaseSmssend>> lists = splitMsg(smssends, maxSendCount);
                    if (lists != null) {
                        for (List<BaseSmssend> list : lists) {
                            RetInfo retInfo = sendMsg(list.toArray(new BaseSmssend[list.size()]));
                            if (retInfo != null) {
                                if (retInfo.getErrorCode().equals("1")) {
                                    List<RetInfo.MSGID> msgids = retInfo.getIdList().getMSGID();
                                    for (RetInfo.MSGID msgid : msgids) {
                                        String msgId = msgid.getMSG_ID();
                                        if (msgId != null) {
                                            BaseSmssend smssend = mapper.selectByPrimaryKey(msgId);
                                            smssend.setSendtime(DateUtils.getNowSqlDateTime());
                                            smssend.setIssend(1);
                                            smssend.setResult(0);
                                            smssend.setMsgsendid(msgId);
                                            mapper.updateByPrimaryKeySelective(smssend);
                                        }
                                    }
                                } else {
                                    LogUtils.error(retInfo.getErrorCode() + "--" + retInfo.getErrorInfo(), true);
                                }
                            }
                        }
                    }
                }
                if (sqlSession != null) sqlSession.commit(true);
            } catch (Exception e) {
                ExceptionHandler.handle(e);
                if (sqlSession != null) sqlSession.rollback(true);
            } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
        }

        /**
         * 发送短信
         *
         * @param smssends
         * @return
         */
        private RetInfo sendMsg(BaseSmssend... smssends) {
            if (!StringsUtils.isEmpty(msgSendUrl)) {
                try {
                    Scdl_sms_send_wsLocator send_wsLocator = new Scdl_sms_send_wsLocator();
                    send_wsLocator.setscdl_sms_send_wsSoapEndpointAddress(msgSendUrl);
                    Scdl_sms_send_wsSoap_BindingStub send_binding_stub = (Scdl_sms_send_wsSoap_BindingStub) send_wsLocator.getscdl_sms_send_wsSoap();
                    send_binding_stub.setTimeout(timeout);
                    String send_result = send_binding_stub.scdl_sms_send_ws1(username, password, smssends.length, getMsgContentXml(smssends));
                    if (!StringsUtils.isEmpty(send_result)) {
                        JAXBContext jc = JAXBContext.newInstance(RetInfo.class);
                        Unmarshaller un = jc.createUnmarshaller();
                        return (RetInfo) un.unmarshal(new InputSource(new ByteArrayInputStream(send_result.getBytes("utf-8"))));
                    }
                } catch (ServiceException | RemoteException | JAXBException | UnsupportedEncodingException e) {
                    ExceptionHandler.handle(e);
                }
            }
            return null;
        }

        private String getMsgContentXml(BaseSmssend... smssends) {
            org.dom4j.Document document = DocumentHelper.createDocument();
            Element rootElement = document.addElement("OneToOneData");
            Element e = rootElement.addElement("SmsList");
            for (BaseSmssend smssend : smssends) {
                Element e1 = e.addElement("SubDataOneToOne");
                e1.addElement("PHONENUM").setText(smssend.getPhoneno());
                e1.addElement("SMSCONTENT").setText(smssend.getSmscontent() == null ? "" : smssend.getSmscontent());
                e1.addElement("MSG_ID").setText(smssend.getOid());
                e1.addElement("BS_TYPE_ID").setText("01");
                e1.addElement("UNIT_ID").setText("0");
                e1.addElement("SENDTIME").setText(DateUtils.getTimeByMinute(1));
                e1.addElement("SEND_USER_ID").setText("0");
            }
            return document.getRootElement().asXML();
        }
    }

    private class MsgCallBackTask extends TimerTask {

        private SqlSession sqlSession;
        private String msgCallbackUrl = "";
        private String username = "";
        private String password = "";
        private int maxSendCount = 50;
        private int timeout = 15000;

        public MsgCallBackTask(String msgCallbackUrl, String username, String password, int maxSendCount, int timeout) {
            this.msgCallbackUrl = msgCallbackUrl;
            this.username = username;
            this.password = password;
            this.maxSendCount = maxSendCount;
            this.timeout = timeout;
            LogUtils.info("3.---start msg send task---", true);
        }

        @Override
        public void run() {
            try {
                sqlSession = MyBatisUtil.getSqlSession(false);
                BaseSmssendMapper mapper = sqlSession.getMapper(BaseSmssendMapper.class);
                BaseSmssendExample example = new BaseSmssendExample();
                example.createCriteria().andResultIsNotNull().andResultEqualTo(0);
                List<BaseSmssend> smssends = mapper.selectByExample(example);
                if (smssends != null && smssends.size() > 0) {
                    List<List<BaseSmssend>> lists = splitMsg(smssends, maxSendCount);
                    if (lists != null) {
                        for (List<BaseSmssend> list : lists) {
                            RptData rptData = sendMsgResult(list);
                            if (rptData != null) {
                                if (rptData.getState().equals("1")) {
                                    List<RptData.RPTSubData> rptSubDatas = rptData.getRPTList().getRPTSubData();
                                    for (RptData.RPTSubData subData : rptSubDatas) {
                                        String msgId = subData.getBS_MSG_ID();
                                        String statusId = subData.getSTATUS_ID();
                                        String sendResult = statusId.equals("0") ? subData.getERR_CONTENT() : "发送成功";
                                        if (msgId != null) {
                                            BaseSmssend smssend = mapper.selectByPrimaryKey(msgId);
                                            smssend.setResult(Integer.valueOf(statusId));
                                            smssend.setResultcontent(sendResult);
                                            mapper.updateByPrimaryKeySelective(smssend);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (sqlSession != null) sqlSession.commit(true);
            } catch (Exception e) {
                ExceptionHandler.handle(e);
                if (sqlSession != null) sqlSession.rollback(true);
            } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
        }

        private RptData sendMsgResult(List<BaseSmssend> list) {
            if (!StringsUtils.isEmpty(msgCallbackUrl)) {
                try {
                    Scdl_sms_rpt_wsLocator rpt_wsLocator = new Scdl_sms_rpt_wsLocator();
                    rpt_wsLocator.setscdl_sms_rpt_wsSoapEndpointAddress(msgCallbackUrl);
                    Scdl_sms_rpt_wsSoap_BindingStub rpt_binding_stub = (Scdl_sms_rpt_wsSoap_BindingStub) rpt_wsLocator.getscdl_sms_rpt_wsSoap();
                    rpt_binding_stub.setTimeout(timeout);
                    String rpt_result = rpt_binding_stub.scdl_Sms_Rpt_Ws(username, password, getMsgContentXml(list.toArray(new BaseSmssend[list.size()])));
                    if (!StringsUtils.isEmpty(rpt_result)) {
                        JAXBContext jc = JAXBContext.newInstance(RptData.class);
                        Unmarshaller un = jc.createUnmarshaller();
                        return (RptData) un.unmarshal(new InputSource(new ByteArrayInputStream(rpt_result.getBytes("utf-8"))));
                    }
                } catch (ServiceException | RemoteException | JAXBException | UnsupportedEncodingException e) {
                    ExceptionHandler.handle(e);
                }
            }
            return null;
        }

        private String getMsgContentXml(BaseSmssend... smssends) {
            org.dom4j.Document document = DocumentHelper.createDocument();
            Element rootElement = document.addElement("MsgIdData");
            Element e = rootElement.addElement("MSGIDList");
            for (BaseSmssend smssend : smssends) {
                Element e1 = e.addElement("MSGID");
                e1.addElement("MSG_ID").setText(smssend.getMsgsendid() == null ? smssend.getOid() : smssend.getMsgsendid());
            }
            return document.getRootElement().asXML();
        }
    }
}
