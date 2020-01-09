package cn.mhj.Field;

import cn.mhj.Creature.FightCreature;
import java.util.Vector;
import javafx.scene.canvas.GraphicsContext;

public class Unit<T extends FightCreature> {
  public static final int WIDTH = 45, HEIGHT = 45;

  private Position position;
  private Vector<T> creatures;
  private GraphicsContext battleMap;

  public Unit(GraphicsContext battleMap) {
    this.position = new Position();
    this.creatures = new Vector<T>();
    this.battleMap = battleMap;
  }

  public Unit(Position position, GraphicsContext battleMap) {
    this.position = position;
    this.creatures = new Vector<T>();
    this.battleMap = battleMap;
  }

  public void addCreature(T creature) {
    creatures.add(creature);
    drawUnit();
  }

  public void removeCreature(T creature) {
    creatures.remove(creature);
    drawUnit();
  }

  public boolean hasCreature() {
    for (T creature : creatures) {
      if (creature.isAlive()) {
        return true;
      }
    }
    return false;
  }

  private void drawUnit() {
    if (battleMap != null) {
      battleMap.clearRect(position.getY() * WIDTH, position.getX() * HEIGHT, WIDTH, HEIGHT);
      for (T creature : creatures) {
        //      Platform.runLater(
        //          () ->
        //              battleMap.drawImage(
        //                  creature.getImage(),
        //                  position.getY() * WIDTH,
        //                  position.getX() * HEIGHT,
        //                  WIDTH,
        //                  HEIGHT));
        //    }
        battleMap.drawImage(
            creature.getImage(), position.getY() * WIDTH, position.getX() * HEIGHT, WIDTH, HEIGHT);
      }
    }
  }

  public Vector<T> getCreatures() {
    return creatures;
  }

  public void clear() {
    creatures.clear();
  }
}
