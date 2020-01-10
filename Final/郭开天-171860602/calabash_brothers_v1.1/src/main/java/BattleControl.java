import java.util.concurrent.*;
public class BattleControl{
    public static void main(String[]args)throws Exception{
        BattleField battle=battleInit();
        battleStart(battle);
        replyStart();
        System.exit(0);
    }
    public static void go() throws Exception{
        BattleField battle=battleInit();
        battleStart(battle);
        //replyStart();
        return;
    }
    public static void rego() throws Exception{
        replyStart();
        return;
    }
    public static BattleField battleInit()throws Exception{
        BattleField battle=new BattleField(7,7);
        Formation form1=new Formation(0);
        Creature[]g0={new Creature(0,"dw"),new Creature(1,"ew"),new Creature(2,"sw"),
            new Creature(3,"si"),new Creature(4,"ww"),new Creature(5,"lw"),new Creature(6,"qw")};
        Creature[]g1={new Creature(0,"bf"),new Creature(1,"wg"),new Creature(2,"xz"),
            new Creature(3,"hm"),new Creature(4,"sj"),new Creature(5,"wg"),new Creature(6,"bf")};
        battle.fieldInit(form1,form1,g0,g1);
        return battle;
    }
    public static void battleStart(BattleField battle)throws Exception{        
        ExecutorService exec=Executors.newCachedThreadPool();
        battle.printField();
        
        for(int i=0;i<battle.getG0length();i++)
            exec.execute(battle.getG0(i));
        for(int i=0;i<battle.getG1length();i++)
            exec.execute(battle.getG1(i));

        Future<Boolean> future = exec.submit(battle.awaker);
        future.get();
        //battle.printField();
        exec.shutdown();
        System.out.println("done");
        Thread.sleep(1000);
        battle.write.close();
    }
    public static BattleField replyStart()throws Exception{
        BattleField battle=new BattleField("test");
        battle.printField();
        return battle;
    }
}