import java.util.Random;

public class CalabashBrotherSort{
    static void randomizePlace(int[] list){
        Random ran = new Random();
        int len = list.length;
        for(int i = 0;i < len;++i){
            int ranPlace = ran.nextInt(len);
            int temp = list[i];
            list[i] = list[ranPlace];
            list[ranPlace] = temp;
        }
    }
    public static void main(String[] args){
        int[] initPlace = {0,1,2,3,4,5,6};
        CalabashBrother[] calabashBrothers = new CalabashBrother[7];

        randomizePlace(initPlace);
        for(int i = 0;i < 7;++i){
            calabashBrothers[i] = new CalabashBrother();
            calabashBrothers[i].setPosition(initPlace[i]);
        }

        //bubble sort
        System.out.println("bubble sort");
        System.out.println("init place");
        World.showMap(1);
        World.bubbleSort();
        World.showMap(1);


        //binary insert sort
        System.out.println("binary insert sort");
        System.out.println("change place first");
        randomizePlace(initPlace);
        for(int i = 0;i < 7;++i){
            calabashBrothers[i].walkTo(initPlace[i]);
        }
        System.out.println("init place");
        World.showMap(1);
        World.binaryInsertSort();
        World.showMap(2);
    }
}