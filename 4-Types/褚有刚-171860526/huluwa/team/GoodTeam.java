package huluwa.team;

import huluwa.creature.Creature;
import huluwa.creature.Grandpa;
import huluwa.creature.Huluwa;
import huluwa.util.Position;

import java.util.ArrayList;

public class GoodTeam extends Team<Grandpa, Huluwa> {

    public GoodTeam(Grandpa leader, ArrayList<Huluwa> followers) {
        super(leader, followers);
    }

    @Override
    public void buZhen(Position[][] positions) {
        leader.buZhen(positions, followers);
    }

    public void shuffleHuluwa() {
        leader.shuffleHuluwa(followers);
    }

    public void sortHuluwa() {
        leader.sortHuluwa(followers);
    }

    @Override
    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(leader);
        creatures.addAll(followers);
        return creatures;
    }

    @Override
    public void setInitialPos(Position[][] positions) {
        leader.setPosition(positions[0][0]);
        leader.buZhen(positions, followers);
    }
}

