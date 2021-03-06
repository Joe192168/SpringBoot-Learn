package com.joe.oauth.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@PersistJobDataAfterExecution //参数持久化
@DisallowConcurrentExecution// 不允许并发执行
public class TestJob1 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println(new Date() + "    job执行");
    }

}