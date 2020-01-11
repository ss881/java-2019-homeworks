public class Plot {
    private Unit unit;
    private int terrain;//地形属性，待实现
    Plot() { unit = null; }
    public void setUnit(Unit unit) { this.unit = unit; }
    public Unit getUnit() { return unit; }
    public boolean isEmpty() { return (unit==null); }
}
