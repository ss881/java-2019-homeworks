package cn.mhj.Creature;

import cn.mhj.Field.Battlefield;
import javafx.scene.image.Image;

public abstract class Creature {

  private Battlefield battlefield;
  private Image image;

  Creature(Battlefield battlefield) {

    this.battlefield = battlefield;
    this.image = null;
  }

  public Battlefield getBattlefield() {
    return battlefield;
  }

  protected void setImage(Image image) {
    this.image = image;
  }

  public Image getImage() {
    return image;
  }
}
