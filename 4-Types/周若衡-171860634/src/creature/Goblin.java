package creature;

public class Goblin extends BadCreature {

    public Goblin(){
        super();
    }
    public Goblin(final int x,final int y){
        super(x,y);
    }
    @Override
    public String toString(){
        return new String("喽啰");
    }
}
