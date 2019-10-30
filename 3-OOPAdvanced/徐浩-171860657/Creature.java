public class Creature
{
    protected String name;      //名字
    protected boolean status;   //此处是否被占 0未被占，1被占
    protected boolean camp;     //阵营 0为蛇精阵营，1为葫芦娃阵营

    public Creature(String name,boolean status,boolean camp)
    {
        this.name=name;
        this.camp=camp;
        this.status=status;
    }
    public Creature()
    {
        this.name="";
        this.camp=false;
        this.status=false;
    }
    public void setCreature(String name,boolean status,boolean camp)
    {
        this.name=name;
        this.status=status;
        this.camp=camp;
    }
    public void setCamp(boolean camp) {
        this.camp = camp;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public boolean getCamp() {
        return camp;
    }
    public boolean getStatus() {
        return status;
    }
}
