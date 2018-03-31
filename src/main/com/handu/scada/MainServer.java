package main.com.handu.scada;

import main.com.handu.scada.business.dtu.DtuUpdateUtil;
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
import java.util.*;

/**
 * Created by 柳梦 on 2017/12/15.
 */
public class MainServer {

    private static int START_SQL = 1;
    public static int START_NO_SQL = 2;
    public static int START_TYPE = START_SQL;
    private static long startTime;
    private static long endTime;
    private static Timer timer;

    public static void main(String[] strings) {
        start();
    }

    private static void start() {
        initUpdate();
        LogUtils.info("please choose start by (s)ql or (n)oSql:", true);
        Scanner scanner = new Scanner(System.in);
        timer = new Timer();
        //程序启动没有选择，默认选择有数据库启动
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                START_TYPE = START_SQL;
                startBySql();
                scanner.close();
            }
        }, 30 * 1000);
        String command;
        while ((command = scanner.next()) != null) {
            switch (command) {
                case Command.START_SQL:
                    timer.cancel();
                    timer = null;
                    START_TYPE = START_SQL;
                    startBySql();
                    scanner.close();
                    break;
                case Command.START_NOSQL:
                    timer.cancel();
                    timer = null;
                    START_TYPE = START_NO_SQL;
                    startNoSql();
                    scanner.close();
                    break;
                default:
                    LogUtils.error("please choose start by (s)ql) or (n)oSql:");
                    break;
            }
        }
    }

    private static void startBySql() {
        LogUtils.info("start by sql:");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        initProperties();
        if (initCache()) {
            endTime = System.currentTimeMillis();
            timer.cancel();
            timer = null;
            LogUtils.info((endTime - startTime) % 1000 == 0 ? ((endTime - startTime) / 1000) + "seconds" : ((endTime - startTime) / 1000) + "seconds" + (endTime - startTime) % 1000 + "millSeconds", true);
            initSubscriber();
            initJob();
            initInput();
            initChannel();
        } else {
            endTime = System.currentTimeMillis();
            timer.cancel();
            timer = null;
        }
    }

    private static void startNoSql() {
        LogUtils.info("start by no sql:");
        initProperties();
        initSubscriber();
        initInput();
        initChannel();
    }

    /**
     * 初始化dtu更新相关文件
     */
    private static void initUpdate() {
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
    private static void initInput() {
        new Thread(MainServer::commandInput).start();
    }

    /**
     * 初始化运行参数
     */
    private static void initProperties() {
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
            inputStream = Resources.getResourceAsStream("configure.properties");
            properties = new Properties();
            properties.load(inputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (entry.getKey().equals("dtu.port")) {
                    Config.setDtuPorts(AesUtils.decrypt(String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("cmd.port")) {
                    Config.setCmdPort(AesUtils.decrypt(String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("switch.port")) {
                    Config.setSwitchPorts(AesUtils.decrypt(String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("dtu.heartbeat")) {
                    Config.setHeartBeat(Integer.valueOf((String) entry.getValue()));
                }
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 初始化缓存
     */
    private static boolean initCache() {
        LogUtils.info("-----2.init cache-------", true);
        startTime = System.currentTimeMillis();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endTime = System.currentTimeMillis();
                LogUtils.info((endTime - startTime) % 1000 == 0 ? ((endTime - startTime) / 1000) + "seconds" : ((endTime - startTime) / 1000) + "seconds" + (endTime - startTime) % 1000 + "millSeconds", true);
            }
        }, 0, 1000);
        return MyCacheManager.getInstance().init();
    }

    /**
     * 订阅所有发布
     */
    private static void initSubscriber() {
        LogUtils.info("-----3.init subscriber-------", true);
        EventManager.getInstance().injectSubscriber();
    }

    /**
     * 初始化所有任务并开始执行
     */
    private static void initJob() {
        LogUtils.info("-----4.init job-------", true);
        QuartzManager.getInstance().start();
    }

    /**
     * 初始化通道
     */
    private static void initChannel() {
        LogUtils.info("-----5.init channel-------", true);
        //启动各种设备端口
        List<Ports> portsList = new ArrayList<>();
        if (!StringsUtils.isEmpty(Config.getCmdPort())) {
            portsList.add(new Ports(Config.getCmdPort().split(","), PortType.CMD));
        }
        if (!StringsUtils.isEmpty(Config.getDtuPorts())) {
            portsList.add(new Ports(Config.getDtuPorts().split(","), PortType.DTU));
        }
        if (!StringsUtils.isEmpty(Config.getSwitchPorts())) {
            portsList.add(new Ports(Config.getSwitchPorts().split(","), PortType.SWITCH));
        }
        TcpServer.getInstance().start(Config.getHost(), portsList);
    }

    /**
     * 监听控制台输入
     */
    private static void commandInput() {
        Scanner scanner = new Scanner(System.in);
        String command;
        while ((command = scanner.nextLine()) != null) {
            Command.command(command);
        }
    }
}
