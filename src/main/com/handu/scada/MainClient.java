package main.com.handu.scada;

import main.com.handu.scada.netty.client.dtu.CmdClient;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.Scanner;

/**
 * Created by 柳梦 on 2018/02/05.
 */
public class MainClient {
    public static void main(String[] args) {
        LogUtils.info("please input ip and port by ip:port:", true);
        Scanner scanner = new Scanner(System.in);
        String command;
        while ((command = scanner.next()) != null) {
            if (!StringsUtils.isEmpty(command)) {
                try {
                    String ip_port[] = command.split(":");
                    new CmdClient(ip_port[0], Integer.parseInt(ip_port[1])).start();
                    scanner.close();
                } catch (Exception e) {
                    LogUtils.info("input ip and port error!", true);
                }
            } else {
                LogUtils.info(" input ip and port error!", true);
            }
        }
    }
}
