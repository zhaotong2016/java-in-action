package com.hunter.demo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobTest implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("job执行:"+context.getMergedJobDataMap());
    }
}
