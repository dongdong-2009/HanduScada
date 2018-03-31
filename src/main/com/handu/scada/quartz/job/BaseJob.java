package main.com.handu.scada.quartz.job;

import org.quartz.Job;

/**
 * Created by 柳梦 on 2017/12/08.
 */
public interface BaseJob extends Job {
    /**
     * 是否启用
     */
    void isEnable(boolean isEnable);

    String jobName();

    String cronExpression();

}
