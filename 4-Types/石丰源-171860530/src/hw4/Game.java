package hw4;

import hw4.creature.*;
import hw4.creature.monster.*;
import hw4.Dictionary.*;
import hw4.Formation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Game {
    private Field field;
    private ArrayList<Calabash> calabashes;
    private Grandpa grandpa;
    private ArrayList<Heeler> heelers;
    private Snake snake;
    private Scorpion scorpion;
    private Formation goodTeam;
    private Formation badTeam;

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
            String className = FormationDictionary.getName(i);
            try {
                //Class.forName是不是必须是完整的类名，包括包的结构
                Class<?> FormationClass = Class.forName("hw4.Formation." + className);
                Formation f = (Formation) FormationClass.getDeclaredConstructor().newInstance();

                goodTeam = new FormationChangshe();
                goodTeam.setField(field);
                goodTeam.setTeam(calabashes);
                goodTeam.setCheeuper(grandpa);
                goodTeam.setLeader(null);

                badTeam = f;
                badTeam.setField(field);
                badTeam.setTeam(heelers);
                badTeam.setCheeuper(snake);
                badTeam.setLeader(scorpion);

                changeFormation();
                show();
            }
            catch (ClassNotFoundException | NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    private void show(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        field.show();
        grandpa.cheerUp();
        snake.cheerUp();
    }
    private void changeFormation() throws ClassNotFoundException {
        field.clear();
        goodTeam.arrangePosition();
        badTeam.arrangePosition();
    }
}
