import creature.*;
import factory.GrandPaFactory;
import factory.ScorpionFactory;
import formation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


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
        String name = creature.getClass().getSimpleName();
        if(name.equals("GrandPa")){
            symbol='G';
        }
        else if(name.equals("Scorpion")){
            symbol='X';
        }
        else if(name.equals("Snake")){
            symbol='S';
        }
        else if(name.equals("Evial")){
            symbol='E';
        }
        else if(name.equals("Huluwa")){
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


    void setCreature(Creature creature){
        boolean change=false;
        if(creature.inMapBefore()){
            //remove if in map
            {
                change=true;
                removeCreature(creature);
            }
        }
        if(creature.notInMap()==false)
        {
            change=true;
            placeCreature(creature);
        }
        if(change)
            draw();
    }

    void setCreatures(ArrayList<Creature> creatures){
        for(Creature creature:creatures){
            setCreature(creature);
        }
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

    public void changeFormRandomly(int n,Scorpion scorpion,ArrayList<Creature>evials){
        Random random  = new Random();
        Formation<Creature> formation = null;
        if(n < 0)
            n = random.nextInt(6);
        switch (n){
            case 0:{
                System.out.println("\n transform to 雁行:\n--------------------------------------------\n");
                formation = new Goose();
            }break;
            case 1:{
                System.out.println("\n transform to 锋矢:\n--------------------------------------------\n");
                formation = new Arrow();
            }break;
            case 2:{
                System.out.println("\n transform to 衡轭:\n--------------------------------------------\n");
                formation = new Car();
            }break;
            case 3:{
                System.out.println("\n transform to 鱼鳞:\n--------------------------------------------\n");
                formation = new FishScale();
            }break;
            case 4:{
                System.out.println("\n transform to 偃月:\n--------------------------------------------\n");
                formation = new Moon();
            }break;
            case 5:{
                System.out.println("\n transform to 方:\n--------------------------------------------\n");
                formation = new Square();
            }break;
            default: formation = new Arrow();
        }
        formation.changeForm(scorpion,evials);
    }

    public static void main(String[]args) throws InterruptedException, FileNotFoundException {

        GrandPaFactory grandPaFactory = new GrandPaFactory();
        GrandPa grandPa = grandPaFactory.generate();
        ArrayList<Creature> huluwas = grandPa.initialize();
        ScorpionFactory scorpionFactory = new ScorpionFactory();
        Scorpion scorpion = scorpionFactory.generate();
        ArrayList<Creature> evials = scorpion.initialize();

        BattleFiled battleFiled = new BattleFiled();
        battleFiled.setCreatures(huluwas);
        battleFiled.setCreature(grandPa);
        battleFiled.setCreature(scorpion);
        battleFiled.setCreatures(evials);

        Thread.sleep(500);
        battleFiled.changeFormRandomly(-1,scorpion,evials);
        battleFiled.setCreature(scorpion);
        battleFiled.setCreatures(evials);

    }
}
