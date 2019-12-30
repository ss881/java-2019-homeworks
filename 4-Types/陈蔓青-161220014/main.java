public class main {

    public static void main(String[] args){

        BattleField battle = new BattleField();

        System.out.println("\n鹤翼\n");
        battle.CalabashBroRandom();
        battle.heyi();
        battle.print();

        System.out.println("\n雁行\n");
        battle.CalabashBroRandom();
        battle.yanxing();
        battle.print();

        System.out.println("\n衡轭\n");
        battle.CalabashBroRandom();
        battle.henge();
        battle.print();

        System.out.println("\n鱼鳞\n");
        battle.CalabashBroRandom();
        battle.yulin();
        battle.print();

        System.out.println("\n方円\n");
        battle.CalabashBroRandom();
        battle.fangyuan();
        battle.print();

        System.out.println("\n偃月\n");
        battle.CalabashBroRandom();
        battle.yanyue();
        battle.print();

        System.out.println("\n锋矢\n");
        battle.CalabashBroRandom();
        battle.fengshi();
        battle.print();
    }
}
