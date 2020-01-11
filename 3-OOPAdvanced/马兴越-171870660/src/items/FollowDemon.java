package items;

/*
 * 小喽啰。比其他的多一个编号。
 */
import field.*;
public class FollowDemon extends Living {
    private int order;

    public FollowDemon(Position pos, Field field_, int order) {
        super(pos, field_);
        this.order=order;
    }

    public String toString(){
        return "("+order+")";
    }

    @Override
    public boolean exchangeable(){
        return true;
    }
}
