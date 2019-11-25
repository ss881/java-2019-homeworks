package creature;

import javafx.scene.paint.Color;
import gamectrl.Formation;
import objinfo.XPoint2D;

import java.util.*;

// 老爷爷类
final public class GrandPa extends Creature {
    // 种葫芦的花园
    private CalabashBrotherFactory garden = new CalabashBrotherFactory();
    // 阵型控制器
    private Formation formation = new Formation();
    private CalabashBrother[] players = new CalabashBrother[7];// 七个葫芦娃
    private int aliveCounter = 7;

    public int getAliveCounter() {
        return aliveCounter;
    }

    public GrandPa() {
        super();
        setName("Gp");
        setPosition(new XPoint2D(3, 0));
    }

    public void sayComeOn() {
        System.out.println("（画外音）" + getName() + ": 我是老爷爷，我在给我的葫芦娃加油");
    }

    public Creature[] getCalabashBrothers() {
        return players;
    }

    // 种7个葫芦娃 随机排一排
    public void callCalabashBrothers() {
        players[0] = garden.create(0, Color.rgb(255, 0, 0));
        players[1] = garden.create(1, Color.rgb(255, 128, 0));
        players[2] = garden.create(2, Color.rgb(255, 255, 0));
        players[3] = garden.create(3, Color.rgb(0, 255, 0));
        players[4] = garden.create(4, Color.rgb(0, 255, 255));
        players[5] = garden.create(5, Color.rgb(0, 0, 255));
        players[6] = garden.create(6, Color.rgb(128, 0, 255));
        randomShuffle();

        formation.load(0);// 构造长蛇阵

        // 把坐标赋给一群葫芦娃
        assignCoordinate();
    }

    private void assignCoordinate() {
        for (int i = 0; i < formation.getSize(); i++) {
            XPoint2D pos = formation.getCoordinate(i);
            pos.setX(pos.getX() + 2);
            pos.setY(pos.getY() + 2);
            players[i].setPosition(pos);
        }
    }

    // 正式排队 从老大到老七的长蛇阵
    public void callCalabashBrothersLineUp() {
        playRankBubbleSort();
        formation.load(0);// 构造长蛇阵
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
