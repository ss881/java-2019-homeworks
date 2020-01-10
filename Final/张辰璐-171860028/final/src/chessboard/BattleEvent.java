package chessboard;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import chessman.creature.Creature;
import chessman.creature.CreatureState;

public class BattleEvent implements Runnable {
    //take responsibility for deciding what will happen when two diff camp creature meet

    Creature cala;
    Creature mons;

    public BattleEvent(Creature cala, Creature mons) {
        this.cala = cala;
        this.mons = mons;
        cala.setState(CreatureState.INBATTLE);
        mons.setState(CreatureState.INBATTLE);
    }

    @Override
    public void run() {
        //System.out.println(cala.getName()+"和"+mons.getName()+"战斗线程开始");
        while (cala.getBlood() > 0 && mons.getBlood() > 0 && !cala.isKilled() && !mons.isKilled()) {
            Random random = new Random();
            int temp = random.nextInt(2);
            // 0: mons attacked，1: hulu attacked
            if (temp == 0) {
                mons.beAttacked(cala.getForce());

            } else {
                cala.beAttacked(mons.getForce());
            }
            try {
                //a event happened and stop here for user to watch
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (cala.getBlood() <= 0) {
            cala.setState(CreatureState.DEAD);
        } else {
            cala.setState(CreatureState.RUNNING);
        }
        if (mons.getBlood() <= 0) {
            mons.setState(CreatureState.DEAD);
        } else {
            mons.setState(CreatureState.RUNNING);
        }
        //System.out.println(cala.getName()+"和"+mons.getName()+"战斗线程退出");
    }

}