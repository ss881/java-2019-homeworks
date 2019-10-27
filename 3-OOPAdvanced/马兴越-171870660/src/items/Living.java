/*
 * 所有生物体的公共基类。
 */
package items;

import field.*;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public abstract class Living{
    protected Field field;
    protected final Position position;//此对象不可变
    protected boolean movable;

    public Living(Position pos,Field field_){
        position=pos.copy();
        field=field_;
        movable=true;
    }

    public Position getPosition(){
        return position;
    }

    /*
     * 排序和排阵列时，已经定位到指定位置的对象不可再移动。
     */
    public void setMovable(boolean m){
        movable=m;
    }

    public boolean isMovable(){
        return movable;
    }

    /*
     * 只允许在8邻域内移动。即abs(dx),abs(dy)<=1.
     * 返回移动是否成功。如果目标位置被占用，则移动失败。
     */
    public boolean move(int dx,int dy){
        assert abs(dx)<=1 && abs(dy)<=1;
        return field.moveLiving(this,dx,dy);
    }

    /**
     * 与another交换位置。要求二者必须是相邻的。
     * 相邻的检查交给Field完成。
     * 如果不相邻，返回false。
     */
    public boolean swapWith(Living another){
        assert another!=null;
        assert field.livingAt(position) == this;
        assert field.livingAt(another.position) == another;
        return field.swapLiving(this,another);
    }

    /**
     * 移动到pos所示位置，如果指定位置不相邻，或者该位置的对象不可移动，则返回false。
     */
    public boolean moveOrSwap(int dx,int dy){
        assert abs(dx)<=1 && abs(dy)<=1;
        Position target=new Position(position.getX()+dx,position.getY()+dy);
        if(field.livingAt(target)==null)
            return move(dx,dy);
        else if(field.livingAt(target).isMovable())
            return swapWith(field.livingAt(target));
        return false;
    }

    /**
     * 从当前位置取捷径走到position确定的位置。
     * 若有阻挡，对于movable的，直接和他交换；否则找一个方向绕开。
     * 如果找不到路径，返回false.
     */
    public boolean walkTowards(Position target){
        // 新建标记面板，用于记录走过的地方。
        // 在走过的地方添加一个虚拟的Living对象。
        Field passed=new Field();
        List<Living> called=new LinkedList<>();
        called.add(this);
//        boolean flag = pathTo(passed,target,called);
//        System.out.println("寻路历程"+toString()+"，结果"+flag);
//        passed.draw();
//        return flag;
        return pathTo(passed,target,called);
    }

    /**
     * 递归算法实现找路径过程。
     */
    private boolean pathTo(Field passed, Position target, List<Living> called){
        if(position.equals(target))//基本情况，已经移动到位
            return true;
        Position.Direction direction=position.new Direction(target);
        Position toMove;
        //首先将当前位置标记为走过
        Living flag=new PassedFlag(position,passed);
        passed.addLiving(flag);
        for(int i=0;i<8;i++){
            toMove=direction.adjacentPosition();
            if(!field.unreachable(toMove) && passed.livingAt(toMove)==null ){
                moveOrSwap(direction.dx(),direction.dy());
                if(pathTo(passed,target,called))
                    return true;
                else
                    moveOrSwap(-direction.dx(),-direction.dy());
            }
            else if (field.inside(toMove)){
                //不能直接过去，可以考虑交换
                Living an=field.livingAt(toMove);
                if(an!=null)
                    if(an.exchangeable() && an.getClass()==getClass()
                            && called.indexOf(an)<0){
                        called.add(an);
                        if(an.pathTo(passed,target,called)){
                            moveOrSwap(direction.dx(),direction.dy());
                            return true;
                        }
                    }
            }
            direction.next();
        }
        return false;
    }

    public boolean exchangeable(){
        return false;
    }
}
