import Army.Army;
import Creatures.*;

import Space.SpaceFlat;
import Space.Coordinate;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        System.out.println("Number 1~7 represents 1 calabash boy independently.");
        System.out.println("Character G represents the old grandpa.");
        System.out.println("Character S represents the snake demon.");
        System.out.println("Character ¥ represents the scorpion demon.");
        System.out.println("Character x represents the small demons.");
        System.out.println("Battle begins:");
        System.out.println("--------------------------------------------------------------------------------");
        SpaceFlat battlefield=new SpaceFlat(40);
        //initalize battlefield

        Grandpa grandpa=new Grandpa();
        Coordinate grandpa_pos=new Coordinate(14,18);
        Coordinate calabash_army_pos=new Coordinate(19,18);
        CalabashBoy b1=new CalabashBoy("大娃","红色",1,1);
        CalabashBoy b2=new CalabashBoy("二娃","橙色",2,2);
        CalabashBoy b3=new CalabashBoy("三娃","黄色",3,3);
        CalabashBoy b4=new CalabashBoy("四娃","绿色",4,4);
        CalabashBoy b5=new CalabashBoy("五娃","青色",5,5);
        CalabashBoy b6=new CalabashBoy("六娃","蓝色",6,6);
        CalabashBoy b7=new CalabashBoy("七娃","紫色",7,7);
        CalabashBoy[]temp_array=new CalabashBoy[7];
        temp_array[0]=b1;temp_array[1]=b2;temp_array[2]=b3;temp_array[3]=b4;
        temp_array[4]=b5;temp_array[5]=b6;temp_array[6]=b7;
        Sort sorter=new Sort();
        sorter.BubbleSort(temp_array);
        Vector<Creature> CalabashBoys=new Vector<Creature>();
        for(int i=0;i<7;i++)
        {
            CalabashBoys.add(temp_array[i]);
        }
        Army CalabashBoyArmy=new Army(grandpa,grandpa_pos,CalabashBoys,calabash_army_pos,1);
        CalabashBoyArmy.arrangeFormation("SNAKE");
        /*System.out.println(CalabashBoyArmy.cheerleader.getName());
        System.out.println(CalabashBoyArmy.cheer_position.getX()+"，"+CalabashBoyArmy.cheer_position.getY());
        for(int i=0;i<7;i++)
        {
            System.out.println(CalabashBoyArmy.members.get(i).getName());
            System.out.println(CalabashBoyArmy.formation.positions.get(i).getX()+","+CalabashBoyArmy.formation.positions.get(i).getY());
        }*/
        //initialize one army of calabash boys


        SnakeDemon snake=new SnakeDemon();
        Coordinate snake_pos=new Coordinate(14,21);
        Coordinate demon_army_pos=new Coordinate(19,21);
        ScorpionDemon scorpion=new ScorpionDemon();
        SmallDemon[] demons_array=new SmallDemon[18];
        for(int i=0;i<18;i++)
        {
            demons_array[i]=new SmallDemon();
        }
        Vector<Creature> DemonTroops=new Vector<Creature>();
        DemonTroops.add(scorpion);
        for(int i=0;i<18;i++)
        {
            DemonTroops.add(demons_array[i]);
        }
        //initial another army of demons


        final String[] fomations={"STORK","GOOSE","BALANCE","SCALE","SQUARE","MOON","ARROW"};
        for(int i=0;i<7;i++)
        {
            //if(i!=4)continue;
            System.out.println("Battle"+(i+1)+":");
            System.out.println("--------------------------------------------------------------------------------");
            Army DemonSideArmy=new Army(snake,snake_pos,DemonTroops,demon_army_pos,-1);
            DemonSideArmy.arrangeFormation(fomations[i]);
            /*for(int j=0;j<15;j++)
            {
                System.out.println(DemonSideArmy.members.get(j).getName());
                System.out.println(DemonSideArmy.formation.positions.get(j).getX()+","+DemonSideArmy.formation.positions.get(j).getY());
            }*/
            battlefield.enterArmy(DemonSideArmy);
            battlefield.enterArmy(CalabashBoyArmy);
            battlefield.printMap();
            System.out.println("--------------------------------------------------------------------------------");
            battlefield.clear();
        }
        //battle begins
    }
}
