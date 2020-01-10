package chessboard;

import chessman.creature.Creature;

import java.util.ArrayList;

public class Formation {
    //genericity used
    //this class is used to ini creature position

    public Position[] list= new Position[8];
    public Formation(String type){
        initList(type);
    }
    private void initList(String type){
        if (type == "Fangmen"){
            list[0] = new Position(5,4);
            list[1] = new Position(4,3);
            list[2] = new Position(6,3);
            list[3] = new Position(3,2);
            list[4] = new Position(7,2);
            list[5] = new Position(4,1);
            list[6] = new Position(6,1);
            list[7] = new Position(5,0);
        }
        else if (type == "Heyi"){
            list[0] = new Position(5,4);
            list[1] = new Position(4,3);
            list[2] = new Position(6,3);
            list[3] = new Position(3,2);
            list[4] = new Position(7,2);
            list[5] = new Position(2,1);
            list[6] = new Position(8,1);
            list[7] = new Position(5,1);
        }
        else if(type == "Henge"){
            list[0] = new Position(2,2);
            list[1] = new Position(3,1);
            list[2] = new Position(4,2);
            list[3] = new Position(5,1);
            list[4] = new Position(6,2);
            list[5] = new Position(7,1);
            list[6] = new Position(8,2);
            list[7] = new Position(5,0);
        }
        else{
           //chagnshe
            for(int i = 0 ; i <8 ;i++){
                list[i] = new Position(i+2,0);
            }
        }
    }
    public void setBattlePlace(BattleField field,ArrayList<? extends Creature>hululist,ArrayList<? extends Creature>monsterlist){
        //put the evil and justice onto battleField
        int i = 0;
        for(Creature creature:monsterlist){
            creature.setPosition(i+1,11);
            field.setCreatrue(creature,1+i,11);
            i++;
        }
        i = 0 ;

        for(Creature creature:hululist){
            creature.setPosition(list[i].getX()-1,list[i].getY()+2);
            field.setCreatrue(creature,list[i].getX()-1,list[i].getY()+2);
            i++;
        }

    }
    public Position[] getPosition(){
        return list;
    }
}