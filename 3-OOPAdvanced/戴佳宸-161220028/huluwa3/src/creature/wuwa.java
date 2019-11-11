package creature;
import java.net.*;
import map.*;
public class wuwa extends Creature implements set {

    public wuwa(){
    	super.imageurl=wuwa.class.getResource("5.jpg");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
