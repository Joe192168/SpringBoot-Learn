package com.joe.oauth.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJob1 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println(new Date() + "    job执行");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Boolean result = false;
        int count = 0;
        while(!result) {
            try {
                Thread.sleep(5 * 1000); //设置暂停的时间 5 秒
                count ++ ;
                System.out.println(sdf.format(new Date()) + "--循环执行第" + count + "次");
                if (count == 3) {
                    result = true;
                    break ;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}