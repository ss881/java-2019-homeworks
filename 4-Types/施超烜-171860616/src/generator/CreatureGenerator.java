package generator;
import creature.*;
import java.lang.Class;
public class CreatureGenerator implements Generator<Creature>{
    private Class[] creatureType={CalabashBrother.class,Grandfather.class,Wannabe.class,ScorpionEssence.class,SnakeEssence.class,Space.class};
    public CreatureGenerator(){
        
    }
    public Creature generate(String classname){
        try{
            for(int i=0;i<creatureType.length;i++){
                if(creatureType[i].getName().equals("creature."+classname)){
                    return (Creature)creatureType[i].getConstructor().newInstance();
                }
            }
            return null;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}