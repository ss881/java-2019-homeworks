package controller;

import creature.Creature;
import javafx.scene.canvas.Canvas;

import static java.lang.System.exit;

public class Position {
    private Creature creature;
    private int i;
    private int j;
    private boolean empty;

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
        this.empty = true;
        creature = null;
    }

    public void setCreature(Creature creature) {//初始化时在this位置上放置creature生物
        this.creature = creature;
        this.empty = false;
        creature.setPosition(i,j);
    }

    public void moveCreatureFrom(Position position) {//position上的creature移动到this的位置上
        creature = position.creature;
        creature.setPosition(i,j);
        empty = false;
        position.creature = null;
        position.empty = true;
    }

    public void swapCreatureWith(Position position) {//this位置与position位置上的生物进行交换
        Creature temp = position.creature;
        position.creature = creature;
        creature = temp;
        creature.setPosition(i,j);
        position.creature.setPosition(position.i,position.j);
    }

    public boolean isEmpty() {
        return empty;
    }

    public void showCreature(Canvas canvas) {
        if(!empty) {
            if (canvas != null)
                creature.showGUI(canvas);
            else {
                System.out.println("no canvas!");
                exit(-1);
            }
        }
    }

    public Creature getCreature(){
        return this.creature;
    }

    public void clear() {
        this.creature = null;
        this.empty = true;
    }

    public void kill() {
        if(this.creature!=null){
            if(this.creature.isAlive()){
                this.creature.killSelf();
            }
            this.creature = null;
        }
        this.empty = true;
    }
}