package com.example.demo;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//资源类
class WaitDemoTwo {
    final Lock lock = new ReentrantLock();
    final Condition condition1 = lock.newCondition();
    final Condition condition2 = lock.newCondition();
    final Condition condition3 = lock.newCondition();
    Integer flag = 0;
    //1、判断  2、干活  3、通知
    public void printA5(){
        lock.lock();
        try{
            while (flag != 0){
                condition1.await(); }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + (i+1)); }
            flag = 1;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); } }
    public void printB10(){
        lock.lock();
        try {
            while (flag != 1){
                condition2.await(); }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + (i+1)); }
            flag = 2;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); } }
    public void printC15(){
        lock.lock();
        try {
            while (flag != 2) {
                condition3.await(); }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + (i+1)); }
            flag = 0;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); } }}
/**
 *  多线程之间按顺序调用，实现A->B->C。三个线程启动，要求如下：
 *  AA打印5次，BB打印10次，CC打印15次
 *  。。。打印10轮
 */
public class ConditionDemo{
    public static void main(String[] args) {
        WaitDemoTwo waitDemo = new WaitDemoTwo();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                waitDemo.printA5(); }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                waitDemo.printB10(); }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                waitDemo.printC15(); }
        },"CC").start(); }}

