package organism;
import java.util.Random;

//interface Generator<T> {T next(String classname);}

public class OrganismGenerator implements Generator<Organism>
{
    private Class[] types={Land.class,GrandFather.class,Snake.class,Scorpion.class,Soldier.class};
    private static Random rand = new Random(47);

    public OrganismGenerator(){};
    private int size=0;
    public OrganismGenerator(int sz){size=sz;}

    public Organism next(String classname)
    {
        try
        {
            for(int i=0;i<types.length;i++)
            {
                if(types[i].getName().equals("organism."+classname)) return (Organism)types[i].getConstructor().newInstance();
            }

            return new Organism();

        }catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    };


    /*public  static void main(String[] args)
    {
        OrganismGenerator gen=new OrganismGenerator();
        for(int i=0;i<1;i++) System.out.println(gen.next("Land").getName());
    }*/
}


