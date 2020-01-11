package Creature;

public class CalabashBros extends Creature{
    private COLOR color;

    private int rank;

    public CalabashBros(int i, COLOR c){
        rank = i;
        color = c;
    }

    public int whoAreYou() {
        return rank;
    }

    @Override
    public void print() {
        System.out.print('C' + " ");
    }
}
