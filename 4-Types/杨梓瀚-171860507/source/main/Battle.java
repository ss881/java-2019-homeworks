package main;

import Creature.Creature;

import java.util.ArrayList;
import java.util.Collections;
/*
描述二维空间内的位置信息
 */

public class Battle {
    private static final int N = 10;

    private static ArrayList<Position<?>>positionArrayList = new ArrayList<>(Collections.nCopies(N * N, null));;

    Battle(){
    }

    public static void setPosition(Position<?> p, int x, int y){
        positionArrayList.set(x * N + y, p);
    }

    public static Position<?> getPosition(int x, int y){
        return positionArrayList.get(x * N + y);
    }

    public static int getN(){
        return N;
    }

    public static void print(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (positionArrayList.get(i * N + j) != null)
                    positionArrayList.get(i * N + j).getHolder().print();
                else
                    System.out.print(' ' + " ");
            }
            System.out.print('\n');
        }
    }

    public static void reset(){
        Position<?>position = null;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                position = positionArrayList.get(i * N + j);
                if (position != null)
                    if (position.getHolder().getClass().getSimpleName().equals("Minions") || position.getHolder().getClass().getSimpleName().equals("Scorpion"))
                        positionArrayList.set(i * N + j, null);
            }
        }
    }

}
