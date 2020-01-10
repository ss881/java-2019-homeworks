package creature;

import ground.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Grandpa extends Good {

    private static Element ele_grandpa = null;
    static {
        try {
            Document document = new SAXReader().read(new File("src\\main\\resources\\attributes.xml"));
            Element root = document.getRootElement();
            ele_grandpa = root.element("Creature").element(Grandpa.class.getSimpleName());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Grandpa(Ground ground) {
        super(ground, "Grandpa.png");
        this.setAttributes(ele_grandpa);
    }

    private void avoid() {
        if (ground.getGrandpa().getPosition().getRow() < position.getRow()) {
            if (!ground.isOccupied(position.getRow() + 1, position.getCol()))
                ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
            else
                ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
        }
        else if (ground.getGrandpa().getPosition().getRow() > position.getRow()) {
            if (!ground.isOccupied(position.getRow() - 1, position.getCol()))
                ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
            else
                ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
        }
        else {
            int direct = (int) (Math.random() * 2);
            if (direct == 0) {
                if (!ground.isOccupied(position.getRow() + 1, position.getCol()))
                    ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                else
                    ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
            }
            else {
                if (!ground.isOccupied(position.getRow() - 1, position.getCol()))
                    ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                else
                    ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
            }
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
                    avoid();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    @Override
    public void attack() { }

    @Override
    public void setAttributes(Element ele_thisClass) { }

    @Override
    public String toString() { return getClass().getSimpleName() + "(" + getPosition().getRow() + "," + getPosition().getCol() + ")"; }
}
