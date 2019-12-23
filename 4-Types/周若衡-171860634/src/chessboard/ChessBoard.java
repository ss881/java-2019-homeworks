package chessboard;

import creature.Creature;

public class ChessBoard {
    private static final int SIZE=10;
    private Cell[][] chessBoard;
    {
        chessBoard=new Cell[SIZE][SIZE];
    }
    public <T extends Creature> void placeCreature(T creature,final int xPos,int yPos){
        creature.stay(xPos,yPos);
        chessBoard[xPos][yPos]=new Cell<>(creature);
    }
    public void formationChange(){
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if(chessBoard[i][j]!=null){
                    int x=chessBoard[i][j].getCreature().getX();
                    int y=chessBoard[i][j].getCreature().getY();
                    if(x!=i || y!=j){
                        chessBoard[x][y]=new Cell<>(chessBoard[i][j].getCreature());
                        chessBoard[i][j]=null;
                    }
                }
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder output=new StringBuilder();
        for(Cell[] row : chessBoard){
            for(Cell cell: row){
                if(cell==null){
                    output.append(String.format("%-8s","一一"));
                }else{
                    output.append(String.format("%-8s",cell.getCreature().toString()));
                }
            }
            output.append("\n");
        }
        return output.toString();
    }
}
