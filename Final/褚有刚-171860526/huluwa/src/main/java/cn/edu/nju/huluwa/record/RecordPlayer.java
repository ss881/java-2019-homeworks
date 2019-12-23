package cn.edu.nju.huluwa.record;


import cn.edu.nju.huluwa.bullet.BulletFactory;
import cn.edu.nju.huluwa.bullet.BulletManager;
import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;
import cn.edu.nju.huluwa.huluworld.HuluWorld;

import java.util.List;

public class RecordPlayer implements Runnable {
    //    private List<Creature> creatures;
    private HuluWorld huluWorld;
    private List<Record> records;

    public RecordPlayer(HuluWorld huluWorld, List<Record> records) {
        this.huluWorld = huluWorld;
        this.records = records;
    }

    @Override
    public void run() {
        Position[][] positions = huluWorld.getPositionManager().getPositions();
        BulletManager bulletManager = huluWorld.getBulletManager();
        for (Record record : records) {
            Creature creature = getCreature(record.getId());
            assert(creature != null);
            switch (record.type) {
                case CREATE:
                    creature.setState(record.getState());
                    creature.setDamage(record.getDamage());
                    creature.setHealth(record.getHealth());
                    creature.setPosition(record.getX() < 0 || record.getY() < 0
                            ? null : positions[record.getX()][record.getY()]);
                    break;
                case MOVE:
                    creature.setPosition(positions[record.getX()][record.getY()]);
                    break;
                case HURT:
                    creature.setHealth(record.getHealth());
                    break;
                case DEAD:
                    // TODO: show dead image
                    break;
                case BULLET_CREATE:
                    huluWorld.getBulletManager().addBullet(BulletFactory.newBullet(record));
                    break;
                case BULLET_MOVE:
                    huluWorld.getBulletManager().move(record.getId(), record.getX(), record.getY());
                    break;
                case BULLET_REMOVE:
                    huluWorld.getBulletManager().removeById(record.getId());
                    break;
            }
//            System.out.println(record);
            if(record.type != Record.RecordType.CREATE) huluWorld.getScene().repaint();
            if(record.getTimeMillis() == 0) continue;
            try {
                Thread.sleep(record.getTimeMillis());
            } catch (InterruptedException e) {
                break;
//                e.printStackTrace();
            }
        }
    }

    private Creature getCreature(int id) {
        for (Creature creature : huluWorld.getAllCreatures()) {
            if (creature.getId() == id) return creature;
        }
        return null;
    }
}
