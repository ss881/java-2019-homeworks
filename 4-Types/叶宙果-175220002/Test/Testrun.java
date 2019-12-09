package Test;

import BattleField.Formation;
import BattleField.Space;
import Creatures.Creature;
import Creatures.Grandpa;
import Creatures.Huluwa;
import Creatures.Monsters;
import Creatures.Scorpion;
import Creatures.Snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Testrun {
    public static void main(String[] arg) throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        int x = 15;
        int y = 15;
        Space testspace = new Space(x, y);
        ArrayList<Huluwa> huluwas = new ArrayList<>();
        huluwas.add(new Huluwa(5, -1, -1));
        huluwas.add(new Huluwa(4, -1, -1));
        huluwas.add(new Huluwa(6, -1, -1));
        huluwas.add(new Huluwa(0, -1, -1));
        huluwas.add(new Huluwa(3, -1, -1));
        huluwas.add(new Huluwa(1, -1, -1));
        huluwas.add(new Huluwa(2, -1, -1));

        Collections.sort(huluwas); // 葫芦娃排序

        
        
        



        Scorpion scorpion=new Scorpion(-1,-1);
        Monsters monsters[]=new Monsters[20];
        for(int i=0;i<20;i++)
        {
            monsters[i]=new Monsters(-1,-1);
        }

        Grandpa grandpa = new Grandpa(-1,-1);
        Snake snake = new Snake(-1,-1);

        Formation formation = new Formation();

        


        for(int i=0;i<8;i++)
        {
            System.out.println("------------------------------------------");
            
            testspace.initmap(x, y);
            formation.Formationforgood(testspace, huluwas, 3);
            formation.Formationforbad(testspace, scorpion, monsters, i);
            grandpa.setto(0, 0, testspace);
            snake.setto(0, 14, testspace);

            Class<?> bawa;
            try 
            {
                bawa = Class.forName("Creatures.Monsters");
                //Constructor<?> cons = bawa.getConstructor(int.class, int.class, String.class);//获得构造方法
                //Object obj = cons.newInstance(14,14,"八娃");
                Monsters obj = (Monsters) bawa.getConstructor(int.class, int.class).newInstance(10, 10);
                obj.setto(14, 14, testspace);
            } 
            catch (ClassNotFoundException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Creature now = testspace.getcreature(0,0);
            System.out.println(now.getClass().getName());



            testspace.showthemap(x, y);

            System.out.println("------------------------------------------");
        }


    }
}