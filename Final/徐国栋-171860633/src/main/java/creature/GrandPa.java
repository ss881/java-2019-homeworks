package creature;

import javafx.scene.image.Image;
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
        setApperrance(new Image("images/papa.png"),new Image("images/papa - 副本.png"));
        isEvil=false;
        setName("Gp");
        setPosition(new XPoint2D(1, 0));
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
            pos.setY(pos.getY() + 1);
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
    public void makeNewFormation(int i){
        playRankBubbleSort();
        XPoint2D[]positions=new XPoint2D[7];
        switch (i){
            case 1://fm
                positions[0]=new XPoint2D(2,0);
                positions[1]=new XPoint2D(1,1);
                positions[2]=new XPoint2D(0,2);
                positions[3]=new XPoint2D(1,3);
                positions[4]=new XPoint2D(2,4);
                positions[5]=new XPoint2D(3,3);
                positions[6]=new XPoint2D(4,2);
                break;
            case 2://fs
                positions[0]=new XPoint2D(0,2);
                positions[1]=new XPoint2D(1,1);
                positions[2]=new XPoint2D(2,0);
                positions[3]=new XPoint2D(2,1);
                positions[4]=new XPoint2D(2,2);
                positions[5]=new XPoint2D(3,1);
                positions[6]=new XPoint2D(4,2);
                break;
            case 3://hz
                positions[0]=new XPoint2D(1,0);
                positions[1]=new XPoint2D(0,1);
                positions[2]=new XPoint2D(1,2);
                positions[3]=new XPoint2D(0,3);
                positions[4]=new XPoint2D(1,4);
                positions[5]=new XPoint2D(0,5);
                positions[6]=new XPoint2D(1,6);
                break;
            case 4://hy
                positions[0]=new XPoint2D(0,0);
                positions[1]=new XPoint2D(1,1);
                positions[2]=new XPoint2D(2,2);
                positions[3]=new XPoint2D(3,3);
                positions[4]=new XPoint2D(4,2);
                positions[5]=new XPoint2D(5,1);
                positions[6]=new XPoint2D(6,0);
                break;
            case 5://yh
                positions[0]=new XPoint2D(0,6);
                positions[1]=new XPoint2D(1,5);
                positions[2]=new XPoint2D(2,4);
                positions[3]=new XPoint2D(3,3);
                positions[4]=new XPoint2D(4,2);
                positions[5]=new XPoint2D(5,1);
                positions[6]=new XPoint2D(6,0);
                break;
            case 6://yy
                positions[0]=new XPoint2D(0,2);
                positions[1]=new XPoint2D(1,2);
                positions[2]=new XPoint2D(2,2);
                positions[3]=new XPoint2D(2,1);
                positions[4]=new XPoint2D(3,4);
                positions[5]=new XPoint2D(3,0);
                positions[6]=new XPoint2D(2,3);
                break;
            case 7://yl
                positions[0]=new XPoint2D(1,0);
                positions[1]=new XPoint2D(0,1);
                positions[2]=new XPoint2D(1,1);
                positions[3]=new XPoint2D(0,2);
                positions[4]=new XPoint2D(1,2);
                positions[5]=new XPoint2D(2,2);
                positions[6]=new XPoint2D(1,3);
                break;
            default:
                positions[0]=new XPoint2D(0,0);
                positions[1]=new XPoint2D(0,1);
                positions[2]=new XPoint2D(0,2);
                positions[3]=new XPoint2D(0,3);
                positions[4]=new XPoint2D(0,4);
                positions[5]=new XPoint2D(0,5);
                positions[6]=new XPoint2D(0,6);
                break;
        }
        for(int ii=0;ii<7;ii++){
            XPoint2D pos = positions[ii];
            pos.setX(pos.getX() + 2);
            pos.setY(pos.getY() + 1);
            players[ii].setPosition(pos);
        }
    }

    private static class RankComparator implements Comparator<CalabashBrother> {
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
