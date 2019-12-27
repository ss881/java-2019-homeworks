package control;

import javax.swing.*;
import java.awt.*;
import static control.Console.*;

public class Buttons extends JFrame{
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JButton
    grandpaShow = new JButton("爷爷登场"),
    calaShow = new JButton("葫芦娃现身！");

    public Buttons(){
        setLayout(new FlowLayout());
        add(grandpaShow);
        add(calaShow);
    }

    public static void main(String[] args) {
        run(new Buttons(), 200, 100);
    }

}