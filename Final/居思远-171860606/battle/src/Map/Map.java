package Map;

public class Map {
    private int N=15;
    private int map[][]=new int[N][N];
    public Map()
    {
        for (int jk = 0; jk < N; jk++) {
            for (int jt = 0; jt < N; jt++)
                map[jk][jt] = -1;
        }
    }

    public int getContent(int x,int y)
    {return map[x][y];}

    public int getX(int id)
    {
        boolean found=false;
        int temp = -1;
        for(int jk=0;jk<N;jk++) {
            for (int jt = 0; jt < N; jt++) {
                if(map[jk][jt]==id)
                {temp=jk;found=true;break;}
            }
            if(found==true)break;
        }
        return temp;
    }

    public int getY(int id)
    {
        boolean found=false;
        int temp = -1;
        for(int jk=0;jk<N;jk++) {
            for (int jt = 0; jt < N; jt++) {
                if(map[jk][jt]==id)
                {temp=jt;found=true;break;}
            }
            if(found==true)break;
        }
        return temp;
    }

    public void setMap(int x, int y,int id)
    {
        map[x][y]=id;
    }

    public void reSet()
    {
        for (int jk = 0; jk < N; jk++) {
            for (int jt = 0; jt < N; jt++)
                map[jk][jt] = -1;
        }
    }

    public void move(int x,int y, int id, int search)
    {
        //0为上，1为下，2为左，3为右
        if(search==0)
            map[x][y+1]=id;
        else if(search==1)
            map[x][y-1]=id;
        else if(search==1)
            map[x-1][y]=id;
        else if(search==1)
            map[x+1][y]=id;
        map[x][y]=-1;
    }

    public void show(){
        for (int jk = 0; jk < N; jk++) {
            for (int jt = 0; jt < N; jt++)
            {
                System.out.print(map[jk][jt]);
                System.out.print("  ");
            }
            System.out.println("");
        }

    }

}
