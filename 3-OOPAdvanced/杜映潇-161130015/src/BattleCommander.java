public class BattleCommander {
    private World world;

    public BattleCommander(World world) {
        this.world = world;
    }

    private void initialCalabashBrother() {
        /* We will place calabash brother on the second column */
        int numCalabashBrother = 7;
        int columnIndex = 1;
        for (int i = 0; i < numCalabashBrother; i++) {
            world.place(new CalabashBrother(i), i, columnIndex);
        }
    }

    private void initialScorpionAndGoblins() {
        /* We will place scorpion and goblins using the Yoke (衡轭阵) starting on the last but one column */
        int columnIndex = world.getSIZE() - 2;
        int columnBias = 1;
        int numGoblins = 6;
        world.place(Scorpion.getScorpionObject(), 0, columnIndex);
        for (int i = 0; i < numGoblins; i++) {
            world.place(new Goblin(), i + 1, columnIndex - columnBias);
            columnBias = columnBias == 1 ? 0 : 1;
        }
    }

    private void initialGrandfatherAndSnake() {
        /* We will place grandfather on the center of the back of all calabash brothers */
        world.place(Grandfather.getGrandfatherObject(), 3, 0);
        /* We will place snake on the center of the back of scorpion and goblins */
        world.place(Snake.getSnakeObject(), 3, world.getSIZE() - 1);
    }

    public void initial() {
        initialCalabashBrother();
        initialScorpionAndGoblins();
        initialGrandfatherAndSnake();
    }

    public void changeScorpionAndGoblinsFormation() {
        /* Change the arrangement of scorpion and goblins from Yoke (衡轭阵) to Square (方圆) */
        /* The Square I used here is a little different from that in the markdown file
         (https://github.com/njuics/java-2019-homeworks/tree/master/3-OOPAdvanced)
         I put the scorpion, which is there leader, in the center of the square, which meets the description of
         Square in this wiki page: (https://en.wikipedia.org/wiki/Tactical_formation)
         */
        int size = world.getSIZE();
        world.move(3, size - 3, 2, size - 4);
        world.move(0, size - 2, 3, size - 3);
        world.move(6, size - 2, 4, size - 4);
    }

    public String getBattleField() {
        return world.toString();
    }

    public static void main(String[] args) {
        World world = new World();
        BattleCommander battleCommander = new BattleCommander(world);
        battleCommander.initial();
        System.out.println(battleCommander.getBattleField());
        battleCommander.changeScorpionAndGoblinsFormation();
        System.out.println(battleCommander.getBattleField());
    }

}
