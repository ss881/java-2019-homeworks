import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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
        GrandPa grandPa=goodTeam.getGrandPa();
        for(Huluwa huluwa:goodTeam.getHuluwas()){
            //for huluwa,must be only one creature on one tile
            Position curPos=huluwa.currentPosition;
            map[curPos.x][curPos.y].ch1=huluwa.getSymbol();
            map[curPos.x][curPos.y].count=1;
            map[curPos.x][curPos.y].changed=true;
            draw();
        }
        map[grandPa.currentPosition.x][grandPa.currentPosition.y].ch1='G';
        map[grandPa.currentPosition.x][grandPa.currentPosition.y].changed=true;
        draw();
    }

    public void removeEvial(Evial evial){
        if(map[evial.previousPosition.x][evial.previousPosition.y].count==1){
            map[evial.previousPosition.x][evial.previousPosition.y].ch1=' ';
            map[evial.previousPosition.x][evial.previousPosition.y].count--;
        }
        else if(map[evial.previousPosition.x][evial.previousPosition.y].count==2){//count==2
            map[evial.previousPosition.x][evial.previousPosition.y].ch1=
                    map[evial.previousPosition.x][evial.previousPosition.y].ch2;
            map[evial.previousPosition.x][evial.previousPosition.y].count--;
        }
        else{
            throw new RuntimeException("error! cannot remove nothng!");
        }
//        draw();
    }

    public void placeEvial(Evial evial){
        if(map[evial.currentPosition.x][evial.currentPosition.y].count==0){
            map[evial.currentPosition.x][evial.currentPosition.y].ch1=evial.getSymbol();
        }
        else if(map[evial.currentPosition.x][evial.currentPosition.y].count==1){
            //in fact ,no need "if"
            map[evial.currentPosition.x][evial.currentPosition.y].ch2=evial.getSymbol();
        }
        if(map[evial.currentPosition.x][evial.currentPosition.y].count==2){
            throw new RuntimeException("error!connot put more than 2 on one tile!");
        }
        else{//must
            map[evial.currentPosition.x][evial.currentPosition.y].changed=true;
        }
        map[evial.currentPosition.x][evial.currentPosition.y].count++;
//        draw();
    }

    public void setBadTeam(BadTeam badTeam){
        Scorpion scorpion=badTeam.getScorpion();
        Evial[]evials=badTeam.getEvials();
        Snake snake=badTeam.getSnake();
        //remove previous positions
        for(Evial evial:evials){
            boolean change=false;
            if(evial.inMapBefore()){
                //remove if in map
                {
                    change=true;
                    removeEvial(evial);
                }
            }
            if(evial.notInMap()==false)
            {
                change=true;
                placeEvial(evial);
            }
            if(change)
                draw();
        }

        if(scorpion.inMapBefore()){
            //remove if in map
            removeEvial(scorpion);
        }

        placeEvial(scorpion);
        draw();

        if(snake.inMapBefore()){
            //remove if in map
           removeEvial(snake);
        }
        placeEvial(snake);
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
        goodTeam.sortHuluwa();

        BadTeam badTeam=new BadTeam();




        BattleFiled battleFiled=new BattleFiled();
        battleFiled.setGoodTeam(goodTeam);
        battleFiled.setBadTeam(badTeam);

        Thread.sleep(500);
        badTeam.changeRandomly();
        battleFiled.setBadTeam(badTeam);


    }
}
