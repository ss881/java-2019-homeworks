package items;
import field.*;
public class PassedFlag extends Living{
    private static int number;
    static {
        number=0;
    }
    private int order;
    public PassedFlag(Position pos, Field field_) {
        super(pos, field_);
        order=number++;
    }

    @Override
    public String toString(){
        return "F"+order;
    }
}
