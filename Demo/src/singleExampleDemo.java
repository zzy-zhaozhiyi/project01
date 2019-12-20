/**
 * @author zzy
 * @create 2019-12-09 8:40
 */
public class singleExampleDemo {
    private  singleExampleDemo(){};
    private  static volatile singleExampleDemo instance = null;
    public static singleExampleDemo getInstance(){
        if(instance==null){
            synchronized (singleExampleDemo.class){
                if(instance==null){
                    instance = new singleExampleDemo();
                }
            }
        }
        return  instance;
    }

}
