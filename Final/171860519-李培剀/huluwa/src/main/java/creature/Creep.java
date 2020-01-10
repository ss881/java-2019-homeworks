package creature;

import ground.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Creep extends Bad {

    private static int index = 0;
    private int id;

    private static Element ele_creep = null;
    static {
        try {
            Document document = new SAXReader().read(new File("src\\main\\resources\\attributes.xml"));
            Element root = document.getRootElement();
            ele_creep = root.element("Creature").element(Creep.class.getSimpleName());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Creep(Ground ground) {
        super(ground, "Creep.png");
        this.id = index++;
        this.setAttributes(ele_creep);
    }

    public int getId() { return id; }

    @Override
    public String toString() { return getClass().getSimpleName() + ":" + id + "(" + getPosition().getRow() + "," + getPosition().getCol() + ")"; }
}
