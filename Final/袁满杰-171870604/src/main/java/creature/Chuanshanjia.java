package creature;

import space.Space;

public class Chuanshanjia extends Creature{
        int id;
        Chuanshanjia(Space s, int id,float[] init,int team_id){
            super(s,init,team_id);
            this.id=id;
            this.name="甲"+id;
        }
        public int getid(){return id;}

}
