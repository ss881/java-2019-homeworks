package figure.finish;

import imgs.ImageRepository;
import location.Direction;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class FinishedToad extends FinishedBulletEmitter{
    {
        ((JLabel) this.getComponent()).setIcon(new ImageIcon(ImageRepository.getImage("Toad", defaultPicDim)));
    }

    public FinishedToad() {
    }

    public FinishedToad(int bulletDamage, Direction bulletDirection, int bulletSpeed) {
        super(bulletDamage, bulletDirection, bulletSpeed);
    }

    public FinishedToad(int bulletDamage, Direction bulletDirection, int bulletSpeed, int bulletInterval, TimeUnit bulletTimeUnit) {
        super(bulletDamage, bulletDirection, bulletSpeed, bulletInterval, bulletTimeUnit);
    }
}
