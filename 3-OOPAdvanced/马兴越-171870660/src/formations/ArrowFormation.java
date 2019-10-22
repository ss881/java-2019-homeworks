package formations;

import field.*;
import items.Living;
public class ArrowFormation extends Formation {

    public ArrowFormation(Field field, Living leader, Living[] followers) {
        super(field, leader, followers);
    }

    public Position[] form(){
        int swingCount=N/2,t=0;
        Position position=leader.getPosition();
        Position[] positions=new Position[N];
        if(swingCount%2==1)
            swingCount++;
        int mainCount=N-swingCount;
        Position p=position.copy();
        Position.Direction d=p.new Direction(Position.Direction.S);
        for(int i=0;i<mainCount;i++){
            positions[t++]=d.adjacentPosition();
            d.aStep();
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.SW);
        for(int i=0;i<swingCount/2;i++){
            positions[t++]=d.adjacentPosition();
            d.aStep();
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.SE);
        for(int i=0;i<swingCount/2;i++){
            positions[t++]=d.adjacentPosition();
            d.aStep();
        }
        return positions;
    }

    @Override
    public String toString(){
        return "锋矢阵型";
    }
}
