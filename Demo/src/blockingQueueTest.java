import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzy
 * @create 2019-12-24 8:37
 */
class MyResource {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;


    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    public void product() throws Exception {
        String data = null;
        boolean value;
        while (flag) {
            data = atomicInteger.getAndIncrement() + "";
            value = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (value) {
                System.out.println(Thread.currentThread().getName() + "插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "插入队列shibai");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("大老板叫停,flag=flase了，生产活动结束了");
    }

    public void consume() throws Exception {
        String result;
        while (flag) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                this.flag = false;
                System.out.println(Thread.currentThread().getName() + "/超过两秒，没有取到，退出消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费成功" + result);
        }
    }

    public void stop() {
        this.flag = false;
    }
}

public class blockingQueueTest {
    public static void main(String[] args) {
        MyResource resource = new MyResource(new ArrayBlockingQueue<String>(3));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产者开始启动");
            try {
                resource.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "product").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "消费者开始启动");
            try {
                resource.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("5秒后大佬版叫停");
        resource.stop();


    }


}