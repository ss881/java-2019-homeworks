package formation;

import chessboard.Cell;

public class FormationFengShi extends Formation{
    public FormationFengShi(int x,int y,int z) {
        //x,y表示箭头
        formation.add(new Cell(x,y,null));
        formation.add(new Cell(x,y-1*z,null));
        formation.add(new Cell(x,y-2*z,null));
        formation.add(new Cell(x,y-3*z,null));
        formation.add(new Cell(x-1,y-1*z,null));
        formation.add(new Cell(x+1,y-1*z,null));
        formation.add(new Cell(x-2,y-2*z,null));
        formation.add(new Cell(x+2,y-2*z,null));
    }
}
