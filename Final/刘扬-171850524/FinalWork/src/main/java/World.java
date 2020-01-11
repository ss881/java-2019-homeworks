public class World {
    public Creature[][] map;
    public static final int N = 18;
    public World(){
        map = new Creature[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                map[i][j] = null;
    }
    public int getSize() { return N; }
    public void show(){
        //打印对峙局面
        System.out.println("打印对峙局面");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) {
                    System.out.printf("%4s","");
                }
                else {
                    String name = map[i][j].tellName();
                    System.out.printf("%4s",name);
                }
            }
            System.out.println("");
        }
    }
}
