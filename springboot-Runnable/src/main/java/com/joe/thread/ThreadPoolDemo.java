package com.joe.thread;

import com.joe.handler.CustomPolicy;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池Demo
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws Exception{
        LinkedBlockingDeque<Runnable> objects = new LinkedBlockingDeque<>(30);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                20,20,1000,TimeUnit.MILLISECONDS,
                objects,new CustomPolicy()
        );
        Future<String> submit = null;
        /*for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }*/

        for (int i = 0; i < 50; i++) {
            submit = threadPoolExecutor.submit(new CallableDemo());
        }

        for (int i = 0; i < 100; i++) {

            System.out.println(submit.get());

        }
    }
}