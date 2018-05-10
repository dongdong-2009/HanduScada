package main.com.handu.scada.quartz;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.io.*;
import java.net.URL;
import java.util.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by 柳梦 on 2017/12/08.
 */
public class QuartzManager {

    private static final String PATH = "cron" + File.separator + "cron.properties";

    private static final String DEFAULT_JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";

    private static QuartzManager singleton;

    private Scheduler scheduler;

    public static QuartzManager getInstance() {
        if (singleton == null) {
            synchronized (QuartzManager.class) {
                if (singleton == null) {
                    singleton = new QuartzManager();
                }
            }
        }
        return singleton;
    }

    private QuartzManager() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
        }
    }

    /**
     * 开始所有任务
     */
    public boolean start() {
        try {
            if (isStart()) {
                LogUtils.error("quartz has started!", true);
                return false;
            }
            if (scheduler == null) {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            }
            if (init()) {
                //调度器开始调度任务
                scheduler.start();
            }
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
            return false;
        }
        return true;
    }

    /**
     * 重启
     */
    public boolean restart() {
        return stop() && start();
    }

    private boolean init() {
        List<Class> classList = getAllImplClassesByInterface(BaseJob.class);
        if (classList != null && classList.size() > 0) {
            try {
                File file = new File(PATH);
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                Properties props = new Properties();
                InputStream inputStream = new FileInputStream(PATH);
                props.load(inputStream);
                boolean isUpdate = false;
                for (Class aClass : classList) {
                    BaseJob job = (BaseJob) aClass.newInstance();
                    //表达式
                    String cron = job.cronExpression();
                    try {
                        int seconds = Integer.parseInt(cron);
                        addOnceJob(job.jobName(), DEFAULT_JOB_GROUP_NAME, aClass, seconds * 60);
                    } catch (Exception e) {
                        //是否启用
                        boolean isEnable = job.isEnable();
                        if (!props.containsKey(aClass.getName() + ".enable")) {
                            props.setProperty(job.getClass().getName() + ".enable", String.valueOf(isEnable));
                            isUpdate = true;
                        } else {
                            isEnable = Boolean.parseBoolean(props.getProperty(aClass.getName() + ".enable"));
                        }
                        if (!props.containsKey(aClass.getName())) {
                            props.setProperty(job.getClass().getName(), cron);
                            isUpdate = true;
                        } else {
                            cron = props.getProperty(aClass.getName());
                        }
                        if (isEnable) {
                            addOrUpdateJob(job.jobName(), aClass, cron);
                        }
                    }
                }
                if (isUpdate) {
                    FileOutputStream fos = new FileOutputStream(file);
                    props.store(fos, "job cronExpression");
                    fos.close();// 关闭流
                }
            } catch (IOException | InstantiationException | IllegalAccessException ignored) {
                ExceptionHandler.handle(ignored);
                return false;
            }
        }
        return true;
    }

    /**
     * 调度是否启动
     *
     * @return
     */
    public boolean isStart() {
        try {
            return scheduler != null && scheduler.isStarted();
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
        }
        return false;
    }

    /**
     * 停止任务
     */
    public boolean stop() {
        try {
            if (!isStart()) {
                LogUtils.error("quartz is not start!", true);
                return false;
            }
            //停止开始调度任务
            if (scheduler != null) {
                scheduler.shutdown();
                scheduler = null;
            }
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
            return false;
        }
        return true;
    }

    /**
     * 添加只执行一次的job
     *
     * @param jobId
     * @param jobGroupName
     * @param cls
     * @param delayed
     */
    public void addOnceJob(String jobId, String jobGroupName, Class<? extends Job> cls, int delayed) {
        try {
            //定义一个Trigger
            Trigger trigger = newTrigger()
                    .withIdentity(jobId, jobGroupName) //定义name/group
                    .startAt(DateUtils.getDelayedDateBySeconds(delayed))//一旦加入scheduler，30分钟后执行
                    .withSchedule(simpleSchedule() //使用SimpleTrigger
                            .withRepeatCount(0)) //只执行一次,重复0次
                    .build();
            if (!scheduler.checkExists(new TriggerKey(jobId, jobGroupName))) {
                //定义一个JobDetail
                JobDetail jobDetail = newJob(cls) //定义Job类，这是真正的执行逻辑所在
                        .withIdentity(jobId, jobGroupName) //定义name/group
                        .usingJobData("jobId", jobId) //定义属性
                        .build();
                //加入这个调度
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception ignored) {
            ExceptionHandler.handle(ignored);
        }
    }

    /**
     * 添加定时任务
     *
     * @param jobId          任务id
     * @param jobGroupName   任务组名称
     * @param cls            任务类class
     * @param cronExpression 执行时间表达式
     */
    public Date addOrUpdateJob(String jobId, String jobGroupName, Class<? extends Job> cls, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobId, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                JobDetail jobDetail = newJob(cls).withIdentity(jobId, jobGroupName).build();
                jobDetail.getJobDataMap().put("jobId", jobId);
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                        .withMisfireHandlingInstructionDoNothing();
                //按新的cronExpression表达式构建一个新的trigger
                trigger = newTrigger().withIdentity(jobId, jobGroupName).withSchedule(scheduleBuilder).build();
                //将任务及其触发器放入调度器
                return scheduler.scheduleJob(jobDetail, trigger);
            } else {
                modifyJob(jobId, jobGroupName, cronExpression);
            }
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
        }
        return null;
    }

    /**
     * 修改任务
     *
     * @param jobName
     * @param jobGroupName
     * @param cron
     */
    public void modifyJob(String jobName, String jobGroupName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
//                // 触发器
//                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
//                // 触发器名,触发器组
//                triggerBuilder.withIdentity(jobName, jobGroupName);
//                triggerBuilder.startNow();
//                // 触发器时间设定
//                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
//                // 创建Trigger对象
//                trigger = (CronTrigger) triggerBuilder.build();
//                /*
//                 * 方式一 ：修改一个任务的触发时间
//                 * 调用 rescheduleJob 结束
//                 */
//                scheduler.rescheduleJob(triggerKey, trigger);

                /** 方式二：先删除，然后在创建一个新的Job  */
                JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                Class<? extends Job> jobClass = jobDetail.getJobClass();
                deleteJob(jobName, jobGroupName);
                addOrUpdateJob(jobName, jobGroupName, jobClass, cron);
            }
        } catch (Exception ignored) {
            ExceptionHandler.handle(ignored);
        }
    }

    /**
     * @param jobName
     * @param cron
     */
    public void modifyJob(String jobName, String cron) {
        modifyJob(jobName, DEFAULT_JOB_GROUP_NAME, cron);
    }

    /**
     * @param jobId          任务id
     * @param cls            任务类class
     * @param cronExpression 执行时间表达式
     */
    public Date addOrUpdateJob(String jobId, Class cls, String cronExpression) {
        return addOrUpdateJob(jobId, DEFAULT_JOB_GROUP_NAME, cls, cronExpression);
    }

    /**
     * 删除任务
     *
     * @param jobId     任务Id
     * @param groupName 任务组名称
     * @return
     */
    public boolean deleteJob(String jobId, String groupName) {
        try {
            JobKey jobKey = new JobKey(jobId, groupName);
            TriggerKey triggerKey = new TriggerKey(jobId, groupName);
            if (scheduler.checkExists(jobKey)) {
                scheduler.pauseTrigger(triggerKey);//停止触发器
                scheduler.deleteJob(jobKey);
                return scheduler.unscheduleJob(triggerKey);//移除触发器
            }
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
        }
        return false;
    }

    /**
     * 删除任务
     *
     * @param jobId 任务Id
     */
    public void deleteJob(String jobId) {
        deleteJob(jobId, DEFAULT_JOB_GROUP_NAME);
    }

    /**
     * 暂停任务
     *
     * @param jobId     任务Id
     * @param groupName 任务组名称
     */
    public void pauseJob(String jobId, String groupName) {
        try {
            JobKey jobKey = new JobKey(jobId, groupName);
            if (scheduler.checkExists(jobKey)) {
                scheduler.pauseJob(jobKey);
                scheduler.pauseTrigger(new TriggerKey(jobId, groupName));
            }
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
        }
    }

    /**
     * 暂停任务
     *
     * @param jobId 任务Id
     */
    public void pauseJob(String jobId) {
        pauseJob(jobId, DEFAULT_JOB_GROUP_NAME);
    }

    /**
     * 再次启动Job
     *
     * @param jobId     任务Id
     * @param groupName 任务组名称
     */
    public void resumeJob(String jobId, String groupName) {
        try {
            JobKey jobKey = new JobKey(jobId, groupName);
            if (scheduler.checkExists(jobKey)) {
                scheduler.resumeJob(jobKey);
                scheduler.resumeTrigger(new TriggerKey(jobId, groupName));
            }
        } catch (SchedulerException ignored) {
            ExceptionHandler.handle(ignored);
        }
    }

    /**
     * 恢复任务
     *
     * @param jobId 任务Id
     */
    public void resumeJob(String jobId) {
        resumeJob(jobId, DEFAULT_JOB_GROUP_NAME);
    }

    /**
     * 获取正在运行的计划任务
     *
     * @return
     */
    public Map<String, Trigger> getTriggers() {
        Map<String, Trigger> triggerMap = new HashMap<>();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = null;
        try {
            jobKeys = scheduler.getJobKeys(matcher);
        } catch (SchedulerException ignored) {
        }
        if (jobKeys != null) {
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = null;
                try {
                    triggers = scheduler.getTriggersOfJob(jobKey);
                } catch (SchedulerException ignored) {
                }
                if (triggers != null) {
                    for (Trigger trigger : triggers) {
                        triggerMap.put(jobKey.getGroup() + "." + jobKey.getName(), trigger);
                    }
                }
            }
        }
        return triggerMap;
    }

    public List<Class> getAllImplClassesByInterface(Class c) {
        // 给一个接口，返回这个接口的所有实现类
        List<Class> returnClassList = new ArrayList<>();// 返回结果
        // 如果不是一个接口，则不做处理
        if (c.isInterface()) {
            String packageName = c.getPackage().getName();// 获得当前包名
            try {
                List<Class> allClass = getClassesByPackageName(packageName);// 获得当前包下以及包下的所有类
                for (Class allClas : allClass) {
                    if (c.isAssignableFrom(allClas)) {
                        if (!c.equals(allClas)) {// 本身不加
                            returnClassList.add(allClas);
                        }
                    }
                }
            } catch (ClassNotFoundException | IOException ignored) {
                ExceptionHandler.handle(ignored);
            }
        }
        return returnClassList;

    }

    // 从一个包中查找出所有类,在jar包中不能查找
    private List<Class> getClassesByPackageName(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //递归查找文件夹【即对应的包】下面的所有文件
                assert !file.getName().contains(".");
                /**
                 * J2SE 1.4在语言上提供了一个新特性，就是assertion(断言)功能，它是该版本在Java语言方面最大的革新。在软件开发中，assertion是一种经典的调试、测试方式。

                 在语法上，为了支持assertion，Java增加了一个关键字assert。它包括两种表达式，分别如下：

                 　　assert expression1;

                 　　assert expression1: expression2;

                 　　在两种表达式中，expression1表示一个boolean表达式，expression2表示一个基本类型或者是一个对象(Object) ，基本类型包括boolean,char,double,float,int和long。由于所有类都为Object的子类，因此这个参数可以用于所有对象。

                 　　assert

                 　　如果为true，则程序继续执行。

                 　　如果为false，则程序抛出AssertionError，并终止执行。
                 */
                classes.addAll(findClasses(file, packageName + '.' + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
