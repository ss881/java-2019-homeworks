package formation;

import creature.Creature;

import java.util.ArrayList;

public class Car extends Formation<Creature> {

    @Override
    public void changeForm(Creature leader, ArrayList<Creature> followers) {
        leader.moveTo(3,7);
        followers.get(0).moveTo(6,5);
        int count=1;
        for(int i=0;i<3;++i){
            followers.get(count).moveTo(3+2*i+1,6);
            ++count;
        }
        for(int i=0;i<2;++i){
            followers.get(count).moveTo(3+2*i+2,7);
            ++count;
        }
        for(;count<followers.size();++count){
            //currently-not-used evial move disappear
            followers.get(count).moveTo(-1,-1);
        }
    }
}
