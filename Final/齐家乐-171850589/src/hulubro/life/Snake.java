package hulubro.life;

import hulubro.map.Grid;
import hulubro.map.Map;
import javafx.scene.image.Image;

public class Snake extends Life{
    public Snake(Map map){
        super(map);
        image=new Image(Snake.class.getResourceAsStream("../pic/蛇精.png"));
        team=Team.ENEMY;
        name="蛇精";
    }
}
