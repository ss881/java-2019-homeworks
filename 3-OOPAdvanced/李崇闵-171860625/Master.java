public class Master {
    Old_man old_man;
    Xiezi xiezi;
    Snake snake;
    Ground ground;
    void Creat(){
        ground=new Ground(20);
    }
    void Set_pos(){

        xiezi=new Xiezi(8,10,"蝎");
        ground.Register(xiezi);
        old_man=new Old_man(7,9,"爷");
        ground.Register(old_man);
        snake=new Snake(7,10,"蛇");
        ground.Register(snake);
    }
    void Opposition1(){
        xiezi.Creat(ground);
        xiezi.Heyi(ground);

        old_man.Creat(ground);
        old_man.Longsnake(ground);


        ground.Print_all();
    }
    void Opposition2(){
        System.out.println();
        xiezi.Yanxin(ground);
        ground.Print_all();
    }
    public static void main(String []args){
        Master master=new Master();
        master.Creat();
        master.Set_pos();

        master.Opposition1();
        master.Opposition2();
    }

}
