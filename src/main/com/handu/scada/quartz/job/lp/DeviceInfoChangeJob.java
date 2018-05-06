package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.cache.CacheCmdType;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.DeviceInfochanges;
import main.com.handu.scada.db.bean.DeviceInfochangesExample;
import main.com.handu.scada.db.bean.common.DeviceCacheResult;
import main.com.handu.scada.db.bean.common.DtuCacheResult;
import main.com.handu.scada.db.mapper.DeviceInfochangesMapper;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by 柳梦 on 2018/01/03.
 */
public class DeviceInfoChangeJob extends CommonJob implements BaseJob {

    private SqlSession sqlSession = null;

    @Override
    public String jobName() {
        return "DeviceInfoChangeJob";
    }

    @Override
    public String cronExpression() {
        return "30 2/5 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            String[] ports = Config.getDtuPorts().split(",");

            DeviceInfochangesMapper mapper = sqlSession.getMapper(DeviceInfochangesMapper.class);
            DeviceInfochangesExample example = new DeviceInfochangesExample();
            example.createCriteria().andPortIn(Arrays.stream(ports).map(Integer::parseInt).collect(Collectors.toList()));
            List<DeviceInfochanges> infoChanges = mapper.selectByExample(example);

            //记录添加后修改的设备id
            List<String> dtuOids = new ArrayList<>();
            List<String> deviceOids = new ArrayList<>();
            if (infoChanges != null && infoChanges.size() > 0) {
                infoChanges.forEach(e -> {
                    DeviceTableEnum device = DeviceTableEnum.getDeviceByTableName(e.getTabname());
                    if (device != null) {
                        CacheCmdType c;
                        switch (device.getGroup()) {
                            case DTU:
                                c = CacheCmdType.get(e.getOptype());
                                if (c != null) {
                                    switch (c) {
                                        case DELETE:
                                            MyCacheManager.removeDeviceCacheByDeviceId(e.getKeyid());
                                            break;
                                        default:
                                            dtuOids.add(e.getKeyid());
                                            break;
                                    }
                                }
                                break;
                            case DTU_DEVICE:
                                c = CacheCmdType.get(e.getOptype());
                                if (c != null) {
                                    switch (c) {
                                        case DELETE:
                                            MyCacheManager.removeDtuCacheByDtuId(e.getKeyid());
                                            break;
                                        default:
                                            deviceOids.add(e.getKeyid());
                                            break;
                                    }
                                }
                                break;
                            case SWITCH:
                                break;
                        }
                    }
                });
                CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
                if (dtuOids.size() > 0) {
                    //初始化dtu相关
                    List<DtuCacheResult> dtuList = commonMapper.selectDtuCacheResult(Arrays.asList(ports), dtuOids);
                    if (dtuList != null) {
                        MyCacheManager.updateDtuCacheResult(dtuList);
                        LogUtils.info("init dtu cache result count " + dtuList.size(), true);
                    }
                }
                if (deviceOids.size() > 0) {
                    //初始化设备相关
                    List<DeviceCacheResult> deviceCacheResults = commonMapper.selectDeviceCacheResult(Arrays.asList(ports), deviceOids);
                    if (deviceCacheResults != null) {
                        //初始化附属信息
                        deviceCacheResults = MyCacheManager.getInstance().initAdditionProperty(deviceCacheResults, commonMapper);
                        MyCacheManager.updateDeviceCacheResult(deviceCacheResults);
                        LogUtils.info("init device cache result count " + deviceCacheResults.size(), true);
                    }
                }
                //删除查询到所有记录
                commonMapper.deleteInfoChanges(infoChanges.stream().map(DeviceInfochanges::getOid).collect(Collectors.toList()));
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }
}
