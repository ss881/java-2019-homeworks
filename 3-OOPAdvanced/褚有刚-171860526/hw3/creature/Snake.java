package hw3.creature;

import javax.swing.*;
import java.util.Date;

public class Snake extends Creature implements Cheer{
    public Snake() {
        super("蛇精",new ImageIcon("pics/蛇精.jpg").getImage());
//        setImage();
    }
    @Override
    public void cheer(JTextArea info) {
        info.append(new Date() + "\n");
        info.append(getName() + ": 冲啊, 小妖们!\n");
    }
}
