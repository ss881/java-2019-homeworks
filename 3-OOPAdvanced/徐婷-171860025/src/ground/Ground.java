package ground;
import creatures.*;

public class Ground{
    static final int N=13;
    Creature huluwa[];
    Creature damons[];
    Cell [][]grounds;
    public Ground(){
        grounds=new Cell[N][N];
        huluwa=new Creature[8];
        damons=new Creature[26];
        for(int i=0;i<huluwa.length;i++)
            huluwa[i]=new Creature();
        for(int i=0;i<damons.length;i++) {
            damons[i] = new Creature();

        }
    }
    public Ground(Creature hulu[],Creature snake[]){
        grounds=new Cell[N][N];
        update(hulu);
        update(snake);
    }
    public void update(Creature []c){
       // System.out.println("update from ground "+c[0]);
        
        if(c[0].getCamp()==Camp.HULUWA){

            for(int i=0;i<c.length;i++){
                huluwa[i]=c[i];
            }
        }
        else{

            for(int i=0;i<c.length;i++){
                damons[i]=c[i];
            }
        }
        update();
    }
    private void update(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                grounds[i][j]=new Cell();
            }
        }
        for(int i=0;i<huluwa.length;i++){
            int x=huluwa[i].getCoordX();
            int y=huluwa[i].getCoordY();
            grounds[x][y].setNameAndState(huluwa[i].getName(), huluwa[i].getState());
        }
        for(int i=0;i<damons.length;i++){
            int x=damons[i].getCoordX();
            int y=damons[i].getCoordY();
            grounds[x][y].setNameAndState(damons[i].getName(), damons[i].getState());
            //注意葫芦娃与蛇精位置重合
        }
    }
    public void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                //if(grounds[i][j])
                System.out.print(grounds[i][j].getName()+"   ");
            }
            System.out.print("\n");
        }
    }
}