import java.util.ArrayList;

public class Formation {
    //阵型函数，以xy作为起始坐标
    public static void snake(ArrayList<Creature> queue, int x, int y){//xy是纵向长蛇的最上面一点的坐标
        int a = x, b = y;
        int len = queue.size();
        int N = Creature.world.getSize();
        if((a+len-1) >= N || a >= N || b >= N || a < 0 || b < 0){
            System.out.println("坐标越界，无法创建长蛇阵型");
            return;
        }
        for(int i = 0; i < len; i++){
            //queue.get(i).world.map[a][b] = queue.get(i);
            //queue.get(i).x = a; queue.get(i).y = b;
            boolean flag = queue.get(i).walk( a, b);
            if( flag == false)
                System.out.println("长蛇阵型排列失败,位置："+a+','+b);
            a++;
        }
    }
    public static void wing(ArrayList<Creature> queue, int x, int y){//xy是鹤翼中心点的坐标
        int len = queue.size();
        int half1 = (len - 1) / 2;
        int half2 = len - 1 - half1;
        int N = Creature.world.getSize();
        int i = 0;
        int a = x, b = y;
        if((a-half1) < 0 || (a-half2) < 0 || (b-half1) < 0 || (b+half2) >= N || a >= N || b >= N || a < 0 || b < 0){
            System.out.println("坐标越界，无法创建鹤翼阵型");
            return;
        }
        for(; i < half1 + 1; i++){
            boolean flag = queue.get(i).walk(a, b);
            if( flag == false)
                System.out.println("鹤翼阵型排列失败,位置："+a+','+b);
            a--; b--;
        }
        a = x - 1;
        b = y + 1;
        for(; i < len; i++){
            boolean flag = queue.get(i).walk(a, b);
            if( flag == false)
                System.out.println("鹤翼阵行排列失败,位置："+a+','+b);
            a--; b++;
        }
    }
    public static void goose(ArrayList<Creature> queue, int x, int y){
        //(x,y)是雁行阵型的右上坐标
        int len = queue.size();
        int a = x, b = y;
        int N = Creature.world.getSize();
        if( a < 0 || b >= N || (a+len-1) >= N || (y-len+1) < 0){
            System.out.println("坐标越界，无法创建雁行阵型");
            return;
        }
        for(int i = 0; i < len; i++){
            //ueue.get(i).world.map[a][b] = queue.get(i);
            //queue.get(i).x = a; queue.get(i).y = b;
            boolean flag = queue.get(i).walk(a, b);
            if( flag == false)
                System.out.println("雁行阵行排列失败,位置："+a+','+b);
            a++; b--;
        }
    }
    public static void yoke(ArrayList<Creature> queue, int x, int y){
        //衡轭 (x,y)是最上方的坐标
        int len = queue.size();
        int N = Creature.world.getSize();
        if(x<0||y<0||x>=N||y>=N||len+x-1>=N){
            System.out.println("坐标越界，无法创建衡轭阵型");
            return;
        }
        int a = x, b = y;
        for(int i = 0; i < len; i++){
            boolean flag = queue.get(i).walk(a, b);
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
    public static void scale(ArrayList<Creature> queue, int x, int y){//鱼鳞

    }
    public static void square(ArrayList<Creature> queue, int x, int y){
        int len = queue.size();
        if(len%4!=0){
            System.out.println("生物个数不允许创建方圆阵型");
            return;
        }
        int N = Creature.world.getSize();
        if(x<0||y<0||x>=N||y>=N||x+len/2>=N||y+len/4>=N||y-len/4<0){
            System.out.println("坐标越界，无法创建方圆阵型");
            return;
        }
        int turn = len / 4;
        int cnt = 0;
        int m = 0;
        int a = x, b = y;
        for(int i = 0; i < len; i++){
            boolean flag = queue.get(i).walk(a, b);
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
    public static void moon(ArrayList<Creature> queue, int x, int y){

    }
    public static void arrow(ArrayList<Creature> queue, int x, int y){
        int len = queue.size();
        if(len < 7){
            System.out.println("生物数量不足，无法创建箭矢阵型");
            return;
        }
        int N = Creature.world.getSize();
        if(x<0||y<0||x>=N||y>=N||x+2>=N||y+2>=N||y-2<0||x+len-5>=N){
            System.out.println("坐标越界，无法创建箭矢阵型");
            return;
        }
        queue.get(0).walk(x,y);
        queue.get(1).walk(x+1,y+1);
        queue.get(2).walk(x+2,y+2);
        queue.get(3).walk(x+1,y-1);
        queue.get(4).walk(x+2,y-2);
        int a = x+1, b = y;
        for(int i = 5; i < len; i++){
            queue.get(i).walk(a,b);
            a++;
        }
    }
}
