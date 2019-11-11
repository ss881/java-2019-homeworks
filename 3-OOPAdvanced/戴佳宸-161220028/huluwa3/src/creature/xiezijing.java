package creature;
import java.net.*;
import map.*;
public class xiezijing extends Creature implements set {

    public xiezijing(){
    	super.imageurl=xiezijing.class.getResource("boss.png");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
