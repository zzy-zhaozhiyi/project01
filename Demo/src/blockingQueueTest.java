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

    String data = null;
    boolean value;

    public void product() throws Exception {
        while (flag) {
            data = atomicInteger.getAndIncrement() + "";
            value = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (value) {
                System.out.println(Thread.currentThread().getName() + "插入队列成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "插入队列shibai");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("大老板叫停");
    }

    public void consume() throws Exception {
        String result;
        while (flag) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                this.flag = false;
            }
            System.out.println(Thread.currentThread().getName() + "/消费成功，退出");
        }
    }
    public void stop(){
        this.flag=false;
    }
}
public class blockingQueueTest{
    public static void main(String[] args)  {
        MyResource resource = new MyResource(new ArrayBlockingQueue<String>(3));
       new Thread(() -> {
           System.out.println(Thread.currentThread().getName()+"生产者开始启动");
           try {
               resource.product();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }, "product").start();
       new Thread(() -> {
           System.out.println(Thread.currentThread().getName()+"消费者开始启动");
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


       resource.stop();
        System.out.println("5秒后大佬版叫停");


    }



}