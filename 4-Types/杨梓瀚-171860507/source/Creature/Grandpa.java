package Creature;

import javafx.util.Pair;
import Formation.*;
import main.Battle;
import main.Position;

import java.util.*;

public class Grandpa extends Creature {
    private static final int numOfCalabash = 7;

    private CalabashBrosSet calabashBrosSet;

    private Formation formation;

    public Grandpa(){
        calabashBrosSet = new CalabashBrosSet();
        formation = new Formation();
    }

    public void init(){
        calabashBrosSet.init();
    }

    public void longsnake() {
        Vector<Pair<Integer, Integer>> p = formation.snake(numOfCalabash);
        calabashBrosSet.longSnake(p);
    }

    public void set() {
        Battle.setPosition(new Position<Grandpa>(this), Battle.getN() / 2 - 1, 0);
    }

    @Override
    public void print() {
        System.out.print('G' + " ");
    }
}
