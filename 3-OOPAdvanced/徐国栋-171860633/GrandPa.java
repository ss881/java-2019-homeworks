import java.awt.Color;
import java.util.*;

// 老爷爷类
final class GrandPa extends Creature {
    private PretrainedFormation formationCtrl;
    private CalabashBrother[] players;// 七个葫芦娃
    private int aliveCounter;

    int getAliveCounter() {
        return aliveCounter;
    }

    GrandPa() {
        super();
        name = "Gp";
        setPosition(new XPoint2D(3, 0));
        formationCtrl = new PretrainedFormation();
    }

    public void sayComeOn() {
        System.out.println("（画外音）" + name + ": 我是老爷爷，我在给我的葫芦娃加油");
    }

    Creature[] getCalabashBrothers() {
        return players;
    }

    // 召唤葫芦娃 随机排一排
    public void callCalabashBrothers() {
        aliveCounter = 7;
        players = new CalabashBrother[7];
        players[0] = new CalabashBrother(0, new Color(255, 0, 0));
        players[1] = new CalabashBrother(1, new Color(255, 128, 0));
        players[2] = new CalabashBrother(2, new Color(255, 255, 0));
        players[3] = new CalabashBrother(3, new Color(0, 255, 0));
        players[4] = new CalabashBrother(4, new Color(0, 255, 255));
        players[5] = new CalabashBrother(5, new Color(0, 0, 255));
        players[6] = new CalabashBrother(6, new Color(128, 0, 255));
        randomShuffle();

        formationCtrl.load(0);// 构造长蛇阵

        // 把坐标赋给一群葫芦娃
        assignCoordinate();
    }

    private void assignCoordinate() {
        for (int i = 0; i < formationCtrl.getSize(); i++) {
            XPoint2D pos = formationCtrl.getCoordinate(i);
            pos.x += 2;
            pos.y += 2;
            players[i].setPosition(pos);
        }
    }

    // 正式排队 从老大到老七的长蛇阵
    public void callCalabashBrothersLineUp() {
        playRankBubbleSort();
        formationCtrl.load(0);// 构造长蛇阵
        // 把坐标赋给一群葫芦娃
        assignCoordinate();
    }

    private class RankComparator implements Comparator<CalabashBrother> {
        @Override
        public int compare(CalabashBrother a, CalabashBrother b) {
            return a.rank - b.rank;
        }
    }

    private void playRankBubbleSort() {
        RankComparator rc = new RankComparator();
        int n = players.length - 1;
        CalabashBrother tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rc.compare(players[j], players[j + 1]) > 0) {
                    tmp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = tmp;
                }
            }
        }
    }

    private void randomShuffle() {
        ArrayList<CalabashBrother> randList = new ArrayList<>(Arrays.asList(players));
        Collections.shuffle(randList);
        players = randList.toArray(players);
    }
}