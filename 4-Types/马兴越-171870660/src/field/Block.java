package field;

import items.Living;
/**
 * Field上的一个地块，可以承载至多一个Living。
 */
public class Block {
    private Living living;
    private final int x,y;  //blank final
    //package-access
    Block(int x,int y){
        this.x=x;
        this.y=y;
    }

    public boolean hasLiving(){
        return living!=null;
    }

    public void setLiving(Living l){
        living=l;
    }

    public Living getLiving(){
        return living;
    }

    public void removeLiving(){
        living=null;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }
}
