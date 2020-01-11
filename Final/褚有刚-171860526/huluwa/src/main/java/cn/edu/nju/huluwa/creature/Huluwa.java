package cn.edu.nju.huluwa.creature;


import cn.edu.nju.huluwa.huluworld.HuluWorld;

public class Huluwa extends Creature implements Comparable<Huluwa> {
    private int rank;

    public Huluwa(HuluWorld huluWorld, int rank, String name, Colour color) {
        super(huluWorld, name, color);
//      super(name,
//              new ImageIcon("pics/" + name + ".jpg").getImage());
        this.rank = rank;
    }

    @Override
    public int compareTo(Huluwa o) {
        return Integer.compare(rank, o.rank);
    }

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Minion || creature instanceof Snake || creature instanceof Scorpion;
    }
}
