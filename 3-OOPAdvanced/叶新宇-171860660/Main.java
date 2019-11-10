import java.util.*;

public class Main {
    public static void main(String[] args) {
        BattleField bf = new BattleField(20);
        CalabashLineup calabashLineup = new CalabashLineup(new Position(bf.getSize() / 4, 1));
        ScorpionLineup scorpionLineup = new ScorpionLineup(new Position(bf.getSize() / 2, bf.getSize() / 2));
        calabashLineup.joinBattleField(bf);
        scorpionLineup.joinBattleField(bf);
        bf.printBattleField();

        System.out.print("请输入一个0~6之间的整数，让蝎子精阵营变换一个阵法:");
        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        scorpionLineup.ChangeTactic(bf, command);
        bf.printBattleField();
    }
}
