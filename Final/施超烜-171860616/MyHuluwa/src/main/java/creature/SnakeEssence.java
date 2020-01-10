package creature;

import controller.MyMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class SnakeEssence extends Creature {
    public SnakeEssence() {
        super();
        this.name = "蛇精";
        this.attack = 20;
        this.defence = 0;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/snake.png").toString(),50,50,false,false);
        this.battleImage =  new Image(this.getClass().getClassLoader().getResource(new String("pic/斧子.png")).toString(), 20,20,false,false);
        this.skillImage =  new Image(getClass().getClassLoader().getResource("pic/加.png").toString(),20,20,false,false);
    }

    @Override //使用技能
    public void skill(MyMap map, Canvas canvas){ //蛇精释放妖风，对所有地方单位造成伤害，所有友方单位进行恢复
        if(!this.isAlive()) return;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                Creature temp = map.getCreature(i,j);
                if(temp != null){
                    if(temp.isAlive()){
                        if(temp.getNature()){
                            temp.lostBlood(10);
                            canvas.getGraphicsContext2D().drawImage(this.skillImage, j * 50, i * 50);
                        }else {
                            temp.addBlood(10);
                            canvas.getGraphicsContext2D().drawImage(this.skillImage, j * 50, i * 50);
                        }}
                }
            }
        }
    }
}
