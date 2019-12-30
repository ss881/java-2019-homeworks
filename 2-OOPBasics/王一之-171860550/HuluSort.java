import Huluwa;
import java.util.Random;

public class HuluSort{
    
    public static void rSort(Huluwa[] A){
        for(int i=1;i<A.length;++i)
            for(int j=1;j<A.length-i;++j){
                if(A[j+1].Compare_rank(A[j])>0)            {
                    A[0]=A[j];
                    A[j]=A[j+1];
                    A[j+1]=A[0];
                    A[j].Say_change(j+1, j);
                    A[j+1].Say_change(j, j+1);
                }
            }
    }

    public static void cSort(Huluwa[] A){
        for(int i=1;i<A.length;++i)
            for(int j=1;j<A.length-i;++j){
                if(A[j+1].Compare_color(A[j])>0)            {
                    A[0]=A[j];
                    A[j]=A[j+1];
                    A[j+1]=A[0];
                    A[j].Say_change(j+1, j);
                    A[j+1].Say_change(j, j+1);
                }
            }
    }

    public static void ran_array(int []A){
        
        for(int i=0;i<7;++i){
            A[i]=0;
        }
        int pos;
        Random r=new Random();
        for(int i=1;i<=7;++i){
            pos=r.nextInt(7);
            while(A[pos]!=0){
                pos=r.nextInt(7);
            }
            A[pos]=i;
        }
    }

    public static void print_hulu_r(Huluwa[] G){
        System.out.println("result:");
        for(int i=1;i<G.length;++i)
        {
            G[i].Say_rank();
        }
        System.out.println();
    }

    public static void print_hulu_c(Huluwa[] G){
        System.out.println("result:");
        for(int i=1;i<G.length;++i)
        {
            G[i].Say_color();
        }
        System.out.println();
    }

    public static void main(String[] args){
        int []ran=new int[7];
        ran_array(ran);
        Huluwa []ground=new Huluwa[8];
        ground[ran[0]]=new Huluwa(1);
        ground[ran[1]]=new Huluwa(2);
        ground[ran[2]]=new Huluwa(3);
        ground[ran[3]]=new Huluwa(4);
        ground[ran[4]]=new Huluwa(5);
        ground[ran[5]]=new Huluwa(6);
        ground[ran[6]]=new Huluwa(7);

        print_hulu_r(ground);

        rSort(ground);

        print_hulu_r(ground);

        cSort(ground);

        print_hulu_c(ground);

    }

}