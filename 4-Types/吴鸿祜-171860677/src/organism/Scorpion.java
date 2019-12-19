package organism;

public class Scorpion extends Organism{
    public Scorpion()
    {
        this.name="🦂";
    }

    public Scorpion(Scorpion temp)
    {
        super(temp);
        this.name=temp.name;
    }
}
