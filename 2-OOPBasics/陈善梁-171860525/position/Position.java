package position;

public class Position implements Comparable<Position>{
    private int pos;

    public Position(int pos){
        this.pos=pos;
    }

    public Position(Position position){
        this.pos=position.pos;
    }

    public Position(){
        this(-1);
    }

    public int getPos(){
        return pos;
    }

    public void setPos(int pos){
        this.pos=pos;
    }

    @Override
    public int compareTo(Position o) {
        return this.pos-o.pos;
    }
}