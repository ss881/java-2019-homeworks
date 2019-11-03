package figure.finish;

import imgs.ImageRepository;
import location.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class FinishedDawa extends FinishedBulletEmitter {
    {
        ((JLabel) this.getComponent()).setIcon(new ImageIcon(ImageRepository.getImage("Dawa", defaultPicDim)));
    }

    public FinishedDawa() {
    }

    public FinishedDawa(int bulletDamage, Direction bulletDirection, int bulletSpeed) {
        super(bulletDamage, bulletDirection, bulletSpeed);
    }

    public FinishedDawa(int bulletDamage, Direction bulletDirection, int bulletSpeed, int bulletInterval, TimeUnit bulletTimeUnit) {
        super(bulletDamage, bulletDirection, bulletSpeed, bulletInterval, bulletTimeUnit);
    }
}
