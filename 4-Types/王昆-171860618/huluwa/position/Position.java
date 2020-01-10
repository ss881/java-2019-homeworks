package huluwa.position;
public class Position implements Comparable<Position>{
    private static int mapWidth;
    private static int mapHeight;
    private static int xMax;
    private static int yMax;
    private static int xLT;
    private static int yT;
    private static int yB;
    private static int xRT;
    static public void setMapSize(int w,int h){
        mapWidth = w-50;
        mapHeight = h;
        xLT = mapWidth/8;
        xRT = mapWidth*7/8;
        yT = mapHeight/4;
        yB = mapHeight*15/16;
    }
    static public void setMaxGrid(int x,int y){
        xMax = x;
        yMax = y;
    }
    public static int sizeX(){
        return xMax;
    }
    public static int sizeY(){
        return yMax;
    }
    private int x;
    private int y;
    public Position(int a, int b){
        this.x = a;
        this.y = b;
    }
    public int getXOnMap(){
        int baseXLeft =  50 + xLT - (xLT*y)/yMax;
        int baseXRight = xRT + ((mapWidth - xRT)*y)/yMax;
        int x = baseXLeft + (int) ((baseXRight -baseXLeft)*((1.0*this.x)/(1.0*xMax)));
        return  x;

    }
    public int getYOnMap(){
        return yT + ((yB-yT)*(this.y)) /yMax;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Position){
            Position p = (Position)obj;
            return p.getX() == this.x && p.getY() == this.y;
        }else{
            return false;
        }
    }
    @Override
    public String toString(){
        return this.x + ","+this.y;
    }
    @Override
    public int hashCode(){
        return x+x*y;
    }
    @Override
    public int compareTo(Position o) {
        int y = o.getY();
        if(this.getY() == y){
            return this.getX() - o.getX();
        }
        return this.getY() - y;
    }
}