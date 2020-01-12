package hulubro.life;

import hulubro.map.Map;
import javafx.scene.image.Image;

public class Scorpion extends Life{
    public Scorpion(Map map){
        super(map);
        image=new Image(Snake.class.getResourceAsStream("../pic/蝎子精.png"));
        team=Team.ENEMY;
        name="蝎子精";
    }
}
