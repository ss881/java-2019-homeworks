import java.lang.reflect.Array;

public class God {
    Formation formation = new Formation();

    Creature createCreature(String name){
        return new Creature(name);
    }
    Enemy[] createEnemy(String[] name){//创建敌人
        int len = name.length;
        Factory<Enemy> factory = new Factory<>(new EnemyCreator(), name);
        Enemy[] enemy = factory.arr;
        return enemy;
    }
    Boy[] createBoy(String[] name){//创建葫芦娃
        //int len = name.length;
        Factory<Boy> factory = new Factory<>(new BoyCreator(), name);
        Boy[] boy = factory.arr;
        return boy;
    }
    void move(World world, Creature creature, int x, int y){
        creature.walk(world, x, y);
    }
    void sortY(World world, int x, int y, int len){//对纵向排列的葫芦娃进行排序
        Creature[][] map = world.map;
        int N = world.getSize();
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
                Class c1 = map[j][y].getClass();
                Class c2 = map[j+1][y].getClass();
                Class c3 = Boy.class;
                boolean check = (c1.getName() == c2.getName()) && (c1.getName() == c3.getName());
                if(check == false){
                    System.out.println("sort wrong!");
                    return;
                }
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
                    boolean flag = a.walk(world, tmpX, tmpY);
                    flag = flag & b.walk(world, j, y);
                    flag = flag & a.walk(world, j+1, y);
                    if(flag == false){//葫芦娃移动失败
                        System.out.println("排序失败");
                        return;
                    }
                }
            }
        }
    }
    void sortX(World world, int x, int y, int len){//对横向排列的葫芦娃进行排序
        Creature[][] map = world.map;
        int N = world.getSize();
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
                Class c1 = map[x][j].getClass();
                Class c2 = map[x][j+1].getClass();
                Class c3 = Boy.class;
                boolean check = (c1.getName() == c2.getName()) && (c1.getName() == c3.getName());
                if(check == false) {
                    System.out.println("sort wrong!");
                    return;
                }
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
                    boolean flag = a.walk(world, tmpX, tmpY);
                    flag = flag & b.walk(world, x, j);
                    flag = flag & a.walk(world, x, j+1);
                    if(flag == false){
                        System.out.println("排序失败");
                        return;
                    }
                }
            }
        }
    }

    public static int checkBoy(String name){
        if(name == "老大"){
            return 1;
        }
        else if(name == "老二"){
            return 2;
        }
        else if(name == "老三"){
            return 3;
        }
        else if(name == "老四"){
            return 4;
        }
        else if(name == "老五"){
            return 5;
        }
        else if(name == "老六"){
            return 6;
        }
        else if(name == "老七"){
            return 7;
        }
        else{
            return 0;
        }
    }
    public static String getBoyColor(int rank) {
        switch(rank){
            case 1: return "红色";
            case 2: return "橙色";
            case 3: return "黄色";
            case 4: return "绿色";
            case 5: return "青色";
            case 6: return "蓝色";
            case 7: return "紫色";
            default: return "";
        }
    }
}

interface Creator<T> {
    T[] create(String[] names);
}

class Factory<T> {
    T[] arr;
    public <C extends Creator<T>> Factory(C creator, String[] names){
        arr = creator.create(names);
    }
}

class BoyCreator implements Creator<Boy>{
    BoyCreator(){}
    @Override
    public Boy[] create(String[] names) {
        Boy[] boy = new Boy[names.length];
        for(int i = 0; i<names.length; i++){
            int rank = God.checkBoy(names[i]);
            String color = God.getBoyColor(rank);
            boy[i] = new Boy(names[i],color,rank);
        }
        return boy;
    }
}
class EnemyCreator implements Creator<Enemy>{
    EnemyCreator(){}
    @Override
    public Enemy[] create(String[] names) {
        Enemy[] enemy = new Enemy[names.length];
        for(int i = 0; i < names.length; i++){
            enemy[i] = new Enemy(names[i]);
        }
        return enemy;
    }
}
