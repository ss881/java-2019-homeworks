package record;

import creature.HuluBros;
import creature.Monsters;
import war.BattleField;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recorder implements Runnable{

    private FileWriter writer;
    private StringBuilder stringBuilder;

    private static class Singleton{
        private static Recorder recorder = new Recorder();
    }

    public static Recorder getInstance(){
        return Singleton.recorder;
    }

    private Recorder(){
        stringBuilder = new StringBuilder();
    }


    public void writeToFile(){
        synchronized(Recorder.class){
            if(stringBuilder.length() == 0)
                return;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String curTime = sdf.format(new Date()).substring(4);
                File rcdFile = new File("record"+'_'+curTime+".rcd");
                rcdFile.createNewFile();
                writer = new FileWriter(rcdFile);
                writer.write(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.length());
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    if(writer != null)
                        writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        //每隔0.5秒记录viewList:
        while (BattleField.getInstance().isPlay()) {
            /*synchronized (BattleField.getInstance().creatureViewList) {
                ArrayList<ImageView> creatureViewList = BattleField.getInstance().creatureViewList;
                for (int i = 0; i < creatureViewList.size(); i++) {
                    double x = creatureViewList.get(i).getX();
                    double y = creatureViewList.get(i).getY();
                    stringBuilder.append("view "+x + " " + y + '\n');
                }
            }*/
            //记录HuluBros,Monsters:
            synchronized (BattleField.getInstance().hb) {//根据id找到view
                HuluBros hb = BattleField.getInstance().hb;
                stringBuilder.append(hb.grandPa.id + " " + hb.grandPa.pos.x + ' ' + hb.grandPa.pos.y + ' ' + hb.grandPa.getHealthPoint() + '\n');
                for (int i = 0; i < 7; i++) {
                    stringBuilder.append(hb.hList.get(i).id + " " + hb.hList.get(i).pos.x + ' ' + hb.hList.get(i).pos.y + ' ' + hb.hList.get(i).getHealthPoint() + '\n');
                }
            }
            synchronized (BattleField.getInstance().ms) {
                Monsters ms = BattleField.getInstance().ms;
                stringBuilder.append(ms.ScorpionSpirit.id + " " + ms.ScorpionSpirit.pos.x + ' ' + ms.ScorpionSpirit.pos.y + ' ' + ms.ScorpionSpirit.getHealthPoint() + '\n');
                stringBuilder.append(ms.SnakeSpirit.id + " " + ms.SnakeSpirit.pos.x + ' ' + ms.SnakeSpirit.pos.y + ' ' + ms.SnakeSpirit.getHealthPoint() + '\n');
                for (int i = 0; i < BattleField.getInstance().f.mCount; i++) {
                    stringBuilder.append(ms.mList.get(i).id + " " + ms.mList.get(i).pos.x + ' ' + ms.mList.get(i).pos.y + ' ' + ms.mList.get(i).getHealthPoint() + '\n');
                }
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writeToFile();
    }

}
