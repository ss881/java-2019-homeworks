package game;

import java.io.IOException;
import java.util.LinkedList;

class Creature implements Runnable {
    public String name = "";
    public int x = 0;
    public int y = 0;//x和y表示这个生物在地图上的位置
    public int hp = 0;  //生命值
    public int atk = 0; //攻击力
    public int mp = 0;  //能量，满了就可以放技能
    public boolean alive = true;  //是否存活
    public boolean isconductor = false; //是否是指挥官
    public String picture;
    public boolean wait = true; //是否等待

    Creature(String name, int hp, int atk, String picture) { //构造器
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.picture = picture;
    }

    @Override
    public void run() { //进入一个等待队列，等待指挥
        while (alive) {
            if (!wait) {
                action();
                wait = true;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }


    public void action() {
        //重要的自动寻路算法，每个回合执行的唯一入口
        //寻找离自己最近的敌人
        if (!alive)
            return;
        int min = 1000;
        int nx = 0;
        int ny = 0;
        Creature enemy = null;
        LinkedList<Creature> elist;
        if (this.getClass() == Calabash.class)
            elist = GameRun.snake.we;
        else elist = GameRun.grandpa.we;
        for (int i = 0;
             i < elist.size();
             i++) {
            if ((elist.get(i).x - x) * (elist.get(i).x - x) + (elist.get(i).y - y) * (elist.get(i).y - y) < min) {
                min = (elist.get(i).x - x) * (elist.get(i).x - x) + (elist.get(i).y - y) * (elist.get(i).y - y);
                nx = elist.get(i).x;
                ny = elist.get(i).y;
                enemy = elist.get(i);
            }
        }
        if (min > 4) //超过2的攻击范围，向敌方前进
        {
            boolean ret = true;
            if (nx - x > 2)
                ret = go(x + 1, y);
            else if (x - nx > 2)
                ret = go(x - 1, y);
            else if (ny - y > 2)
                ret = go(x, y + 1);
            else if (y - ny > 2)
                ret = go(x, y - 1);
            if (ret == false) {
                //需要绕路，这是一个极其弱智的版本，但是有效，因为没时间写了 ：）
                if (nx > x) {
                    go(x + 1, y);
                } else if (ny > y) {
                    go(x, y + 1);
                }
            }
        } else {
            try {
                attack(enemy);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean go(int nx, int ny) {   //Goto方法，用于行走
        if (!alive)
            return false;
        if (nx > 19 || ny > 19)
            return false;

        talk("我从(" + x + "," + y + ")走到了(" + nx + "," + ny + ")");
        Map.map[x][y] = null;
        x = nx;
        y = ny;
        Map.map[x][y] = this;
        return true;
    }

    public void attack(Creature c) throws IOException {   //攻击
        if (!alive)
            return;
        if (mp >= 100)
            skill(c);
        else {
            System.out.println("【" + name + "】攻击了【" + c.name + "】");
            c.hp -= atk;
            if (c.hp <= 0) {
                c.die();
                mp += 20;
            } else {
                hp -= c.atk;
                if (hp <= 0) {
                    die();
                    c.mp += 20;
                }
            }
            //与敌人相互攻击，若击杀则+20mp
        }
    }

    public void skill(Creature c) throws IOException {
        if (!alive)
            return;
        System.out.println("【" + name + "】对【" + c.name + "】释放了技能");
        mp -= 100;
        int combo = Game.getRandom(3) + 1;
        c.hp -= combo * atk;
        if (c.hp <= 0) {
            c.die();
        }
    }//虚方法，独特的技能，因为没时间写了，就全写成一样，暴击 吧 ：）

    public void talk(String s) {     //Talk方法，用于说话
        if (!alive || (!Game.running && !Game.isplay))
            return;
        System.out.println(name + "：" + s);
    }

    public void die() {
        if (!alive)
            return;
        talk("awsl！\n【" + name + "】扑街");
        if (this.getClass() == Calabash.class)
            GameRun.grandpa.we.remove(this);
        else GameRun.snake.we.remove(this);
        Map.map[x][y] = new Body();
        alive = false;
    }
}

