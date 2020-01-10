package com.company;

public class Scorpion<T extends Integer> extends Creature<T> {
    int louluonum = Ground.GROUBD_SIZE;
    int louluoid = 1;

    public Scorpion(Position<T> position, String name, Ground<T> ground) {
        super(position, name, ground);
    }

    public void setLouluonum(int louluonum) {
        this.louluonum = louluonum;
    }

    public boolean callLouLuo() {
        System.out.println("蝎子精：小的们，都出来吧！");
        for (int i = 0; i < louluonum; i++) {
            Position<Integer> nextlouluo = ground.getNewAddress(Ground.GROUBD_SIZE / 4, Ground.GROUBD_SIZE, 0, Ground.GROUBD_SIZE);
            if (nextlouluo == null) { return false;}
            new XiaoBing<Integer>(nextlouluo, "bing" + louluoid++, ground);
        }
        return true;
    }

    public void setLouluoStrategy(Position<T> position, StrategyFormation strategy)
            throws Exception {
        System.out.println("蝎子精：变换队形->" + strategy + position);
        position.setPath(1);
        try {
            ground.setGroupStrategyFormation("XiaoBing", this, strategy, position, louluonum);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

}
