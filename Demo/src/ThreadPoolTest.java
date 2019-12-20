import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzy
 * @create 2019-11-26 15:07
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ThreadPoolExecutor service = new ThreadPoolExecutor(
                3,18,
                10L,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(12),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 30; i++) {
            service.execute(() -> {
                ticket.sales();
            });

        }
    }
}

class Ticket {
    private int ticket = 30;
    private final Lock lock = new ReentrantLock();

    public void sales() {
        lock.lock();

        try {
            while (ticket <= 0) {
                return;
            }
            //大于0就继续卖票
            System.out.println(Thread.currentThread().getName() + ":卖的的第" + (ticket--) + "剩余的票是" + ticket);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
