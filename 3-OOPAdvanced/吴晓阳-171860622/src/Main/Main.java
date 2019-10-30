package Main;

import figure.feature.Lifable;
import figure.finish.*;
import figure.finish.FinishedFigure;
import location.LocationUtils;
import myUtils.MyUtils;
import newgui.Console;
import newgui.GridFrame;

import java.awt.*;
import java.util.*;
import java.util.List;

/*
* 阵型，以（0,0）为基准点
 */
interface Formation{
    /*
    * 获取阵型
     */
    List<Point> getFormation();

    /*
    * 阵型的尺寸
     */
    Dimension getSize();

    /*
    * 阵型包含的人数
     */
    int getNum();
}

/*
* 蛇形阵型
 */
class SnakeFormation implements Formation{
    private List<Point> formation = new ArrayList<>();

    private Dimension size = new Dimension(10, 10);

    private SnakeFormation(){
        formation.add(new Point(5, 0));
        formation.add(new Point(5, 1));
        formation.add(new Point(5, 2));
        formation.add(new Point(5, 3));
        formation.add(new Point(5, 4));
        formation.add(new Point(5, 5));
        formation.add(new Point(5, 6));
    }

    @Override
    public List<Point> getFormation() {
        return Collections.unmodifiableList(formation);
    }

    @Override
    public Dimension getSize() {
        return new Dimension(size);
    }

    @Override
    public int getNum() {
        return formation.size();
    }

    private static final SnakeFormation instance = new SnakeFormation();

    public static SnakeFormation getInstance(){
        return instance;
    }
}

/*
* 雁行阵型
 */
class GooseFormation implements Formation{
    private List<Point> formation = new ArrayList<>();

    private Dimension size = new Dimension(10, 10);

    private GooseFormation(){
        formation.add(new Point(2, 6));
        formation.add(new Point(3, 5));
        formation.add(new Point(4, 4));
        formation.add(new Point(5, 3));
        formation.add(new Point(6, 2));
        formation.add(new Point(7, 1));
        formation.add(new Point(8, 0));
    }

    @Override
    public List<Point> getFormation() {
        return Collections.unmodifiableList(formation);
    }

    @Override
    public Dimension getSize() {
        return new Dimension(size);
    }

    @Override
    public int getNum() {
        return formation.size();
    }

    private static final GooseFormation instance = new GooseFormation();

    public static GooseFormation getInstance(){
        return instance;
    }
}

/*
* 鹤翼阵型
 */
class CraneWingFormation implements Formation{
    private List<Point> formation = new ArrayList<>();

    private Dimension size = new Dimension(10, 10);

    private CraneWingFormation(){
        formation.add(new Point(5, 6));
        formation.add(new Point(2, 3));
        formation.add(new Point(3, 4));
        formation.add(new Point(4, 5));
        formation.add(new Point(6, 5));
        formation.add(new Point(7, 4));
        formation.add(new Point(8, 3));
    }

    @Override
    public List<Point> getFormation() {
        return Collections.unmodifiableList(formation);
    }

    @Override
    public Dimension getSize() {
        return new Dimension(size);
    }

    @Override
    public int getNum() {
        return formation.size();
    }

    private static final CraneWingFormation instance = new CraneWingFormation();

    public static CraneWingFormation getInstance(){
        return instance;
    }
}

interface PathFinder{
    Queue<Point> findPath(Point src, Point dst);
}

class BFSPathFinder implements PathFinder {
    /*
    * map中的值的意义
    * -1：存在人物在该位置
    * 0：空位置
    * 1：往右
    * 2：往下
    * 3：往左
    * 4：往上
    * 5：从左方来
    * 6：从上方来
    * 7：从右方来
    * 8：从下方来
     */
    int[][] map;

    int row;

    int col;

    public BFSPathFinder(int[][] map, int row, int col){
        this.map = map;
        this.row = row;
        this.col = col;
    }

    @Override
    public Queue<Point> findPath(Point src, Point dst) {
        if(map[dst.x][dst.y] != 0) return null;

        int[][] tmpMap = map.clone();
        Queue<Point> workQueue = new LinkedList<>();
        Queue<Point> resQueue = new LinkedList<>();
        boolean suc = false;

        tmpMap[src.x][src.y] = -2;
        workQueue.offer(new Point(src));

        while(workQueue.isEmpty() == false){
            Point pos = workQueue.poll();
            assert(tmpMap[pos.x][pos.y] == 0);

            Point nextPos = new Point();

            nextPos.setLocation(pos.x + 1, pos.y);
            if(checkPos(nextPos) && tmpMap[nextPos.x][nextPos.y] == 0){
                tmpMap[nextPos.x][nextPos.y] = 5;
                if(nextPos.equals(dst)){ suc = true; break; }
                else{ workQueue.offer(new Point(nextPos)); }
            }

            nextPos.setLocation(pos.x, pos.y + 1);
            if(checkPos(nextPos) && tmpMap[nextPos.x][nextPos.y] == 0){
                tmpMap[nextPos.x][nextPos.y] = 6;
                if(nextPos.equals(dst)){ suc = true; break; }
                else{ workQueue.offer(new Point(nextPos)); }
            }

            nextPos.setLocation(pos.x - 1, pos.y);
            if(checkPos(nextPos) && tmpMap[nextPos.x][nextPos.y] == 0){
                tmpMap[nextPos.x][nextPos.y] = 7;
                if(nextPos.equals(dst)){ suc = true; break; }
                else{ workQueue.offer(new Point(nextPos)); }
            }

            nextPos.setLocation(pos.x, pos.y - 1);
            if(checkPos(nextPos) && tmpMap[nextPos.x][nextPos.y] == 0){
                tmpMap[nextPos.x][nextPos.y] = 8;
                if(nextPos.equals(dst)){ suc = true; break; }
                else{ workQueue.offer(new Point(nextPos)); }
            }
        }

        if(suc = false){
            return null;
        }
        else{// 反向回溯一次路径，再正向生成路径
            Point curPos = new Point(dst);
            while(curPos.equals(src) == false){
                switch(tmpMap[curPos.x][curPos.y]){
                    case 5:// from left
                        tmpMap[curPos.x - 1][curPos.y] = 1;
                        curPos.setLocation(curPos.x - 1, curPos.y);
                        break;
                    case 6:// from up
                        tmpMap[curPos.x][curPos.y - 1] = 2;
                        curPos.setLocation(curPos.x, curPos.y - 1);
                        break;
                    case 7:// from right
                        tmpMap[curPos.x + 1][curPos.y] = 3;
                        curPos.setLocation(curPos.x + 1, curPos.y);
                        break;
                    case 8:// from bottom
                        tmpMap[curPos.x][curPos.y + 1] = 4;
                        curPos.setLocation(curPos.x, curPos.y + 1);
                        break;
                    default:
                        throw new RuntimeException();
                }
            }

            while(curPos.equals(dst) == false){
                resQueue.offer(new Point(curPos));
                switch (tmpMap[curPos.x][curPos.y]){
                    case 1: curPos.setLocation(curPos.x + 1, curPos.y); break;
                    case 2: curPos.setLocation(curPos.x, curPos.y + 1); break;
                    case 3: curPos.setLocation(curPos.x - 1, curPos.y); break;
                    case 4: curPos.setLocation(curPos.x, curPos.y - 1); break;
                    default: throw new RuntimeException();
                }
            }
            resQueue.offer(new Point(curPos));
        }

        throw new RuntimeException();
    }

    private boolean checkPos(int r, int c){
        return (r > 0 && r < row && c > 0 && c < col);
    }

    private boolean checkPos(Point pos){
        return checkPos(pos.x, pos.y);
    }
}

public class Main {
    public static List<FinishedFigure> enemies = new ArrayList<>();

    public static List<FinishedFigure> calabashes = new ArrayList<>();

    public static final int GRID_ROW = 40;

    public static final int GRID_COL = 40;

    public static int[][] map = new int[GRID_ROW][GRID_COL];

    public static final Console frame = new GridFrame(GRID_ROW, GRID_COL);

    public static Point getEmptyPos(){
        Point pos = new Point();
        while(true){
            pos.setLocation(MyUtils.random.nextInt(GRID_ROW - 1), MyUtils.random.nextInt(GRID_COL - 1));
            Collection c = frame.getGridItems(pos.x, pos.y);
            if(c == null || c.isEmpty()){
                return pos;
            }
        }
    }

    public static void initCreature(){
        {
            enemies.add(new FinishedScorpion());
            enemies.add(new FinishedToad());
            enemies.add(new FinishedToad());
            enemies.add(new FinishedToad());
            enemies.add(new FinishedToad());
            enemies.add(new FinishedToad());
            enemies.add(new FinishedToad());

            enemies.forEach(enemy->{
                Point pos = getEmptyPos();
                map[pos.x][pos.y] = -1;
                frame.addItem(enemy, pos.x, pos.y);
            });
        }

        {
        calabashes.add(new FinishedDawa());
        calabashes.add(new FinishedErwa());
        calabashes.add(new FinishedSanwa());
        calabashes.add(new FinishedSiwa());
        calabashes.add(new FinishedWuwa());
        calabashes.add(new FinishedLiuwa());
        calabashes.add(new FinishedQiwa());

        calabashes.forEach(calabash->{
            Point pos = getEmptyPos();
            map[pos.x][pos.y] = -1;
            frame.addItem(calabash, pos.x, pos.y);
        });
    }
}

    public static void init(){
        initCreature();
    }

    public static Queue<Point> makePath(PathFinder finder, Point src, Point dst){
        return finder.findPath(src, dst);
    }

    public static void calabashesMoveTo(Formation formation){

    }

    public static void figuresMoveTo(List<FinishedFigure> figures, Formation formation, Point datum)throws Exception{
        ArrayList<Integer> processorIndexes = new ArrayList<>();
        for(int i = 0; i < formation.getNum(); i++){
            FinishedFigure figure = figures.get(i);
            Point dstGridPos = formation.getFormation().get(i);
            dstGridPos.setLocation(dstGridPos.x, dstGridPos.y * 2);
            dstGridPos.translate(datum.x, datum.y);

            Rectangle itemRect = frame.getItemPos(figure);
            Point srcGridPos = itemRect.getLocation();

            Dimension totalMove = LocationUtils.getGridMovePixelMomentum(srcGridPos, dstGridPos, frame.getGridSize());
            figure.setMomentum(totalMove);
        }

        Thread.sleep(1000);
        for(FinishedFigure figure : figures){
            System.out.println(frame.getItemPos(figure));
        }
    }

    public static void main(String[] args)throws Exception{
        init();
        frame.display();

        // 葫芦娃蛇形排队
        Thread.sleep(2000);
        figuresMoveTo(calabashes, SnakeFormation.getInstance(), new Point(15, 20));

        // 添加爷爷和妖精
        Thread.sleep(2000);
        frame.addItem(new FinishedSnake(), 5, 5,4, 4);
        frame.addItem(new FinishedGrandpa(), 5, 30, 4, 4);

        // 妖精雁阵排序
        Thread.sleep(2000);
        figuresMoveTo(enemies, GooseFormation.getInstance(), new Point(15, 0));

        // 妖精鹤翼排序
        Thread.sleep(2000);
        figuresMoveTo(enemies, CraneWingFormation.getInstance(), new Point(15, 0));


    }
}