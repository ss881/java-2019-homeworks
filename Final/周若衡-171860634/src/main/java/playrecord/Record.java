package playrecord;

import behave.Behave;
import creature.Creature;

public class Record implements java.io.Serializable{
    private Creature creature;
    private Behave behave;
    public Record(Creature creature, Behave behave){
        this.creature=(Creature) creature.clone();
        this.behave=(Behave) behave.clone();
    }
    public Creature getCreature(){return creature;}
    public Behave getBehave(){return behave;}
}
