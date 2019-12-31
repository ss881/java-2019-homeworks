package HLW.Creature;

import HLW.Basic.*;

/* At any time there can be only one XieZiJing or no XieZiJing in the world */
public class HLWXieZiJing extends HLWCreature{

    /* Indicates whether there have been a XieZiJing created */
    public static HLWXieZiJing instance = null;

    private HLWXieZiJing(HLWWorld world){
        super("蝎子精" , world);
        this.world.addMonster(this);
    }

    private HLWXieZiJing(HLWWorld world, int x){
        super("蝎子精", world, x);
        this.world.addMonster(this);
    }

    private HLWXieZiJing(HLWWorld world, int x, int y){
        super("蝎子精", world, x, y);
        this.world.addMonster(this);
    }

    private HLWXieZiJing(HLWWorld world, int x, int y, int z){
        super("蝎子精", world, x, y, z);
        this.world.addMonster(this);
    }

    public static HLWXieZiJing getInstance(HLWWorld world, int x, int y, int z){
        if(instance != null){
            return instance;
        }
        if(x >= 0 && y >= 0 && z == -1 ) {
            instance = new HLWXieZiJing(world, x, y);
        }else if(x >= 0 && y == -1 && z == -1){
            instance = new HLWXieZiJing(world, x);
        }else if(x == -1 && y == -1 && z == -1){
            instance = new HLWXieZiJing(world);
        }else{
            instance = new HLWXieZiJing(world, x, y, z);
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
        return new String("蝎子精");
    }

    @Override
    public void printName() {
        System.out.print("蝎子精" );
    }

    @Override
    public String getClassAndName() {
        return "x";
    }
}
