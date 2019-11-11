package map;
import java.util.*;
import organism.*;
import formation.*;
import position.*;

import java.util.Random;

public class Map {
    private int len;
    private int width;
    private Organism all[][];
    private ArrayList<Formation> array=new ArrayList<>();

    public Map(int maxlength,int maxwidth)
    {
        this.len=maxlength;
        this.width=maxwidth;
        all=new Organism[maxlength][maxwidth];

        for(int i =0;i<len;i++)
        {
            for(int j=0;j<width;j++) all[i][j]=new Land();
        }

        storeOrganism();
    }

    private  void addFormation(Formation formation)
    {
        Organism organisms[][]=formation.getOrganisms();

        for(int i=0;i<organisms.length;i++)
        {
            for(int j=0;j<organisms[i].length;j++)
            {
                Organism temp=null;
                if(organisms[i][j] instanceof Soldier) temp=new Soldier((Soldier)organisms[i][j]);
                else if(organisms[i][j] instanceof Scorpion) temp=new Scorpion((Scorpion)organisms[i][j]);
                else if(organisms[i][j] instanceof Land) temp=new Land((Land)organisms[i][j]);

                int x=temp.getPosition().getX()+2;
                int y=temp.getPosition().getY()+8;
                temp.setPosition(new Position(x,y));
                //阵图中草地会覆盖原来地图的喽啰或者其他生物
                all[x][y]=temp;
            }
            //System.out.println();
        }
    }

    public void addOrganism(Organism person)
    {
        if(person.getPosition().getX()!=-1)
        {
            int x=person.getPosition().getX();
            int y=person.getPosition().getY();
            all[x][y]=person;
        }

        else
        {
            Random random=new Random();
            int x=random.nextInt(this.len-2);
            int y=random.nextInt(3);  //左边的三列
            while(all[x][y] instanceof Land==false)
            {
                x=random.nextInt(this.len-2);
                y=random.nextInt(3);
            }

            if(person instanceof  GrandFather)
            {
               // System.out.println(x+" "+y);
                person.setPosition(new Position(x,y));
                all[x][y]=person;
            }

            else if(person instanceof  Snake)
            {
                //System.out.println(x+" "+(y+10));
                person.setPosition(new Position(x,y+10));
                all[x][y+10]=person;
            }

        }


    }

    private void storeOrganism()
    {
        Organism organisms[]=new Organism[18];
        Organism leader=new Snake();
        for(int i=0;i<18;i++) organisms[i]=new Soldier();
        Formation formations[]=new Formation[7];


        formations[0]=new CraneWingFormation();
        formations[1]=new WildGooseFormation();
        formations[2]=new YoKeFormation();
        formations[3]=new FishScaleFormation();
        formations[4]=new FangWeiFormation();
        formations[5]=new HaoYueFormation();
        formations[6]=new FrontalFormation();

        for(int i=0;i<7;i++) array.add(formations[i]);
    }

    public void selectFormation(int index)
    {
        addFormation(array.get(index));
    }

    public void clearMap()
    {
        for(int i =0;i<len;i++)
        {
            for(int j=0;j<width;j++) all[i][j]=new Land();
        }
    }

    public void printMap()
    {
        for(int i =0;i<len;i++)
        {
            for(int j=0;j<width;j++)
            {
                if(all[i][j] instanceof Huluwa) System.out.print(all[i][j].getName()+" ");
                else System.out.print(all[i][j].getName()+"   ");
            }
            System.out.println();
        }
        System.out.println();
    }


    /*
    public void printclass()
    {
        for(int i =0;i<len;i++)
        {
            for(int j=0;j<width;j++)
            {
                System.out.println(all[i][j].getClass());
            }
            System.out.println();
        }
        System.out.println();
    }

     public void exchange()
    {
        for(int i =0;i<len;i++)
        {
            for(int j=0;j<width;j++)
            {
               if(all[i][j] instanceof GrandFather)
               {
                   Land temp=(Land)all[i][j+1];
                   System.out.println(temp);
                   System.out.println(all[i][j+1]);
                   all[i][j+1]=all[i][j];
                   all[i][j]=temp;
                   return ;
               }
            }

        }

    }
    */

}
