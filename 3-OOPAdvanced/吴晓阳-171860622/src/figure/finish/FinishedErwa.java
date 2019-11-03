package figure.finish;

import imgs.ImageRepository;
import location.Direction;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class FinishedErwa extends FinishedBulletEmitter {
    {
        ((JLabel) this.getComponent()).setIcon(new ImageIcon(ImageRepository.getImage("Erwa", defaultPicDim)));
    }

    public FinishedErwa() {
    }

    public FinishedErwa(int bulletDamage, Direction bulletDirection, int bulletSpeed) {
        super(bulletDamage, bulletDirection, bulletSpeed);
    }

    public FinishedErwa(int bulletDamage, Direction bulletDirection, int bulletSpeed, int bulletInterval, TimeUnit bulletTimeUnit) {
        super(bulletDamage, bulletDirection, bulletSpeed, bulletInterval, bulletTimeUnit);
    }
}
