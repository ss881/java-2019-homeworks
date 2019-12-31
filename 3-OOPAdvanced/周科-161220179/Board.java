public class Board {
    public int xmax;
    public int ymax;
    public char[][] myBoard;
    Board(){
        xmax=10;ymax=20;
        myBoard=new char[xmax][ymax];
        for(int i=0;i<xmax;i++) {
            for (int j = 0; j < ymax; j++) {
                myBoard[i][j] = ' ';
            }
        }
    }
    /*
    Board(int x,int y){
        xmax=x;
        ymax=y;
        myBoard=new char[xmax][ymax];
        for(int i=0;i<xmax;i++) {
            for (int j = 0; j < ymax; j++) {
                myBoard[i][j] = ' ';
            }
        }
    }
     */
    public void InitialBoard(){
        for(int i=0;i<xmax;i++) {
            for (int j = 0; j < ymax; j++) {
                myBoard[i][j] = ' ';
            }
        }
    }
    public void PrintBoard(){
        for(int i=0;i<xmax;i++) {
            for (int j = 0; j < ymax; j++) {
                System.out.print(myBoard[i][j]);
            }
            System.out.println();
        }
    }
    public void FormFeatrues(FormCalabash fc,FormSoldiers fs){
        myBoard[fc.gFather.GetRow()][fc.gFather.GetColumn()]='G';
        for(int i=0;i<7;i++) {
            myBoard[fc.form[i].GetRow()][fc.form[i].GetColumn()]=(char)(i+1+'0');
        }
        myBoard[fs.myKing.GetRow()][fs.myKing.GetColumn()]='K';
        myBoard[fs.myQueen.GetRow()][fs.myQueen.GetColumn()]='Q';
        for(int i=0;i<6;i++){
            myBoard[fs.form[i].GetRow()][fs.form[i].GetColumn()]='s';
        }
    }
    public static void main(String args[]){
        Board BattleField =new Board();
        MySort mySortCalabash=new MySort();
        mySortCalabash.BubbleSortBoys();
        mySortCalabash.QueueBoys[0].SetRowColumn(5,8);
        FormCalabash myFromCalabash =new FormCalabash(mySortCalabash.QueueBoys);
        myFromCalabash.Forming();

        FormSoldiers myFormSoldiers1=new FormSoldiers(1,5,18);
        myFormSoldiers1.Forming();
        BattleField.FormFeatrues(myFromCalabash,myFormSoldiers1);
        BattleField.PrintBoard();

        BattleField.InitialBoard();
        FormSoldiers myFormSoldiers2=new FormSoldiers(2,8,18);
        myFormSoldiers2.Forming();
        BattleField.FormFeatrues(myFromCalabash,myFormSoldiers2);
        BattleField.PrintBoard();

    }


}
