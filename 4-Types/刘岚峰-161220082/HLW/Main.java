package HLW;

import HLW.Basic.HLWCreature;
import HLW.Basic.HLWWorld;
import HLW.Creature.*;
import HLW.Formation.*;

import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) throws Exception{
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
        f1.addMember(HLWXieZiJing.getInstance(world, -1, -1, -1));

        Class clz = Class.forName("HLW.Creature.HLWYeYe");
        Method method = clz.getMethod("getInstance", HLWWorld.class, int.class, int.class, int.class);
        HLWYeYe yeye = (HLWYeYe)method.invoke(null, world, 0, 10, 0);
        clz = Class.forName("HLW.Creature.HLWSheJing");
        method = clz.getMethod("getInstance", HLWWorld.class, int.class, int.class, int.class);
        HLWSheJing shejing = (HLWSheJing)method.invoke(null, world, 49, 10, 0);

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
