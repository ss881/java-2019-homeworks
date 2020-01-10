package generator;
import formation.*;
public class FormationGenerator implements Generator<Formation>{
    private Class[] formationType={CraneWing.class,FangYan.class,FishScale.class,SharpArrow.class,WildGeese.class,YanYue.class,Yokes.class};
    public FormationGenerator(){

    }
    public Formation generate(String classname){
        try{
            for(int i=0;i<formationType.length;i++){
                String temp=formationType[i].getName();
                if(formationType[i].getName().equals("formation."+classname)){
                    return (Formation)formationType[i].getConstructor().newInstance();
                }
            }
            return null;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}