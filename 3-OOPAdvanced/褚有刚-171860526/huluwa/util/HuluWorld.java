package huluwa.util;

import huluwa.creature.*;

import java.util.ArrayList;

public class HuluWorld {
    private ArrayList<Huluwa> huluwas;
    private ArrayList<Minion> minions;
    private Grandpa grandpa;
    private Scorpion scorpion;
    private Snake snake;
    private Position[][] positions;

    HuluWorld() {
        super();
    }

    public void sortHuluwa() {
        grandpa.sortHuluwa(huluwas);
    }

    public void shufflehuluwa() {
        grandpa.shuffleHuluwa(huluwas);
    }

    public String minionsBuZhen() {
        scorpion.buZhen(positions, minions);
        StringBuilder cheerInfo = new StringBuilder();
        for(Creature creature : getAllCreatures()) {
            if(creature instanceof Cheerable) {
                cheerInfo.append(((Cheerable)creature).cheer());
            }
        }
        return cheerInfo.toString();
    }

    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.addAll(huluwas);
        creatures.addAll(minions);
        creatures.add(grandpa);
        creatures.add(scorpion);
        creatures.add(snake);
        return creatures;
    }

    void setHuluwas(ArrayList<Huluwa> huluwas) {
        this.huluwas = huluwas;
    }

    void setMinions(ArrayList<Minion> minions) {
        this.minions = minions;
    }

    void setGrandpa(Grandpa grandpa) {
        this.grandpa = grandpa;
    }

    void setScorpion(Scorpion scorpion) {
        this.scorpion = scorpion;
    }

    void setSnake(Snake snake) {
        this.snake = snake;
    }

    void setPositions(Position[][] positions) {
        this.positions = positions;
    }
}
