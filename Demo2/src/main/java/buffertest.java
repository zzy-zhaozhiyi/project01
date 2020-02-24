import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Hashtable;

/**
 * @author zzy
 * @create 2020-02-13 10:54
 */
public class buffertest {
    public static void main(String[] args) {
        BufferedReader br =null;
        Hashtable<String, String> stringStringHashtable = new Hashtable<>();

        try {
            long t1 = new Date().getTime();
             br = new BufferedReader(new FileReader("Demo2\\helloworld.txt"));
            String data;
            while((data=br.readLine())!=null){
                System.out.println(data);
            }
            long t2 = new Date().getTime();
            System.out.println(t2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
