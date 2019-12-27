public class BattleGround {
    static Tile[][] ground=new Tile[16][16];
    public static  void main(String[] args)
    {
        BattleGround m=new BattleGround();
        CalabashBrothers Cb[]=new CalabashBrothers[7];
        Sidekicks Sk[]=new Sidekicks[7];
        Grandpa Gp=new Grandpa(7,2,m.ground);
        Snake snake=new Snake(12,7,m.ground);
        Scorpion scorpion=new Scorpion(6,7,m.ground);
        Gp.plantCalabash(Cb,7,m.ground);
        scorpion.summonSidekicks(Sk,7,m.ground);
        m.print();
        scorpion.StartCommand(ground,7,Sk);
        Gp.commandCalabash(m.ground);
        scorpion.T1();
        m.print();
        scorpion.T2();
        m.print();
        scorpion.T3();
        m.print();
        scorpion.T4();
        m.print();
        scorpion.T5();
        m.print();
        scorpion.T6();
        m.print();
        scorpion.T7();
        m.print();
        scorpion.T8();
        m.print();
    }
    public BattleGround()
    {
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                ground[i][j]=new Tile();
            }

        }
    }
    public void print()
    {
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                if(ground[i][j].GetIsOccupied()==true)
                {
                    System.out.print("@");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
