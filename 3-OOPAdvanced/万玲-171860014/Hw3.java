

class Being
{
    protected int posX;
    protected int posY;
    Being()
    {
        posX=-1;
        posY=-1;
    }
    Being(int x,int y)
    {
        posX=x;
        posY=y;
    }
    void display()
    {
        System.out.print(" ");
    }
    public void changePos(int x,int y)
    {
        posX=x;
        posY=y;
    }
}
class Huluwa extends Being
{
    private int colornum;
    private int num;
    //String color;
    //Huluwa(){Being();colornum=0;}
    Huluwa(int c,int n)
    {
        super();
        colornum=c;
        num=n;
    }
    void setPos(int x,int y)
    {
        posX=x;
        posY=y;
    }
    Boolean ChangeOrNot(Huluwa h)
    {
        if(colornum>h.colornum)
        {
            return true;

        }
        return false;
    }
    @Override
    void display()
    {
        switch(colornum)
        {
            case 1:System.out.print("a");break;
            case 2:System.out.print("b");break;
            case 3:System.out.print("c");break;
            case 4:System.out.print("d");break;
            case 5:System.out.print("e");break;
            case 6:System.out.print("f");break;
            case 7:System.out.print("g");break;
            default:break;
        }
        //System.out.print(colornum);
    }

}
class SheJing extends Being
{
    SheJing(int x,int y)
    {
        super(x,y);
    }
    @Override
    void display()
    {
        System.out.print("*");
    }

}
class LouLuo extends Being
{
    LouLuo(int x,int y)
    {
        super(x,y);
    }
    @Override
    void display()
    {
        System.out.print("o");
    }
}
class LaoYeYe extends Being
{
    LaoYeYe(int x,int y)
    {
        super(x,y);
    }
    @Override
    void display()
    {
        System.out.print("#");
    }
}
class XieZiJing extends Being
{
    XieZiJing(int x,int y)
    {
        super(x,y);
    }
    @Override
    void display()
    {
        System.out.print("x");
    }
}

class Mmap
{
    private Being m[][]=new Being[10][10];
    Mmap(Huluwa h[])
    {
        //initialize
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                m[i][j]=new Being(i,j);
            }
        }

        for(int i=0;i<h.length-1;i++)
        {
            for(int j=0;j<h.length-i-1;j++)
            {
                if(h[j].ChangeOrNot(h[j+1]))
                {
                    Huluwa tmp=h[j];
                    h[j]=h[j+1];
                    h[j+1]=tmp;
                }
            }
        }
        
        for(int i=0;i<7;i++)
        {
            h[i].setPos(i, 0);
            m[i][0]=h[i];
        }
        m[0][8]=new XieZiJing(0,8);
        for(int i=1;i<7;i++)
        {
            if(i%2!=0)
            {
                m[i][9]=new LouLuo(i, 9);
            }
            else{
                m[i][8]=new LouLuo(i, 8);
            }
        }
        m[7][0]=new LaoYeYe(7,0);
        m[7][9]=new SheJing(7, 9);
        
    }
    void changeQue(Huluwa[] h)
    {
        //initialize
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                m[i][j]=new Being(i,j);
            }
        }
        //place huluwa
        for(int i=0;i<7;i++)
        {
            h[i].setPos(i, 0);
            m[i][0]=h[i];
        }
        //place laoyeye
        m[7][0]=new LaoYeYe(7,0);
        //place xiaolouluo
        m[0][7]=new LouLuo(0, 7);
        m[1][6]=new LouLuo(1, 6);
        m[1][8]=new LouLuo(1, 8);
        m[2][5]=new LouLuo(2,5);
        m[2][9]=new LouLuo(2, 9);
        m[3][6]=new LouLuo(3, 6);
        m[3][8]=new LouLuo(3, 8);
        m[4][7]=new LouLuo(4, 7);
        //place xiezijing
        m[5][7]=new XieZiJing(5, 7);
        //place shejing
        m[7][7]=new SheJing(7, 7);

    }
    void Display()
    {
        //if(x==0) return;
        for(Being[] b:m)
        {
            for(Being bb:b)
            {
                bb.display();
            }
            System.out.print("\n");
        }
    }
}

public class Hw3
{
    public static void main(String[] args)
    {
        Huluwa[] h={new Huluwa(7, 0),new Huluwa(4, 1),new Huluwa(6, 2),new Huluwa(1, 3),new Huluwa(3, 4),new Huluwa(2, 5),new Huluwa(5, 6)};
        for(int i=0;i<7;i++)
        h[i].display();
        System.out.print("\n");
        
        Mmap mmap=new Mmap(h);
        mmap.Display();
        mmap.changeQue(h);
        mmap.Display();
    }
    
    
}