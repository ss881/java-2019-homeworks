package organism;

import position.Position;

public class Huluwa extends  Organism{

    private int rank;
    private int color;


    public  Huluwa(int rank)
    {
        this.rank=rank;
        this.color=rank;
        this.name=sayRank(this);
    }

    public Huluwa(int rank,int x,int y)
    {
        super(x,y);
        this.rank=rank;
        this.color=rank;
        this.name=sayRank(this);
    }

    public Huluwa(Huluwa temp)
    {
        super(temp);
        this.rank=temp.rank;
        this.color=temp.color;
    }

    public void moveto(int i,int j)
    {
        int x=this.getPosition().getX();
        int y=this.getPosition().getY();
        if(x!=-1) System.out.println(sayRank(this)+":从("+x+","+y+")移动到("+i+","+j+")");
        else System.out.println(sayRank(this)+":从移动到("+i+","+j+")");

        setPosition(new Position(i,j));
    }

    public int getRank(){return rank;}

    public int getColor(){return color;}

    private String sayRank(Huluwa brother)
    {
        int rank=brother.getRank();
        switch(rank)
        {
            case 1: return "老大";
            case 2: return "老二";
            case 3: return "老三";
            case 4: return "老四";
            case 5: return "老五";
            case 6: return "老六";
            case 7: return "老七";
        }

        return null;
    }

    private String sayColor(Huluwa brother)
    {
        int color=brother.getColor();
        switch(color)
        {
            case 1: return "红色";
            case 2: return "橙色";
            case 3: return "黄色";
            case 4: return "绿色";
            case 5: return "青色";
            case 6: return "蓝色";
            case 7: return "紫色";
        }

        return null;
    }
}
