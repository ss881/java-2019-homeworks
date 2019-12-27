package field;/*
 * 用于维护生物的位置。作为生物的构成部分。
 */

import static java.lang.Math.*;

public class Position {
    private int x,y;

    public class Direction{
        public static final int E=0,NE=1,N=2,NW=3,W=4,SW=5,S=6,SE=7;
        private int dir;

        //inner class
        public Direction(Position another){
            int dx=another.getX()-getX();
            int dy=another.getY()-getY();
            if(dx>0){
                if(dy<0)
                    dir=NE;
                else if(dy==0)
                    dir=E;
                else
                    dir=SE;
            }
            else if(dx<0){
                if(dy<0)
                    dir=NW;
                else if(dy==0)
                    dir=W;
                else
                    dir=SW;
            }
            else{
                if(dy<0)
                    dir=N;
                else
                    dir=S;
            }
        }

        public Direction(int dir_){
            dir=dir_;
        }

        public int dx(){
            switch (dir){
                case N: case S:return 0;
                case SW: case W: case NW:return -1;
                case SE: case E: case NE:return 1;
                default:return 0;//静默处理
            }
        }

        public int dy(){
            switch (dir){
                case W: case E:return 0;
                case N: case NE: case NW:return -1;
                case S: case SE: case SW:return 1;
                default:return 0;
            }
        }

        /*
         * 按顺序找下一个方向。
         */
        public void next(){
            dir=(dir+1)%8;
        }

        /*
         * 返回当前Direction所示方向的下一个位置，而不检查有效性。
         * 创建新对象。
         */
        public Position adjacentPosition(){
            return new Position(x+dx(),y+dy());
        }

        /*
         * 移动到direction所示方向的下一个位置，改变当前对象。
         * 不检查有效性。
         */
        public void aStep(){
            x=x+dx();
            y=y+dy();
        }
    }

    public Position(int _x,int _y){
        x=_x;
        y=_y;
    }

    public void setPos(int _x,int _y){
        x=_x;
        y=_y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /*
     * 返回两个位置在8邻域下是否相邻。
     */
    public boolean adjacentWith(Position another){
        return abs(another.x-x)<=1 && abs(another.y-y)<=1;
    }

    public boolean equals(Position another){
        return another.x==x && another.y==y;
    }

//    public Position adjacentPosition(Direction dir){
//        return new Position(x+dir.dx(),y+dir.dy());
//    }

    public String toString(){
        return "("+x+", "+y+")";
    }

    public Position copy(){
        return new Position(x,y);
    }

}
