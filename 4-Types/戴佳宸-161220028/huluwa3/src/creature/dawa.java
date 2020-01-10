package creature;
import java.net.*;
import map.*;
public class dawa extends Creature implements set {

    public dawa(){
    	super.imageurl=dawa.class.getResource("1.jpg");
    }


    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
