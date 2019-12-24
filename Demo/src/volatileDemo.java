import java.util.concurrent.TimeUnit;

/**
 * @author zzy
 * @create 2019-12-06 18:03
 * 这是验证volatile多线程操作的可见性
 */
public class volatileDemo {
    public static void main(String[] args) {
        number number = new number();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"进来了");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number.addNumber();
            System.out.println(Thread.currentThread().getName()+"number的值是"+number.number);
        }, "aaa").start();

        while (number.number==0){

        }
        System.out.println(Thread.currentThread().getName()+"主线程得到的值是"+number.number);

    }
}
class number{
 volatile int number = 0;
    public void addNumber(){
          //  int number = 60;这是重新定义的值
        this.number=60;
    }
}