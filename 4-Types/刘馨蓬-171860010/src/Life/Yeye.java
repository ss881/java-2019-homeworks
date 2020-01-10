package Life;

import DotMatrix.*;
import Position.*;


public class Yeye extends Cammander{
    private Calabash[] minions;
    private DotMatrix dot;
    private Position standard;
    public Yeye(){
        name="爷爷";
        standard=new Position(4,0);
    }
    public void chooseDotMatrix(DotMatrixName matrixname, TwoDimensionsSpace map){
        dot = new DotMatrix(matrixname);
            minions=new Calabash[7];
            minions[0]=new Calabash(map,"老大");
            minions[1]=new Calabash(map,"老二");
            minions[2]=new Calabash(map,"老三");
            minions[3]=new Calabash(map,"老四");
            minions[4]=new Calabash(map,"老五");
            minions[5]=new Calabash(map,"老六");
            minions[6]=new Calabash(map,"老七");
    }
    public void changeLifePosition(TwoDimensionsSpace map){
            for (int i = 0; i < dot.DotLength(); i++) {
                Position temp = new Position(dot.lifePosition(i).X, dot.lifePosition(i).Y);
                temp.PositionAdd(standard);
                minions[i].move(temp,map);
            }
    }
    public void changeSelfPosition(TwoDimensionsSpace map){
        Position temp=dot.commanderPosition();
        temp.PositionAdd(standard);
        this.changePosition(temp,map);
    }
    public void clear(TwoDimensionsSpace map){
        for(int i=0;i<dot.DotLength();i++){
            minions[i].leaveThere(map);
        }
        this.leaveThere(map);
    }
}
