package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.DeviceHistoryStaterecord;
import main.com.handu.scada.db.bean.DeviceStaterecord;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.db.mapper.DeviceHistoryStaterecordMapper;
import main.com.handu.scada.db.mapper.DeviceStaterecordMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.dtu.DtuChannelManager;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.UUIDUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public class DtuStateJob extends CommonJob implements BaseJob {

    private SqlSession sqlSession;

    private class Dtu {

        Dtu(String dtuId, boolean isOnline) {
            this.dtuId = dtuId;
            this.isOnline = isOnline;
        }

        private String dtuId;
        private boolean isOnline;

        String getDtuId() {
            return dtuId;
        }

        boolean isOnline() {
            return isOnline;
        }
    }

    @Override
    public void isEnable(boolean isEnable) {
        enable = isEnable;
    }

    @Override
    public String jobName() {
        return "lp DtuStateJob";
    }

    @Override
    public String cronExpression() {
        return "0 0/2 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (enable) {
            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
            try {
                List<Dtu> list = new ArrayList<>();
                Vector<DeviceDtuCacheResult> cacheResults = MyCacheManager.getInstance().getDataByKey(MyCacheManager.DEVICE_DTU_INFO);
                cacheResults.forEach(result -> list.add(new Dtu(result.getDtuId(), DtuChannelManager.getDeviceChannelIsActive(result.getDtuAddress()))));
                if (list.size() > 0) {
                    sqlSession = MyBatisUtil.getSqlSession(false);
                    DeviceStaterecordMapper mapper = sqlSession.getMapper(DeviceStaterecordMapper.class);
                    for (Dtu dtu : list) {
                        String dtuId = dtu.getDtuId();
                        boolean isOnline = dtu.isOnline();
                        DeviceStaterecord staterecord = mapper.selectByPrimaryKey(dtuId);
                        if (staterecord != null) {
                            if (!isOnline) {
                                staterecord.setUnlinetime(DateUtils.getNowSqlDateTime());
                                staterecord.setDescription("离线");
                                staterecord.setState(2);
                                mapper.updateByPrimaryKeySelective(staterecord);
                            } else {
                                staterecord.setDescription("在线");
                                staterecord.setState(1);
                                mapper.updateByPrimaryKeySelective(staterecord);
                            }
                        } else {
                            if (!isOnline) {
                                staterecord = new DeviceStaterecord();
                                staterecord.setDeviceid(dtuId);
                                staterecord.setRecordid(dtuId);
                                staterecord.setDevicetablename("device_dtu");
                                staterecord.setState(2);
                                staterecord.setDescription("离线");
                                staterecord.setUnlinetime(DateUtils.getNowSqlDateTime());
                                mapper.insert(staterecord);

                                DeviceHistoryStaterecordMapper historyStaterecordMapper = sqlSession.getMapper(DeviceHistoryStaterecordMapper.class);
                                DeviceHistoryStaterecord historyStaterecord = new DeviceHistoryStaterecord();
                                historyStaterecord.setRecordid(UUIDUtils.getUUId());
                                historyStaterecord.setDeviceid(staterecord.getDeviceid());
                                historyStaterecord.setState(2);
                                historyStaterecord.setDevicetablename("device_dtu");
                                historyStaterecord.setDescription("离线");
                                historyStaterecord.setOnlinetime(staterecord.getOnlinetime());
                                historyStaterecord.setUnlinetime(staterecord.getUnlinetime());
                                historyStaterecord.setDuration(String.valueOf(DateUtils.getDiffSeconds(staterecord.getUnlinetime(), new Date())));
                                historyStaterecordMapper.insert(historyStaterecord);
                            } else {
                                staterecord = new DeviceStaterecord();
                                staterecord.setDeviceid(dtuId);
                                staterecord.setRecordid(dtuId);
                                staterecord.setDevicetablename("device_dtu");
                                staterecord.setState(1);
                                staterecord.setDescription("在线");
                                mapper.insert(staterecord);
                            }
                        }
                    }
                }
                if (sqlSession != null) sqlSession.commit(true);
            } catch (Exception e) {
                if (sqlSession != null) sqlSession.rollback(true);
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }
    }
}
