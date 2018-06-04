package main.com.handu.scada.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;

/**
 * Created by 柳梦 on 2017/12/08.
 */

//不允许并发执行
@DisallowConcurrentExecution
public interface BaseJob extends Job {
    /**
     * 是否启用
     */
    default boolean isEnable() {
        return true;
    }

    /**
     * job名称
     *
     * @return
     */
    default String jobName() {
        return this.getClass().getName();
    }

    /**
     * job表达式
     *
     * @return
     */
    default String cronExpression() {
        return "0 0/5 * * * ?";
    }
}
