package com.hunter.demo.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class QuartzTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        Map<String,Object> map=new HashMap<String, Object>();


        map.put("name","张三");


        task(new JobTest(),map,"task1",new Date(System.currentTimeMillis() + 5000));

        System.out.println("--结束---");


        Thread.sleep(10000);

    }


    /**
     * 删除任务
     */
    private static void delTask(String key) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.deleteJob(new JobKey(key));
    }
    /**
     * 创建任务
     */
    private static void task(Job job, Map<String,Object> param, String key, Date date) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        // 开始
        scheduler.start();
        // job 唯一标识 test.test-1
        JobKey jobKey = new JobKey(key);
       // if(scheduler.checkExists(jobKey)) return;//任务已存在
        JobDataMap map=new JobDataMap(param);//传到参数
        JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(jobKey).setJobData( map).build();
      /*  Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(key)
                // 执行时间
                .startAt(date)
                .build();*/



       /* Trigger trigger = TriggerBuilder.newTrigger()
                         .withIdentity(key)
          .withSchedule(dailyAtHourAndMinute(10, 0))
                         .startAt(date).build();
*/

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(key)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
                .startAt(date).build();






        scheduler.scheduleJob(jobDetail , trigger);
    }



}
