package creature;

import controller.MyMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class ScorpionEssence extends Creature{
    public ScorpionEssence(){
        super();
        this.name = "蝎子精";
        this.attack = 20;
        this.defence = 10;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/xiezi.jpg").toString(),50,50,false,false);
        this.battleImage =  new Image(this.getClass().getClassLoader().getResource(new String("pic/斧子.png")).toString(), 20,20,false,false); }

    @Override
    public void attack(Creature enemy,Canvas canvas) {
        if(!this.isAlive() || !enemy.isAlive()) return;
        int damage = this.attack - enemy.defence;
        if(critical){
            damage*=3;
            critical=false;
        }
        if(damage <= 0) damage = 0;
        enemy.lostBlood(damage);
        int enemy_x = enemy.i;
        int enemy_y = enemy.j; //坐标
        //根据坐标进行绘图，进行攻击显示
        if(canvas != null)
            drawAttack(enemy_x,enemy_y,canvas);
    }

    @Override //使用技能
    public void skill(MyMap map, Canvas canvas) { //蝎子精头脑简单四肢发达，能够发动无上妖力造成3倍暴击
        if(!this.isAlive()) return;
        this.setCritical();
    }
}
