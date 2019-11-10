import java.util.*;

public class homework3 {
    public static void main(String[] args){
        String[][] boyName={{"老五","青","5"},{"老三","黄色","3"},{"老大","红色","1"},{"老二","橙色","2"},{"老七","紫色","7"},{"老六","蓝色","6"},{"老四","绿色","4"}};
        String[] enemyName = {"蝎子精","炮灰1号","炮灰2号","炮灰3号","炮灰4号","炮灰5号","炮灰6号","炮灰7号"};

        God god = new God();
        System.out.println("生成世界");
        World world = new World();
        System.out.println("生成生物体");
        Boy[] boy = god.createBoy(boyName);//创建七个葫芦娃
        Enemy[] enemy = god.createEnemy(enemyName);//创建蝎子精等七个敌人
        Creature grandpa = god.createCreature("爷爷");
        Creature snake = god.createCreature("蛇精");

        System.out.println("葫芦娃站队（长蛇阵型）");
        god.formation.snake(world, boy,1, 1);//葫芦娃站队：乱序的长蛇阵
        System.out.println("葫芦娃排序");
        god.sortY(world, 1,1, 7);//乱序葫芦娃排序
        System.out.println("爷爷移动");
        god.move(world, grandpa, 0, 0);//放置爷爷

        System.out.println("敌人站队（鹤翼阵型）");
        god.formation.wing(world, enemy, 7, 14);//敌人以鹤翼阵型站队
        System.out.println("蛇精移动");
        god.move(world, snake, 0, 14);//放置蛇精
        world.show();//打印对峙局面

        System.out.println("敌人变换阵型为雁行");
        god.formation.snake(world, enemy,0,19);
        god.formation.goose(world, enemy, 2, 14);//敌人变化阵型为雁行
        System.out.println("蛇精移动");
        god.move(world, snake, 1, 14);//蛇精移动
        world.show();//打印对峙局面

        System.out.println("敌人变换阵型为衡轭");
        god.formation.snake(world, enemy,0,19);
        god.formation.yoke(world, enemy, 3, 14);//敌人变化阵型为衡轭
        System.out.println("蛇精移动");
        god.move(world, snake, 2, 14);//蛇精移动
        world.show();//打印对峙局面

        System.out.println("敌人变换阵型为方圆");
        god.formation.snake(world,enemy,0,19);
        god.formation.square(world,enemy, 3, 10);//敌人变化阵型为方圆
        System.out.println("蛇精移动");
        god.move(world, snake, 2, 10);//蛇精移动
        world.show();//打印对峙局面

        System.out.println("敌人变换阵型为箭矢");
        god.formation.snake(world, enemy,0,19);
        god.formation.arrow(world, enemy, 3, 14);//敌人变化阵型为箭矢
        System.out.println("蛇精移动");
        god.move(world, snake, 2, 14);//蛇精移动
        world.show();//打印对峙局面
    }

}

class God{
    Formation formation = new Formation();
    Creature createCreature(String name){
        return new Creature(name);
    }
    Enemy[] createEnemy(String[] name){//创建敌人
        int len = name.length;
        Enemy[] enemy = new Enemy[len];
        for(int i = 0; i < len; i++){//循环进行实例初始化
            enemy[i] = new Enemy(name[i]);
        }
        return enemy;
    }
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
}

class World{//坐标类，定义代表地图的二维坐标和对数组的操作
    Creature[][] map;
    static final int N = 20;
    World(){
        map = new Creature[N][N];
    }
    int getSize(){return N;}
    //打印对峙局面
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
class Formation{
    //阵型函数，以xy作为起始坐标
    void snake(World world, Creature[] queue, int x, int y){//xy是纵向长蛇的最上面一点的坐标
        int a = x, b = y;
        int len = queue.length;
        int N = world.getSize();
        if((a+len-1) >= N || a >= N || b >= N || a < 0 || b < 0){
            System.out.println("坐标越界，无法创建长蛇阵型");
            return;
        }
        for(int i = 0; i < len; i++){
            boolean flag = queue[i].walk(world, a, b);
            if( flag == false)
                System.out.println("长蛇阵型排列失败,位置："+a+','+b);
            a++;
        }
    }
    void wing(World world, Creature[] queue, int x, int y){//xy是鹤翼中心点的坐标
        int len = queue.length;
        int half1 = (len - 1) / 2;
        int half2 = len - 1 - half1;
        int N = world.getSize();
        int i = 0;
        int a = x, b = y;
        if((a-half1) < 0 || (a-half2) < 0 || (b-half1) < 0 || (b+half2) >= N || a >= N || b >= N || a < 0 || b < 0){
            System.out.println("坐标越界，无法创建鹤翼阵型");
            return;
        }
        for(; i < half1 + 1; i++){
            boolean flag = queue[i].walk(world, a, b);
            if( flag == false)
                System.out.println("鹤翼阵型排列失败,位置："+a+','+b);
            a--; b--;
        }
        a = x - 1;
        b = y + 1;
        for(; i < len; i++){
            boolean flag = queue[i].walk(world, a, b);
            if( flag == false)
                System.out.println("鹤翼阵行排列失败,位置："+a+','+b);
            a--; b++;
        }
    }
    void goose(World world, Creature[] queue, int x, int y){
        //(x,y)是雁行阵型的右上坐标
        int len = queue.length;
        int a = x, b = y;
        int N = world.getSize();
        if( a < 0 || b >= N || (a+len-1) >= N || (y-len+1) < 0){
            System.out.println("坐标越界，无法创建雁行阵型");
            return;
        }
        for(int i = 0; i < len; i++){
            boolean flag = queue[i].walk(world, a, b);
            if( flag == false)
                System.out.println("雁行阵行排列失败,位置："+a+','+b);
            a++; b--;
        }
    }
    void yoke(World world, Creature[] queue, int x, int y){
        //衡轭 (x,y)是最上方的坐标
        int len = queue.length;
        int N = world.getSize();
        if(x<0||y<0||x>=N||y>=N||len+x-1>=N){
            System.out.println("坐标越界，无法创建衡轭阵型");
            return;
        }
        int a = x, b = y;
        for(int i = 0; i < len; i++){
            boolean flag = queue[i].walk(world, a, b);
            if( flag == false)
                System.out.println("衡轭阵行排列失败,位置："+a+','+b);
            if(i%2==0){
                b--;
            }
            else
                b++;
            a++;
        }

    }
    void scale(World world, Creature[] queue, int x, int y){//鱼鳞

    }
    void square(World world, Creature[] queue, int x, int y){
        int len = queue.length;
        if(len%4!=0){
            System.out.println("生物个数不允许创建方圆阵型");
            return;
        }
        int N = world.getSize();
        if(x<0||y<0||x>=N||y>=N||x+len/2>=N||y+len/4>=N||y-len/4<0){
            System.out.println("坐标越界，无法创建方圆阵型");
            return;
        }
        int turn = len / 4;
        int cnt = 0;
        int m = 0;
        int a = x, b = y;
        for(int i = 0; i < len; i++){
            boolean flag = queue[i].walk(world, a, b);
            if( flag == false)
                System.out.println("方圆阵行排列失败,位置："+a+','+b);

            if(cnt == turn){
                cnt = 0; m++;
            }
            if(m==0){
                a++;b--;
            }
            else if(m==1){
                a++;b++;
            }
            else if(m==2){
                a--;b++;
            }
            else{
                a--;b--;
            }
            cnt++;
        }
    }
    void moon(World world, Creature[] queue, int x, int y){

    }
    void arrow(World world, Creature[] queue, int x, int y){
        int len = queue.length;
        if(len <= 7){
            System.out.println("生物数量不足，无法创建箭矢阵型");
            return;
        }
        int N = world.getSize();
        if(x<0||y<0||x>=N||y>=N||x+2>=N||y+2>=N||y-2<0||x+len-5>=N){
            System.out.println("坐标越界，无法创建箭矢阵型");
            return;
        }
        queue[0].walk(world,x,y);
        queue[1].walk(world,x+1,y+1);
        queue[2].walk(world,x+2,y+2);
        queue[3].walk(world,x+1,y-1);
        queue[4].walk(world,x+2,y-2);
        int a = x+1, b = y;
        for(int i = 5; i < len; i++){
            queue[i].walk(world,a,b);
            a++;
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
        /*switch(rank){
            case 1: name = "老大"; color = "红色"; break;
            case 2: name = "老二"; color = "橙色"; break;
            case 3: name = "老三"; color = "黄色"; break;
            case 4: name = "老四"; color = "绿色"; break;
            case 5: name = "老五"; color = "青色"; break;
            case 6: name = "老六"; color = "蓝色"; break;
            case 7: name = "老七"; color = "紫色"; break;
            default: System.out.println("创建葫芦娃失败");
        }*/
    }
    int tellRank(){ return rank;}
    String tellColor(){ return color;}
}
class Enemy extends Creature{
    Enemy(String name){
        this.name = name;
    }
}
