package creature;
import java.net.*;
import map.*;
public class xiaobing extends Creature implements set,remove{

    public xiaobing(){
    	super.imageurl=xiaobing.class.getResource("xiaobing.PNG");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

    public void removebing(Map map, int x, int y){
    	remove(map,map.getPos()[x][y]);
    }

}
