package main.com.handu.scada.quartz.job.lp.lp2007;

import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2017/12/29.
 */
public class DeviceRcdTrialSwitchJob extends CommonJob implements BaseJob {

    @Override
    public boolean isEnable() {
        return false;
    }

    @Override
    public String jobName() {
        return "lp2007 DeviceRcdTrialSwitchJob";
    }

    @Override
    public String cronExpression() {
        return "0 30 0 25 * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //SqlSession sqlSession;
//        if (enable) {
//            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
//            try {
//                sqlSession = MyBatisUtil.getSqlSession(false);
//                DeviceRcdTrialswitchlogMapper mapper = sqlSession.getMapper(DeviceRcdTrialswitchlogMapper.class);
//                Vector<DeviceDtuCacheResult> cacheResults = MyCacheManager.getInstance().getDataByKey(MyCacheManager.DEVICE_DTU_INFO);
//                ConcurrentHashMap<String, String> hashMap = MyCacheManager.getInstance().getDataByKey(MyCacheManager.DEVICE_RCD_TRIAL_SWITCH_LOG);
//                if (cacheResults != null) {
//                    cacheResults.forEach(cacheResult -> {
//                        send(cacheResult, DeviceCmdTypeEnum.SwitchTrip, DeviceTypeEnum.LP);
//                        DeviceRcdTrialswitchlog trial = new DeviceRcdTrialswitchlog();
//                        trial.setOid(UUIDUtils.getUUId());
//                        trial.setRcdid(cacheResult.getDeviceId());
//                        trial.setOperator("System");
//                        trial.setResult(TrialResultType.down.getType());
//                        trial.setDowntime(DateUtils.getNowSqlDateTime());
//                        mapper.insert(trial);
//                        if (!hashMap.contains(cacheResult.getDeviceId())) {
//                            hashMap.put(cacheResult.getDeviceId(), trial.getOid());
//                        }
//                    });
//                }
//                if (sqlSession != null) sqlSession.commit();
//            } catch (Exception e) {
//                if (sqlSession != null) sqlSession.rollback();
//                ExceptionHandler.handle(e);
//            } finally {
//                if (sqlSession != null) sqlSession.close();
//            }
//        }
    }
}
