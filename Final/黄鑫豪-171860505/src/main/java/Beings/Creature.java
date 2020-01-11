package Beings;
import Field.TwoDSpace;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Creature implements Runnable{
    private int x, y, view_index, run_index;
    private int health;
    private TwoDSpace tds;
    private boolean isGood;
    private boolean toRun;
    private Image image;

    Creature(int x, int y, int health, TwoDSpace tds, boolean isGood, String imagepath) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.tds = tds;
        this.isGood = isGood;
        this.view_index = -1;
        this.run_index = -1;
        this.toRun = true;
        this.image = new Image(imagepath);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHealth(int i) {
        health = i;
    }

    public int getX() {
        return this.x;
    }

    public void setViewIndex(int view_index) {
        this.view_index = view_index;
    }

    public void setRunIndex(int run_index) {
        this.run_index = run_index;
    }

    public int getViewIndex() {
        return view_index;
    }

    public int getY() {
        return this.y;
    }

    public Image getImage() {
        return this.image;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void run(){
        while (!Thread.interrupted()) {
            if (!toRun) return;

            Random rand = new Random();

            if (!isAlive()) {
                tds.getAllView().get(view_index).getIV().setImage(new Image("dead.png"));
                toRun = false;
            }else{
                int targetDis = 50;
                Creature target = null;
                ArrayList<Creature> enemies = isGood ? tds.getBads() : tds.getGoods();
                for (Creature enemy : enemies) {
                    if (!enemy.isAlive()) continue;
                    int dis = Math.abs(enemy.x - x) + Math.abs(enemy.y - y);
                    if (dis < targetDis) {
                        targetDis = dis;
                        target = enemy;
                    }
                }

                if (target == null) {
                    toRun = false;
                } else {
                    if (targetDis == 1) {
                        tds.Fight(this, target, rand);
                    } else {
                        if (tds.Move(this, target, rand)) {
                            tds.getAllView().get(view_index).setX(x);
                            tds.getAllView().get(view_index).setY(y);
                        }
                    }
                }
            }

            Platform.runLater(() -> tds.drawOneCreature(view_index));

            try {
                if (toRun) {
                    Thread.sleep(rand.nextInt(500) + 300);
                } else {
                    tds.stopOneRun(run_index);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

}
