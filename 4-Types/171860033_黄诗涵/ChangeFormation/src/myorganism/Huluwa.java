package myorganism;
import java.util.*;
public class Huluwa extends Organism{
   // protected static Random rand = new Random();
    protected String rank;
    protected String color;
    protected int num;
    public Huluwa() {
        //type=Species.HULUWA;
    }

    public int getnum()
    {
        return num;
    }
    public String getRank()
    {
        return rank;
    }
    public String getColor()
    {
        return color;
    }
}
class Red extends Huluwa{
    public Red(){
        rank="老大";
        color="红";
        num=1;
    }
}
class Orange extends Huluwa{
    public Orange(){
        rank="老二";
        color="橙";
        num=2;
    }
}
class Yellow extends Huluwa{
    public Yellow(){
        rank="老三";
        color="黄";
        num=3;

    }
}
class Green extends Huluwa{
    public Green(){
        rank="老四";
        color="绿";
        num=4;

    }
}
class Indigo extends Huluwa{
    public Indigo(){
        rank="老五";
        color="青";
        num=5;
    }
}
class Blue extends Huluwa{
    public Blue(){
        rank="老六";
        color="蓝";
        num=6;
    }
}
class Purple extends Huluwa{
    public Purple(){
        rank="老七";
        color="紫";
        num=7;

    }
}
