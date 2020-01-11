package creature;

public class Snake extends BadCreature{
    private static Snake snake;
    static{
        snake=new Snake();
    }

    public Snake(){
        super();
    }
    public Snake(final int x,final int y){
        super(x,y);
    }
    @Override
    public String toString(){
        return new String("蛇精");
    }

    public static Snake getSnake(){
        return snake;
    }
}
