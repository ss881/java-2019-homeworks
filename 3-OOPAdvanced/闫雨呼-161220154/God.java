public class God {
    private final static int changeTimes=6;
    private final static int BROTHERS_NUM=7;
    final static int FOLLOWERS_NUM=7;
    public static void main(String[] args){
        //由上帝创造所有的生命体
        CalabashBrother[] calabashBrothers=new CalabashBrother[BROTHERS_NUM];
        for(int i=0;i<BROTHERS_NUM;i++)
            calabashBrothers[i]=new CalabashBrother("B"+(i+1));
        BadFollower[] badFollowers=new BadFollower[FOLLOWERS_NUM];
        for(int i=0;i<FOLLOWERS_NUM;i++)
            badFollowers[i]=new BadFollower("F"+(i+1));
        Grandpa grandpa=new Grandpa("GP",calabashBrothers);
        ScorpionSperm scorpionSperm=new ScorpionSperm("SS",badFollowers);

        //初始化阵型参数
        Formation.initFormation();
        GameMap gameMap=new GameMap(grandpa,scorpionSperm);
        for(int i=0;i<changeTimes;i++) {
            try {
                Thread.sleep(1000);
                gameMap.changeFormation();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
