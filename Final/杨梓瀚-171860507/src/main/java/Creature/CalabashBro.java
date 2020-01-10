package Creature;

import Battle.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class CalabashBro extends Creature implements Runnable{
    private COLOR color;

    public CalabashBro(COLOR c, Object lock, String imageFile){
        super(lock, imageFile);
        setHpAndBD(Attribute.CALABASH_HP, Attribute.CALABASH_BD);
        color = c;
    }


    @Override
    public void run() {
        //try {
            //System.out.println("get position " + this.toString());
            while (!Fight.isEnd()){
                synchronized (lock){
                    if (turn == Fight.getNowTurn()){
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
            //TimeUnit.SECONDS.sleep(1);
        //} catch (InterruptedException e) {
            //e.printStackTrace();
        //}
    }

    @Override
    public void print() {
        if (isCreatureAlive())
            System.out.print('C');
        else
            System.out.print('x');
    }

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Snake || creature instanceof Minions || creature instanceof Scorpion;
    }

}

