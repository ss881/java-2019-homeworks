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

    public void standAsFormation(Formation formation){
        try {
            formation.embattle();
        }
        catch (NoSpaceForFormationException e){
            System.out.println(e.toString());
        }
    }

    /*
     * “鹤翼”阵型。
     */
    public void standAsSwing(){
        Formation formation=new SwingFormation(field,this,followDemons);
        standAsFormation(formation);
    }

    /*
     * “锋矢”阵型。
     * 将大致一半的followers放在主干线上，向下调整以保证两翼的数目是偶数。
     * 剩下的作为两翼。
     */
    public void standAsArrow(){
        ArrowFormation formation=new ArrowFormation(field,this,followDemons);
        standAsFormation(formation);
    }

    @Override
    public String toString(){
        return "Sco";
    }
}
