package cn.whh.creature;

import cn.whh.map.Field;
import cn.whh.map.Position;
import javafx.scene.image.Image;

import java.util.Random;

public class Huluwa extends Creature implements FightStrategy
{
    private int id;
    private transient Field field;

    /*
         1、大娃(红娃):攻击和生命值较高，暴击可造成3~4倍伤害
         2、二娃(橙娃):聪明，暴击率高，能够轻易打出暴击。
         3、三娃(黄娃):防御力高，暴击时防御力提升6,并造成防御力*0.3的额外伤害
         4、四娃(绿娃):暴击时对中了debuff的敌人额外造成40点伤害，否则造成1.5倍暴击
         5、五娃(青娃):暴击时给敌人套破甲buff，降低10点护甲。
         6、六娃(蓝娃):高速，拥有全场最高速度，且攻击距离为3。
         7、七娃(紫娃):暴击时给敌人套中毒buff，每回合扣除10点生命。
      */
    private void setProperties()
    {
        switch (this.id)
        {
            case 1:{allHP=490;curHP=allHP;attack=50;curAttack=attack;defense=20;curDefense=defense;critRate=0.2;curCritRate=critRate;speed=1000;curSpeed=speed;attackDistance=1;curAttackDistance=attackDistance;};break;
            case 2:{allHP=400;curHP=allHP;attack=38;curAttack=attack;defense=15;curDefense=defense;critRate=0.35;curCritRate=critRate;speed=1000;curSpeed=speed;attackDistance=1;curAttackDistance=attackDistance;};break;
            case 3:{allHP=450;curHP=allHP;attack=38;curAttack=attack;defense=24;curDefense=defense;critRate=0.2;curCritRate=critRate;speed=1000;curSpeed=speed;attackDistance=1;curAttackDistance=attackDistance;};break;
            case 4:{allHP=400;curHP=allHP;attack=37;curAttack=attack;defense=15;curDefense=defense;critRate=0.2;curCritRate=critRate;speed=1000;curSpeed=speed;attackDistance=1;curAttackDistance=attackDistance;};break;
            case 5:{allHP=400;curHP=allHP;attack=50;curAttack=attack;defense=15;curDefense=defense;critRate=0.2;curCritRate=critRate;speed=1000;curSpeed=speed;attackDistance=1;curAttackDistance=attackDistance;};break;
            case 6:{allHP=400;curHP=allHP;attack=42;curAttack=attack;defense=10;curDefense=defense;critRate=0.2;curCritRate=critRate;speed=1500;curSpeed=speed;attackDistance=3;curAttackDistance=attackDistance;};break;
            case 7:{allHP=395;curHP=allHP;attack=33;curAttack=attack;defense=14;curDefense=defense;critRate=0.2;curCritRate=critRate;speed=1000;curSpeed=speed;attackDistance=1;curAttackDistance=attackDistance;};break;
            default:break;
        };
    }

    public Huluwa(int i, Field field)
    {
        super();
        this.id=i;
        this.aliveImage=new Image(this.getClass().getResourceAsStream("/"+ Integer.toString(i)+".png"));
        this.deadImage=new Image(this.getClass().getResourceAsStream("/"+ Integer.toString(i)+"_dead.png"));
        this.field=field;

        this.debuff=Debuff.None;
        this.debuffTime=0;
        setProperties();
    }

    public Huluwa(Huluwa huluwa)
    {
        super(huluwa);
        //System.out.println("这是葫芦娃的构造函数");
        this.id=huluwa.id;
        this.aliveImage=huluwa.aliveImage;
        this.deadImage=huluwa.deadImage;
        //this.field=huluwa.field;

        /*this.allHP=huluwa.allHP;
        this.curHP=huluwa.curHP;
        this.attack=huluwa.attack;
        this.critRate=huluwa.critRate;
        this.speed=huluwa.speed;*/
    }

    public int getId(){return this.id;}

    @Override
    public String toString() {
        return "this is Huluwa";
    }

    @Override
    public void run()
    {
        try {
            while (alive && field.getBadGuysCount()>0) {

                if(stop) continue;
                modifyDebuffState();
                if(curHP<=0) this.getThread().interrupt();


                thread.sleep(new Random().nextInt(500) + 2000-curSpeed);
                int minDistance = 100;
                Position minPos = new Position(-1, -1);
                Creature creature = this;
                for (int i = 0; i < field.height; i++) {
                    for (int j = 0; j < field.width; j++) {
                        creature = field.Get(i, j);
                        if ((creature instanceof Snake || creature instanceof Scorpion || creature instanceof Sodier) && creature.isAlive()) {
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
                        if (minPos.getY() > position.getY())  field.move(this, position, 1);

                        else if (minPos.getY() < position.getY()) field.move(this, position, 3);
                    }
                    else if (position.getX() > minPos.getX()) field.move(this, position, 0);

                    else if (position.getX() < minPos.getX()) field.move(this, position, 2);

                }
            }

        } catch (/*Interrupted*/Exception e) {
            System.out.println("我是葫芦娃，AWSL");
            this.alive=false;
            this.curHP=0;
            field.setGoodGuysCount(field.getGoodGuysCount()-1);
        }


    }

   public void fight(Creature creature)
   {
       int hurt=0;
       switch (id)
       {
           case 1:{
               if(Math.random()<curCritRate)
               {
                   isCritical=true;
                   if(Math.random()<0.5) hurt=attack*3-creature.getCurDefense()>0?attack*3-creature.getCurDefense():0;
                   else hurt=attack*4-creature.getCurDefense()>0?attack*4-creature.getCurDefense():0;
               }
               else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
               creature.setCurHP(creature.getCurHP()-hurt);
               if(creature.getCurHP()<=0) creature.getThread().interrupt();

           };break;

           case 2:{
               if(Math.random()<curCritRate)
               {
                   isCritical=true;
                   curCritRate+=0.04;
                   hurt=attack*2-creature.getCurDefense()>0?attack*2-creature.getCurDefense():0;
               }
               else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
               creature.setCurHP(creature.getCurHP()-hurt);
               if(creature.getCurHP()<=0) creature.getThread().interrupt();
           };break;

           case 3:{
               if(Math.random()<curCritRate)
               {
                   isCritical=true;
                   curDefense+=2;
                   hurt=curAttack+(int)(curDefense*0.3)-creature.getCurDefense()>0?curAttack+(int)(curDefense*0.3)-creature.getCurDefense():0;
               }
               else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
               creature.setCurHP(creature.getCurHP()-hurt);
               if(creature.getCurHP()<=0) creature.getThread().interrupt();
           };break;

           case 4:{
               if(Math.random()<curCritRate)
               {
                   isCritical=true;
                   if(creature.getDebuff()!=Debuff.None) hurt=curAttack+40-creature.getCurDefense()>0?curAttack+40-creature.getCurDefense():0;
                   else hurt=(int)(curAttack*1.5)-creature.getCurDefense()>0?(int)(curAttack*1.5)-creature.getCurDefense():0;
               }
               else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
               creature.setCurHP(creature.getCurHP()-hurt);
               if(creature.getCurHP()<=0) creature.getThread().interrupt();
           };break;

           case 5:{
               if(Math.random()<curCritRate)
               {
                   isCritical=true;
                   hurt=attack*2-creature.getCurDefense()>0?attack*2-creature.getCurDefense():0;
                   if(creature.getDebuff()==Debuff.None) creature.setDebuff(Debuff.Broken);
               }
               else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
               creature.setCurHP(creature.getCurHP()-hurt);
               if(creature.getCurHP()<=0) creature.getThread().interrupt();
           };break;

           case 6:{
               if(Math.random()<curCritRate)
               {
                   isCritical=true;
                   hurt=attack*2-creature.getCurDefense()>0?attack*2-creature.getCurDefense():0;
               }
               else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
               creature.setCurHP(creature.getCurHP()-hurt);
               if(creature.getCurHP()<=0) creature.getThread().interrupt();
           };break;

           case 7:{
               if(Math.random()<curCritRate)
               {
                   isCritical=true;
                   hurt=attack*2-creature.getCurDefense()>0?attack*2-creature.getCurDefense():0;
                   if(creature.getDebuff()==Debuff.None) creature.setDebuff(Debuff.Poison);
               }
               else hurt=curAttack-creature.getCurDefense()>0?curAttack-creature.getCurDefense():0;
               creature.setCurHP(creature.getCurHP()-hurt);
               if(creature.getCurHP()<=0) creature.getThread().interrupt();
           };break;

           default:break;
       }
   }

}
