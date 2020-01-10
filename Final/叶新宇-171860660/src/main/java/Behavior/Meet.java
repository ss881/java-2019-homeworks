package Behavior;

import Item.Creature;
import Item.CalabashCrew;
import Item.MonsterCrew;
import Property.Identity;

public class Meet {
    private String result;
    public Meet(Creature creature1, Creature creature2) {
        StringBuilder stringBuilder = new StringBuilder("");
        int attack1 = creature1.getAttack();
        int attack2 = creature2.getAttack();
        int defense1 = creature1.getDenfense();
        int defense2 = creature2.getDenfense();
        int hp1 = creature1.getHp();
        int hp2 = creature2.getHp();
        if(hp1 <= 0 || hp2 <= 0) {
            result="";
            return;
        }
        int harm2 = attack1 - defense2;
        int harm1 = attack2 - defense1;

        if(harm1 <= 0)
            harm1 = 1;

        if(harm2 <= 0)
            harm2 = 1;

        while(hp1 > 0 && hp2 > 0) {
            hp2 -= harm2;
            if(hp2 <= 0) break;
            hp1 -= harm1;
        }
        if(hp1 <= 0) {
            stringBuilder.append(creature2 + "战胜了" + creature1 + "\n");
            creature1.die();
            creature2.setHp(hp2);
            if(creature1.getId() == Identity.CALABASH)
                CalabashCrew.die();
            else
                MonsterCrew.die();
        }
        else {
            stringBuilder.append(creature1 + "战胜了" + creature2 + "\n");
            creature2.die();
            creature1.setHp(hp1);
            if(creature2.getId() == Identity.CALABASH)
                CalabashCrew.die();
            else
                MonsterCrew.die();
        }
        result = stringBuilder.toString();
    }

    public String getResult() {
        return result;
    }
}
