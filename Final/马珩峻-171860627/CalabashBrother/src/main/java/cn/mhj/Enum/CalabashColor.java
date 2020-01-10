package cn.mhj.Enum;

public enum CalabashColor {
  RED(0, "红色"),
  ORANGE(1, "橙色"),
  YELLOW(2, "黄色"),
  GREEN(3, "绿色"),
  CYAN(4, "青色"),
  BLUE(5, "蓝色"),
  PURPLE(6, "紫色");

  public final int colorValue;

  public final String chinese;

  CalabashColor(int colorValue, String chinese) {
    this.colorValue = colorValue;
    this.chinese = chinese;
  }

  @Override
  public String toString() {
    return this.chinese;
  }
}
