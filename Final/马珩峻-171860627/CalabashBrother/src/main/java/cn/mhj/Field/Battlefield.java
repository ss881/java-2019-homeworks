package cn.mhj.Field;

import cn.mhj.Controller.BattlefieldController;
import cn.mhj.Creature.EnemyCamp;
import cn.mhj.Creature.FightCreature;
import cn.mhj.Creature.SelfCamp;
import cn.mhj.Enum.FormationType;
import cn.mhj.Enum.GameStatus;
import cn.mhj.Reply.Recorder;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.scene.canvas.GraphicsContext;

public class Battlefield implements Runnable {

  private static final int DEFAULT_WIDTH = 20, DEFAULT_HEIGHT = 15;
  private int width, height;

  private Unit<FightCreature>[][] map;
  private SelfCamp selfCamp;
  private EnemyCamp enemyCamp;
  private GameStatus gameStatus;
  private GraphicsContext battleMap;
  private Recorder recorder;
  private BattlefieldController battlefieldController;

  public Battlefield() {
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;
    this.map = new Unit[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.map[i][j] = new Unit<FightCreature>(new Position(i, j), battleMap);
      }
    }
  }

  public Battlefield(
      GameStatus gameStatus,
      GraphicsContext battleMap,
      BattlefieldController battlefieldController) {
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;
    this.gameStatus = gameStatus;
    this.battleMap = battleMap;
    this.battlefieldController = battlefieldController;
    this.recorder = new Recorder();
    this.map = new Unit[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.map[i][j] = new Unit<FightCreature>(new Position(i, j), battleMap);
      }
    }
    try {
      this.selfCamp = new SelfCamp(this, new Range(0, this.height, 0, (this.width - 1) / 2));
      this.enemyCamp =
          new EnemyCamp(this, new Range(this.height - 1, 0, this.width - 1, (this.width + 1) / 2));
      this.selfCamp.setEnemies(this.enemyCamp.getSoldiers());
      this.enemyCamp.setEnemies(this.selfCamp.getCalabashBrother());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public BattlefieldController getBattlefieldController() {
    return battlefieldController;
  }

  public Vector<FightCreature> getCreatures(int x, int y) {
    return map[x][y].getCreatures();
  }

  public Vector<FightCreature> getCreatures(Position position) {
    return getCreatures(position.getX(), position.getY());
    //    return this.map[position.getX()][position.getY()].getCreatures();
  }

  public synchronized void addCreature(int x, int y, FightCreature fightCreature) {
    map[x][y].addCreature(fightCreature);
  }

  public synchronized void addCreature(Position position, FightCreature fightCreature) {
    addCreature(position.getX(), position.getY(), fightCreature);
    //    this.map[position.getX()][position.getY()].addCreature(fightCreature);
  }

  public synchronized void removeCreature(FightCreature fightCreature) {
    map[fightCreature.getPosition().getX()][fightCreature.getPosition().getY()].removeCreature(
        fightCreature);
  }

  public boolean hasCreature(int x, int y) {
    return map[x][y].hasCreature();
  }

  public boolean hasCreature(Position position) {
    return hasCreature(position.getX(), position.getY());
  }

  public Vector<FightCreature> getAllCreatures() {
    Vector<FightCreature> allCreatures = new Vector<>();
    allCreatures.addAll(selfCamp.getCalabashBrother());
    allCreatures.addAll(enemyCamp.getSoldiers());
    return allCreatures;
  }

  public void setSelfFormation(FormationType formationType) {
    this.selfCamp.setFormation(formationType);
  }

  public void setEnemyFormation(FormationType formationType) {
    this.enemyCamp.setFormation(formationType);
  }

  public void setGameStatus(GameStatus gameStatus) {
    this.gameStatus = gameStatus;
  }

  public void setLatch(CountDownLatch latch, Executor executor) {
    Vector<FightCreature> allCreatures = getAllCreatures();
    for (FightCreature creature : allCreatures) {
      creature.setLatch(latch);
      executor.execute(creature);
    }
    System.out.println("set latch success");
  }

  public synchronized void recordRound(FightCreature fightCreature, int roundNum) {
    recorder.addRecord(fightCreature, roundNum);
  }

  public boolean checkPosition(Position position) {
    return position.getX() >= 0
        && position.getX() < width
        && position.getY() >= 0
        && position.getY() < height;
  }

  public boolean isEnd() {
    return selfCamp.isLoss() || enemyCamp.isLoss();
  }

  private void battle() {
    recorder.initRecorder(this);
    int threadNum = selfCamp.getCalabashBrother().size() + enemyCamp.getSoldiers().size();
    Executor executor = Executors.newFixedThreadPool(threadNum);
    CountDownLatch latch = new CountDownLatch(threadNum);
    setLatch(latch, executor);
    try {
      latch.await(20, TimeUnit.SECONDS);
      battleWin();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void battleWin() {
    System.out.println("battle end");
    String winner = "";
    if (selfCamp.isLoss()) {
      winner = "妖精";
    } else {
      winner = "葫芦娃";
    }
    System.out.println(winner + "win!");
    //    Alert winDialog = new Alert(AlertType.INFORMATION, "winner:" + winner);
    //    winDialog.setTitle("battle end");
    //    winDialog.showAndWait();
    recorder.writeRecord(winner);
    battlefieldController.setGameStatus(GameStatus.END);
  }

  @Override
  public void run() {
    System.out.println(gameStatus.toString());
    if (gameStatus == GameStatus.RUNNING) {
      battle();
    }
  }
}
