package Creature;

import main.Battle;
import main.Position;

public class Snake extends Creature {
    @Override
    public void print() {
        System.out.print('N' + " ");
    }

    public void set(){
        Battle.setPosition(new Position<Snake>(this), Battle.getN() / 2 - 1, Battle.getN() - 1);
    }
}
