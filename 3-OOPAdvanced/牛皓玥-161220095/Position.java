public class Position {
    int pos_x;                  //位置坐标 x
    int pos_y;                  //位置坐标 y
    boolean flag = false;       //位置上是否有生物
    Creature creature;          //生物

    Position(int x,int y){
        this.pos_x = x;
        this.pos_y = y;
    }
}

