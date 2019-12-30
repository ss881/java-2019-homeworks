package Test;

import BattleField.Formation;
import BattleField.Space;
import Creatures.Grandpa;
import Creatures.Huluwa;
import Creatures.Monsters;
import Creatures.Scorpion;
import Creatures.Snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Testrun
{
    public static void main(String[] arg) 
    {
        int x=15;
        int y=15;
        Space testspace=new Space(x,y);
        ArrayList<Huluwa> huluwas = new ArrayList<>();
        huluwas.add(new Huluwa(5,-1,-1));
        huluwas.add(new Huluwa(4,-1,-1));
        huluwas.add(new Huluwa(6,-1,-1));
        huluwas.add(new Huluwa(0,-1,-1));
        huluwas.add(new Huluwa(3,-1,-1));
        huluwas.add(new Huluwa(1,-1,-1));
        huluwas.add(new Huluwa(2,-1,-1));
        
        Collections.sort(huluwas);         //葫芦娃排序
        
        
        
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
            testspace.showthemap(x, y);

            System.out.println("------------------------------------------");
        }
        
        
        
        
        

    }
}