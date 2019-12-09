package myorganism;
import java.util.*;
public class Huluwa extends Organism{
   // protected static Random rand = new Random();
    protected String rank;
    protected String color;
    protected int num;
    public Huluwa(int x,int y) {
        super(x,y);type=Species.HULUWA;
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
    public Red(int x,int y ){
        super(x,y);
        rank="老大";
        color="红";
        num=1;
    }
}
class Orange extends Huluwa{
    public Orange(int x,int y ){
        super(x,y);

        rank="老二";
        color="橙";
        num=2;
    }
}
class Yellow extends Huluwa{
    public Yellow(int x,int y ){
        super(x,y);
        rank="老三";
        color="黄";
        num=3;

    }
    public void test(){
        System.out.println("ok\n");
    }
}
class Green extends Huluwa{
    public Green(int x,int y ){
        super(x,y);
        rank="老四";
        color="绿";
        num=4;

    }
}
class Indigo extends Huluwa{
    public Indigo(int x,int y ){
        super(x,y);
        rank="老五";
        color="青";
        num=5;

    }
}
class Blue extends Huluwa{
    public Blue(int x,int y){
        super(x,y);
        rank="老六";
        color="蓝";
        num=6;
    }
}
class Purple extends Huluwa{
    public Purple(int x,int y ){
        super(x,y);
        rank="老七";
        color="紫";
        num=7;

    }
}
