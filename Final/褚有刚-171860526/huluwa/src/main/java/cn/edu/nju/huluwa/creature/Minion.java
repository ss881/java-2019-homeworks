package cn.edu.nju.huluwa.creature;


import cn.edu.nju.huluwa.huluworld.HuluWorld;

public class Minion extends Creature {
    public Minion(HuluWorld huluWorld, String name, Colour color) {
        super(huluWorld, name, color);
//        super(name, new ImageIcon("pics/小喽啰.jpg").getImage());
    }

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Grandpa || creature instanceof Huluwa;
    }
}
