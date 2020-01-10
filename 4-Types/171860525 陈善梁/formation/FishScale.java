package formation;

import creature.Creature;

import java.util.ArrayList;

public class FishScale extends Formation<Creature> {

    @Override
    public void changeForm(Creature leader, ArrayList<Creature> followers) {
        leader.moveTo(4,7);
        followers.get(0).moveTo(2,7);
        int count=2;
        followers.get(1).moveTo(5,8);
        for(int i=0;i<3;++i){
            followers.get(count).moveTo(6,5+2*i);
            ++count;
        }
        for(int i=0;i<4;++i){
            followers.get(count).moveTo(7,4+2*i);
            ++count;
        }
        followers.get(count).moveTo(8,7);
        ++count;
        for(;count<followers.size();++count){
            //currently-not-used evial move disappear
            followers.get(count).moveTo(-1,-1);
        }
    }
}
