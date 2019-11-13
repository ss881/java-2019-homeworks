public class Homework3 {
    public static void main(String[] args) {
        GrandPa papa = new GrandPa();
        ScorpionSperm xz = new ScorpionSperm();
        SnakeEssence ss = new SnakeEssence();
        ViewField viewfield = new ViewField(13, 13);

        // 场景一：蛇精、老爷爷进场，葫芦娃乱序排队
        System.out.println("（场景 0）：蛇精、老爷爷进场，葫芦娃乱序排队");
        papa.callCalabashBrothers();
        papa.sayComeOn();
        // 人物调动完成
        viewfield.clearMap();
        viewfield.acceptMove(papa);
        viewfield.acceptMove(ss);
        viewfield.acceptMove(papa.getCalabashBrothers(), 7);
        viewfield.print();
        // 场景绘制完成

        // 其它场景：蛇精、老爷爷在场，葫芦娃保持长蛇阵，蝎子精和小喽啰变换阵型
        for (int i = 1; i <= 7; i++) {
            System.out.println("（场景 "+i+"）: 蛇精、老爷爷在场，葫芦娃保持长蛇阵，蝎子精和小喽啰变换阵型");
            papa.callCalabashBrothersLineUp();
            papa.sayComeOn();
            xz.callEvilLolos(20);
            xz.makeNewFormation(i);
            ss.sayComeOn();

            viewfield.clearMap();
            viewfield.acceptMove(papa);
            viewfield.acceptMove(ss);
            viewfield.acceptMove(xz);
            viewfield.acceptMove(papa.getCalabashBrothers(), 7);
            viewfield.acceptMove(xz.getEvilLolos(), xz.getLoloCounter());
            viewfield.print();
        }
    }

}