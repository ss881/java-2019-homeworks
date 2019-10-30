package creature;
import java.net.*;
import map.*;
public class liuwa extends Creature implements set {

    public liuwa(){
    	super.imageurl=liuwa.class.getResource("6.jpg");
    }


    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
