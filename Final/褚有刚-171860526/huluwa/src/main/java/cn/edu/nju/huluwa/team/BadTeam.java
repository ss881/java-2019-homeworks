package cn.edu.nju.huluwa.team;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.creature.Minion;
import cn.edu.nju.huluwa.creature.Scorpion;
import cn.edu.nju.huluwa.creature.Snake;
import cn.edu.nju.huluwa.position.Position;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class BadTeam extends Team<Scorpion, Minion> {
    private Snake snake;

    public BadTeam(Scorpion leader, ArrayList<Minion> followers, Snake snake, Image winImage) {
        super(leader, followers, winImage);
        this.snake = snake;
    }

    @Override
    public void buZhen(Position[][] positions) {
        leader.buZhen(positions, followers);
    }

    @Override
    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(leader);
        creatures.add(snake);
        creatures.addAll(followers);
        return creatures;
    }

    @Override
    public void setInitialPos(Position[][] positions) {
        snake.setPosition(positions[11][0]);
        leader.setInitialPos(positions, followers);
    }
}
