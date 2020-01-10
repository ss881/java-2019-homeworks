package team;

import battlefield.BattleField;
import creature.Leader;
import creature.Louluo;
import creature.Scorpion;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvilTeamTest {
    private static BattleField battleField;
    private static Lock lock;
    private static Leader snake;
    private static ArrayList<Louluo> louluos;

    @BeforeClass
    public static void setUp() {
        battleField = new BattleField();
        lock = new ReentrantLock();
        snake = new Leader("snake", battleField, false, lock);
        louluos = new ArrayList<Louluo>();
        louluos.add(new Scorpion("scorpion", battleField, lock));
        for (int i = 1; i < 9; i++) {
            louluos.add(new Louluo(Integer.toString(i) + "louluo", battleField, i, lock));
        }
    }

    @Test
    public void checkLost() {
        EvilTeam evilTeam = new EvilTeam<Leader, Louluo>(snake, louluos, battleField);
        assert (evilTeam.checkLost()==false);
    }

    @Test
    public void getAllCreature() {
        EvilTeam evilTeam = new EvilTeam<Leader, Louluo>(snake, louluos, battleField);
        assert (evilTeam.getAllCreature().size()==10);
    }
}