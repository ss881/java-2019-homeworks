package playrecord;

import behave.AttackBehave;
import behave.BulletBehave;
import behave.CallBehave;
import behave.MoveBehave;
import creature.Creature;
import creature.Elder;
import creature.Pangolin;
import main.Main;
import chessboard.ChessBoard;

import java.io.*;
import java.util.ArrayList;

public class PlayRecorder implements Runnable{
    private ArrayList<Record> records;
    private ChessBoard chessBoard;
    public PlayRecorder(ArrayList<Record> commands, ChessBoard board){
        this.records =commands;
        this.chessBoard =board;
    }

    @Override
    public void run() {
        int cur=0;
        long placeBackStartTime=System.currentTimeMillis();
        long placeBackCurTime=0;
        Main.textArea.appendText("Starting Playing Record\n");
        while (cur< records.size() && !Thread.interrupted()) {

            Record command = records.get(cur);
            placeBackCurTime = System.currentTimeMillis();
            if (command.getBehave().getSleepTime() <= (placeBackCurTime - placeBackStartTime)) {
                Creature temp = Main.chessBoard.getChessBoard().searchCreature(command.getCreature());
                if(command.getBehave() instanceof BulletBehave){
                    BulletBehave fireBehave=(BulletBehave)command.getBehave();
                    Main.chessBoard.getBulletAdminister().addBullet(fireBehave.getBullet());
                    //System.out.println("add bullet");
                    cur++;
                }
                else if (temp != null) {
                    if (command.getBehave() instanceof MoveBehave) {
                        MoveBehave moveBehave = (MoveBehave) command.getBehave();
                        temp.move(moveBehave.getDx(), moveBehave.getDy(), chessBoard,Main.textArea,false);
                    } else if (command.getBehave() instanceof AttackBehave) {
                        AttackBehave attackBehave = (AttackBehave) command.getBehave();
                        Creature enemy = Main.chessBoard.getChessBoard().searchCreature(attackBehave.getEnemy());
                        if (enemy != null) {
                            temp.attack(enemy, chessBoard,Main.textArea,false);
                        } else {
                            System.err.println("attackbehave error");
                        }
                    }else if(command.getBehave() instanceof CallBehave){
                        CallBehave callBehave = (CallBehave) command.getBehave();
                        Creature peapot=callBehave.getCreature();
                        if(peapot instanceof Pangolin && temp instanceof Elder){
                            ((Elder) temp).callMonster(chessBoard,Main.textArea,false);
                        }
                        else{
                            System.err.println("callbehave error");
                        }
                    }
                    cur++;
                }
                else{
                    System.out.println(command.getCreature().getName());
                    System.out.println(cur);
                    if(command.getBehave() instanceof AttackBehave)
                        System.err.println("attack");
                    if(command.getBehave() instanceof MoveBehave)
                        System.err.println("move");
                    System.err.println("id not exists");
                }
                Main.chessBoard.paint();
            }
        }
    }

    //Record数组中的每一条信息都写入file中。同时先写两个阵营的阵型信息(存在index中)
    public static void writeRecords(ArrayList<Record> records, ArrayList<Integer> index, File file){
        try{
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(index.get(0));    //写入两个阵型信息
            out.writeObject(index.get(1));
            for(Record r:records){
                out.writeObject(r);
            }
            System.out.println("------->write records size:"+records.size());
            int count=0;
            for(Record command:records){
                if(command.getBehave() instanceof AttackBehave){
                    count++;
                }
            }
            System.err.println("write records size:"+records.size());
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }catch (Exception i){
            i.printStackTrace();
        }
    }

    //读入阵型信息 然后再循环读取文件中的每一条记录至commands数组中
    public static void readRecords(ArrayList<Record> records, ArrayList<Integer> index, File file){
        records.clear();
        index.clear();
        try
        {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            int index1=(Integer) in.readObject();
            int index2=(Integer) in.readObject();
            index.add(index1);
            index.add(index2);
            Record r=null;
            while((r=(Record)in.readObject())!=null){
                records.add(r);
            }
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Record class not found");
            c.printStackTrace();
            return;
        }
    }

}
