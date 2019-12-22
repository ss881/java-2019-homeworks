import Controller.BattleCommander;
import Utils.CreatureFactory;
import Utils.Factory;
import WorldMap.CalabashWorld;
import WorldMap.World;

public class Main {
    public static void main(String[] args) {
        /* Initialize world */
        World calabashWorld = new CalabashWorld();
        Factory creatureFactory = new CreatureFactory();

        /* Initialize commander */
        BattleCommander battleCommander = new BattleCommander(calabashWorld, creatureFactory);

        battleCommander.initial(); /* Initial battle field */
        String battleField = battleCommander.getBattleField();

        /* Visualize the situation of battle field */
        System.out.println(battleField);

        /* Change the formation of the Goblin side */
        battleCommander.changeScorpionAndGoblinsFormation();
        battleField = battleCommander.getBattleField();

        /* Visualize the new result */
        System.out.println(battleField);
    }
}
