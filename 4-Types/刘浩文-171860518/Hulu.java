import java.util.Random;
public class Hulu {
    public static void main(String argc[]){
        Handler handler=new Handler();
        handler.snakePattern();
        handler.leadScorpion();
        handler.leadSnakeAndOracle();
        handler.printMap();
        System.out.print('\n');
        handler.reArrange();
        handler.printMap();
    }
}
