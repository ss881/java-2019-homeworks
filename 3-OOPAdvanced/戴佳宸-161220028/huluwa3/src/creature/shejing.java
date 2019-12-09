package creature;
import java.net.*;
import map.*;
public class shejing extends Creature implements set {

    public shejing(){
    	super.imageurl=shejing.class.getResource("shejing.jpg");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
