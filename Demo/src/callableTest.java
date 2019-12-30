import com.sun.org.apache.xpath.internal.operations.String;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zzy
 * @create 2019-11-26 18:44
 */
public class callableTest {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<Integer>(() -> {
            System.out.println("妮好真的想妮了");
            return 200;
        });


        new Thread(task, "妮好").start();
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

/*
class myCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+":这是执行了call接口的方法");
        return 200;
    }
}*/
