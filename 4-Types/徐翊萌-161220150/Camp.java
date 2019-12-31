import java.util.ArrayList;

public class Camp<T extends Unit> {
    private ArrayList<Unit> soldiers;
    private T leader;
    Camp(T leader) {
        this.leader = leader;
        soldiers = new ArrayList<>();
    }

    Camp(T leader, Unit[] units, int num) {
        this.leader = leader;
        soldiers = new ArrayList<>();
        for(int i = 0; i < num; i++)
            soldiers.add(units[i]);
    }

    public T getLeader() { return leader; }

    public ArrayList<Unit> getSoldiers() { return soldiers; }

    public void AddUnit(Unit unit) { soldiers.add(unit); }
}
