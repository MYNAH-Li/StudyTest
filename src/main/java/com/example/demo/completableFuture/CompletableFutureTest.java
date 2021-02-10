package com.example.demo.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 异步编排
 *
 * 女2号上场
 * 男2号上场
 *      等待4秒
 *      男1号上场
 *              等待3秒
 *              女1号上场
 */

public class CompletableFutureTest {
    public static void main(String[] args) {
        //男2号和女2号同时上场
        //CompletableFuture.runAsync() 表示没有返回值的创建线程
        //CompletableFuture.supplyAsync() 表示有返回值的创建线程
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println("女2号上场");
        });

        //并行的需要创建两个CompletableFuture对象 CompletableFuture1和CompletableFuture2
        //串行的需要在completableFuture2对象后紧跟thenAccept()方法
        //thenAccept()方法表示继承上一个线程继续使用
        //thenAcceptAsync()方法表示从线程池中重新拿一个线程出来使用
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("男2号上场");
        }).thenAccept((t)->{
            //男1号等待4秒上场
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("男1号上场");
        }).thenAccept((t)->{
            //女1号等待3秒上场
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("女1号上场");
        });
        //CompletableFuture.allOf(）表示等待上面所有的线程
        //join（）方法表示主线程等待子线程一同打印
        CompletableFuture.allOf(completableFuture2,completableFuture1).join();
    }
}
