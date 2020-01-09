package cn.mhj.Creature;

import cn.mhj.Field.Battlefield;
import cn.mhj.Field.Position;
import javafx.scene.image.Image;

public class Enemy extends FightCreature {
  private static final int ENEMY_POWER = 7;

  Enemy(Position position, Battlefield battlefield) throws Exception {
    super(ENEMY_POWER, position, battlefield);
    this.aliveImage = new Image("cn/mhj/pic/Creature/enemy.png");
    this.deadImage = new Image("cn/mhj/pic/Creature/enemyDead.png");
    setImage(aliveImage);
  }
}
