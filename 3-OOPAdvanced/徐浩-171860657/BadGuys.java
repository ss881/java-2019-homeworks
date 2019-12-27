class BadGuy extends Creature
{
    private double hp;      //血量

    public BadGuy()
    {
        super("x",true,false);
        this.hp = 100;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    public double getHp() {
        return hp;
    }
    @Override
    public String getName() {
        return super.getName();
    }
}

public class BadGuys
{
    private BadGuy[] bgs;
    private int num;
    public BadGuys(int n) {
        bgs=new BadGuy[n];
        for (int i = 0; i < n; i++) {
            bgs[i]=new BadGuy();
        }
        num=n;
    }

    public int getNum() {
        return num;
    }

    public BadGuy[] getBadGuys() {
        return bgs;
    }
}