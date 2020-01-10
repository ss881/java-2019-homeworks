package administer;

import formation.Formation;
import formation.FormationFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class FormationAdminister {
    private  int goodFormationIndex =0;
    private  int badFormationIndex =0;

    private static ArrayList<Point> goodStartPoint=new ArrayList<>();
    private static ArrayList<Point> badStartPoint=new ArrayList<>();
    public static ArrayList<Point> getGoodStartPoint(){return goodStartPoint;}
    public static ArrayList<Point> getBadStartPoint(){return badStartPoint;}

    public  int getGoodFormationIndex(){return goodFormationIndex;}
    public  int getBadFormationIndex(){return badFormationIndex;}
    static {
        goodStartPoint.add(new Point(2,0));
        goodStartPoint.add(new Point(4,0));
        goodStartPoint.add(new Point(2,1));
        goodStartPoint.add(new Point(0,0));
        goodStartPoint.add(new Point(2,3));
        goodStartPoint.add(new Point(2,2));
        goodStartPoint.add(new Point(2,0));
        goodStartPoint.add(new Point(2,3));

        badStartPoint.add(new Point(2,8));
        badStartPoint.add(new Point(4,5));
        badStartPoint.add(new Point(2,7));
        badStartPoint.add(new Point(4,1));
        badStartPoint.add(new Point(2,5));
        badStartPoint.add(new Point(2,6));
        badStartPoint.add(new Point(2,8));
        badStartPoint.add(new Point(2,5));
    }

    public  void setGoodFormationIndex(int index) {
        index%=8;
        Formation f1= FormationFactory.create(index, (int)goodStartPoint.get(index).getX(),(int)goodStartPoint.get(index).getY() ,1);;
        Formation f2=FormationFactory.create(badFormationIndex, (int)badStartPoint.get(badFormationIndex).getX(),(int)badStartPoint.get(badFormationIndex).getY() ,-1);;
        if(Objects.requireNonNull(f1).checkWhetherCrash(f2)==false)
            goodFormationIndex =index%8;
    }
    public  void setBadFormationIndex(int index){
        index%=8;
        Formation f1=FormationFactory.create(goodFormationIndex, (int)goodStartPoint.get(goodFormationIndex).getX(),(int)goodStartPoint.get(goodFormationIndex).getY() ,1);;
        Formation f2=FormationFactory.create(index, (int)badStartPoint.get(index).getX(),(int)badStartPoint.get(index).getY() ,-1);;
        if(Objects.requireNonNull(f1).checkWhetherCrash(f2)==false)
            badFormationIndex =index%8;
    }

    public  int getArg1GoodFormation(int index){
        return (int)goodStartPoint.get(index).getX();
    }
    public  int getArg2GoodFormation(int index){
        return (int)goodStartPoint.get(index).getY();
    }
    public  int getArg1BadFormation(int index){
        return (int)badStartPoint.get(index).getX();
    }
    public  int getArg2BadFormation(int index){
        return (int)badStartPoint.get(index).getY();
    }
}

