package map;

import java.util.Random;
import formation.*;
import god.God;
import team.Team;

//地图类
public class GameMap {
    public final static int N=17;
    //由地砖组成的地图
    public static Tile[][] battleField=new Tile[N][N];
    private Team goodTeam,badTeam;
    public GameMap(Team goodTeam,Team badTeam){
        System.out.println("初始化阵型");
        for(int i=0;i<150;i++)
            System.out.print('-');
        System.out.print("\n");

        this.goodTeam=goodTeam;
        this.badTeam=badTeam;
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                battleField[i][j] = new Tile(i, j);
            }
        }
        try {
            setGrandpaAndScorpionSperm();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        print();
    }
    private void setGrandpaAndScorpionSperm() throws Exception{
        if(!goodTeam.leader.tryMove(new Position((N-1)/2,0)))
            throw new Exception("爷爷无法移动到指定位置");
        if(!badTeam.leader.tryMove(new Position((N-1)/2,N-1)))
            throw new Exception("蝎子精无法移动到指定位置");
        goodTeam.changeFormation(FormationKind.SNAKE);
        badTeam.changeFormation(FormationKind.values()[new Random().nextInt(God.FORMATION_NUM)]);
    }
    //变换阵型
    public void changeFormation(){
        badTeam.changeFormation(FormationKind.values()[new Random().nextInt(God.FORMATION_NUM)]);
        print();
    }
    //打印地图
    private void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(battleField[i][j].creature != null){
                    //Creature here
                    battleField[i][j].creature.solute();
                    System.out.print("\t");
                }
                else{
                    //Nothing here
                    System.out.print("\t");
                }
                System.out.print("\t");
            }
            System.out.print("\n");
        }
        for(int i=0;i<150;i++)
            System.out.print('-');
        System.out.print("\n");
    }
    //判断目标位置是否越界
    public static boolean outOfRange(Position dst){
        return dst.x < 0 || dst.x >= GameMap.N || dst.y < 0 || dst.y >= GameMap.N;
    }
    //判断目标位置是否有生命体存在
    public static boolean hasCreature(Position dst){
        return battleField[dst.x][dst.y].creature!=null;
    }
}
