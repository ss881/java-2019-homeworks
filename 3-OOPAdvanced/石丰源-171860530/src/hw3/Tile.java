package hw3;
public class Tile {
    private Thing2D holder;
    private int x;
    private int y;
    public Tile(){
        holder = null;
        x = -1;
        y = -1;
    }
    public Tile(int x, int y){
        holder = null;
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setHolder(Thing2D t){
        holder = t;
    }
    public Thing2D getHolder(){
        return holder;
    }
    public void removeHolder(){
        holder = null;
    }
    public void show(){
        if(holder != null)
            holder.show();
        else{
            System.out.printf("%8s", " ");
        }
    }
    public void clear(){
        holder = null;
    }
}
