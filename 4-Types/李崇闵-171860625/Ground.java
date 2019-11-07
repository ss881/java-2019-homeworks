import java.lang.reflect.*;
import java.util.Arrays;
public class Ground<T extends Creature> {
     private int N;
     //private Creature[][] array;
     private T[][] array;
     Class<T> Type;
     private Class<T> kind;
     Ground(Class<T> type,int n){
        N=n;
        kind=type;
       // Type=type;
        //array=new Creature[N][N];
        array=(T[][])Array.newInstance(type,N,N);
        for(int i=0;i<N;i++){
                for(int j=0;j<N;j++) {
                    //array[i][j]=new Creature();
                    //array[i][j].name="[]";
                    //array[i][j].row=i;
                    //array[i][j].col=j;
                    T temp=(T)new Creature();
                    //Creature temp=new Creature();
                    //T temp=type.newInstance() ;
                    temp.name="[]";
                    temp.row=i;
                    temp.col=j;
                    Array.set(array[i],j,temp);
;
            }
        }
    }
    private void Change_pos(T c){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j].name.equals("[]")){
                    array[i][j]=array[c.row][c.col];
                    array[c.row][c.col].row=i;
                    array[c.row][c.col].col=j;
                    array[c.row][c.col]=c;
                    break;
                }
            }
            break;
        }
    }
    void Register(T c){
        while(c.row>=N)
            c.row--;
        while(c.col>=N)
            c.col--;
        if(array[c.row][c.col].name.equals("[]"))
            array[c.row][c.col]=c;
        else
            Change_pos(c);
    }

    void Logout(T c){
        //array[i][j]=new Creature();
        //array[c.row][c.col].name="[]";
        //array[c.row][c.col].row=c.row;
        //array[c.row][c.col].col=c.col;
        T temp=(T)new Creature();
        temp.name="[]";
        temp.row=c.row;
        temp.col=c.col;
        Array.set(array[c.row],c.col,temp);
    }
    void Print_all(){
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(array[i][j].name);
                if(j==N/2-1){
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
