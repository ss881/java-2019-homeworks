public class Soldiers {
    public int no;
    private int row;
    private int column;
    Soldiers(int number){
        no=number;
        row=0;
        column=0;
    }
    public int GetRow(){
        return row;
    }
    public int GetColumn(){
        return column;
    }
    public void SetRowColumn(int x,int y){
        row=x;column=y;
    }
}
