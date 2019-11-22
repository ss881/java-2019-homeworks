package formation;

import creature.Creature;

import java.util.ArrayList;

public class Goose extends Formation<Creature> {
    @Override
    public void changeForm(Creature leader, ArrayList<Creature> followers) {
        leader.moveTo(6,7);
        followers.get(0).moveTo(4,7);
        int count=1;
        for(int i=1;i<=2;++i){
            followers.get(count).moveTo(6-i,7+i);
            followers.get(count+1).moveTo(6+i,7-i);
            count+=2;
        }
        for(;count<followers.size();++count){
            //currently-not-used evial move disappear
            followers.get(count).moveTo(-1,-1);
        }
    }
}
