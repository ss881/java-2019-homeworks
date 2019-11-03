/*
 * order,color皆以1-7表示，数值相同。设为两个变量，预留扩展可能性。
 */
public class Calabash {
    private int order,color;//排行
    private Position position;

    public Calabash(int order){
        this.order=order;
        this.color=order;
        this.position=new Position(order);
    }

    public String getName(){
        switch (order){
            case 1:return "老大";
            case 2:return "老二";
            case 3:return "老三";
            case 4:return "老四";
            case 5:return "老五";
            case 6:return "老六";
            case 7:return "老七";
            default:return "Invalid order!";
        }
    }

    public String getColorName(){
        switch (color){
            case 1:return "红色";
            case 2:return "橙色";
            case 3:return "黄色";
            case 4:return "绿色";
            case 5:return "青色";
            case 6:return "蓝色";
            case 7:return "紫色";
            default:return "Invalid color!";
        }
    }

    /*
     * 向前和向后只能通过这里来操作，记录当前位置。
     */
    public void moveForward(){
        System.out.print(getName()+":");
        position.moveForward();
    }

    public void moveBackward(){
        System.out.print(getName()+":");
        position.moveBackward();
    }

//    public int currentPosition(){
//        return position.currentPosition();
//    }

    /*
     * 相当于重载小于操作符。按排行比较，当小于时返回true。
     */
    public boolean orderBefore(Calabash c){
        return order<c.order;
    }

    public boolean colorBefore(Calabash c){
        return color<c.color;
    }
}
