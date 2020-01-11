package Formation;

import javafx.util.Pair;

import java.util.List;

public class Yoke implements Loadable {
    private static List<Pair<Integer, Integer>> formation;

    @Override
    public boolean load(List<Pair<Integer, Integer>> formation) {
        if (formation == null)
            return false;
        Yoke.formation = formation;
        System.out.println("Yoke loaded");
        return true;
    }

    @Override
    public List<Pair<Integer, Integer>> getFormation() {
        return formation;
    }
}
