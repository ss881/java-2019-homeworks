public class Hulu extends Creature {
    Hulu(int r,int c,String n){
                row=r;
                col=c;
                name=n;
    }
    boolean Look_forward(Hulu h,int i){
            if(col==h.col&&row==h.row+i){
            return false;
        }
        return true;
    }
}
