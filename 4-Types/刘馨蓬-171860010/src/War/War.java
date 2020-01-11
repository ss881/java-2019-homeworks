package War;

import DotMatrix.*;
import Life.*;
import Position.*;

import java.lang.reflect.InvocationTargetException;

public class War {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        TwoDimensionsSpace map=new TwoDimensionsSpace();
        Cammander<Yeye> yeye=new Cammander<Yeye>(new Yeye());
        Cammander<Xiezi> xiezi=new Cammander<Xiezi>(new Xiezi());
        yeye.chooseDotMatrix(DotMatrixName.HULUWA,map);
        yeye.changeLifePosition(map);
        xiezi.chooseDotMatrix(DotMatrixName.HEYI,map);
        xiezi.changeLifePosition(map);
        yeye.changeSelfPosition(map);
        xiezi.changeSelfPosition(map);
        map.printMap();
        System.out.println("======================================================");
        xiezi.clear(map);
        xiezi.chooseDotMatrix(DotMatrixName.YANYUE,map);
        xiezi.changeLifePosition(map);
        xiezi.changeSelfPosition(map);
        map.printMap();
    }
}

