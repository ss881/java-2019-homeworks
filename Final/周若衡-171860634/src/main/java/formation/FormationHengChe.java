package formation;

import chessboard.Cell;

public class FormationHengChe extends Formation{
    public FormationHengChe(int x,int y,int z) {
        //x,y中间节点
        formation.add(new Cell(x,y,null));
        formation.add(new Cell(x-2,y,null));
        formation.add(new Cell(x+2,y,null));
        formation.add(new Cell(x,y-1*z,null));
        formation.add(new Cell(x-1,y-1*z,null));
        formation.add(new Cell(x-2,y-1*z,null));
        formation.add(new Cell(x+1,y-1*z,null));
        formation.add(new Cell(x+2,y-1*z,null));
    }
}
