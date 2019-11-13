package formation;

import creature.Creature;

public class ChangShe {
    protected String name;

    public void setName() {
        this.name = "长蛇阵";
    }
    public void setChangshe(Creature crowd[]){
        for(int i=0;i<crowd.length;i++){
            crowd[i].moveto(0,0+i);//根据需排阵的群体设置其位置，这里长蛇阵横坐标一样
        }
    }
}
