public class Snake extends Creature {
    private static Snake snake = new Snake();

    private Snake() {
        super("Snake");
    }

    public static Snake getSnakeObject() {
        return snake;
    }
}
