import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zzy
 * @create 2019-11-27 11:52
 */
public class ReadAndWriteLock {
    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
             test.put();
            }, "写"+String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
             test.get();
            }, "读"+String.valueOf(i)).start();
        }
    }
}

class Test {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put() {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入数据");
            TimeUnit.SECONDS.sleep(2l);
            System.out.println(Thread.currentThread().getName()+"写入完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }
    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读数据");
            TimeUnit.SECONDS.sleep(2l);
            System.out.println(Thread.currentThread().getName()+"读入完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

    }
}