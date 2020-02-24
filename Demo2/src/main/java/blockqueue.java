import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzy
 * @create 2020-02-15 20:37
 */
public class blockqueue {
    private  boolean flag = true;
    private BlockingQueue<Object> blockingQueue;
    private AtomicInteger atomicInteger;

    public blockqueue(BlockingQueue<Object> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    public void product() throws InterruptedException {
        while(flag){

            String data = atomicInteger.getAndIncrement()+"";
            boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(Thread.currentThread().getName()+"生产了"+data);
            }else{
                System.out.println(Thread.currentThread().getName()+"shibaile ");
            }
        }
        System.out.println("大老板叫停了生产");
    }
    public void consume() throws InterruptedException {
        while(flag){

            Object poll = this.blockingQueue.poll(2, TimeUnit.SECONDS);
            if(null==poll||poll.equals("")){
                System.out.println(Thread.currentThread().getName()+"么有消费到");
                this.flag=false;
                return;
            }
            System.out.println(Thread.currentThread().getName()+"消费到了"+poll);
        }

    }
    public void  stop(){
        this.flag = false;
    }
}
