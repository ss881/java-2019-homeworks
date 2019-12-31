package battle;

import config.Config;
import creature.Creature;
import creature.Good;
import creature.State;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import team.BadTeam;
import team.GoodTeam;
import team.Team;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Bullet implements Runnable, Serializable {
    private transient int direction;  // 1 -1
    private int x, y;
    enum Status{flying, hit, over}
    private Status status;
    private transient Image flyImage;
    private transient Image hitImage;
    private Creature from;
    private static transient final Ground ground = Ground.getInstance();
    private static transient GoodTeam goodTeam = GoodTeam.getInstance();
    private static transient BadTeam badTeam = BadTeam.getInstance();
    public void copy(Bullet b){
        this.x = b.x;
        this.y = b.y;
        this.status = b.status;
        this.from = b.from;
    }
    public Bullet(){}
    public Bullet(int x, int y, int direction, Image flyImage, Image hitImage, Creature from){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.flyImage = flyImage;
        this.hitImage = hitImage;
        this.status = Status.flying;
        this.from = from;
    }
    public void run(){
        while(status!= Status.over){
            synchronized (ground){
                if(status==Status.hit) {
                    status = Status.over;
                    break;
                }
                this.x+= direction;
                if(!ground.inGround(this.x, this.y)){
                    status = Status.over;
                    break;
                }
                Team t;
                if(from instanceof Good)t = badTeam;
                else t = goodTeam;
                for(Creature c: t.getTeamMembers()){
                    if(c.getState()==State.LIVE&&c.getX() == x&&c.getY()==y){
                        c.beAttacked(from);
                        status = Status.hit;
                    }
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        synchronized (ground) {
            ground.removeBullet(this);
        }
    }


    public void draw(GraphicsContext gc){
        gc.drawImage(getImage(), x* Config.IMAGEWIDTH, y*Config.IMAGEHEIGHT, Config.IMAGEWIDTH, Config.IMAGEHEIGHT);
    }
    private Image getImage(){
        Image ret ;
        if(flyImage==null)flyImage = from.getFlyImage();
        if(hitImage==null)hitImage = from.getHitImage();
        if(status==Status.flying)
            ret = flyImage;
        else if(status== Status.hit)
            ret = hitImage;
        else ret = null;
        return ret;
    }
    public void setOver(){
        this.status = Status.over;
    }

}
