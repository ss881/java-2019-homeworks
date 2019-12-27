public class Creature {
    String name;    //生物名称
    int pos_x;      //位置坐标 x
    int pos_y;      //位置坐标 y
//    int hp = 100;   //生命值

    //报告姓名
    public void tellname(){
        System.out.print(name);
    }

    //报告完成命令
    public void report(){
        System.out.print("\t【" + this.name +"】"+ " 完成！\n");
    }

}
