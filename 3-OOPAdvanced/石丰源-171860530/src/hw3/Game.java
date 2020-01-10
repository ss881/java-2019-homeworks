package hw3;
import java.util.ArrayList;
import hw3.Creature.*;
import hw3.Creature.Monster.*;
import hw3.Dictionary.*;
import hw3.Formation.*;

public class Game {
    private Field field;
    private ArrayList<Calabash> calabashes;
    private Grandpa grandpa;
    private ArrayList<Heeler> heelers;
    private Snake snake;
    private Scorpion scorpion;

    public Game(){}
    public void init(){
        field = new Field();
        calabashes = new ArrayList<>();
        for(int i = 1; i <= 7; i++){
            calabashes.add(new Calabash(NameDictionary.getName(i), ColorDictionary.getColor(i), i));
        }
        grandpa = new Grandpa();
        heelers = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            heelers.add(new Heeler());
        }
        snake = new Snake();
        scorpion = new Scorpion();
    }
    public void play(){
        for(int i = 1; i <= 8; i++){
            try{
                changeFormation(i);
                show();
                grandpa.cheerUp();
                snake.cheerUp();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    private void show(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        field.show();
    }
    private void changeFormation(int order) throws ClassNotFoundException {
        field.clear();

        Formation f = new FormationChangshe();
        boolean map[][] = f.getMap();
        int index = 0;
        int N = field.getN();

        //安放爷爷
        field.getTiles()[10][3].setHolder(grandpa);
        //安放蝎子精
        field.getTiles()[1][5].setHolder(scorpion);
        //安放蛇精
        field.getTiles()[10][7].setHolder(snake);
        //安放葫芦娃
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j]){
                    field.getTiles()[i][j].setHolder(calabashes.get(index++));
                }
            }
        }

        //安放妖怪,之后再RTTI,加反射机制
        switch(order){
            case 1:{
                f = new FormationHeyi();
            }break;
            case 2:{
                f = new FormationYanxing();
            }break;
            case 3:{
                f = new FormationHenge();
            }break;
            case 4:{
                f = new FormationChangshe();
                map = f.getMap();
                index = 0;
                for(int i = 0; i < N; i++){
                    for(int j = 0; j < N; j++){
                        if(map[i][j]){
                            field.getTiles()[i][j + 5].setHolder(heelers.get(index++));
                        }
                    }
                }
                return;
            }
            case 5:{
                f = new FormationYulin();
            }break;
            case 6:{
                f = new FormationFangyuan();
            }break;
            case 7:{
                f = new FormationYanyue();
            }break;
            case 8:{
                f = new FormationFengshi();
            }break;
            default:break;
        }
        map = f.getMap();
        index = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j]){
                    field.getTiles()[i][j].setHolder(heelers.get(index++));
                }
            }
        }
    }
}
