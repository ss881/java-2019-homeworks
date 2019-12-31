public class Queen {
    private String name;
    private int row;
    private int column;

    Queen(){
        name="蛇精";
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
