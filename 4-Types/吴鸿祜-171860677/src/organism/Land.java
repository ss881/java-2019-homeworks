package organism;

public class Land extends Organism{
    public Land()
    {
        this.name="🌳";
    }

    public  Land(Land temp)
    {
        super(temp);
        this.name=temp.name;
    }
}
