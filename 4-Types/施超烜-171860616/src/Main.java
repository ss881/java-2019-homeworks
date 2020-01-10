import java.util.Random;
import map.*;
public class Main{
    public static void main(String[] args){
        Map map=new Map(15);
        Random rand=new Random();
        int times=rand.nextInt(3)+4;
        System.out.println("共"+Integer.toString(times)+"次对阵");
        for(int i=1;i<=times;i++){
            System.out.println("第"+Integer.toString(i)+"次对阵");
            map.begin();
        }
    }
}