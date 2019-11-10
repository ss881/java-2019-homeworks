public class Lives {
    position myPosition;

    boolean moving;
    position dest;
    position orin;
    void walk(position x,Tile y[][])
    {
        dest=new position(x.x,x.y);
        orin=new position(myPosition.x,myPosition.y);
        int d1=0;int d2=0;
        int d1len=Math.abs(x.x-myPosition.x);
        int d2len=Math.abs(x.y-myPosition.y);
        if(x.x>myPosition.x)
        {
            d1=1;
        }
        else if(x.x<myPosition.x)
        {
            d1=-1;
        }
        if(x.y>myPosition.y)
        {
            d2=1;
        }
        else if(x.y<myPosition.y)
        {
            d2=-1;
        }
        for(int i=0;i<d1len;i++)
        {
            position temp=new position(myPosition.x+d1,myPosition.y);
            oneStep(temp,y);
        }
        for(int i=0;i<d2len;i++)
        {
            position temp=new position(myPosition.x,myPosition.y+d2);
            oneStep(temp,y);
        }
        dest=null;
    }
    void oneStep(position x,Tile y[][])
    {
        if(moving==false)
        {
            if(y[x.x][x.y].isOccupied==false)
            {
                y[x.x][x.y].isOccupied=true;
                y[x.x][x.y].which=this;
                y[myPosition.x][myPosition.y].isOccupied=false;
                y[myPosition.x][myPosition.y].which=null;
                myPosition.x=x.x;
                myPosition.y=x.y;
            }
            else if((x.x==dest.x)&&(x.y==dest.y))
            {
                //y[x.x][x.y].isOccupied=true;
                Lives temp=y[x.x][x.y].which;
                y[x.x][x.y].which=this;
                //y[myPosition.x][myPosition.y].isOccupied=false;
                y[myPosition.x][myPosition.y].which=temp;
                y[myPosition.x][myPosition.y].which.myPosition.x=myPosition.x;
                y[myPosition.x][myPosition.y].which.myPosition.y=myPosition.y;
                myPosition.x=x.x;
                myPosition.y=x.y;

            }
            else
            {
                moving=true;
                y[myPosition.x][myPosition.y].isOccupied=false;
                y[myPosition.x][myPosition.y].which=null;
                myPosition.x=x.x;
                myPosition.y=x.y;

            }
        }
        else
        {
            if(y[x.x][x.y].isOccupied==false)
            {
                y[x.x][x.y].isOccupied=true;
                y[x.x][x.y].which=this;
                myPosition.x=x.x;
                myPosition.y=x.y;
                moving=false;
            }
            else if((x.x==dest.x)&&(x.y==dest.y))
            {
                //y[x.x][x.y].isOccupied=true;
                Lives temp=y[x.x][x.y].which;
                y[x.x][x.y].which=this;
                //y[myPosition.x][myPosition.y].isOccupied=false;
                myPosition.x=x.x;
                myPosition.y=x.y;
                moving=false;
                temp.moving=true;
                temp.walk(orin,y);

            }
            else
            {
                myPosition.x=x.x;
                myPosition.y=x.y;

            }
        }

    }
}
