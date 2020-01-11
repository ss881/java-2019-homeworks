public class King {
    private String name;
    private int row;
    private int column;

    King() {
        name = "蝎子精";
        row = 0;
        column = 0;
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
