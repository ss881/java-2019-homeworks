package creature;

public class Scorpion extends BadCreature{
    private static Scorpion scorpion;
    static{
        scorpion=new Scorpion();
    }

    public Scorpion(){
        super();
    }
    public Scorpion(final int x,final int y){
        super(x,y);
    }
    @Override
    public String toString(){
        return new String("蝎子");
    }

    public Scorpion getScorpion(){
        return scorpion;
    }
}
