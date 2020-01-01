package Model.World;

import Model.World.Position;
import Model.World.Tile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Formation<T> {
    private Position myPosition;
    private T array[];
    private int num;
    private Tile ground[][];
    public Formation(Position x,int y,Tile z[][],T o[])
    {
        myPosition=x;
        num=y;
        array=o;
        ground =z;
    }
    public void T1()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x-1;b=myPosition.y+1;break;
                case 1:a=myPosition.x-2;b=myPosition.y+2;break;
                case 2:a=myPosition.x-3;b=myPosition.y+3;break;
                case 3:a=myPosition.x+1;b=myPosition.y+1;break;
                case 4:a=myPosition.x+2;b=myPosition.y+2;break;
                case 5:a=myPosition.x+3;b=myPosition.y+3;break;
                case 6:a=myPosition.x+4;b=myPosition.y+4;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                Throwable t = e.getCause();
                t.printStackTrace();
            }
        }
    }
    public void T2()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x+1;b=myPosition.y+1;break;
                case 1:a=myPosition.x+2;b=myPosition.y+2;break;
                case 2:a=myPosition.x+3;b=myPosition.y+3;break;
                case 3:a=myPosition.x+4;b=myPosition.y+4;break;
                case 4:a=myPosition.x+5;b=myPosition.y+5;break;
                case 5:a=myPosition.x+6;b=myPosition.y+6;break;
                case 6:a=myPosition.x+7;b=myPosition.y+7;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                System.out.println("此处接收被调用方法内部未被捕获的异常");
                e.printStackTrace();
            }
        }
    }
    public void T3()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x-1;b=myPosition.y;break;
                case 1:a=myPosition.x+1;b=myPosition.y;break;
                case 2:a=myPosition.x+1;b=myPosition.y+1;break;
                case 3:a=myPosition.x;b=myPosition.y+1;break;
                case 4:a=myPosition.x-1;b=myPosition.y+1;break;
                case 5:a=myPosition.x-2;b=myPosition.y+2;break;
                case 6:a=myPosition.x+2;b=myPosition.y+2;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                System.out.println("此处接收被调用方法内部未被捕获的异常");
                e.printStackTrace();
            }
        }
    }
    public void T4()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x;b=myPosition.y+1;break;
                case 1:a=myPosition.x;b=myPosition.y+2;break;
                case 2:a=myPosition.x;b=myPosition.y+3;break;
                case 3:a=myPosition.x;b=myPosition.y+4;break;
                case 4:a=myPosition.x;b=myPosition.y+5;break;
                case 5:a=myPosition.x;b=myPosition.y+6;break;
                case 6:a=myPosition.x;b=myPosition.y+7;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                System.out.println("此处接收被调用方法内部未被捕获的异常");
                e.printStackTrace();
            }
        }
    }
    public void T5()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x-1;b=myPosition.y+1;break;
                case 1:a=myPosition.x-2;b=myPosition.y+2;break;
                case 2:a=myPosition.x+1;b=myPosition.y+1;break;
                case 3:a=myPosition.x+2;b=myPosition.y+2;break;
                case 4:a=myPosition.x;b=myPosition.y+2;break;
                case 5:a=myPosition.x;b=myPosition.y+3;break;
                case 6:a=myPosition.x;b=myPosition.y+4;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                System.out.println("此处接收被调用方法内部未被捕获的异常");
                e.printStackTrace();
            }
        }
    }
    public void T6()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x-1;b=myPosition.y+1;break;
                case 1:a=myPosition.x-2;b=myPosition.y+2;break;
                case 2:a=myPosition.x+1;b=myPosition.y+1;break;
                case 3:a=myPosition.x+2;b=myPosition.y+2;break;
                case 4:a=myPosition.x-1;b=myPosition.y+3;break;
                case 5:a=myPosition.x;b=myPosition.y+4;break;
                case 6:a=myPosition.x+1;b=myPosition.y+3;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                System.out.println("此处接收被调用方法内部未被捕获的异常");
                e.printStackTrace();
            }
        }
    }
    public void T7()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x-1;b=myPosition.y;break;
                case 1:a=myPosition.x+1;b=myPosition.y;break;
                case 2:a=myPosition.x+1;b=myPosition.y+1;break;
                case 3:a=myPosition.x;b=myPosition.y+1;break;
                case 4:a=myPosition.x-1;b=myPosition.y+1;break;
                case 5:a=myPosition.x-2;b=myPosition.y+2;break;
                case 6:a=myPosition.x+2;b=myPosition.y+2;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                System.out.println("此处接收被调用方法内部未被捕获的异常");
                e.printStackTrace();
            }
        }
    }
    public void T8()
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            Position temp;
            switch (i)
            {
                case 0:a=myPosition.x+1;b=myPosition.y+1;break;
                case 1:a=myPosition.x+2;b=myPosition.y+2;break;
                case 2:a=myPosition.x-1;b=myPosition.y+1;break;
                case 3:a=myPosition.x-2;b=myPosition.y+2;break;
                case 4:a=myPosition.x;b=myPosition.y+1;break;
                case 5:a=myPosition.x;b=myPosition.y+2;break;
                case 6:a=myPosition.x;b=myPosition.y+3;break;
            }
            temp=new Position(a,b);
            try{
                Class<? extends Object> tClass =array[i].getClass();
                Method walk=tClass.getMethod("walk",Position.class);
                walk.invoke(array[i],temp);
                Method SetPic=tClass.getMethod("SetPic");
                SetPic.invoke(array[i]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("越界i="+i);
            } catch (InvocationTargetException e) {
                System.out.println("此处接收被调用方法内部未被捕获的异常");
                e.printStackTrace();
            }
        }
    }

}
