package com.example.demo.auxiliary;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 6辆车抢占3个车位
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < 7; i++) {
            String j = String.valueOf(i);
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第" + j + "辆车抢到了车位");
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(4));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第" + j + "辆车离开了车位");
                semaphore.release();
            }).start();
        }
    }
}
