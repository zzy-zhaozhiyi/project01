import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author zzy
 * @create 2019-11-27 11:39
 */
public class circleBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("开始开会");
        });
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("准备开会材料");
                    TimeUnit.SECONDS.sleep(1l);
                    System.out.println("到达会议室");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }

    }
}
