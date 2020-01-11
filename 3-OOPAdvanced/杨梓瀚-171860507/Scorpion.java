import javafx.util.Pair;

import java.util.Vector;

public class Scorpion extends Creature{

    enum formation{crane, goose, yoke, fish, square, moon, arrow};

    private Vector<Minions>minionsVector;

    private int indexOfFormation = 0;

    private int N;

    Scorpion(int n){
        super(KIND.scorpion);
        minionsVector = new Vector<>(13);

        for (int i = 0; i < 13; i++) {
            minionsVector.add(new Minions());
        }
        N = n;
    }

    public void go(Creature[][] war) {
        formation f = formation.values()[(indexOfFormation++ % 7)];

        Vector<Pair<Integer, Integer>> position;
        switch (f){
            case fish:
                position = Formation.fish();
                break;
            case moon:
                position = Formation.moon();
                break;
            case yoke:
                position = Formation.yoke();
                break;
            case arrow:
                position = Formation.arrow();
                break;
            case crane:
                position = Formation.crane();
                break;
            case goose:
                position = Formation.goose();
                break;
            case square:
                position = Formation.square();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + f);
        }
        this.setPosition(position.get(0).getKey() - 1, N / 2 + position.get(0).getValue() - 1);
        this.setWar(war);

        for(int i = 0; i < position.size() - 1; i++) {
            minionsVector.get(i).setPosition(position.get(i + 1).getKey() - 1, N / 2 + position.get(i + 1).getValue() - 1);
            minionsVector.get(i).setWar(war);
        }
    }
}
