import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zzy
 * @create 2019-11-27 11:31
 */
public class cutdownlatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(7);
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println("准备出门");
                    TimeUnit.SECONDS.sleep(3L);
                    System.out.println("出门完成");
                    count.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
        count.await();
        System.out.println("值班经理已经关门");

    }
}
