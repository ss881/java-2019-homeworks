package gamectrl;

import creature.Creature;

import java.util.concurrent.TimeUnit;

public class SceneRefresher implements Runnable,MainConfig {
    private MainControl mainControler;
    public SceneRefresher(MainControl controller) {
        mainControler = controller;
    }

    @Override
    public void run() {
        while(true){
            try {
                synchronized (Creature.class) {
                    mainControler.update();
                }
                TimeUnit.MILLISECONDS.sleep(MainConfig.UI_REFRESH_INTERVAL);
                if(mainControler.gameOver){
                    synchronized (Creature.class) {
                        mainControler.update();
                    }
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println("refreshed.");
        }

    }
}
