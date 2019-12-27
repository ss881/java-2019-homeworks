package huluwa.creature;

import javax.swing.*;
import java.util.Date;

public class Snake extends Creature implements Cheerable {
    public Snake() {
        super("蛇精");
//        super("蛇精",new ImageIcon("pics/蛇精.jpg").getImage());
    }
    @Override
    public String cheer() {
        return new Date() + "\n" + getName() + ": 冲啊，小妖们！\n";
    }
}
