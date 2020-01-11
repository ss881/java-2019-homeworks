package HLW.Creature;

import HLW.Basic.HLWCreature;
import HLW.Basic.HLWLog;
import HLW.Basic.HLWWorld;

public class HLWHuLuWa extends HLWCreature {
    public static final String[] HuLuWaSeniority = {
        "大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃"
    };

    public static final String[] HuLuWaColor = {
        "赤", "橙", "黄", "绿", "青", "蓝", "紫"
    };

    /* The rank of the HuLuWa among his family , the value must be from 1 to 7*/
    private int seniority;
    /* may be need add other properties */

    public HLWHuLuWa(HLWWorld world, int seniority){
        super("葫芦娃-"+HuLuWaSeniority[seniority-1], world);
        if(seniority < 1 || seniority > 7){
            HLWLog.HLW_ERROR("葫芦娃的兄弟的辈分只能是1到7之间的某个数.");
            System.exit(-1);
        }
        this.seniority = seniority;
        this.world.addHuLuWa(this);
    }

    public HLWHuLuWa(HLWWorld world, int seniority, int x){
        super("葫芦娃", world, x);
        if(seniority < 1 || seniority > 7){
            HLWLog.HLW_ERROR("葫芦娃的兄弟的辈分只能是1到7之间的某个数.");
            System.exit(-1);
        }
        this.seniority =seniority;
        this.world.addHuLuWa(this);
    }

    public HLWHuLuWa(HLWWorld world, int seniority, int x, int y){
        super("葫芦娃", world, x, y);
        if(seniority < 1 || seniority > 7){
            HLWLog.HLW_ERROR("葫芦娃的兄弟的辈分只能是1到7之间的某个数.");
            System.exit(-1);
        }
        this.seniority = seniority;
        this.world.addHuLuWa(this);
    }

    public HLWHuLuWa(HLWWorld world, int seniority, int x, int y, int z){
        super("葫芦娃", world, x, y, z);
        if(seniority < 1 || seniority > 7){
            HLWLog.HLW_ERROR("葫芦娃的兄弟的辈分只能是1到7之间的某个数.");
            System.exit(-1);
        }
        this.seniority = seniority;
        this.world.addHuLuWa(this);
    }

    void setSeniority(int seniority){
        this.seniority = seniority;
    }

    public String getHLWHuLuWaColor(){
        return HuLuWaColor[this.seniority - 1];
    }

    @Override
    public String getName(){
        return HuLuWaSeniority[this.seniority - 1];
    }

    @Override
    public String getClassAndName(){
        return String.format("%d", this.seniority);
    }

    /**
     * brief: compare two HuLuWas by their seniority number. If seniority of a is smaller than that of b, return true,
     * else return false.
     * param: a and b are HuLuWas who put into comparison
     */
    public static boolean compareBySeniority(HLWHuLuWa a, HLWHuLuWa b){
        return a.seniority < b.seniority;
    }

    @Override
    public int getSeniority(){
        return this.seniority;
    }

    @Override
    public void printName(){
        System.out.print(this.name + "-" + HuLuWaSeniority[this.seniority-1]);
    }

    @Override
    public void printAlias(){
        System.out.print(this.name + "-" + HuLuWaColor[this.seniority-1]);
    }

}
