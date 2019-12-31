import java.util.Arrays;

class position{
    int row;
    int col;
}

class creature{
    position p = new position();
    String name;
}                           //生物类

class hulu extends creature
{
    String color;
    int rank;
    hulu()
    {
        p.col=-1;p.row=-1;
    }
}

class yeye extends creature{
    yeye(){
        name="老爷爷";
        p.col=-1;p.row=-1;
    }
    public position search_one(maps map)                //老爷爷找自己的位置
    {
        int sign = 0;
        int jk=0,jt=0;
        for(jk=0;jk<15;jk++)
        {
            for(jt=0;jt<15;jt++)
            {
                if(map.map[jk][jt]=="老大  ")             //找老大的位置，然后选择在他右边的第一个空位
                {
                    sign = 1;break;
                }
            }
            if(sign == 1)
                break;
        }
        position temp=new position();
        temp.col=-1;temp.row=-1;
        if(sign == 1)
        {
            for(int jc=jt+1;jc<15;jc++)
            {
                if(map.map[jk][jc]=="空位  ")
                {
                    temp.row=jk;temp.col=jc;
                    break;
                }
            }
        }
        return temp;
    }

}

class xiezi extends creature{
    xiezi(){
        name="蝎子精";
        p.col=-1;p.row=-1;
    }
}

class louluo extends creature{
    louluo(){
        name="喽啰  ";
        p.col=-1;p.row=-1;
    }
}

class shejing extends creature{
    shejing(){
        name="蛇精  ";
        p.col=-1;p.row=-1;
    }

    public position search_xiezi(maps map)                //蛇精找自己的位置
    {
        int sign = 0;
        int jk=0,jt=0;
        for(jk=14;jk>=0;jk--)
        {
            for(jt=14;jt>=0;jt--)
            {
                if(map.map[jk][jt]=="蝎子精")             //找蝎子精的位置，然后选择在他右边的第一个空位
                {
                    sign = 1;break;
                }
            }
            if(sign == 1)
                break;
        }
        position temp=new position();
        temp.col=-1;temp.row=-1;
        if(sign == 1)
        {
            for(int jc=jt+1;jc<15;jc++)
            {
                if(map.map[jk][jc]=="空位  ")
                {
                    temp.row=jk;temp.col=jc;
                    break;
                }
            }
        }
        return temp;
    }

}

class maps{
    int N=15;
    String map[][]=new String[N][N];
    maps()
    {
        for (int jk = 0; jk < N; jk++) {
            for (int jt = 0; jt < N; jt++)
                map[jk][jt] = "空位  ";
        }
    }

    public void place(int row, int col, String name)
    {
        map[row][col]=name;
    }

    public void show(){
        for (int jk = 0; jk < N; jk++) {
            for (int jt = 0; jt < N; jt++)
            {
                System.out.print(map[jk][jt]);
                System.out.print("  ");
            }
            System.out.println("");
        }

    }

}

class formation{
    int number;
}                                   //阵型类（目前只有长蛇、锋矢（箭头）和方圆）

class snake extends formation{
    position p[]=new position[7];
    snake(){
        for(int jk=0;jk<7;jk++)
            p[jk]=new position();
        number=7;
        int row1=0;
        for(int jk=0;jk<7;jk++) {
            p[jk].row = row1;
            p[jk].col = 7;
            row1++;
        }
    }
}

class jiantou extends formation{
    position p[]=new position[8];
    jiantou(){
        for(int jk=0;jk<8;jk++)
            p[jk]=new position();
        number=8;
        int row1=14;
        for(int jk=0;jk<5;jk++) {
            p[jk].row = row1;
            p[jk].col = 7;
            row1--;
        }
        p[5].row = 10;
        p[5].col = 6;
        p[6].row = 10;
        p[6].col = 8;
        p[7].row = 9;
        p[7].col = 7;
    }
}

class fangyuan extends formation{
    position p[]=new position[8];
    fangyuan(){
        for(int jk=0;jk<8;jk++)
            p[jk]=new position();
        number=7;
        int row1=14;
        p[0].row = 14;p[0].col = 7;
        p[1].row = 13;p[1].col = 6;
        p[2].row = 13;p[2].col = 8;
        p[3].row = 12;p[3].col = 5;
        p[4].row = 12;p[4].col = 9;
        p[5].row = 11;p[5].col = 6;
        p[6].row = 11;p[6].col = 8;
        p[7].row = 10;p[7].col = 7;
    }
}

class hululine{
    hulu boys[]=new hulu[7];
    hululine()
    {
        for(int jk=0;jk<7;jk++)
            boys[jk]=new hulu();
        boys[0].name="老大  ";boys[0].rank=0;boys[0].color="红";
        boys[1].name="老二  ";boys[1].rank=1;boys[1].color="橙";
        boys[2].name="老三  ";boys[2].rank=2;boys[2].color="黄";
        boys[3].name="老四  ";boys[3].rank=3;boys[3].color="绿";
        boys[4].name="老五  ";boys[4].rank=4;boys[4].color="青";
        boys[5].name="老六  ";boys[5].rank=5;boys[5].color="蓝";
        boys[6].name="老七  ";boys[6].rank=6;boys[6].color="紫";
    }
}

class louluoline{
    louluo louluos[]=new louluo[7];
    louluoline()
    {
        for(int jk=0;jk<7;jk++)
            louluos[jk]=new louluo();
    }
}

public class opposite {

    static maps map = new maps();
    static hululine hu = new hululine();
    static yeye yy = new yeye();
    static louluoline lo = new louluoline();
    static xiezi xz = new xiezi();
    static shejing sj =new shejing();

    public static void to_snake(){                           //葫芦娃站成长蛇队
        snake s=new snake();
        for(int jk=0;jk<7;jk++)
        {
            if(hu.boys[jk].p.row!=-1)                       //若已站位，则让该葫芦娃先离开地图再站位
            {
                map.map[hu.boys[jk].p.row][hu.boys[jk].p.col]="空位  ";
            }
            hu.boys[jk].p.row=s.p[jk].row;
            hu.boys[jk].p.col=s.p[jk].col;
            map.place(hu.boys[jk].p.row,hu.boys[jk].p.col,hu.boys[jk].name);
        }
    }

    public static void to_jiantou(){                        //蛇精和小喽啰站成箭头（锋矢）
        jiantou t=new jiantou();
        for(int jk=0;jk<7;jk++)
        {
            if(lo.louluos[jk].p.row!=-1)                //若已站位，则让该喽啰先离开地图再站位
            {
                map.map[lo.louluos[jk].p.row][lo.louluos[jk].p.col]="空位  ";
            }
            lo.louluos[jk].p.row=t.p[jk+1].row;
            lo.louluos[jk].p.col=t.p[jk+1].col;
            map.place(lo.louluos[jk].p.row,lo.louluos[jk].p.col,lo.louluos[jk].name);
        }
        if(xz.p.row!=-1)              //若已站位则该蝎子先离开地图
            map.map[xz.p.row][xz.p.col]="空位  ";
        xz.p.row=t.p[0].row;
        xz.p.col=t.p[0].col;
        map.place(xz.p.row,xz.p.col,xz.name);
    }

    public static void to_fangyuan(){                        //蛇精和小喽啰站成方圆
        fangyuan t=new fangyuan();
        for(int jk=0;jk<7;jk++)
        {
            if(lo.louluos[jk].p.row!=-1)                  //若已站位，则让该喽啰先离开地图再站位
            {
                map.map[lo.louluos[jk].p.row][lo.louluos[jk].p.col]="空位  ";
            }
            lo.louluos[jk].p.row=t.p[jk+1].row;
            lo.louluos[jk].p.col=t.p[jk+1].col;
            map.place(lo.louluos[jk].p.row,lo.louluos[jk].p.col,lo.louluos[jk].name);
        }
        if(xz.p.row != -1)              //若已站位则该蝎子先离开地图
            map.map[xz.p.row][xz.p.col]="空位  ";
        xz.p.row=t.p[0].row;
        xz.p.col=t.p[0].col;
        map.place(xz.p.row,xz.p.col,xz.name);
    }

    public static void main(String args[])
    {
        to_snake();
        to_jiantou();
        if(yy.p.row!=-1)
            map.map[yy.p.row][yy.p.col]="空位  ";
        yy.p = yy.search_one(map);
        map.place(yy.p.row,yy.p.col,yy.name);
        if(sj.p.row!=-1)
            map.map[sj.p.row][sj.p.col]="空位  ";
        sj.p = sj.search_xiezi(map);
        map.place(sj.p.row,sj.p.col,sj.name);
        System.out.println("当前对峙局势如下：");
        map.show();
        System.out.println("葫芦娃势力排成长蛇阵，蝎子精势力排成锋矢阵。");


        System.out.println("蝎子精和小喽啰重新排阵：");
        to_snake();
        to_fangyuan();
        if(yy.p.row!=-1)
            map.map[yy.p.row][yy.p.col]="空位  ";
        yy.p = yy.search_one(map);
        map.place(yy.p.row,yy.p.col,yy.name);
        if(sj.p.row!=-1)
            map.map[sj.p.row][sj.p.col]="空位  ";
        sj.p = sj.search_xiezi(map);
        map.place(sj.p.row,sj.p.col,sj.name);
        System.out.println("当前对峙局势如下：");
        map.show();
        System.out.println("葫芦娃势力排成长蛇阵，蝎子精势力排成方圆阵。");
    }

}
