package creature;
import java.net.*;
import map.*;
public class Creature {
    public URL imageurl;
    public URL geturl(){
        return this.imageurl;
    }

    public void move(Map map,Position pos){
    	pos.setCreature(map,this);
    }

    public void remove(Map map,Position pos){
    	pos.removeCrearure(map);
    }

}
