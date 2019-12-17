package creature;
import java.net.*;
import map.*;
public class qiwa extends Creature implements set {

    public qiwa(){
    	super.imageurl=qiwa.class.getResource("7.jpg");
    }

    public URL geturl(){
        return super.imageurl;
    }

    public void setwa(Map map, int x, int y){
    	move(map,map.getPos()[x][y]);
    }

}
