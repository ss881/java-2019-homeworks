public class World <C extends Creature> {
    C[][] map;
    static final int N = 20;
    World(){
        map = (C[][])new Creature[N][N];
    }
    int getSize(){return N;}
    //打印对峙局面
    void show(){
        //打印对峙局面
        System.out.println("打印对峙局面");
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (map[i][j] == null){
                    System.out.printf("%4s","");
                }
                else{
                    String name = map[i][j].tellName();
                    System.out.printf("%4s",name);
                }
            }
            System.out.println("");
        }
    }
}
