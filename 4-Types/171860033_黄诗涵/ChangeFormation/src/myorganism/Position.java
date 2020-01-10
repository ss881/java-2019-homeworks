package myorganism;
import java.lang.*;
import java.lang.reflect.*;

public class Position<T extends Organism> {
    private int x;
    private int y;
    private T creature;
    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public void set(T t)
    {
        creature=t;
        if(t==null) {
            return;
        }
    try {
        Class c=creature.getClass();
        Method m=c.getMethod("setpos", Position.class);
        m.invoke(creature,this);
    }
    catch (NoSuchMethodException e) {}
    catch (IllegalAccessException e){}
    catch (InvocationTargetException e){}
    }
    public T get()
    {
        return creature;
    }
    public String getName()
    {
        return this.creature.getClass().getName();
    }
    public String coordinateStr()
    {
        return '('+Integer.toString(x)+','+Integer.toString(y)+')';
    }

}
