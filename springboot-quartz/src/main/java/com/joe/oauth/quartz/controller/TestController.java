package com.joe.oauth.quartz.controller;

import com.joe.oauth.quartz.domin.TaskScheduleVo;
import com.joe.oauth.quartz.job.TestJob1;
import com.joe.oauth.quartz.service.QuartzService;
import com.joe.oauth.quartz.util.CronUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class TestController {

    @Autowired
    private QuartzService quartzService;
    @RequestMapping("/addjob")
    public void startJob(String jobName) {
        TaskScheduleVo taskScheduleVo = new TaskScheduleVo();
        Calendar now = Calendar.getInstance();
        taskScheduleVo.setJobType(1);
        taskScheduleVo.setHour(now.get(Calendar.HOUR_OF_DAY));
        taskScheduleVo.setMinute(now.get(Calendar.MINUTE)+1);//分
        taskScheduleVo.setSecond(0);//秒
        //组装cron表达式
        String cron = CronUtil.createCronExpression(taskScheduleVo);
        quartzService.addJob(TestJob1.class, jobName, "test", "0/10 * * * * ?");
    }
    
    @RequestMapping("/updatejob")
    public void updatejob(String jobName) {
            quartzService.updateJob(jobName, "test", "0/10 * * * * ?");
    }
    
    @RequestMapping("/deletejob")
    public void deletejob(String jobName) {
            quartzService.deleteJob(jobName, "test");
    }
    
    @RequestMapping("/pauseJob")
    public void pauseJob(String jobName) {
            quartzService.pauseJob(jobName, "test");
    }
    
    @RequestMapping("/resumeJob")
    public void resumeJob(String jobName) {
            quartzService.resumeJob(jobName, "test");
    }
    
    @RequestMapping("/queryAllJob")
    public Object queryAllJob() {
            return quartzService.queryAllJob();
    }
    

    @RequestMapping("/queryRunJob")
    public Object queryRunJob() {
            return quartzService.queryRunJob();
    }
}