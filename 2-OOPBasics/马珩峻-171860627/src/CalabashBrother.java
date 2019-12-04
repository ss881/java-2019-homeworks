import java.util.Collections;
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

  private class CalabashBoy {
    private Color color;
    private String name;

    private CalabashBoy(Color color) {
      this.color = color;
      switch (color) {
        case RED:
          this.name = "老大";
          break;
        case ORANGE:
          this.name = "老二";
          break;
        case YELLOW:
          this.name = "老三";
          break;
        case GREEN:
          this.name = "老四";
          break;
        case CYAN:
          this.name = "老五";
          break;
        case BLUE:
          this.name = "老六";
          break;
        case PURPLE:
          this.name = "老七";
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
  }

  private Vector<CalabashBoy> calabashBoys = new Vector<CalabashBoy>();

  public CalabashBrother() {
    this.calabashBoys.addElement(new CalabashBoy(Color.RED));
    this.calabashBoys.addElement(new CalabashBoy(Color.ORANGE));
    this.calabashBoys.addElement(new CalabashBoy(Color.YELLOW));
    this.calabashBoys.addElement(new CalabashBoy(Color.GREEN));
    this.calabashBoys.addElement(new CalabashBoy(Color.CYAN));
    this.calabashBoys.addElement(new CalabashBoy(Color.BLUE));
    this.calabashBoys.addElement(new CalabashBoy(Color.PURPLE));
  }

  public void shuffle() {
    Collections.shuffle(this.calabashBoys);
  }

  public void bubbleSort() {
    // 冒泡
    for (int i = 0; i < calabashBoys.size(); i++) {
      for (int j = 0; j < calabashBoys.size() - i - 1; j++) {
        if (calabashBoys.get(j).getNum() > calabashBoys.get(j + 1).getNum()) {
          System.out.println(calabashBoys.get(j).getName() + ":" + j + "->" + (j + 1));
          swap(calabashBoys.get(j), calabashBoys.get(j + 1));
        }
      }
    }

    // 报数
    for (CalabashBoy i : calabashBoys) {
      i.numberOff();
    }
  }

  // 因为并没有什么官方的二分法排序定义，个人对二分排序的理解就是归并或者快排，所以在这里使用了快排
  public void binarySort() {
    if (calabashBoys.isEmpty()) return;
    quickSort(0, calabashBoys.size() - 1);

    for (CalabashBoy i : calabashBoys) {
      i.colorOff();
    }
  }

  private void quickSort(int left, int right) {
    if (right < left) return;

    CalabashBoy pivot = calabashBoys.get(right);
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (calabashBoys.get(j).color.colorValue < pivot.color.colorValue) {
        i++;
        if (i == j) continue;
        System.out.println(calabashBoys.get(i).getName() + ":" + i + "->" + j);
        swap(calabashBoys.get(i), calabashBoys.get(j));
      }
    }

    int q = i + 1;
    if (q != right) {
      System.out.println(calabashBoys.get(q).getName() + ":" + q + "->" + right);
      swap(calabashBoys.get(q), calabashBoys.get(right));
    }

    quickSort(left, q - 1);
    quickSort(q + 1, right);
  }

  private void swap(CalabashBoy src, CalabashBoy dst) {
    CalabashBoy temp = new CalabashBoy(src.color);
    src.set(dst);
    dst.set(temp);
  }

  // for test
  public void printColor() {
    int pos = 0;
    for (CalabashBoy i : calabashBoys) {
      System.out.println("第" + pos + "个兄弟是" + i.getName() + "，颜色是" + i.getColor());
      pos++;
    }
  }
}
