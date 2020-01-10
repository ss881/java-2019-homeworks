package formation;

import chessboard.Cell;


public class FormationYanXing extends Formation {
    public FormationYanXing(int x,int y,int z) {
        formation.add(new Cell(x,y,null));
        formation.add(new Cell(x-1,y+1,null));
        formation.add(new Cell(x-2,y+2,null));
        formation.add(new Cell(x-3,y+3,null));
        formation.add(new Cell(x-1,y,null));
        formation.add(new Cell(x-2,y+1,null));
        formation.add(new Cell(x-3,y+2,null));
        formation.add(new Cell(x-4,y+3,null));
    }
}
