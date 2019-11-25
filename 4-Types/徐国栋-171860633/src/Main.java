import creature.*;
import gamectrl.Ground2D;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        int N=13;
        ReflectionTester tester=new ReflectionTester();// 测试反射
        GrandPa papa = new GrandPa();
        ScorpionSperm xz = new ScorpionSperm();
        // 通过反射获取蝎子精拥有的方法
        tester.displayMethod("creature.ScorpionSperm");
        SnakeEssence ss = new SnakeEssence();
        Ground2D ground = new Ground2D(N,N);

        // 场景一：蛇精、老爷爷进场，葫芦娃乱序排队
        System.out.println("（场景 0）：蛇精、老爷爷进场，葫芦娃乱序排队");
        papa.callCalabashBrothers();
        papa.sayComeOn();
        // 人物调动完成
        ground.clearMap();
        ground.acceptMove(papa);
        ground.acceptMove(ss);
        ground.acceptMove(xz);
        ground.acceptMove(papa.getCalabashBrothers(), 7);
        ground.displayCli();
        // 场景绘制完成
        // 通过反射获取地图上的人物计数
        tester.countThroughReflection(ground.getGrids(),N,N);

        // 其它场景：蛇精、老爷爷在场，葫芦娃保持长蛇阵，蝎子精和小喽啰变换阵型
        for (int i = 1; i <= 7; i++) {
            System.out.println("（场景 " + i + "）: 蛇精、老爷爷在场，葫芦娃保持长蛇阵，蝎子精和小喽啰变换阵型");
            papa.callCalabashBrothersLineUp();
            papa.sayComeOn();
            xz.callEvilLolos(20);
            xz.makeNewFormation(i);
            ss.sayComeOn();

            ground.clearMap();
            ground.acceptMove(papa);
            ground.acceptMove(ss);
            ground.acceptMove(xz);
            ground.acceptMove(papa.getCalabashBrothers(), 7);
            ground.acceptMove(xz.getEvilLolos(), xz.getLoloCounter());
            ground.displayCli();
            // 通过反射获取地图上的人物计数
            tester.countThroughReflection(ground.getGrids(),N,N);
        }
    }

}