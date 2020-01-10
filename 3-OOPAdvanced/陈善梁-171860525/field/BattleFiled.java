package field;

import creatures.*;

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

    public  void setHuluwas(Huluwa[]huluwas,GrandPa grandPa){
        for(Huluwa huluwa:huluwas){
            //for huluwa,must be only one creature on one tile
            Position curPos=huluwa.getCurrentPosition();
            map[curPos.x][curPos.y].ch1=huluwa.getSymbol();
            map[curPos.x][curPos.y].count=1;
            map[curPos.x][curPos.y].changed=true;
            draw();
        }
        Position currentPosition=grandPa.getCurrentPosition();
        map[currentPosition.x][currentPosition.y].ch1='G';
        map[currentPosition.x][currentPosition.y].changed=true;
        draw();
    }

    public void removeEvial(Evial evial){
        Position previousPosition=evial.getPreviousPosition();
        if(map[previousPosition.x][previousPosition.y].count==1){
            map[previousPosition.x][previousPosition.y].ch1=' ';
            map[previousPosition.x][previousPosition.y].count--;
        }
        else if(map[previousPosition.x][previousPosition.y].count==2){//count==2
            map[previousPosition.x][previousPosition.y].ch1=
                    map[previousPosition.x][previousPosition.y].ch2;
            map[previousPosition.x][previousPosition.y].count--;
        }
        else{
            throw new RuntimeException("error! cannot remove nothng!");
        }
//        draw();
    }

    public void placeEvial(Evial evial){
        Position currentPosition=evial.getCurrentPosition();
        if(map[currentPosition.x][currentPosition.y].count==0){
            map[currentPosition.x][currentPosition.y].ch1=evial.getSymbol();
        }
        else if(map[currentPosition.x][currentPosition.y].count==1){
            //in fact ,no need "if"
            map[currentPosition.x][currentPosition.y].ch2=evial.getSymbol();
        }
        if(map[currentPosition.x][currentPosition.y].count==2){
            throw new RuntimeException("error!connot put more than 2 on one tile!");
        }
        else{//must
            map[currentPosition.x][currentPosition.y].changed=true;
        }
        map[currentPosition.x][currentPosition.y].count++;
//        draw();
    }

    public void setEvils(Scorpion scorpion,Snake snake,Evial[]evials){
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

    public static void main(String[]args) throws InterruptedException {

        GrandPa grandPa=new GrandPa();
        Huluwa[]huluwas=grandPa.initialize();
        grandPa.sort(huluwas);

        Scorpion scorpion = new Scorpion();
        Snake snake=new Snake();
        Evial []evials=scorpion.initialize(snake);
        snake.setCurrentPosition(new Position (4,7));



        BattleFiled battleFiled=new BattleFiled();
        battleFiled.setHuluwas(huluwas,grandPa);
        battleFiled.setEvils(scorpion,snake,evials);
        Thread.sleep(500);
        scorpion.changeRandomly(evials,snake);
        battleFiled.setEvils(scorpion,snake,evials);

    }
}
