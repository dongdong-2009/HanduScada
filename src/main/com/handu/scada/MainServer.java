package main.com.handu.scada;

import main.com.handu.scada.business.start.Start;

/**
 * Created by 柳梦 on 2017/12/15.
 */
public class MainServer {

    public static int START_SQL = 1;
    public static int START_NO_SQL = 2;
    public static int START_TYPE = START_SQL;

    public static void main(String[] strings) {
        new Start().start();
    }
}
