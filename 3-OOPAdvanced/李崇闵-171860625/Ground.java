public class Ground {
    Creature [][]Array;
    int N;
    Ground(int n){
        N=n;
        Array=new Creature[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++) {
                Array[i][j]=new Creature();
                Array[i][j].name="[]";
                Array[i][j].row=i;
                Array[i][j].col=j;
            }
    }
    void Change_pos(Creature c){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(Array[i][j].name=="[]"){
                    Array[i][j]=Array[c.row][c.col];
                    Array[c.row][c.col].row=i;
                    Array[c.row][c.col].col=j;
                    Array[c.row][c.col]=c;
                    break;
                }
            }
            break;
        }
    }
    void Register(Creature c){
        while(c.row>=N)
            c.row--;
        while(c.col>=N)
            c.col--;
        if(Array[c.row][c.col].name=="[]")
             Array[c.row][c.col]=c;
        else
            Change_pos(c);
    }

    void Logout(Creature c){
        Array[c.row][c.col]=new Creature();
        Array[c.row][c.col].name="[]";
        Array[c.row][c.col].row=c.row;
        Array[c.row][c.col].col=c.col;
    }
    void Print_all(){
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(Array[i][j].name);
                if(j==N/2-1){
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
