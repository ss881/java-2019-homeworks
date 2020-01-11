package creature;

import gamectrl.Formation;
import gamectrl.MainConfig;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import objinfo.XPoint2D;

final public class ScorpionSperm extends Creature{
    private EvilLolo[] players;// 若干小喽啰
    private int loloNumbers;// 总数
    private int loloCounter;// 战斗人员总数
    private Formation formation = new Formation();;
    private EvilLoloFactory loloFactory = new EvilLoloFactory();

    public ScorpionSperm() {
        super();
        isEvil=true;
        setApperrance(new Image("images/xz.png"),new Image("images/xz - 副本.png"));
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
        XPoint2D size = formation.load(index);
        loloCounter = formation.getSize()-1;// 事实上有一个小喽啰的位置给了蝎子精
        if (loloCounter > loloNumbers) {
            loloNumbers = loloCounter;
            players = new EvilLolo[loloNumbers];
            for(int i=0;i<loloNumbers;i++){
                players[i]=new EvilLolo(i);
            }
        }
        XPoint2D pos = formation.getCoordinate(0);
        pos.setX(ctrl.col-1-size.getX()+pos.getX());
        pos.setY(pos.getY() + 1);
        setPosition(pos);
        //System.out.println(loloCounter+","+formation.getSize());
        for (int i = 0; i < loloCounter; i++) {
            pos = formation.getCoordinate(i+1);
            pos.setX(ctrl.col-1-size.getX()+pos.getX());
            pos.setY(pos.getY() + 1);
            players[i].setPosition(pos);
            //System.out.println(pos.toString());
        }
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
