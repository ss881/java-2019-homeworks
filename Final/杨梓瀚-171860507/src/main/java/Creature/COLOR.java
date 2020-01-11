package Creature;

public enum COLOR {
    RED("老大","红"), ORANGE("老二", "橙"), YELLOW("老三","黄"), GREEN("老四", "绿"), CYAN("老五", "靛"), BLUE("老六", "蓝"), PURPLE("老七", "紫");

    private String name;

    private String color;

    private COLOR(String name, String color){
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return name + "(" + color + ")";
    }

}
