package formation;

import creature.Creature;

public class FengShi {

    protected String name;

    public void setName() {
        this.name = "锋矢阵";
    }
    public void setFengShi(Creature crowd[]){
        int i;
        int heYiCount=(crowd.length+1)/2;
        for( i=0;i<(heYiCount+1)/2;i++){
            crowd[i].moveto(0+i,(heYiCount+1)/2-1-i);
        }
        for(;i<heYiCount;i++){
            crowd[i].moveto(0+i,i-(heYiCount+1)/2+1);
        }
        for(int j=0;i<crowd.length;i++,j++){
            crowd[i].moveto((heYiCount+1)/2-1,1+j);
        }
    }
}
