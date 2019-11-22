package creature;

import factory.EvialFactory;
import factory.SnakeFactory;
import formation.Crane;
import formation.Formation;
import formation.Moon;

import java.util.ArrayList;

public class Scorpion extends Evial {
    static final int N=12;//size of map
    static final int NUM_EVILS=20;//max number of evils

    //都是以Creature形式返回
    public ArrayList<Creature> initialize(){//add Snake to evials?
        ArrayList<Creature> evials = new ArrayList<Creature>();
        SnakeFactory snakeFactory = new SnakeFactory();
        EvialFactory evialFactory = new EvialFactory();
        evials.add(snakeFactory.generate());//先放蛇精
        for(int i=0;i<NUM_EVILS;++i){
            evials.add(evialFactory.generate());
        }

        //TODO set initialForm
        Formation formation = new Crane();
        formation.changeForm(this,evials);
        return evials;
    }


//    public static void main(String[]args){
//        Scorpion scorpion = new Scorpion();
//        ArrayList<Creature> creatures = scorpion.initialize();
//        for(Creature c : creatures){
//            System.out.println(c);
//        }
//        Moon moon = new Moon();
//        moon.changeForm(scorpion,creatures);
//        System.out.println();
//        for(Creature c : creatures){
//            System.out.println(c);
//        }
//    }
}
