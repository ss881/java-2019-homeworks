package Life;
import DotMatrix.*;
import Position.*;
public class Xiezi extends Cammander{
    private Life[] minions;
    private DotMatrix dot;
    private Position standard;
    public Xiezi(){
        name="大蝎子";
        standard=new Position(4,5);
    }
    public void chooseDotMatrix(DotMatrixName matrixname,TwoDimensionsSpace map){
        dot = new DotMatrix(matrixname);
            minions = new Life[dot.DotLength()];
            for(int i=0;i<dot.DotLength();i++){
                minions[i]=new Life();
            }
    }
    public void changeLifePosition(TwoDimensionsSpace map){
            for (int i = 0; i < dot.DotLength(); i++) {
                Position temp = new Position(dot.lifePosition(i).X, dot.lifePosition(i).Y);
                temp.PositionAdd(standard);
                minions[i].changePosition(temp,map);
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
