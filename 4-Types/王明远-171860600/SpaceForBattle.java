public class SpaceForBattle{

    private int size_of_space;// the size of space
    private int [][]space;// to store the whole space
    SpaceForBattle(int initial_size)
    {
        size_of_space=initial_size;
        space =new int [size_of_space][size_of_space];
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
                space[i][j]=0;
    }

    private int identityTomark(Identity id)// change id into int
    {
        int mark=0;
        switch(id)
        {
            case Grandpa:   
                mark=1;break;
            case BrotherStandl: //this is the general mark ,
                                // we use more concrete mark to represent differet brotherstandl 
                mark=2;break;
            case MonsterSnake:
                mark=3;break;
            case MonsterScorpion:
                mark=4;break;
            case MonsterSaltFish:
                mark=5;break;
            case Unkown:break;
        }
        return mark;
    }

    void rebulid_space()// empty the space
    {
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
                space[i][j]=0;
    }
    boolean rush_to_coordinate(Identity id, int x,int y)
    //one creature with id rush to somespace from out
    {
        int mark=identityTomark(id);
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return false;
        if(space[x][y]==0)
        {
            space[x][y]=mark;
            return true;
        }
        else 
            return false;
    }

    boolean rush_to_coordinate(BrotherStandl brotherStandl, int x,int y)
    // use the special function to tell the difference of brotherstandl
    {
        int mark=brotherStandl.report_color()+10;
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return false;
        if(space[x][y]==0)
        {
            space[x][y]=mark;
            return true;
        }
        else 
            return false;
    }

    void change_coordinate(Identity id,int x1, int x2, int y1, int y2)
    // one creature with id change its space to another
    {
        if(space[x2][y2]==0)
        {
            rush_to_coordinate(id, x2, y2);
            if(x1<0||y1<0||x1>=size_of_space||y1>=size_of_space);
            else
            space[x1][y1]=0;
        }   
        else
        {
            int partner=space[x2][y2];
            space[x2][y2]=0;
            rush_to_coordinate(id, x2, y2);
            space[x1][y1]=partner;
        }
    }
    void overlook_space()// output the whole space from high
    {
        for(int i=0;i<size_of_space;++i)
        {
            for(int j=0;j<size_of_space;++j)
            {
                switch(space[j][i])
                {
                    case 0:System.out.printf("%1s","  ");break;
                    case 1:System.out.printf("%1s","爷");break;
                    case 3:System.out.printf("%1s","蛇");break;
                    case 4:System.out.printf("%1s","蝎");break;
                    case 5:System.out.printf("%1s","兵");break;
                    case 11:System.out.printf("%1s","红");break;// we cannot tell the rank of the brotherstandl from high
                                                                // so we use color to represent each brotherstandl
                    case 12:System.out.printf("%1s","橙");break;
                    case 13:System.out.printf("%1s","黄");break;
                    case 14:System.out.printf("%1s","绿");break;
                    case 15:System.out.printf("%1s","青");break;
                    case 16:System.out.printf("%1s","蓝");break;
                    case 17:System.out.printf("%1s","紫");break;
                    default:break;
                }
            }
            System.out.print("\n");
        }
    }
    int get_size()// get the size of space
    {
        return size_of_space;
    }
    void set_space(int chess,int x,int y)// set one concrete space
    {
        space[x][y]=chess;
    }
    int read_space(int x,int y)// try to know the state of concrete space
    {
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
        return 0;
        else
        return space[x][y];
    }
    boolean check_space(Identity id ,int x ,int y)// try to check one concrete space's state
    {
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return false;
        if(space[x][y]==identityTomark(id))
            return true;
        return false;
    }
}