package main.com.handu.scada.quartz.job.hd4g;

import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.quartz.utils.DtuCommand;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/04/09.
 * 集中器最后一次心跳时间
 */
public class ConcentratorHeartbeatTimeJob extends CommonJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (enable) {
            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
            ConcurrentHashMap<String, DeviceDtuCacheResult> cacheResults = MyCacheManager.getInstance().getDeviceDtuCache();
            if (cacheResults != null) {
                Map<String, Long> dtuAddresses;
                synchronized (cacheResults) {
                    dtuAddresses = cacheResults
                            .entrySet()
                            .stream()
                            .collect(Collectors.groupingBy(o -> o.getValue().getDtuAddress(), Collectors.counting()));
                }
                if (dtuAddresses != null) {
                    dtuAddresses
                            .entrySet()
                            .stream()
                            .map(Map.Entry::getKey)
                            .forEach(s -> DtuCommand.getInstance().readConcentratorHeartbeatTime(s));
                }
            }
            send(DeviceTypeEnum.DTU, DeviceCmdTypeEnum.ConcentratorHeartbeatTime);
        }
    }

    @Override
    public void isEnable(boolean isEnable) {
        this.enable = isEnable;
    }

    @Override
    public String jobName() {
        return "4g ConcentratorHeartbeatTimeJob";
    }

    @Override
    public String cronExpression() {
        return "0 0/15 * * * ?";
    }
}
