package HLW.Creature;

import HLW.Basic.HLWCreature;
import HLW.Basic.HLWWorld;

public class HLWSheJing extends HLWCreature {
    public static HLWSheJing instance = null;

    private HLWSheJing(HLWWorld world){
        super("蛇精" , world);
        this.world.addMonster(this);
    }

    private HLWSheJing(HLWWorld world, int x){
        super("蛇精", world, x);
        this.world.addMonster(this);
    }

    private HLWSheJing(HLWWorld world, int x, int y){
        super("蛇精", world, x, y);
        this.world.addMonster(this);
    }

    private HLWSheJing(HLWWorld world, int x, int y, int z){
        super("蛇精", world, x, y, z);
        this.world.addMonster(this);
    }

    public static HLWSheJing getInstance(HLWWorld world, int x, int y, int z){
        if(instance != null){
            return instance;
        }
        if(x >= 0 && y >= 0 && z == -1 ) {
            instance = new HLWSheJing(world, x, y);
        }else if(x >= 0 && y == -1 && z == -1){
            instance = new HLWSheJing(world, x);
        }else if(x == -1 && y == -1 && z == -1){
            instance = new HLWSheJing(world);
        }else{
            instance = new HLWSheJing(world, x, y, z);
        }
        return instance;
    }

    @Override
    public int getSeniority() {
        return 0;
    }

    @Override
    public void printAlias() {
        this.printName();
    }

    @Override
    public String getName() {
        return new String("蛇精");
    }

    @Override
    public void printName() {
        System.out.print("蛇精" );
    }

    @Override
    public String getClassAndName() {
        return "s";
    }
}
