package cn.edu.nju.huluwa.huluworld;


import cn.edu.nju.huluwa.Scene;
import cn.edu.nju.huluwa.team.BadTeam;
import cn.edu.nju.huluwa.team.GoodTeam;
import cn.edu.nju.huluwa.team.TeamBuilder;

public class WorldBuilder {
    private WorldBuilder() {

    }

    public static HuluWorld buildHuluWorld(Scene scene) {
        HuluWorld huluWorld = new HuluWorld(scene);

        // create teams
        GoodTeam goodTeam = TeamBuilder.buildGoodTeam(huluWorld);
        BadTeam badTeam = TeamBuilder.buildBadTeam(huluWorld);

        // initial huluWorld
        goodTeam.setInitialPos(huluWorld.getPositionManager().getPositions());
        badTeam.setInitialPos(huluWorld.getPositionManager().getPositions());

        // direct huluWorld
        huluWorld.setGoodTeam(goodTeam);
        huluWorld.setBadTeam(badTeam);

        return huluWorld;
    }
}
