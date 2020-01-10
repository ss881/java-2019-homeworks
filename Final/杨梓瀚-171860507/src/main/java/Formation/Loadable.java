package Formation;

import javafx.util.Pair;

import java.util.List;

public interface Loadable {
    boolean load(List<Pair<Integer, Integer>> formation);

    List<Pair<Integer, Integer>> getFormation();
}
