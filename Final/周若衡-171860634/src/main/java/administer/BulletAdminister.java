package administer;

import creature.BadCreature;
import creature.Being;
import creature.Bullet;
import gui.ChessBoard;
import main.Main;

import java.util.ArrayList;

public class BulletAdminister implements Runnable {
    ArrayList<Bullet> bullets=new ArrayList<>();
    @Override
    public void run() {
        while(Main.chessBoard.isOver()==false){
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (bullets){
                for(int i=0;i<bullets.size();i++){
                    Bullet b=bullets.get(i);
                    b.move();
                    if(b.getD_x()< ChessBoard.START_X ||b.getD_x()>ChessBoard.START_X+ChessBoard.column*ChessBoard.CellXSize ||b.getD_y()<ChessBoard.START_Y||b.getD_y()>ChessBoard.START_Y+ChessBoard.row*ChessBoard.CellYSize){
                        boolean flag=false;
                        if(i!=bullets.size()-1){
                            flag=true;
                        }
                        bullets.remove(i);
                        if(flag==true){
                            i--;
                        }
                    }
                }
                for(int i=0;i<bullets.size();i++){
                    Bullet b=bullets.get(i);
                    synchronized (Main.chessBoard.getChessBoard()){
                        Being being=Main.chessBoard.getChessBoard().getBeing(b.getD_x(),b.getD_y());
                        if(being instanceof BadCreature){
                            synchronized (being){
                                if(((BadCreature) being).isAlive()==true){
                                    ((BadCreature) being).setHp(((BadCreature) being).getHp()-b.getAttack());
                                    if(((BadCreature) being).getHp()<=0){
                                        ((BadCreature) being).setHp(0);
                                        ((BadCreature) being).setAlive(false);
                                    }
                                    boolean flag=false;
                                    if(i!=bullets.size()-1){
                                        flag=true;
                                    }
                                    bullets.remove(i);
                                    if(flag==true){
                                        i--;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Main.chessBoard.paint();
        }
    }

    public void addBullet(Bullet b){
        synchronized (bullets){
            bullets.add(b);
        }
    }
    public ArrayList<Bullet> getBullets(){
        return bullets;
    }
    public void clear(){
        bullets.clear();
    }
}

