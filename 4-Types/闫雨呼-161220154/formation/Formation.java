package formation;
import map.*;
import creature.Creature;
import god.God;


//阵型类
public class Formation {
    //存储阵型信息
    private static Position[][] formations=new Position[8][];
    static{
        formations[0]=new Position[]{new Position(0,0),new Position(1,1),new Position(2,2),
                new Position(3,3),new Position(-1,1),new Position(-2,2),new Position(-3,3)};//鹤翼
        formations[1]=new Position[]{new Position(0,0),new Position(1,-1),new Position(2,-2),
                new Position(-1,1),new Position(-2,2)};//雁行
        formations[2]=new Position[]{new Position(0,0),new Position(-1,1),new Position(-2,0),
                new Position(-3,1),new Position(1,1),new Position(2,0)};//衡轭
        formations[3]=new Position[]{new Position(0,0),new Position(-1,1),new Position(-2,2),
                new Position(-3,3),new Position(0,2),new Position(0,4),new Position(0,6),
                new Position(-1,3),new Position(-1,5),new Position(1,3)};//鱼鳞
        formations[4]=new Position[]{new Position(0,0),new Position(1,1),new Position(-1,1),
                new Position(2,2),new Position(-2,2),new Position(1,3),new Position(-1,3),
                new Position(0,4)};//方円
        formations[5]=new Position[]{new Position(-1,-4),new Position(0,-4),new Position(1,-4),
                new Position(-1,-2),new Position(0,-2),new Position(1,-2),new Position(-2,-1),
                new Position(2,-1),new Position(-3,0),new Position(-1,0),new Position(0,0),
                new Position(1,0),new Position(3,0),new Position(-2,1),new Position(2,1),
                new Position(-3,2),new Position(3,2),new Position(-4,3),new Position(4,3)};//偃月
        formations[6]=new Position[]{new Position(0,0),new Position(-1,1),new Position(1,1),
                new Position(0,1),new Position(-2,2),new Position(2,2),new Position(0,2),
                new Position(3,3),new Position(-3,3),new Position(0,3),new Position(0,4),
                new Position(0,5)};//锋矢
        formations[7]=new Position[]{new Position(0,0),new Position(-1,0),new Position(-2,0),
                new Position(-3,0),new Position(1,0),new Position(2,0)};//长蛇
    }
    //变换阵型
    public static void changeFormation(Creature[] creatures,FormationKind formation){
        if(creatures[0].badGuy)
            System.out.print("小喽啰：");
        else
            System.out.print("葫芦娃：");
        switch (formation){
            case MOON:{System.out.println("偃月阵");break;}
            case CRANE:{System.out.println("鹤翼阵");break;}
            case GOOSE:{System.out.println("雁行阵");break;}
            case YOKE:{System.out.println("衡軛阵");break;}
            case SCALE:{System.out.println("鱼鳞阵");break;}
            case SQUARE:{System.out.println("方円阵");break;}
            case ARROW:{System.out.println("箭矢阵");break;}
            case SNAKE:{System.out.println("长蛇阵");break;}
        }
        Position[] curFormation=formations[formation.ordinal()];
        Position center;
        if(creatures[0].badGuy)
            center=new Position((GameMap.N-1)/2,findBadGuyCenterY(curFormation));
        else
            center=new Position((GameMap.N-1)/2,(GameMap.N-1)/4);
        Position dst=new Position(-1,-1);
        for(int i=0;i<curFormation.length;i++){
            dst.set(curFormation[i].x+center.x,curFormation[i].y+center.y);
            if(!creatures[i].tryMove(dst) && creatures[i].curTile!=null){
                try {
                    swap(creatures[i].curTile.position, dst);
                }catch (NullPointerException nex){
                    System.out.println("空指针错误");
                    System.exit(-1);
                }
            }
        }
        if(creatures[0].badGuy){
            for(int i=curFormation.length;i<God.FOLLOWERS_NUM;i++){
                if(creatures[i].curTile==null)
                    continue;
                creatures[i].curTile.creature=null;
                creatures[i].curTile=null;
            }
        }
    }
    //两个生命体交换位置
    private static void swap(Position pos1,Position pos2) throws NullPointerException{
        if(pos1.equals(pos2))
            return;
        Position temp=null;
        //寻找一个空闲位置实现位置交换
        for(int i=0;i<GameMap.battleField.length;i++){
            for(int j=0;j<GameMap.battleField.length;j++){
                if(GameMap.battleField[i][j].creature==null) {
                    temp = new Position(i, j);
                    break;
                }
            }
        }
        if(temp==null)
            throw new NullPointerException();
        GameMap.battleField[pos1.x][pos1.y].creature.tryMove(temp);
        GameMap.battleField[pos2.x][pos2.y].creature.tryMove(pos1);
        GameMap.battleField[temp.x][temp.y].creature.tryMove(pos2);
    }
    private static int findBadGuyCenterY(Position[] formation){
        int ymin=10,ymax=-10;
        for(Position p:formation){
            if(p.y<ymin)
                ymin=p.y;
            if(p.y>ymax)
                ymax=p.y;
        }
        int start=(GameMap.N-1)/2,end=(GameMap.N-1);
        for(int i=start;i<=end;i++){
            if(i+ymin>=(GameMap.N-1)/2&&i+ymax<=GameMap.N){
                return i;
            }
        }
        return 0;
    }
}
