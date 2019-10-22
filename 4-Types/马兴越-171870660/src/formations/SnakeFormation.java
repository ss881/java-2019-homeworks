package formations;

import field.Field;
import field.Position;
import items.Living;

public class SnakeFormation extends Formation {
    public SnakeFormation(Field field, Living leader, Living[] followers) {
        super(field, leader, followers);
    }

    @Override
    protected Position[] form() {
        Position[] positions=new Position[N];
        Position p=leader.getPosition().copy();
        Position.Direction d=p.new Direction(Position.Direction.S);
        int t=0;
        for(int i=0;i<N;i++){
            positions[t++]=d.adjacentPosition();
            d.aStep();
        }
        return positions;
    }
}
