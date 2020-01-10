import java.lang.Thread;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.lang.Math;
class fight implements Runnable{
    //private static int i = 0;
    private static int justice_num = 8;//葫芦娃阵营的数量：7+1
    private static int rival_num = 6;//妖精阵营数量
    private role r;
    private chessboard cb;
    public fight(role r,chessboard cb){
        this.r = r;
        this.cb = cb;
    }
    public role find_rival(){
         Random ran = new Random();
         int row = cb.rowof();
         int col = cb.colof();
         int i = ran.nextInt(row);
         int j = ran.nextInt(col);
         while(cb.ij_empty(i, j) || cb.who(i, j).get_justice() == r.get_justice()) {
              i = ran.nextInt(row);
              j = ran.nextInt(col);
         }
         role rival = cb.who(i,j);
         return rival;
    }
    public void goto_rival(role rival){
        while(!whether_next_to_rival(rival)){
            one_step_toward_rival(rival);
        }
    }
    public boolean whether_next_to_rival(role rival){
        double res = Math.pow(rival.row()-r.row(),2)+Math.pow(rival.col()-r.col(),2);
        double distance = Math.sqrt(res);
        if(distance<=1)
            return true;
        else
            return false;
    }
    public void one_step_toward_rival(role rival){
        int row = r.row();
        int col = r.col();
        if(row<rival.row())
        {
            r.goto_new_place(cb,row+1,col);
            return;
        }
        if(row>rival.row())
        {
            r.goto_new_place(cb,row-1,col);
            return;
        }
        if(col<rival.col())
        {
            r.goto_new_place(cb,row,col+1);
            return;
        }
        if(col>rival.col())
        {
            r.goto_new_place(cb,row,col-1);
            return;
        }
    }
    public void run(){
        while(r.get_blood()>0&&rival_num>0&&justice_num>0){//直到一方没有生物停下
            role rival = find_rival();
            goto_rival(rival);
            attack_with_rival(rival);
        }
        //System.out.print(i++);
    }
    public void attack_with_rival (role rival){
        int attack = r.get_attack();
        int blood = r.get_blood();
        int rival_attack= rival.get_attack();
        int rival_blood=rival.get_blood();
        while(blood>0&&rival_blood>0){
            rival_blood = rival_blood-attack;
            if(rival_blood<=0) break;
            blood = blood - rival_attack;
        }
        if(blood<=0){
            r.die(cb);
            justice_num--;//己方阵亡一个
            r.set_blood(0);
        }
        else
            r.set_blood(blood);
        if(rival_blood<=0){
            rival.die(cb);
            rival_num--;//敌人阵亡一个
            rival.set_blood(0);
        }
        else
            rival.set_blood(rival_blood);
    }
}
public class multithread {
    public static void main(String[] args)throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> huluwaclass = Huluwa.class;//reflection机制
        Constructor con = huluwaclass.getConstructor(String.class,int.class);//获取构造器
        Huluwa[] huluwas = new Huluwa[7];
        //根据构造器创建葫芦娃实例
        huluwas[0] = (Huluwa)con.newInstance("大娃",1);//huluwas[0]= new Huluwa("大娃",1);
        huluwas[1] = (Huluwa)con.newInstance("二娃",2);//huluwas[1]= new Huluwa("二娃",1);
        huluwas[2] = (Huluwa)con.newInstance("三娃",3);//huluwas[2]= new Huluwa("三娃",3);
        huluwas[3] = (Huluwa)con.newInstance("四娃",4);//huluwas[3]= new Huluwa("四娃",4);
        huluwas[4] = (Huluwa)con.newInstance("五娃",5);//huluwas[4]= new Huluwa("五娃",5);
        huluwas[5] = (Huluwa)con.newInstance("六娃",6);//huluwas[5]= new Huluwa("六娃",6);
        huluwas[6] = (Huluwa)con.newInstance("七娃",7);//huluwas[6]= new Huluwa("七娃",7);
        //reflection机制创建棋盘实例
        Class<?> chessboardclass = chessboard.class;
        Constructor con_board = chessboardclass.getConstructor(int.class,int.class);
        chessboard board = (chessboard)con_board.newInstance(11,11);
        //chessboard board = new chessboard(11,11);
        //reflection机制创建妖怪实例
        Class monsterclass = monster.class;
        Constructor mon_board = monsterclass.getConstructor(String.class,String.class);
        monster[] monsters = new monster[5];
        monsters[0] = (monster)mon_board.newInstance("蝎精","蝎子");//monsters[0]=new monster("蝎精","蝎子");
        monsters[1] = (monster)mon_board.newInstance("喽啰","兔子");//monsters[1]=new monster("喽啰","兔子");
        monsters[2] = (monster)mon_board.newInstance("喽啰","土拨鼠");//monsters[2]=new monster("喽啰","土拨鼠");
        monsters[3] = (monster)mon_board.newInstance("喽啰","蚊子");//monsters[3]=new monster("喽啰","蚊子");
        monsters[4] = (monster)mon_board.newInstance("喽啰","乌龟");//monsters[4]=new monster("喽啰","乌龟");
        //reflection机制创建蛇精和爷爷实例
        monster monster_boss = (monster)mon_board.newInstance("蛇精","蛇");//monster monster_boss = new monster("蛇精","蛇");
        Class humanclass = human.class;
        Constructor human_con = humanclass.getConstructor(String.class,boolean.class);
        human yeye = (human)human_con.newInstance("爷爷",true);//human yeye = new human("爷爷",true);
        homework4 static_work = new homework4();
        static_work.static_arrange(board,huluwas,monsters,monster_boss,yeye);

        Random ran = new Random();
        for(Huluwa huluwa : huluwas)
        {
            int attack = 1+ran.nextInt(5);//1-5
            huluwa.set_attack(attack);
            int blood = 20+ran.nextInt(11);//20-30
            huluwa.set_blood(blood);
        }
        for(monster mon : monsters){
            int attack = 1+ran.nextInt(5);//1-5
            mon.set_attack(attack);
            int blood = 20+ran.nextInt(11);//20-30
            mon.set_blood(blood);
        }
        yeye.set_attack(0);
        yeye.set_blood(100);
        int attack = 5+ran.nextInt(5);//6-10
        monster_boss.set_attack(attack);
        int blood = 40+ran.nextInt(21);//40-60
        monster_boss.set_blood(blood);
        board.print_board();

        for(Huluwa huluwa : huluwas)
            new Thread(new fight(huluwa,board)).run();
        new Thread(new fight(yeye,board)).run();
        board.print_board();
    }
}
