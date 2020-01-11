package creature;

public enum Color {
    red("老大","红色"),
    orange("老二", "橙色"),
    yellow("老三", "黄色"),
    green("老四", "绿色"),
    cyan("老五", "青色"),
    blue("老六", "蓝色"),
    purple("老七", "紫色");

    private final String AgeCN;
    private final String ColorCN;

    Color(String AgeCN, String ColorCN) { this.AgeCN = AgeCN; this.ColorCN = ColorCN; }

    public String getAgeCN() { return AgeCN; }

    public String getColorCN() { return ColorCN; }
}