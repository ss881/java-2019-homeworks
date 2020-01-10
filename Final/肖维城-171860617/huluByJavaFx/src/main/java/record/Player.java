package record;

import war.BattleField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Player implements Runnable{
    private StringBuilder recordBuffer;
    private String filePath;

    private static class Singleton{
        private static Player player = new Player();
    }

    private Player(){
        recordBuffer = new StringBuilder();
    }

    public static Player getInstance(){
        return Singleton.player;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        System.out.println(this.filePath);
    }

    void readFile(){
        if(filePath.isEmpty())
            return;
        BufferedReader bufferedReader = null;
        synchronized (Player.class) {
            try{
                bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
                String record = null;
                recordBuffer.delete(0, recordBuffer.length());
                while((record=bufferedReader.readLine())!=null){
                    recordBuffer.append(record+"\n");
                }
            } catch (IOException exception){
                exception.printStackTrace();
            } finally {
                if(bufferedReader != null){
                    try {
                        bufferedReader.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        readFile();
        if(recordBuffer.toString().isEmpty())
            return;
        String[] records = recordBuffer.toString().split("\n");
        System.out.println(records[0]+'\n'+records[1]+" recordsSize="+records.length);
        //int countView = 0;
        int countCreature = 0;
        //while (BattleField.getInstance().isPlay()){
            for(int i=0; i<records.length; i++){
                String[] info = records[i].split(" ");
                    int id = Integer.valueOf(info[0]);
                    int x = Integer.parseInt(info[1]);
                    int y = Integer.parseInt(info[2]);
                    int hp = Integer.valueOf(info[3]);
                    System.out.println("id="+id+" x="+x+" y="+y+" hp="+hp);
                    if(id >= 0 && id <= 7){
                        if(id == 0) {
                            if(BattleField.getInstance().hb.grandPa.isAlive()) {
                                BattleField.getInstance().hb.grandPa.setPosition(x, y);
                                BattleField.getInstance().hb.grandPa.setHealthPoint(hp);
                            }
                            System.out.println("grandpa");
                            countCreature++;
                        }
                        else{//1-7
                            if(BattleField.getInstance().hb.hList.get(id-1).isAlive()) {
                                BattleField.getInstance().hb.hList.get(id - 1).setPosition(x, y);
                                BattleField.getInstance().hb.hList.get(id - 1).setHealthPoint(hp);
                            }
                            System.out.println("hulu");
                            countCreature++;
                        }
                    }else if(id == (int)'#'){
                        if(BattleField.getInstance().ms.ScorpionSpirit.isAlive()) {
                            BattleField.getInstance().ms.ScorpionSpirit.setPosition(x, y);
                            BattleField.getInstance().ms.ScorpionSpirit.setHealthPoint(hp);
                        }
                        countCreature++;
                    }else if(id == (int)'$'){
                        if(BattleField.getInstance().ms.SnakeSpirit.isAlive()) {
                            BattleField.getInstance().ms.SnakeSpirit.setPosition(x, y);
                            BattleField.getInstance().ms.SnakeSpirit.setHealthPoint(hp);
                        }
                        countCreature++;
                    }else if(id >= ((int)'A') && id <= ((int)'Z')){
                        if(BattleField.getInstance().ms.mList.get(id-(int)'A').isAlive()) {
                            BattleField.getInstance().ms.mList.get(id - (int) 'A').setPosition(x, y);
                            BattleField.getInstance().ms.mList.get(id - (int) 'A').setHealthPoint(hp);
                        }
                        System.out.println("frogs");
                        countCreature++;
                    }else{
                        assert true;
                    }
                //}
                System.out.println(countCreature);
                if(countCreature == (8+2+BattleField.getInstance().f.mCount)) {
                    BattleField.getInstance().modifyView();
                    try {
                        Random r = new Random();
                        //Thread.sleep(500);
                        Thread.sleep(r.nextInt(500)+100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    countCreature = 0;
                }
            }
        //}
        //BattleField.getInstance().modifyView();
    }
}
