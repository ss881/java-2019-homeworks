package creature;

import ground.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Scorpion extends Bad {

    private static Element ele_scorpion = null;
    static {
        try {
            Document document = new SAXReader().read(new File("src\\main\\resources\\attributes.xml"));
            Element root = document.getRootElement();
            ele_scorpion = root.element("Creature").element(Scorpion.class.getSimpleName());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Scorpion(Ground ground) {
        super(ground, "Scorpion.png");
        this.setAttributes(ele_scorpion);
    }

    @Override
    public String toString() { return getClass().getSimpleName() + "(" + getPosition().getRow() + "," + getPosition().getCol() + ")"; }
}
