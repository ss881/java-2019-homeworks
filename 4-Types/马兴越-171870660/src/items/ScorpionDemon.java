package items;
/*
 * 蝎子精，领队。负责指挥喽啰排队。
 */

import exceptions.NoSpaceForFormationException;
import field.*;
import formations.*;

public class ScorpionDemon extends Living implements Leader{
    private int followCount;
    private FollowDemon[] followDemons;

    public ScorpionDemon(Position pos, Field field_, int followCount_) {
        super(pos, field_);
        followCount = followCount_;
        followDemons=new FollowDemon[followCount];
        for(int i=0;i<followCount_;i++){
            Position position=field_.randomPosition();
            FollowDemon followDemon=new FollowDemon(position,field_,i+1);
            field.addLiving(followDemon);
            followDemons[i]=followDemon;
        }
    }

    @Override
    public String toString(){
        return "Sco";
    }


    @Override
    public <T extends Formation> void embattleFormation(Class<T> formType)
            throws NoSpaceForFormationException {
        FormationHandler<T> fh=new FormationHandler<T>(
                field,this,followDemons,formType);
        fh.embattle();
    }
}
