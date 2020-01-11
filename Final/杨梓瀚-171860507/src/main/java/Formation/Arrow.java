package Formation;

import javafx.util.Pair;

import java.util.List;

public class Arrow implements Loadable {
    private static List<Pair<Integer, Integer>> formation;

    @Override
    public boolean load(List<Pair<Integer, Integer>> formation) {
        if (formation == null)
            return false;
        Arrow.formation = formation;
        System.out.println("Arrow loaded");
        return true;
    }

    @Override
    public List<Pair<Integer, Integer>> getFormation() {
        return formation;
    }
}
