package behave;


import creature.Creature;

public class AttackBehave extends Behave {
    private Creature enemy;

    public AttackBehave(int sleepTime,Creature enemy) {
        super(sleepTime);
        this.enemy=(Creature) enemy.clone();
    }
    @Override
    public Object clone() {
        AttackBehave temp = null;
        temp = (AttackBehave)super.clone();
        temp.enemy = (Creature)enemy.clone();
        return temp;
    }
    public Creature getEnemy(){return enemy;}
}
