public class CalabashBrother extends Unit{
    private String name;
    private String color;
    private int seq;
    CalabashBrother(String name, String color, int seq) {
        this.name = name;
        this.color = color;
        this.seq = seq;
    }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getSeq() {return seq; }
}