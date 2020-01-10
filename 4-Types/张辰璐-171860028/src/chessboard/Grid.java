package chessboard;
import chessman.Creature;
import chessman.Huluwa;

public class Grid <T extends Creature>{
    private T x;

    public Grid() {
        x = null;

    }

    public boolean isEmpty() {
        return (x == null);
    }


    public Creature get() {
        return x;
    }

    public void clear() {
        x = null;
    }

    public void set(T x) {
        if(Huluwa.class.isInstance(x))
            System.out.println("a huluwa is in grid");
        this.x = x;
    }

}
