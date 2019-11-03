public class FormSoldiers {
    private int LeaderRow;
    private int LeaderColumn;
    public King myKing;
    public Queen myQueen;
    public Soldiers[] form;
    private int FormKind;

    FormSoldiers(int kind,int x,int y){
        FormKind=kind;
        myKing=new King();
        myKing.SetRowColumn(x,y);
        myQueen=new Queen();
        LeaderRow=myKing.GetRow();
        LeaderColumn=myKing.GetColumn();
    }

    public void Forming(){
        form=new Soldiers[6];
        for(int i=0;i<6;i++){
           form[i]=new Soldiers(i+1);
        }
        myQueen.SetRowColumn(LeaderRow,LeaderColumn+1);//蛇精呆在蝎子精身后
        if(FormKind==1){
            for(int i=0;i<3;i++) {
                form[i].SetRowColumn(LeaderRow - i - 1, LeaderColumn - i - 1);
            }
            for(int i=3;i<6;i++) {
                form[i].SetRowColumn(LeaderRow + i - 2, LeaderColumn - i + 2);

            }

        }
        else if(FormKind==2){
            for(int i=0;i<6;i++) {
                form[i].SetRowColumn(LeaderRow - i - 1, LeaderColumn - i - 1);
              //  System.out.print(LeaderRow - i - 1);
              //  System.out.print(' ');
             //   System.out.println(LeaderColumn - i - 1);
            }
        }
        else{
            System.out.println("undeclined forming!");
        }
    }
}
