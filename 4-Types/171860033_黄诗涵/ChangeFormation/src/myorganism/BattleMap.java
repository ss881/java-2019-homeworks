package myorganism;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.PrimitiveIterator;
import java.util.Vector;

public class BattleMap {
    private Position[][] position;
    private int size;
    private int mid;
    private Vector<Monster> monsters;
    private int monsterNum;
    private Vector<Scorpion> scorpions;
    public BattleMap(int N) {
       try{
           if(N<18)
               throw new IllegalArgumentException();
           size=N;
            mid=(size-1)/2;
            position=new Position[N][N];
            monsters=new Vector<Monster>(size*size);
            scorpions=new Vector<Scorpion>(size*size);

            for (int i=0;i<N;i++)
                for(int j=0;j<N;j++)
                    position[i][j]=new Position(i,j);
       }
       catch (IllegalArgumentException e) {
           System.out.println("Size of map too small.(N>=18)"); }

    }
    public void initmap() {

        int mid=(size-1)/2;
        Position<Grandfather> p=new Position<Grandfather>(mid,0);
        p.set(new Grandfather());
        position[mid][0]=p;

        Position<Red> p1=new Position<Red>(mid,2);
        p1.set(new Red());
        position[mid][2]=p1;

        Position<Orange> p2=new Position<>(mid,3);
        p2.set(new Orange());
        position[mid][3]=p2;

        Position<Yellow> p3=new Position<Yellow>(mid,4);
        p3.set(new Yellow());
        position[mid][4]=p3;

        Position<Green> p4=new Position<Green>(mid,5);
        p4.set(new Green());
        position[mid][5]=p4;

        Position<Indigo> p5=new Position<Indigo>(mid,6);
        p5.set(new Indigo());
        position[mid][6]=p5;

        Position<Blue> p6=new Position<Blue>(mid,7);
        p6.set(new Blue());
        position[mid][7]=p6;

        Position<Purple> p7=new Position<Purple>(mid,8);
        p7.set(new Purple());
        position[mid][8]=p7;

        Position<Snack> p8=new Position<>(mid,size-1);
        p8.set(new Snack());
        position[mid][size-1]=p8;
    }
    public void createMonsters(int num)
    {
        Scorpion s=new Scorpion();
        scorpions.add(s);
        for(int i=0;i<num;i++)
        {
            Monster temp=new Monster();
            temp.setId(Integer.toString(i));
            monsters.add(temp);
        }
        monsterNum=num;
    }
    private void Return()
    {
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
            {
                if(position[i][j].get()!=null)
                {
                    if(Monster.class.isInstance(position[i][j].get())||Scorpion.class.isInstance(position[i][j].get()))
                    {
                        try {
                            Class c=position[i][j].get().getClass();
                            Method method=c.getMethod("setQueuing", boolean.class);
                            method.invoke(position[i][j].get(),false);
                        }
                        catch (NoSuchMethodException e) {}
                        catch (IllegalAccessException e){}
                        catch (InvocationTargetException e){}
                        position[i][j].set(null);
                    }
                }
           /*     if(map[i][j]!=null&&map[i][j].type==Species.MONSTER)
                {
                    ((Monster)map[i][j]).Return(map,monsters);
                }
                else if(map[i][j]!=null&&map[i][j].type==Species.SCORPION)
                ((Scorpion)map[i][j]).Return(map,scorpions);
            */
            }
    }
    private void Exit()
    {
       for(int i=0;i<monsters.size();i++) {
           if (monsters.get(i).queuing == false)
               monsters.get(i).setpos(null);
       }
       for(int i=0;i<scorpions.size();i++)
           if(scorpions.get(i).queuing==false)
               scorpions.get(0).setpos(null);
    }
    public void crescentMoon()
    {
        Return();
        int count=0;
        for(int i=mid-1;i<=mid+1;i++)
        {
            for(int j=mid+1;j<=mid+3;j++)
            {
                if(count==monsterNum)
                    return;
                if(i==mid&&j==mid+1)
                {
                    position[i][j].set(scorpions.get(0));
                    continue;
                }
                position[i][j].set(monsters.get(count++));
            }
        }
        int up=mid-2,down=mid+2;
        int k=mid+2;
        for(;up>=0&&down<size;up--,down++,k++)
            for(int i=1;i>=0;i--)
            {
                if(count==monsterNum)
                    return;
                position[up][k+i].set(monsters.elementAt(count++));
                if(count==monsterNum)
                    return;
                position[down][k+i].set(monsters.elementAt(count++));
            }
        Exit();
    }
    public void craneWing()
    {
        System.out.println("Crane Wing");
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        int count=0;
        position[mid][mid+1].set(scorpions.get(0));
        for(int i=0;(mid-1-i)>0&&(mid+2+i)<size-1;i++)
        {
            if(count==monsterNum)
                return;
            //monsters.elementAt(0).move(mid-1-i,mid+2+i,map);
            //monsters.remove(0);
            position[mid-1-i][mid+2+i].set(monsters.get(count++));
            if(count==monsterNum)
                return;
//            monsters.elementAt(0).move(mid+1+i,mid+2+i,map);
  //          monsters.remove(0);
            position[mid+1+i][mid+2+i].set(monsters.get(count++));
        }
        Exit();
    }
    public void wildGoose()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        int count=0;
        position[mid][mid+1].set(scorpions.get(0));
        for(int i=0;(mid-1-i)>0&&(mid+2+i)<size-1;i++)
        {
            if(monsterNum==count)
                return;
            position[mid-1-i][mid+2+i].set(monsters.get(count++));
        }
        Exit();
    }
    public void chongE()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        int count=0;
        position[mid][mid+1].set(scorpions.get(0));
        boolean k=false;
        int x,y;
        for(int i=mid+2;i<size-1;i++)
        {
            if(monsterNum==count)
                return;
           if(k)
           {x=mid;y=i;k=!k;}
           else
           {x=mid-1;y=i;k=!k;}
            position[x][y].set(monsters.get(count++));
        }
        Exit();
    }
    public void fishScale()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        int count=0;
        position[mid][mid+1].set(scorpions.get(0));
        position[mid-1][mid+2].set(monsters.get(count++));
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
            position[mid][mid+2].set(monsters.get(count++));
            //monsters.elementAt(0).move(mid,mid+2+k,map);
            //monsters.remove(0);
        }
        for(int j=0;j<k-1&&j<size-1;j++)
        {
            for(int i=mid-1-j;i<=mid+1+j&&i>=0&&i<size;i++)
            {
                if(monsterNum<=count)
                    return;
               // if(map[i][mid+3+j]!=null)
                 //   continue;
                position[i][mid+3+j].set(monsters.get(count++));
              //  monsters.elementAt(0).move(i,mid+3+j,map);
               // monsters.remove(0);
            }
        }
        Exit();
    }
    public void fangYuan()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        int count=0;
        position[mid][mid+1].set(scorpions.get(0));
        int k=0;
        while(4*k-1<=monsters.size()&&k<mid/2)
            k++;
        for(int i=0;i<k;i++)
        {
            if(monsterNum==count)
                return;
            position[mid-1-i][mid+2+i].set(monsters.get(count++));
            if(monsterNum==count)
                return;
            position[mid+1+i][mid+2+i].set(monsters.get(count++));
        }
        int j=mid+2+k;
        for(int i=0;i<k-1;i++,j++)
        {
            if(monsterNum==count)
                return;
            position[mid-k+i+1][j].set(monsters.get(count++));
            if(monsters.size()==0)
                return;
            position[mid-1-i+k][j].set(monsters.get(count++));
        }
        position[mid][mid+1+2*k].set(monsters.get(count++));
        //monsters.elementAt(0).move(mid,mid+1+2*k,map);
       // monsters.remove(0);
        Exit();
    }
    public void sharpArrow()
    {
        Return();
        if(monsters.size()==0||scorpions.size()==0)
            return;
        int count=0;
        position[mid][mid+1].set(scorpions.get(0));
        int k=0;
        while((4*k<=monsters.size())&&k<mid-1&&(mid+2+2*k)<size-1)
            k++;
        for(int i=0;i<k;i++)
        {
            if(monsterNum==count)
                return;
            position[mid-1-i][mid+2+i].set(monsters.get(count++));
        }
        for(int i=0;i<k;i++)
        {
            if(monsterNum==count)
                return;
            position[mid+1+i][mid+2+i].set(monsters.get(count++));
        }
        for(int i=0;i<2*k;i++)
        {
            if(monsterNum==count)
                return;
            position[mid][mid+2+i].set(monsters.get(count++));
        }
        Exit();
    }
    public void printMap()
    {
        for(int i=0;i<size;i++)
        {    for(int j=0;j<size;j++)
            {
                if(position[i][j].get()==null)
                {
                    System.out.printf("□  ");
                    continue;
                }
                if(Red.class.isInstance(position[i][j].get()) )
                    System.out.printf("\u001b[31m●\u001b[0m  ");
                else if(Orange.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[91m●\u001b[0m  ");
                else if(Yellow.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[33m●\u001b[0m  ");
                else if(Green.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[32m●\u001b[0m  ");
                else if(Indigo.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[36m●\u001b[0m  ");
                else if(Blue.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[34m●\u001b[0m  ");
                else if(Purple.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[35m●\u001b[0m  ");
            /*    if(map[i][j].type==Species.HULUWA)
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
                } */
                else if(Grandfather.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[96m★\u001b[0m ");
                else if(Monster.class.isInstance(position[i][j].get()))
                    //System.out.printf("\u001b[37m●\u001b[0m  ");
                    System.out.printf("●  ");
                else if(Snack.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[91m★\u001b[0m ");
                else if(Scorpion.class.isInstance(position[i][j].get()))
                    System.out.printf("\u001b[95m▲\u001b[0m  ");
            }
            System.out.printf("\n");
        }
        System.out.printf("\n\n");
    }


}