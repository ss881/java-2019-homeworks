package creature;

abstract public class Leader <T extends Creature>extends Creature {
    abstract public T[] initialize();
    abstract public void changeForm(T[]ts);
}
