package HLW.Creature;

import HLW.Basic.*;

public class HLWYeYe extends HLWCreature{

    public static HLWYeYe instance = null;

    private HLWYeYe(HLWWorld world){
        super("爷爷" , world);
        this.world.addHuLuWa(this);
    }

    private HLWYeYe(HLWWorld world, int x){
        super("爷爷", world, x);
        this.world.addHuLuWa(this);
    }

    private HLWYeYe(HLWWorld world, int x, int y){
        super("爷爷", world, x, y);
        this.world.addHuLuWa(this);
    }

    private HLWYeYe(HLWWorld world, int x, int y, int z){
        super("爷爷", world, x, y, z);
        this.world.addHuLuWa(this);
    }

    public static HLWYeYe getInstance(HLWWorld world, int x, int y, int z){
        if(instance != null){
            return instance;
        }
        if(x >= 0 && y >= 0 && z == -1 ) {
            instance = new HLWYeYe(world, x, y);
        }else if(x >= 0 && y == -1 && z == -1){
            instance = new HLWYeYe(world, x);
        }else if(x == -1 && y == -1 && z == -1){
            instance = new HLWYeYe(world);
        }else{
            instance = new HLWYeYe(world, x, y, z);
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
        return new String("爷爷");
    }

    @Override
    public void printName() {
        System.out.print("爷爷" );
    }

    @Override
    public String getClassAndName() {
        return "y";
    }

}
