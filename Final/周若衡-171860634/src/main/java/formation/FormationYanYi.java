package formation;

import chessboard.Cell;

public class FormationYanYi extends Formation{
    public FormationYanYi(int x,int y,int z) {
        //x,y端点
        formation.add(new Cell(x,y,null));
        formation.add(new Cell(x-1,y+1*z,null));
        formation.add(new Cell(x+1,y+1*z,null));
        formation.add(new Cell(x,y+2*z,null));
        formation.add(new Cell(x-1,y+2*z,null));
        formation.add(new Cell(x-2,y+2*z,null));
        formation.add(new Cell(x+1,y+2*z,null));
        formation.add(new Cell(x+2,y+2*z,null));
    }
}
