import Creature.*;
import Formation.Circle;
import Map.Map;

public class Generic {

    static public Map map = new Map();

    static private Hulu hulu[]=new Hulu[7];
    static private Grandpa grandpa;
    static private Snake snake;
    static private Scorpion scorpion;
    static private Monster monster[]=new Monster[7];

    public static void main(String[] args) {

        init();
        initLay();
        showMap();

    }



    static public void init()
    {
        hulu[0]=new Hulu(1,1,"大娃","红");
        hulu[1]=new Hulu(2,2,"二娃","橙");
        hulu[2]=new Hulu(3,3,"三娃","黄");
        hulu[3]=new Hulu(4,4,"四娃","绿");
        hulu[4]=new Hulu(5,5,"五娃","青");
        hulu[5]=new Hulu(6,6,"六娃","蓝");
        hulu[6]=new Hulu(7,7,"七娃","紫");     //id 1-7,葫芦娃
        grandpa = new Grandpa(8);       //id = 8，老爷爷
        for(int jk=0;jk<7;jk++)
            monster[jk] = new Monster(jk+9);                   //id 9-15,小怪
        scorpion = new Scorpion(16);     //id = 16，蝎子精
        snake = new Snake(17);       //id = 17，蛇精

    }




    static public void initLay()
    {
        Circle c1 = new Circle(hulu[0]);
        Circle c2 = new Circle(monster[0]);
        for(int jk=0;jk<7;jk++)
        {
            map.setMap(c1.returnX(jk),c1.returnY(jk),hulu[jk].getId());
        }
        for(int jk=0;jk<7;jk++)
        {
            map.setMap(c2.returnX(jk),c2.returnY(jk),monster[jk].getId());
        }
        map.setMap(0,7,grandpa.getId());
        map.setMap(14,7,snake.getId());
        map.setMap(14,6,scorpion.getId());

    }


    static public void showMap()
    {
        for (int jk = 0; jk < 15; jk++) {
            for (int jt = 0; jt < 15; jt++)
            {
                if(map.getContent(jk,jt)==-1)
                    System.out.print("空格   ");
                else if(map.getContent(jk,jt)>=1&&map.getContent(jk,jt)<=7)
                {
                    System.out.print(hulu[map.getContent(jk,jt)-1].getName());
                    System.out.print("   ");
                }
                else if(map.getContent(jk,jt)==8)
                {
                    System.out.print(grandpa.getName());
                    System.out.print("  ");
                }
                else if(map.getContent(jk,jt)>=9&&map.getContent(jk,jt)<=15)
                {
                    System.out.print(monster[map.getContent(jk,jt)-9].getName());
                    System.out.print("  ");
                }
                else if(map.getContent(jk,jt)==16)
                {
                    System.out.print(scorpion.getName());
                    System.out.print(" ");
                }
                else if(map.getContent(jk,jt)==17)
                {
                    System.out.print(snake.getName());
                    System.out.print("   ");
                }

            }
            System.out.println("");
        }
    }


}




