import java.util.Random;

//地图类
class GameMap {
    final static int N=15;
    //由地砖组成的地图
    static Tile[][] battleField=new Tile[N][N];
    private Grandpa grandpa;
    private ScorpionSperm scorpionSperm;
    GameMap(Grandpa grandpa,ScorpionSperm scorpionSperm){
        System.out.println("初始化阵型");
        this.scorpionSperm=scorpionSperm;
        this.grandpa=grandpa;
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                battleField[i][j]=new Tile();
        setGrandpaAndScorpionSperm();
        print();
    }
    private void setGrandpaAndScorpionSperm(){
        if(grandpa.canMove(battleField,(N-1)/2,0));
            grandpa.runTo(battleField,(N-1)/2,0);
        if(scorpionSperm.canMove(battleField,(N-1)/2,N-1))
            scorpionSperm.runTo(battleField,(N-1)/2,N-1);
        grandpa.setCalabashBrothers();
        scorpionSperm.setBadGuys();
    }
    //变换阵型
    void changeFormation(){
        scorpionSperm.changeFormation(FormationKind.values()[new Random().nextInt(God.FOLLOWERS_NUM)]);
        print();
    }
    //打印地图
    private void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(battleField[i][j].creature != null){
                    //Creature here
                    battleField[i][j].creature.solute();
                }
                else{
                    //Nothing here
                    System.out.print("**");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        for(int i=0;i<45;i++)
            System.out.print('-');
        System.out.print("\n");
    }
}
