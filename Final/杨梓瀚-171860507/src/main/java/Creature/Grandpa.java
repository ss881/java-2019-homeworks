package Creature;

import UI.MainWindow;
import Battle.*;

public class Grandpa extends Creature implements Runnable{
    private static final int numOfCalabash = 7;

    private static final int X = Battle.getM() / 2 - 1;

    private static final int Y = 0;

    //private CalabashBros calabashBrosSet;

    public Grandpa(Object lock){
        super(lock, "src/main/resources/pic/grandpa.jpg");
        setHpAndBD(Attribute.GRANDPA_HP, Attribute.GRANDPA_BD);
        Battle.setPosition(new Position<Grandpa>(this, X, Y));
        //calabashBrosSet = new CalabashBros();
    }

    @Override
    public void run() {
        while (!Fight.isEnd()){
            synchronized (lock){
                if (Fight.getNowTurn() == turn){
                    System.out.println(Fight.getNowTurn() + this.toString());
                    //Fight.print();
                    Fight.addNowTurn();
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
        //System.out.println("线程结束");
        Fight.cheer();
        MainWindow.start = false;
    }

    @Override
    public void print() {
        if (isCreatureAlive())
            System.out.print('G');
        else
            System.out.print('x');
    }

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Snake || creature instanceof Minions || creature instanceof Scorpion;
    }
}

