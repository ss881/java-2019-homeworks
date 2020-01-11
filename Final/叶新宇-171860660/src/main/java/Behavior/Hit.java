package Behavior;

import Item.Creature;
import Item.Bullet;
import Item.CalabashBoy;
import Item.CalabashCrew;
import Item.MonsterCrew;
import Property.CalabashProperty;
import Property.Identity;
import Property.Position;

public class Hit {
    private String result;
    private Position pos;
    public Hit(Bullet bullet, Creature creature) {
        StringBuilder stringBuilder = new StringBuilder("");
        int attack = bullet.getAttack();
        String shooter = bullet.getShooter();
        pos = new Position(bullet.getPos().getX(), bullet.getPos().getY());
        bullet.setOut();
        int harm = attack - creature.getDenfense();
        if(creature instanceof CalabashBoy)
            if(((CalabashBoy)creature).getColor() == CalabashProperty.BLUE)
                harm = 0;

        if(harm <= 0 || creature.isDead())
            result="";
        else {
            int health = creature.getHp();
            if(harm >= health) {
                creature.die();
                if(creature.getId() == Identity.CALABASH)
                    CalabashCrew.die();
                else
                    MonsterCrew.die();
                stringBuilder.append(shooter + "战胜了" + creature + "\n");
                result = stringBuilder.toString();
            }
            else {
                creature.setHp(health - harm);
                result="";
            }
        }
    }

    public String getResult() {
        return result;
    }

    public Position getPos() {
        return pos;
    }
}

