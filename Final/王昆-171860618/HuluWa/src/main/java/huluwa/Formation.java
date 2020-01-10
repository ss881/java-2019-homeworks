package huluwa;
import huluwa.position.Position;

import java.util.ArrayList;
import java.util.List;
class Formation {
    public final static int TOTOAL = 4;
    private Position base;
    private int num;
    private int direction;
    private static int dx = 1;
    private static int dy = 1;
    public List<Position> getFormation(int i){
        switch(i){
            case 0:{
                return this.heYi();
            }
            case 1:{
                return this.changShe();
            }
            case 2:{
                return this.chongE();
            }
            default:{
                return this.yanXing();
            }

        }
    }
    Formation(Position p, int n, int d){
        direction = d;
        num = n;
        base = new Position(p.getX(),p.getY()-dy);
    }
    public List<Position> heYi(){
    List<Position> heYisites =  new ArrayList<>();
    int x = base.getX();
    int y = base.getY();
    heYisites.add(new Position(x,y));
    for(int i=1;i<num;i++){
        int t = (i%2 == 1)?1:-1;
        int p = (i-1)/2+1;
        heYisites.add(new Position(x+p*direction*dx,y+p*t*dy));
    }
    return heYisites;
   }
    public List<Position> yanXing(){
    List<Position> yanXingsites =  new ArrayList<>();
    int x = base.getX();
    int y = base.getY()-(num-1)/2;
    yanXingsites.add(new Position(x,y));
    for(int i=1;i<num;i++){
        yanXingsites.add(new Position(x+i*direction*dx,y+i*dy));
    }
    return yanXingsites;
   }
   public List<Position> chongE(){
    List<Position> chongEsites =  new ArrayList<>();
    int x = base.getX();
    int y = base.getY()-(num-1)/2;
    chongEsites.add(new Position(x,y));
    for(int i=1;i<num;i++){
        chongEsites.add(new Position(x+ (i%2)*direction*dx,y+i*dy));
    }
    return chongEsites;
   }
   public List<Position> changShe(){
    List<Position> changShesites =  new ArrayList<>();
    int x = base.getX();
    int y = base.getY();
    changShesites.add(new Position(x,y));
    for(int i=1;i<num;i++){
        int t = (i%2 == 1)?1:-1;
        int p = (i-1)/2+1;
        changShesites.add(new Position(x,y+t*p*dy));
    }
    return changShesites;
   }
}
