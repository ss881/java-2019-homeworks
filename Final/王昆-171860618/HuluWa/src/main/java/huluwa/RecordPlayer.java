package huluwa;

import javafx.application.Platform;
import huluwa.position.Position;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class RecordPlayer extends Thread {
    private int time = 0;
    private File filepath;
    RecordPlayer(File file) {
        this.filepath = file;
    }
    public void run(){
        //System.out.println("start replay");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null){
                //System.out.println(line);
                handleRecords(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(GameController::crashed);
        }
    }
    private void handleRecords(String record) throws Exception {
        List<String> list = Arrays.asList(record.split(" "));
        int t = Integer.parseInt(list.get(0));
        TimeUnit.MILLISECONDS.sleep(t-time);
        time = t;
        if(list.get(1).equals("set")){
            Creature c = Creature.searchByName(list.get(2));
            if(c == null)
                throw new NullPointerException();
            List<String> position = Arrays.asList(list.get(3).split(","));
            int x = Integer.parseInt(position.get(0));
            int y = Integer.parseInt(position.get(1));
            Position p = new Position(x, y);
            //System.out.println("move " + c + " " + p);
            c.joinMap(p);
        }
        else if(list.get(1).equals("move")){
            Creature c = Creature.searchByName(list.get(2));
            if(c == null)
                throw new NullPointerException();
            List<String> position = Arrays.asList(list.get(3).split(","));
            int x = Integer.parseInt(position.get(0));
            int y = Integer.parseInt(position.get(1));
            Position p = new Position(x, y);
            //System.out.println("move " + c + " " + p);
            c.move(p);
        }
        else if(list.get(1).equals("remove")) {
            Creature c = Creature.searchByName(list.get(2));
            if(c == null)
                throw new NullPointerException();
            //System.out.println("remove " + c);
            c.getAttacked();
        }else{
            throw new Exception("无法执行的错误文件");
        }

    }
}