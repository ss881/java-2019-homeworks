package Property;

public enum CalabashProperty {
    RED("大娃",80,10,500),
    ORANGE("二娃",50,40,400),
    YELLOW("三娃",30,60,500),
    GREEN("四娃",40,40,400),
    CYAN("五娃",40,40,400),
    BLUE("六娃",40,40,400),
    PURPLE("七娃",100,0,400);

    final private String name;
    final private int attack;
    final private int defense;
    final private int maxHP;

    CalabashProperty(String name, int attack, int defense, int maxHP) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.maxHP = maxHP;
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxHP() {
        return maxHP;
    }
}
