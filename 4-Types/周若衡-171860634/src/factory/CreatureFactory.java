package factory;

import creature.*;

import java.lang.reflect.Constructor;

public class CreatureFactory implements Generator<Creature> {

    public enum CreatureClassName{
        CalabashBrother,
        Elder,
        Scorpion,
        Snake,
        Goblin
    }
    private static Class[] types={
            creature.CalabashBrother.class,
            creature.Elder.class,
            creature.Scorpion.class,
            creature.Snake.class,
            creature.Goblin.class};

    @Override
    public Creature generate(String className,int rank){
        Creature creature=null;
        int typeIndex=-1;
        try{
            typeIndex=CreatureClassName.valueOf(className).ordinal();
        }catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
        try{
            Constructor<Creature> creatureConstructor;
            if(rank==-1){
                creatureConstructor=types[typeIndex].getDeclaredConstructor();
                creature=creatureConstructor.newInstance();
            }else{
                creatureConstructor=types[typeIndex].getDeclaredConstructor(int.class);
                creature=creatureConstructor.newInstance(rank);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return creature;
    }
}
