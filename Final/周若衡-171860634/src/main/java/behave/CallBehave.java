package behave;

import creature.Creature;

public class CallBehave extends Behave {
    private Creature c;

    public CallBehave(int sleepTime, Creature c) {
        super(sleepTime);
        this.c=c;
    }
    @Override
    public Object clone() {
        CallBehave temp = null;
        temp = (CallBehave) super.clone();
        temp.c=(Creature)c.clone();
        return temp;
    }
    public Creature getCreature(){return c;}
}
