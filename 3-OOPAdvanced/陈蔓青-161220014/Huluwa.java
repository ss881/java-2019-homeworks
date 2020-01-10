enum HuluwaNames {大娃, 二娃, 三娃, 四娃, 五娃, 六娃, 七娃}

public class Huluwa extends Creature {

    Huluwa(int i){
        super(HuluwaNames.values()[i].toString(), "葫芦娃");
    }

}
