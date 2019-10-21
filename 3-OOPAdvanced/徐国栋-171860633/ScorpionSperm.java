final class ScorpionSperm extends Creature {
    EvilLolo[] players;// 若干小喽啰
    int loloNumbers;// 总数
    int loloCounter;// 战斗人员总数
    private PretrainedFormation formationCtrl;

    ScorpionSperm() {
        name = "Xz";
        formationCtrl = new PretrainedFormation();
    }

    public void callEvilLolos(int _loloNumbers) {
        loloNumbers = _loloNumbers;
        players = new EvilLolo[loloNumbers];
        for (int i = 0; i < loloNumbers; i++)
            players[i] = new EvilLolo();
    }

    public void makeNewFormation(int index) {
        formationCtrl.load(index);
        loloCounter = formationCtrl.getSize();
        if (loloCounter > loloNumbers) {
            loloNumbers = loloCounter;
            players = new EvilLolo[loloNumbers];
        }
        XPoint2D pos = formationCtrl.getCoordinate(0);
        pos.x += 6;
        pos.y += 2;
        setPosition(pos);
        for (int i = 1; i < loloCounter; i++) {
            pos = formationCtrl.getCoordinate(i);
            pos.x += 6;
            pos.y += 2;
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

    Creature[] getEvilLolos() {
        return players;
    }
}