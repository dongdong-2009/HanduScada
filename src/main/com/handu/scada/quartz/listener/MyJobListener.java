package main.com.handu.scada.quartz.listener;

import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by 柳梦 on 2018/05/22.
 */
public class MyJobListener implements JobListener {

    @Override
    //相当于为我们的监听器命名
    public String getName() {
        return "MyJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        //即将要执行，可以做准备工作
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        //"否决JobDetail"是在Triiger被其相应的监听器监听时才具备的能力
        //被否决执行了，可以做些日志记录
        LogUtils.printTask(getName() + "--" + context.getJobDetail().getJobClass().getName() + "--jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        //执行完毕，可以释放资源等操作
        LogUtils.error(getName() + "--" + context.getJobDetail().getJobClass().getName() + "--jobWasExecuted");
    }
}
