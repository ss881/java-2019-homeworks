package Creatures;

enum HuluBro
{
    R("老大","红色"),O("老二","橙色"),Y("老三","黄色"),G("老四","绿色"),C("老五","青色"),B("老六","蓝色"),P("老七","紫色");
    final String name;
    final String color;

    HuluBro(String name,String color)
    {
        this.name = name;
        this.color = color;
    }

}

public class Huluwa extends Creature implements Comparable<Huluwa>
{
    int num;
    String color;
    HuluBro hulubro;
    public Huluwa(int i,int x,int y)
    {
        super(x,y,true);
        hulubro = HuluBro.values()[i];
        this.name=hulubro.name;
        this.color=hulubro.color;
        this.num=hulubro.ordinal();
    }
    
    @Override
    public int compareTo(Huluwa o) 
    {
        return this.num - o.num;
    }

}
