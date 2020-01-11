package creature;

public class CalabashBrother extends Creature
{
    private String color;
    private int order;

    public CalabashBrother(String name, int camp, String token, String color, int order)
    {
        super(name, camp, token);
        this.color = color; this.order = order;
    }

    public String getColor()
    {
        return color;
    }

    public int getOrder()
    {
        return order;
    }
}
