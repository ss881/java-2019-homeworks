enum CalabashNames {大娃, 二娃, 三娃, 四娃, 五娃, 六娃, 七娃}

public class CalabashBrother extends Creature {

    protected int rank;

    public CalabashBrother(int num){

        rank = num + 1;
        name = CalabashNames.values()[num].toString();
        type = "葫芦娃";

    }

    /*public static void main(String[] args) {

        CalabashBrother calabash = new CalabashBrother(6);
        System.out.println(calabash.getRank() +(String)calabash.getName() + calabash.getColor() + calabash.getType());

    }*/

}
