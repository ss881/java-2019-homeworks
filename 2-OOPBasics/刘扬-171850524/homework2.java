import java.util.*;

public class homework2 {
    public static void main(String[] args){
        String[][] boyName={{"老五","青色","5"},{"老三","黄色","3"},{"老大","红色","1"},{"老二","橙色","2"},{"老七","紫色","7"},{"老六","蓝色","6"},{"老四","绿色","4"}};

        God god = new God();
        System.out.println("生成世界");
        World world = new World();
        System.out.println("创建葫芦娃");
        Boy[] boy = god.createBoy(boyName);//创建七个葫芦娃
        System.out.println("葫芦娃乱序排列为一行");
        for(int i = 0; i < boy.length; i++)
            god.move(world, boy[i],2,1+i);
        System.out.println("冒泡排序");
        god.bubbleSort(world,2,1,boy.length);

        System.out.println("葫芦娃乱序排列为一行");
        for(int i = 0; i < boy.length; i++)
            god.move(world, boy[i],3,2+i);
        System.out.println("二分排序");
        god.insertionSort(world,3,2,boy.length);
    }

}

class God{
    Boy[] createBoy(String[][] info){//创建葫芦娃
        int len = info.length;
        Boy[] boy = new Boy[len];
        for(int i = 0; i < len; i++){//循环进行实例初始化
            boy[i] = new Boy(info[i][0], info[i][1],Integer.parseInt(info[i][2]));
        }
        return boy;
    }
    void move(World world, Creature creature, int x, int y){
        creature.walk(world, x, y);
    }
    void bubbleSort(World world, int x, int y, int len){//对横向排列的葫芦娃进行排序
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
        System.out.println("排序结束，葫芦娃依次报数：");
        for(int i=0;i<len;i++) {
            String name = ((Boy)(world.map[x][y + i])).tellName();
            System.out.println(name);
        }
        System.out.println("报数结束");
    }
    void insertionSort(World world, int x, int y, int len){//二分排序
        int N = world.getSize();
        for(int i=1;i<len;i++){
            int low = 0, high = i - 1;
            int mid = 0;
            while(low <= high){//按照二分法寻找i应该插入的位置
                mid = low + (high-low) / 2;
                Boy a = (Boy)(world.map[x][y+i]);
                Boy b = (Boy)(world.map[x][y+mid]);
                if(a.tellRank() > b.tellRank()){
                    low = mid + 1;
                }
                else{
                    high = mid - 1;
                }
            }
            //上述循环结束后，i应该插在high的后面
            if(high+1 == i)
                continue;
            int tmpX = -1, tmpY = -1;
            int min = 2*N;
            for(int m = 0; m < N; m++){//二重循环找到距离待交换的葫芦娃最近的坐标
                for(int n = 0; n < N; n++){
                    if(world.map[m][n] == null){
                        int distance = Math.abs(n-i-y)+Math.abs(n-high-1-y)+Math.abs(m-x)*2;
                        if(distance<min){
                            tmpX = m; tmpY = n;
                            min = distance;
                        }
                    }
                }
            }
            world.map[x][y+i].walk(world, tmpX, tmpY);
            for(int j = i - 1; j > high; j --){//high后面的对象依次向后移动一位
                world.map[x][y+j].walk(world, x, y+j+1);
            }
            world.map[tmpX][tmpY].walk(world,x,y+high+1);
        }
        System.out.println("排序结束，葫芦娃依次报颜色：");
        for(int i=0;i<len;i++) {
            String name = ((Boy)(world.map[x][y + i])).tellColor();//报颜色
            System.out.println(name);
        }
        System.out.println("报颜色结束");
    }
}

class World{//坐标类，定义代表地图的二维坐标和对数组的操作
    Creature[][] map;
    static final int N = 20;
    World(){
        map = new Creature[N][N];
    }
    int getSize(){return N;}
    void show(){
        //打印对峙局面
        System.out.println("打印对峙局面");
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (map[i][j] == null){
                    System.out.printf("%4s","");
                }
                else{
                    String name = map[i][j].tellName();
                    System.out.printf("%4s",name);
                }
            }
            System.out.println("");
        }
    }
}
class Creature{
    String name = "";
    int x = -1;
    int y = -1;
    Creature(){}
    Creature(String name){ this.name = name;}
    boolean walk(World world, int x, int y){
        int n = world.getSize();
        if(x<0 || y<0 || x>=n || y>= n){
            System.out.println("目的坐标不在已知空间中，无法移动至目的坐标");
            return false;
        }
        if (world.map[x][y] != null) {
            System.out.println("目的坐标存在生物，无法移动至目的坐标");
            world.map[x][y].tellName();
            return false;
        }
        world.map[x][y] = this;
        if (this.x >= 0 && this.y >= 0) {
            //该生物已经存在于坐标空间中
            world.map[this.x][this.y] = null;
        }
        if(this.x<0 || this.y<0 || this.x>=n || this.y>= n){
            System.out.println(name+"->(" + x + "," + y + ")");
        }
        else {
            System.out.println(name + "(" + this.x + "," + this.y + ")->(" + x + "," + y + ")");
        }
        this.x = x;
        this.y = y;
        return true;
    }
    String tellName(){ return name; }
}
class Boy extends Creature{
    String color = "";
    int rank = 0;
    Boy(){}
    Boy(String name, String color, int rank){
        this.rank = rank;
        this.name = name;
        this.color = color;
    }
    int tellRank(){ return rank;}
    String tellColor(){ return color;}
}
