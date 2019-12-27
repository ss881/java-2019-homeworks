package items;
/*
 * 老人家类。负责“指挥”葫芦娃，维护葫芦娃列表。
 */

import field.*;

public class Elder extends Living {
    private Calabash[] calabashes;

    /*
     * 老人家“种”出了葫芦娃
     * 随机产生位置并添加葫芦娃。
     */
    public Elder(Position pos, Field field_) {
        super(pos, field_);
        calabashes=new Calabash[7];
        for(int i=0;i<7;i++){
//            field.Position position;
//            if(i!=0)
//                position=field_.randomPosition();
//            else
//                position=new field.Position(5,1);
            Position position=field_.leftRandomPosition();
            Calabash cal=new Calabash(position,field_,i+1);
            calabashes[i]=cal;
            field_.addLiving(cal);
        }
    }

    /*
     * 按“长蛇阵”排列。指定老大在一个位置（保证他下面足以排够其他的人），
     * 然后依次指挥其他葫芦娃到下面的位置来。
     */
    public void standAsSnake(){
        for (Calabash c:calabashes){
            c.setMovable(true);
        }
        while (Field.N-calabashes[0].getPosition().getY()<7) {
            calabashes[0].moveOrSwap(0,-1);
        }
        calabashes[0].setMovable(false);
        Position pos=calabashes[0].getPosition().copy();
        for(int i=1;i<7;i++){
            pos.setPos(pos.getX(),pos.getY()+1);
            calabashes[i].walkTowards(pos);
//            assert calabashes[i].getPosition().equals(pos);
            calabashes[i].setMovable(false);
        }
    }

    @Override
    public String toString(){
        return "LRJ";
    }
}
