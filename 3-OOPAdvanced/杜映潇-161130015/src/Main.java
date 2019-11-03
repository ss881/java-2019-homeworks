public class Main {
    public static void main(String[] args) {
        /* Initialize world */
        World world = new World();

        /* Initialize commander */
        BattleCommander battleCommander = new BattleCommander(world);

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
