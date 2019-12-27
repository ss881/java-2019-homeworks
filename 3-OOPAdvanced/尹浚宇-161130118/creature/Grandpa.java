package creature;

public class Grandpa extends Creature implements Cheer
{
    public Grandpa(String name, int camp, String token)
    {
        super(name, camp, token);
    }

    public void cheer()
    {
        System.out.println("爷爷来帮你们啦!");
    }
}
