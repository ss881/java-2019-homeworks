package creature;

import java.lang.reflect.Constructor;

import util.ImageType;

public class CreatureFactory implements Generator<Creature> {


    private static Class[] classType ={
            creature.CalabashBrother.class,
            creature.Elder.class,
            creature.Scorpion.class,
            creature.Snake.class,
            creature.Goblin.class
    };

    @Override
    public Creature generate(String className,int rank){
        Creature creature=null;
        Constructor<Creature> creatureConstructor;
        try {
            creatureConstructor = classType[rank].getDeclaredConstructor(int.class, int.class, ImageType.class, int.class, int.class, boolean.class, int.class, String.class, int.class, int.class);
            creature=creatureConstructor.newInstance();

        }catch (Exception e){
            e.printStackTrace();
        }
        return creature;
    }
}

