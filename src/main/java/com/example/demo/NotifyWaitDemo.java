package com.example.demo;
//资源类
class WaitDemo {
    private int num = 0;
    /**加一*/
    public synchronized void incr() throws InterruptedException {
        //判断
        while(num == 1){
            this.wait();
        }
        //干活
        num++;
        System.out.println(Thread.currentThread().getName() + "+当前num值为:" + num );
        //通知
        this.notifyAll();
    }
    /**减一*/
    public synchronized void dsrc() throws InterruptedException {
        while (num == 0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "-当前num值为:" + num );
        this.notifyAll();
    }
}

//打印1010101101010
public class NotifyWaitDemo{
    public static void main(String[] args) {
        WaitDemo waitDemo = new WaitDemo();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    waitDemo.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    waitDemo.dsrc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    waitDemo.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"cc").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    waitDemo.dsrc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"dd").start();
    }
}

