package creature;

import position.Position;

interface MoveAble{
    void moveTo(Position p);
    void disappear();//jump out of side:-1
}

interface switchAble<T>{
    void switchWith(T t);
}

public class Creature implements MoveAble,switchAble<Creature>{
    protected Position position;//aggression

    public Creature(){
        position=new Position();
    }

    public Creature(int pos){
        position=new Position(pos);
    }

    @Override
    public void moveTo(Position p) {
        position.setPos(p.getPos());
    }

    @Override
    public void disappear() {
        position.setPos(-1);//jump out
    }

    @Override public String toString(){
        return "Creature :Position = "+position.getPos();
    }

    @Override
    public void switchWith(Creature creature) {
        Position tempAnother=new Position(creature.position);
        Position tempThis=new Position(this.position);
        creature.disappear();
        this.moveTo(tempAnother);
        creature.moveTo(tempThis);

//        //exchange idx
//        int temp=creature.;
//        creature.position.setPos(this.position.getPos());
//        this.position.setPos(temp);
    }
}

