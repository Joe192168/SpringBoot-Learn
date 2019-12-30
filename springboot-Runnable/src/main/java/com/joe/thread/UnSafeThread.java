package com.joe.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 线程不安全操作
 */
public class UnSafeThread {

    private static int num = 0;
    private static CountDownLatch countDownLatch =new CountDownLatch(10);

    /**
     * 每次调用对num进行++的操作
     *
     */
    public static void  increment(){
        num++;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j <100 ; j++) {
                    increment();
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //在每个线程只想完后调用cutdown
                countDownLatch.countDown();
            }).start();

        }
        while (true){
            if(countDownLatch.getCount()==0){
                break;
            }
        }
        System.out.println(num);
    }
}