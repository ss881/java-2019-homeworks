package finalproject.controller;

import finalproject.creatures.Creature;
import finalproject.creatures.Grandfather;
import finalproject.creatures.Scorpion;
import finalproject.creatures.Snake;
import finalproject.fxview.BattleFieldController;
import finalproject.utils.Factory;
import finalproject.worldmap.World;

import java.io.*;
import java.util.*;

public class GameController {
    private static final int NUMCALABASHBROTHER = 7;
    private static final int NUMGOBLINS = 6;
    private World world;
    private Factory factory;
    private List<Runnable> tasks;

    public GameController(World world, Factory factory) {
        this.world = world;
        this.factory = factory;
        tasks = new ArrayList<>();
    }

    private void initialCalabashBrother() {
        /* We will place calabash brother on the second column */
        int columnIndex = 1;
        for (int i = 0; i < NUMCALABASHBROTHER; i++) {
            world.place(factory.generate("finalproject.creatures.CalabashBrother#" + i), i, columnIndex);
            tasks.add(new Task(world.getCreature(i, columnIndex), world));
        }
    }

    private void initialScorpionAndGoblins() {
        /* We will place scorpion and goblins using the Yoke (衡轭阵) starting on the last but one column */
        int columnIndex = world.getSIZE() - 2;
        int columnBias = 1;
        world.place(Scorpion.getScorpionObject(), 0, columnIndex);
        tasks.add(new Task(Scorpion.getScorpionObject(), world));
        for (int i = 0; i < NUMGOBLINS; i++) {
            world.place(factory.generate("finalproject.creatures.Goblin"), i + 1, columnIndex - columnBias);
            tasks.add(new Task(world.getCreature(i + 1, columnIndex - columnBias), world));
            columnBias = columnBias == 1 ? 0 : 1;
        }
    }

    private void initialGrandfatherAndSnake() {
        /* We will place grandfather on the center of the back of all calabash brothers */
        world.place(Grandfather.getGrandfatherObject(), 3, 0);
        /* We will place snake on the center of the back of scorpion and goblins */
        world.place(Snake.getSnakeObject(), 3, world.getSIZE() - 1);
    }

    public void refresh() {
        tasks.clear();
        world.clear();
        Scorpion scorpion = Scorpion.getScorpionObject();
        scorpion.refresh();
        initialCalabashBrother();
        initialScorpionAndGoblins();
        initialGrandfatherAndSnake();
    }

    public Creature.Side startGame(BattleFieldController controller) {
        int calabashSideAlive = NUMCALABASHBROTHER;
        int goblinSideAlive = NUMGOBLINS + 1;
        controller.showBattleField(world);
        while (true) {
            try {
                Collections.shuffle(tasks);
                Iterator<Runnable> taskIter = tasks.iterator();
                while (taskIter.hasNext()) {
                    Task task = (Task) taskIter.next();
                    Thread thread = new Thread(task);
                    thread.start();
                    thread.join();

                    if (!(task.getCreature().isAlive())) {
                        if (task.getCreature().getSide() == Creature.Side.CalabashSide) {
                            calabashSideAlive--;
                        } else {
                            goblinSideAlive--;
                        }
                        taskIter.remove();
                    }
                    System.out.println(world);
                    controller.showBattleField(world);
                    Thread.sleep(100);
                }
                if (goblinSideAlive == 0) {
                    return Creature.Side.CalabashSide;
                } else if (calabashSideAlive == 0) {
                    return Creature.Side.GoblinSide;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Creature.Side rewind(File file, BattleFieldController controller) {
        try {
            if (file == null) {
                throw new IllegalArgumentException();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String move;
            int calabashSideAlive = NUMCALABASHBROTHER;
            int goblinSideAlive = NUMGOBLINS + 1;
            while ((move = bufferedReader.readLine()) != null) {
                System.out.println(move);
                String[] tokens = move.split("_");
                if (tokens[1].compareTo("KILLED") == 0) {
                    if (tokens[0].compareTo("Goblin") == 0 || tokens[0].compareTo("Scorpion") == 0) {
                        goblinSideAlive--;
                    } else {
                        calabashSideAlive--;
                    }
                    int x = Integer.parseInt(tokens[2]);
                    int y = Integer.parseInt(tokens[3]);
                    world.kill(x, y);
                } else {
                    int oldX = Integer.parseInt(tokens[2]);
                    int oldY = Integer.parseInt(tokens[3]);
                    int newX = Integer.parseInt(tokens[5]);
                    int newY = Integer.parseInt(tokens[6]);
                    world.move(oldX, oldY, newX, newY);
                }
                controller.showBattleField(world);
                Thread.sleep(100);
            }

            if (calabashSideAlive == 0) {
                return Creature.Side.GoblinSide;
            } else if (goblinSideAlive == 0) {
                return Creature.Side.CalabashSide;
            } else {
                assert (false);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /* THIS METHOD IS NO LONGER NEEDED IN THE FINAL PROJECT */
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
    }

}
