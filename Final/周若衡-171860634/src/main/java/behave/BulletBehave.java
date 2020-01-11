package behave;

import creature.Bullet;

public class BulletBehave extends Behave {
    private Bullet bullet;

    public BulletBehave(int sleepTime, Bullet bullet) {
        super(sleepTime);
        this.bullet=(Bullet)bullet.clone();
    }

    @Override
    public Object clone() {
        BulletBehave temp = null;
        temp = (BulletBehave)super.clone();
        temp.bullet = (Bullet) bullet.clone();
        return temp;
    }
    public Bullet getBullet(){
        return bullet;
    }
}
