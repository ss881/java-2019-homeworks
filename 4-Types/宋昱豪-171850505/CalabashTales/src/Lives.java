public class Lives {
    protected Position myPosition;
    protected boolean moving;
    protected Position dest;
    protected Position orin;
    private void oneStep(Position x,Tile y[][])
    {
        if(moving==false)
        {
            if(y[x.x][x.y].GetIsOccupied()==false)
            {
                y[x.x][x.y].SetALL(true,this);
                y[myPosition.x][myPosition.y].SetALL(false,null);
                myPosition.x=x.x;
                myPosition.y=x.y;
            }
            else if((x.x==dest.x)&&(x.y==dest.y))
            {
                //y[x.x][x.y].isOccupied=true;
                Lives temp=y[x.x][x.y].GetWho();
                y[x.x][x.y].SetWho(this);
                //y[myPosition.x][myPosition.y].isOccupied=false;
                temp.myPosition.x=myPosition.x;
                temp.myPosition.y=myPosition.y;
                y[myPosition.x][myPosition.y].SetWho(temp);
                // y[myPosition.x][myPosition.y].which.myPosition.x=myPosition.x;
                // y[myPosition.x][myPosition.y].which.myPosition.y=myPosition.y;
                myPosition.x=x.x;
                myPosition.y=x.y;

            }
            else
            {
                moving=true;
                y[myPosition.x][myPosition.y].SetALL(false,null);
                myPosition.x=x.x;
                myPosition.y=x.y;

            }
        }
        else
        {
            if(y[x.x][x.y].GetIsOccupied()==false)
            {
                y[x.x][x.y].SetALL(true,this);
                myPosition.x=x.x;
                myPosition.y=x.y;
                moving=false;
            }
            else if((x.x==dest.x)&&(x.y==dest.y))
            {
                //y[x.x][x.y].isOccupied=true;
                Lives temp=y[x.x][x.y].GetWho();
                y[x.x][x.y].SetWho(this);
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

    public void walk(Position x,Tile y[][])
    {
        dest=new Position(x.x,x.y);
        orin=new Position(myPosition.x,myPosition.y);
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
            Position temp=new Position(myPosition.x+d1,myPosition.y);
            oneStep(temp,y);
        }
        for(int i=0;i<d2len;i++)
        {
            Position temp=new Position(myPosition.x,myPosition.y+d2);
            oneStep(temp,y);
        }
        dest=null;
    }
    public Position GetOrin()
    {
        return orin;
    }
    public void SetOrin(Position x)
    {
        orin=x;
    }
    public void SetDest(Position x)
    {
        dest=x;
    }
    public Position GetDest()
    {
        return dest;
    }
    public boolean GetMoving()
    {
        return moving;
    }
    public void SetMoving(boolean x)
    {
        moving=x;
    }
    public void SetPosition(Position x)
    {
        myPosition=x;
    }
    public Position GetPosition()
    {
        return myPosition;
    }
}
