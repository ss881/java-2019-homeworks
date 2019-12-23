package app;
import java.util.*;
import java.lang.reflect.*;
class CreatureControl
{
    CreatureVec[] creavecs = new CreatureVec[Creature.getcreanum()];
    static final int mapx = 10;
    static final int mapy = 10;
    static final int louluonum = 8;
    Court court;
    CreatureControl()
    {
        court = new Court(10,10);
        for(int i = 0;i < Creature.getcreanum();i++)
            creavecs[i] = new CreatureVec();
    }
  /*  int getCreatureOrder(String creaname) throws Exception
    {
        try
        {
            Class<?> classcrea = Class.forName("app."+creaname);
            Method ormethod = classcrea.getDeclaredMethod("getorder");
            int order = (int)ormethod.invoke(null);
            return order;
        }
        catch(Exception e)
        {
            throw e;
        }
    }*/

    public void addEle(String creaname,String name,int x,int y)
    {
        try
        {
             Class<?> classcrea = Class.forName("app."+creaname);
             Class<?>[] paraclass = {String.class,int.class,int.class};
             Constructor<?> con = classcrea.getDeclaredConstructor(paraclass);
             Method ormethod = classcrea.getDeclaredMethod("getorder");
             Creature creaobject = (Creature)con.newInstance(name,x,y);
             int order = (int)ormethod.invoke(creaobject);
             creavecs[order].addEle(creaobject);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    public void initCreatures()
    {
        addEle("HuLuWa","大娃",-1,-1);
        addEle("HuLuWa","二娃",-1,-1);
        addEle("HuLuWa","三娃",-1,-1);
        addEle("HuLuWa","四娃",-1,-1);
        addEle("HuLuWa","五娃",-1,-1);
        addEle("HuLuWa","六娃",-1,-1);
        addEle("HuLuWa","七娃",-1,-1);
        addEle("YeYe","爷爷",-1,-1);
        addEle("XieZi","蝎子精",-1,-1);
        addEle("SheJing","蛇精",-1,-1);
        for(int i = 0;i < louluonum;i++)
            addEle("LouLuo","喽啰",-1,-1);
    }
   public void shuffleAll()
   {
    for(int i = 0;i < Creature.getcreanum();i++)
    {
        court.shufflePlace(creavecs[i]);
    }
   }

    public void sortHuPlace(int mode)
    {
        court.clearupPlace(creavecs[HuLuWa.getorder()]);
        court.clearupPlace(creavecs[YeYe.getorder()]);
        Enumeration<Creature>  e = creavecs[HuLuWa.getorder()].creavec.elements();
        switch(mode)
        {
        case 0://snake
                int i = 0;
                while(e.hasMoreElements())
                {
                   Creature temp = e.nextElement();
                   if(i < mapx)
                   {
                        temp.setPlace(i,0);
                        court.setInCourt(i, 0, temp.getid(), HuLuWa.getsk());
                        i++;
                   }
                   else
                        break;

                }
        break;

        default:break;
        }
          
         
    
          
        court.shufflePlace(creavecs[YeYe.getorder()]);
    }
    public void sortOppoPlace(int mode)
    {
        court.clearupPlace(creavecs[XieZi.getorder()]);
        court.clearupPlace(creavecs[SheJing.getorder()]);
        court.clearupPlace(creavecs[LouLuo.getorder()]);
        Enumeration<Creature>  e1 = creavecs[LouLuo.getorder()].creavec.elements();
        Enumeration<Creature>  e2 = creavecs[XieZi.getorder()].creavec.elements();
        Creature Xie = e2.nextElement();
        switch(mode)
        {
        case 0://chong
            int i = 1;
            char flg = 'F';
            while(e1.hasMoreElements())
            {
                Creature temp = e1.nextElement();
                if(i < mapx && flg == 'F')
                {
                    temp.setPlace(i,mapy-2);
                    court.setInCourt(i,mapy-2,temp.getid(),LouLuo.getsk());
                    flg = 'T';
                }
                else if(i < mapx && flg == 'T')
                {
                    temp.setPlace(i,mapy-1);
                    court.setInCourt(i,mapy-1,temp.getid(),LouLuo.getsk());
                    flg = 'F';
                }
                else
                {
                    break;
                }
                i++;
            }
            Xie.setPlace(0, mapy-1);
            court.setInCourt(0,mapy-1,Xie.getid(),XieZi.getsk());
            break;
        case 1://heyi
            int cenx = mapx/2;
            int ceny = mapy/2;
            Xie.setPlace(cenx, ceny);
            court.setInCourt(cenx,ceny,Xie.getid(),XieZi.getsk());
            int ord = 0;
            while(ord < louluonum/2 && cenx > 0 && ceny < mapy)
            {
                Creature temp = e1.nextElement();
                cenx--;
                ceny++;
                temp.setPlace(cenx,ceny);
                court.setInCourt(cenx,ceny,temp.getid(),LouLuo.getsk());
                ord++;
            }
            cenx = mapx/2;
            ceny = mapy/2;
            while(ord < louluonum && cenx < mapx && ceny < mapy)
            {
                Creature temp = e1.nextElement();
                cenx++;
                ceny++;
                temp.setPlace(cenx,ceny);
                court.setInCourt(cenx,ceny,temp.getid(),LouLuo.getsk());
                ord++;
            }
        break;
        default:break;
        } 
        court.shufflePlace(creavecs[SheJing.getorder()]);    
    }
    
    
}