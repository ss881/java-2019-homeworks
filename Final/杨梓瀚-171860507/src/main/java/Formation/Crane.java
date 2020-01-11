package Formation;

import javafx.util.Pair;

import java.util.List;

public class Crane implements Loadable{
    private static List<Pair<Integer, Integer>> formation;

    @Override
    public boolean load(List<Pair<Integer, Integer>> formation) {
        if (formation == null)
            return false;
        Crane.formation = formation;
        System.out.println("Crane loaded");
        return true;
    }

    @Override
    public List<Pair<Integer, Integer>> getFormation() {
        return formation;
    }
}
