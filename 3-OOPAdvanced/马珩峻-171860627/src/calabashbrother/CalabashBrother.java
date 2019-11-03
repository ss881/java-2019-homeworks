package calabashbrother;

import java.util.Vector;

public class CalabashBrother {

  private enum Color {
    RED(1, "红色"),
    ORANGE(2, "橙色"),
    YELLOW(3, "黄色"),
    GREEN(4, "绿色"),
    CYAN(5, "青色"),
    BLUE(6, "蓝色"),
    PURPLE(7, "紫色");

    public final int colorValue;

    public final String chinese;

    Color(int colorValue, String chinese) {
      this.colorValue = colorValue;
      this.chinese = chinese;
    }

    @Override
    public String toString() {
      return this.chinese;
    }
  }

  public class CalabashBoy extends Unit {
    private Color color;
    private String name;
    private char symbol;

    private CalabashBoy(Color color, Position position) {
      super(position, UnitType.CALABASHBOY);
      this.color = color;
      switch (color) {
        case RED:
          this.name = "老大";
          this.symbol = 'R';
          break;
        case ORANGE:
          this.name = "老二";
          this.symbol = 'O';
          break;
        case YELLOW:
          this.name = "老三";
          this.symbol = 'Y';
          break;
        case GREEN:
          this.name = "老四";
          this.symbol = 'G';
          break;
        case CYAN:
          this.name = "老五";
          this.symbol = 'C';
          break;
        case BLUE:
          this.name = "老六";
          this.symbol = 'B';
          break;
        case PURPLE:
          this.name = "老七";
          this.symbol = 'P';
          break;
      }
    }

    public void set(CalabashBoy src) {
      if (src == this) {
        return;
      }
      this.name = src.name;
      this.color = src.color;
    }

    public String getName() {
      return this.name;
    }

    public String getColor() {
      return this.color.toString();
    }

    public int getNum() {
      return this.color.colorValue;
    }

    // 报自己的排名
    public void numberOff() {
      System.out.println(this.name);
    }

    // 报自己的颜色
    public void colorOff() {
      System.out.println(this.color.toString());
    }

    public void printOnMap() {
      System.out.printf("%c", this.symbol);
    }
  }

  private final int CALABASBOYNUMBER = 7;
  Vector<CalabashBoy> calabashBoys = new Vector<CalabashBoy>();

  public CalabashBrother() {
    //    this.calabashBoys.addElement(new CalabashBoy(Color.values()[0], new Position(1, 0)));
    //    this.calabashBoys.addElement(new CalabashBoy(Color.ORANGE));
    //    this.calabashBoys.addElement(new CalabashBoy(Color.YELLOW));
    //    this.calabashBoys.addElement(new CalabashBoy(Color.GREEN));
    //    this.calabashBoys.addElement(new CalabashBoy(Color.CYAN));
    //    this.calabashBoys.addElement(new CalabashBoy(Color.BLUE));
    //    this.calabashBoys.addElement(new CalabashBoy(Color.PURPLE));

    for (int i = 0; i < CALABASBOYNUMBER; i++) {
      this.calabashBoys.addElement(new CalabashBoy(Color.values()[i], new Position(i + 1, 0)));
    }
  }
}
