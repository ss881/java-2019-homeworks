package nju.sfy.model.creature;

public class Calabash extends Creature {
    private String color;                       //颜色
    private int order;                          //排行

    public Calabash(String name, String color, int order){
        super(name, 100, 40, 1, 1000);
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

