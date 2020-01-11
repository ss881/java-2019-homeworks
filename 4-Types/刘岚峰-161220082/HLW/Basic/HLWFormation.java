package HLW.Basic;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public abstract class HLWFormation {
    /* The center of the formation, described as 3-D coordinates*/
    protected int[] center;
    /* Name(type) of the formation, combined with center can be seen as identification of a formation*/
    protected String name;
    /* Then HLW.Basic.HLWCreature stands in the center of the formation*/
    protected HLWCreature centerCreature;
    /* The initial member numbers of the formation. The members ArrayList's size must equal to this
    * value before line up */
    protected int size;
    /* Members in the formation*/
    protected ArrayList<HLWCreature> members;
    /* Related world */
    protected HLWWorld world;


    /** brief: construction of HLW.Basic.HLWFormation. Users will need to add formation members
     * one by one by themselves, also the center Creature will be set afterwards */
    public HLWFormation(HLWWorld world, int[] c, String name, int size){
        if(world == null){
            System.err.println("world can't be null!");
            System.exit(-1);
        }
        this.world = world;
        /* TD: will do something to handle the situation where two formation are overlapped */
        if(!this.world.checkPointInBoundary(c)){
            HLWLog.HLW_ERROR("The center of the formation is illegal.");
            System.exit(-1);
        }
        this.center = new int[3];
        System.arraycopy(c, 0, this.center, 0, 3);
        this.name = name;
        this.size = size;
        members = new ArrayList<HLWCreature>();
    }


    protected abstract int[] getBeginPosition();

    /** brief: Call this function will then form the formation , before doing this, need to set up the center,
     * the center creature and members, there will be other parameters needed by each formation respectively */
    public void lineUp() {
        int[] loc = null;
        HLWCreature[] targets = new HLWCreature[1];

        if (this.size != members.size()) {
            HLWLog.HLW_ERROR(String.format("The formation can't be lined up due to the inconsistent size (need %d but %d)",
                    this.size, this.members.size()));
            System.exit(-1);
        }

        HLWLog.HLW_NOTICE(String.format("%s站队开始。", this.name));
        loc = getBeginPosition();

        for (HLWCreature c : members) {
            if (this.world.checkLocation(loc)) {
                targets[0] = c;
                c.prepareMessage("move to", loc, targets);
                c.sendOneMessage();
                c.handleOneMessage();
            } else {
                targets[0] = this.world.getCreature(loc);
                if (targets[0] != c ) {
                    c.prepareMessage("switch", null, targets);
                    c.sendOneMessage();
                    targets[0].handleOneMessage();
                }
            }
            findNextPositionInFormation(loc);
        }
        this.distributeRandomly();
        HLWLog.HLW_NOTICE(String.format("%s站队结束。", this.name));
    }

    /** brief: Same as last function but need to point out the center creature, noted that the center creature
     * must has been in the members ArrayList */
    public void lineUp(HLWCreature centerCreature){

        this.lineUp();
        if(centerCreature.getFormation() != this){
            HLWLog.HLW_ERROR("The assigned center creature doesn't belong to this formation!");
        }else{
            if(this.world.checkLocation(this.getCenter())){
                centerCreature.prepareMessage("move to", this.getCenter(),
                        new HLWCreature[]{centerCreature});
                centerCreature.sendOneMessage();
                centerCreature.handleOneMessage();
            }else{
                HLWCreature[]   tgt = new HLWCreature[1];
                tgt[0] = this.world.getCreature(this.getCenter());
                centerCreature.prepareMessage("switch", null, tgt);
                centerCreature.sendOneMessage();
                tgt[0].handleOneMessage();
            }
        }
    }

    /* Transfer this formation into the target formation given in the parameter. */
    public void transferFormation(HLWFormation f){
        this.world.copyLocation(this.center, f.center);
        f.size = this.size;
        f.world = this.world;
        for(int i = 0; i < f.size; i++){
            HLWCreature tmp = this.members.get(i);
            tmp.setFormation(f);
            f.members.add(tmp);
        }
        this.members.clear();
    }

    /** brief: swap world map locations of two internal HLWCreatures in the members ArrayList,
     * before swapping should check the two HLWCreatures' belonging to this Formation.
     * params:
     * both A and B are in this formation, and will change their location in the world map */
    public void swapInternal(HLWCreature A, HLWCreature B){
        HLWCreature[] tgt = new HLWCreature[1];
        tgt[0] = B;
        if(A.getFormation() != this || B.getFormation() != this){
            HLWLog.HLW_ERROR("The creatures to be swapped are not belong to this formation!");
            System.exit(-1);
        }
        A.prepareMessage("switch", null, tgt);
        A.sendOneMessage();
        B.handleOneMessage();
    }


    public HLWWorld getWorld(){
        return this.world;
    }

    public int getFullSize(){ return this.size;}

    public int getCurrentSize(){return this.members.size();}

    /** brief: Add member to the members ArrayList
     * params:
     * */
    public void addMember(HLWCreature newMember){
        if(newMember.getWorld() != this.world){
            System.err.println("The Creature and the formation are in different worlds.");
            System.exit(-1);
        }
        if(members.size() == this.size){
            System.err.printf("There is no empty space for new member in this formation! The max number is %d.\n",
                    this.size);
        }else{
            this.members.add(newMember);
            newMember.setFormation(this);
            /* MessageFormat.format("Formation member Status: %s--(%d/%d)",
                    this.getFormationID(), this.members.size(), this.size); */
            HLWLog.HLW_NOTICE(String.format("Formation member Status: %s--(%d/%d)",
                    this.getFormationID(), this.members.size(), this.size));
        }
    }

    protected abstract void findNextPositionInFormation(int[] loc);

    protected void distributeRandomly(){
        int locA, locB;
        Random r = new Random();
        HLWCreature[] targets = new HLWCreature[1];
        HLWCreature src;

        for(int i = 0; i < this.size; i++){
            locA = r.nextInt(this.size);
            locB = r.nextInt(this.size);
            targets[0] = this.members.get(locA);
            src = members.get(locB);
            src.prepareMessage("switch", null, targets);
            src.sendOneMessage();
            targets[0].handleOneMessage();
        }
    }

    public String getName() {
        return name;
    }

    public int[] getCenter(){
        return this.center;
    }

    public String getStringCenter(){
        return HLWWorld.getStringLocation(this.center);
    }

    /** brief: Get the formation's type and its center(note that center can be seen as the identification
     * of a formation */
    public String getFormationID(){
        return getName() + getStringCenter();
    }
}
