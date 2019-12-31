package creature;

import battle.Bullet;
import battle.Status;
import javafx.scene.image.Image;

import java.util.concurrent.TimeUnit;

public abstract class Good extends Creature{
    public Good(){
        attack = 12;
    }
    private transient static Image bulletImage = new Image("bulletGood.png");
    private transient static Image hitImage= new Image("hitBad.gif");
    public Image getHitImage(){return hitImage;}
    public Image getFlyImage(){return bulletImage;}
    protected void shootThread(){
        new Thread(new Runnable() {
            public void run() {
                while(state==State.LIVE&&ground.whoWin()== Status.RUNNING){
                    synchronized (ground) {
                        Bullet b = new Bullet(Good.this.x, Good.this.y, 1, bulletImage, hitImage, Good.this);
                        ground.addBullet(b);
                        new Thread(b).start();
                    }
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
