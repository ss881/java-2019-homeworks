package cn.edu.nju.huluwa.team;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.creature.Grandpa;
import cn.edu.nju.huluwa.creature.Huluwa;
import cn.edu.nju.huluwa.position.Position;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class GoodTeam extends Team<Grandpa, Huluwa> {

    public GoodTeam(Grandpa leader, ArrayList<Huluwa> followers, Image winImage) {
        super(leader, followers, winImage);
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
        leader.setInitialPos(positions, followers);
    }
}

