package hw3.Formation;

//容纳的Creature可以改成泛型
public abstract class Formation {
    private static int N = 12;
    protected int numberOfMembers = 0;
    protected boolean[][] map = new boolean[N][N];
    abstract void initMap();
    public Formation(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = false;
            }
        }
        initMap();                      //会调用子类的initMap()方法么
    }
    public boolean[][] getMap(){
        return map;
    }
    public int getNumberOfMembers(){
        return numberOfMembers;
    }
}

