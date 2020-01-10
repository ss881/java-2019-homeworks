import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.*;
import java.util.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.util.*;

@CreatureAnnotation
public class Creature implements Runnable{
    //生物体的属性
    protected String name = null; //姓名
    protected int x = -1, y = -1; //坐标
    protected ImageView creatureIMG = null; //图像
    protected int HP = 100; //血量
    protected int damage = 1; //伤害
    protected boolean alive = true; //存活状态
    protected int camp = 0; //所属阵营: 0中立, 1好人, -1好人

    private static final int eyesignt = 15;
    public static World world;
    public static BorderPane pane;
    public static String savePath;

    Creature(){}

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            if (this.action() == false) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
    //定义Creature对象在地图上的动作
    public synchronized boolean action() {
        if(this.alive == false) //死后无动作, 进程结束
            return false;
        if(this.HP <= 0) { //由存活状态变成死亡状态, 进程结束
            this.die();
            return false;
        }
        //敌人全部死亡, 进程结束
        if(God.isEnd() == true) {
            System.out.println("战斗结束");
            if(God.isShowed() == false) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ImageView img;
                        if(camp == 1)
                            img = new ImageView(new Image("victory.png",231,115,true,true));
                        else
                            img = new ImageView(new Image("defeat.png",241,118,true,true));
                        img.setX(500);
                        img.setY(450);
                        Creature.pane.getChildren().add(img);
                    }
                });
                God.setShowResult(true);
            }
            return false;
        }
        //存在敌人时
        if(this.camp != 0) { //需要寻找敌人进行攻击
           Node node = this.findEnemy();
            //没有敌人则随机移动一个单位
            if(node.x == -1 && node.y == -1) {
                this.randomWalk();
            }
            else {//向敌人所在方向移动一个单位的距离或对身边敌人发动攻击
                int x = node.x, y = node.y;
                if(world.map[x][y] != null) {
                    this.attack(world.map[x][y]);
                    //System.out.println("攻击");
                }
                else {
                    this.walk(x,y);
                    //System.out.println("有目的的移动");
                }
            }
           //this.randomWalk();
        }
        else { //中立生物不会攻击
            this.randomWalk();
        }
        return true;
    }

    public synchronized Node findEnemy() {
        Node nextStep = new Node();
        int n = world.getSize();
        int xmin = Math.max(x - eyesignt, 0);
        int ymin = Math.max(y - eyesignt, 0);
        int xmax = Math.min(x + eyesignt, n-1);
        int ymax = Math.min(y + eyesignt, n-1);
        int i, j;
        //遍历自身周围，寻找存活的敌人
        int distance = 200;
        for(i = xmin; i <= xmax; i++) {
            for( j = ymin; j <= ymax; j++) {
                if(world.map[i][j] != null && world.map[i][j].camp != 0
                        && world.map[i][j].camp + this.camp == 0 && world.map[i][j].alive == true) {
                    //System.out.println(world.map[i][j].tellName());
                    Road road = new Road(this.world);
                    Node node1 = new Node(this.x, this.y);
                    Node node2 = new Node(i, j);
                    ArrayList<Node> res = road.findRoad(node1, node2);
                    if(res.isEmpty() == true) {
                        continue;
                    }
                    int size = res.size();
                    if(res.get(0).distance < distance) {
                        distance = res.get(0).distance;
                        nextStep.x = res.get(size-2).x;
                        nextStep.y = res.get(size-2).y;
                    }
                }
            }
        }
        return nextStep;
    }
    public synchronized void attack(Creature enemy) {
        if(enemy == null)
            return;
        if(enemy.alive == false)
            return;
        enemy.HP -= this.damage;
    }
    public synchronized boolean walk(int x, int y) {
        int n = world.getSize();
        if(x<0 || y<0 || x >= n || y>= n){
            System.out.println("目的坐标不在已知空间中，无法移动至目的坐标");
            return false;
        }
        if (world.map[x][y] != null) {
            System.out.println("目的坐标存在生物，无法移动至目的坐标"+this.name + "->" +  world.map[x][y].tellName()
            + this.x +" "+this.y+" "+x+" "+y);
            return false;
        }

        world.map[x][y] = this;
        if (this.x >= 0 && this.y >= 0) {
            world.map[this.x][this.y] = null;
        }
        //System.out.println(name + "(" + this.x + "," + this.y + ")->(" + x + "," + y + ")" + HP);

        ImageTranslation imageTranslation = new ImageTranslation(creatureIMG, this.x, this.y, x, y);
        Platform.runLater(imageTranslation);
        /*final int xx = this.x, yy = this.y, xxx = x, yyy = y;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ImageView img = getCreatureIMG();
                int x1 = xx, y1 = yy, x2 = xxx, y2 = yyy;
                Timeline timeline = new Timeline();
                KeyValue xValue1 = new KeyValue(img.xProperty(),y1*50);
                KeyValue xValue2 = new KeyValue(img.xProperty(),y2*50);
                KeyValue yValue1 = new KeyValue(img.yProperty(),x1*50);
                KeyValue yValue2 = new KeyValue(img.yProperty(),x2*50);
                Duration duration1 = new Duration(0);
                Duration duration2 = new Duration(1000);
                KeyFrame xFrame1 = new KeyFrame(duration1, xValue1);
                KeyFrame yFrame1 = new KeyFrame(duration1, yValue1);
                KeyFrame xFrame2 = new KeyFrame(duration2, xValue2);
                KeyFrame yFrame2 = new KeyFrame(duration2, yValue2);
                timeline.getKeyFrames().addAll(xFrame1,yFrame1,xFrame2,yFrame2);
                timeline.play();
            }
        });*/
        save(this.x, this.y, x, y);
        this.x = x;
        this.y = y;
        //this.creatureIMG.setX(y*50);
        //this.creatureIMG.setY(x*50);

        return true;
    }
    public synchronized void randomWalk() {
        int N = world.getSize();
        ArrayList<Node> arr = new ArrayList<Node>();
        if(x-1 >= 0 && world.map[x-1][y] == null)
            arr.add(new Node(x-1,y));
        if(x+1 < N && world.map[x+1][y] == null)
            arr.add(new Node(x+1,y));
        if(y-1 >= 0 && world.map[x][y-1] == null)
            arr.add(new Node(x,y-1));
        if(y+1 < N && world.map[x][y+1] == null)
            arr.add(new Node(x,y+1));
        int size = arr.size();
        if(size == 0)
            return;
        Random random = new Random();
        int index = Math.abs(random.nextInt() % 256) % size;
        Node node = arr.get(index);
       // System.out.println(name+"("+x + "," +y+")->"+"("+node.x + "," +node.y+")");
        this.walk(node.x, node.y);
    }
    public synchronized void die() {
        this.alive = false;
        this.HP = 0;
        if(this.camp == 1)
            God.justDec();
        if(this.camp == -1)
            God.evilDec();

        ImageToDead imagetoDead = new ImageToDead(this);
        Platform.runLater(imagetoDead);
        /*Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ImageView imgDead = new ImageView(new Image("dead.png",
                        50, 50, true, true));
                imgDead.setX(y*50);
                imgDead.setY(x*50);
                ImageView imgAlive = getCreatureIMG();
                setCreatureIMG(imgDead);
                Creature.pane.getChildren().remove(imgAlive);
                Creature.pane.getChildren().add(imgDead);
            }
        });*/
        save(this.x, this.y, this.x, this.y);
    }
    public synchronized void save(int x1, int y1, int x2, int y2) {
        if(this.savePath == null || this.savePath == "")
            return;
        try {
            FileWriter fw = new FileWriter(savePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String alive = null;
            if(this.alive == true)
                alive = "true";
            else
                alive = "false";
            bw.write(this.name + " " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + alive + " " + System.currentTimeMillis() +"\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("保存失败");
        }
    }

    String tellName() { return name; }
    void setName(String name) { this.name = name; }
    int tellCamp() { return this.camp; }
    void setCamp(int camp) { this.camp = camp; }
    int tellHP() {return this.HP; }
    void setHP(int hp) {this.HP = hp; }
    int tellDamage() { return this.damage; }
    void setDamage(int d) { this.damage = d; }
    int getX() { return this.x; }
    int getY() { return this.y; }
    ImageView getCreatureIMG() { return this.creatureIMG; }
    void setCreatureIMG(ImageView img) { this.creatureIMG = img; }
    @Deprecated
    private void moveCreatureImg(int x1, int y1, int x2, int y2) {
        //创建路径
        javafx.scene.shape.Path path = new javafx.scene.shape.Path();
        path.getElements().add(new MoveTo(y1*50, x1*50));
        path.getElements().add(new LineTo(y2*50, x2*50));
        //创建路径转变
        PathTransition pt=new PathTransition();
        pt.setDuration(Duration.millis(1000));//设置持续时间4秒
        pt.setPath(path);//设置路径
        pt.setNode(this.creatureIMG);//设置物体
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //设置周期性，无线循环
        //pt.setCycleCount(Timeline.INDEFINITE);
        //pt.setAutoReverse(true);//自动往复
        pt.play();//启动动画
    }
}
//用于实现图片平移的类，被walk函数调用 https://blog.csdn.net/guanguoxiang/article/details/45621659
class ImageTranslation implements Runnable {
    ImageView img;
    int x1, y1, x2, y2;
    int gap = 1000;
    ImageTranslation(ImageView creatureIMG, int x1, int y1, int x2, int y2) {
        this.img = creatureIMG;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    //@Override
    public void run() {
        img.setX(y2*50);
        img.setY(x2*50);
        Timeline timeline = new Timeline();
        KeyValue xValue1 = new KeyValue(img.xProperty(),y1*50);
        KeyValue xValue2 = new KeyValue(img.xProperty(),y2*50);
        KeyValue yValue1 = new KeyValue(img.yProperty(),x1*50);
        KeyValue yValue2 = new KeyValue(img.yProperty(),x2*50);
        Duration duration1 = new Duration(0);
        Duration duration2 = new Duration(gap);
        KeyFrame xFrame1 = new KeyFrame(duration1, xValue1);
        KeyFrame yFrame1 = new KeyFrame(duration1, yValue1);
        KeyFrame xFrame2 = new KeyFrame(duration2, xValue2);
        KeyFrame yFrame2 = new KeyFrame(duration2, yValue2);
        timeline.getKeyFrames().addAll(xFrame1,yFrame1,xFrame2,yFrame2);
        timeline.play();
    }
    public static void translation(Timeline timeline, ImageView img, int x1, int y1, int x2, int y2, int startTime) {
        KeyValue xValue1 = new KeyValue(img.xProperty(),y1*50);
        KeyValue xValue2 = new KeyValue(img.xProperty(),y2*50);
        KeyValue yValue1 = new KeyValue(img.yProperty(),x1*50);
        KeyValue yValue2 = new KeyValue(img.yProperty(),x2*50);
        Duration duration1 = new Duration(startTime);
        Duration duration2 = new Duration(startTime+1000);
        KeyFrame xFrame1 = new KeyFrame(duration1, xValue1);
        KeyFrame yFrame1 = new KeyFrame(duration1, yValue1);
        KeyFrame xFrame2 = new KeyFrame(duration2, xValue2);
        KeyFrame yFrame2 = new KeyFrame(duration2, yValue2);
        timeline.getKeyFrames().addAll(xFrame1,yFrame1,xFrame2,yFrame2);
    }

}
class ImageToDead implements Runnable {
    private ImageView imgDead = new ImageView(new Image("dead.png",
            50, 50, true, true));
    Creature creature = null;
    int x = -1, y = -1;
    ImageToDead(Creature creature) {
        this.creature = creature;
        x = this.creature.getX();
        y = this.creature.getY();
        imgDead.setX(y*50);
        imgDead.setY(x*50);
    }
    @Override
    public void run() {
        ImageView imgAlive = this.creature.getCreatureIMG();
        //this.creature.setCreatureIMG(imgDead);
        Creature.pane.getChildren().remove(imgAlive);
        Creature.pane.getChildren().add(imgDead);
        /*Timeline timeline = new Timeline();
        KeyValue xValue = new KeyValue(imgDead.xProperty(),y*50);
        KeyValue yValue = new KeyValue(imgDead.yProperty(),x*50);
        Duration duration = new Duration(0);
        KeyFrame xFrame = new KeyFrame(duration, xValue);
        KeyFrame yFrame = new KeyFrame(duration, yValue);
        timeline.getKeyFrames().addAll(xFrame,yFrame);
        timeline.play();*/
        /*Timeline timeline = new Timeline();
        KeyValue xValue0 = new KeyValue(imgDead.xProperty(),-1*50);
        KeyValue yValue0 = new KeyValue(imgDead.yProperty(),-1*50);
        KeyValue xValue = new KeyValue(imgDead.xProperty(),y*50);
        KeyValue yValue = new KeyValue(imgDead.yProperty(),x*50);
        Duration duration0 = new Duration(0);
        Duration duration = new Duration(0);
        KeyFrame xFrame0 = new KeyFrame(duration0, xValue0);
        KeyFrame yFrame0 = new KeyFrame(duration0, yValue0);
        KeyFrame xFrame = new KeyFrame(duration, xValue);
        KeyFrame yFrame = new KeyFrame(duration, yValue);
        timeline.getKeyFrames().addAll(xFrame0,yFrame0,xFrame,yFrame);
        timeline.play();*/
    }
    public static void dead(Timeline timeline, Creature creature, int x, int y, int startTime) {
        ImageView imgDead = new ImageView(new Image("dead.png",
                50, 50, true, true));
        imgDead.setX(-1*50);
        imgDead.setY(-1*50);
        ImageView imgAlive = creature.getCreatureIMG();
        creature.setCreatureIMG(imgDead);
        //Creature.pane.getChildren().remove(imgAlive);
        Creature.pane.getChildren().add(imgDead);
        KeyValue xValue0 = new KeyValue(imgDead.xProperty(),-1*50);
        KeyValue yValue0 = new KeyValue(imgDead.yProperty(),-1*50);
        KeyValue xValue = new KeyValue(imgDead.xProperty(),y*50);
        KeyValue yValue = new KeyValue(imgDead.yProperty(),x*50);
        Duration duration0 = new Duration(startTime);
        Duration duration = new Duration(startTime);
        KeyFrame xFrame0 = new KeyFrame(duration0, xValue0);
        KeyFrame yFrame0 = new KeyFrame(duration0, yValue0);
        KeyFrame xFrame = new KeyFrame(duration, xValue);
        KeyFrame yFrame = new KeyFrame(duration, yValue);
        timeline.getKeyFrames().addAll(xFrame0,yFrame0,xFrame,yFrame);
    }
}
class Road {
    Node[][] graph = null;
    int N = 0;
    public World world = null;
    Road(World world) {
        this.world = world;
        N = world.getSize();
        graph = new Node[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                graph[i][j] = new Node();
            }
        }
    }
    public ArrayList<Node> findRoad(Node node1, Node node2) {
        ArrayList<Node> road = new ArrayList<Node>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(node1);
        graph[node1.x][node1.y].x = -2; graph[node1.x][node1.y].y = -2;
        while(queue.isEmpty() == false) {
            Node node = queue.poll();
            if(node.x == node2.x && node.y == node2.y) {
                fillRoad(road, node);
                break;
            }
            int x = node.x, y = node.y;
            if(x-1 >= 0) {
                int res = check(x-1,y,node2);
                if(res == 1) {
                    fillGraph(x-1,y,node,queue);
                    fillRoad(road,node2);
                    break;
                }
                else if(res == 0) {
                    fillGraph(x-1,y,node,queue);
                }
                else {//不可走或者已经走过
                    //不进行处理
                }
            }
            if(x+1 < N) {
                int res = check(x+1,y,node2);
                if(res == 1) {
                    fillGraph(x+1,y,node,queue);
                    fillRoad(road,node2);
                    break;
                }
                else if(res == 0) {
                    fillGraph(x+1,y,node,queue);
                }
                else {//不可走或者已经走过
                    //不进行处理
                }
            }
            if(y-1 >= 0) {
                int res = check(x,y-1,node2);
                if(res == 1) {
                    fillGraph(x,y-1,node,queue);
                    fillRoad(road,node2);
                    break;
                }
                else if(res == 0) {
                    fillGraph(x,y-1,node,queue);
                }
                else {//不可走或者已经走过
                    //不进行处理
                }
            }
            if(y+1 < N) {
                int res = check(x,y+1,node2);
                if(res == 1) {
                    fillGraph(x,y+1,node,queue);
                    fillRoad(road,node2);
                    break;
                }
                else if(res == 0) {
                    fillGraph(x,y+1,node,queue);
                }
                else {//不可走或者已经走过
                    //不进行处理
                }
            }
        }
        return road;
    }
    private int check(int x, int y, Node end) {
        if(x == end.x && y == end.y)
            return 1;
        if(world.map[x][y] != null) //此处不可走
            return -1;
        else {//此处没有生物，可以走
            if(graph[x][y].x == -1 && graph[x][y].y == -1)//没有走过
                return 0;
            else//已经走过
                return -1;
        }
    }
    private void fillRoad(ArrayList<Node> road, Node node) {
        int x = node.x, y = node.y;
        while(x != -2 && y != -2) {
            Node tmpNode = new Node(x,y);
            tmpNode.distance = graph[x][y].distance;
            road.add(tmpNode);
            int tmp1 = graph[x][y].x;
            int tmp2 = graph[x][y].y;
            x = tmp1; y = tmp2;
        }
    }
    private void fillGraph(int x, int y, Node node, Queue<Node> queue) {
        graph[x][y].x = node.x;
        graph[x][y].y = node.y;
        graph[x][y].distance = graph[node.x][node.y].distance + 1;
        queue.offer(new Node(x, y));
    }
}
class Node {
    int x = -1;
    int y = -1;
    int distance = 0;
    Node() {}
    Node(int x, int y) { this.x = x; this.y = y; }
}

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface CreatureAnnotation {
    String value() default "Creature类及其子类的注解";
}