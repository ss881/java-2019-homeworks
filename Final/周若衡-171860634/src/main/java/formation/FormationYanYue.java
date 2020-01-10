package formation;

import chessboard.Cell;

public class FormationYanYue extends Formation{
    public FormationYanYue(int x,int y,int z){
        formation.add(new Cell(x,y,null));
        formation.add(new Cell(x-1,y,null));
        formation.add(new Cell(x+1,y,null));
        formation.add(new Cell(x,y+1*z,null));
        formation.add(new Cell(x-1,y+1*z,null));
        formation.add(new Cell(x-2,y+1*z,null));
        formation.add(new Cell(x+1,y+1*z,null));
        formation.add(new Cell(x+2,y+1*z,null));
    }
}
