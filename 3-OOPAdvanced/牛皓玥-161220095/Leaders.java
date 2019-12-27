import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Leaders extends Creature{
//    int r_cheer;                                //加油半径
//    int cheer;                                  //加油增加的生命值
    List<Creature> kids = new ArrayList<Creature>();   //领导的队伍
    int for_num;                                //阵型序号
    Formation formation;                        //队伍阵型
    int center_x;                               //阵型中心在地图中的位置 x
    final static int center_y = 10;             //阵型中心在地图中的位置 y（固定）


    Leaders(){
        this.pos_x = 0;
        Random r = new Random();
        this.for_num = r.nextInt(8);
    }

    //输出一方的生物位置坐标（也就是数组编号）
    public void print_map_position(){
        for(Creature kid :kids){
            System.out.println("(" + kid.pos_x + "," + kid.pos_y + ")\t");
        }
        System.out.println("(" + this.pos_x + "," + this.pos_y + ")\t");
    }

    //随机选择阵型序号
    public int random_choose_formation(int total_formation_num){
        Random seed = new Random();
        for_num = seed.nextInt(total_formation_num);
        while(for_num == 3)
            for_num = seed.nextInt(total_formation_num);
        return for_num;
    }

    //产生阵型
    public void genernate_formation(int for_num){
        this.for_num = for_num;
        this.formation = new Formation(for_num);
        //阵型位置填充
        for(int i=0;i<7;i++){
            this.formation.pos[i].creature = kids.get(i);
            kids.get(i).report();
        }
    }

    //将阵型摆入地图
    public void put_formation(Map map){
        for(int i=0;i<7;i++){
            int temp_x = this.center_x + formation.pos[i].pos_y;
            int temp_y = this.center_y + formation.pos[i].pos_x;
            map.positions[temp_x][temp_y].flag = true;
            map.positions[temp_x][temp_y].creature = kids.get(i);
            map.positions[temp_x][temp_y].creature.pos_x = map.positions[temp_x][temp_y].pos_x;
            map.positions[temp_x][temp_y].creature.pos_y = map.positions[temp_x][temp_y].pos_y;

        }
    }

    //进入地图
    public void put_self(Map map){
        int leader_x = this.center_x + this.pos_y;
        int leader_y = this.center_y + this.pos_x;
        map.positions[leader_x][leader_y].flag = true;
        map.positions[leader_x][leader_y].creature = this;
        map.positions[leader_x][leader_y].creature.pos_x = map.positions[leader_x][leader_y].pos_x;
        map.positions[leader_x][leader_y].creature.pos_y = map.positions[leader_x][leader_y].pos_y;
    }

    //撤回阵型
    public void retreat_formation(Map map){
        int x = this.center_x;
        int y = this.center_y;
        for(int i=0;i<7;i++){
            int temp_x = x + formation.pos[i].pos_y;
            int temp_y = y + formation.pos[i].pos_x;
            map.positions[temp_x][temp_y].flag = false;
        }
    }
}
