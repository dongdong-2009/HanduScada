package main.com.handu.scada.business.start;

import main.com.handu.scada.business.dtu.DtuUpdateUtil;
import main.com.handu.scada.business.utpc.AnalyzeRecord;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.PortType;
import main.com.handu.scada.netty.server.Ports;
import main.com.handu.scada.netty.server.TcpServer;
import main.com.handu.scada.quartz.QuartzManager;
import main.com.handu.scada.utils.AesUtils;
import main.com.handu.scada.utils.Command;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;
import org.apache.ibatis.io.Resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 柳梦 on 2018/05/02.
 * 启动初始化类
 */
public class Start {

    private long startTime;
    private long endTime;
    private Timer timer;
    private Scanner scanner = new Scanner(System.in);
    private boolean isSqlStart = true;

    public Start() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new CommandInput());
    }

    private void startSql() {
        LogUtils.info("start with sql:", true);
        if (initCache()) {
            endTime = System.currentTimeMillis();
            timer.cancel();
            timer = null;
            LogUtils.info((endTime - startTime) % 1000 == 0 ? ((endTime - startTime) / 1000) + "s" : ((endTime - startTime) / 1000) + "s" + (endTime - startTime) % 1000 + "ms", true);
            initJob();
            initChannel();
        } else {
            endTime = System.currentTimeMillis();
            timer.cancel();
            timer = null;
        }
    }

    private void startNoSql() {
        LogUtils.info("start with no sql:", true);
        initChannel();
    }

    /**
     * 初始化dtu更新相关文件
     */
    private void initUpdate() {
        try {
            String updatePath = DtuUpdateUtil.UPDATE_PATH + File.separator + DtuUpdateUtil.DEVICE_ADDRESS_NAME;
            File file = new File(updatePath);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                boolean b = fileParent.mkdirs();
                if (!b) return;
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 启动输入监测
     */
    public void start() {
        initUpdate();
        initProperties();
        initSubscriber();
        if (isSqlStart) {
            startSql();
        } else {
            startNoSql();
        }
    }

    /**
     * 初始化运行参数
     */
    private void initProperties() {
        LogUtils.info("-----1.init properties-------", true);
        try {
            InputStream inputStream = Resources.getResourceAsStream("log4j.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().equals("isDebug"))
                    .forEach(entry ->
                            Config.isDebug = Boolean.parseBoolean(String.valueOf(entry.getValue()))
                    );
            Resources.setCharset(Charset.forName("UTF-8"));
            inputStream = Resources.getResourceAsStream("configure.properties");
            //中文乱码
            InputStreamReader reader = new InputStreamReader(inputStream, "gbk");
            properties = new Properties();
            properties.load(reader);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (entry.getKey().equals("dtu.port")) {
                    Config.setDtuPorts(AesUtils.decrypt(String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("cmd.port")) {
                    Config.setCmdPort(AesUtils.decrypt(String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("dtu.heartbeat")) {
                    Config.setHeartBeat(Integer.valueOf(String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("system.name")) {
                    Config.setSystemName(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("utpc.rate")) {
                    AnalyzeRecord.UTPC_RATE = Double.parseDouble((String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("normal.voltage")) {
                    AnalyzeRecord.NORMAL_VOLTAGE = Double.parseDouble((String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("lowVoltage.rate")) {
                    AnalyzeRecord.LOW_VOLTAGE_RATE = Double.parseDouble((String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("start.sql")) {
                    this.isSqlStart = Boolean.parseBoolean(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("netty.timeout")) {
                    Config.setTimeout(Integer.parseInt(String.valueOf(entry.getValue())));
                } else if (String.valueOf(entry.getKey()).startsWith("device101")) {
                    Config.setDevice101Ports(AesUtils.decrypt(String.valueOf(entry.getValue())));
                }
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 初始化缓存
     */
    private boolean initCache() {
        LogUtils.info("-----3.init cache-------", true);
        startTime = System.currentTimeMillis();
        timer = new Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                endTime = System.currentTimeMillis();
                LogUtils.info((endTime - startTime) % 1000 == 0 ? ((endTime - startTime) / 1000) + "s" : ((endTime - startTime) / 1000) + "s" + (endTime - startTime) % 1000 + "ms", true);
            }
        }, 0, 1000);
        return MyCacheManager.getInstance().init();
    }

    /**
     * 订阅所有发布
     */
    private void initSubscriber() {
        LogUtils.info("-----2.init subscriber-------", true);
        EventManager.getInstance().injectSubscriber();
    }

    /**
     * 初始化所有任务并开始执行
     */
    private void initJob() {
        LogUtils.info("-----4.init job-------", true);
        QuartzManager.getInstance().start();
    }

    /**
     * 初始化通道
     */
    private void initChannel() {
        LogUtils.info("-----5.init channel-------", true);
        //启动各种设备端口
        List<Ports> portsList = new ArrayList<>();
        if (!StringsUtils.isEmpty(Config.getCmdPort())) {
            portsList.add(new Ports(Config.getCmdPort().split(","), PortType.CMD));
        }
        if (!StringsUtils.isEmpty(Config.getDtuPorts())) {
            portsList.add(new Ports(Config.getDtuPorts().split(","), PortType.DTU));
        }
        if (!StringsUtils.isEmpty(Config.getDevice101Ports())) {
            portsList.add(new Ports(Config.getDevice101Ports().split(","), PortType.DEVICE101));
        }
        TcpServer.getInstance().start(Config.getHost(), portsList);
    }


    private class CommandInput implements Runnable {
        @Override
        public void run() {
            String command;
            while ((command = scanner.nextLine()) != null) {
                Command.command(command);
            }
        }
    }
}
