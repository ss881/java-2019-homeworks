package calabashbrother;

class Camp<T extends Leader<E>, E extends Unit> {
  public enum Formation {
    HY(1, "鹤翼"),
    YX(2, "雁行"),
    HE(3, "衡轭"),
    CS(4, "长蛇"),
    YL(5, "鱼鳞"),
    FY(6, "方円"),
    YY(7, "偃月"),
    FS(8, "锋矢");

    public final int formationValue;

    public final String chinese;

    Formation(int formationValue, String chinese) {
      this.formationValue = formationValue;
      this.chinese = chinese;
    }

    @Override
    public String toString() {
      return this.chinese;
    }
  }

  static class Range {
    int xMin, xMax, yMin, yMax;

    Range(int xMin, int xMax, int yMin, int yMax) {
      this.xMin = xMin;
      this.xMax = xMax;
      this.yMin = yMin;
      this.yMax = yMax;
    }
  }

  T leader;
  Soldiers<E> soldiers;
  private Range range;

  Camp(T leader, Soldiers<E> soldiers, Range range) {
    this.leader = leader;
    this.soldiers = soldiers;
    this.range = range;
  }

  boolean lineUp() {
    soldiers.lineUp();
    return soldiers.formationIsReady;
  }

  void changeFormation(Formation formation) {
    leader.orderFormation(formation, this.soldiers, this.range);
  }
}
