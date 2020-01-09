package com.joe.oauth.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor 框架Demo
 */
public class ExecutorDemo  {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        ExecutorService executorService2 = Executors.newScheduledThreadPool(2);
        ExecutorService executorService3 = Executors.newWorkStealingPool(2);
        ExecutorService executorService4 = Executors.newSingleThreadExecutor();
        ExecutorService executorService5 = Executors.newSingleThreadScheduledExecutor();
        //提交要处理的任务
        executorService.submit(()->{

            System.out.println(Thread.currentThread().getName());
        });

        executorService.shutdown();
    }

}