package game;

public class Coordinate {
    private int x;
    private int y;
    
    public Coordinate(int newx, int newy) {
    	//synchronized(Formation.mutex) {
    		x = newx;
    		y = newy;
    	//}
    }

    public int getX() { 
    	//synchronized(Formation.mutex) {
    		return x; 
    	//}
    }

    public int getY() { 
    	//synchronized(Formation.mutex) {
    		return y; 
    	//}
    }

    public void setX(int newx) { 
    	//synchronized(Formation.mutex) {
    		x = newx; 
    	//}
    }

    public void setY(int newy) {
    	//synchronized(Formation.mutex) {
    		y = newy; 
    	//}
    }
}