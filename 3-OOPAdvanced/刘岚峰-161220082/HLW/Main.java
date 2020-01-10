package HLW;

import HLW.Basic.HLWCreature;
import HLW.Basic.HLWWorld;
import HLW.Creature.*;
import HLW.Formation.*;

public class Main {
    public static void main(String[] args){
        HLWWorld    world = new HLWWorld("Test", 50, 50, 30);
        HLWFormationChangShe    formation = new HLWFormationChangShe("长蛇", world, new int[]{0, 10, 10}, 7);
        HLWFormationYanXing f1 = new HLWFormationYanXing("雁行", world, new int[]{0, 10, 37}, 9);
        HLWFormationHeYi    f2 = new HLWFormationHeYi("鹤翼", world, new int[]{0, 35, 37}, 9);
        for(int i = 0; i<formation.getFullSize() ;i++){
            HLWCreature   tmp = new HLWHuLuWa(world, i%7 + 1);
            formation.addMember(tmp);
        }
        for(int i = 0; i<f1.getFullSize() - 1; i++){
            HLWCreature tmp = new HLWMinion(world);
            f1.addMember(tmp);
        }
        HLWCreature Yeye = HLWYeYe.getInstance(world, 0, 10, 0);
        HLWCreature SheJing = HLWSheJing.getInstance(world, 49, 10, 0);
        f1.addMember(HLWXieZiJing.getInstance(world, -1, -1, -1));
        world.printWorld();
        formation.lineUp();
        world.printWorld();
        f1.lineUp(HLWXieZiJing.getInstance(world, -1, -1, -1));
        world.printWorld();
        f1.transferFormation(f2);
        f2.lineUp(HLWXieZiJing.getInstance(world, -1, -1, -1));
        world.printWorld();
    }
}
