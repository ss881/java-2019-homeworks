package cn.mhj.Enum;

public enum FormationType {
  HY(0, "鹤翼"),
  YX(1, "雁行"),
  HE(2, "衡轭"),
  CS(3, "长蛇"),
  YL(4, "鱼鳞"),
  FY(5, "方円"),
  YY(6, "偃月"),
  FS(7, "锋矢");

  public final int formationValue;

  public final String chinese;

  FormationType(int formationValue, String chinese) {
    this.formationValue = formationValue;
    this.chinese = chinese;
  }

  @Override
  public String toString() {
    return this.chinese;
  }
}
