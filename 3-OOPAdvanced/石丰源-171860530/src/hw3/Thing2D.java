package hw3;
public class Thing2D {
    protected String name = null;
    protected Tile tile = null;
    protected  Field field = null;

    public Thing2D(){ }
    public Thing2D(String name){
        tile = null;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setTile(Tile tile){
        this.tile = tile;
    }
    public Tile getTile(){
        return tile;
    }
    public void setField(Field field){
        this.field = field;
    }
    public Field getField(){
        return field;
    }
    public void show(){
        System.out.printf("%8s", name);
    }
}
