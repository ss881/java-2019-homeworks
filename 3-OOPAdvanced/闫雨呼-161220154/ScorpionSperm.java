import java.util.Random;

//小喽啰类
class BadFollower extends Creature{
    BadFollower(String name){
        super(name,true);
    }
}

//蝎子精类
class ScorpionSperm extends Creature{
    private final static int FOLLOWERS_NUM=7;
    private BadFollower[] badFollowers;
    ScorpionSperm(String name,BadFollower[] badFollowers){
        super(name,true);
        this.badFollowers=badFollowers;
    }
    //初始化
    void setBadGuys(){
        changeFormation(FormationKind.values()[new Random().nextInt(God.FOLLOWERS_NUM)]);
    }
    //变换阵型
    void changeFormation(FormationKind formation){
        Formation.changeFormation(GameMap.battleField,badFollowers,formation);
    }
}
