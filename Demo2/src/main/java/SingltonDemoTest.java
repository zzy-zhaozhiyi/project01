/**
 * @author zzy
 * @create 2020-02-10 20:16
 */
public class SingltonDemoTest {
    public static void main(String[] args) {
        SingltonDemo singltonDemo = new SingltonDemo();
        new Thread(()-> System.out.println(singltonDemo.getInstance()));
    }

}

class SingltonDemo {
    private void singltonDemo() {
    };
    private static SingltonDemo instance = null;

    public synchronized SingltonDemo getInstance() {
        if (instance == null) {
            instance = new SingltonDemo();
        }
        return instance;
    }

    ;

}