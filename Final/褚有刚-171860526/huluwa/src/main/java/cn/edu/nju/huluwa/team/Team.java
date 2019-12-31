package cn.edu.nju.huluwa.team;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.creature.Leader;
import cn.edu.nju.huluwa.position.Position;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Team <L extends Leader, F extends Creature> {
    protected L leader;
    protected ArrayList<F> followers;
    protected Image winImage;
    public Team(L leader, ArrayList<F> followers, Image winImage) {
        this.leader = leader;
        this.followers = followers;
        this.winImage = winImage;
    }
    public abstract void buZhen(Position[][] positions);
    public abstract ArrayList<Creature> getAllCreatures();
    public abstract void setInitialPos(Position[][] positions);
    public int getAliveCount() {
        int aliveCount = 0;
        for(Creature c : getAllCreatures()) {
            if(c.getState() == Creature.State.JOIN && !c.isDead()) aliveCount++;
        }
        return aliveCount;
    }
    public Image getWinImage() {
        return winImage;
    }
}
