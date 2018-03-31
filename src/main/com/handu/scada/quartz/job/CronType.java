package main.com.handu.scada.quartz.job;

/**
 * Created by 柳梦 on 2018/03/28.
 */
public enum CronType {
    OnlyOnce(),//一次
    EveryDay(),//每天一次
    Never(),//从不
    Other()//其他
}
