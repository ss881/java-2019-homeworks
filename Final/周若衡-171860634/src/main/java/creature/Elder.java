package creature;


import chessboard.Cell;
import gui.MyTextArea;
import behave.CallBehave;
import playrecord.Record;
import main.Main;
import chessboard.ChessBoard;
import util.ImageType;

public class Elder extends GoodCreature {

    public Elder(int x, int y, ImageType image, int hp, boolean alive, int battle, String name, int speed, int id) {
        super(x,y,image,hp,alive,battle,name,speed,id);
    }
    @Override
    public Object clone() {
        Elder temp = null;
        temp = (Elder) super.clone();	//浅复制
        return temp;
    }

    public boolean callMonster(ChessBoard board, MyTextArea textArea, boolean isAddCommand) {
        synchronized (board){
            if(board.canPutCreature(x,y+1)==true){
                Pangolin peaPod=new Pangolin(x,y+1, ImageType.PEA_POD,50,true,0,"pangolin",100,20);
                Cell temp=board.getSquare(x,y+1);
                temp.setBeing(peaPod);
                board.setSquare(temp);
                if(textArea!=null){
                    textArea.appendTextArea("召唤穿山甲进入战场\n");
                }
                if(isAddCommand==true){
                    long curTime = System.currentTimeMillis();
                    //准备记录信息，写入数组之中
                    Record command = new Record(this, new CallBehave((int) (curTime - Main.chessBoard.startTime), peaPod));
                    synchronized (Main.chessBoard.getRecordAdminister().getCommands()) {
                        Main.chessBoard.getRecordAdminister().addOne(command);
                    }
                }
                if(textArea!=null && isAddCommand==true){
                    Thread t=new Thread(peaPod);
                    t.start();
                }

                return true;
            }
        }
        return false;
    }
}