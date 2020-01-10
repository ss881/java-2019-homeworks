package Formation;

import javafx.util.Pair;

import java.util.List;

public class Fish implements Loadable {
    private static List<Pair<Integer, Integer>> formation;

    @Override
    public boolean load(List<Pair<Integer, Integer>> formation) {
        if (formation == null)
            return false;
        Fish.formation = formation;
        System.out.println("Fish loaded");
        return true;
    }

    @Override
    public List<Pair<Integer, Integer>> getFormation() {
        return formation;
    }
}
