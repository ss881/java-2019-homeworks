package formation;

import creature.Creature;

import java.util.ArrayList;

public class Square extends Formation<Creature> {

    @Override
    public void changeForm(Creature leader, ArrayList<Creature> followers) {
        leader.moveTo(4,7);
        followers.get(0).moveTo(6,7);
        int count=1;
        for(int i=0;i<2;++i){
            followers.get(count).moveTo(4+2*i+1,6);
            followers.get(count+1).moveTo(4+2*i+1,8);
            count+=2;
        }
        followers.get(count).moveTo(6,5);
        followers.get(count+1).moveTo(6,9);
        followers.get(count+2).moveTo(8,7);
        count+=3;
        for(;count<followers.size();++count){
            //currently-not-used evial move disappear
            followers.get(count).moveTo(-1,-1);
        }
    }
}
