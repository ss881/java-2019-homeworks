package ground;

import creature.*;
import formulation.*;
import log.LogWriter;
import trajectory.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ground {

    private static final int ROWS = 16, COLS = 23; // COLS is x | column, ROWS is y | row
    private static final int IMAGE_SIZE = 32;
    private static final int TRAJECTORY_SIZE = 20;
    private static final int HULUWA_NUM = 7;
    private static final int CREEP_NUM = 19;
    private final Position[][] positions = new Position[ROWS][COLS];

    private ArrayList<Good> huluwas = new ArrayList<>(); // 葫芦娃数组
    private ArrayList<Bad> creeps = new ArrayList<>(); // 妖怪数组，第0个为Scorpion
    private final ArrayList<Trajectory> trajectories = new ArrayList<>(); // 弹道数组
    private Grandpa grandpa = null;
    private Snake snake = null;
    private boolean isInited = false;
    private boolean isBattling = false;
    private ExecutorService pool = null;

    private LogWriter logWriter;
    private TextArea battleLogArea;

    public Ground(TextArea battleLogArea) {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                positions[i][j] = new Position(i, j);
            }
        }
        this.battleLogArea = battleLogArea;
    }

    /* related to BattleController */

    public void groundInit(LogWriter logWriter) throws InvocationTargetException, IllegalAccessException {
        if (isBattling)
            return;
        this.logWriter = logWriter;
        Generator generator = new Generator();
        generator.genGrandpa(this, (ROWS - 1) / 2, 0);
        generator.genSnake(this, (ROWS - 1) / 2, COLS - 1);
        Formulation form = new Formulation(this);
        Method[] methods = form.getSortedDeclaredMethods();
        System.out.print("Huluwas ");
        battleLogArea.appendText("Huluwas ");
        methods[(int) (Math.random() * 8)].invoke(form, Side.Good, battleLogArea);
        System.out.print("Creeps ");
        battleLogArea.appendText("Creeps ");
        methods[(int) (Math.random() * 8)].invoke(form, Side.Bad, battleLogArea);
        isInited = true;
        if(huluwas.size() != HULUWA_NUM || creeps.size() != CREEP_NUM + 1) {
            System.out.println("number error!");
            System.exit(0);
        }
    }

    public void groundStart() {
        if (!isInited)
            return;
        pool = Executors.newCachedThreadPool();
        for(Good good : huluwas) {
            pool.execute(good);
        }
        for(Bad bad : creeps) {
            pool.execute(bad);
        }
        pool.execute(grandpa);
        pool.execute(snake);
        pool.shutdown();
        isBattling = true;
    }

    public void groundClear() {
        if (isBattling)
            return;
        synchronized (trajectories) {
            trajectories.clear();
        }
        try {
            if (pool != null) {
                pool.shutdownNow();
                pool.awaitTermination(50, TimeUnit.MILLISECONDS);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        huluwas.clear();
        creeps.clear();
        grandpa = null;
        snake = null;
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                positions[i][j].removeCreature();
                positions[i][j].removeDeadCreatures();
            }
        }
        isInited = false;
        isBattling = false;
    }

    /* related to Creature */

    /**
     * 绘制creatures。若creature存活，绘制其图像。若position上有死亡creature，绘制其死亡图像。
     * 存活图像优先级高于死亡图像。若position上有多个死亡creature，绘制最新死亡者的死亡图像。
     * @param context .
     */
    public void display(GraphicsContext context) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ArrayList<Creature> deadCreatures = positions[i][j].getDeadCreatures();
                if (deadCreatures.size() != 0) {
                    Creature c = deadCreatures.get(0);
                    logWriter.writeLog(c);
                    context.drawImage(c.getTombImage(), c.getPosition().getX(), c.getPosition().getY(), IMAGE_SIZE, IMAGE_SIZE);
                }
                Creature creature = positions[i][j].getCreature();
                if (creature == null)
                    continue;
                logWriter.writeLog(creature);
                context.drawImage(creature.getImage(), creature.getPosition().getX(), creature.getPosition().getY(), IMAGE_SIZE, IMAGE_SIZE);
                context.setFill(Color.RED);
                context.fillRoundRect(positions[i][j].getX(), positions[i][j].getY() - 5, IMAGE_SIZE, 5, 10, 10);
                if (creature instanceof Good)
                    context.setFill(Color.LIGHTGREEN);
                else
                    context.setFill(Color.DEEPSKYBLUE);
                double ratio = creature.getCurHealth() / creature.getMaxHealth();
                context.fillRoundRect(positions[i][j].getX(), positions[i][j].getY() - 5, IMAGE_SIZE * ratio, 5, 10, 10);
            }
        }
    }

    /**
     * 若无Huluwa存活，返回2；若无Creep存活，返回1；若非前两种情况，返回0。
     * @return 返回0则未有胜负；返回1则Huluwas胜；返回2则Creeps胜。（规则待修改）
     */
    public int whoWins() {
        boolean isHuluwaAlive = false;
        for (Good huluwa : huluwas) {
            if (!huluwa.isDead()) {
                isHuluwaAlive = true;
                break;
            }
        }
        if (!isHuluwaAlive) {
            isBattling = false;
            return 2;
        }
        boolean isCreepAlive = false;
        for (Bad creep : creeps) {
            if (!creep.isDead()) {
                isCreepAlive = true;
                break;
            }
        }
        if (!isCreepAlive) {
            isBattling = false;
            return 1;
        }
        return 0;
    }

    public double getDistance(Creature src, Creature dst) { return Math.sqrt(Math.pow((src.getPosition().getRow() - dst.getPosition().getRow()), 2) + Math.pow((src.getPosition().getCol() - dst.getPosition().getCol()), 2)); }

    public Creature getNearestEnemy(Creature src) {
        Creature nearestCreature = null;
        double minDistance = 100;
        if(src instanceof Good) {
            for(Bad enemy : creeps) {
                if(!enemy.isDead()) {
                    double tmpDistance = getDistance(src, enemy);
                    if (tmpDistance < minDistance) {
                        minDistance = tmpDistance;
                        nearestCreature = enemy;
                    }
                }
            }
        }
        else { // this instanceof Bad
            for(Good enemy : huluwas) {
                if(!enemy.isDead()) {
                    double tmpDistance = getDistance(src, enemy);
                    if (tmpDistance < minDistance) {
                        minDistance = tmpDistance;
                        nearestCreature = enemy;
                    }
                }
            }
        }
        return nearestCreature;
    }

    /**
     * if ((src is adjacent to dst) or
     * (src and dst share a and only a vertex and there are two creatures adjacent to both of them)),
     * src is unmovable, otherwise movable
     * @param src the source
     * @param dst the dest
     * @return true means src is movable while false means src is unmovable
     */
    public boolean isMovable(Creature src, Creature dst) {
        if (dst == null) // 敌人死光了，可以移动，但没必要
            return false;
        if (getDistance(src, dst) <= 1)
            return false;
        if (getDistance(src, dst) != Math.sqrt(2))
            return true;
        return !positions[src.getPosition().getRow()][dst.getPosition().getCol()].isOccupied() || !positions[dst.getPosition().getRow()][src.getPosition().getCol()].isOccupied();
    }

    public void addCreatureAt(Creature creature, int row, int col) {
        if(positions[row][col].isOccupied())
            return;
        positions[row][col].setCreature(creature);
        creature.setPosition(positions[row][col]);
        if(creature instanceof Huluwa) {
            huluwas.add((Huluwa)creature);
        }
        else if(creature instanceof Grandpa) {
            grandpa = (Grandpa)creature;
        }
        else if(creature instanceof Scorpion) {
            creeps.add((Scorpion)creature);
        }
        else if(creature instanceof Creep) {
            creeps.add((Creep)creature);
        }
        else { // Snake
            snake = (Snake)creature;
        }
    }

    public void moveCreatureTo(Creature creature, int row, int col) {
        if (positions[row][col].isOccupied())
            return;
        creature.getPosition().removeCreature();
        positions[row][col].setCreature(creature);
        creature.setPosition(positions[row][col]);
    }

    /* related to Trajectory */

    /**
     * 绘制trajectories并进行攻击判定。若非SnakeTrajectory且目标已死，删去该trajectory。
     * 攻击判定简易，如代码。
     * @param context .
     */
    public void displayTrajectory(GraphicsContext context) {
        synchronized (trajectories) {
            Iterator<Trajectory> iterator = trajectories.iterator();
            while (iterator.hasNext()) {
                Trajectory t = iterator.next();
                if (!(t instanceof SnakeTrajectory) && t.getTarget().isDead()) {
                    iterator.remove();
                    continue;
                }
                logWriter.writeLog(t);
                context.drawImage(t.getImage(), t.getX(), t.getY(), TRAJECTORY_SIZE, TRAJECTORY_SIZE);
                if (t instanceof SnakeTrajectory) { // Snake的弹道
                    if (t.getSpeed() * t.getLiveTime() * 0.001 >= COLS - 1) {
                        if (!t.getTarget().isDead() && !t.getAttacker().isDead() && (int) (t.getY() / IMAGE_SIZE) == grandpa.getPosition().getRow()) {
                            t.doDamage();
                        }
                        iterator.remove();
                    }
                }
                else { // Huluwa, Scorpion和Creep的弹道
                    if (t.getSpeed() * t.getLiveTime() * 0.001 >= 1) {
                        if (!t.getTarget().isDead() && !t.getAttacker().isDead() && t.getTarget().equals(t.getTargetPosition().getCreature())) {
                            t.doDamage();
                        }
                        iterator.remove();
                    }
                }
                /* unfinished */
            }
        }
    }

    public void addTrajectory(Trajectory trajectory) {
        synchronized (trajectories) {
            trajectories.add(trajectory);
        }
    }

    public void trajectoriesMove(int millisecond) {
        synchronized (trajectories) {
            for (Trajectory t : trajectories) {
                try {
                    t.move(millisecond);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                /* unfinished */
            }
        }
    }

    /* getter and setter */

    public int getRows() { return ROWS; }

    public int getCols() { return COLS; }

    public int getHuluwaNum() { return HULUWA_NUM; }

    public int getCreepNum() { return CREEP_NUM; }

    public Position getPosition(int row, int col) {
        if (row < 0 || row >= 16 || col < 0 || col >= 23)
            return null;
        return positions[row][col];
    }

    public boolean isOccupied(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
            return true;
        return positions[row][col].isOccupied();
    }

    public ArrayList<Good> getHuluwas() { return huluwas; }

    public ArrayList<Bad> getCreeps() { return creeps; }

    public ArrayList<Trajectory> getTrajectories() { return trajectories; }

    public Grandpa getGrandpa() { return grandpa; }

    public Snake getSnake() { return snake; }

    public TextArea getBattleLogArea() { return battleLogArea; }

    public boolean isInited() { return isInited; }

    public boolean isBattling() { return isBattling; }
}
