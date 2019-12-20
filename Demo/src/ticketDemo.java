import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzy
 * @create 2019-11-24 9:50
 */
        public class ticketDemo {
            private int tikcet = 30;
            Lock lock = new ReentrantLock();

            public void sale() {
                lock.lock();
                try {
                    if (tikcet > 0) {
                        System.out.println(Thread.currentThread().getName() + ":买出第" + (tikcet--) + ".剩余" + tikcet);
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
    }

    public static void main(String[] args) {
        ticketDemo ticket = new ticketDemo();
//方法体中的方法，只有一行的{}可以省略
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "A的买票程序").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "B的买票程序").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "C的买票程序").start();
    }

}
