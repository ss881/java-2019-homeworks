package Creature;

import Field.Field;

//葫芦娃类
public class Gourd  extends Good {
    public void initPos(int x,int y,Field field){
        this.field=field;
        this.x=x;
        this.y=y;
        this.field.addCreature(this,this.x,this.y);
    }
    //长蛇阵
    public void LookForward(Gourd gourd, int order){
        this.field=gourd.field;
        this.x=gourd.x;
        this.y=gourd.y+order;
        this.field.addCreature(this,this.x,this.y);
    }
}

