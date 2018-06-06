package main.com.handu.scada.business.message;

import main.com.handu.scada.business.message.client.rpt.Scdl_sms_rpt_wsLocator;
import main.com.handu.scada.business.message.client.rpt.Scdl_sms_rpt_wsSoap_BindingStub;
import main.com.handu.scada.business.message.client.send.Scdl_sms_send_wsLocator;
import main.com.handu.scada.business.message.client.send.Scdl_sms_send_wsSoap_BindingStub;
import main.com.handu.scada.db.bean.BaseSmssend;
import main.com.handu.scada.db.bean.BaseSmssendExample;
import main.com.handu.scada.db.mapper.BaseSmssendMapper;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.DBServiceUtil;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.*;
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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 柳梦 on 2017/12/29.
 * 短信发送
 */
public class MsgManager extends DBServiceUtil {

    private BlockingQueue<Msg> queue;
    private static MsgManager singleton;
    private Timer timer;

    private MsgManager(boolean isLoop) {
        //开始循环短信入库
        if (isLoop) {
            timer = new Timer();
            queue = new LinkedBlockingQueue<>();
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
        timer = new Timer();
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
            LogUtils.error(message.toString());
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
                    insertMsg(msgList);
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }

        /**
         * 获取发送手机号码
         * 并存入短信代发库
         *
         * @param messages
         */
        private void insertMsg(List<Msg> messages) {
            try {
                sqlSession = MyBatisUtil.getSqlSession();
                CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
                //取出设备id
                List<String> deviceIds = messages
                        .stream()
                        .collect(Collectors.groupingBy(Msg::getDeviceId, Collectors.toList()))
                        .entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
                //找到通讯分组和短信发送人和电话号码
                List<MsgAdditionProperty> propertyList = mapper.selectMsgAdditionProperty(deviceIds);
                //过滤掉告警类型为空短信号码不符合规范的按照设备id分组
                Map<String, List<MsgAdditionProperty>> propertyMap = propertyList
                        .stream()
                        .filter(e -> e.getAlarms() != null && StringsUtils.isNotEmptyAndValidLength(e.getPhone(), 11))
                        .collect(Collectors.groupingBy(MsgAdditionProperty::getDeviceId, Collectors.toList()));
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT INTO base_smssend( Oid, DeviceId, DeviceTableName, PhoneNo, SmsContent, RecordTime, IsSend ) VALUES ");
                int i[] = {0};
                messages.forEach(msg -> {
                    String deviceId = msg.getDeviceId();
                    List<MsgAdditionProperty> p = propertyMap.get(deviceId);
                    if (p != null) {
                        TxtUtils.getInstance().alarm(msg.getDeviceId() + "--" + msg.getMsgContent() + "--" + p.stream().map(e -> e.getName() + "--" + e.getPhone()).collect(Collectors.joining(",")));
                        if (p.size() > 0) {
                            p.stream()
                                    .filter(e -> Arrays.stream(e.getAlarms().split(",")).anyMatch(s -> Objects.equals(s, msg.getDeviceAlarms() + "")))
                                    .forEach(property -> {
                                                sb.append(getStartColumn(UUIDUtils.getUUId()))
                                                        .append(getColumn(msg.getDeviceId()))
                                                        .append(getColumn(msg.getDeviceTableName()))
                                                        .append(getColumn(property.getPhone()))
                                                        .append(getColumn(msg.getMsgContent()))
                                                        .append(getColumn(DateUtils.getNowSqlDateTime()))
                                                        .append(getEndColumn(0))
                                                        .append(",");
                                                i[0]++;
                                            }
                                    );

                        }
                    } else {
                        TxtUtils.getInstance().alarm(msg.getDeviceId() + "--" + msg.getMsgContent() + "--未找到短信接收人和电话号码--");
                    }
                });
                if (i[0] > 0) {
                    sb.deleteCharAt(sb.lastIndexOf(","));
                    mapper.insertBySql(sb.toString());
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
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
            this.msgSendUrl = AesUtils.decrypt(msgSendUrl);
            this.username = AesUtils.decrypt(username);
            this.password = AesUtils.decrypt(password);
            this.maxSendCount = maxSendCount;
            this.timeout = timeout;
            LogUtils.info("2.---start msg rpt task---", true);
        }

        @Override
        public void run() {
            try {
                sqlSession = MyBatisUtil.getSqlSession();
                BaseSmssendMapper mapper = sqlSession.getMapper(BaseSmssendMapper.class);
                BaseSmssendExample example = new BaseSmssendExample();
                example.createCriteria().andIssendIsNull();
                example.or(example.createCriteria().andIssendEqualTo(0));
                example.setOrderByClause(" RecordTime desc,Priority desc ");
                List<BaseSmssend> smssends = mapper.selectByExample(example);
                if (smssends != null && smssends.size() > 0) {
                    //第一步获取设备id不为空的短信
                    List<BaseSmssend> smssends1 = smssends.stream().
                            filter(smssend -> smssend.getPhoneno().length() == 11
                                    && StringsUtils.isNotEmpty(smssend.getSmscontent())
                                    && StringsUtils.isNotEmpty(smssend.getDeviceid())
                            ).collect(Collectors.toList());
                    //以下用于获取设备的名称
                    Map<String, String> map = new HashMap<>();
                    if (smssends1.size() > 0) {
                        for (Map.Entry<String, List<BaseSmssend>> e : smssends1
                                .stream()
                                .collect(Collectors.groupingBy(BaseSmssend::getDevicetablename))
                                .entrySet()) {
                            String tableName = e.getKey();
                            String str = e.getValue().stream().map(e1 -> "'" + e1.getDeviceid() + "'").collect(Collectors.joining(","));
                            mapper.selectListBySql("select Oid as deviceId,Name as name from " + tableName + " where Oid in(" + str + ")")
                                    .forEach(e2 -> {
                                        String deviceId = (String) e2.get("deviceId");
                                        String name = (String) e2.get("name");
                                        map.put(deviceId, name);
                                    });
                        }
                        smssends1 = smssends1.stream().map(e -> {
                            String content = e.getSmscontent();
                            String name = map.get(e.getDeviceid());
                            content = content.replace("[**]", "[" + name + "设备]");
                            e.setSmscontent(content);
                            return e;
                        }).collect(Collectors.toList());
                    }
                    //第二步获取设备id为空的短信
                    List<BaseSmssend> smssends2 = smssends.stream().
                            filter(smssend -> smssend.getPhoneno().length() == 11
                                    && StringsUtils.isNotEmpty(smssend.getSmscontent())
                                    && StringsUtils.isEmpty(smssend.getDeviceid())
                            ).collect(Collectors.toList());
                    //第三部将两个集合合并
                    smssends = Stream.of(smssends1, smssends2).flatMap(Collection::stream).collect(Collectors.toList());
                    List<List<BaseSmssend>> lists = splitMsg(smssends, maxSendCount);
                    if (lists != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("insert into base_smssend( Oid, IsSend, SendTime, Result, MsgSendId ) values ");
                        int i = 0;
                        for (List<BaseSmssend> list : lists) {
                            RetInfo retInfo = sendMsg(list.toArray(new BaseSmssend[list.size()]));
                            if (retInfo != null) {
                                if (retInfo.getErrorCode().equals("1")) {
                                    List<RetInfo.MSGID> msgids = retInfo.getIdList().getMSGID();
                                    for (RetInfo.MSGID msgid : msgids) {
                                        String msgId = msgid.getMSG_ID();
                                        if (msgId != null) {
                                            sb.append(getStartColumn(msgId))
                                                    .append(getColumn(1))
                                                    .append(getColumn(DateUtils.dateToStr(DateUtils.getNowSqlDateTime())))
                                                    .append(getColumn(0))
                                                    .append(getEndColumn(msgId))
                                                    .append(",");
                                            i++;
                                        }
                                    }

                                } else {
                                    TxtUtils.getInstance().error(retInfo.getErrorCode() + "--" + retInfo.getErrorInfo());
                                }
                            }
                        }
                        if (i > 0) {
                            sb.deleteCharAt(sb.lastIndexOf(","));
                            sb.append(" on duplicate key update Oid=values(Oid),IsSend=values(IsSend),SendTime=values(SendTime),Result=values(Result),MsgSendId=values(MsgSendId) ");
                            mapper.insertBySql(sb.toString());
                        }
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
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
                e1.addElement("BS_TYPE_ID").setText("018");
                e1.addElement("UNIT_ID").setText("SC001.017");
                e1.addElement("SENDTIME").setText(DateUtils.getTimeByMinute(1));
                e1.addElement("SEND_USER_ID").setText(username);
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
            this.msgCallbackUrl = AesUtils.decrypt(msgCallbackUrl);
            this.username = AesUtils.decrypt(username);
            this.password = AesUtils.decrypt(password);
            this.maxSendCount = maxSendCount;
            this.timeout = timeout;
            LogUtils.info("3.---start msg send task---", true);
        }

        @Override
        public void run() {
            try {
                sqlSession = MyBatisUtil.getSqlSession();
                BaseSmssendMapper mapper = sqlSession.getMapper(BaseSmssendMapper.class);
                BaseSmssendExample example = new BaseSmssendExample();
                example.createCriteria().andResultIsNotNull().andResultEqualTo(0);
                List<BaseSmssend> smssends = mapper.selectByExample(example);
                if (smssends != null && smssends.size() > 0) {
                    List<List<BaseSmssend>> lists = splitMsg(smssends, maxSendCount);
                    if (lists != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("insert into base_smssend (Oid, Result, ResultContent) values ");
                        int i = 0;
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
                                            sb.append(getStartColumn(msgId))
                                                    .append(getColumn(Integer.valueOf(statusId)))
                                                    .append(getEndColumn(sendResult))
                                                    .append(",");
                                            i++;
                                        }
                                    }
                                } else {
                                    TxtUtils.getInstance().error(rptData.getErrorCode() + "--" + rptData.getErrorInfo());
                                }
                            }
                        }
                        if (i > 0) {
                            sb.deleteCharAt(sb.lastIndexOf(","));
                            sb.append(" on duplicate key update Oid=values(Oid),Result=values(Result),ResultContent=values(ResultContent)");
                            mapper.insertBySql(sb.toString());
                        }
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
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
