package cn.mhj.Field;

import static java.lang.Math.abs;

public class Range {
  private int xMin, xMax, yMin, yMax;
  private int xStep, yStep;
  private int xMid, yMid;

  public Range(int xMin, int xMax, int yMin, int yMax) {
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
    this.xStep = (this.xMax - this.xMin) / abs((this.xMax - this.xMin));
    this.yStep = (this.yMax - this.yMin) / abs((this.yMax - this.yMin));
    this.xMid = Math.abs(this.xMax - this.xMin) / 2;
    this.yMid = Math.abs(this.yMax - this.yMin) / 2;
  }

  public int getXMin() {
    return this.xMin;
  }

  public int getXMax() {
    return this.xMax;
  }

  public int getYMin() {
    return this.yMin;
  }

  public int getYMax() {
    return this.yMax;
  }

  public int getXStep() {
    return this.xStep;
  }

  public int getYStep() {
    return this.yStep;
  }

  public int getXMid() {
    return this.xMid;
  }

  public int getYMid() {
    return this.yMid;
  }
}
