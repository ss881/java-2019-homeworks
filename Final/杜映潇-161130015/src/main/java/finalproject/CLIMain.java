package finalproject;

import finalproject.controller.GameController;
import finalproject.utils.CreatureFactory;
import finalproject.utils.Factory;
import finalproject.worldmap.CalabashWorld;
import finalproject.worldmap.World;

public class CLIMain {
    public static void main(String[] args) {
        /* Initialize world */
        World calabashWorld = new CalabashWorld();
        Factory creatureFactory = new CreatureFactory();

        /* Initialize controller */
        GameController gameController = new GameController(calabashWorld, creatureFactory);

        gameController.refresh(); /* Initial battle field */
        System.out.println(gameController.getBattleField());

        //gameController.startGame();

        /* Visualize the final result */
        System.out.println(gameController.getBattleField());
    }
}
