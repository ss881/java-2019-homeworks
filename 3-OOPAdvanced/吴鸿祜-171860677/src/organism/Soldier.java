package organism;

public class Soldier extends Organism{

    public Soldier()
    {
        this.name="😈";
    }

    public  Soldier(Soldier temp)
    {
        super(temp);
        this.name=temp.name;
    }
}
