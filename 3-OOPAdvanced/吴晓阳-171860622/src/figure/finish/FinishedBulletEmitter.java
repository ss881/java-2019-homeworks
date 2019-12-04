package figure.finish;

import creator.Creator;
import generators.Generator;
import imgs.ImageRepository;
import item.Emissions.EmissionBullet;
import item.Finished.FinishedBullet;
import item.Item;
import location.Direction;
import generators.SyncGenerator;
import location.LocationUtils;

import javax.swing.*;
import javax.swing.plaf.LabelUI;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/*
* 子弹发射者
* 默认图像是大娃
 */
public class FinishedBulletEmitter extends FinishedFigure{
    protected static Dimension defaultPicDim;{
        defaultPicDim = LocationUtils.virtualDimToPixelDim(new Dimension(50, 50), Toolkit.getDefaultToolkit().getScreenSize());

    }

    {
        JLabel label = new JLabel(new ImageIcon(ImageRepository.getImage("Dawa", defaultPicDim))){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                FinishedBulletEmitter.this.draw(g);
            }
        };
        label.setSize(defaultPicDim);
        this.setComponent(label);
    }

    public FinishedBulletEmitter(){
        super();
    }

    public FinishedBulletEmitter(int bulletDamage, Direction bulletDirection, int bulletSpeed){
        this.bulletDamage = bulletDamage;
        this.bulletDirection = bulletDirection;
        this.bulletSpeed = bulletSpeed;
    }

    public FinishedBulletEmitter(int bulletDamage, Direction bulletDirection, int bulletSpeed, int bulletInterval, TimeUnit bulletTimeUnit){
        this.bulletDamage = bulletDamage;
        this.bulletDirection = bulletDirection;
        this.bulletSpeed = bulletSpeed;
        this.bulletInterval = bulletInterval;
        this.bulletTimeUnit = bulletTimeUnit;
    }

    protected int bulletDamage = FinishedBullet.DEFAULT_DAMAGE;

    protected Direction bulletDirection = null;

    /*
    * 虚拟像素速度
     */
    protected int bulletSpeed = FinishedBullet.DEFAULT_SPEED;

    protected int bulletInterval = 500;

    protected TimeUnit bulletTimeUnit = TimeUnit.MILLISECONDS;

    public Generator bulletEmitter = new SyncGenerator(new BulletCreator(), bulletInterval, bulletTimeUnit);

    @Override
    public boolean isReadyToEmit() {
        return bulletEmitter.isReady();
    }

    @Override
    public Item emit() {
        return (Item) bulletEmitter.fetch();
    }

    /*
    * 创造器，用于生成器
     */
    class BulletCreator implements Creator<MyEmissionBullet>{
        @Override
        public MyEmissionBullet create(Object... args) {
            return new MyEmissionBullet();
        }
    }

    /*
    * 用于creator创建子弹，必须有一个空参数构造器
     */
    public class MyEmissionBullet extends EmissionBullet {
        public MyEmissionBullet(){
            super(FinishedBulletEmitter.this, bulletDamage, bulletDirection, bulletSpeed);
        }

        @Override
        protected boolean interactTypeCheck(Item item) {
            return super.interactTypeCheck(item) && item != getEmitter();
        }
    }
}
