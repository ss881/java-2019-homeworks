
package types.src;
public enum Color {
    红色("老大", 1),橙色("老二", 2),黄色("老三", 3),绿色("老四", 4), 青色("老五", 5), 蓝色("老六", 6), 紫色("老七", 7);
    private String name;
    private int index;
    
    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public String getColorName() {
        return name;
    }
    public int getIndex() {
        return index;
    }

}