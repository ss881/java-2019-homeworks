package items;

/*
 * 葫芦娃类。
 * 在Living的基础上新增排行。
 */
import field.*;

public class Calabash extends Living {
    private int order;
    public Calabash(Position pos, Field field_, int order_) {
        super(pos, field_);
        order=order_;
    }

    @Override
    public String toString(){
        return "["+order+"]";
    }

}
