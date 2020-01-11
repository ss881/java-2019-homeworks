package Creature;

import javafx.util.Pair;
import Formation.*;
import Battle.*;

import javafx.scene.image.*;

import java.util.ArrayList;
import java.util.List;

public class Scorpion extends Creature implements Runnable{
    enum formationType{crane, goose, yoke, fish, square, moon, arrow};

    private List<Minions> minionsList;

    private int indexOfFormation = 0;

    //private Formation formation;

    private int numOfMinions;

    private Thread[] threads;

    public Scorpion(Object lock){
        super(lock, "src/main/resources/pic/XieZiJing.jpg");
        setHpAndBD(Attribute.SCORPION_HP, Attribute.SCORPION_BD);
        minionsList = new ArrayList<Minions>();
        //formation = new Formation();
    }

    public void initAllTurn(){
        for (int i = 0; i < numOfMinions; i++){
            minionsList.get(i).initTurn();
        }
        initTurn();
    }

    @Override
    public void run() {
        for (int i = 0; i < numOfMinions; i++){
            //minionsVector.get(i).run();
            threads[i].start();
        }
        while (!Fight.isEnd()){
            synchronized (lock){
                if (Fight.getNowTurn() == turn){
                    System.out.println(Fight.getNowTurn() + this.toString());
                    if (alive){
                        Position<?> position = Battle.getPositionByInstance(this);
                        assert position != null;
                        action(position);
                    }
                    //Fight.print();
                    Fight.addNowTurn();
                    lock.notifyAll();
                    if (alive){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public int init() {
        do {
            indexOfFormation = ((int)(Math.random() * 10)) % Type.values().length;
        }while (!Type.values()[indexOfFormation].checkCharacter());
        Type type = Type.values()[(indexOfFormation)];
        List<Pair<Integer, Integer>> position = Formation.getFormation(type);
        assert position != null;
        numOfMinions = position.size() - 1;
        threads = new Thread[numOfMinions];
        for (int i = 0; i < numOfMinions; i++){
            minionsList.add(new Minions(lock, Attribute.MINIONS_HP, Attribute.MINIONS_BD));
            threads[i] = new Thread(minionsList.get(i));
        }
        Battle.setPosition(new Position<Scorpion>(this, position.get(0).getKey(), Battle.getN() / 2 + position.get(0).getValue() - 1));


        for(int i = 0; i < position.size() - 1; i++) {
            Battle.setPosition(new Position<Minions>(minionsList.get(i), position.get(i + 1).getKey(), Battle.getN() / 2 + position.get(i + 1).getValue() - 1));
        }
        return position.size();
    }

    public void addMinions(int x, int y, int hp, int bd, int turn){
        Minions minions = new Minions(lock, hp, bd);
        Battle.setPosition(new Position<Minions>(minions, x, y));
        minions.setTurn(turn);
        minionsList.add(minions);
    }

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Grandpa || creature instanceof CalabashBro;
    }

    @Override
    public void print() {
        if (isCreatureAlive())
            System.out.print('S');
        else
            System.out.print('x');
    }

    public List<ImageView> getAllImageView(){
        List<ImageView> list = new ArrayList<>();
        list.add(getImageView());
        for (int i = 0; i< numOfMinions; i++){
            list.add(minionsList.get(i).getImageView());
        }
        return list;
    }

    public List<Minions> getMinionsList(){
        return minionsList;
    }


}
