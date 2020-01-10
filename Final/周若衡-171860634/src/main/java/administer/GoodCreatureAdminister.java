package administer;

import creature.CalabashBrother;
import creature.Elder;
import util.ImageType;

import java.util.ArrayList;


public class GoodCreatureAdminister {
    private ArrayList<CalabashBrother> brothers=new ArrayList<>();
    private Elder elder;

    public GoodCreatureAdminister(){
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_ONE,1,50,true,10,"红娃",2400,1));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_TWO,2,50,true,10,"橙娃",2400,2));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_THREE,3,50,true,10,"黄娃",2400,3));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_FOUR,4,50,true,10,"绿娃",2400,4));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_FIVE,5,50,true,10,"青娃",2400,5));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_SIX,6,50,true,10,"蓝娃",2400,6));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_SEVEN,7,50,true,10,"紫娃",2400,7));
        elder =new Elder(-1,1, ImageType.ELDER,70,true,20,"爷爷",3700,8);
    }
    public ArrayList<CalabashBrother> getHuLuWas(){
        return brothers;
    }
    public Elder getElder(){
        return elder;
    }
    public void initial(){
        brothers.clear();
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_ONE,1,50,true,10,"红娃",2400,1));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_TWO,2,50,true,10,"橙娃",2400,2));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_THREE,3,50,true,10,"黄娃",2400,3));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_FOUR,4,50,true,10,"绿娃",2400,4));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_FIVE,5,50,true,10,"青娃",2400,5));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_SIX,6,50,true,10,"蓝娃",2400,6));
        brothers.add(new CalabashBrother(-1,-1, ImageType.BROTHER_SEVEN,7,50,true,10,"紫娃",2400,7));
        elder =new Elder(-1,1, ImageType.ELDER,50,true,20,"爷爷",3700,8);
    }

    public boolean isAllDie(){
        if(elder.isAlive()==false)
            return true;

        for(CalabashBrother huluwa:brothers){
            if(huluwa.isAlive())
                return false;
        }
        if(elder.isAlive())
            return false;
        return true;
    }
}