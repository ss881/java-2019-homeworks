package Creature;

public class Monster extends Creature {
    public Monster(int id)
    {
        setName("喽啰");
        setId(id);
        alive = true;
        setCamp(1);
        rush = true;
    }
}
