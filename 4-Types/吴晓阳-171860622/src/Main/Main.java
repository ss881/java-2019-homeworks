package Main;

import creator.BasicCreator;
import creator.Creator;
import figure.feature.Lifable;
import figure.finish.*;
import figure.finish.FinishedFigure;
import imgs.ImageRepository;
import imgs.ImageUtils;
import location.LocationUtils;
import myUtils.MyUtils;
import newgui.Console;
import newgui.GridFrame;

import java.awt.*;
import java.text.Normalizer;
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

class FormationCreator implements Creator<Formation>{
    @Override
    public Formation create(Object... args) {
        return new Formation() {
            private List<Point> form = new ArrayList<>();{
                for(int i = 0; i < args.length; i++){
                    form.add((Point)args[i]);
                }
            }

            @Override
            public List<Point> getFormation() {
                return Collections.unmodifiableList(form);
            }

            @Override
            public Dimension getSize() {
                return null;
            }

            @Override
            public int getNum() {
                return form.size();
            }
        };
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
        Creator<FinishedFigure> creator = new BasicCreator<>(FinishedFigure.class, Image.class);
        {
            FinishedFigure scorpion = creator.create(ImageRepository.getImage("Scorpion", new Dimension(50, 50)));
            FinishedFigure toad1 = creator.create(ImageRepository.getImage("Toad", new Dimension(50, 50)));
            FinishedFigure toad2 = creator.create(ImageRepository.getImage("Toad", new Dimension(50, 50)));
            FinishedFigure toad3 = creator.create(ImageRepository.getImage("Toad", new Dimension(50, 50)));
            FinishedFigure toad4 = creator.create(ImageRepository.getImage("Toad", new Dimension(50, 50)));
            FinishedFigure toad5 = creator.create(ImageRepository.getImage("Toad", new Dimension(50, 50)));
            FinishedFigure toad6 = creator.create(ImageRepository.getImage("Toad", new Dimension(50, 50)));
            enemies.add(scorpion);
            enemies.add(toad1);
            enemies.add(toad2);
            enemies.add(toad3);
            enemies.add(toad4);
            enemies.add(toad5);
            enemies.add(toad6);

            enemies.forEach(enemy->{
                Point pos = getEmptyPos();
                map[pos.x][pos.y] = -1;
                frame.addItem(enemy, pos.x, pos.y);
            });
        }

        {
        calabashes.add(creator.create(ImageRepository.getImage("Dawa", new Dimension(50, 50))));
        calabashes.add(creator.create(ImageRepository.getImage("Erwa", new Dimension(50, 50))));
        calabashes.add(creator.create(ImageRepository.getImage("Sanwa", new Dimension(50, 50))));
        calabashes.add(creator.create(ImageRepository.getImage("Siwa", new Dimension(50, 50))));
        calabashes.add(creator.create(ImageRepository.getImage("Wuwa", new Dimension(50, 50))));
        calabashes.add(creator.create(ImageRepository.getImage("Liuwa", new Dimension(50, 50))));
        calabashes.add(creator.create(ImageRepository.getImage("Qiwa", new Dimension(50, 50))));

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
        FormationCreator creator = new FormationCreator();

        // 生成蛇形阵型
        Formation snakeFormation = creator.create(
                new Point(5, 0),
                new Point(5, 1),
                new Point(5, 2),
                new Point(5, 3),
                new Point(5, 4),
                new Point(5, 5),
                new Point(5, 6)
        );

        // 葫芦娃蛇形排队
        Thread.sleep(2000);
        figuresMoveTo(calabashes, snakeFormation, new Point(15, 20));

        // 添加爷爷和妖精
        Thread.sleep(2000);
        frame.addItem(new FinishedSnake(), 5, 5,4, 4);
        frame.addItem(new FinishedGrandpa(), 5, 30, 4, 4);

        // 生成雁阵阵型
        Formation gooseFormation = creator.create(
                new Point(2, 6),
                new Point(3, 5),
                new Point(4, 4),
                new Point(5, 3),
                new Point(6, 2),
                new Point(7, 1),
                new Point(8, 0)
        );

        // 妖精雁阵排序
        Thread.sleep(2000);
        figuresMoveTo(enemies, gooseFormation, new Point(15, 0));

        // 生成鹤翼阵型
        Formation craneWingFormation = creator.create(
                new Point(5, 6),
                new Point(2, 3),
                new Point(3, 4),
                new Point(4, 5),
                new Point(6, 5),
                new Point(7, 4),
                new Point(8, 3)
        );
        // 妖精鹤翼排序
        Thread.sleep(2000);
        figuresMoveTo(enemies, CraneWingFormation.getInstance(), new Point(15, 0));


    }
}