package app;
import java.util.*;
import java.lang.reflect.*;
public class Court
{
    static Random rand = new Random();
    int mapx;
    int mapy;
    char[][]chmap;
    int [][]idmap;
    Court()
    {
        mapx = 10;
        mapy = 10;
        init();
    }
    Court(int x,int y)
    {
        mapx = x;
        mapy = y;
        init();
    }
    void init()
    {
        chmap = new char[mapx][mapy];
        idmap = new int[mapx][mapy];
        for(int i = 0;i < mapx;i++)
        {
            for(int j = 0;j < mapy;j++)
            {
                chmap[i][j] = 'O';
                idmap[i][j] = -1;
            }
        }
    }
    public void setInCourt(int x,int y,int id,char skin)
    {
        idmap[x][y] = id;
        chmap[x][y] = skin;
    }
    public void shufflePlace(CreatureVec cvec)
    {
        Enumeration<Creature>  e = cvec.creavec.elements();
        Creature classtemp = cvec.creavec.get(0);
        boolean grbool;
        char skin;
        try
        {
            Class<?> creaclass = classtemp.getClass();
            Method me1 = creaclass.getDeclaredMethod("getgrbool");
            Method me2 = creaclass.getDeclaredMethod("getsk");
            grbool = (boolean)me1.invoke(null);
            skin = (char)me2.invoke(null);
        }
        catch(Exception excep)
        {
            excep.printStackTrace();
            return;
        }
             while(e.hasMoreElements())
            {
                Creature temp = e.nextElement();
                int setx = 0;
                int sety = 0;
                int motox = temp.getx();
                int motoy = temp.gety();
                if(grbool)
                {
                    do
                    {
                       setx = rand.nextInt(mapx);
                       sety = rand.nextInt(mapy/2);
                    }while(chmap[setx][sety] != 'O');
                    if(motox != -1 && motoy != -1)
                    {
                        chmap[motox][motoy] = 'O';
                        idmap[motox][motoy] = -1;
                    }
                    chmap[setx][sety] = skin;
                    idmap[setx][sety] = temp.getid();
                    temp.setPlace(setx, sety);
                }
                else 
                {
                    do
                    {
                       setx = rand.nextInt(mapx);
                       sety = rand.nextInt(mapy/2) + mapy/2;
                    }while(chmap[setx][sety] != 'O');
                    if(motox != -1 && motoy != -1)
                    {
                        chmap[motox][motoy] = 'O';
                        idmap[motox][motoy] = -1;
                    }
                    chmap[setx][sety] = skin;
                    idmap[setx][sety] = temp.getid();
                    temp.setPlace(setx, sety);
                }
            }
    }
    public void clearupPlace(CreatureVec cvec)
    {
        Enumeration<Creature>  e = cvec.creavec.elements();
        while(e.hasMoreElements())
       {
           Creature temp = e.nextElement();
           int motox = temp.getx();
           int motoy = temp.gety();
            if(motox != -1 && motoy != -1)
            {
                    chmap[motox][motoy] = 'O';
                    idmap[motox][motoy] = -1;
            }
            temp.setPlace(-1,1);
       }
    }
    
    
    public void prin()
    {
        for(int i = 0;i < mapx;i++)
        {
            for(int j = 0;j < mapy;j++)
            {
                if(chmap[i][j] == 'H' || chmap[i][j] == 'L')
                    System.out.print("" + chmap[i][j] + idmap[i][j] + " ");
                else
                    System.out.print(chmap[i][j] + "  ");
                if(j == mapy/2 - 1)
                    System.out.print(" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
}