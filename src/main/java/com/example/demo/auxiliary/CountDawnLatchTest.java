package com.example.demo.auxiliary;

import java.util.concurrent.CountDownLatch;

/**
 * 6个同学陆续离开教室后值班同学才可以关门。
 */
public class CountDawnLatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i < 7; i++) {
            String a = String.valueOf(i);
            new Thread(()->{
                System.out.println("第"+a+"名同学离开教室了");
            }).start();
            countDownLatch.countDown();
        }
        countDownLatch.await();
        System.out.println("值日生关门了");
    }
}
