package chessman;
import chessboard.Position;
import javafx.geometry.Pos;

public class Creature {
    String name;
    Position site;
    Boolean camp;//true is justice, false is evil
    CreatureState state;

    Creature(String n, Position s, Boolean c) {
        name = n;
        site = s;
        camp = c;
        state = CreatureState.READY;
    }

    @Override
    public String toString() {
        String res =name+",site:"+(camp?"justice":"evil")+"Position:"+site.toString();
        return res;
    }

    public CreatureState setLive() {
        CreatureState past = state;
        state = CreatureState.LIVE;
        return past;
    }

    public CreatureState setDead() {
        CreatureState past = state;
        state = CreatureState.DEAD;
        return past;
    }

    public String getName() {
        return name;
    }

    public Boolean getCamp() {
        return camp;
    }

    public Position getSite() {
        return site;
    }
    public void setSite(Position x){site=x;}

    public Position changeSite(Position nextSite) {
        Position past = site;
        site = nextSite;
        return past;//save the past state
    }
}






