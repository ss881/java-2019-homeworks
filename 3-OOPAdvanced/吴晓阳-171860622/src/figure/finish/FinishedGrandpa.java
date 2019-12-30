package figure.finish;

import imgs.ImageRepository;
import location.Direction;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class FinishedGrandpa extends FinishedBulletEmitter {
    {
        ((JLabel) this.getComponent()).setIcon(new ImageIcon(ImageRepository.getImage("Grandpa", defaultPicDim)));
    }

    public FinishedGrandpa() {
    }

    public FinishedGrandpa(int bulletDamage, Direction bulletDirection, int bulletSpeed) {
        super(bulletDamage, bulletDirection, bulletSpeed);
    }

    public FinishedGrandpa(int bulletDamage, Direction bulletDirection, int bulletSpeed, int bulletInterval, TimeUnit bulletTimeUnit) {
        super(bulletDamage, bulletDirection, bulletSpeed, bulletInterval, bulletTimeUnit);
    }
}
