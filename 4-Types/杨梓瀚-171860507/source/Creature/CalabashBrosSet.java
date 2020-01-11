package Creature;

import javafx.util.Pair;
import main.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CalabashBrosSet {
    private static final int numOfCalabash = 7;

    private CalabashBros[] calabashBrosVector;

    public CalabashBrosSet(){
        calabashBrosVector = new CalabashBros[numOfCalabash];
    }

    public void init(){
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < numOfCalabash; i++) {
            list.add(i);
        }
        //决定初始乱序
        for (int i = 0; i< numOfCalabash; i++) {
            int n = (int)(Math.random()*list.size());   //抽到的位置
            calabashBrosVector[i] = new CalabashBros(list.get(n), COLOR.values()[list.get(n)]);
            list.remove(n);
        }

    }

    public void longSnake(Vector<Pair<Integer, Integer>>p){
        queue();//先排好队

        for(int i = 0; i < numOfCalabash; i++) {
            //getCalBro(i).setPosition(p.get(i).getKey() - 1, p.get(i).getValue() - 1);
            Battle.setPosition(new Position<CalabashBros>(getCalBro(i)), p.get(i).getKey() - 1, p.get(i).getValue() - 1);
        }
    }

    private void queue(){
        //先给第一个葫芦娃排队，老大排在最前面
        for (int i = 0; i < numOfCalabash; i++){
            for (int j = numOfCalabash - 1; j > i;j--) {
                if (cmp(getCalBro(j), getCalBro(j-1)))
                    swap(j, j-1);
            }
        }
    }

    private void swap(int i, int j) {
        CalabashBros t = getCalBro(i);
        setCalBro(getCalBro(j), i);
        setCalBro(t, j);
    }

    private boolean cmp(CalabashBros a, CalabashBros b) {
        return a.whoAreYou() < b.whoAreYou();
    }

    private CalabashBros getCalBro(int i){
        return calabashBrosVector[i];
    }

    private void setCalBro(CalabashBros c, int i){
        calabashBrosVector[i] = c;
    }
}
