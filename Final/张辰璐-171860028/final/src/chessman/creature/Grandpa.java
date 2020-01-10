package chessman.creature;

import java.util.concurrent.TimeUnit;

import javafx.scene.image.Image;

public class Grandpa extends Justice {

    public Grandpa() {
        image = new Image("yeye.png");
        name = "爷爷";

        fullBlood = 20;
        Force = 4;
        Blood = fullBlood;
        Speed = 500;
    }

    @Override
    public void run() {
        System.out.println(getName()+"线程开始");
        int step = 0;
        while(!isKilled) {
            synchronized (field) {
                if (state == CreatureState.RUNNING) {
                    if (field.existBadCreature(position.getX(), position.getY()+1)) {
                        Creature monster = field.getCreature(position.getX(), position.getY()+1);
                        if (monster.getState() == CreatureState.RUNNING ){
                            field.createBattleEvent(this, monster);
                        } else{
                            setCreatureOnNextPosition(getNextPosition());
                            step++;
                        }
                    } else {
                        setCreatureOnNextPosition(getNextPosition());
                        step++;
                    }
                }
            }
            switch (state) {
                case DEAD:
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        synchronized (field) {
                            field.clearCreature(position.getX(), position.getX());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isKilled = true;
                    break;
                case RUNNING:
                    // todo:every 7 steps cure hulu
                    if (step%7 == 0) {
                        //cure();
                    }
                    break;
                default:
                    break;
            }
            try {
                //different from huluwa
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName()+"线程退出");
    }
}