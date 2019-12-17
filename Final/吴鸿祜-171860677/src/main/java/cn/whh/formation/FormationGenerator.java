package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class FormationGenerator implements Generator<Formation> {

    private Class[] types={
            ChangSheFormation.class,
            FangYuanFormation.class,
            FengShiFormation.class,
            YuLinFormation.class,
            HengEFormation.class,
            HeYiFormation.class,
            YanXingFormation.class,
            YanYueFormation.class};

    public FormationGenerator(){};

    public Formation produceFormation(String formationName, ArrayList<? extends Creature> camp, int symobol)
    {
        Formation formation=null;
        try{
            if(formationName.equals("长蛇阵")) formation= (Formation)types[0].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
            else if(formationName.equals("方円阵")) formation= (Formation)types[1].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
            else if(formationName.equals("锋矢阵")) formation= (Formation)types[2].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
            else if(formationName.equals("鱼鳞阵")) formation= (Formation)types[3].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
            else if(formationName.equals("衡轭阵")) formation= (Formation)types[4].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
            else if(formationName.equals("鹤翼阵")) formation=(Formation)types[5].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
            else if(formationName.equals("雁行阵")) formation= (Formation)types[6].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
            else if(formationName.equals("偃月阵")) formation= (Formation)types[7].getConstructor(ArrayList.class,int.class).newInstance(camp,symobol);
        }catch (Exception e)
        {
            System.out.println(e);
        }finally {
            return formation;
        }

    }
}
