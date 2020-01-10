package Formation;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Vector;

public class Formation {        //阵型
    public Vector<Pair<Integer, Integer>> crane() {
        Vector<Pair<Integer, Integer>> p = new Vector<>();
        int []x = {2, 3, 4, 3, 2};
        int []y = {1, 2, 3, 4, 5};

        for (int i = 0; i < 5; i++) {
            p.add(new Pair<>(x[i], y[i]));
        }
        return p;
    }

    public Vector<Pair<Integer, Integer>> goose() {
        Vector<Pair<Integer, Integer>> p = new Vector<>();

        for (int i = 0; i < 5; i++) {
            p.add(new Pair<>(i + 1, 5 - i));
        }
        return p;
    }

    public Vector<Pair<Integer, Integer>> yoke() {
        Vector<Pair<Integer, Integer>> p = new Vector<>();
        int []y = {3, 2, 3, 2, 3};

        for (int i = 0; i < 5; i++) {
            p.add(new Pair<>(i + 1, y[i]));
        }
        return p;
    }

    public Vector<Pair<Integer, Integer>> fish() {//逐行扫描
        int []x = {2, 3, 2, 3, 4, 1, 2, 3, 4, 5, 3};
        int []num = {1, 1, 3, 5, 1};

        return fillVector(num, x);
    }

    public Vector<Pair<Integer, Integer>> square() {
        int []x = {3, 2, 4, 1, 5, 2, 4, 3};
        int []num = {1, 2, 2, 2, 1};

        return fillVector(num, x);
    }

    public Vector<Pair<Integer, Integer>> moon() {
        int []x = {3, 4, 2, 3, 1, 2, 3, 4, 5, 2, 3, 3, 4};
        int []num = {2, 2, 5, 2, 2};

        return fillVector(num, x);
    }

    public Vector<Pair<Integer, Integer>> arrow() {
        int []x = {3, 2, 3, 4, 1, 3, 5, 3, 3};
        int []num = {1, 3, 3, 1, 1};

        return fillVector(num, x);
    }

    public Vector<Pair<Integer, Integer>> snake(int x) {
        Vector<Pair<Integer, Integer>> p = new Vector<>();
        for (int i = 0; i < x; i++) {
            p.add(new Pair<>(i + 1, x / 4 + 2));
        }
        return p;
    }

    private Vector<Pair<Integer, Integer>> fillVector(int []num, int []x) {
        Vector<Pair<Integer, Integer>> p = new Vector<>();

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < num[i]; j++, k++) {
                p.add(new Pair<>(i + 1,x[k]));
            }
        }
        return p;
    }

}
