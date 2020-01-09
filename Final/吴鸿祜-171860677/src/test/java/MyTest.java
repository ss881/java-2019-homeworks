import cn.whh.controller.Replay;
import cn.whh.creature.Creature;
import cn.whh.creature.Grandpa;
import cn.whh.creature.Huluwa;
import cn.whh.creature.Sodier;
import cn.whh.formation.Formation;
import cn.whh.formation.FormationGenerator;
import cn.whh.map.Field;
import javafx.scene.image.Image;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyTest {

    static ArrayList<Image> cache;

    @BeforeClass
    static public void testInit()
    {
        System.out.println("观察BeforeClass注解的用法");
        cache=new ArrayList<>();
        cache.add(new Image(MyTest.class.getResourceAsStream("/1.png")));
        System.out.println("老大的图片:"+cache.get(0));
        System.out.println();
    }

    private Replay init()
    {
        System.out.println("导入默认存档");
        ArrayList<Image> imageCache=new ArrayList<>();

        //String root="D:\\MyPrograme\\IDEAproject\\HuluwaGame\\src\\main\resources\\";
        for(int i=1;i<=7;i++)
        {
            imageCache.add(new Image(this.getClass().getResourceAsStream("/"+ Integer.toString(i)+".png")));
            imageCache.add(new Image(this.getClass().getResourceAsStream("/"+ Integer.toString(i)+"_dead.png")));
        }

        imageCache.add(new Image(this.getClass().getResourceAsStream("/soldier.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/soldier_dead.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/scorpion.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/scorpion_dead.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/grandPa.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/grandPa_dead.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/snake.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/snake_dead.png")));

        return new Replay(imageCache,new File("./src/main/resources/defaultSave.history"),13,10);
    }

    @Test
    public void printTest()
    {
        System.out.println("只是单纯测试一下打印");
        System.out.println();
    }

    @Test
    public void testReplay()
    {
        System.out.println("测试回放类Replay的反序列化是否正常进行，打印第一帧");
        Replay replay=init();
        FutureTask<ArrayList<Creature[][]>> futureTask=new FutureTask<ArrayList<Creature[][]>>(replay);
        Thread thread=new Thread(futureTask) ;
        thread.start();
        try{
            ArrayList<Creature[][]> historyField = futureTask.get();  //阻塞等待返回值
            System.out.println("总帧数:"+historyField.size());
            Creature[][] tmp=historyField.get(0);
            for(int i=0;i<10;i++)
            {
                for(int j=0;j<13;j++)
                {
                    System.out.print(tmp[i][j].getClass().getSimpleName()+" ");
                }
                System.out.println();
            }
            System.out.println();
        }catch (InterruptedException e)
        {
            System.out.println(e);
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFieldAdd()
    {
        System.out.println("测试地图类Field的添加生物的功能");
        Field field=new Field();

        System.out.println("添加5个士兵和1个爷爷");
        for(int i=0;i<5;i++) field.Add(i,2,new Sodier(field));
        field.Add(4,0,new Grandpa(field));

        for(int i=0;i<field.height;i++)
        {
            for(int j=0;j<field.width;j++)
            {
                System.out.print(field.Get(i,j).getClass().getSimpleName()+" ");
            }
            System.out.println();
        }

        System.out.println();
    }

    @Test
    public void testFormationFactory()
    {
        System.out.println("测试生成阵法的工厂类FormationGenerator");
        Field field=new Field();
        ArrayList<Creature> brothers=new ArrayList<>();
        for(int i=0;i<7;i++) brothers.add(new Huluwa(i+1,field));
        brothers.add(new Grandpa(field));
        FormationGenerator factory=new FormationGenerator();
        Formation formation=factory.produceFormation("鹤翼阵",brothers,0);
        Creature[][] tmp=formation.getCreatures();
        for(int i=0;i<field.height;i++)
        {
            for(int j=0;j<field.width;j++)
            {
                System.out.print(tmp[i][j].getClass().getSimpleName()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @AfterClass
    static public  void endTest()
    {
        System.out.println("结束了所有单元测试");
        System.out.println();
    }

    public void main(String[] args)
    {
        testReplay();
    }
}
