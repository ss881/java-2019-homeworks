package hulubro.life;

import hulubro.map.Map;
import javafx.scene.image.Image;

public class Grandfather extends Life{
    public Grandfather(Map map){
        super(map);
        image=new Image(Grandfather.class.getResourceAsStream("../pic/0.png"));
        team=Team.HULU;
        name="老爷爷";
    }
}
