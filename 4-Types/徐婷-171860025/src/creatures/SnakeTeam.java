package creatures;
import java.util.*;
import formation.Formation;
enum ArrangeMent{HeYi,YanXing,HengE,YuLing,FangYuan,YanYue,FengShi}

public class SnakeTeam {
    Snake snake = new Snake("蛇精", 12, 12);
    List<Damon> damons = new ArrayList<>();
    ArrangeMent arrangement = ArrangeMent.HeYi;
    public SnakeTeam(){
        damons.add(new Damon("蝎子"));
        for(int i = 1;i<20;i++){
            damons.add(new Damon("喽啰"));
        }
        Formation.heYi(damons, 7, 6);
    }
    public void arrange(){
        switch(arrangement){
            case HeYi: Formation.yanXing(damons,6,3);arrangement=ArrangeMent.YanXing;break;
            case YanXing:Formation.hengE(damons, 7,5);arrangement=ArrangeMent.HengE;break;
            case HengE:Formation.yuLing(damons,7,6);arrangement=ArrangeMent.YuLing;break;
            case YuLing:Formation.fangYuan(damons, 6,6);arrangement = ArrangeMent.FangYuan;break;
            case FangYuan:Formation.yanYue(damons, 5,6);arrangement=ArrangeMent.YanYue;break;
            case YanYue:Formation.fengShi(damons,6,6);arrangement=ArrangeMent.FengShi;break;
            case FengShi:Formation.heYi(damons,7,6);arrangement=ArrangeMent.HeYi;break;
        }
    }
    public List<SnakeSide>getTeamMembers(){
        List<SnakeSide> ret = new ArrayList<>();
        ret.add(snake);
        ret.addAll(1, damons);
        return ret;
    }
}
