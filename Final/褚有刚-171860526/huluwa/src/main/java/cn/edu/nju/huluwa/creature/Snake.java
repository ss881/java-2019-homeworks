package cn.edu.nju.huluwa.creature;


import cn.edu.nju.huluwa.huluworld.HuluWorld;

import java.util.Date;

public class Snake extends Creature implements Cheerable {
    public Snake(HuluWorld huluWorld, Colour color) {
        super(huluWorld, "蛇精", color);
//        super("蛇精",new ImageIcon("pics/蛇精.jpg").getImage());
    }
    @Override
    public String cheer() {
        return new Date() + "\n" + getName() + ": 冲啊，小妖们！\n";
    }

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Grandpa || creature instanceof Huluwa;
    }
}
