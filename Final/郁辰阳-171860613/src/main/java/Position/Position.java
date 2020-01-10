package Position;

//单元位置

public class Position {
	private int x;
	private int y;
	
	Position(){x=0; y=0;}
	public Position(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public Position up() { return new Position(x-1,y);}
	public Position down() { return new Position(x+1,y);}
	public Position left() { return new Position(x,y-1);}
	public Position right() { return new Position(x,y+1);}
	
	public String toString() {
		return String.valueOf(x)+' '+String.valueOf(y);
	}
}
