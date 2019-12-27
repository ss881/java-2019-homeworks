import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        BattleField bf = new BattleField(20);
        CalabashLineup calabashLineup = new CalabashLineup(new Position(bf.getSize() / 4, 1));
        ScorpionLineup scorpionLineup = new ScorpionLineup(new Position(bf.getSize() / 2 - 2, bf.getSize() / 2 + 2));
        calabashLineup.joinBattleField(bf);
        scorpionLineup.joinBattleField(bf);
        bf.printBattleField();
        System.out.println("0:鹤翼 1:冲轭 2:方圆 3:锋矢 4:雁行 5:偃月 6:鱼鳞");
        System.out.print("请输入一个0~6之间的整数，让蝎子精阵营变换一个阵法(输入0~6以外的整数都会排出鹤翼阵):");
        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        scorpionLineup.ChangeTactic(bf, command);
        bf.printBattleField();
    }
}
