package creature;
import java.net.*;
import map.*;
public class sanwa extends Creature implements set {

    public sanwa(){
    	super.imageurl=sanwa.class.getResource("3.jpg");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
