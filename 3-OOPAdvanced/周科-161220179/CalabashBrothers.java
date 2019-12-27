public class CalabashBrothers {
    static String Color[]={"红色","橙色","黄色","绿色","青色","蓝色","紫色"};
    static String Name[]={"老大","老二","老三","老四","老五","老六","老七"};
    static int Rank[]={1,2,3,4,5,6,7};

    String color,name;
    int rank;
    int row;
    int column;

    CalabashBrothers(int n){
        color=Color[n];
        name=Name[n];
        rank=Rank[n];
        row=0;column=0;
    }

    public void TellName(){
        System.out.println(name);
    }
    public void TellColor(){
        System.out.println(color);
    }
    public void TellExchangeLocation(int oldLoc,int newLoc) {
        System.out.println(name + ": " + oldLoc + "->" + newLoc);
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
