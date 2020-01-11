package formation;


import chessboard.Cell;
import creature.Creature;
import chessboard.ChessBoard;

import java.util.ArrayList;

public abstract class Formation {

    protected ArrayList<Cell> formation=new ArrayList<>();
    public Formation() {
    }

    private ArrayList<Cell> getSquare(){
        return formation;
    }

    public void pubFormationOnBoard(ChessBoard board, ArrayList<? extends Creature> creatures){
        assert(formation.size()<=creatures.size());
        int i=0;
        for(Cell s:formation){
            if(board.isEmpty(s.getX(),s.getY())){
                s.setBeing(creatures.get(i));
                creatures.get(i).setX(s.getX());
                creatures.get(i).setY(s.getY());
                i++;
                board.setSquare(s);
            }
        }
    }

    public boolean checkWhetherCrash(Formation a){

        for(Cell temp1:a.getSquare()){
            for(Cell temp2:getSquare()){
                if(temp1.getX()==temp2.getX()&&temp1.getY()==temp2.getY()){
                    return true;
                }
            }
        }
        return false;
    }
}
