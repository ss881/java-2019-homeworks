package app;
public class HuLuWaCourt {
    
    public static void main(String[] args) throws Exception {
        System.out.println("H1-H7:大娃-七娃 Y:爷爷 X：蝎子精 L1-L8：小喽啰 S：蛇精，左半边为葫芦娃阵营，右半边为蝎子精蛇精阵营");
        CreatureControl creacon = new CreatureControl();
        creacon.initCreatures();
        creacon.shuffleAll();
        System.out.println("初始两方乱序：");
        creacon.court.prin();
        creacon.sortHuPlace(0);
        creacon.sortOppoPlace(0);
        System.out.println("两方摆阵，爷爷、蝎子精随机分处于左、右边：");
        System.out.println("左方长蛇阵，右方冲轭阵：");
        creacon.court.prin();
        System.out.println("右方换鹤翼阵，蛇精位置再次随机：");
        creacon.sortOppoPlace(1);
        creacon.court.prin();
    }
}



