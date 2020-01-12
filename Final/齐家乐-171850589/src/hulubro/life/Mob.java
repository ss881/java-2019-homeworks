package hulubro.life;

import hulubro.map.Grid;
import hulubro.map.Map;
import javafx.scene.image.Image;

public class Mob extends Life{
    public Mob(Map map, int i) {
        super(map);
        int n=i%4+10;
        image=new Image(Mob.class.getResourceAsStream("../pic/"+n+".png"));
        team=Team.ENEMY;
        name="喽啰"+i;
    }
}
