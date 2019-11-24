package items;

/*
 * 葫芦娃类。
 * 在Living的基础上新增排行。
 */
import field.*;

public class Calabash extends Living {
    private int order;
    private String color;
    public Calabash(Position pos, Field field_, int order_, String color_) {
        super(pos, field_);
        order=order_;
        color=color_;
    }

    @Override
    public String toString(){
        return "["+color+"]";
    }

}
