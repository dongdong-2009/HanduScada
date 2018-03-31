package main.com.handu.scada;

import main.com.handu.scada.business.message.MsgManager;

/**
 * Created by 柳梦 on 2018/01/16.
 */
public class MainMsg {

    public static void main(String[] args) {
        MsgManager.getInstance(false).startSendMsg();
    }
}
