package game;

public class CalabashBrother extends Role {
	private Color myColor;
    private int rank;

    /*public CalabashBrother() {
        mycoordinate = new Coordinate(0, 0);
    }*/

    public CalabashBrother(Color c, int r, int x, int y) {
        mycoordinate = new Coordinate(x, y);
        myColor = c;
        rank = r;
    }

    public int getRank() {
        return rank;
    }
}