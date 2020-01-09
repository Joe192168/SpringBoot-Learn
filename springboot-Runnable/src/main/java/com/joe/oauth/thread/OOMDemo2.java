package com.joe.oauth.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *这个会把你的所有内存占满后才停止
 * 谨慎测试这个
 */
public class OOMDemo2 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        while (true){

            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }



    }

}