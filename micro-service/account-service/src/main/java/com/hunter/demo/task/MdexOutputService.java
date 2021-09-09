package com.hunter.demo.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import java.util.Date;


@Slf4j
@Service
public class MdexOutputService implements SchedulingConfigurer {

    private static String defaultCron = "0/5 * * * * ?";

    private static String DEFAULT_FORMAT = "0/% * * * * ?";

    public static void setDefaultCron(Integer cron) {
        MdexOutputService.defaultCron = String.format(DEFAULT_FORMAT, cron);
    }

    /**
     * 计算收益率
     */
    public void calculateRateOfReturn(){
        log.info("计算收益率...........");
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
               calculateRateOfReturn();   // 任务逻辑
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 任务触发，可修改任务的执行周期
                CronTrigger trigger = new CronTrigger(defaultCron);
                Date nextExecutionTime = trigger.nextExecutionTime(triggerContext);
                return nextExecutionTime;
            }
        });
    }
}
