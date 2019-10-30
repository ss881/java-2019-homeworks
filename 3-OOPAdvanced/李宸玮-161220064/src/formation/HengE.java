package formation;

import creature.Creature;

public class HengE {
    protected String name;

    public void setName() {
        this.name = "衡轭阵";
    }
    public void setHengE(Creature crowd[]){
        for(int i=0;i<crowd.length;i++){
            if((i%2)==0){
            crowd[i].moveto(0,0+i); }
            else{
                crowd[i].moveto(1,0+i);
            }
                //根据需排阵的群体设置其位置，这里长蛇阵横坐标一样
        }
    }

}
