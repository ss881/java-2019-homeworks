package formation;

import creature.Creature;

import java.util.ArrayList;

public class Moon extends Formation<Creature> {

    @Override
    public void changeForm(Creature leader, ArrayList<Creature> followers) {
        leader.moveTo(5,5);
        followers.get(0).moveTo(6,4);
        int count=1;
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(i==0&&j==0){
                    continue;
                }
                else{
                    followers.get(count).moveTo(5+i,5+j);
                    ++count;
                }
            }
        }
        for(int i=0;i<2;++i){
            followers.get(count).moveTo(5-i-1,6+i+1);
            followers.get(count+1).moveTo(7+i+1,6+i+1);
            count+=2;
        }
        for(int i=0;i<3;++i){
            followers.get(count).moveTo(5-i-1,7+i+1);
            followers.get(count+1).moveTo(7+i+1,7+i+1);
            count+=2;
        }
        for(;count<followers.size();++count){
            //currently-not-used evial move disappear
            followers.get(count).moveTo(-1,-1);
        }
    }
}
