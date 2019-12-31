package chessman.creature;

import java.util.concurrent.TimeUnit;

//import skill.Shoot;
import chessboard.Position;

import javax.naming.Name;

public abstract class Justice extends Creature {
    //only take responsibility for taking actions
    //DIP

    public Justice(){
        isJustice = true;
        position = new Position();
    }
    @Override
    public void run() {
        System.out.println(getName()+"线程开始");
        //int step = 0;
        while(!isKilled) {
            //only one creature can visit filed each time
            synchronized (field) {
                //take a step forward
                if(state == CreatureState.RUNNING) {
                    // bad creatures ahead, start battle
                    if (field.existBadCreature(position.getX(), position.getY()+1)) {
                        Creature monster = field.getCreature(position.getX(), position.getY()+1);
                        if(monster.getState() == CreatureState.RUNNING ) {
                            //only field can change creature, avoid creature do things to creature
                            field.createBattleEvent(this, monster);
                        } else {
                            //go to nextPos, and clear curPos info
                            setCreatureOnNextPosition(getNextPosition());
                           // step++;
                        }
                    } else {
                        //go ahead directly
                        setCreatureOnNextPosition(getNextPosition());
                        //step++;
                    }
                }
            }
            if (state == CreatureState.DEAD) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (field) {
                        //disappear from the field, not to block others, after dead stay a short time to tell user sb. die
                        field.clearCreature(position.getX(), position.getY());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isKilled = true;
            }
            try {
                //different creature have different speed to go ahead
                TimeUnit.MILLISECONDS.sleep(((Huluwa)this).getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName()+"线程退出");
    }
    @Override
    public String toString(){
        String res= getName()+" blood:"+getBlood()+" force:"+getForce()+" speed:"+getSpeed();
        return "s";
    }
}