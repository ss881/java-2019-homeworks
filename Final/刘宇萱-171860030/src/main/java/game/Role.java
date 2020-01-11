package game;

public class Role {
	protected Coordinate mycoordinate = new Coordinate(0, 0);
    public void setCoordinate(Coordinate c) {
    	synchronized(Formation.class) {
    		mycoordinate.setX(c.getX());
    		mycoordinate.setY(c.getY());
    	}
    }
    public Coordinate getCoordinate() {
    	synchronized(Formation.class) {
        //c.setX(mycoordinate.getX());
        //c.setY(mycoordinate.getY());
    		return new Coordinate(mycoordinate.getX(), mycoordinate.getY());
    	}
    }
}