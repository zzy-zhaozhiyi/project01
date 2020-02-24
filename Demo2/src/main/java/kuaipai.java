import java.util.Arrays;

/**
 * @author zzy
 * @create 2020-02-13 20:46
 */
public class kuaipai {
    public static void main(String[] args) {
        int[] a ={5,23,65,24,12,7,2,4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
   public static  void  sort(int[] a ){
       int length = a.length;
       int temp;
      for(int i=length-1;i>0;i--){
          for(int j =0;j<i;j++){
              if(a[j]>a[j+1]){
                  temp = a[j];
                  a[j]=a[j+1];
                  a[j+1]= temp;
              }
          }
      }


   }
}
