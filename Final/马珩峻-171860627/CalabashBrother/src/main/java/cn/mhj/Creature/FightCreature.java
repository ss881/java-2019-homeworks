package cn.mhj.Creature;

import cn.mhj.Field.Battlefield;
import cn.mhj.Field.Position;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import javafx.scene.image.Image;
import javafx.util.Pair;

public class FightCreature extends Creature implements Runnable {
  private static int fightCreatureNum;
  private static int roundNum;
  private int creatureID;
  private boolean isAlive;
  private int power;
  private Vector<FightCreature> enemies;
  private CountDownLatch latch;
  protected Position position;
  protected Image aliveImage;
  protected Image deadImage;

  static {
    fightCreatureNum = 0;
    roundNum = 0;
  }

  public static void setRoundNum(int roundNum) {
    FightCreature.roundNum = roundNum;
  }

  public static int getRoundNum() {
    return roundNum;
  }

  public static int getFightCreatureNum() {
    return fightCreatureNum;
  }

  public FightCreature(int power, Position position, Battlefield battlefield) throws Exception {
    super(battlefield);
    this.creatureID = fightCreatureNum;
    FightCreature.fightCreatureNum++;
    this.power = power;
    this.isAlive = true;
    this.position = position;
    this.latch = null;
    this.enemies = null;
    if (battlefield != null) {
      if (!battlefield.checkPosition(position)) {
        throw new Exception("creature position out of range, position:" + position);
      }
    }
  }

  public void init(Position position) {
    isAlive = true;
    setImage(aliveImage);
    moveTo(position);
  }

  private Pair<FightCreature, Integer> searchNearestEnemy() {
    int distance = Integer.MAX_VALUE;
    FightCreature target = null;
    for (FightCreature enemy : enemies) {
      if (enemy.isAlive()) {
        int tempDistance = getDistance(enemy);
        if (tempDistance < distance) {
          distance = tempDistance;
          target = enemy;
        }
      }
    }
    return new Pair<>(target, distance);
  }

  private int getDistance(FightCreature enemy) {
    return Math.abs(position.getX() - enemy.position.getX())
        + Math.abs(position.getY() - enemy.position.getY());
  }

  private void act() {
    Pair<FightCreature, Integer> targetEnemy = searchNearestEnemy();
    FightCreature target = targetEnemy.getKey();
    int distance = targetEnemy.getValue();
    if (distance == 1) {
      fightWithEnemy(target);
    } else {

      if (position.getY() < target.position.getY()) {
        Position newPosition = new Position(position.getX(), position.getY() + 1);
        if (!getBattlefield().hasCreature(newPosition)) {
          moveTo(newPosition);
          return;
        }
      } else if (position.getY() > target.position.getY()) {
        Position newPosition = new Position(position.getX(), position.getY() - 1);
        if (!getBattlefield().hasCreature(newPosition)) {
          moveTo(newPosition);
          return;
        }
      }

      if (position.getX() < target.position.getX()) {
        Position newPosition = new Position(position.getX() + 1, position.getY());
        if (!getBattlefield().hasCreature(newPosition)) {
          moveTo(newPosition);
        }
      } else if (position.getX() > target.position.getX()) {
        Position newPosition = new Position(position.getX() - 1, position.getY());
        if (!getBattlefield().hasCreature(newPosition)) {
          moveTo(newPosition);
        }
      }
    }
  }

  private synchronized void fightWithEnemy(FightCreature enemy) {
    Random random = new Random();
    int result = random.nextInt(getPower() + enemy.getPower());
    if (result < getPower()) {
      enemy.beKilled();
      getBattlefield().recordRound(enemy, roundNum);
      fixPower();
    } else {
      beKilled();
      enemy.fixPower();
    }
  }

  public synchronized void moveTo(Position position) {
    if (getBattlefield() != null) {
      getBattlefield().removeCreature(this);
      getBattlefield().addCreature(position, this);
    }
    this.position = position;
  }

  public void setEnemies(Vector<FightCreature> enemies) {
    this.enemies = enemies;
  }

  public int getCreatureID() {
    return creatureID;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public int getPower() {
    return power;
  }

  public void fixPower() {
    power -= 1;
  }

  public void beKilled() {
    isAlive = false;
    setImage(deadImage);
    moveTo(position);
  }

  public Position getPosition() {
    return position;
  }

  public void setLatch(CountDownLatch latch) {
    this.latch = latch;
  }

  @Override
  public void run() {
    try {
      Random random = new Random();
      while (!getBattlefield().isEnd()) {
        synchronized (getBattlefield()) {
          if (isAlive) {
            act();
            getBattlefield().recordRound(this, roundNum);
            roundNum++;
          }
        }

        try {
          Thread.sleep(random.nextInt(500) + 500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } finally {
      latch.countDown();
    }
  }
}
