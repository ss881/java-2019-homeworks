public class Boy extends Creature{
    String color = "";
    int rank = 0;
    Boy(){}
    Boy(String name){
        this.name = name;
    }
    Boy(String name, String color, int rank){
        this.rank = rank;
        this.name = name;
        this.color = color;
        /*switch(rank){
            case 1: name = "老大"; color = "红色"; break;
            case 2: name = "老二"; color = "橙色"; break;
            case 3: name = "老三"; color = "黄色"; break;
            case 4: name = "老四"; color = "绿色"; break;
            case 5: name = "老五"; color = "青色"; break;
            case 6: name = "老六"; color = "蓝色"; break;
            case 7: name = "老七"; color = "紫色"; break;
            default: System.out.println("创建葫芦娃失败");
        }*/
    }
    void setRank(int rank){
        this.rank = rank;
    }
    void setColor(String color){
        this.color = color;
    }
    int tellRank(){ return rank;}
    String tellColor(){ return color;}
}
