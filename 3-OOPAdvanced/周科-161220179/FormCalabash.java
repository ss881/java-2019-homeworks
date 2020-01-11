public class FormCalabash {
    private int LeaderRow;
    private int LeaderColumn;
    public GrandFather gFather;
    public CalabashBrothers[] form;

    FormCalabash(CalabashBrothers []queue){
        LeaderRow=queue[0].GetRow();
        LeaderColumn=queue[0].GetColumn();
        form=queue;
        gFather=new GrandFather();
    }

    public void Forming(){
        for(int i=1;i<7;i++){
            form[i].SetRowColumn(LeaderRow,LeaderColumn-i);
        }
        gFather.SetRowColumn(LeaderRow-1,LeaderColumn-3);//爷爷站在长蛇阵的正上方。
    }
}
