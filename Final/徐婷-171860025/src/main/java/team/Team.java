package team;

import creature.Creature;

import java.util.ArrayList;
import java.util.List;

public abstract class Team implements Runnable{
    public abstract List<Creature> getTeamMembers();
    public abstract void setCheererLive();
    public abstract void setCheererDead();
}
