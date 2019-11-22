package formation;

import creature.Creature;

import java.util.ArrayList;

//对于蛇精，也是作为普通的妖怪再follower中的
public class Crane extends  Formation<Creature> {

    @Override
    public void changeForm(Creature leader, ArrayList<Creature> followers) {
        leader.moveTo(7,7);
        followers.get(0).moveTo(5,7);
        int count=1;
        for(int i=1;i<=3;++i){
            followers.get(count).moveTo(7-i,7-i);
            followers.get(count+1).moveTo(7-i,7+i);
            count+=2;
        }
        for(;count<followers.size();++count){
            //currently-not-used evial move disappear
            followers.get(count).moveTo(-1,-1);
        }
    }
}
