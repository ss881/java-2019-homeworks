package Creature;

import Battle.*;
import Formation.*;

public class Snake extends Creature implements Runnable{
    private static final int X = Battle.getM() / 2 - 1;

    private static final int Y = Battle.getN() - 1;

    public Snake(Object lock){
        super(lock, "src/main/resources/pic/SheJing.jpg");
        setHpAndBD(Attribute.SNAKE_HP, Attribute.SNAKE_BD);
        Battle.setPosition(new Position<Snake>(this, X, Y));
    }

    @Override
    public void run() {
        while (!Fight.isEnd()){
            synchronized (lock){
                if (Fight.getNowTurn() == turn){
                    System.out.println(Fight.getNowTurn() + " " + turn + " " + this.toString());
                    Fight.print();
                    Fight.addNowTurn();
                    //System.out.println(Fight.getNowTurn());
                    lock.notifyAll();

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

    @Override
    public void print() {
        if (isCreatureAlive())
            System.out.print('N');
        else
            System.out.print('x');
    }


    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Grandpa || creature instanceof CalabashBro;
    }

}
