package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.db.mapper.DeviceInfochangesMapper;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Arrays;
import java.util.List;


/**
 * Created by 柳梦 on 2018/01/03.
 */
public class DeviceInfoChangeJob extends CommonJob implements BaseJob {

    private SqlSession sqlSession;

    @Override
    public String jobName() {
        return "lp DeviceInfoChangeJob";
    }

    @Override
    public String cronExpression() {
        return "0 0 0/2 * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (enable) {
            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
            try {
                sqlSession = MyBatisUtil.getSqlSession(true);
                DeviceInfochangesMapper mapper = sqlSession.getMapper(DeviceInfochangesMapper.class);
                //List<DeviceInfochanges> infochanges = mapper.selectByExample(new DeviceInfochangesExample());
                //if (infochanges != null && infochanges.size() > 0) {
                CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
                //初始化设备相关
                List<DeviceDtuCacheResult> list = commonMapper.selectDeviceDtuCacheResult(Arrays.asList(Config.getDtuPorts().split(",")));
                if (list != null) {
                    MyCacheManager.getInstance().putDeviceDtuCacheResult(list);
                    LogUtils.info("init device cache result count " + list.size(), true);
                    //infochanges.forEach(deviceInfochanges -> mapper.deleteByPrimaryKey(deviceInfochanges.getOid()));
                }
                //}
                //if (sqlSession != null) sqlSession.commit(true);
            } catch (Exception e) {
                //if (sqlSession != null) sqlSession.rollback(true);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }
    }

    @Override
    public void isEnable(boolean isEnable) {
        enable = isEnable;
    }
}
