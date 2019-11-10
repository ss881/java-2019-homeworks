//报数类型
enum CountKind{
    NAME,COLOR
}
//葫芦娃颜色
enum Color{
    RED,ORANGE,YELLOW,GREEN,CYAN,BLUE,PUPPLE
}
//葫芦娃名称
enum Name{
    ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN
}

public class God {
    public static void main(String[] args){
        Map gameMap=new Map();

        System.out.println("Shuffle Begins!");
        gameMap.shuffle();
        System.out.println(" ");

        System.out.println("BubbleSort Begins!");
        gameMap.bubbleSort();
        System.out.println(" ");

        System.out.println("Count Begins(according to names)!");
        gameMap.count(CountKind.NAME);
        System.out.println(" ");

        System.out.println("Shuffle Begins!");
        gameMap.shuffle();
        System.out.println(" ");

        System.out.println("BinarySort Begins!");
        gameMap.binarySort();
        System.out.println(" ");

        System.out.println("Count Begins(according to colors)!");
        gameMap.count(CountKind.COLOR);
        System.out.println(" ");
    }
}

