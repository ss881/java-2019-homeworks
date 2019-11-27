package hw4.Formation;

import hw4.Field;
import hw4.creature.Creature;
import hw4.creature.Grandpa;
import hw4.creature.monster.Snake;

import java.util.ArrayList;

public abstract class Formation {
    private static int N = 12;
    protected int numberOfMembers = 0;
    protected boolean[][] map = new boolean[N][N];
    protected ArrayList<? extends Creature> members;
    protected Creature leader;
    protected Creature cheeuper;
    protected Field field;

    abstract void initMap();
    public Formation(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = false;
            }
        }
        initMap();
    }
    public boolean[][] getMap(){
        return map;
    }
    public int getNumberOfMembers(){
        return numberOfMembers;
    }
    public void setTeam(ArrayList<? extends Creature> t){
        members = t;
    }
    public void setCheeuper(Creature c){
        cheeuper = c;
    }
    public void setField(Field field){
        this.field = field;
    }
    public void setLeader(Creature c){
        leader = c;
    }

    public void arrangePosition(){
        int index = 0;
        int offset = 0;
        if(cheeuper instanceof Snake){
            field.getTiles()[1][5].setHolder(leader);
            field.getTiles()[10][7].setHolder(cheeuper);
            if(this instanceof FormationChangshe){
                offset = 5;
            }
        }
        else if(cheeuper instanceof Grandpa){
            field.getTiles()[10][3].setHolder(cheeuper);
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j]){
                    field.getTiles()[i][j + offset].setHolder(members.get(index++));
                }
            }
        }
    }
}