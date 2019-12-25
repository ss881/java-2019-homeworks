package cn.edu.nju.huluwa.team;


import cn.edu.nju.huluwa.creature.*;
import cn.edu.nju.huluwa.huluworld.HuluWorld;
import cn.edu.nju.huluwa.util.ImageLoader;

import java.util.ArrayList;

public class TeamBuilder {
    private TeamBuilder(){
    }

    public static GoodTeam buildGoodTeam(HuluWorld huluWorld) {
        Grandpa grandpa = CreatureFactory.createGrandpa(huluWorld);
        ArrayList<Huluwa> huluwas = CreatureFactory.createHuluwas(huluWorld);
        return new GoodTeam(grandpa, huluwas, ImageLoader.getGoodTeamWinImage());
    }

    public static BadTeam buildBadTeam(HuluWorld huluWorld) {
        Scorpion scorpion = CreatureFactory.createScorpion(huluWorld);
        ArrayList<Minion> minions = CreatureFactory.createMinions(huluWorld);
        Snake snake = CreatureFactory.createSnake(huluWorld);
        return new BadTeam(scorpion, minions, snake, ImageLoader.getBadTeamWinImage());
    }
}
