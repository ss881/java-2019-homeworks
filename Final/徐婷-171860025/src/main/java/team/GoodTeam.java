package team;

import battle.Formation;
import battle.Ground;
import creature.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GoodTeam extends Team implements Serializable {
    private Grandpa cheerer = new Grandpa();
    private List<CalabashBros> soldiers = new ArrayList<CalabashBros>();
    private transient static GoodTeam instance;
    public static GoodTeam getInstance(){return instance;}

    public GoodTeam(){
        soldiers.add(new First());
        soldiers.add(new Second());
        soldiers.add(new Third());
        soldiers.add(new Fourth());
        soldiers.add(new Fifth());
        soldiers.add(new Sixth());
        soldiers.add(new Seventh());
        Formation.changShe(soldiers);
        Ground.getInstance().update(this);
    }
    public GoodTeam(boolean b){
        this();
        instance = this;
    }
    public void run(){
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(cheerer);
        for(CalabashBros soldier: soldiers){
            exec.execute(soldier);
        }
        exec.shutdown();
    }
    public List<Creature> getTeamMembers(){
        List<Creature> ret = new ArrayList<Creature>();
        ret.add(cheerer);
        ret.addAll(1, soldiers);
        return ret;
    }
    public void copy(GoodTeam t){
        cheerer.copy(t.cheerer);
        for(int i = 0;i<soldiers.size();i++){
            soldiers.get(i).copy(t.soldiers.get(i));
        }
    }
    public void setCheererLive(){
        cheerer.setState(State.LIVE);
        cheerer.setPos(6, 0);
    }
    public void setCheererDead(){
        cheerer.setState(State.DEAD);
    }
    public void setSoldiersDead(){
        for(Creature c: soldiers)
            c.setState(State.DEAD);
    }
    public List<CalabashBros> getSoldiers(){
        return soldiers;
    }
}
