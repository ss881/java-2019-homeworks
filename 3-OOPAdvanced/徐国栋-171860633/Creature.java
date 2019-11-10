
public class Creature { // 生物类，包含人物的基本属性，用到外观类
    private int id; // 生物的全局唯一编号，避免重复
    public String name; // 生物的名称，允许重复
    public BioAppearance apperrance; // 生物的外观，包含颜色
    public Creature() {
        name = new String();
        apperrance = new BioAppearance();
        position=new XPoint2D(0,0);
    }

    public int getId() {
        return id;
    }

    private XPoint2D position;

    public XPoint2D getPosition() {
        XPoint2D result=new XPoint2D(position.getX(),position.getY());
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