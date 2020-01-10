package creature;

import controller.MyMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class Wannabe extends Creature{
    public Wannabe(){
        super();
        this.name = "小喽啰";
        this.attack = 15;
        this.defence = 6;
        this.nature=false;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/wannabe.png").toString(),50,50,false,false);
        this.battleImage =  new Image(this.getClass().getClassLoader().getResource(new String("pic/斧子.png")).toString(),20,20,false,false);
    }
    @Override //使用技能
    public void skill(MyMap map, Canvas canvas) { //最垃圾的小喽啰，只能无能狂怒为自己回血
        if(!this.isAlive()) return;
        this.addBlood(5);
    }
}
