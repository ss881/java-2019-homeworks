public class Snake extends Lives {

    public Snake(int x,int y ,Tile z[][])
    {
        moving=false;
        myPosition=new Position(x,y);
        if(z[x][y].GetIsOccupied()==false)
        {
            z[x][y].SetALL(true,this);
        }
    }
}
