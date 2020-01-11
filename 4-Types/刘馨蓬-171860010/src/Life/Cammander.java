package Life;

import DotMatrix.*;
import Position.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Cammander <T extends Life>extends Life{
    private T cammander;
    private Class clz;
    public Cammander(){}
    public Cammander(T classType) throws ClassNotFoundException {
        cammander=classType;
        clz=Class.forName((cammander.getClass().getName()));
    }
    public void chooseDotMatrix(DotMatrixName matrixname,TwoDimensionsSpace map) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method=clz.getMethod("chooseDotMatrix",DotMatrixName.class,TwoDimensionsSpace.class);
        method.invoke(cammander,matrixname,map);
    }
    public void changeLifePosition(TwoDimensionsSpace map) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method=clz.getMethod("changeLifePosition",TwoDimensionsSpace.class);
        method.invoke(cammander,map);
    }
    public void changeSelfPosition(TwoDimensionsSpace map) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method=clz.getMethod("changeSelfPosition",TwoDimensionsSpace.class);
        method.invoke(cammander,map);
    }
    public void clear(TwoDimensionsSpace map) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method=clz.getMethod("clear",TwoDimensionsSpace.class);
        method.invoke(cammander, map);
    }
}
