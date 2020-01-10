package creature;

import objinfo.XPoint2D;
import objinfo.BioAppearance;

public class Creature { // 生物类，包含人物的基本属性，用到外观类
    private int id; // 生物的全局唯一编号，避免重复
    private String name = new String(); // 生物的名称，允许重复
    public BioAppearance apperrance = new BioAppearance();; // 生物的外观，包含颜色

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public Creature() {

    }

    public int getId() {
        return id;
    }

    private XPoint2D position = new XPoint2D(0, 0);

    public XPoint2D getPosition() {
        XPoint2D result = new XPoint2D(position.getX(), position.getY());
        return result;
    }

    public void setPosition(XPoint2D _position) {
        position.setX(_position.getX());
        position.setY(_position.getY());
    }

    protected void setId(int _id) {
        id = _id;
    }
}