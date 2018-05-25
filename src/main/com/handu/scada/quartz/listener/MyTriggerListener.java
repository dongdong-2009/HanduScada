package main.com.handu.scada.quartz.listener;

import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created by 柳梦 on 2018/05/22.
 */
public class MyTriggerListener implements TriggerListener {

    @Override
    public String getName() {
        return "MyTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        //Trigger被触发了，此时Job上的execute()方法将要被执行
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        //检查此次Job的相关资源准备情况
        //不便展开任务，返回true表示否决此次任务执行
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        //当前Trigger触发错过了
        LogUtils.printTask(getName() + "--" + trigger.getKey().getName() + "--triggerMisfired");
    }

    @Override//这是2.+版本的配置，差别在于将triggerInstructionCode从整型改成了枚举类型
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        //Trigger被触发并且完成了Job的执行, 此方法被调用
        LogUtils.error(getName() + "--" + context.getJobDetail().getJobClass().getName() + "--" + triggerInstructionCode.name() + "--triggerComplete");
    }
}
