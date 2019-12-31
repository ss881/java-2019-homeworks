package creature;

import map.GameMap;
import map.Position;
import map.Tile;

//生命体类
public class Creature{
    //生命体位置
    public Tile curTile;
    //生命体是否为好的一方（葫芦娃与爷爷一方）
    public boolean badGuy;
    Creature(boolean badGuy){
        this.curTile=null;
        this.badGuy=badGuy;
    }
    //生命体报出自己的名字（反射机制）
    public void solute(){
        if(this instanceof CalabashBrother)
            System.out.print("葫芦娃");
        else if(this instanceof Grandpa)
            System.out.print("爷爷");
        else if(this instanceof ScorpionSperm)
            System.out.print("蝎子精");
        else if(this instanceof BadFollower)
            System.out.print("小喽啰");
        else
            System.out.print("未知生物");
    }
    //生命体查看[a,b]位置是否可达，如果可达则进行移动
    public boolean tryMove(Position dst){
        if(GameMap.outOfRange(dst))
            return false;   //超出地图范围
        else if(curTile!=null&&curTile.position.equals(dst))
            return false;   //处于目的地，因此不需要移动
        else if(GameMap.hasCreature(dst))
            return false;   //目的地位置被其他生物占领，无法进行移动
        else{
            if(curTile!=null){
                GameMap.battleField[curTile.position.x][curTile.position.y].creature=null;
                curTile=null;
            }
            GameMap.battleField[dst.x][dst.y].creature=this;
            curTile=GameMap.battleField[dst.x][dst.y];
            return true;
        }
    }
}