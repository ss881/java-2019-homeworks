//阵型种类
enum FormationKind{
    CRANE,GOOSE,YOKE,SCALE,SQUARE,MOON,ARROW,SNAKE
}

//表示位置坐标
class Position{
    int x;
    int y;
    Position(int x,int y){
        this.x=x;
        this.y=y;
    }
}

//阵型类
class Formation {
    //阵型种类数目
    private static int FORMATION_NUM=8;
    private static int NUM=7;
    //存储阵型信息
    private static Position[][] preparedFormations=new Position[FORMATION_NUM][NUM];
    static void initFormation(){
        preparedFormations[0]=new Position[]{new Position(0,0),new Position(1,1),new Position(2,2),new Position(3,3),new Position(-1,1),new Position(-2,2),new Position(-3,3)};//鹤翼
        preparedFormations[1]=new Position[]{new Position(0,0),new Position(1,-1),new Position(2,-2),new Position(3,-3),new Position(-1,1),new Position(-2,2),new Position(-3,3)};//雁行
        preparedFormations[2]=new Position[]{new Position(0,0),new Position(-1,1),new Position(-2,0),new Position(-3,1),new Position(1,1),new Position(2,0),new Position(3,1)};//衡轭
        preparedFormations[3]=new Position[]{new Position(0,0),new Position(-1,1),new Position(-2,2),new Position(-3,3),new Position(0,2),new Position(-1,3),new Position(1,3)};//鱼鳞
        preparedFormations[4]=new Position[]{new Position(0,0),new Position(1,1),new Position(-1,1),new Position(2,2),new Position(-2,2),new Position(1,3),new Position(-1,3)};//方*
        preparedFormations[5]=new Position[]{new Position(0,0),new Position(-1,1),new Position(-2,2),new Position(-3,3),new Position(0,1),new Position(1,2),new Position(2,3)};//偃月
        preparedFormations[6]=new Position[]{new Position(0,0),new Position(-1,1),new Position(1,1),new Position(0,1),new Position(0,2),new Position(0,3),new Position(0,4)};//锋矢
        preparedFormations[7]=new Position[]{new Position(0,0),new Position(-1,0),new Position(-2,0),new Position(-3,0),new Position(1,0),new Position(2,0),new Position(3,0)};//长蛇
    }
    //变换阵型
    static void changeFormation(Tile[][] battleField,Creature[] creatures,FormationKind formation){
        switch (formation){
            case MOON:{System.out.println("偃月阵");break;}
            case CRANE:{System.out.println("鹤翼阵");break;}
            case GOOSE:{System.out.println("雁行阵");break;}
            case YOKE:{System.out.println("衡軛阵");break;}
            case SCALE:{System.out.println("鱼鳞阵");break;}
            case SQUARE:{System.out.println("方阵");break;}
            case ARROW:{System.out.println("箭矢阵");break;}
            case SNAKE:{System.out.println("长蛇阵");break;}
        }
        Position center;
        if(creatures[0].badGuy)
            center=new Position((GameMap.N-1)/2,GameMap.N-6);
        else
            center=new Position((GameMap.N-1)/2,2);
        int dstX,dstY;
        for(int i=0;i<NUM;i++){
            dstX=preparedFormations[formation.ordinal()][i].x+center.x;
            dstY=preparedFormations[formation.ordinal()][i].y+center.y;
            if(creatures[i].canMove(battleField,dstX,dstY) || creatures[i].x==-1)
                creatures[i].runTo(battleField, dstX, dstY);
            else
                swap(battleField,creatures[i].x,creatures[i].y,dstX,dstY);
        }
    }
    //两个生命体交换位置
    private static void swap(Tile[][] battleField,int x1,int y1,int x2,int y2){
        if(x1==x2&&y1==y2)
            return;
        Position temp=null;
        //寻找一个空闲位置实现位置交换
        for(int i=0;i<battleField.length;i++){
            for(int j=0;j<battleField.length;j++){
                if(battleField[i][j].creature==null) {
                    temp = new Position(i, j);
                    break;
                }
            }
        }
        battleField[x1][y1].creature.runTo(battleField,temp.x,temp.y);
        battleField[x2][y2].creature.runTo(battleField,x1,y1);
        battleField[temp.x][temp.y].creature.runTo(battleField,x2,y2);
    }
}
