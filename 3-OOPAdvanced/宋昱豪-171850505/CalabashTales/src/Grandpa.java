public class Grandpa extends Lives {
    CalabashBrothers Cb[];
    void plantCalabash(CalabashBrothers x[],int seeds,Tile ground[][])
    {
        int a=-1;int b=-1;
        for(int i=0;i<seeds;i++)
        {
            color tempColor;
            switch(i+1)
            {
                case 1:tempColor=color.RED; break;
                case 2:tempColor=color.ORANGE;break;
                case 3:tempColor=color.YELLOW;break;
                case 4:tempColor=color.GREEN;break;
                case 5:tempColor=color.CYAN;break;
                case 6:tempColor=color.BLUE;break;
                case 7:tempColor=color.PURPLE;break;
                default:tempColor=color.BLACK;System.out.println("怎么种出了妖怪！");break;
            }
            switch (i)
            {
                case 0:a=-1;b=-1;break;
                case 1:a=0;b=-1;break;
                case 2:a=1;b=-1;break;
                case 3:a=1;b=0;break;
                case 4:a=1;b=1;break;
                case 5:a=0;b=1;break;
                case 6:a=-1;b=1;break;
            }
            position tempPosition=new position(myPosition.x+a,myPosition.y+b);//need to fix the exactly position
            x[i]=new CalabashBrothers(i+1,tempColor,tempPosition);
            if(ground[tempPosition.x][tempPosition.y].isOccupied==false)
            {
                ground[tempPosition.x][tempPosition.y].isOccupied=true;
                ground[tempPosition.x][tempPosition.y].which=x[i];
            }
           else
            {
                System.out.println("你踩到我了！");
            }

        }
        Cb=x;
    }
    void commandCalabash(Tile ground[][])
    {

        for(int i=0; i< 7; i++)
        {
            position x=new position(i+3,myPosition.y+2);//need to fix the exactly position
            Cb[i].walk(x,ground);
        }
        for(int i=0;i<7;i++)
        {
            Cb[i].getMyNumber();
        }
    }
    Grandpa(int x,int y,Tile z[][])
    {
        moving=false;
        myPosition=new position(x,y);
        if(z[x][y].isOccupied==false) {
            z[x][y].isOccupied = true;
            z[x][y].which = this;
        }
    }
}
