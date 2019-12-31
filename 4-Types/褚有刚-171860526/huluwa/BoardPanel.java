package huluwa;

import huluwa.creature.Creature;
import huluwa.util.*;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private int row;
    private int col;
    private HuluWorld huluWorld;

    private class Painter extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40); // sleep 40ms to keep the flush frequency
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BoardPanel(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        huluWorld = WorldBuilder.buildHuluWorld(row, col);
        new Painter().start();
    }

    public void sortHuluwa() {
        huluWorld.sortHuluwa();
    }

    public void shuffleHuluwa() {
        huluWorld.shufflehuluwa();
    }

    public void buZhen(JTextArea info) {
        info.append(huluWorld.badTeamBuZhen());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth();
        int height = getHeight();
        double gridWidth = (double) width / col;
        double gridHeight = (double) height / row;
        // draw background
        // draw horizontal lines
        for (int i = 1; i < row; i++) {
            g.drawLine(0, (int) (gridHeight * i), width, (int) (gridHeight * i));
        }
        // draw vertical lines
        for (int i = 1; i < col; i++) {
            g.drawLine((int) (gridWidth * i), 0, (int) (gridWidth * i), height);
        }

        // draw creatures
        for (Creature c : huluWorld.getAllCreatures()) {
            if (c.hasPosition()) {
                g.drawImage(c.getImage(), (int) (c.getPosition().getX() * gridWidth),
                        (int) (c.getPosition().getY() * gridHeight), (int) gridWidth, (int) gridHeight, this);
            }
        }
    }
}
