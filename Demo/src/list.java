/**
 * @author zzy
 * @create 2019-11-24 10:28
 */
public class list {
    public static void main(String[] args) {
        phone phone = new phone();
        //  phone phone2 = new phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "b").start();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();


    }
}

class phone {
    public synchronized void sendEmail() {
        System.out.println("发送Email:" + Thread.currentThread().getName());
    }
}