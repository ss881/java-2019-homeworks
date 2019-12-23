package nju.sfy.model.formation;

import nju.sfy.model.Field;
import nju.sfy.model.creature.Creature;
import nju.sfy.model.creature.Grandpa;
import nju.sfy.model.creature.monster.Snake;

import java.util.ArrayList;

public abstract class Formation {
    private static int N = 10;
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
            field.getTiles()[5][1].setHolder(leader);
            field.getTiles()[7][9].setHolder(cheeuper);

            leader.setField(field);
            leader.setTile(field.getTiles()[5][1]);

            cheeuper.setField(field);
            cheeuper.setTile(field.getTiles()[7][9]);

            if(this instanceof FormationChangshe){
                offset = 5;
            }
        }
        else if(cheeuper instanceof Grandpa){
            cheeuper.setField(field);
            cheeuper.setTile(field.getTiles()[3][9]);
            field.getTiles()[3][9].setHolder(cheeuper);
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j]){
                    members.get(index).setField(field);
                    members.get(index).setTile(field.getTiles()[i][j + offset]);
                    field.getTiles()[i][j + offset].setHolder(members.get(index++));
                }
            }
        }
    }
}
