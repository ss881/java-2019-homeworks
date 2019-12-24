package huluwa.util;

import huluwa.creature.*;
import huluwa.team.BadTeam;
import huluwa.team.GoodTeam;

import java.util.ArrayList;

public class TeamBuilder {
    private TeamBuilder(){
    }

    public static GoodTeam buildGoodTeam() {
        Grandpa grandpa = CreatureFactory.createGrandpa();
        ArrayList<Huluwa> huluwas = CreatureFactory.createHuluwas();
        return new GoodTeam(grandpa, huluwas);
    }

    public static BadTeam buildBadTeam() {
        Scorpion scorpion = CreatureFactory.createScorpion();
        ArrayList<Minion> minions = CreatureFactory.createMinions();
        Snake snake = CreatureFactory.createSnake();
        return new BadTeam(scorpion, minions, snake);
    }
}
