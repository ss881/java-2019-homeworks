package calabash;

abstract class SortBase {
	int value;

	public SortBase() {
	}

	public SortBase(int value) {
		this.value = value;
	}

	boolean smallerThan(SortBase base) {
		return value < base.value;
	}

	boolean largerThan(SortBase base) {
		return value > base.value;
	}

	@Override
	public String toString() {
		// TODO �Զ����ɵķ������
		return value + "";
	}
}

class Order extends SortBase {
	public Order() {
	}

	public Order(int orderValue) {
		super(orderValue);
	}

	@Override
	public String toString() {
		switch (value) {
		case 0:
			return "�ϴ�";
		case 1:
			return "�϶�";
		case 2:
			return "����";
		case 3:
			return "����";
		case 4:
			return "����";
		case 5:
			return "����";
		case 6:
			return "����";
		default:
			return "����Ǻ�«С���?";
		}
	}
}

class Color extends SortBase {

	public Color() {
	}

	public Color(int colorValue) {
		super(colorValue);
	}

	@Override
	public String toString() {
		switch (value) {
		case 0:
			return "��ɫ";
		case 1:
			return "��ɫ";
		case 2:
			return "��ɫ";
		case 3:
			return "��ɫ";
		case 4:
			return "��ɫ";
		case 5:
			return "��ɫ";
		case 6:
			return "��ɫ";
		default:
			return "������߲ʺ�«��?";
		}
	}
}

public class Calabash {

	private Order order;
	private Color color;

	public Calabash() {
	}

	public Calabash(int num) {
		order = new Order(num);
		color = new Color(num);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public String exPosInfo(int posEx,int pos) {
		return order+":"+posEx+"--->"+pos;
	}
}
