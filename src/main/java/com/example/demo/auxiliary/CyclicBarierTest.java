package com.example.demo.auxiliary;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 组队打boss过关卡游戏
 */

public class CyclicBarierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, ()->{
            System.out.println("恭喜通关成功");
        });

        for (int i = 1; i < 4; i++) {
            String j = String.valueOf(i);
            new Thread(()->{
                for (int k = 1; k < 4; k++) {
                    System.out.println("第" + j +"个人开始过"+ k +"关");
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(4));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第" + j +"个人过第"+ k +"成功");
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
