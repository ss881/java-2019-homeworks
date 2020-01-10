public class Formation{
    int[]x;
    int[]y;
    int minx;
    int miny;
    public void formationInit(int minx,int miny,int n,int[]x,int[]y){
        this.x=x.clone();
        this.y=y.clone();
        try{
            if(n!=x.length||n!=y.length)
                throw new Exception("Formation error:coordinate number discrepancy");
            for(int i=0;i<n;i++)
                if(x[i]<0||x[i]>=minx/2||y[i]<0||y[i]>=miny)
                    throw new Exception("Formation error:coordinate transboundary");
        }catch(Exception e){
            System.err.println(e);
        }
    }
    public Formation(int minx,int miny,int n,int[]x,int[]y){
        formationInit(minx, miny, n, x, y);
    }
    public Formation(int code){
        switch(code){
        case 0:
            int n0=7;
            int minx0=7;
            int miny0=7;
            int[]x0={0,0,0,0,0,0,0};
            int[]y0={0,1,2,3,4,5,6};
            formationInit(minx0,miny0,n0,x0,y0);
            break;
        case 1:
            int n1=7;
            int minx1=7;
            int miny1=7;
            int[]x1={2,1,0,1,0,1,2};
            int[]y1={0,1,2,3,4,5,6};
            formationInit(minx1, miny1, n1, x1, y1);
        default:
            System.err.println("Formation error:code error");
        }
    }
    public int getlenth(){
        return x.length;
    }
    public int getX(int i){
        return x[i];
    }
    public int getY(int i){
        return y[i];
    }
    public int getMinx(){
        return minx;
    }
    public int getMiny(){
        return miny;
    }
}