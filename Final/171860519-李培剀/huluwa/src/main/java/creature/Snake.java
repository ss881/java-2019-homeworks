package creature;

import ground.Ground;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import trajectory.Trajectory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Snake extends Bad {

    private static Element ele_snake = null;
    static {
        try {
            Document document = new SAXReader().read(new File("src\\main\\resources\\attributes.xml"));
            Element root = document.getRootElement();
            ele_snake = root.element("Creature").element(Snake.class.getSimpleName());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Snake(Ground ground) {
        super(ground, "Snake.png");
        this.setAttributes(ele_snake);
    }

    private void chase() {
        if (ground.getGrandpa().getPosition().getRow() < position.getRow()) {
            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
        }
        else if (ground.getGrandpa().getPosition().getRow() > position.getRow()) {
            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
        }
    }

    @Override
    public void run() {
        while(true) {
            int sleepTime = 200; // ms
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                synchronized (ground) {
                    if (isDead || !ground.isBattling())
                        break;
                    chase();
                    attack();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    @Override
    public void attack() {
        Creature target = ground.getGrandpa();
        if (target == null || target.isDead)
            return;
        Trajectory trajectory = trajectoryFactory.createTrajectory(target, ground.getGrandpa().isDead, ta);
        if (trajectory == null)
            return;
        ground.addTrajectory(trajectory);
    }

    @Override
    public String toString() { return getClass().getSimpleName() + "(" + getPosition().getRow() + "," + getPosition().getCol() + ")"; }
}
