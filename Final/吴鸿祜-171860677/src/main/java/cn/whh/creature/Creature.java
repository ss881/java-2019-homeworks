package cn.whh.creature;
import cn.whh.map.Position;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Creature implements Runnable, Serializable {

    //基本属性:生死的图片，生死状态，位置，线程，暂停标记
    public transient Image aliveImage;
    public transient Image deadImage;

    protected boolean alive;
    protected transient Position position;
    protected transient Thread thread;
    protected boolean stop;  //判断游戏是否暂停

    //游戏属性：生命值,移速/攻速，攻击力，防御力，暴击率,攻击距离
    protected int curHP;
    protected int allHP;
    protected int curSpeed;
    protected int speed;
    protected int curAttack;
    protected int attack;
    protected int curDefense;
    protected int defense;
    protected double curCritRate;
    protected double critRate;
    protected boolean isCritical; //这回合是否触发了暴击
    protected int attackDistance;
    protected int curAttackDistance;

    protected Debuff debuff;
    protected int debuffTime;

    public Creature()
    {
        this.position=new Position(-1,-1);
        this.alive=true;
        this.stop=true;

        this.allHP=0;
        this.curHP=allHP;
        this.speed=1000;
        this.curSpeed=speed;
        this.attack=0;
        this.curAttack=attack;
        this.defense=0;
        this.curDefense=defense;
        this.critRate=0;
        this.curCritRate=critRate;
        this.isCritical=false;

        this.attackDistance=0;
        this.curAttackDistance=attackDistance;

        this.debuff=Debuff.values()[0];
        this.debuffTime=0;
    }

    public Creature(Creature creature)
    {
        // System.out.println("这是基类的构造函数");
        this.aliveImage=creature.aliveImage;
        this.deadImage=creature.deadImage;
        this.alive=creature.alive;
        this.position=creature.position;
        this.thread=creature.thread;
        this.stop=creature.stop;

        this.allHP=creature.allHP;
        this.curHP=creature.curHP;
        this.speed=creature.speed;
        this.curSpeed=creature.curSpeed;
        this.attack=creature.attack;
        this.curAttack=creature.curAttack;
        this.defense=creature.defense;
        this.curDefense=creature.curDefense;
        this.critRate=creature.critRate;
        this.curCritRate=creature.curCritRate;
        this.isCritical=creature.isCritical;
        this.attackDistance=creature.attackDistance;
        this.curAttackDistance=creature.curAttackDistance;

        this.debuff=creature.debuff;
        this.debuffTime=creature.debuffTime;
    }

    public void setPosition(int x,int y)
    {
        position.setX(x);
        position.setY(y);
    }

    public Position getPosition()
    {
        return position;
    }

    public boolean isAlive() {return alive;}

    public void setAlive(boolean cur){alive=cur;}

    public void setThread(Thread t) {thread=t;}

    public Thread getThread(){return thread;}

    public boolean isStop() {return stop;}

    public void setStop(boolean state) {stop=state;}

    @Override
    public void run(){}

    //属性设置
    public int getAllHP(){return allHP;}

    public int getCurHP(){return curHP;}

    public void setCurHP(int hp){curHP=hp;}

    public int getAttack() {return attack;}

    public int getDefense() {return defense;}

    public int getCurDefense(){return curDefense;}

    public void setCurDefense(int defend) {curDefense=defend;}

    public double getCritRate() {return critRate;}

    public int getAttackDistance(){return attackDistance;}

    public int getSpeed(){return speed;}

    public Debuff getDebuff(){return debuff;}

    public void setDebuff(Debuff debuff){this.debuff=debuff;this.debuffTime=3;}

    public int getDebuffTime(){return debuffTime;}

    public boolean isCritical() {return isCritical;}

    public void setCritical(boolean crit) {isCritical=crit;}


    protected void modifyDebuffState()
    {
        if(debuffTime==0) debuff=Debuff.None;
        if(debuff==Debuff.None) return;

        if(debuff==Debuff.Poison)
        {
            curHP-=10;
            debuffTime--;
        }

        if(debuff==Debuff.Broken)
        {
            curDefense=defense-10;
            debuffTime--;
            if(debuffTime==0)
            {
                curDefense=defense;
            }
        }
        return;
    }

}

