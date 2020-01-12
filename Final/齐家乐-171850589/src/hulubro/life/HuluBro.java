package hulubro.life;

import hulubro.map.Map;
import javafx.scene.image.Image;

public class HuluBro extends Life{
    public HuluBro(Map map, int i){
        super(map);
        image=new Image(HuluBro.class.getResourceAsStream("../pic/"+i+".png"));
        team=Team.HULU;
        switch (i){
            case 1:name="红娃";break;
            case 2:name="橙娃";break;
            case 3:name="黄娃";break;
            case 4:name="绿娃";break;
            case 5:name="青娃";break;
            case 6:name="蓝娃";break;
            case 7:name="紫娃";break;
        }
    }
}
