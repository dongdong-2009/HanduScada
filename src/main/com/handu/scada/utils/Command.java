package main.com.handu.scada.utils;

import main.com.handu.scada.business.dtu.DtuUpdateUtil;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.QuartzManager;
import main.com.handu.scada.quartz.utils.DtuCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/01/11.
 */
public class Command {

    private static final ArrayList<String> menuList = new ArrayList<String>() {{
        add("1." + MENU);
        add("2." + UPDATE);
        add("3." + CMD);
        add("4." + DEBUG);
        add("5." + QUARTZ);
        add("6." + ENCRYPT);
        add("7." + DECRYPT);
        add("8." + DTU_INFO);
        add("9." + RESTART);
        add("10." + NUMBER);
        add("11." + BROAD_CAST_TIME);
        add("12." + SQL);
        add("13." + SIGNAL_STRENGTH);
        add("14." + COMMUNICATION_MODEL);
        add("15." + EXPORT);
        add("16." + AFN0C25);
    }};
    private static final ArrayList<String> cmdList = new ArrayList<String>() {{
        add("cmd-1000(baseData)");
        add("cmd-1001(dateTime)");
        add("cmd-1002(runState)");
        add("cmd-1003(tripEventRecord)");
        add("cmd-1004(readControlWord)");
        add("cmd-1005(temperature)");
        add("cmd-1006(fallSwitch)");
        add("cmd-1007(readPostalAddress)");
        add("cmd-1008(readAFN0C25)");
    }};

    /**
     * 通信模式
     */
    private static final String COMMUNICATION_MODEL = "communication model --> cm4(G)-dtuAddress,cm2(G)-dtuAddress,cma(uto)-dtuAddress";
    //4G模式
    private static final String COMMUNICATION_MODEL_4G = "cm4-*";
    //2G模式
    private static final String COMMUNICATION_MODEL_2G = "cm2-*";
    //自动模式
    private static final String COMMUNICATION_MODEL_AUTO = "cma-*";

    /**
     * 信号强度
     */
    private static final String SIGNAL_STRENGTH = "signal strength --> c(ollect)ss-dtuAddress,r(ead)ss-dtuAddress)";
    private static final String SIGNAL_R = "rss-*";
    private static final String SIGNAL_C = "css-*";

    private static final String AFN0C25 = "HM_AFN0C25 --> afn25-dtuAddress";
    private static final String AFN0C25_Y = "afn25-*";

    private static final String MENU = "menu";

    private static final String CMD = "cmd";
    private static final String CMD_Y = "cmd-*";

    private static final String DEBUG = "log --> l-o(pen),l-(c)lose";
    private static final String DEBUG_Y = "l-o";
    private static final String DEBUG_N = "l-c";

    private static final String QUARTZ = "quartz --> q-(o)pen,q-(c)lose,q-(r)estart";
    private static final String QUARTZ_Y = "q-o";
    private static final String QUARTZ_N = "q-c";
    private static final String QUARTZ_R = "q-r";

    private static final String UPDATE = "update --> update-(s)top,update-(500~10000) start";
    private static final String UPDATE_N = "update-s";
    private static final String UPDATE_Y = "update-*";

    private static final String DECRYPT = "dec --> dec-str";
    private static final String DECRYPT_Y = "dec-*";

    private static final String ENCRYPT = "enc --> enc-str";
    private static final String ENCRYPT_Y = "enc-*";

    public static final String START_SQL = "s";
    public static final String START_NOSQL = "n";

    private static final String RESTART = "restart --> rs-dtuAddress";
    private static final String DTU_RESTART_Y = "rs-*";

    private static final String DTU_INFO = "info --> if-dtuAddress";
    private static final String DTU_INFO_Y = "if-*";

    private static final String NUMBER = "num";
    private static final String EXPORT = "dtu online export --> export";
    private static final String EXPORT_Y = "export";

    private static final String SQL = "sql execute --> sql-*";
    private static final String SQL_Y = "sql-*";

    private static final String BROAD_CAST_TIME = "broadcastTime --> bt-dtuAddress";
    private static final String TIME = "bt-*";

    public static void command(String command) {
        switch (command) {
            case CMD:
                String cmd = "";
                for (String s : cmdList) {
                    if (!cmd.equals("")) cmd += ",\n";
                    else cmd += "\n";
                    cmd += s;
                }
                LogUtils.info(cmd, true);
                return;
            case DEBUG_Y:
                Config.isDebug = true;
                LogUtils.info("log has " + (Config.isDebug ? "opened" : "closed"), true);
                return;
            case DEBUG:
                LogUtils.info("log has " + (Config.isDebug ? "opened" : "closed") + ",l-(o)pen,l-(c)losesss", true);
                return;
            case DEBUG_N:
                Config.isDebug = false;
                LogUtils.info("log has " + (Config.isDebug ? "opened" : "closed"), true);
                return;
            case QUARTZ:
                LogUtils.info("quartz is " + (QuartzManager.getInstance().isStart() ? "opened" : "closed") + ",q-(o)pen,q-(c)lose,q-(r)start", true);
                return;
            case QUARTZ_Y:
                if (QuartzManager.getInstance().start())
                    LogUtils.info("quartz has " + (QuartzManager.getInstance().isStart() ? "opened" : "closed"), true);
                return;
            case QUARTZ_N:
                if (QuartzManager.getInstance().stop())
                    LogUtils.info("quartz has " + (QuartzManager.getInstance().isStart() ? "opened" : "closed"), true);
                return;
            case QUARTZ_R:
                if (QuartzManager.getInstance().restart())
                    LogUtils.info("quartz is restart: state has " + (QuartzManager.getInstance().isStart() ? "opened" : "closed"), true);
                return;
            case NUMBER:
                DtuCommand.getInstance().getClientNumber();
                return;
            case UPDATE_N:
                DtuUpdateUtil.stop();
                return;
            case EXPORT_Y:
                DtuCommand.getInstance().exportDtuOnline2Txt();
                return;
        }

        if (command.startsWith(MENU)) {
            int index = 0;
            String str = command.replace(MENU, "");
            try {
                if (StringsUtils.isNotEmpty(str)) index = Integer.parseInt(str);
            } catch (Exception e) {
                LogUtils.error("not find command!", true);
                return;
            }
            //分页
            List<String> menu = menuList.stream().skip(index * 10).limit(10).collect(Collectors.toList());
            str = "\n--current page " + (index + 1) + "," + "total menus " + menuList.size() + "--";
            for (String s : menu) {
                str += "\n" + s;
            }
            if (menu.size() == 0) {
                LogUtils.error("not find any more menus!", true);
                return;
            }
            if (StringsUtils.isNotEmpty(str)) {
                LogUtils.info(str, true);
            }
            return;
        }

        if (isMatch(command, UPDATE_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            try {
                Integer interval = Integer.parseInt(command);
                if (interval >= 500 && interval <= 10000) {
                    if (DtuUpdateUtil.verify()) {
                        if (!DtuUpdateUtil.isUpdating) {
                            DtuUpdateUtil.update();
                        } else {
                            LogUtils.error("the update command has been sent...", true);
                        }
                    }
                } else {
                    LogUtils.error("intervalTime error,intervalTime must between 500 and 10000!", true);
                }
            } catch (NumberFormatException e) {
                LogUtils.error("intervalTime error,intervalTime must be integer!", true);
            }
            return;
        }

        if (isMatch(command, CMD_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            try {
                Integer i = Integer.valueOf(command);
                DtuCommand.getInstance().sendByValue(i);
            } catch (NumberFormatException e) {
                LogUtils.error("cmd type error,please retry", true);
            }
            return;
        }

        if (isMatch(command, ENCRYPT_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                LogUtils.info("encrypt str-->" + AesUtils.encrypt(command), true);
            }
            return;
        }

        if (isMatch(command, DECRYPT_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                LogUtils.info("decrypt str-->" + AesUtils.decrypt(command), true);
            }
            return;
        }

        if (isMatch(command, DTU_INFO_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().readDtuInfo(dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }

        if (isMatch(command, DTU_RESTART_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().restartDtu(dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }

        if (isMatch(command, SQL_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                if (command.length() > 0) {
                    DtuCommand.getInstance().executeSql(command);
                } else {
                    LogUtils.error("sql input error!", true);
                }
            }
            return;
        }

        if (isMatch(command, SIGNAL_R)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().readSignalStrength(dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }

        if (isMatch(command, SIGNAL_C)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().collectSignalStrength(dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }

        if (isMatch(command, COMMUNICATION_MODEL_4G)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().setCommunicationModel(4, dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }
        if (isMatch(command, COMMUNICATION_MODEL_2G)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().setCommunicationModel(2, dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }
        if (isMatch(command, AFN0C25_Y)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().sendTo4g(DeviceCmdTypeEnum.HM_AFN0C25, dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }
        if (isMatch(command, COMMUNICATION_MODEL_AUTO)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().setCommunicationModel(0, dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }
        if (isMatch(command, TIME)) {
            command = command.substring(command.indexOf("-") + 1, command.length());
            if (!StringsUtils.isEmpty(command)) {
                String dtuAddresses[] = command.split(",");
                DtuCommand.getInstance().broadcastTime(dtuAddresses);
            } else {
                LogUtils.error("input error!", true);
            }
            return;
        }
        LogUtils.error("command not find!", true);
    }

    private static boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex + 1;
                iIndex++;
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }
        return j == p.length();
    }
}
