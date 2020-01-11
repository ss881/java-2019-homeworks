package creature;

public class Elder extends GoodCreature {
    private static Elder elder;
    static {
        elder=new Elder();
    }
    public Elder(){
        super();
    }
    public Elder(final int x,final int y){
        super(x,y);
    }
    @Override
    public String toString(){
        return new String("爷爷");
    }

    public static Elder getElder(){
        return elder;
    }
}
