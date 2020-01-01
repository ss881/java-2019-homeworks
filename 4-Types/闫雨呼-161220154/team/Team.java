package team;

import creature.Creature;
import formation.Formation;
import formation.FormationKind;

//队伍类（泛型）
public class Team<T1 extends Creature,T2 extends Creature> {
    public T1 leader;
    private T2[] followers;
    public Team(T1 leader,T2[] followers){
        this.leader=leader;
        this.followers=followers;
    }
    public void changeFormation(FormationKind formation) {
        Formation.changeFormation(followers,formation);
    }
}
