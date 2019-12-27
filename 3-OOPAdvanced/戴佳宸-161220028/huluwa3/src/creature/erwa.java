package creature;
import java.net.*;
import map.*;
public class erwa extends Creature implements set {

    public erwa(){
    	super.imageurl=erwa.class.getResource("2.jpg");
    }


    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
