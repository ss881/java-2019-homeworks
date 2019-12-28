public class Object {
    protected Position mypos;
    protected String name;

    public Object(){
        mypos = new Position(-1,-1);
        name = "";
    }

    public void setPos(int x, int y){
        mypos.setPos(x, y);
    }

    public Position getMypos(){
        return mypos;
    }

    public String describe() {
        return name;
    }

    public boolean isnothing(){
        return true;
    }
}
