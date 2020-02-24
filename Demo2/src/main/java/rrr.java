import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * @author zzy
 * @create 2020-02-12 22:48
 */
public class rrr {
    public static void main(String[] args) throws IOException {
        FileReader fr=null;
        try {
            long t1 = new Date().getTime();
            fr = new FileReader("Demo2\\helloworld.txt");
            char[] c = new char[5];
            int len;
            while((len=fr.read(c))!=-1){
                String s = new String(c,0,len);
                System.out.print(s);
            }
            long t2 = new Date().getTime();
            System.out.println(t2-t1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fr.close();
        }

    }

}
