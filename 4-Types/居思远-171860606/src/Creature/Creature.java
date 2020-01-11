package Creature;

public class Creature{


    private String name;
    private int id;
    private int state;
    protected boolean alive;
    protected int camp;   //阵营，0为葫芦娃阵营，1为蛇精阵营
    protected boolean rush;     //是否开始就可以移动

    Creature()
    {}

    Creature(int id,String name)
    {
        this.id=id;
        this.name=name;
    }

    protected void setId(int id)
    {this.id=id;}
    protected void setName(String name)
    {this.name=name;}
    protected void setCamp(int camp)
    {this.camp=camp;}

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public String getName()
    {return name;}
    public int getId()
    {return id;}
    public int getState()
    {return state;}
    public boolean getAlive()
    {return alive;}
    public int getCamp()
    {return camp;}


}                           //生物类


