package huluwa.team;

import huluwa.creature.Creature;
import huluwa.creature.Minion;
import huluwa.creature.Scorpion;
import huluwa.creature.Snake;
import huluwa.util.Position;

import java.util.ArrayList;

public class BadTeam extends Team<Scorpion, Minion>{
    private Snake snake;
    public BadTeam(Scorpion leader, ArrayList<Minion> followers, Snake snake) {
        super(leader, followers);
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
        leader.buZhen(positions, followers);
    }
}
