import javafx.util.Pair;

import java.util.Vector;

public class CalabashBrosSet {
    private static final int numOfCalabash = 7;

    private CalabashBros[] calabashBrosVector;

    CalabashBrosSet(){
        calabashBrosVector = new CalabashBros[]{
            new CalabashBros(0, COLOR.values()[0]),
            new CalabashBros(1, COLOR.values()[1]),
            new CalabashBros(2, COLOR.values()[2]),
            new CalabashBros(3, COLOR.values()[3]),
            new CalabashBros(4, COLOR.values()[4]),
            new CalabashBros(5, COLOR.values()[5]),
            new CalabashBros(6, COLOR.values()[6])

        };
    }

    public void outOfOrder(Vector<Integer>vector) {
        for (int i = 0; i < numOfCalabash; i++) {
            int index = askWhere(vector.get(i));
            swap(i, index);
        }
    }

    public void longSnake(Vector<Pair<Integer, Integer>>p){
        queue();//先排好队

        for(int i = 0; i < numOfCalabash; i++) {
            getCalBro(i).setPosition(p.get(i).getKey() - 1, p.get(i).getValue() - 1);
        }
    }

    public int askWhere(int rank){
        int i;
        for (i = 0; i < numOfCalabash; i++) {
            if (getCalBro(i).whoAreYou() == rank) {
                break;
            }
        }
        return i;
    }

    public void setWar(Creature[][] war) {
        for (CalabashBros calabashBros : calabashBrosVector) {
            calabashBros.setWar(war);
        }
    }

    public void queue(){
        //先给第一个葫芦娃排队，老大排在最前面
        for (int i = 0; i < numOfCalabash; i++){
            for (int j = numOfCalabash - 1; j > i;j--) {
                if (cmp(getCalBro(j), getCalBro(j-1)))
                    swap(j, j-1);
            }
        }
    }

    public void swap(int i, int j) {
        CalabashBros t = getCalBro(i);
        setCalBro(getCalBro(j), i);
        setCalBro(t, j);
    }

    public boolean cmp(CalabashBros a, CalabashBros b) {
        return a.whoAreYou() < b.whoAreYou();
    }

    public CalabashBros getCalBro(int i){
        return calabashBrosVector[i];
    }

    public void setCalBro(CalabashBros c, int i){
        calabashBrosVector[i] = c;
    }
}
