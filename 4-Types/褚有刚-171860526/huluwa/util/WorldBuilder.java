package huluwa.util;

import huluwa.creature.*;
import huluwa.team.BadTeam;
import huluwa.team.GoodTeam;

import java.util.ArrayList;

public class WorldBuilder {
    private WorldBuilder() {

    }

    public static HuluWorld buildHuluWorld(int row, int col) {
        HuluWorld huluWorld = new HuluWorld();

        // create teams
        GoodTeam goodTeam = TeamBuilder.buildGoodTeam();
        BadTeam badTeam = TeamBuilder.buildBadTeam();

        // initial creatures' external attributes
        Position[][] positions = new Position[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                positions[i][j] = new Position(i, j);
            }
        }

        // initial huluWorld
        goodTeam.setInitialPos(positions);
        badTeam.setInitialPos(positions);

        // direct huluWorld
        huluWorld.setGoodTeam(goodTeam);
        huluWorld.setBadTeam(badTeam);
        huluWorld.setPositions(positions);

        return huluWorld;
    }
}
