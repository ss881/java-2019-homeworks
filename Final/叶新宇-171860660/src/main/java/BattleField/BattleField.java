package BattleField;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.io.BufferedWriter;
import java.io.IOException;
import Property.Position;
import Item.Creature;
import Item.DeadCreature;


public class BattleField {
    private Block[][] info = new Block[16][9];
    public BattleField() {
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 9; j++)
                info[i][j] = new Block();
    }

    public Block[][] getBattleField() {
        return info;
    }

    public void printBattleField(GraphicsContext gc) {
        synchronized (info) {
            for(int i = 0; i < 16; i++) {
                for(int j = 0; j < 9; j++) {
                    if(!info[i][j].isEmpty()) {
                        Creature creature = info[i][j].getHolder();
                        Position pos = creature.getPosition();
                        gc.drawImage(info[i][j].getImage(), pos.getX(), pos.getY(),60,60);
                        if(!(creature instanceof DeadCreature)) {
                            gc.setFill(Color.RED);
                            gc.fillRect(pos.getX() + 5,pos.getY(),50,5);
                            gc.setFill(Color.color(0,1.0,0.3));
                            float ratio = creature.getHPRatio();
                            gc.fillRect(pos.getX() + 5,pos.getY(),50 * ratio,5);
                        }
                        else {
                            gc.drawImage(creature.getImage(),pos.getX(),pos.getY(),60,60);
                            if(((DeadCreature)creature).isTimeout())
                                info[i][j].withdraw();
                            else
                                ((DeadCreature)creature).decreaseTime();
                        }
                    }
                }
            }
        }
    }

    public void outputRep(BufferedWriter fout) {
        synchronized (info) {
            for(int i = 0; i < 16; i++) {
                for(int j = 0; j < 9; j++) {
                    if(!info[i][j].isEmpty()) {
                        try {
                            fout.write(info[i][j].getHolder().getStatus());
                            fout.newLine();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void clearBattleField() {
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 9; j++)
                info[i][j].withdraw();
    }
}
