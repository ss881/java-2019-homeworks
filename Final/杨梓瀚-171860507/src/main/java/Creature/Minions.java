package Creature;

import Battle.*;

import Creature.Creature;

public class Minions extends Creature implements Runnable{

    public Minions(Object lock, int hp, int bd){
        super(lock, "src/main/resources/pic/minion.jpeg");
        setHpAndBD(hp, bd);
    }

    @Override
    public void run() {
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

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Grandpa || creature instanceof CalabashBro;
    }

    @Override
    public void print() {
        if (isCreatureAlive())
            System.out.print('M');
        else
            System.out.print('x');
    }
}
