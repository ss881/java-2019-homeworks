package creature;

import controller.MyMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Grandfather extends Creature {
    public Grandfather(){
        super();
        name = "爷爷";
        nature = true;
        this.attack = 15;
        this.defence = 5;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/grandfather.png").toString(),50,50,false,false);
        this.skillImage =  new Image(getClass().getClassLoader().getResource("pic/药.png").toString(),20,20,false,false);
    }

    @Override //使用技能
    public void skill(MyMap map, Canvas canvas){ //爷爷能够为所有友方单位回复10点生命值
        if(!this.isAlive()) return;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                Creature temp = map.getCreature(i,j);
                if(temp != null){
                    if(temp.isAlive() && temp.getNature()) {
                        temp.addBlood(10); //回复
                        canvas.getGraphicsContext2D().drawImage(this.skillImage, j * 50, i * 50);
                    }
                }
            }
        }
    }
}
