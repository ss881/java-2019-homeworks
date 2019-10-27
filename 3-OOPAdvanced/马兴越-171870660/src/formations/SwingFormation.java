package formations;

import field.Field;
import field.Position;
import items.Living;

public class SwingFormation extends Formation {

    public SwingFormation(Field field, Living leader, Living[] followers) {
        super(field, leader, followers);
    }

    protected Position[] form(){
        Position[] positions=new Position[N];
        int depth=N/2;
        int t=0;
        Position position=leader.getPosition();
        //left
        Position p=position.copy();
        Position.Direction d=p.new Direction(Position.Direction.NW);
        for(int i=0;i<depth;i++){
            positions[t++]=d.adjacentPosition();
            d.aStep();
        }
        p=position.copy();
        d=p.new Direction(Position.Direction.NE);
        for(int i=0;i<depth;i++){
            positions[t++]=d.adjacentPosition();
            d.aStep();
        }
        return positions;
    }

    @Override
    public boolean embattle() {
        if(N%2!=0){
            System.out.println(toString()+"需要偶数的从者！");
            return false;
        }
        return super.embattle();
    }

    @Override
    public String toString(){
        return "鹤翼阵型";
    }
}
