package com.atguigu.activemq.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzy
 * @create 2019-12-26 9:56
 */
class MyBlockQueue {
    AtomicInteger atomicInteger = new AtomicInteger();
    private volatile boolean flag = true;
    BlockingQueue blockingQueue = null;

    public MyBlockQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prodect() throws Exception {
        String data = null;
        boolean value;
        while (flag) {
            data = this.atomicInteger.getAndIncrement() + "";
            value = this.blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (value) {
                System.out.println(Thread.currentThread().getName() + "生产成功" + data);
            } else {
                System.out.println(Thread.currentThread().getName() + "停止生产");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("店主已经叫停生产");

    }

    public void consume() throws Exception {
        while (flag) {
            String poll = (String) this.blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == poll || poll.equalsIgnoreCase("")) {
                this.flag = false;
                System.out.println(Thread.currentThread().getName() + "2秒后没取到，退出消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费成功" + poll);
        }
    }

    public void stop() {
        this.flag = false;
    }

}


public class BlockingQueueTest {
    public static void main(String[] args)  {
        MyBlockQueue queue = new MyBlockQueue(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产者线程启动");
            try {
                queue.prodect();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "product").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "消费者线程启动");
            try {
                queue.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consume").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒后叫停");
        queue.stop();
    }

}
