package com.atguigu.activemq.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzy
 * @create 2019-12-26 14:37
 */
public class conditionTest {
    public static void main(String[] args) {
        print print = new print();
        new Thread(() -> {
            try {
                print.print5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a").start();
        new Thread(() -> {
            try {
                print.print10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b").start();
        new Thread(() -> {
            try {
                print.print15();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "c").start();
    }

}

class print {
    private Integer flag = 1;
    Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        while (flag != 1) {
            condition1.await();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+": AAAAA");
        }
        flag = 2;
        condition2.signal();
        lock.unlock();
    }

    public void print10() throws InterruptedException {
        lock.lock();
        while (flag != 2) {
            condition1.await();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+": BBBBB");
        }
        flag = 3;
        condition3.signal();
        lock.unlock();
    }

    public void print15() throws InterruptedException {
        lock.lock();
        while (flag != 3) {
            condition1.await();
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(Thread.currentThread().getName()+": CCCCC");
        }
        flag = 1;
        condition1.signal();
        lock.unlock();
    }

}