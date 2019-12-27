package huluwa.team;

import huluwa.creature.Creature;
import huluwa.creature.Leader;
import huluwa.util.Position;

import java.util.ArrayList;

public abstract class Team <L extends Leader, F extends Creature> {
    protected L leader;
    protected ArrayList<F> followers;
    public Team(L leader, ArrayList<F> followers) {
        this.leader = leader;
        this.followers = followers;
    }
    public abstract void buZhen(Position[][] positions);
    public abstract ArrayList<Creature> getAllCreatures();
    public abstract void setInitialPos(Position[][] positions);
}
