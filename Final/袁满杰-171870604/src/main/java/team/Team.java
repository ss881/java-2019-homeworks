package team;

import creature.Creature;
import exception.CharacterErrorException;
import exception.HuluwaOutOfNumException;
import exception.ZhenfaIDOutOfNumException;
import space.Space;

import java.util.ArrayList;

public abstract class Team extends ArrayList<Creature> {
    protected int[] num_map = {8, 8, 6, 7, 11, 9, 20, 13};
    Space space;
    ArrayList<Thread> pool = new ArrayList<Thread>();

    public Team(Space s) {
        space = s;
    }

    public abstract void zhenfa(int id) throws HuluwaOutOfNumException, ZhenfaIDOutOfNumException, CharacterErrorException;

    public void born() {
        for (Creature i : this)
            i.born();
    }

    public int check_alive() {
        int res = 0;
        for (Creature i : this)
            if (i.is_alive())
                res++;
        return res;
    }

    public Creature find(String name) {
        Creature res = null;
        for (Creature i : this)
            if (i.equal(name))
                return i;
        return res;
    }

    public void start() {
        for (Creature i : this
        ) {
            Thread temp = new Thread(i);
            pool.add(temp);
            temp.start();
        }
    }

    public void interrupt() {
        for (Thread i : pool) {
            i.interrupt();
        }
    }

    public void set_visiable(boolean value) {
        for (Creature i : this) {
            i.set_visiable(value);
        }
    }

    public void init() {
        for (Creature i : this
        ) {
            i.init();
        }
    }
}
