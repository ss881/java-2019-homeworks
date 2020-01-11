import java.util.ArrayList;

public class God {
    private static int justCampCnt = 0;
    private static int evilCampCnt = 0;
    private static boolean showResult = false;
    public static synchronized void justInc() { justCampCnt++; }
    public static synchronized void justDec() { justCampCnt--; }
    public static synchronized void evilInc() { evilCampCnt++; }
    public static synchronized void evilDec() { evilCampCnt--; }
    public static synchronized boolean isEnd() { return justCampCnt == 0 || evilCampCnt == 0; }
    public static synchronized boolean isShowed() { return showResult; }
    public static synchronized void setShowResult(boolean flag) { showResult = flag; }
    public static void init() { justCampCnt = 0; evilCampCnt = 0; showResult = false; }
    public static void makeFormation(ArrayList<Creature> queue, String formationName, int x, int y) {
        if(formationName.equals("snake") == true) {
            Formation.snake(queue, x, y);
        }
        else if(formationName.equals("wing") == true) {
            Formation.wing(queue, x, y);
        }
        else if(formationName.equals("goose") == true) {
            Formation.goose(queue, x, y);
        }
        else if(formationName.equals("yoke") == true) {
            Formation.yoke(queue, x, y);
        }
        else if(formationName.equals("scale") == true) {
            Formation.scale(queue, x, y);
        }
        else if(formationName.equals("square") == true) {
            Formation.square(queue, x, y);
        }
        else if(formationName.equals("moon") == true) {
            Formation.moon(queue, x, y);
        }
        else if(formationName.equals("arrow") == true) {
            Formation.arrow(queue, x, y);
        }
    }

    public static ArrayList<Creature> getCreatures(String[] names) {
        ArrayList<Creature> res = new ArrayList<Creature>();
        for(String i : names ) {
            res.add(getCreature(i));
        }
        return res;
    }
    public static Creature getCreature(String name) {
        if(name.equals("老大") == true)
            return new Boy(name,"红色", 1);
        if(name.equals("老二") == true)
            return new Boy(name,"橙色", 2);
        if(name.equals("老三") == true)
            return new Boy(name,"黄色", 3);
        if(name.equals("老四") == true)
            return new Boy(name,"绿色", 4);
        if(name.equals("老五") == true)
            return new Boy(name,"青色", 5);
        if(name.equals("老六") == true)
            return new Boy(name,"蓝色", 6);
        if(name.equals("老七") == true)
            return new Boy(name,"紫色", 7);
        if(name.equals("爷爷") == true)
            return new Grandpa();
        return new Enemy(name);
    }

    //创建多个生物
    @Deprecated
    public static Enemy[] createEnemy(String[] name){//创建敌人
        int len = name.length;
        Enemy[] enemy = new Enemy[len];
        for(int i = 0; i < len; i++){//循环进行实例初始化
            enemy[i] = new Enemy(name[i]);
        }
        return enemy;
    }
    @Deprecated
    public static Boy[] createBoy(String[][] info){//创建葫芦娃
        int len = info.length;
        Boy[] boy = new Boy[len];
        for(int i = 0; i < len; i++){//循环进行实例初始化
            boy[i] = new Boy(info[i][0], info[i][1],Integer.parseInt(info[i][2]));
        }
        return boy;
    }
    //给葫芦娃排序的函数
    public static void sortY(int x, int y, int len){//对纵向排列的葫芦娃进行排序
        Creature[][] map = Creature.world.map;
        int N = Creature.world.getSize();
        if(len <= 0){//合法性检查
            System.out.println("排序元素个数非法, 排序失败");
            return;
        }
        if(x < 0 || y < 0 || x >= N || y >= N || x + len - 1 >= N){//越界检查
            System.out.println("坐标越界, 排序失败");
            return;
        }
        for(int i = 0; i < len; i++){//冒泡排序
            for(int j = x; j < x + len - 1; j++){
                //ab是两个相邻的葫芦娃
                Boy a = (Boy)map[j][y];
                Boy b = (Boy)map[j+1][y];
                if(a.tellRank()>b.tellRank()){//根据排行判断是否进行交换
                    int tmpX = -1, tmpY = -1;
                    int min = 2*N;
                    for(int m = 0; m < len; m++){//二重循环找到距离待交换的葫芦娃最近的坐标
                        for(int n = 0; n < len; n++){
                            if(map[m][n] == null){
                                int distance = Math.abs(m-j)+Math.abs(m-j-1)+Math.abs(n-y)*2;
                                if(distance<min){
                                    tmpX = m; tmpY = n;
                                    min = distance;
                                }
                            }
                        }
                    }
                    boolean flag = a.walk(tmpX, tmpY);
                    flag = flag & b.walk(j, y);
                    flag = flag & a.walk(j+1, y);
                    if(flag == false){//葫芦娃移动失败
                        System.out.println("排序失败");
                        return;
                    }
                }
            }
        }
    }
    public static void sortX(int x, int y, int len){//对横向排列的葫芦娃进行排序
        Creature[][] map = Creature.world.map;
        int N = Creature.world.getSize();
        if(len <= 0){//合法性检查
            System.out.println("排序元素个数非法, 排序失败");
            return;
        }
        if(x < 0 || y < 0 || x >= N || y >= N || y + len - 1 >= N){//越界检查
            System.out.println("坐标越界, 排序失败");
            return;
        }
        for(int i = 0; i < len; i++){//冒泡排序
            for(int j = y; j < y + len - 1; j++){
                Boy a = (Boy)map[x][j];
                Boy b = (Boy)map[x][j+1];
                if(a.tellRank()>b.tellRank()){//进行交换
                    int tmpX = -1, tmpY = -1;
                    int min = 2*N;
                    for(int m = 0; m < len; m++){//二重循环找到距离待交换的葫芦娃最近的坐标
                        for(int n = 0; n < len; n++){
                            if(map[m][n] == null){
                                int distance = Math.abs(n-j)+Math.abs(n-j-1)+Math.abs(m-x)*2;
                                if(distance<min){
                                    tmpX = m; tmpY = n;
                                    min = distance;
                                }
                            }
                        }
                    }
                    boolean flag = a.walk(tmpX, tmpY);
                    flag = flag & b.walk(x, j);
                    flag = flag & a.walk(x, j+1);
                    if(flag == false){
                        System.out.println("排序失败");
                        return;
                    }
                }
            }
        }
    }
}
