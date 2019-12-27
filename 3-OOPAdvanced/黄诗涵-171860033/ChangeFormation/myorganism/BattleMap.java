package myorganism;
import java.util.PrimitiveIterator;
import java.util.Vector;

public class BattleMap {
    private Organism[][] map;
    private int size;
    private int mid;
    private Vector<Monster> monsters;
    private Vector<Scorpion> scorpions;
    public BattleMap(int N) {
       try{
           if(N<18)
               throw new IllegalArgumentException();
           size=N;
            mid=(size-1)/2;
            map=new Organism[N][N];
            monsters=new Vector<Monster>(size*size);
            scorpions=new Vector<Scorpion>(size*size);
       }
       catch (IllegalArgumentException i)
       {
           System.out.println("Size of map too small.(N>=18)");
       }

    }
    public void initmap() {
        int mid=(size-1)/2;
        map[mid][0]=new Grandfather(mid,0);
        map[mid][2]=new Red(mid,2);
        map[mid][3]=new Orange(mid,3);
        map[mid][4]=new Yellow(mid,4);
        map[mid][5]=new Green(mid,5);
        map[mid][6]=new Indigo(mid,6);
        map[mid][7]=new Blue(mid,7);
        map[mid][8]=new Purple(mid,8);
        map[mid][size-1]=new Snack(mid,size-1);
    }
    public void createScorpions(int num)
    {
        for(int i=0;i<num;i++)
        {
            Scorpion temp=new Scorpion(-1,-1);
            scorpions.add(temp);
        }
    }
    public void createMonsters(int num)
    {
        Scorpion s=new Scorpion(-1,-1);
        scorpions.add(s);
        for(int i=0;i<num;i++)
        {
            Monster temp=new Monster(-1,-1);
            monsters.add(temp);
        }
    }
    private void Return()
    {
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
            {   if(map[i][j]!=null&&map[i][j].type==Species.MONSTER)
                {
                    ((Monster)map[i][j]).Return(map,monsters);
                }
                else if(map[i][j]!=null&&map[i][j].type==Species.SCORPION)
                ((Scorpion)map[i][j]).Return(map,scorpions);

            }
    }
    private void Exit()
    {
       for(int i=0;i<monsters.size();i++)
           monsters.elementAt(i).exitMap(map);
       for(int i=0;i<scorpions.size();i++)
           scorpions.elementAt(i).exitMap(map);
    }
    public void crescentMoon()
    {
        Return();
        for(int i=mid-1;i<=mid+1;i++)
        {
            for(int j=mid+1;j<=mid+3;j++)
            {
                if(monsters.size()==0)
                    return;
                if(i==mid&&j==mid+1)
                {
                    scorpions.elementAt(0).move(i,j,map);
                    scorpions.remove(0);
                }
                monsters.elementAt(0).move(i,j,map);
                monsters.remove(0);
            }
        }
        int up=mid-2,down=mid+2;
        int k=mid+2;
        for(;up>=0&&down<size;up--,down++,k++)
            for(int i=1;i>=0;i--)
            {
                if(monsters.size()==0)
                    return;
                monsters.elementAt(0).move(up,k+i,map);
                monsters.remove(0);
                if(monsters.size()==0)
                    return;
                monsters.elementAt(0).move(down,k+i,map);
                monsters.remove(0);
            }
       Exit();
    }
    public void craneWing()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        scorpions.elementAt(0).move(mid,mid+1,map);
        scorpions.remove(0);
        for(int i=0;(mid-1-i)>0&&(mid+2+i)<size-1;i++)
        {
            if(monsters.size()==0)
                return;
            if(map[mid-1-i][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid-1-i,mid+2+i,map);
            monsters.remove(0);
            if(monsters.size()==0)
                return;
            if(map[mid+1+i][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid+1+i,mid+2+i,map);
            monsters.remove(0);
        }
        Exit();

    }
    public void wildGoose()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        scorpions.elementAt(0).move(mid,mid+1,map);
        scorpions.remove(0);
        for(int i=0;(mid-1-i)>0&&(mid+2+i)<size-1;i++)
        {
            if(monsters.size()==0)
                return;
            if(map[mid-1-i][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid-1-i,mid+2+i,map);
            monsters.remove(0);
        }
        Exit();
    }
    public void chongE()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        scorpions.elementAt(0).move(mid,mid+1,map);
        scorpions.remove(0);
        boolean k=false;
        int x,y;
        for(int i=mid+2;i<size-1;i++)
        {
            if(monsters.size()==0)
                return;
           if(k)
           {x=mid;y=i;k=!k;}
           else
           {x=mid-1;y=i;k=!k;}

            if(map[x][y]!=null)
                continue;
            monsters.elementAt(0).move(x,y,map);
            monsters.remove(0);
        }
        Exit();
    }
    public void fishScale()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        scorpions.elementAt(0).move(mid,mid+1,map);
        scorpions.remove(0);
        monsters.elementAt(0).move(mid-1,mid+2,map);
        monsters.remove(0);
        int total=monsters.size();
        int sum=0;
        int k=1;
        while(total>sum)
        {
            sum=k*3+(k-1)*k;
            k++;
        }
        k--;
        if(total>0)
        {
            monsters.elementAt(0).move(mid,mid+2+k,map);
            monsters.remove(0);
        }
        for(int j=0;j<k-1&&j<size-1;j++)
        {
            for(int i=mid-1-j;i<=mid+1+j&&i>=0&&i<size;i++)
            {
                if(monsters.size()==0)
                    return;
                if(map[i][mid+3+j]!=null)
                    continue;
                monsters.elementAt(0).move(i,mid+3+j,map);
                monsters.remove(0);
            }
        }
        Exit();
    }
    public void HouEn()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        scorpions.elementAt(0).move(mid,mid+1,map);
        scorpions.remove(0);
        int k=0;
        while(4*k-1<=monsters.size()&&k<mid/2)
            k++;
        for(int i=0;i<k;i++)
        {
            if(monsters.size()==0)
                return;
            if(map[mid-1-i][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid-1-i,mid+2+i,map);
            monsters.remove(0);
            if(monsters.size()==0)
                return;
            if(map[mid+1+i][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid+1+i,mid+2+i,map);
            monsters.remove(0);
        }
        int j=mid+2+k;
        for(int i=0;i<k-1;i++,j++)
        {
            if(monsters.size()==0)
                return;
            if(map[mid-k+1+i][j]!=null)
                continue;
            monsters.elementAt(0).move(mid-k+1+i,j,map);
            monsters.remove(0);
            if(monsters.size()==0)
                return;
            if(map[mid-1+k-i][j]!=null)
                continue;
            monsters.elementAt(0).move(mid-1+k-i,j,map);
            monsters.remove(0);
        }
        monsters.elementAt(0).move(mid,mid+1+2*k,map);
        monsters.remove(0);
        Exit();
    }
    public void sharpArrow()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        scorpions.elementAt(0).move(mid,mid+1,map);
        scorpions.remove(0);
        int k=0;
        while((4*k<=monsters.size())&&k<mid-1&&(mid+2+2*k)<size-1)
            k++;
        for(int i=0;i<k;i++)
        {
            if(monsters.size()==0)
                return;
            if(map[mid-1-i][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid-1-i,mid+2+i,map);
            monsters.remove(0);
        }
        for(int i=0;i<k;i++)
        {
            if(monsters.size()==0)
                return;
            if(map[mid+1+i][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid+1+i,mid+2+i,map);
            monsters.remove(0);
        }
        for(int i=0;i<2*k;i++)
        {
            if(monsters.size()==0)
                return;
            if(map[mid][mid+2+i]!=null)
                continue;
            monsters.elementAt(0).move(mid,mid+2+i,map);
            monsters.remove(0);
        }
        Exit();
    }
    public void printMap()
    {
        for(int i=0;i<size;i++)
        {    for(int j=0;j<size;j++)
            {
                if(map[i][j]==null)
                {
                    System.out.printf("□  ");
                    continue;
                }
                if(map[i][j].type==Species.HULUWA)
                {
                    switch (((Huluwa)map[i][j]).getnum())
                    {
                        case 1:System.out.printf("\u001b[31m●\u001b[0m  ");break;
                        case 2:System.out.printf("\u001b[91m●\u001b[0m  ");break;
                        case 3:System.out.printf("\u001b[33m●\u001b[0m  ");break;
                        case 4:System.out.printf("\u001b[32m●\u001b[0m  ");break;
                        case 5:System.out.printf("\u001b[36m●\u001b[0m  ");break;
                        case 6:System.out.printf("\u001b[34m●\u001b[0m  ");break;
                        case 7:System.out.printf("\u001b[35m●\u001b[0m  ");break;
                    }
                }
                else if(map[i][j].type==Species.GRANDFATHER)
                    System.out.printf("\u001b[96m★\u001b[0m ");
                else if(map[i][j].type==Species.MONSTER)
                    //System.out.printf("\u001b[37m●\u001b[0m  ");
                    System.out.printf("●  ");
                else if(map[i][j].type==Species.SNAKE)
                    System.out.printf("\u001b[91m★\u001b[0m ");
                else if(map[i][j].type==Species.SCORPION)
                    System.out.printf("\u001b[95m▲\u001b[0m  ");
            }
            System.out.printf("\n");
        }
        System.out.printf("\n\n");
    }

    public void test()
    {
        map[0][0]=new Red(0,0);
        map[0][0].move(0,1,map);
        printMap();
    }
}