package main.com.handu.scada.business.dtu;

import main.com.handu.scada.netty.server.dtu.DtuNetworkConnection;
import main.com.handu.scada.netty.server.dtu.DtuChannelManager;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 柳梦 on 2018/01/11.
 */
public class DtuUpdateUtil {

    public static final String DEVICE_ADDRESS_NAME = "deviceAddress.txt";
    private static String UPDATE_FILE_NAME = "";
    public static final String UPDATE_PATH = "update";
    public static int interval = 1000;
    public static boolean isUpdating = false;

    /**
     * 停止
     */
    public static void stop() {
        isUpdating = false;
        String dtuAddress = getDtuAddress();
        if (!Objects.equals(dtuAddress, "")) {
            String[] dtuAddresses = dtuAddress.split(",");
            if (dtuAddresses.length > 0) {
                for (String address : dtuAddresses) {
                    if (!StringsUtils.isEmpty(address)) {
                        String clientId = DtuChannelManager.getClientId(dtuAddress);
                        if (clientId != null) {
                            DtuNetworkConnection state = DtuChannelManager.getNetworkState(clientId);
                            if (state != null && state.getChannel().isActive()) {
                                state.stopUpdate();
                            }
                        }
                    }
                }
            }
            LogUtils.info("stop update!", true);
        }
    }

    /**
     * 升级前校验文件是否存在
     *
     * @return
     */
    public static boolean verify() {
        if (Objects.equals(getDtuAddress(), "")) {
            LogUtils.error("device address is null or error!", true);
            return false;
        }
        File fileDirectory = new File(UPDATE_PATH);
        if (fileDirectory.isDirectory()) {
            String[] fileList = fileDirectory.list();
            if (fileList != null && fileList.length > 0) {
                for (String filePath : fileList) {
                    if (filePath.startsWith("HD_4G_V") && filePath.endsWith(".bin")) {
                        File file = new File(fileDirectory.getAbsolutePath() + File.separator + filePath);
                        UPDATE_FILE_NAME = file.getName();
                        LogUtils.info("find update file " + file.getName(), true);
                    }
                }
                File file = new File(UPDATE_PATH + File.separator + UPDATE_FILE_NAME);
                if (!file.isFile() || !file.exists()) {
                    LogUtils.info("not find update file,please put update deviceAddressFile and updateFile into " + UPDATE_PATH, true);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取文件信息
     */
    private static String getDtuAddress() {
        String content = "";
        try {
            File file = new File(UPDATE_PATH + File.separator + DEVICE_ADDRESS_NAME);
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    content += lineTxt;
                }
                read.close();
            } else {
                isUpdating = false;
                LogUtils.error("not find update deviceAddress file(" + UPDATE_PATH + File.separator + DEVICE_ADDRESS_NAME + "),please put update deviceAddress and update file into " + UPDATE_PATH, true);
            }
        } catch (Exception e) {
            isUpdating = false;
            LogUtils.error("read file content error", true);
        }
        return content;
    }

    /**
     * 获取升级文件开始升级
     */
    public static void update() {
        LogUtils.info("update path " + UPDATE_PATH + File.separator + UPDATE_FILE_NAME, true);
        File file = new File(UPDATE_PATH + File.separator + UPDATE_FILE_NAME);
        //判断文件是否存在
        if (file.isFile() && file.exists()) {
            List<byte[]> sendBufferList = splitBuffer(file);
            if (sendBufferList.size() > 0) {
                begin(sendBufferList);
            } else {
                isUpdating = false;
                LogUtils.error("split error...", true);
            }
        } else {
            isUpdating = false;
            LogUtils.error("not find update file (" + UPDATE_FILE_NAME + "),please put file into " + UPDATE_PATH, true);
        }
    }

    /**
     * 开始发送更新命令
     *
     * @param sendBufferList
     */
    private static void begin(List<byte[]> sendBufferList) {
        String dtuAddress = getDtuAddress();
        if (!Objects.equals(dtuAddress, "")) {
            String[] dtuAddresses = dtuAddress.split(",");
            if (dtuAddresses.length > 0) {
                isUpdating = true;
                for (String address : dtuAddresses) {
                    sendCommand(sendBufferList, address);
                }
            } else {
                LogUtils.error("not find dtuAddress in file!", true);
            }
        }
    }

    /**
     * 向dtu发送命令
     *
     * @param sendBufferList
     * @param dtuAddress
     */
    private static void sendCommand(List<byte[]> sendBufferList, String dtuAddress) {
        if (!StringsUtils.isEmpty(dtuAddress)) {
            String clientId = DtuChannelManager.getClientId(dtuAddress);
            if (clientId != null) {
                DtuNetworkConnection state = DtuChannelManager.getNetworkState(clientId);
                if (state != null && state.getChannel().isActive()) {
                    state.startUpdate(sendBufferList, dtuAddress);
                }
            } else {
                LogUtils.error("dtu " + dtuAddress + " is not connect!", true);
            }
        } else {
            LogUtils.error("dtuAddress is null or error!", true);
        }
    }

    /**
     * 分包
     */
    private static List<byte[]> splitBuffer(File file) {
        long total = file.length();
        List<byte[]> sendBufferList = new ArrayList<>();
        int packLen = 1024;
        int packCount = (int) (total / packLen);

        try {
            FileInputStream in = new FileInputStream(file);
            byte[] sendCmd = new byte[(int) total];
            in.read(sendCmd);
            in.close();
            for (long i = 0; i < packCount; i++) {
                byte[] temp = new byte[packLen];
                System.arraycopy(sendCmd, (int) (i * packLen), temp, 0, packLen);
                sendBufferList.add(temp);
            }
            if (packCount * packLen < total) {
                byte[] temp = new byte[(sendCmd.length - packCount * packLen)];
                System.arraycopy(sendCmd, (packCount * packLen), temp, 0, temp.length);
                sendBufferList.add(temp);
            }
        } catch (IOException e) {
            isUpdating = false;
        }
        LogUtils.info("file size " + total / 1024 + "kb,subpackage" + (packCount + 1) + "...", true);
        return sendBufferList;
    }
}
