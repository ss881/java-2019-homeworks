package hulubro.controller;

import hulubro.map.Map;

import java.io.IOException;

public class Check implements Runnable{
    private Map map;
    private Controller controller;
    Check(Map map,Controller controller){
        this.map=map;
        this.controller=controller;
    }
    @Override
    public void run() {
        while (!map.finish){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(controller.checkwinner()==0){
                System.out.println("葫芦娃胜利");
                map.finish =true;
            }else if(controller.checkwinner()==1){
                System.out.println("妖精胜利");
                map.finish =true;
            }
        }
        try {
            map.save(controller.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.started=false;
    }
}
