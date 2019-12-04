package creature;

public class Snake extends Creature implements Cheer
{
    public Snake(String name, int camp, String token)
    {
        super(name, camp, token);
    }

    public void cheer()
    {
        System.out.println("小的们给我上啊!");
    }
}
