package creature;
import java.net.*;
import map.*;
public class siwa extends Creature implements set {

    public siwa(){
    	super.imageurl=siwa.class.getResource("4.jpg");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
