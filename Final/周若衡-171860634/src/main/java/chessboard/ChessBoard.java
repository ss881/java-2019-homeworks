package chessboard;

import creature.BadCreature;
import creature.Being;
import creature.Creature;
import creature.GoodCreature;
import util.DirectionVector;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ChessBoard {
    public Lock lock = new ReentrantLock();

    public static ArrayList<DirectionVector> directions;
    static{
        directions=new ArrayList<>();
        directions.add(new DirectionVector(-1,0));  //上
        directions.add(new DirectionVector(1,0));   //下
        directions.add(new DirectionVector(0,-1));  //左
        directions.add(new DirectionVector(0,1));   //右
    }

    private Cell[][] board;
    private int row;
    private int column;
    public int getRow(){return row;}
    public int getColumn(){return column;}

    public boolean isOutOfBound(int x,int y){
        if(x<0||x>=row)
            return true;
        if(y<0||y>=column)
            return true;
        return false;
    }
    public ChessBoard(int x, int y) {
        row = x;
        column = y;
        board = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = new Cell(i, j, null);
            }
        }
    }
    public boolean isEmpty(int x, int y){
        try{
            if(board[x][y].getBeing()==null){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void setSquare(Cell s){
        board[s.getX()][s.getY()]=s;
    }
    public Cell getSquare(int x, int y){
        try{
            return board[x][y];
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean canPutCreature(int x,int y){
        if(!(x>=0&&x<row && y>=0&&y<column)){
            return false;
        }
        if(board[x][y].getBeing()==null)
            return true;
        else
            return false;
    }

    public Creature getEnemy(Creature cur){
        try{
            if(cur.isAlive()==false)
                return null;
            int x=cur.getX();
            int y=cur.getY();
            for(int i=x-1;i<=x+1;i++){
                for(int j=y-1;j<=y+1;j++){
                    if(i==x&&j==y)
                        continue;
                    if(i>=0&&i<row&&j>=0&&j<column){
                        Cell temp= getSquare(i,j);
                        if(temp.getBeing()!=null && temp.getBeing() instanceof Creature){
                            Creature enemy=(Creature) temp.getBeing();
                            if(enemy.isAlive()==true){
                                if((cur instanceof GoodCreature && enemy instanceof BadCreature)||(cur instanceof BadCreature && enemy instanceof GoodCreature)){
                                    return enemy;
                                }
                            }
                            else{
                                if((cur instanceof GoodCreature && enemy instanceof BadCreature)||(cur instanceof BadCreature && enemy instanceof GoodCreature)){
                                    return enemy;
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void setCreature(Creature c, int x, int y){
        if(!(x>=0&&x<row&&y>=0&&y<column))
            return;
        if(board[x][y].getBeing()!=null)
            return;
        board[x][y].setBeing(c);
        c.setX(x);
        c.setY(y);
    }

    public void clear(){
        for(int i=0;i<row;i++)
            for(int j=0;j<column;j++){
                board[i][j].setBeing(null);
            }
    }

    public Creature searchCreature(Creature temp){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(board[i][j].getBeing()!=null && board[i][j].getBeing() instanceof  Creature){
                    Creature cur=(Creature)(board[i][j].getBeing());
                    if(cur.getId()==temp.getId()){
                        return cur;
                    }
                }
            }
        }
        return null;
    }

    public Being getBeing(double d_x, double d_y){
        int tx=((int)d_y- gui.ChessBoard.START_Y)/ gui.ChessBoard.CellYSize;
        int ty=((int)d_x- gui.ChessBoard.START_X)/ gui.ChessBoard.CellXSize;
        if(tx<0||tx>=row||ty<0||ty>=column)
            return null;
        return board[tx][ty].getBeing();
    }
}

