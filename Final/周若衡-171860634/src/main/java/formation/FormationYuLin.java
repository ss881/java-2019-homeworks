package formation;

import chessboard.Cell;

public class FormationYuLin extends Formation{
    public FormationYuLin(int x,int y,int z){
        formation.add(new Cell(x,y,null));
        formation.add(new Cell(x,y-1*z,null));
        formation.add(new Cell(x,y-2*z,null));
        formation.add(new Cell(x,y-3*z,null));
        formation.add(new Cell(x-1,y-1*z,null));
        formation.add(new Cell(x-1,y-2*z,null));
        formation.add(new Cell(x-2,y-2*z,null));
        formation.add(new Cell(x+1,y-2*z,null));
    }
}
