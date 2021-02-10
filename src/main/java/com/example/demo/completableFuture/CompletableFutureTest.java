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
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println("女2号上场");
        });
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
        CompletableFuture.allOf(completableFuture2,completableFuture1).join();
    }
}
