class Position {
    int x;
    int y;

    public Position(int a, int b) {
        x = a;
        y = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeX(int i) {
        x = i;
    }

    public void changeY(int i) {
        y = i;
    }

}

public class Chessman {
    String name;
    String color;
    Position site;
    int id;

    /*Should only be initialed in this way, no default constructor!
    * When be extended it will be called.*/
    public Chessman(String n, String c, Position s, int i) {
        name = n;
        color = c;
        site = s;
        id = i;
    }

    public int changeSite(Position nextSite) {
        //TODO:study the change in address!
        site = nextSite;
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Position getSite() {
        return site;
    }

    public int getId(){
        return id;
    }
}
