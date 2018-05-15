package main.com.handu.scada.business;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.exception.ExceptionHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/04/03.
 * 数据入库操作入口
 */
public class DBCmdTask {

    /**
     * 每次取数据的数量
     */
    private int takeSize = 10000;
    /**
     * 提交间隔时间
     */
    private int interval = 10000;

    /**
     * 设备数据遥信遥测入库队列
     */
    private BlockingQueue<DeviceData> dataQueue = new LinkedBlockingDeque<>();

    private static DBCmdTask singleton;

    public static DBCmdTask getInstance() {
        if (singleton == null) {
            synchronized (DBCmdTask.class) {
                if (singleton == null) {
                    singleton = new DBCmdTask();
                }
            }
        }
        return singleton;
    }

    private DBCmdTask() {
        init();
        new Timer().schedule(new DeviceDataTask(), 10000, interval);
    }

    private void init() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("configure.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (entry.getKey().equals("db.takeSize")) {
                    takeSize = Integer.valueOf(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("db.interval")) {
                    interval = Integer.parseInt(String.valueOf(entry.getValue())) * 1000;
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 消息入队
     *
     * @param results
     * @return
     */
    public void push(DeviceData... results) {
        try {
            for (DeviceData result : results) {
                this.dataQueue.put(result);
            }
        } catch (InterruptedException e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 消息出队
     *
     * @return
     */
    private List<DeviceData> take() {
        List<DeviceData> results = new ArrayList<>();
        this.dataQueue.drainTo(results, takeSize);
        return results;
    }

    private class DeviceDataTask extends TimerTask {
        @Override
        public void run() {
            SqlSession sqlSession = null;
            try {
                List<DeviceData> results = take();
                if (results.size() > 0) {
                    sqlSession = MyBatisUtil.getSqlSession();
                    CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
                    results.stream()
                            .collect(Collectors.groupingBy(DeviceData::getClazz, Collectors.toList()))
                            .entrySet()
                            .forEach(e -> {
                                try {
                                    Class<? extends IDBService> clazz = e.getKey();
                                    if (clazz != null) {
                                        IDBService idbService = clazz.newInstance();
                                        List<DeviceData> dataList = e.getValue();
                                        idbService.submit(mapper, dataList);
                                    }
                                } catch (Exception e1) {
                                    ExceptionHandler.handle(e1);
                                }
                            });
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }
    }
}
