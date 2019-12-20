import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzy
 * @create 2019-12-08 9:17
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"得的值的是"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2019, 1314)+"得的值的是"+atomicInteger.get());
    }
}
