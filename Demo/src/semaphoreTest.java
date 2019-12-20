import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zzy
 * @create 2019-11-27 11:46
 */
public class semaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+":占领了车位");
                    TimeUnit.SECONDS.sleep(3l);
                    System.out.println(Thread.currentThread().getName()+":释放了了车位");
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
    }
}
