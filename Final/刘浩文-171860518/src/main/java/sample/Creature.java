package main.java.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Creature implements MyObservable,Runnable{

    private static final int MAPSIZE = 15;
    private static final int CREATURENUM = 15;
    private static char[][] map;
    private static Object lock;
    private static int whoTurn;
    private static RouteNotifier rn;
    private static BattleNotifier bn;

    private static volatile int[] HP;
    private static volatile int[] DEF;
    private static volatile int[] ATK;

    private boolean grandpaBUFF,snakeBUFF;

    private static List<MyObserver> list;

    private int x;
    private int y;

    private int id;
    private char icon;

    private boolean goodPerson;

    public Creature()
    {
        rn=new RouteNotifier();
        bn=new BattleNotifier();
        lock=new Object();
        map=new char[MAPSIZE][];

        HP=new int[CREATURENUM];
        for(int i=0;i<CREATURENUM;i++) HP[i]=-1;
        DEF=new int[CREATURENUM];
        for(int i=0;i<CREATURENUM;i++) DEF[i]=-1;
        ATK=new int[CREATURENUM];
        for(int i=0;i<CREATURENUM;i++) ATK[i]=-1;

        grandpaBUFF=true;
        snakeBUFF=true;

        whoTurn=1;
        for(int i=0;i<MAPSIZE;i++) map[i]=new char[MAPSIZE];
        for(int i=0;i<MAPSIZE;i++)
        {
            for(int j=0;j<MAPSIZE;j++) map[i][j]='_';
        }

    }
    public Creature(int x,int y,int id,char icon,boolean goodPerson,int givenHP,int givenATK,int givenDEF)
    {
        if(x==-1)
        {
            list= new ArrayList<>();
            return;
        }
        this.x=x;
        this.y=y;
        this.id=id;
        this.icon=icon;
        this.goodPerson=goodPerson;
        /*map icon
        abcdefg huluboys
        O grandpa
        S Snake
        X scorpion
        jklmn imps
        */
        map[x][y]=icon;

        HP[id]=givenHP;
        ATK[id]=givenATK;
        DEF[id]=givenDEF;
    }
    public static void setRoute(int id,int tarX,int tarY)
    {
        rn.id=id;
        rn.tarX=tarX;
        rn.tarY=tarY;
    }
    public static void setBattle(int killer_id,int killed_id)
    {
        bn.killed_id=killed_id;
        bn.killer_id=killer_id;
    }
    private void printMap()
    {
        for(int i=0;i<MAPSIZE;i++)
        {
            for(int j=0;j<MAPSIZE;j++) System.out.print(map[j][i]);
            System.out.println();
        }
        System.out.println();
    }
    private boolean continueGame()
    {
        boolean goodAlive=false,badAlive=false;
        for(int i=0;i<8;i++) if(HP[i]>0) goodAlive=true;
        for(int i=8;i<15;i++) if(HP[i]>0) badAlive=true;
        return (goodAlive&&badAlive);
    }
    boolean findGoodPerson(char c)
    {
        if(c=='_') return false;
        if(HP[char2ID(c)]<=0) return false;
        if(c<='G' || c=='O') return true;
        return false;
    }
    boolean findBadPerson(char c)
    {
        if(c=='_') return false;
        if(HP[char2ID(c)]<=0) return false;
        return !findGoodPerson(c);
    }
    int char2ID(char c)
    {
        if(c=='_') return -1;
        switch(c)
        {
            case 'A':return 0;
            case 'B':return 1;
            case 'C':return 2;
            case 'D':return 3;
            case 'E':return 4;
            case 'F':return 5;
            case 'G':return 6;
            case 'O':return 7;
            case 'J':return 8;
            case 'K':return 9;
            case 'L':return 10;
            case 'M':return 11;
            case 'N':return 12;
            case 'S':return 13;
            case 'X':return 14;
            default:return -1;
        }
    }
    private int findEnemyID()
    {
        boolean noUp=false,noDown=false,noLeft=false,noRight=false;
        if(x==0) noLeft=true;
        if(x==MAPSIZE-1) noRight=true;
        if(y==0) noUp=true;
        if(y==MAPSIZE-1) noDown=true;
        if(goodPerson)
        {
            if(noUp);
            else {
                if(findBadPerson(map[x][y-1])) return char2ID(map[x][y-1]);
            }
            if(noDown);
            else {
                if(findBadPerson(map[x][y+1])) return char2ID(map[x][y+1]);
            }
            if(noLeft);
            else {
                if(findBadPerson(map[x-1][y])) return char2ID(map[x-1][y]);
            }
            if(noRight);
            else {
                if(findBadPerson(map[x+1][y])) return char2ID(map[x+1][y]);
            }
        }
        else
        {
            if(noUp);
            else {
                if(findGoodPerson(map[x][y-1])) return char2ID(map[x][y-1]);
            }
            if(noDown);
            else {
                if(findGoodPerson(map[x][y+1])) return char2ID(map[x][y+1]);
            }
            if(noLeft);
            else {
                if(findGoodPerson(map[x-1][y])) return char2ID(map[x-1][y]);
            }
            if(noRight);
            else {
                if(findGoodPerson(map[x+1][y])) return char2ID(map[x+1][y]);
            }
        }
        return -1;
    }
    public int decideDirect(boolean test)
    {
        int[] directPool=new int[40];
        int rand=new Random().nextInt(40);
        if(x<6)
        {
            for(int i=0;i<5;i++) directPool[i]=0;
            for(int i=5;i<20;i++) directPool[i]=1;
        }
        else if(x>8)
        {
            for(int i=0;i<15;i++) directPool[i]=0;
            for(int i=15;i<20;i++) directPool[i]=1;
        }
        else
        {
            for(int i=0;i<10;i++) directPool[i]=0;
            for(int i=10;i<20;i++) directPool[i]=1;
        }
        if(y<6)
        {
            for(int i=20;i<25;i++) directPool[i]=2;
            for(int i=25;i<40;i++) directPool[i]=3;
        }
        else if(y>8)
        {
            for(int i=20;i<35;i++) directPool[i]=2;
            for(int i=35;i<40;i++) directPool[i]=3;
        }
        else
        {
            for(int i=20;i<30;i++) directPool[i]=2;
            for(int i=30;i<40;i++) directPool[i]=3;
        }
        if(test)
        {
            for(int i=0;i<40;i++) System.out.print(directPool[i]);
            System.out.print('\n');
        }
        return directPool[rand];
    }
    private void moveAndBattle() throws InterruptedException, IOException {
        synchronized (lock) {
            while (whoTurn % CREATURENUM != id) {
                lock.wait();
            }
            if(HP[id]<=0)
            {
                //whoTurn++;
                System.out.println("died:  "+id);
                //lock.notifyAll();
                //return;
            }
            else {
                int direct;
                int whileTime=0;
                int tarX = x, tarY = y;
                do {
                    whileTime++;
                    if(whileTime==30)
                    {
                        Thread.sleep(500);
                        whoTurn++;
                        lock.notifyAll();
                        return;
                    }
                    direct = decideDirect(false);
                    System.out.println("t direct "+direct);
                    switch (direct) {
                        case 0://left
                            tarX=x-1;
                            break;
                        case 1://right
                            tarX=x+1;
                            break;
                        case 2://up
                            tarY=y-1;
                            break;
                        case 3://down
                            tarY=y+1;
                            break;
                        default:
                            break;
                    }
                    //out of bound then on the other way
                    if (tarX >= MAPSIZE)
                    {
                        tarX -= 2;
                        direct=0;
                    }
                    if (tarX < 0)
                    {
                        tarX += 2;
                        direct=1;
                    }
                    if (tarY >= MAPSIZE)
                    {
                        tarY -= 2;
                        direct=2;
                    }
                    if (tarY < 0)
                    {
                        tarY += 2;
                        direct=3;
                    }
                } while (map[tarX][tarY] != '_');
                System.out.println("this is " + id);
                System.out.println("from "+x+" "+y+" to "+tarX+" "+tarY+"   direct"+direct);
                map[x][y] = '_';
                map[tarX][tarY] = icon;
                x = tarX;
                y = tarY;
                setRoute(id,tarX,tarY);
                routeNotify();
                logNotify(0,id,tarX,tarY,0);
                //move end
                int isDamaged=findEnemyID();
                if(isDamaged!=-1)
                {
                    if(ATK[id]-DEF[isDamaged]>0)
                        HP[isDamaged]-=(ATK[id]-DEF[isDamaged]);

                    if(HP[isDamaged]<=0) {
                        setBattle(id, isDamaged);
                        battleNotify();
                        logNotify(1, id, isDamaged, 0, 0);//someone down

                        if(isDamaged==7 && grandpaBUFF)
                        {
                            //grandpa is killed, huluboys attack and defense down
                            grandpaBUFF=false;
                            for(int i=0;i<7;i++)
                            {
                                ATK[i]-=5;
                                DEF[i]-=5;
                            }
                        }
                        if(isDamaged==13 && snakeBUFF)
                        {
                            //snake is killed, imps and scorpion attack and defense down
                            snakeBUFF=false;
                            for(int i=8;i<15;i++)
                            {
                                if(i==13) continue;
                                ATK[i]-=10;
                                DEF[i]-=5;
                            }
                        }
                    }
                }
                //printMap();
                Thread.sleep(200);
            }
            whoTurn++;
            lock.notifyAll();
        }
    }
    @Override
    public void run() {
        while (continueGame()) {
            try {
                moveAndBattle();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void registerObserver(MyObserver o) {
        list.add(o);
        System.out.println("added observer "+ o.getClass().getName());
    }

    @Override
    public void routeNotify() {
        list.get(0).routeUpdate(rn);
    }

    @Override
    public void battleNotify() {
        list.get(0).battleUpdate(bn);
    }

    @Override
    public void logNotify(int type, int a, int b, int c, int d) throws IOException {
        list.get(0).gameLogPrint(type,a,b,c,d);
    }
}
