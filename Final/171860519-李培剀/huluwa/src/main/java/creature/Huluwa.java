package creature;

import ground.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Huluwa extends Good{

    private final Color color;

    private static Element ele_huluwa = null;
    static {
        try {
            Document document = new SAXReader().read(new File("src\\main\\resources\\attributes.xml"));
            Element root = document.getRootElement();
            ele_huluwa = root.element("Creature").element(Huluwa.class.getSimpleName());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    Huluwa(Ground ground, Color color) {
        super(ground, "Huluwa" + color.toString() + ".png");
        this.color = color;
        this.setAttributes(ele_huluwa);
    }

    public Color getColor() { return color; }

    @Override
    public String toString() { return getClass().getSimpleName() + ":" + color + "(" + getPosition().getRow() + "," + getPosition().getCol() + ")"; }
}
