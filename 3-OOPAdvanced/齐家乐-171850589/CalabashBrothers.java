//Qi Jiale 2019.09.16

public class CalabashBrothers{
    public static void main(String[] args) {
        CalabashBro [] calabashBros={
            new CalabashBro("红"),
            new CalabashBro("橙"),
            new CalabashBro("黄"),
            new CalabashBro("绿"),
            new CalabashBro("青"),
            new CalabashBro("蓝"),
            new CalabashBro("紫")
        };
        Life [] enemy={
            new Mob(),
            new Mob(),
            new Mob(),
            new Scorpion(),
            new Mob(),
            new Mob(),
            new Mob()
        };
        CalabashMap calabashMap=new CalabashMap(11);
        LongSnake longSnake=new LongSnake(calabashBros);
        CraneWing craneWing=new CraneWing(enemy);
        Grandfather grandfather=new Grandfather();
        Snake snake=new Snake();
        calabashMap.LifeIn(longSnake, 1, 1);
        calabashMap.LifeIn(craneWing, 2, 3);
        calabashMap.LifeIn(grandfather, 9, 1);
        calabashMap.LifeIn(snake, 7, 6);
        calabashMap.PrintMap();

        calabashMap.clear();
        WildGoose wildGoose=new WildGoose(enemy);
        calabashMap.LifeIn(longSnake, 1, 1);
        calabashMap.LifeIn(wildGoose, 2, 3);
        calabashMap.LifeIn(grandfather, 9, 1);
        calabashMap.LifeIn(snake, 7, 6);
        System.out.println("");
        calabashMap.PrintMap();
    }
}

class Life{
    String name;
    Life(){
    }
}

class CalabashBro extends Life{
    CalabashBro(String name) {
       this.name=name;
    }
}

class Grandfather extends Life{
    Grandfather(){
        this.name="爷";
    }
}

class Snake extends Life{
    Snake(){
        this.name="蛇";
    }
}

class Scorpion extends Life{
    Scorpion(){
        this.name="蝎";
    }
}

class Mob extends Life{
    Mob(){
        this.name="喽";
    }
}

class CalabashMap{
    int N;
    Grid grids[][];
    CalabashMap(int N){
        this.N=N;
        this.grids=new Grid[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid();
            }
        }
    }
    public void clear(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid();
            }
        }
    }
    public void LifeIn(Formation formation,int M,int N){
        if(M+formation.get_M()>this.N||N+formation.get_N()>this.N){
            System.err.println("Out of range!");
            assert(false);
        }
        for(int i=0;i<formation.get_M();i++){
            for(int j=0;j<formation.get_N();j++){
                //System.out.println(formation.get_M()+""+formation.get_N()+""+i+""+j);
                if(!formation.is_empty(i, j)){
                    assert(grids[i+M][j+N].empty());
                    grids[i+M][j+N].LifeIn(formation.get_M_N(i, j));
                }
            }
        }
    }
    public void LifeIn(Life life,int M,int N){
        if(M>this.N||N>this.N){
            System.err.println("Out of range!");
            assert(false);
        }
        grids[M][N].LifeIn(life);
    }
    public void PrintMap(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(grids[i][j].empty()){
                    System.out.print("口");
                }else{
                    System.out.print(grids[i][j].life.name);
                }
            }
            System.out.println("");
        }
    }
}

class Formation{
    int M,N;
    Grid grids[][];
    Formation(){
    }
    public int get_M(){
        return this.M;
    }
    public int get_N(){
        return this.N;
    }
    public Boolean is_empty(int i,int j){
        return grids[i][j].empty();
    }
    public Life get_M_N(int M,int N){
        assert(!grids[M][N].empty());
        return grids[M][N].get_life();
    }
}

//鹤翼
class CraneWing extends Formation{
    CraneWing(Life[] life){
        this.M=4;
        this.N=7;
        this.grids=new Grid[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid();
            }
        }
        grids[0][0].LifeIn(life[0]);
        grids[1][1].LifeIn(life[1]);
        grids[2][2].LifeIn(life[2]);
        grids[3][3].LifeIn(life[3]);
        grids[2][4].LifeIn(life[4]);
        grids[1][5].LifeIn(life[5]);
        grids[0][6].LifeIn(life[6]);
    }
}

//雁行阵
class WildGoose extends Formation{
    WildGoose(Life[] life){
        this.M=7;
        this.N=7;
        this.grids=new Grid[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid();
            }
        }
        grids[0][6].LifeIn(life[0]);
        grids[1][5].LifeIn(life[1]);
        grids[2][4].LifeIn(life[2]);
        grids[3][3].LifeIn(life[3]);
        grids[4][2].LifeIn(life[4]);
        grids[5][1].LifeIn(life[5]);
        grids[6][0].LifeIn(life[6]);
    }
}

//长蛇阵
class LongSnake extends Formation{
    LongSnake(Life[] life){
        this.M=7;
        this.N=1;
        this.grids=new Grid[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid();
            }
        }
        grids[0][0].LifeIn(life[0]);
        grids[1][0].LifeIn(life[1]);
        grids[2][0].LifeIn(life[2]);
        grids[3][0].LifeIn(life[3]);
        grids[4][0].LifeIn(life[4]);
        grids[5][0].LifeIn(life[5]);
        grids[6][0].LifeIn(life[6]);
    }
}


class Grid{
    boolean empty;
    Life life;
    Grid(){
        this.empty=true;
        this.life=null;
    }
    public void LifeIn(Life life){
        this.life=life;
        this.empty=false;
    }
    public Life LifeOut(){
        Life temp=this.life;
        this.life=null;
        this.empty=true;
        return temp;
    }
    public boolean empty(){
        return empty;
    }
    public Life get_life(){
        return life;
    }
}