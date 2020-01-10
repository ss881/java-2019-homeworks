package creature;

import gamectrl.Formation;
import javafx.scene.paint.Color;
import objinfo.XPoint2D;

final public class ScorpionSperm extends Creature {
    private EvilLolo[] players;// 若干小喽啰
    private int loloNumbers;// 总数
    private int loloCounter;// 战斗人员总数
    private Formation formation = new Formation();;
    private EvilLoloFactory loloFactory = new EvilLoloFactory();

    public ScorpionSperm() {
        setName("Xz");
    }

    public void callEvilLolos(int _loloNumbers) {
        loloNumbers = _loloNumbers;
        players = new EvilLolo[loloNumbers];
        for (int i = 0; i < loloNumbers; i++) {
            // 淡青色的喽啰
            players[i] = loloFactory.create(-i, Color.rgb(0, 125, 125));
        }
    }

    public void makeNewFormation(int index) {
        formation.load(index);
        loloCounter = formation.getSize();
        if (loloCounter > loloNumbers) {
            loloNumbers = loloCounter;
            players = new EvilLolo[loloNumbers];
        }
        XPoint2D pos = formation.getCoordinate(0);
        pos.setX(pos.getX() + 6);
        pos.setY(pos.getY() + 2);
        setPosition(pos);
        for (int i = 1; i < loloCounter; i++) {
            pos = formation.getCoordinate(i);
            pos.setX(pos.getX() + 6);
            pos.setY(pos.getY() + 2);
            players[i - 1].setPosition(pos);
        }
        loloCounter -= 1;// 事实上有一个小喽啰的位置给了蝎子精
    }

    public int getLoloNumbers() {
        return loloNumbers;
    }

    public int getLoloCounter() {
        return loloCounter;
    }

    public Creature[] getEvilLolos() {
        return players;
    }
}
