package creature;

import battlefield.BattleField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;

public class CreatureFactory {
    private Lock lock;

    public CreatureFactory(Lock lock) {
        this.lock = lock;
    }

    public ArrayList<Huluwa> createHuluwas(BattleField bf) {
        ArrayList<Huluwa> huluwas = new ArrayList<Huluwa>();
        huluwas.add(new Huluwa("1huluwa", bf, 1, lock));
        huluwas.add(new Huluwa("2huluwa", bf, 2, lock));
        huluwas.add(new Huluwa("3huluwa", bf, 3, lock));
        huluwas.add(new Huluwa("4huluwa", bf, 4, lock));
        huluwas.add(new Huluwa("5huluwa", bf, 5, lock));
        huluwas.add(new Huluwa("6huluwa", bf, 6, lock));
        huluwas.add(new Huluwa("7huluwa", bf, 7, lock));
        Collections.shuffle(huluwas);
        return huluwas;
    }

    public ArrayList<Louluo> createLouluos(BattleField bf) {
        ArrayList<Louluo> Louluos = new ArrayList<Louluo>();
        Louluos.add(new Scorpion("scorpion", bf, lock));
        for (int i = 1; i < 9; i++) {
            Louluos.add(new Louluo(Integer.toString(i) + "louluo", bf, i, lock));
        }
        for (Louluo louluo:Louluos){
            louluo.setImage();
        }
        return Louluos;
    }

    public Leader createElder(BattleField bf) {
        Leader leader = new Leader("elder", bf, true, lock);
        leader.loadImage();
        return leader;
    }

    public Leader createSnake(BattleField bf) {
        Leader leader = new Leader("snake", bf, false, lock);
        leader.loadImage();
        return leader;
    }
}
