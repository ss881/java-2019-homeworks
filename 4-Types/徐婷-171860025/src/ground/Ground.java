package ground;
import java.lang.reflect.Method;
import java.util.*;

import creatures.*;



public class Ground {
    static final int N = 13;
    private Cell[][]cells = new Cell[N][N];
    private HuluwaTeam huluwaTeam;
    private SnakeTeam snakeTeam;
    public Ground(HuluwaTeam huluwaTeam, SnakeTeam snakeTeam){
        this.huluwaTeam = huluwaTeam;
        this.snakeTeam = snakeTeam;
        for(int i = 0;i<N;i++){
            for(int j = 0; j < N; j++){
                cells[i][j]=new Cell();
            }
        }
    }
    private void setCellNull(){
        for(int i = 0;i<N;i++){
            for(int j = 0; j<N;j++){
                cells[i][j].setCreature(null);
            }
        }
    }
    public void update(){
        setCellNull();
        List<HuluwaSide> huluwaSide = huluwaTeam.getTeamMembers();
        List<SnakeSide> snakeSide = snakeTeam.getTeamMembers();
        for(HuluwaSide hulu: huluwaSide){
            if(hulu.getState()==State.LIVE) {
                int x = hulu.getCoordX();
                int y = hulu.getCoordY();
                cells[x][y].setCreature(hulu);
            }
        }
        for(SnakeSide snake: snakeSide){
            if(snake.getState()==State.LIVE) {
                int x = snake.getCoordX();
                int y = snake.getCoordY();
                cells[x][y].setCreature(snake);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public void printGround(){
        for(int i = 0;i<N;i++){
            for(int j = 0; j< N ;j++){
                System.out.print(cells[i][j].getName()+"   ");
            }
            System.out.println();
        }
        List<HuluwaSide> huluwaSides = huluwaTeam.getTeamMembers();
        List<SnakeSide> snakeSides = snakeTeam.getTeamMembers();
        List creatures = huluwaSides;
        creatures.addAll(snakeSides);
        for(Object c: creatures){
            if(c.getClass().getInterfaces().length!=0&&c.getClass().getInterfaces()[0].getSimpleName().equals("Cheerer")){
                try{
                    Class cobj = c.getClass();
                    Method cheer = cobj.getMethod("cheer");
                    cheer.invoke(c);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }

       /* for(HuluwaSide huluwa: huluwaSides){
            if(huluwa.getClass().getInterfaces().length!=0&&huluwa.getClass().getInterfaces()[0].getSimpleName().equals("Cheerer")){
                Class cobj = huluwa.getClass();
                try {
                    Method cheer = cobj.getMethod("cheer");
                    cheer.invoke(huluwa);
                }catch (Exception e){
                    System.out.println(e);
                }

            }
        }
        for(SnakeSide snake: snakeSides){
            if(snake.getClass().getInterfaces().length!=0&&snake.getClass().getInterfaces()[0].getSimpleName().equals("Cheerer")){
                Class cobj = snake.getClass();
                try {
                    Method cheer = cobj.getMethod("cheer");
                    cheer.invoke(snake);
                }catch (Exception e){
                    System.out.println(e);
                }

            }
        }*/
        System.out.println();
    }


}
