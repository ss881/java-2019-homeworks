package cn.edu.nju.huluwa.record;


import cn.edu.nju.huluwa.bullet.Bullet;
import cn.edu.nju.huluwa.creature.Creature;

public class RecordFactory {
    private RecordFactory() {

    }

    public static Record newCreateRecord(Creature creature) {
        Record record = newRecord(creature);
        record.setType(Record.RecordType.CREATE);
        return record;
    }

    public static Record newMoveRecord(Creature creature) {
        Record record = newRecord(creature);
        record.setType(Record.RecordType.MOVE);
        return record;
    }

    public static Record newHurtRecord(Creature creature) {
        Record record = newRecord(creature);
        record.setType(Record.RecordType.HURT);
        return record;
    }

    public static Record newDeadRecord(Creature creature) {
        Record record = newRecord(creature);
        record.setType(Record.RecordType.DEAD);
        return record;
    }

    public static Record newUpgradeRecord(Creature creature) {
        Record record = newRecord(creature);
        record.setType(Record.RecordType.UPGRADE);
        return record;
    }

    public static Record newBulletCreateRecord(Bullet bullet) {
        Record record = newBulletRecord(bullet);
        record.setType(Record.RecordType.BULLET_CREATE);
        return record;
    }
    public static Record newBulletMoveRecord(Bullet bullet) {
        Record record = newBulletRecord(bullet);
        record.setType(Record.RecordType.BULLET_MOVE);
        return record;
    }
    public static Record newBulletRemoveRecord(Bullet bullet) {
        Record record = newBulletRecord(bullet);
        record.setType(Record.RecordType.BULLET_REMOVE);
        return record;
    }


    private static Record newRecord(Creature creature) {
        Record record = new Record();
        record.setId(creature.getId());
        record.setDamage(creature.getDamage());
        record.setHealth(creature.getHealth());
        record.setX(creature.getPosition() == null ? -1 : creature.getPosition().getX());
        record.setY(creature.getPosition() == null ? -1 : creature.getPosition().getY());
        record.setState(creature.getState());
        return record;
    }
    private static Record newBulletRecord(Bullet bullet) {
        Record record = new Record();
        record.setId(bullet.getId());
        record.setColour(bullet.getColor());
        record.setX(bullet.getX());
        record.setY(bullet.getY());
        return record;
    }
}
