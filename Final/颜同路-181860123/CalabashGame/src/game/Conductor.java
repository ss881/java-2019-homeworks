package game;

import java.io.IOException;
import java.util.LinkedList;

class Conductor extends Creature {    //指挥官类
    public LinkedList<Creature> we = new LinkedList<Creature>(); //己方所有单位的引用

    Conductor(String name) {
        super(name, 1, 1, null);
        isconductor = true;
    }

    public void cheer() throws IOException {
    }//虚方法，加油助威，随机提高己方所有角色mp

    public void conduct(Creature c, int nx, int ny) {
        c.go(nx, ny);
    }//指挥自己的一方单位移动
}
