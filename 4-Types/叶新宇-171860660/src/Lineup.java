import java.util.*;

public abstract class Lineup {
    protected Creature captain;
    protected Creature cheerleader;
    protected ArrayList<Creature> lineupList;
    protected Tactic t;
    Lineup() {
        lineupList = new ArrayList<>();
    }
    public void addCaptain(Creature c) {
        captain = c;
    }
    public void addCheerleader(Creature c) {
        cheerleader = c;
    }
    public void addSoider(Creature c) {
        lineupList.add(c);
    }
}

class CalabashLineup extends Lineup {
    private Position refpoint;
    CalabashLineup(Position p) {
        refpoint = p;
        addCaptain(new CalabashBoy("老大", "红色"));
        addCheerleader(new Grandfather("爷爷"));
        addSoider(new CalabashBoy("老二", "橙色"));
        addSoider(new CalabashBoy("老三", "黄色"));
        addSoider(new CalabashBoy("老四", "绿色"));
        addSoider(new CalabashBoy("老五", "青色"));
        addSoider(new CalabashBoy("老六", "蓝色"));
        addSoider(new CalabashBoy("老七", "紫色"));
    }

    public void joinBattleField(BattleField b) {
        t = new Tactic(refpoint, lineupList.size() + 1);
        ArrayList<Position> l = t.ChangShe();
        captain.join(b, l.get(0));
        for(int i = 0; i < lineupList.size(); i++)
            lineupList.get(i).join(b, l.get(i + 1));
        cheerleader.join(b, new Position(0, 0));
    }
}

class ScorpionLineup extends Lineup {
    private Position refpoint;
    ScorpionLineup(Position p) {
        refpoint = p;
        addCaptain(new Scorpion("蝎子精"));
        addCheerleader(new Snake("蛇精"));
        for(int i = 0; i < 6; i++)
            addSoider(new Minion("小喽啰" + i + "号"));
    }

    public void joinBattleField(BattleField b) {
        t = new Tactic(refpoint, lineupList.size() + 1);
        ArrayList<Position> l = t.HeYi();
        captain.join(b, l.get(0));
        for(int i = 0; i < lineupList.size(); i++)
            lineupList.get(i).join(b, l.get(i + 1));
        cheerleader.join(b, new Position(0, b.getSize() - 1));
    }

    public void ChangeTactic(BattleField b, int command) {
        t = new Tactic(refpoint, lineupList.size() + 1);
        ArrayList<Position> l;
        switch (command) {
            case 0:
                l = t.HeYi();
                break;
            case 1:
                l = t.ChongE();
                break;
            case 2:
                l = t.FangYuan();
                break;
            case 3:
                l = t.FengShi();
                break;
            case 4:
                l = t.YanXing();
                break;
            case 5:
                l = t.YanYue();
                break;
            case 6:
                l = t.YuLin();
                break;
            default:
                l = t.HeYi();
                break;
        }
        captain.withdraw(b);
        for(Creature i : lineupList)
            i.withdraw(b);
        captain.join(b, l.get(0));
        for(int i = 0; i < lineupList.size(); i++)
            lineupList.get(i).join(b, l.get(i + 1));
    }
}
