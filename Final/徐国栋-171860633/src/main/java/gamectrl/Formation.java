package gamectrl;

import objinfo.XPoint2D;
import java.util.ArrayList;

// 阵型类，定义8个基本阵型
public class Formation {
    private ArrayList<XPoint2D> minimizedExpansion;

    // 返回阵型需要的人数
    public int getSize() {
        return minimizedExpansion.size();
    }

    public XPoint2D getCoordinate(int i) {
        XPoint2D result = new XPoint2D();
        result.setX(getX(i));
        result.setY(getY(i));
        return result;
    }

    // 返回第i个人的横坐标
    public int getX(int i) {
        return minimizedExpansion.get(i).getX();
    }

    // 返回第i个人的纵坐标
    public int getY(int i) {
        return minimizedExpansion.get(i).getY();
    }

    public Formation() {
        minimizedExpansion = null;
    }

    // 加载阵型 1-7:特殊阵型，其它:长蛇阵
    public XPoint2D load(int i) {
        switch (i) {
            case 1:
                return loadFangmen();
            case 2:
                return loadFengshi();
            case 3:
                return loadHengzhi();
            case 4:
                return loadHeyi();
            case 5:
                return loadYanhang();
            case 6:
                return loadYanyue();
            case 7:
                return loadYulin();
            default:
                return loadChangshe();
        }
    }

    // 构造归一化的最小阵型，返回阵型需要的长和宽
    private XPoint2D loadHeyi() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(0, 0));
        minimizedExpansion.add(new XPoint2D(1, 1));
        minimizedExpansion.add(new XPoint2D(2, 2));
        minimizedExpansion.add(new XPoint2D(3, 3));
        minimizedExpansion.add(new XPoint2D(4, 2));
        minimizedExpansion.add(new XPoint2D(5, 1));
        minimizedExpansion.add(new XPoint2D(6, 0));
        return new XPoint2D(7, 4);
    }

    private XPoint2D loadYanhang() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(4, 0));
        minimizedExpansion.add(new XPoint2D(3, 1));
        minimizedExpansion.add(new XPoint2D(2, 2));
        minimizedExpansion.add(new XPoint2D(1, 3));
        minimizedExpansion.add(new XPoint2D(0, 4));
        return new XPoint2D(5, 5);
    }

    private XPoint2D loadHengzhi() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(1, 0));
        minimizedExpansion.add(new XPoint2D(0, 1));
        minimizedExpansion.add(new XPoint2D(1, 2));
        minimizedExpansion.add(new XPoint2D(0, 3));
        minimizedExpansion.add(new XPoint2D(1, 4));
        minimizedExpansion.add(new XPoint2D(0, 5));
        return new XPoint2D(2, 6);
    }

    private XPoint2D loadChangshe() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(0, 0));
        minimizedExpansion.add(new XPoint2D(0, 1));
        minimizedExpansion.add(new XPoint2D(0, 2));
        minimizedExpansion.add(new XPoint2D(0, 3));
        minimizedExpansion.add(new XPoint2D(0, 4));
        minimizedExpansion.add(new XPoint2D(0, 5));
        minimizedExpansion.add(new XPoint2D(0, 6));
        return new XPoint2D(1, 6);
    }

    private XPoint2D loadYulin() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(1, 0));
        minimizedExpansion.add(new XPoint2D(2, 1));
        minimizedExpansion.add(new XPoint2D(1, 2));
        minimizedExpansion.add(new XPoint2D(2, 2));
        minimizedExpansion.add(new XPoint2D(3, 2));
        minimizedExpansion.add(new XPoint2D(0, 3));
        minimizedExpansion.add(new XPoint2D(1, 3));
        minimizedExpansion.add(new XPoint2D(2, 3));
        minimizedExpansion.add(new XPoint2D(3, 3));
        minimizedExpansion.add(new XPoint2D(1, 4));
        return new XPoint2D(4, 5);
    }

    private XPoint2D loadFangmen() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(2, 0));
        minimizedExpansion.add(new XPoint2D(1, 1));
        minimizedExpansion.add(new XPoint2D(3, 1));
        minimizedExpansion.add(new XPoint2D(0, 2));
        minimizedExpansion.add(new XPoint2D(4, 2));
        minimizedExpansion.add(new XPoint2D(1, 3));
        minimizedExpansion.add(new XPoint2D(3, 3));
        minimizedExpansion.add(new XPoint2D(2, 4));
        return new XPoint2D(5, 5);
    }

    private XPoint2D loadYanyue() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(0, 3));
        minimizedExpansion.add(new XPoint2D(0, 4));
        minimizedExpansion.add(new XPoint2D(0, 5));
        minimizedExpansion.add(new XPoint2D(1, 3));
        minimizedExpansion.add(new XPoint2D(1, 4));
        minimizedExpansion.add(new XPoint2D(1, 5));
        minimizedExpansion.add(new XPoint2D(2, 3));
        minimizedExpansion.add(new XPoint2D(2, 4));
        minimizedExpansion.add(new XPoint2D(2, 5));
        minimizedExpansion.add(new XPoint2D(1, 2));
        minimizedExpansion.add(new XPoint2D(2, 2));
        minimizedExpansion.add(new XPoint2D(2, 1));
        minimizedExpansion.add(new XPoint2D(3, 1));
        minimizedExpansion.add(new XPoint2D(4, 0));
        minimizedExpansion.add(new XPoint2D(1, 6));
        minimizedExpansion.add(new XPoint2D(2, 6));
        minimizedExpansion.add(new XPoint2D(2, 7));
        minimizedExpansion.add(new XPoint2D(3, 7));
        minimizedExpansion.add(new XPoint2D(4, 8));
        return new XPoint2D(5, 9);
    }

    private XPoint2D loadFengshi() {
        minimizedExpansion = new ArrayList<XPoint2D>();
        minimizedExpansion.add(new XPoint2D(0, 2));
        minimizedExpansion.add(new XPoint2D(0, 3));
        minimizedExpansion.add(new XPoint2D(1, 1));
        minimizedExpansion.add(new XPoint2D(2, 0));
        minimizedExpansion.add(new XPoint2D(2, 1));
        minimizedExpansion.add(new XPoint2D(2, 2));
        minimizedExpansion.add(new XPoint2D(2, 3));
        minimizedExpansion.add(new XPoint2D(2, 4));
        minimizedExpansion.add(new XPoint2D(2, 5));
        minimizedExpansion.add(new XPoint2D(3, 1));
        minimizedExpansion.add(new XPoint2D(4, 2));
        minimizedExpansion.add(new XPoint2D(4, 3));
        return new XPoint2D(5, 5);
    }

}
