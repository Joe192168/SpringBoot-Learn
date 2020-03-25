package com.joe.oauth.quartz.job;

import com.joe.oauth.quartz.service.UserService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestJob2 extends QuartzJobBean {

    //注入业务service，完成定时任务逻辑
    @Autowired
    private UserService service;
    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println(new Date() + "    job2执行");
        service.play();
    }

}