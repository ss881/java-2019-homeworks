/*
 * 基础阵型抽象类。
 * 由N个Living和1个leader组成，数量固定不变。
 * 阵型的具体形式由包含Position的数组给定。
 * form()抽象方法根据当前leader的位置，计算出所有follower的位置数组并返回。
 * 暂时不考虑效率，每次调用都重新计算一次。
 */
package formations;

import exceptions.NoSpaceForFormationException;
import exceptions.PathNotFoundException;
import field.Position;
import field.Field;
import items.Living;
import items.Leader;
import items.PassedFlag;

public abstract class Formation <T extends Living,Leader> {
    protected final int N; //blank final 从者的数目，方便使用
    protected T leader;
    protected Living[] followers;
    protected Field field;

    public Formation(Field field,T leader,Living[] followers){
        this.field=field;
        this.leader=leader;
        this.followers=followers;
        N=followers.length; //initialize blank final
    }

    /*
     * 公共接口。
     * 先寻找合适位置，再进行布阵。
     */
    public boolean embattle() throws NoSpaceForFormationException{
        findPlace();
        leader.setMovable(false);
        Position[] positions=form();
        for(int i=0;i<N;i++){
            try {
                followers[i].walkTowards(positions[i]);
            }
            catch (PathNotFoundException e){
                System.out.println(
                        "Cannot format as "+toString()+" due to "+e);
                NoSpaceForFormationException n=
                        new NoSpaceForFormationException(this);
                n.initCause(e);
                throw n;
            }
            followers[i].setMovable(false);
        }
        return true;
    }

    /*
     * 实现本抽象方法来给出所有的位置。
     */
    protected abstract Position[] form();

    /*
     * 当前leader的位置是否适合排布本阵型。
     * 检查所有要到的位置上是否有不可移动对象。
     */
    protected boolean ready(){
        Position[] positions=form();
        for(Position p:positions){
            if(field.unreachable(p))
                return false;
        }
        return true;
    }

    /**
     * 从scorpionDemon移植过来。
     * 寻找适合布阵的leader位置。
     * @return 是否成功
     */
    protected void findPlace() throws NoSpaceForFormationException {
        Field passed=new Field();
        for(Living l:followers){
            l.setMovable(true);
        }
        if(! findPlace(passed))
            throw new NoSpaceForFormationException(this);
    }

    private boolean findPlace(Field passed) {
        if(ready())
            return true;
        Position position=leader.getPosition();
        passed.addLiving(new PassedFlag(position.copy(),passed));
        Position.Direction dir=position.new Direction(Position.Direction.S);
        Position p;
        for(int i=0;i<8;i++){
            p=dir.adjacentPosition();
            if(field.inside(p) && passed.livingAt(p)==null &&
                    leader.moveOrSwap(dir.dx(),dir.dy()))
                if(findPlace(passed))
                    return true;
                else
                    //移动失败，先退回来
                    leader.moveOrSwap(-dir.dx(),-dir.dy());
            dir.next();
        }
        return false;
    }
}
