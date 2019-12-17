package cn.whh.creature;

import cn.whh.map.Field;
import cn.whh.map.Position;
import javafx.scene.image.Image;

import java.util.Random;

public class Snake extends Creature implements FightStrategy{
    private Field field;

    public Snake(Field field)
    {
        super();
        this.allHP=80;
        this.curHP=allHP;
        this.aliveImage=new Image(this.getClass().getResourceAsStream("/snake.png"));
        this.deadImage=new Image(this.getClass().getResourceAsStream("/snake_dead.png"));
        this.field=field;

        //低生命值低护甲，高攻远距离
        this.allHP=300;
        this.curHP=allHP;
        this.attack=45;
        this.curAttack=attack;
        this.defense=14;
        this.curDefense=defense;
        this.critRate=0.3;
        this.curCritRate=critRate;
        this.speed=1300;
        this.curSpeed=speed;
        this.attackDistance=3;
        this.curAttackDistance=attackDistance;
    }

    public Snake(Snake snake)
    {
        super(snake);
        //this.allHP=snake.allHP;
        //this.curHP=snake.curHP;
    }

    public void fight(Creature creature)
    {
        int hurt=0;
        if(Math.random()<curCritRate)
        {
            hurt=attack*2-creature.getCurDefense()>0?attack*2-creature.getCurDefense():0;
            isCritical=true;
        }
        else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
        creature.setCurHP(creature.getCurHP()-hurt);
        if(creature.getCurHP()<=0) creature.getThread().interrupt();
    }

    @Override
    public void run()
    {
        try
        {
            while(alive&&field.getGoodGuysCount()>0)
            {
                if(stop) continue;
                modifyDebuffState();
                if(curHP<=0) this.getThread().interrupt();

                thread.sleep(new Random().nextInt(500) +2000-curSpeed);
                int minDistance = 100;

                Position minPos = new Position(-1, -1);
                Creature creature = this;
                for (int i = 0; i < field.height; i++) {
                    for (int j = 0; j < field.width; j++) {
                        creature = field.Get(i, j);
                        if ((creature instanceof Huluwa || creature instanceof Grandpa) && creature.isAlive()) {
                            int distance = Math.abs(position.getX() - i) + Math.abs(position.getY() - j);
                            if (distance < minDistance) {
                                minDistance = distance;
                                minPos.setX(i);
                                minPos.setY(j);
                            }
                        }
                    }
                }

                if (minDistance <=curAttackDistance) {
                    creature = field.Get(minPos.getX(), minPos.getY());
                    fight(creature);

                } else {
                    if (position.getX() == minPos.getX()) {
                        if (minPos.getY() > position.getY()) field.move(this, position, 1);
                        else if (minPos.getY() < position.getY()) field.move(this, position, 3);
                    } else if (position.getX() < minPos.getX()) field.move(this, position, 2);

                    else if (position.getX() > minPos.getX()) field.move(this, position, 0);

                }
            }

        }catch(InterruptedException e)
        {
            System.out.println("我是蛇精，我死了");
            this.alive=false;
            this.curHP=0;
            field.setBadGuysCount(field.getBadGuysCount()-1);
        }
    }
}
