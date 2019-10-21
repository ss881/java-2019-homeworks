public class Scorpion extends Lives {
    void summonSidekicks(Sidekicks x[],int number,Tile ground[][])
    {
        for(int i=0;i<number;i++)
        {
            x[i]=new Sidekicks(i+2,myPosition.y+2,ground);
        }
    }
    void T1(Sidekicks x[],Tile ground[][])//heyi
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }
    void T2(Sidekicks x[],Tile ground[][])//yanxing
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }
    void T3(Sidekicks x[],Tile ground[][])//&$%#*(GY
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }
    void T4(Sidekicks x[],Tile ground[][])//changshe
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }

    void T5(Sidekicks x[],Tile ground[][])
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }
    void T6(Sidekicks x[],Tile ground[][])
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }
    void T7(Sidekicks x[],Tile ground[][])
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }
    void T8(Sidekicks x[],Tile ground[][])
    {
        int a=0;int b=0;
        for(int i=0;i<7;i++)
        {
            position temp;
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
            temp=new position(a,b);
            x[i].walk(temp,ground);
        }
    }
    Scorpion(int x,int y ,Tile z[][])
    {
        moving=false;
        myPosition=new position(x,y);
        if(z[x][y].isOccupied==false)
        {
            z[x][y].isOccupied=true;
            z[x][y].which=this;
        }
    }
}

