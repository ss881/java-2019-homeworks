package formation;

import chessboard.Cell;

public class FormationChangShe extends Formation{
    public FormationChangShe(int x,int y,int z) {
        formation.add(new Cell(x,y,null));
        formation.add(new Cell(x,y+1,null));
        formation.add(new Cell(x,y+2,null));
        formation.add(new Cell(x,y+3,null));
        formation.add(new Cell(x,y+4,null));
        formation.add(new Cell(x,y+5,null));
        formation.add(new Cell(x,y+6,null));
        formation.add(new Cell(x,y+7,null));
    }
}
