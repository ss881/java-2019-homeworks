package team;

import battlefield.BattleField;
import creature.Creature;
import creature.Leader;
import javafx.scene.image.Image;
import battlefield.formation.Formation;

import java.util.ArrayList;

public class GoodTeam<L extends Leader, S extends Creature> implements Team {
    private L leader;
    private ArrayList<S> subordinate;
    private Image teamImage;
    private BattleField battleField;

    public GoodTeam(L leader, ArrayList<S> subordinate, BattleField bf) {
        this.leader = leader;
        this.subordinate = subordinate;
        this.battleField = bf;
    }

    @Override
    public void setTeamImage(){
        teamImage = new Image("image/win.png");
    }

    @Override
    public void arrange() {
        leader.setPosition(0,0);
        Formation.CHANG_SHE.arrange(battleField, subordinate, leader.getPosition(), true);
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
