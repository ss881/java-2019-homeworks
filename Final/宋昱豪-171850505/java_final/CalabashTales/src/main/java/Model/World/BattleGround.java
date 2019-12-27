package Model.World;

import Model.Bad.Scorpion;
import Model.Bad.Sidekicks;
import Model.Bad.Snake;
import Model.Good.CalabashBrothers;
import Model.Good.Grandpa;
import Model.World.Tile;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BattleGround implements  Runnable{
    public static BufferedWriter out;
    public static final int M=20;
    public static final int N=20;
    public static Tile[][] ground=new Tile[M][N];
    public static HashMap<Integer, Boolean> countlive=new HashMap<Integer,Boolean>();
    public static HashMap<Integer, Boolean> countdead=new HashMap<Integer,Boolean>();
    public static boolean end=false;
    public void clear()
    {
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                ground[i][j].SetALL(false,null);
            }
        }
    }
    public BattleGround(File file) throws FileNotFoundException, UnsupportedEncodingException {
        if(file!=null)
        {
            out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Unicode"));
        }
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
              ground[i][j]=new Tile();
            }
        }

    }

    public String outcome(boolean x)
    {
        String o=null;
        if(x==true)
        {
           o="win"+"\t";
        }
        else
        {
            o="fail"+"\t";
        }
        synchronized (out)
        {

            try {
                out.write(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
    public void setend(boolean x)
    {
        end=x;
    }
    public static void whodie(int id)
    {
        countdead.put(id,countlive.get(id));
        countlive.remove(id);
        String o="die_"+id+"\t";
        synchronized (out)
        {

            try {
                out.write(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {

        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                String in;
                if(ground[i][j].GetIsOccupied()==true)
                {
                    Lives one=ground[i][j].GetWho();
                    in=one.id+"_"+one.attributes.URL+"_"+one.attributes.Hp+"_"+one.position.x+"_"+one.position.y+"\t";
                    countlive.put(one.id,true);
                    synchronized (out)
                    {
                        try {
                            out.write(in);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
        synchronized (out)
        {
            try {
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while(end!=true)
        {
            for(int i=0;i<M;i++)
            {
                for(int j=0;j<N;j++)
                {
                    String in;
                    if(ground[i][j].GetIsOccupied()==true)
                    {
                        Lives one=ground[i][j].GetWho();
                        in=one.id+"_"+one.attributes.Hp+"_"+one.position.x+"_"+one.position.y+"\t";
                        synchronized (out)
                        {
                            try {
                                out.write(in);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
            System.out.print("\n");
          /*  Iterator c=count.entrySet().iterator();
           // for(Map.Entry<Integer, Boolean> entry: count.entrySet())
            while(c.hasNext())
            {
                Map.Entry<Integer, Boolean> entry=(Map.Entry<Integer, Boolean>) c.next();
                if(entry.getValue()==false)
                {
                    try {
                        out.write("die_"+entry.getKey()+"\t");
                       // System.out.print("somebody dieeeeeeeeeeeeeeeeeeeeeeeeeee"+entry.getKey());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    c.remove();
                }

            }
            for(int key:count.keySet())
            {
               count.put(key,false);
            }*/
           synchronized (out)
           {
               try {
                   out.newLine();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (out)
        {
            if (out != null)
            {
                try
                {
                    //刷新缓存区
                    out.flush();
                    out.close();
                } catch (IOException e)
                {

                }
            }
        }

    }

}

