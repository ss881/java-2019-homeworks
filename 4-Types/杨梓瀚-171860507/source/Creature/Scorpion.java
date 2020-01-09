package Creature;

import javafx.util.Pair;
import Formation.*;
import main.Battle;
import main.Position;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class Scorpion extends Creature{
    enum formationType{crane, goose, yoke, fish, square, moon, arrow};

    private Vector<Minions>minionsVector;

    private int indexOfFormation = 0;

    private Formation formation;

    public Scorpion(){
        minionsVector = new Vector<>(13);
        formation = new Formation();

        for (int i = 0; i < 13; i++) {
            minionsVector.add(new Minions());
        }
    }

    public void go() {
        formationType f = formationType.values()[(indexOfFormation++ % 7)];

        Vector<Pair<Integer, Integer>> position = null;

        Class<?>mclass = formation.getClass();

        try {
            Method[] methods = mclass.getMethods();
            for (Method m : methods){
                if (m.getName().equals(f.toString())){
                    position = (Vector<Pair<Integer, Integer>>)m.invoke(formation, (Object[]) null);
                    break;
                }
            }
            //Method method = mclass.getMethod(f.toString(), (Class<?>) null);
            //position = (Vector<Pair<Integer, Integer>>)method.invoke(formation, (Object[]) null);
            Battle.setPosition(new Position<Scorpion>(this), position.get(0).getKey() - 1, Battle.getN() / 2 + position.get(0).getValue() - 1);

            for(int i = 0; i < position.size() - 1; i++) {
                Battle.setPosition(new Position<Minions>(minionsVector.get(i)), position.get(i + 1).getKey() - 1, Battle.getN() / 2 + position.get(i + 1).getValue() - 1);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        /*switch (f){
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
        }*/

    }

    @Override
    public void print() {
        System.out.print('S' + " ");
    }
}
