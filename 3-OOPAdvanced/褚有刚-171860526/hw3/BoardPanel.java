package hw3;

import hw3.creature.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BoardPanel extends JPanel {
    private int row;
    private int col;
    private ArrayList<Huluwa> huluwas = new ArrayList<>();
    private ArrayList<Minion> minions = new ArrayList<>();
    private Grandpa grandpa;
    private Snake snake;
    private Scorpion scorpion;
    public BoardPanel(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        for(int i = 1; i <= 7; i++) {
            huluwas.add(new Huluwa(i));
        }
        Collections.shuffle(huluwas);
        for(int i = 0; i < 20; i++) {
            minions.add(new Minion("喽啰#" + (i+1)));
        }
        grandpa = new Grandpa();
        snake = new Snake();
        scorpion = new Scorpion();
        grandpa.setPosition(0, 0);
        snake.setPosition(9, 0);
        grandpa.buZhen(huluwas);
        scorpion.buZhen(minions);
    }

    public void sortHuluwa() {
        grandpa.sortHuluwa(huluwas);
        repaint();
    }

    public void shuffleHuluwa() {
        grandpa.shuffleHuluwa(huluwas);
        repaint();
    }

    public void buZhen(JTextArea info) {
        // randomly buzhen
        scorpion.buZhen(minions);
        grandpa.cheer(info);
        snake.cheer(info);
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth();
        int height = getHeight();
        int gridWidth = (int)((double)width / col);
        int gridHeight = (int)((double)height / row);
        // draw background
        // draw horizontal lines
        for(int i = 1; i < row; i++) {
            g.drawLine(0, gridHeight * i, width, gridHeight * i);
        }
        // draw vertical lines
        for(int i = 1; i < col; i++) {
            g.drawLine(gridWidth * i, 0, gridWidth * i, height);
        }

        // draw creatures
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(grandpa);
        creatures.add(snake);
        creatures.add(scorpion);
        creatures.addAll(huluwas);
        creatures.addAll(minions);

        for(Creature c : creatures) {
            if(c.hasPosition()) {
                g.drawImage(c.getImage(), c.getPosition().getX() * gridWidth,
                        c.getPosition().getY() * gridHeight, gridWidth, gridHeight, this);
            }
        }
    }
}
