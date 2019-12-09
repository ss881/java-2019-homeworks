public class Gourd extends Creature{
    String[] name_list = {"红娃","橙娃","黄娃","绿娃","蓝娃","青娃","紫娃"};  //葫芦娃姓名列表
    int index;                                                            //葫芦娃序号

    //初始化
    Gourd(int i){
        this.index = i;
        this.name = name_list[i];
    }
}
