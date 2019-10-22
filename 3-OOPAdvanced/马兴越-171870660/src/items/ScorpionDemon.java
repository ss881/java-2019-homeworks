package items;
/*
 * 蝎子精，领队。负责指挥喽啰排队。
 */

import exceptions.NoSpaceForFormationException;
import field.*;
import javafx.geometry.Pos;

public class ScorpionDemon extends Living {
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

<<<<<<< HEAD
    private boolean standAsFormation(Formation formation){
        try {
            formation.embattle();
        }
        catch (NoSpaceForFormationException e){
            System.out.println(e.toString());
        }
        return true;
=======
    /*
     * “鹤翼”阵型。
     */
    public void standAsSwing(){
        if(followCount%2!=0){
            System.out.println("Swing requires EVEN number follower!");
            return;
        }
        setMovable(false);
        for(Living f:followDemons) {
            f.setMovable(true);
        }
        if(!findPlaceForSwing()){
            System.out.println("没有空间排布鹤翼阵型！");
            return;
        }
        Position p=position.copy();
        Position.Direction dir=p.new Direction(Position.Direction.NW);
        for(int i=0;i<followCount/2;i++){
            dir.aStep();
            followDemons[i].walkTowards(p);
            followDemons[i].setMovable(false);
//            assert followDemons[i].getPosition().equals(p);
        }
        p=position.copy();
        dir=p.new Direction(Position.Direction.NE);
        for(int i=followCount/2;i<followCount;i++){
            dir.aStep();
            followDemons[i].walkTowards(p);
            followDemons[i].setMovable(false);
//            assert followDemons[i].getPosition().equals(p);
        }
>>>>>>> parent of e2cb683... 2019.10.06  重构formations（阵型）类。
    }

    /*
     * 以蝎子精为参考位置，考察周边空位是否满足布局条件。
     * 条件是所需要范围内，没有不可移动障碍物。（其实就是葫芦娃）
     */
    private boolean readyForSwing(){
        int depth=followCount/2;
        //left
        Position p=position.copy();
        Position.Direction d=p.new Direction(Position.Direction.NW);
        for(int i=0;i<depth;i++){
            d.aStep();
            if(!field.inside(p)||
                    field.livingAt(p)!=null&&!field.livingAt(p).isMovable())
                return false;
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.NE);
        for(int i=0;i<depth;i++){
            d.aStep();
            if(!field.inside(p)||
                    field.livingAt(p)!=null&&!field.livingAt(p).isMovable())
                return false;
        }
        return true;
    }

    /*
     * 寻找一个位置来满足布阵条件。
     */
    private boolean findPlaceForSwing(){
        Field passed=new Field();
//        boolean flag= findPlaceForSwing(passed);
//        passed.draw();
//        return flag;
        return findPlaceForSwing(passed);
    }

    /*
     * 递归算法
     */
    private boolean findPlaceForSwing(Field passed){
        if(readyForSwing())
            return true;
        passed.addLiving(new PassedFlag(position.copy(),passed));
        Position.Direction dir=position.new Direction(Position.Direction.S);
        Position p;
        for(int i=0;i<8;i++){
            p=dir.adjacentPosition();
            if(field.inside(p) && passed.livingAt(p)==null && moveOrSwap(dir.dx(),dir.dy()))
                if(findPlaceForSwing(passed))
                    return true;
                else
                    //移动失败，先退回来
                    moveOrSwap(-dir.dx(),-dir.dy());
            dir.next();
        }
        return false;
    }

    /*
     * “锋矢”阵型。
     * 将大致一半的followers放在主干线上，向下调整以保证两翼的数目是偶数。
     * 剩下的作为两翼。
     */
    public void standAsArrow(){
        if(followCount<3){
            System.out.println("Arrow requires at least 3 followers!");
            return;
        }
        for (Living l:followDemons)
            l.setMovable(true);
        if(!findPlaceForArrow()){
            System.out.println("没有空间排布锋矢阵型！");
            return;
        }
        int swingCount=followCount/2;
        if(swingCount%2==1)swingCount++;
        int mainCount=followCount-swingCount;
        Position p=position.copy();
        Position.Direction d=p.new Direction(Position.Direction.S);
        for(int i=0;i<mainCount;i++){
            d.aStep();
            followDemons[i].walkTowards(p);
            followDemons[i].setMovable(false);
//            assert followDemons[i].getPosition().equals(p);
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.SW);
        for(int i=mainCount;i<mainCount+swingCount/2;i++){
            d.aStep();
            followDemons[i].walkTowards(p);
            followDemons[i].setMovable(false);
//            assert followDemons[i].getPosition().equals(p);
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.SE);
        for(int i=mainCount+swingCount/2;i<mainCount+swingCount;i++){
            d.aStep();
            followDemons[i].walkTowards(p);
            followDemons[i].setMovable(false);
//            assert followDemons[i].getPosition().equals(p);
        }

    }

    private boolean readyForArrow(){
        int swingCount=followCount/2;
        if(swingCount%2==1)swingCount++;
        int mainCount=followCount-swingCount;
        Position p=position.copy();
        Position.Direction d=p.new Direction(Position.Direction.S);
        for(int i=0;i<mainCount;i++){
            d.aStep();
            if(field.Unreachable(p))
                return false;
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.SW);
        for(int i=0;i<swingCount/2;i++){
            d.aStep();
            if(field.Unreachable(p))
                return false;
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.SE);
        for(int i=0;i<swingCount/2;i++){
            d.aStep();
            if(field.Unreachable(p))
                return false;
        }
        return true;
    }

    private boolean findPlaceForArrow(){
        Field passed=new Field();
        return findPlaceForArrow(passed);
    }

    private boolean findPlaceForArrow(Field passed){
        if(readyForArrow())
            return true;
        passed.addLiving(new PassedFlag(position.copy(),passed));
        Position.Direction dir=position.new Direction(Position.Direction.N);
        Position p;
        for(int i=0;i<8;i++){
            p=dir.adjacentPosition();
            if(field.inside(p) && passed.livingAt(p)==null && moveOrSwap(dir.dx(),dir.dy()))
                if (findPlaceForArrow(passed))
                    return true;
                else
                    //移动失败，先退回来
                    moveOrSwap(-dir.dx(),-dir.dy());
            dir.next();
        }
        return false;
    }


    @Override
    public String toString(){
        return "Sco";
    }
}
