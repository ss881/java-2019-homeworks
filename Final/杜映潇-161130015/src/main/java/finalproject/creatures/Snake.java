package finalproject.creatures;

public class Snake extends GoblinSide {
    private static Snake snake = new Snake();

    private Snake() {
        super("Snake");
    }

    public static Snake getSnakeObject() {
        return snake;
    }
}
