package creature;

import ground.*;

public class Generator {
    public void genHuluwa(Ground ground, int row, int col, Color color) { ground.addCreatureAt(new Huluwa(ground, color), row, col); }

    public void genScorpion(Ground ground, int row, int col) {
        ground.addCreatureAt(new Scorpion(ground), row, col);
    }

    public void genCreep(Ground ground, int row, int col) {
        ground.addCreatureAt(new Creep(ground), row, col);
    }

    public void genGrandpa(Ground ground, int row, int col) {
        ground.addCreatureAt(new Grandpa(ground), row, col);
    }

    public void genSnake(Ground ground, int row, int col) {
        ground.addCreatureAt(new Snake(ground), row, col);
    }

    /*public Huluwa genRandomHuluwa(Ground ground, Color color) {
        Huluwa huluwa = new Huluwa(ground, color);
        for (; ; ) {
            int row = (int) (Math.random() * ground.getROWS());
            int col = (int) (Math.random() * ((ground.getCOLS() - 1) / 2 ));
            if (!ground.isOccupied(row, col)) {
                ground.addCreature(huluwa, row, col);
                break;
            }
        }
        return huluwa;
    }

    public Scorpion genRandomScorpion(Ground ground) {
        Scorpion scorpion = new Scorpion(ground);
        for(; ; ) {
            int row = (int) (Math.random() * ground.getROWS());
            int col = (int) (((ground.getCOLS() - 1) / 2 + 1) + Math.random() * (ground.getCOLS() - ((ground.getCOLS() - 1) / 2 + 1)));
            if(!ground.isOccupied(row, col)) {
                ground.addCreature(scorpion, row, col);
                break;
            }
        }
        return scorpion;
    }

    public Creep genRandomCreep(Ground ground) {
        Creep creep = new Creep(ground);
        for (; ; ) {
            int row = (int) (Math.random() * ground.getROWS());
            int col = (int) (((ground.getCOLS() - 1) / 2 + 1) + Math.random() * (ground.getCOLS() - ((ground.getCOLS() - 1) / 2 + 1)));
            if (!ground.isOccupied(row, col)) {
                ground.addCreature(creep, row, col);
                break;
            }
        }
        return creep;
    }*/
}
