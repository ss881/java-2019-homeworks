package formation;
import organism.Generator;


public class FormationGenerator implements Generator<Formation>
{
    private Class[] types={CraneWingFormation.class,FangWeiFormation.class,FishScaleFormation.class,FrontalFormation.class,HaoYueFormation.class,WildGooseFormation.class,YoKeFormation.class};

    public FormationGenerator(){};

    public Formation next(String classname)
    {
        try
        {
            for(int i=0;i<types.length;i++)
            {
                if(types[i].getName().equals("formation."+classname)) return (Formation)types[i].getConstructor().newInstance();
            }

            return null;

        }catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
