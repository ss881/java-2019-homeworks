package Creature;

public class Monster extends Creature {
    public Monster(int id)
    {
        setId(id);
        alive = true;
        setCamp(1);
        rush = true;
    }
}
