import  formation.*;
import  organism.*;
import  map.*;
import position.Position;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void GameStart()
    {
        Map map=new Map(17,17);
        Huluwa brothers[]=new Huluwa[7];
        Random random=new Random();

        System.out.println("葫芦娃归位长蛇阵：");
        for(int i=0;i<7;i++)
        {
            brothers[i]=new Huluwa(i+1,random.nextInt(16),random.nextInt(16));
            brothers[i].moveto(i+2,0);
            map.addOrganism(brothers[i]);
        }


        Organism snake=new Snake();
        Organism grandfather=new GrandFather();


        int index=0,num=6;
        System.out.println("0.鹤翼   1.雁行   2.衡轭   3.鱼鳞    4.方円   5.偃月   6.锋矢");
        System.out.print("请选择阵列0-6：");
        Scanner sc=new Scanner(System.in);
        while(num--!=0)
        {
            index=sc.nextInt();
            map.selectFormation(index);
            map.addOrganism(snake);
            map.addOrganism(grandfather);
            map.printMap();
            System.out.println("0.鹤翼   1.雁行   2.衡轭   3.鱼鳞    4.方円   5.偃月   6.锋矢");
            System.out.print("请选择阵列0-6：");
        }


    }

    public static  void main(String[] args)
    {
        GameStart();

    }
}
