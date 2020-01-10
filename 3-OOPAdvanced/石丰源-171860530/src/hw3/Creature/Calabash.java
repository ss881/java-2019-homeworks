package hw3.Creature;

public class Calabash extends Creature {
    private String color;                       //颜色
    private int order;                          //排行

    public Calabash(String name, String color, int order){
        this.name = name;
        this.color = color;
        this.order = order;
    }

    public int getOrder(){
        return order;
    }

    public String getColor(){
        return color;
    }
}
