import java.lang.reflect.Method;
public class Master {
    static Old_man old_man;
    static Xiezi xiezi;
    static Snake snake;
    static Ground <Creature>ground;
    public void Creat(){
        ground=new Ground<Creature>(Creature.class,20);
    }
    public void Set_pos(){
        xiezi=new Xiezi(8,10,"蝎");
        ground.Register(xiezi);
        old_man=new Old_man(7,9,"爷");
        ground.Register(old_man);
        snake=new Snake(7,10,"蛇");
        ground.Register(snake);
    }
    public void Opposition1(){
        xiezi.Creat(ground);
        xiezi.Heyi(ground);

        old_man.Creat(ground);
        old_man.Longsnake(ground);

        ground.Print_all();
    }
    public void Opposition2(){
        System.out.println();
        xiezi.Yanxin(ground);
        ground.Print_all();
    }
    public static void main(String []args)throws Exception {
        /*Master master=new Master();
        master.Creat();
        master.Set_pos();

        master.Opposition1();
        master.Opposition2();*/

        Class<?> class1 = Master.class;
        Method method = class1.getMethod("Creat");
        method.invoke(class1.newInstance());

        method = class1.getMethod("Set_pos");
        method.invoke(class1.newInstance());

        method = class1.getMethod("Opposition1");
        method.invoke(class1.newInstance());

        method = class1.getMethod("Opposition2");
        method.invoke(class1.newInstance());
    }
}
