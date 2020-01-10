package creature;
import location.Location;
public class Creature{
    private Race race;
    private Location current;
    protected String symbol;//每个生物再控制台上的标识，用字符表示
    public Creature(){
        this.race=Race.Nothing;
        current=new Location(-1,-1);
    }
    public Creature(Race r){
        this.race=r;
        current=new Location(-1,-1);
    }
    public Race getRace(){return race;}
    public Location getLocation(){return current;}
    public void setLocation(int x,int y){current.setX(x);current.setY(y);}
    public String getSymbol(){return symbol;}
    protected void rushto(int x,int y){//生物自身的行为，前往某个坐标 
        current.setX(x);
        current.setY(y);
    }
    public void followOrder(Creature from,int x,int y){//提供生物接受某个生物的命令前往某个位置的抽象方法
        rushto(x, y);
    }
}