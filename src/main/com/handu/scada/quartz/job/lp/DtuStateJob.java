package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.business.dtu.DtuDBService;
import main.com.handu.scada.business.dtu.DtuState;
import main.com.handu.scada.business.dtu.DtuStateResult;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.netty.server.dtu.DtuChannelManager;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public class DtuStateJob extends CommonJob implements BaseJob {

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
        return "30 0/2 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (enable) {
            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
            List<DeviceDtuCacheResult> list = null;
            ConcurrentHashMap<String, DeviceDtuCacheResult> cacheResults = MyCacheManager.getInstance().getDeviceDtuCache();
            if (cacheResults != null) {
                synchronized (cacheResults) {
                    list = cacheResults.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
                }
            }
            if (list != null) {
                list.forEach(cacheResult -> {
                    DtuStateResult result = new DtuStateResult(DtuChannelManager.getDeviceChannelIsActive(cacheResult.getDtuAddress()) ? DtuState.ONLINE : DtuState.OFFLINE, cacheResult.getDtuId(), DateUtils.getNowSqlDateTime());
                    DtuDBService.getInstance().push(result);
                });
            }
        }
    }
}
