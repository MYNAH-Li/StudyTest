package com.example.demo;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//资源类
class WaitDemothere {
    final Lock lock = new ReentrantLock();
    final Condition condition1 = lock.newCondition();
    final Condition condition2 = lock.newCondition();
    Integer flag = 0;
    //1、判断  2、干活  3、通知
    public void printNum() {
        lock.lock();
        try{
            for (int i = 1; i < 53; i++) {
                while (flag == 2){
                    condition2.signal();
                    condition1.await(); }
                System.out.println(i);
                flag++;
                if(i == 52){
                    condition2.signal(); } }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); } }
    public void printChar(){
        lock.lock();
        try {
            for (char i = 65; i < 91; i++) {
                while (flag != 2){
                    condition2.await(); }
                System.out.println(i);
                flag = 0;
                condition1.signal(); }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); } }}
/**
 * 两个线程，一个线程打印1-52，另一个打印字母A-Z打印顺序为12A34B...5152Z，要求用线程间通信
 */
public class Condition2Demo{
    public static void main(String[] args) {
        WaitDemothere waitDemo = new WaitDemothere();
        new Thread(()->{
            waitDemo.printNum();
        },"num").start();
        new Thread(()->{
            waitDemo.printChar();
        },"char").start(); }}

