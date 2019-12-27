package huluwa.util;

import huluwa.creature.*;
import huluwa.team.BadTeam;
import huluwa.team.GoodTeam;

import java.util.ArrayList;

public class HuluWorld {
    private GoodTeam goodTeam;
    private BadTeam badTeam;
    private Position[][] positions;

    HuluWorld() {
        super();
    }

    public void sortHuluwa() {
        goodTeam.sortHuluwa();
    }

    public void shufflehuluwa() {
        goodTeam.shuffleHuluwa();
    }

    public String badTeamBuZhen() {
        badTeam.buZhen(positions);
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
        creatures.addAll(goodTeam.getAllCreatures());
        creatures.addAll(badTeam.getAllCreatures());
        return creatures;
    }


    void setGoodTeam(GoodTeam goodTeam) {
        this.goodTeam = goodTeam;
    }

    void setBadTeam(BadTeam badTeam) {
        this.badTeam = badTeam;
    }

    void setPositions(Position[][] positions) {
        this.positions = positions;
    }
}
