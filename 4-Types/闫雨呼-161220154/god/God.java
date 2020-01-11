package god;
import creature.*;
import map.GameMap;
import team.Team;

public class God {
    private final static int CHANGE_NUM=6;
    private final static int BROTHERS_NUM=7;
    public final static int FOLLOWERS_NUM=20;
    public final static int FORMATION_NUM=8;
    public static void main(String[] args){
        //由上帝创造所有的生命体
        CalabashBrother[] calabashBrothers=new CalabashBrother[BROTHERS_NUM];
        for(int i=0;i<BROTHERS_NUM;i++)
            calabashBrothers[i]=new CalabashBrother();
        BadFollower[] badFollowers=new BadFollower[FOLLOWERS_NUM];
        for(int i=0;i<FOLLOWERS_NUM;i++)
            badFollowers[i]=new BadFollower();
        Grandpa grandpa=new Grandpa();
        ScorpionSperm scorpionSperm=new ScorpionSperm();

        //创造两支队伍
        Team<Grandpa,CalabashBrother> goodTeam=new Team<>(grandpa,calabashBrothers);
        Team<ScorpionSperm,BadFollower> badTeam=new Team<>(scorpionSperm,badFollowers);

        //初始化地图
        GameMap gameMap=new GameMap(goodTeam,badTeam);

        //阵型变换
        for(int i=0;i<CHANGE_NUM;i++) {
            try {
                Thread.sleep(1000);
                gameMap.changeFormation();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
