package creature;

import controller.MyMap;
import javafx.scene.canvas.Canvas;

interface Fight {
    public void attack(Creature enemy, Canvas canvas);
    public void skill(MyMap map,Canvas canvas); //使用技能,由于部分生物技能为范围攻击，需要向方法中传入游戏地图
}
