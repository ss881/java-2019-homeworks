package team;

import creature.*;

public abstract class Team<M extends Creature,L extends Leader<M>> {
    L leader;
    M[]members;
    public abstract void changeForm();
    public abstract M[]getMembers();
    public abstract L getLeader();
}