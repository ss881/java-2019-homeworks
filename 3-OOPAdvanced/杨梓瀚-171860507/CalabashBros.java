
enum COLOR{
    RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE
}

public class CalabashBros extends Creature{
    private COLOR color;

    private int rank;

    CalabashBros(int i, COLOR c){
        super(KIND.calaBro);
        rank = i;
        color = c;

    }

    public int whoAreYou() {
        return rank;
    }
}
