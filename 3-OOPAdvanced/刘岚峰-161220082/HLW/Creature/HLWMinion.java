package HLW.Creature;

import HLW.Basic.*;

public class HLWMinion extends HLWCreature{

    /* The total number of Minions(喽啰) that exist or have existed */
    private static int countMinion = 0;

    private int number;

    public HLWMinion(HLWWorld world){
        super("喽啰" +countMinion, world);
        this.world.addMonster(this);
        this.number = countMinion;
        countMinion += 1;
    }

    public HLWMinion(HLWWorld world, int x){
        super("喽啰"+countMinion, world, x);
        this.world.addMonster(this);
        this.number = countMinion;
        countMinion += 1;
    }

    public HLWMinion(HLWWorld world, int x, int y){
        super("喽啰"+countMinion, world, x, y);
        this.world.addMonster(this);
        this.number = countMinion;
        countMinion += 1;
    }

    public HLWMinion(HLWWorld world, int x, int y, int z){
        super("喽啰"+countMinion, world, x, y, z);
        this.world.addMonster(this);
        this.number = countMinion;
        countMinion += 1;
    }

    @Override
    public int getSeniority() {
        return 0;
    }

    @Override
    public void printAlias() {
        System.out.print("小妖精-"+this.number);
    }

    @Override
    public String getName() {
        return String.format("喽啰-%d", this.number);
    }

    @Override
    public void printName() {
        System.out.print("喽啰-" + this.number);
    }

    @Override
    public String getClassAndName() {
        return "m";
    }
}
