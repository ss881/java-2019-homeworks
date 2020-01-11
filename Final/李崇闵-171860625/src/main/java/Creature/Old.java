package Creature;

import Field.Field;

//老爷爷类
public class Old extends Good {
    public void initPos(int x,int y,Field field){
        this.field=field;
        this.x=x;
        this.y=y;
        this.field.addCreature(this,this.x,this.y);
    }
}
