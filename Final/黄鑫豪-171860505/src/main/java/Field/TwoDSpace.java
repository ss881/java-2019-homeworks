package Field;

import Beings.*;
import Beings.Creature;
import Strategy.FManager;
import GUI.LayoutControl;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class TwoDSpace {
    private final int ROW = 15;
    private final int COL = 15;

    private Position[][] tds;
    private FManager fManager;
    private LayoutControl layoutcontrol;

    private Grandpa grandpa;
    private EvilFather evilfather;
    private EvilMother evilmother;
    private ArrayList<EvilSon> evilsons;
    private ArrayList<GourdEva> gourdevas;
    private ArrayList<Thread> allThread;

    private ArrayList<ImageViewManager> allView;
    private ArrayList<Creature> goods;
    private ArrayList<Creature> bads;

    private boolean mode_battle = false;
    private boolean mode_replay = false;

    public TwoDSpace(LayoutControl layoutcontrol) {
        this.layoutcontrol = layoutcontrol;
        fManager = new FManager(this);
        layoutcontrol.setTDS(this);
    }

    public synchronized Position[][] getTDS() {
        return tds;
    }

    public ArrayList<ImageViewManager> getAllView() {
        return allView;
    }

    public ArrayList<GourdEva> getGourdevas() {
        return gourdevas;
    }

    public ArrayList<EvilSon> getEvilsons() {
        return evilsons;
    }

    public ArrayList<Creature> getGoods() {
        return goods;
    }

    public ArrayList<Creature> getBads() {
        return bads;
    }

    public Grandpa getGrandpa() {
        return grandpa;
    }

    public EvilFather getEvilfather() {
        return evilfather;
    }

    public EvilMother getEvilmother() {
        return evilmother;
    }

    public void initAllView() {
        this.tds = new Position[ROW][COL];

        for (int i = 0; i < ROW; ++i)
            for (int j = 0; j < COL; ++j) {
                tds[i][j] = new Position<>(i, j);
            }

        allView = new ArrayList<>();
        allThread = new ArrayList<>();
        evilsons = new ArrayList<>();
        gourdevas = new ArrayList<>();
        goods = new ArrayList<>();
        bads = new ArrayList<>();

        gourdevas.add(new GourdEva(GourdColor.RED, this));
        gourdevas.add(new GourdEva(GourdColor.ORANGE, this));
        gourdevas.add(new GourdEva(GourdColor.YELLOW, this));
        gourdevas.add(new GourdEva(GourdColor.GREEN, this));
        gourdevas.add(new GourdEva(GourdColor.BLUE, this));
        gourdevas.add(new GourdEva(GourdColor.CYAN, this));
        gourdevas.add(new GourdEva(GourdColor.PURPLE, this));
        grandpa = new Grandpa(this);
        goods.addAll(gourdevas);
        goods.add(grandpa);

        for (int i = 0; i < 7; ++i) {
            evilsons.add(new EvilSon(this));
        }
        evilfather = new EvilFather(this);
        evilmother = new EvilMother(this);
        bads.addAll(evilsons);
        bads.add(evilfather);
        bads.add(evilmother);

        fManager.apply();
        setImageView();
    }

    public boolean isMode_battle() {
        return mode_battle;
    }

    public boolean isMode_replay() {
        return mode_replay;
    }

    private void setImageView() {
        for (int i = 0; i < 7; ++i) {
            GourdEva tmpGourdeva = gourdevas.get(i);
            tmpGourdeva.setViewIndex(i);
            allView.add(new ImageViewManager(tmpGourdeva.getX(), tmpGourdeva.getY(), tmpGourdeva.getImage()));
        }
        grandpa.setViewIndex(7);
        allView.add(new ImageViewManager(grandpa.getX(), grandpa.getY(), grandpa.getImage()));
        evilmother.setViewIndex(8);
        allView.add(new ImageViewManager(evilmother.getX(), evilmother.getY(), evilmother.getImage()));
        evilfather.setViewIndex(9);
        allView.add(new ImageViewManager(evilfather.getX(), evilfather.getY(), evilfather.getImage()));
        for (int i = 0; i < 7; ++i) {
            EvilSon tmpEvilson = evilsons.get(i);
            tmpEvilson.setViewIndex(10 + i);
            allView.add(new ImageViewManager(tmpEvilson.getX(), tmpEvilson.getY(), tmpEvilson.getImage()));
        }
    }

    private void setAllRun() {
        grandpa.setRunIndex(0);
        this.allThread.add(new Thread(grandpa));
        evilfather.setRunIndex(1);
        this.allThread.add(new Thread(evilfather));
        evilmother.setRunIndex(2);
        this.allThread.add(new Thread(evilmother));
        for (int i = 0; i < 7; ++i) {
            gourdevas.get(i).setRunIndex(3 + 2 * i);
            this.allThread.add(new Thread(gourdevas.get(i)));
            evilsons.get(i).setRunIndex(4 + 2 * i);
            this.allThread.add(new Thread((evilsons.get(i))));
        }
    }

    public void startBattle() {
        initAllView();
        layoutcontrol.paintAllView();
        mode_battle = true;
        setAllRun();
        for (Thread t : allThread) {
            t.start();
        }
    }

    public void drawOneCreature(int i) {
        layoutcontrol.paintSomeView(i);
    }

    public void stopOneRun(int i) {
        allThread.get(i).interrupt();
    }

    public synchronized void Fight(Creature c1, Creature c2, Random rand) {
        if (!c1.isAlive() || !c2.isAlive()) return;
        int tmp = rand.nextInt(2);
        if (tmp == 0) {
            c1.setHealth(0);
            //在这里不将死者去除，使得其他生物不会踩在死者上
            //tds[c1.getX()][c1.getY()].init();
            writeFight(c1);
        } else {
            c2.setHealth(0);
            //tds[c2.getX()][c2.getY()].init();
            writeFight(c2);
        }
    }

    public synchronized boolean Move(Creature c1, Creature c2, Random rand) {
        //获取随机移动步长
        int stepsMove = rand.nextInt(6);
        // 获取随机状态
        int tmp = rand.nextInt(5);
        // 状态 0 不移动
        if (tmp == 0) return false;

        if (tmp <= 1) {
            //随机尝试
            int[] xs = {1, 0, -1, 0};
            int[] ys = {0, 1, 0, -1};
            int randMove = rand.nextInt(4);
            int tmpx = c1.getX();
            int tmpy = c1.getY();
            int k = 0;
            while (k < stepsMove && checkMove(tmpx + xs[randMove], tmpy + ys[randMove]) && tds[tmpx + xs[randMove]][tmpy + ys[randMove]].isEmpty()) {
                tmpx += xs[randMove];
                tmpy += ys[randMove];
                k++;
            }
            if (k > 0) {
                tds[c1.getX()][c1.getY()].init();
                tds[tmpx][tmpy].setHolder(c1);
                c1.setXY(tmpx, tmpy);
                writeMove(c1);
                return true;
            }
            return false;
        }

        // 计算目标之间横纵坐标的差值
        int dx = c2.getX() - c1.getX();
        int dy = c2.getY() - c1.getY();
        // 确定移动的方向
        int mx = (dx == 0) ? 0 : dx / Math.abs(dx);
        int my = (dy == 0) ? 0 : dy / Math.abs(dy);
        // 尝试纵轴方向上移动，成功则完成移动
        if (mx != 0 && tds[c1.getX() + mx][c1.getY()].isEmpty()) {
            tds[c1.getX()][c1.getY()].init();
            tds[c1.getX() + mx][c1.getY()].setHolder(c1);
            c1.setXY(c1.getX() + mx, c1.getY());
            writeMove(c1);
            return true;
        }
        // 尝试横轴方向上移动，成功则完成移动
        if (my != 0 && tds[c1.getX()][c1.getY() + my].isEmpty()) {
            tds[c1.getX()][c1.getY()].init();
            tds[c1.getX()][c1.getY() + my].setHolder(c1);
            c1.setXY(c1.getX(), c1.getY() + my);
            writeMove(c1);
            return true;
        }
        // 尝试横纵方向上的反向移动
        if (mx != 0 && checkMove(c1.getX() - mx, 0) && tds[c1.getX() - mx][c1.getY()].isEmpty()) {
            tds[c1.getX()][c1.getY()].init();
            tds[c1.getX() - mx][c1.getY()].setHolder(c1);
            c1.setXY(c1.getX() - mx, c1.getY());
            writeMove(c1);
            return true;
        }
        if (my != 0 && checkMove(0, c1.getY() - my) && tds[c1.getX()][c1.getY() - my].isEmpty()) {
            tds[c1.getX()][c1.getY()].init();
            tds[c1.getX()][c1.getY() - my].setHolder(c1);
            c1.setXY(c1.getX(), c1.getY() - my);
            writeMove(c1);
            return true;
        }

        return false;
    }


    private boolean checkMove(int x, int y) {
        return (x < ROW) && (x >= 0) && (y < COL) && (y >= 0);
    }

    private FileWriter fileWriter;

    public void setFile(File file) {
        try {
            fileWriter = new FileWriter(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        if (fileWriter == null) return;
        try {
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 移动记录： “0”+图像索引+生物坐标
    private void writeMove(Creature c1) {
        String str = "0 " + c1.getViewIndex() + " " + c1.getX() + " " + c1.getY() + "\n";
        try {
            fileWriter.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 战斗记录： “1”+（死亡生物）图像索引
    private void writeFight(Creature c1) {
        String str = "1 " + c1.getViewIndex() + "\n";
        try {
            fileWriter.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关于回放的部分

    private Thread thread;

    public void replay(File file) {
        mode_replay = true;
        initReplay();
        layoutcontrol.paintAllView();
        Replay rp = new Replay(file, this);
        thread = new Thread(rp);
        thread.start();
        System.out.print("here");
    }

    private void initReplay() {
        FManager tmpf = new FManager(this);
        int[] tmpgx = tmpf.getGourdX();
        int[] tmpgy = tmpf.getGourdY();
        int[] tmpex = tmpf.getEvilX();
        int[] tmpey = tmpf.getEvilY();

        allView = new ArrayList<>();
        for (int i = 0; i < 7; ++i) {
            allView.add(new ImageViewManager(tmpgx[i], tmpgy[i], new Image((i + 1) + ".png")));
        }
        allView.add(new ImageViewManager(tmpf.getgpax(), tmpf.getgpay(), new Image("grandpa.png")));
        allView.add(new ImageViewManager(tmpf.getemx(), tmpf.getemy(), new Image("evilmother.png")));
        allView.add(new ImageViewManager(tmpf.getefx(), tmpf.getefy(), new Image("evilfather.png")));
        for (int i = 0; i < 7; ++i) {
            allView.add(new ImageViewManager(tmpex[i], tmpey[i], new Image("evilson.png")));
        }
    }

    void doView(int[] arr) {
        if (arr[0] == 0) {
            allView.get(arr[1]).setX(arr[2]);
            allView.get(arr[1]).setY(arr[3]);
        } else {
            allView.get(arr[1]).getIV().setImage(new Image("dead.png"));
        }
    }

    void shutRedo() {
        thread.interrupt();
    }
}


