package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.business.DBCmdTask;
import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.business.dtu.DtuState;
import main.com.handu.scada.business.dtu.DtuStateResult;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.common.DtuCacheResult;
import main.com.handu.scada.db.service.impl.DeviceDtuHistoryOfflineDBService;
import main.com.handu.scada.db.service.impl.DeviceDtuOfflineDBService;
import main.com.handu.scada.db.service.impl.DeviceDtuOnlineDBService;
import main.com.handu.scada.netty.server.dtu.DtuChannelManager;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public class DtuStateJob extends BaseDtuCommand implements BaseJob {

    @Override
    public String jobName() {
        return "DtuStateJob";
    }

    @Override
    public String cronExpression() {
        return "20 1/5 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ConcurrentHashMap<String, DtuCacheResult> cacheResults = MyCacheManager.getDtuCacheMap();
        List<DtuCacheResult> list = null;
        if (cacheResults != null) {
            synchronized (cacheResults) {
                list = cacheResults
                        .entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());
            }
        }
        if (list != null) {
            list.forEach(cacheResult -> {
                //先获取当前dtu的在线状态
                boolean isOnline1 = cacheResult.isDtuIsOnline();
                //判断连接的有效性
                boolean isOnline2 = DtuChannelManager.getDeviceChannelIsActive(cacheResult.getDtuAddress());
                //开始在线，现在掉线
                if (isOnline1 && !isOnline2) {
                    DtuStateResult result1 = new DtuStateResult(DtuState.OFFLINE, cacheResult.getDtuAddress(), DateUtils.getNowSqlDateTime());
                    result1.setDtuId(cacheResult.getDtuId());
                    DeviceData data1 = new DeviceData(result1, DeviceDtuOfflineDBService.class);
                    DtuStateResult result2 = new DtuStateResult(DtuState.OFFLINE, cacheResult.getDtuAddress(), DateUtils.getNowSqlDateTime());
                    result2.setDtuId(cacheResult.getDtuId());
                    DeviceData data2 = new DeviceData(result2, DeviceDtuHistoryOfflineDBService.class);
                    DBCmdTask.getInstance().push(data1, data2);
                }
                //开始不在线，现在在线
                else if (!isOnline1 && isOnline2) {
                    DtuStateResult result = new DtuStateResult(DtuState.ONLINE, cacheResult.getDtuAddress(), DateUtils.getNowSqlDateTime());
                    result.setDtuId(cacheResult.getDtuId());
                    DeviceData data = new DeviceData(result, DeviceDtuOnlineDBService.class);
                    DBCmdTask.getInstance().push(data);
                }
                //更新缓存中dtu状态
                cacheResult.setDtuIsOnline(isOnline2);
            });
        }
    }
}
