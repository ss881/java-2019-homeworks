public class homework3
{
    public static void main(String[] args)
    {
        World w=new World();
        w.Init();
        //w.Printmap();
        
        w.StandRandomly();
        w.Printmap();
        w.Sort();
        w.Printmap();
        System.out.print("鹤翼阵，并放入爷爷和蛇精");
        Formation tp1=new CraneWing();
        w.StandBadguys(tp1);       
        w.LayGrandpa();
        w.LaySnake();
        w.Printmap();


        w.Clear();
        w.StandRandomly();
        w.Sort();
        System.out.print("雁行阵，并放入爷爷和蛇精");
        Formation tp2=new WildGooseFly();
        w.StandBadguys(tp2);
        w.LayGrandpa();
        w.LaySnake();
        w.Printmap();
    }
}

class Creatures
{
    int row;
    int column;
    char like;
}

class Huluwas extends Creatures
{
    String color;
    int ranking;
    void Exchange(int j)
    {
        row=j;
    }
}

abstract class Formation
{
    //int num;
    int column;
    int row;
    void arrange(Creatures head,Creatures t[],char map[][]){};
}

class CraneWing extends Formation
{
    //static int num=7;
    void arrange(Creatures head,Creatures t[],char map[][])
    {
        map[row][column]=head.like;
        map[row-1][column-1]=t[0].like;
        map[row-2][column-2]=t[1].like;
        map[row-3][column-3]=t[2].like;
        map[row-1][column+1]=t[3].like;
        map[row-2][column+2]=t[4].like;
        map[row-3][column+3]=t[5].like;
    }
}

class WildGooseFly extends Formation
{
    void arrange(Creatures head,Creatures t[],char map[][])
    {
        map[row][column]=head.like;
        map[row-1][column+1]=t[0].like;
        map[row-2][column+2]=t[1].like;
        map[row-3][column+3]=t[2].like;
        map[row-4][column+4]=t[3].like;
    }
}
class World
{
    public static final int N = 20;
    public static final int NobodysNum = 19;
    public static final char Space = ' ';
    char map[][]=new char[N][N];
    Huluwas members[]=new Huluwas[7];
    Creatures Scorpion;
    Creatures Snake;
    Creatures Grandpa;
    Creatures Nobodys[]=new Creatures[NobodysNum];
    void Init()
    {
        for(int i=0;i<7;i++)
        {
            members[i]=new Huluwas();
            members[i].ranking=i;
        }
        members[0].color="红色";members[0].like='1';
        members[1].color="橙色";members[1].like='2';
        members[2].color="黄色";members[2].like='3';
        members[3].color="绿色";members[3].like='4';
        members[4].color="青色";members[4].like='5';
        members[5].color="蓝色";members[5].like='6';
        members[6].color="紫色";members[6].like='7';
        Scorpion=new Creatures();
        Snake=new Creatures();
        Grandpa=new Creatures();
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                map[i][j]=Space;
            }
        }
        Grandpa.like='Y';
        Scorpion.like='+';
        Snake.like='S';
        for(int i=0;i<NobodysNum;i++)
        {
            Nobodys[i]=new Creatures();
            Nobodys[i].like='*';
        }
    }
    void Printmap()
    {
        System.out.println("当前地图为：");
        for(int i=0;i<N;i++)
        {
            System.out.print('-');
        }
        System.out.println();
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        for(int i=0;i<N;i++)
        {
            System.out.print('-');
        }
        System.out.println();
    }
    void StandRandomly()
    {
        System.out.print("葫芦娃随机站队后");
        int[] sequence=new int[7];
        for(int i=0;i<7;i++)
        {
            sequence[i]=i;
        }
        //Random r=new Random();
        for(int i=0;i<7;i++)
        {
            int p=(int)(Math.random()*7);
            //System.out.println(p);
            int tmp=sequence[i];
            sequence[i]=sequence[p];
            sequence[p]=tmp;
        }
        for(int i=0;i<7;i++)
        {
            members[i].column=N/4;
            members[i].row=sequence[i]+N/2-3;
            map[sequence[i]+N/2-3][N/4]=members[i].like;//(char)(i+'0');
            
        }
        /*System.out.println("随机站队后排列顺序为:");
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                
                System.out.print(map[i][j]);
            }
        System.out.println();
        }*/
    }
    void Sort()
    {
        System.out.print("冒泡排序后，");
        for(int i=0;i<7;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(map[j+N/2-3][N/4]>map[j+1+N/2-3][N/4])
                {
                    int a1=(int)(map[j+N/2-3][N/4]-'0');
                    int a2=(int)(map[j+1+N/2-3][N/4]-'0');
                    /*System.out.print(a1);
                    System.out.print(a2);
                    System.out.println();*/
                    members[a1-1].Exchange(j+1+N/2-3);
                    members[a2-1].Exchange(j+N/2-3);
                    char tp=map[j+N/2-3][N/4];
                    map[j+N/2-3][N/4]=map[j+1+N/2-3][N/4];
                    map[j+1+N/2-3][N/4]=tp;
                }
            }
    
        }
        /*for(int i=0;i<7;i++)
        {
            members[map[i]].Baoshu();
        }*/
    }
    void StandBadguys(Formation a)
    {
        a.row=N/2+1;
        a.column=3*N/4;
        a.arrange(Scorpion,Nobodys,map);
    }
    void Clear()
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                map[i][j]=Space;
            }
        }
    }
    void LaySnake()
    {
        for(int i=0;i<N;i++)
        {
            int m=0;
            for(int j=N-1;j>=0;j--)
            {
                if(map[i][j]==Space)
                {
                    map[i][j]=Snake.like;
                    Snake.row=i;
                    Snake.column=j;
                    m=1;
                    break;
                }
            }
            if(m==1)
            break;
        }
    }
    void LayGrandpa()
    {
        int m=0;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(map[i][j]==Space)
                {
                    map[i][j]=Grandpa.like;
                    Grandpa.row=i;
                    Grandpa.column=j;
                    m=1;
                    break;
                }
            }
            if(m==1)
            break;
        }
    }
}
