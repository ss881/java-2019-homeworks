package Record;

import annotation.Description;
import gamectrl.MainConfig;
import gamectrl.MainControl;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Description(comment = "记录播放类。因无法直接在键盘事件里调用gc.drawXXX函数，故将记录播放实现为独立线程")
public class RecordPlayer implements Runnable, MainConfig {
    private ArrayList<ArrayList<Record>> records;
    GraphicsContext gc;
    MainControl mainControl;
    public RecordPlayer(ArrayList<ArrayList<Record>>_records, GraphicsContext _gc,MainControl _mainControl){
        records=_records;
        gc=_gc;
        mainControl=_mainControl;
    }
    @Override
    public void run() {
        for(int i=0;i<records.size();i++){
            try {
                TimeUnit.MILLISECONDS.sleep(MainConfig.UI_REFRESH_INTERVAL);
                gc.drawImage(mainControl.background, 0, 0, mainControl.col*mainControl.step,mainControl.row*mainControl.step);
                ArrayList<Record> group= records.get(i);
                for(int j=0;j<group.size();j++){
                    Record re = group.get(j);
                    //if(re.getImage()!=null)
                    //    System.out.println("come to i = "+i);
                    gc.drawImage(re.getImage(),re.x*mainControl.step,re.y*mainControl.step,mainControl.step,mainControl.step);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<Record> group= records.get(records.size()-1);
        for(int i=0;i<group.size();i++){
            if(group.get(i).isAlive()){
                Record re=group.get(i);
                gc.drawImage(re.getImage(),re.x*mainControl.step,re.y*mainControl.step,mainControl.step,mainControl.step);
            }
        }
        for(int i=0;i<group.size();i++){
            if(group.get(i).isAlive()){
                if(group.get(i).isEvil()){
                    gc.drawImage(mainControl.loloWinImg, 0, 0, mainControl.col*mainControl.step,mainControl.row*mainControl.step);
                }else{
                    gc.drawImage(mainControl.huluwaWinImg, 0, 0, mainControl.col*mainControl.step,mainControl.row*mainControl.step);
                }
                break;
            }
        }
        mainControl.isReplaying=false;
    }
}
