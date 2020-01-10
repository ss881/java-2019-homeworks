package team;

import battlefield.BattleField;
import creature.Creature;
import creature.Leader;
import javafx.scene.image.Image;
import battlefield.formation.Formation;

import java.util.ArrayList;

public class EvilTeam<L extends Leader, S extends Creature> implements Team {
    private L leader;
    private ArrayList<S> subordinate;
    private Image teamImage;
    private BattleField battleField;

    public EvilTeam(L leader, ArrayList<S> subordinate, BattleField bf) {
        this.leader = leader;
        this.subordinate = subordinate;
        this.battleField = bf;

    }

    @Override
    public void setTeamImage(){
        teamImage = new Image("image/lost.png");
    }

    @Override
    public void arrange() {
        leader.setPosition(15,0);
        Formation.HE_YI.arrange(battleField, subordinate, leader.getPosition(), false);
    }

    @Override
    public boolean checkLost() {
        if (leader.currentHealth <= 0)
            return true;
        else
            return false;
    }

    @Override
    public ArrayList<Creature> getAllCreature() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(leader);
        creatures.addAll(subordinate);
        return creatures;
    }

    @Override
    public Image showImage() {
        return teamImage;
    }
}
