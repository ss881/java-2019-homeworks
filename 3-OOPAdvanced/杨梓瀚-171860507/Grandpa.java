import javafx.util.Pair;

import java.util.*;

public class Grandpa extends Creature {
    private static final int numOfCalabash = 7;

    private CalabashBrosSet calabashBrosSet;

    private int N;

    Grandpa(int n){
        super(KIND.grandpa);
        N = n;
        calabashBrosSet = new CalabashBrosSet();
    }

    public void drawLots(){
        List<Integer>list = new ArrayList<>();

        for (int i = 0; i < numOfCalabash; i++) {
            list.add(i);
        }

        //抽签制
        Vector<Integer>vector = new Vector<>();
        for (int i = 0; i< numOfCalabash; i++) {
            int n = (int)(Math.random()*list.size());   //抽到的位置
            vector.add(list.get(n));
            list.remove(n);
        }

        calabashBrosSet.outOfOrder(vector); //自己排队

    }

    public void snake() {
        Vector<Pair<Integer, Integer>> p = Formation.snake(N);
        calabashBrosSet.longSnake(p);
    }

    public void set(Creature[][] war) {
        setWar(war);
        calabashBrosSet.setWar(war);
    }

}
