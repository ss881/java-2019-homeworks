package creature;
import java.net.*;
import map.*;
public class yeye extends Creature implements set {

    public yeye(){
    	super.imageurl=yeye.class.getResource("yeye.PNG");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
