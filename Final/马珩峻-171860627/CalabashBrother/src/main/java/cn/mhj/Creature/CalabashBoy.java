package cn.mhj.Creature;

import cn.mhj.Enum.CalabashColor;
import cn.mhj.Field.Battlefield;
import cn.mhj.Field.Position;
import javafx.scene.image.Image;

public class CalabashBoy extends FightCreature {
  private static final int CALABASH_POWER = 15;

  private CalabashColor color;

  public CalabashBoy(int order, Position position, Battlefield battlefield) throws Exception {
    super(CALABASH_POWER, position, battlefield);
    if (order < 0 || order > 6) {
      throw new Exception(
          String.format("calabashBoy order out of range, expect [0-6] but got %d", order));
    }
    this.color = CalabashColor.values()[order];
    this.aliveImage = new Image(String.format("cn/mhj/pic/Creature/calabashBoy%d.png", order));
    this.deadImage = new Image(String.format("cn/mhj/pic/Creature/calabashBoy%dDead.png", order));
    this.setImage(aliveImage);
  }

  public CalabashColor getColor() {
    return color;
  }
}
