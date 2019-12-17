package cn.whh.creature;

import cn.whh.map.Field;
import cn.whh.map.Position;
import javafx.scene.image.Image;

import java.util.Random;

public class Grandpa extends Creature {
    private Field field;

    public Grandpa(Field field)
    {
        super();
        this.aliveImage=new Image(this.getClass().getResourceAsStream("/grandPa.png"));
        this.deadImage=new Image(this.getClass().getResourceAsStream("/grandPa_dead.png"));
        this.field=field;

        this.allHP=210;
        this.curHP=allHP;
        this.attack=30;   //爷爷的攻击相当于奶量
        this.curAttack=attack;
        this.defense=5;
        this.curDefense=defense;
        this.critRate=0.3;
        this.curCritRate=critRate;
        this.speed=1000;
        this.curSpeed=speed;
        this.attackDistance=3;
        this.curAttackDistance=attackDistance;
    }

    public Grandpa(Grandpa grandpa)
    {
        super(grandpa);
        /*this.allHP=grandpa.allHP;
        this.curHP=grandpa.curHP;
        this.speed=grandpa.speed;
        this.attack=grandpa.attack;
        this.defense=grandpa.defense;
        this.critRate=grandpa.critRate;*/
    }


    @Override
    public void run()
    {
        try {
            while (alive && field.getBadGuysCount() > 0) {
                if (stop) continue;
                modifyDebuffState();
                if (curHP <= 0) this.getThread().interrupt();
                thread.sleep(new Random().nextInt(100) + 2000-curSpeed);

                //int minDistance = 100;
                int minBlood=9999;
                Position minPos = new Position(-1, -1);
                Creature creature = this;
                for (int i = 0; i < field.height; i++) {
                    for (int j = 0; j < field.width; j++) {
                        creature = field.Get(i, j);
                        if ((creature instanceof Huluwa) && creature.isAlive()) {
                            //int distance = Math.abs(position.getX() - i) + Math.abs(position.getY() - j);
                            if (creature.getCurHP()<minBlood) {
                                minBlood=creature.getCurHP();
                                minPos.setX(i);
                                minPos.setY(j);
                            }
                        }
                    }
                }

                int minDistance= Math.abs(position.getX() - minPos.getX()) + Math.abs(position.getY() - minPos.getY());
                if (minDistance <= curAttackDistance) {
                    creature = field.Get(minPos.getX(), minPos.getY());
                    int curHP=creature.getCurHP();
                    int treament=attack;

                    if(Math.random()<curCritRate)
                    {
                        if(field.getGoodGuysCount()>3) treament=attack*2;
                        else treament=attack+10;
                        isCritical=true;
                    }
                    if(curHP+treament>=creature.getAllHP()) creature.setCurHP(creature.getAllHP());
                    else creature.setCurHP(curHP+treament);

                } else {
                    if (position.getX() == minPos.getX()) {
                        if (minPos.getY() > position.getY())  field.move(this, position, 1);

                        else if (minPos.getY() < position.getY()) field.move(this, position, 3);
                    }
                    else if (position.getX() > minPos.getX()) field.move(this, position, 0);

                    else if (position.getX() < minPos.getX()) field.move(this, position, 2);

                }
            }
        }catch (/*Interrupted*/Exception e) {
            System.out.println("我是爷爷，我死了");
            this.alive=false;
            this.curHP=0;
            field.setGoodGuysCount(field.getGoodGuysCount()-1);
        }
    }
}
