import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zzy
 * @create 2019-11-26 8:30
 */
public class myticket {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for(int i = 0; i<3;i++){
                new Thread(()->{
                    list.add(UUID.randomUUID().toString().substring(0,6));
                    System.out.println(list);
                }).start();
        }
    }

}
