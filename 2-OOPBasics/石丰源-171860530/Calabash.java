class Calabash{
    private String name;                        //名字
    private String color;                       //颜色
    private int order;                          //排行
    private int position;                       //在队伍中的位置

    public Calabash(String name, String color, int order){
        this.name = name;
        this.color = color;
        this.order = order;
    }

    public int getOrder(){
        return order;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void numberOffName(){        //按名字报数
        System.out.print(name + " ");
    }

    public void numberOffColor(){       //按颜色报数
        System.out.print(color + " ");
    }

    public void move(int dest){         //移动位置
        System.out.println(name + ": " + Integer.toString(position) + "->" + Integer.toString(dest));
        position = dest;
    }
};