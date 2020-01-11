import java.util.Random;
class role{
    private String name;//名字
    private boolean justice;//善恶阵营
    private int place_row;//所在行
    private int place_col;//所在列
    private int attack;
    private int blood;
    public void set_attack(int attack){
        this.attack=attack;
    }
    public void set_blood(int blood){
        this.blood = blood;
    }
    public void die(chessboard cb){
        cb.ij_die(place_row,place_col);
    }
    public boolean get_justice(){return justice;}
    public int get_attack(){return attack;}
    public int get_blood(){return blood;}
    public role(){//默认是金刚小葫芦
        name = "金刚小葫芦";
        justice = true;
    }
    public role(String name,boolean justice){
        this.name = name;
        this.justice = justice;
    }
    public void set_place(chessboard board,int row,int col){
        this.place_row=row;
        this.place_col=col;
    }
    public void jump_to_place(chessboard board,int row,int col){//设置地址
        board.one_change_place(this,place_row,place_col,row,col);
        this.place_row=row;
        this.place_col=col;
    }
    private boolean one_step_left(chessboard board){
        if(place_col<=0)
            return false;
        else if(!board.ij_empty(place_row,place_col-1))
            return false;
        else{
            board.one_change_place(this,place_row,place_col,place_row,place_col-1);
            place_col--;
            return true;
        }
    }
    private boolean one_step_right(chessboard board){
        if(place_col>=board.colof()-1)
            return false;
        else if(!board.ij_empty(place_row,place_col+1))
            return false;
        else{
            board.one_change_place(this,place_row,place_col,place_row,place_col+1);
            place_col++;
            return true;
        }
    }
    private boolean one_step_up(chessboard board){
        if(place_row<=0)
            return false;
        else if(!board.ij_empty(place_row-1,place_col))
            return false;
        else{
            board.one_change_place(this,place_row,place_col,place_row-1,place_col);
            place_row--;
            return true;
        }
    }
    private boolean one_step_down(chessboard board){
        if(place_row>=board.rowof()-1)
            return false;
        else if(!board.ij_empty(place_row+1,place_col))
            return false;
        else{
            board.one_change_place(this,place_row,place_col,place_row+1,place_col);
            place_row++;
            return true;
        }
    }
    public void goto_new_place(chessboard board,int new_row,int new_col){//去指定地址
        role another_role=null;
        int r_row=0,r_col=0;
        while(place_row<new_row){//在下方
            boolean down = one_step_down(board);
            if(!down) {//下面被人挡住了
                int p_row = place_row+1,p_col=place_col;
                another_role = board.who(p_row, p_col);
                boolean give_way = ask_for_give_way(another_role, board);
                if (!give_way)//不被让路
                    jump_to_place(board,new_row,new_col);//就自己翻个跟头跳过去吧
                else {
                    down = one_step_down(board);
                }
            }
        }
        while(place_row>new_row){//在上方
            boolean up = one_step_up(board);
            if(!up) {//上面被人挡住了
                int p_row = place_row-1,p_col=place_col;
                another_role = board.who(p_row , p_col);
                boolean give_way = ask_for_give_way(another_role, board);
                if (!give_way)//不被让路
                    jump_to_place(board,new_row,new_col);//就自己翻个跟头跳过去吧
                else {
                    up = one_step_up(board);
                }
            }
        }
        while(place_col<new_col){//在右边
            boolean right = one_step_right(board) ;
            if(!right) {//右边被人挡住了
                int p_row=place_row,p_col=place_col+1;
                another_role=board.who(p_row,p_col);
                boolean give_way = ask_for_give_way(another_role, board);
                if (!give_way)//不被让路
                    jump_to_place(board,new_row,new_col);//就自己翻个跟头跳过去吧
                else {
                    right = one_step_right(board);
                }
            }
        }
        while(place_col>new_col){//在左边
            boolean left = one_step_left(board);
            if(!left){
                int p_row=place_row,p_col=place_col-1;
                another_role = board.who(p_row,p_col);
                boolean give_way = ask_for_give_way(another_role,board);
                if(!give_way)
                    jump_to_place(board,new_row,new_col);
                else
                {
                    left = one_step_left(board);
                }
            }
        }
    }
    public boolean ask_for_give_way(role anotherrole,chessboard board) {//询问是否另一个角色能否让位
        if (anotherrole.give_way(this, board))
            return true;
        else
            return false;
    }
    public boolean give_way(role other,chessboard board){//回答自己是否愿意让位置
        if(other.whether_good()==justice)//同一阵营才会让位，敌对阵营的不会让位
            return one_step_left(board)||one_step_right(board)||one_step_up(board)||one_step_down(board);//是否有位置可以让
        else
            return false;
    }
    public String print_name(){
        return name;
    }
    public boolean whether_good(){
        return justice;
    }
    public int row(){return this.place_row;};
    public int col(){return this.place_col;};
}
class Huluwa extends role {//葫芦娃
    private int rank;//排行
    public Huluwa(String name, int rank) {
        super(name, true);
        this.rank = rank;
    }
    public int print_rank() {
        return rank;
    }
}
class monster extends role {//妖精
    private String nature;//妖精的本体比如蝎子、蛇
    public monster(String name,String nature) {
        super(name, false);
        this.nature=nature;
    }
    public monster() {//默认是小妖精
        super("小妖精", false);
    }
    public String natureof(){
        return nature;
    }
}
class human extends role{//人类
    private boolean gender;//性别
    public human(String name,boolean gender){
        super(name,true);
        this.gender=gender;
    }
    public boolean whether_male(){
        return gender;
    }
}
class chessboard<T extends lineup,K extends role>{//棋盘
    private int total_row;
    private int total_col;
    private role[][] board;
    private T t;//阵型抽象类的派生类
    private K[] k;//角色数组
    private boolean[][] board_empty;
    public void ij_die(int row,int col){
        board_empty[row][col]=true;
        board[row][col]=null;
    }
    public chessboard(int row,int col){
        this.total_row=row;
        this.total_col=col;
        board= new role[total_row][total_col];
        board_empty = new boolean[total_row][total_col];
        for(int i =0;i<row;i++)
            for(int j =0;j<col;j++){
                board_empty[i][j]=true;
            }
    }
    public int rowof(){
        return total_row;
    }
    public int colof(){
        return total_col;
    }
    public void random_put_one_role (role r){//开局的乱序放置，相当于刷新在随机位置
        Random ran = new Random();
        int s,t;
        do{
            s = ran.nextInt(total_row);
            t = ran.nextInt(total_col);
        }
        while(!board_empty[s][t]);
        r.set_place(this,s,t);
        put_role(r,s,t);
    }
    public void put_role(role r,int row,int col){
        if(board_empty[row][col])
        {
            board[row][col] = r;
            board_empty[row][col]=false;
        }
    }
    public boolean ij_empty(int row,int col){
        return board_empty[row][col];
    }
    public role who(int row,int col){
        return board[row][col];
    }
    public void one_change_place(role r,int p_row,int p_col,int new_row,int new_col) {
        if (p_row != new_row || p_col != new_col) {
            while (!board_empty[new_row][new_col]) {
                role temp = board[new_row][new_col];
                board[new_row][new_col] = null;
                board_empty[new_row][new_col] = true;
                this.random_put_one_role(temp);
            }
            if (board_empty[new_row][new_col]) {
                board[p_row][p_col] = null;
                board_empty[p_row][p_col] = true;
                board[new_row][new_col] = r;
                board_empty[new_row][new_col] = false;
            }
        }
    }
    public void print_board(){
        for(int i = 0;i<total_row;i++)
        {
            for(int j = 0;j<total_col;j++){
                //jg.drawLine(60*i,60*j,60*(i+1),60*j);
                if(board_empty[i][j]){
                    System.out.print("   ");
                }
                else{
                    System.out.print(board[i][j].print_name()+" ");
                }
            }
            System.out.println();
            //jg.drawLine(60*i,0,60*i,60*10);
        }
    }
    public void arrange_board(T t,K[] k){//泛型
        this.t = t;
        this.k=k;
        this.t.put_roles(this,this.k);
    }
}
abstract class lineup{
    public abstract void put_roles(chessboard board,role[] roles);
}
class random_linup extends lineup{//乱序阵型
    public void put_roles(chessboard board,role[] roles){
        for(role r:roles){
            board.random_put_one_role(r);
        }
    }
}
class snake_lineup extends lineup{//长蛇阵
    public void put_roles(chessboard board,role[] roles){
        for(role r:roles){
            int new_row = 0;
            switch(r.print_name()){
                case "大娃":new_row = 0;break;
                case "二娃":new_row = 1;break;
                case "三娃":new_row = 2;break;
                case "四娃":new_row = 3;break;
                case "五娃":new_row = 4;break;
                case "六娃":new_row = 5;break;
                case "七娃":new_row = 6;break;
                default:new_row = 7;break;
            }
            r.goto_new_place(board,new_row,0);
        }
    }
}
class anser_fly_lineup extends lineup{//雁行
    public void put_roles(chessboard board,role[]roles){
        int gap =0;
        int col = board.colof();
        for(role r:roles){
            if(r.print_name()=="喽啰"){
                r.goto_new_place(board,0+gap,col-1-gap);
                gap++;
            }
            else{//蝎子精
                r.goto_new_place(board,4,col-1-4);
            }
        }
    }
}
class crane_wing extends lineup {//鹤翼
    public void put_roles(chessboard board, role[] roles) {
        int num = 1;
        for(role r:roles){
            if(r.print_name()=="喽啰"){
                switch(num){
                    case 1:r.goto_new_place(board,0,board.colof()-5);num++;break;
                    case 2:r.goto_new_place(board,0,board.colof()-1);num++;break;
                    case 3:r.goto_new_place(board,1,board.colof()-2);num++;break;
                    case 4:r.goto_new_place(board,1,board.colof()-4);num++;break;
                }
            }
            else{
                r.goto_new_place(board,2,board.colof()-3);
            }
        }
    }
}
public class homework4{
    public void static_arrange(chessboard board,Huluwa[] huluwas,monster[] monsters,monster monster_boss,human yeye){

        //随机排列
        random_linup random = new random_linup();
        //random.put_roles(board,huluwas);
        board.arrange_board(random,huluwas);//改用泛型
        //random.put_roles(board,monsters);
        board.arrange_board(random,monsters);//改用泛型
        //board.print_board();//乱序的输出
        //System.out.println();

        //长蛇阵摆好
        snake_lineup snake = new snake_lineup();
        //snake.put_roles(board,huluwas);
        board.arrange_board(snake,huluwas);//改用泛型
        //board.print_board();
        //System.out.println();

        //雁行阵摆好
        anser_fly_lineup anser = new anser_fly_lineup();
        //anser.put_roles(board,monsters);
        board.arrange_board(anser,monsters);//改用泛型

        board.random_put_one_role(monster_boss);
        board.random_put_one_role(yeye);
        monster_boss.jump_to_place(board,5,5);//放蛇精
        yeye.jump_to_place(board,3,1);//放爷爷
        //board.print_board();//输出*/
        //System.out.println();

        /*
        //鹤翼阵
        crane_wing cyane = new crane_wing();
       // cyane.put_roles(board,monsters);
        board.arrange_board(cyane,monsters);

        monster_boss.jump_to_place(board,3,8);
        yeye.jump_to_place(board,3,1);
        board.print_board();
        */
    }
}
