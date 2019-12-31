public class Formation {
    static String[] formation_name_list = {"鹤翼","雁行","衡轭","长蛇","鱼鳞","方円","偃月","锋矢"};
    Position[][] pos_list = new Position[][]{
            {new Position(0,0),new Position(-1,1),new Position(1,1),new Position(-2,2),new Position(2,2),new Position(-3,3),new Position(3,3)},
            {new Position(0,0),new Position(-1,-1),new Position(1,1),new Position(-2,-2),new Position(2,2),new Position(-3,-3),new Position(3,3)},
            {new Position(0,0),new Position(0,1),new Position(0,-1),new Position(1,1),new Position(1,-1),new Position(1,2),new Position(1,-2)},
            {new Position(0,0),new Position(0,1),new Position(0,-1),new Position(0,2),new Position(0,-2),new Position(0,3),new Position(0,-3)},
            {new Position(0,0),new Position(0,2),new Position(0,-1),new Position(-2,0),new Position(2,0),new Position(-1,1),new Position(1,1)},
            {new Position(0,0),new Position(-1,-1),new Position(1,-1),new Position(-2,-2),new Position(2,-2),new Position(-1,-3),new Position(1,-3)},
            {new Position(0,0),new Position(1,0),new Position(1,1),new Position(1,-1),new Position(2,0),new Position(2,2),new Position(2,-2)},
            {new Position(0,0),new Position(-1,1),new Position(1,1),new Position(0,-1),new Position(0,-2),new Position(0,1),new Position(0,2)}
    };
    final static int num = 7;               //阵型中的生物数

    String name;                            //阵型名称
    Position[] pos = new Position[num];     //阵型中的生物位置坐标
    //int space = 1;                          //阵型中生物间距,单位间隔，默认为1
    //int for_pos_x = 0;                      //阵型在地图中的位置 x (默认为在地图中部展开)
    //int for_pos_y = 6;                      //阵型在地图中的位置 y (默认为喽啰阵型的位置)
    //boolean flag;                           //阵营标识（葫芦娃/小喽啰）(喽啰为true,葫芦娃为false)
    /**
     int pos_leader_x;                       //领导位置 x
     int pos_leader_y;                       //领导位置 y
     **/

    Formation(int id){
        this.name = formation_name_list[id];
        this.pos = pos_list[id];
    }

}
