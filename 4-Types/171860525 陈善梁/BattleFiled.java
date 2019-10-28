import creature.*;
import team.*;

import java.io.FileNotFoundException;


class Tile{
    char ch1;
    char ch2;//hidden creature
    int count;//number of creature on this tile
    boolean changed;//to show change on one tile
    Tile(){
        ch1=' ';
        ch2=' ';
        count=0;
        changed=false;
    }
}

public class BattleFiled {
    static final int N=12;
    Tile [][]map;

    public BattleFiled(){
        map=new Tile[N][N];
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                map[i][j]=new Tile();
            }
        }
    }
    public void setGoodTeam(GoodTeam goodTeam){
        GrandPa grandPa=goodTeam.getLeader();
        for(Huluwa huluwa:goodTeam.getMembers()){
            //for huluwa,must be only one creature on one tile
            Position curPos=huluwa.currentPosition;
            map[curPos.x][curPos.y].ch1='H';
            map[curPos.x][curPos.y].count=1;
            map[curPos.x][curPos.y].changed=true;
            draw();
        }
        map[grandPa.currentPosition.x][grandPa.currentPosition.y].ch1='G';
        map[grandPa.currentPosition.x][grandPa.currentPosition.y].changed=true;
        draw();
    }

    public void removeCreature(Creature creature){
        if(map[creature.previousPosition.x][creature.previousPosition.y].count==1){
            map[creature.previousPosition.x][creature.previousPosition.y].ch1=' ';
            map[creature.previousPosition.x][creature.previousPosition.y].count--;
        }
        else if(map[creature.previousPosition.x][creature.previousPosition.y].count==2){//count==2
            map[creature.previousPosition.x][creature.previousPosition.y].ch1=
                    map[creature.previousPosition.x][creature.previousPosition.y].ch2;
            map[creature.previousPosition.x][creature.previousPosition.y].count--;
        }
        else{
            throw new RuntimeException("error! cannot remove nothng!");
        }
//        draw();
    }

    public void placeCreature(Creature creature) {
        //REFLECTION
        char symbol='C';
        if(creature instanceof GrandPa){
            symbol='G';
        }
        else if(creature instanceof Scorpion){
            symbol='X';
        }
        else if(creature instanceof Snake){
            symbol='S';
        }
        else if(creature instanceof Evial){
            symbol='E';
        }
        else if(creature instanceof Huluwa){
            symbol='H';
        }
        else{
            symbol='C';
        }
        if(map[creature.currentPosition.x][creature.currentPosition.y].count==0){
            map[creature.currentPosition.x][creature.currentPosition.y].ch1=symbol;
        }
        else if(map[creature.currentPosition.x][creature.currentPosition.y].count==1){
            //in fact ,no need "if"
            map[creature.currentPosition.x][creature.currentPosition.y].ch2=symbol;
        }
        if(map[creature.currentPosition.x][creature.currentPosition.y].count==2){
            throw new RuntimeException("error!connot put more than 2 on one tile!");
        }
        else{//must
            map[creature.currentPosition.x][creature.currentPosition.y].changed=true;
        }
        map[creature.currentPosition.x][creature.currentPosition.y].count++;
//        draw();
    }

    public void setBadTeam(BadTeam badTeam){
        Scorpion scorpion=badTeam.getLeader();
        Evial[]evials=badTeam.getMembers();
        Snake snake=badTeam.getSnake();
        //remove previous po qsitions
        for(Evial evial:evials){
            boolean change=false;
            if(evial.inMapBefore()){
                //remove if in map
                {
                    change=true;
                    removeCreature(evial);
                }
            }
            if(evial.notInMap()==false)
            {
                change=true;
                placeCreature(evial);
            }
            if(change)
                draw();
        }

        if(scorpion.inMapBefore()){
            //remove if in map
            removeCreature(scorpion);
        }

        placeCreature(scorpion);
        draw();

        if(snake.inMapBefore()){
            //remove if in map
           removeCreature(snake);
        }
        placeCreature(snake);
        draw();
    }

    public void draw(){
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                if(map[i][j].changed==true){
                    System.out.print("\033[32;4m" + map[i][j].ch1 + "\033[0m");
                    map[i][j].changed=false;
                }
                else {
                    System.out.print(map[i][j].ch1);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[]args) throws InterruptedException, FileNotFoundException {

        GoodTeam goodTeam=new GoodTeam();
        goodTeam.changeForm();
        BadTeam badTeam=new BadTeam();

        BattleFiled battleFiled=new BattleFiled();
        battleFiled.setGoodTeam(goodTeam);
        battleFiled.setBadTeam(badTeam);

        Thread.sleep(500);
        badTeam.changeForm();
        battleFiled.setBadTeam(badTeam);


    }
}
