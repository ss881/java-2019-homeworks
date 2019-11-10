package com.company;

public class Scorpion extends Creature {
    int louluonum;
    Creature[] louluo;

    public Scorpion(int x, int y, String name, Map map) {
        super(x, y, name, map);
        louluonum = Map.MAP_SIZE * 2 / 3;
    }
    public Scorpion(int x, int y, String name, Map map, int louluonum) {
        super(x, y, name, map);
        this.louluonum = louluonum;
    }
    public boolean callLouLuo() {
        if (louluo != null) return false;
        System.out.println("蝎子精：小的们，都出来吧！");
        louluo = new Creature[louluonum];
        for (int i = 0; i < louluo.length; i++) {
            int[] node = map.getNewAddress(Map.MAP_SIZE/2, Map.MAP_SIZE - 1, 0, Map.MAP_SIZE - 1);
            if (node == null) { louluo = null; return false;}
            louluo[i] = new Creature(node[0], node[1], "喽啰"+i, map);
        }
        return true;
    }
    public boolean changeWildGoose() {
        if (louluo == null) return false;
        System.out.println("蝎子精：变换队形->雁行");
        this.goSomewhere(Map.MAP_SIZE - 1, 0);
        for (int i = 0; i < louluonum; i++) {
            for (int j = i; j < louluonum; j++) {
                int targetx, targety;
                    targetx = Map.MAP_SIZE - 2 - i;
                    targety = i + 1;
                if (louluo[j].goSomewhere(targetx, targety) == true) {
                    if (i == j) break;
                    Creature temp = louluo[i];
                    louluo[i] = louluo[j];
                    louluo[j] = temp;
                    break;
                }
            }
        }
        return true;
    }
    public boolean changeCraneWing() {
        if (louluo == null) return false;
        System.out.println("蝎子精：变换队形->鹤翼");
        this.goSomewhere((Map.MAP_SIZE * 2 - louluonum - 2) / 2, 0);
        for (int i = 0; i < louluonum; i++) {
            for (int j = i; j < louluonum; j++) {
                int targetx, targety;
                if (i < louluonum / 2) {
                    targetx = Map.MAP_SIZE - 1 - i;
                    targety = i;
                }
                else {
                    targetx = Map.MAP_SIZE - 1 - i;
                    targety = louluonum - 1 - i;
                }
                if (louluo[j].goSomewhere(targetx, targety) == true) {
                    if (i == j) break;
                    Creature temp = louluo[i];
                    louluo[i] = louluo[j];
                    louluo[j] = temp;
                    break;
                }
            }
        }
        return true;
    }
    public boolean changeWave() {
        if (louluo == null) return false;
        System.out.println("蝎子精：变换队形->衝轭");
        this.goSomewhere(Map.MAP_SIZE * 3 / 4 + 1, 0);
        for (int i = 0; i < louluonum; i++) {
            for (int j = i; j < louluonum; j++) {
                int targetx, targety;
                targetx = Map.MAP_SIZE * 3 / 4 + i % 2;
                targety = i + 1;
                if (louluo[j].goSomewhere(targetx, targety) == true) {
                    if (i == j) break;
                    Creature temp = louluo[i];
                    louluo[i] = louluo[j];
                    louluo[j] = temp;
                    break;
                }
            }
        }
        return true;
    }
}
