public class Sidekicks extends Lives {
    Sidekicks(int x,int y,Tile z[][])
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
