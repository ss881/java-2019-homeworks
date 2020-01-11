import java.util.*;
import java.lang.reflect.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

enum Color {
    red("老大","红色"),
    orange("老二", "橙色"),
    yellow("老三", "黄色"),
    green("老四", "绿色"),
    cyan("老五", "青色"),
    blue("老六", "蓝色"),
    purple("老七", "紫色");
    private final String AgeCN;
    private final String ColorCN;
    private Color(String AgeCN, String ColorCN) {this.AgeCN = AgeCN; this.ColorCN = ColorCN;}
    public String getAgeCN() {return AgeCN;}
    public String getColorCN() {return ColorCN;}
}

class Ground {
    private int[][] ground;
    private int groundLength;
    Ground(int n) {ground = new int[n][n]; groundLength = ground.length;}
    int getGroundLength() {return groundLength;}
    int getVal(int row, int col) {return ground[row][col];}
    void setVal(int row, int col, int val) {ground[row][col] = val;}
}

class Creature {
    private int row;
    private int col;
    Creature() {row = 0; col = 0;}
    Creature(int r, int c) {this.row = r; this.col = c;}
    void setRow(int r) {this.row = r;}
    void setCol(int c) {this.col = c;}
    void moveto(int r, int c) {System.out.println("move from (" + this.row + ", " + this.col + ") to (" + r + ", " + c + ")"); setRow(r); setCol(c);}
    public int getRow() {return row;}
    public int getCol() {return col;}
}

class Hulu extends Creature{
    private Color color;
    Hulu() {color = Color.red;}
    Hulu(int r, int c, Color _c) {super(r, c); this.color = _c;}
    void setColor(Color c) {this.color = c;}
    public Color getColor() {return color;}
    /*public void swap(Hulu another) {
        System.out.println(this.color.getAgeCN() + ": " + ("" + this.order) + "->" + ("" + another.getOrder()));
        System.out.println(another.color.getAgeCN() + ": " + ("" + another.getOrder()) + "->" + ("" + this.order));
        int tmp = this.order;
        this.order = another.getOrder();
        another.setOrder(tmp);
    }*/
}

class Scorpion extends Creature {
    Scorpion() {}
    Scorpion(int r, int c) {super(r, c);}
}

class Creep extends Creature {
    Creep() {}
    Creep(int r, int c) {super(r, c);}
}

class Grandpa extends Creature {
    Grandpa() {}
    Grandpa(int r, int c) {super(r, c);}
}

class Snake extends Creature {
    Snake() {}
    Snake(int r, int c) {super(r, c);}
}

class Generate {
    Hulu genHulu(Color color, Ground ground) {
        Hulu hulu = new Hulu();
        hulu.setColor(color);
        for (; ; ) {
            int row = (int) (Math.random() * ground.getGroundLength());
            int col = (int) (Math.random() * ((ground.getGroundLength() - 1) / 2 ));
            if (ground.getVal(row, col) == 0) {
                ground.setVal(row, col, 1);
                hulu.setRow(row);
                hulu.setCol(col);
                break;
            }
        }
        return hulu;
    }
    Creep genCreep(Ground ground) {
        Creep creep = new Creep();
        for (; ; ) {
            int row = (int) (Math.random() * ground.getGroundLength());
            int col = (int) (((ground.getGroundLength() - 1) / 2 + 1) + Math.random() * (ground.getGroundLength() - ((ground.getGroundLength() - 1) / 2 + 1)));
            if (ground.getVal(row, col) == 0) {
                ground.setVal(row, col, 3);
                creep.setRow(row);
                creep.setCol(col);
                break;
            }
        }
        return creep;
    }
}

class Formation {
    boolean isHere(Creature c, int index, ArrayList<Creature>creatures) {
        for(int i = 0 ; i < creatures.size(); i++) {
            if(i == index)
                continue;
            if(c.getRow() == creatures.get(i).getRow() && c.getCol() == creatures.get(i).getCol())
                return true;
        }
        return false;
    }

    public void transformToHeyi(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 7;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for(; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for(; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2;
            int scorp_col = (ground.getGroundLength() / 2 + 1) + INIT_NUM / 2;

            if(scorp_row >= ground.getGroundLength() || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            if(ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i <= INIT_NUM / 2; i++) {
                int creep_row = creatures.get(0).getRow() - i;
                int creep_col = creatures.get(0).getCol() - i;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = INIT_NUM / 2 + 1; i < creatures.size(); i++) {
                int offset = INIT_NUM / 2;
                int creep_row = creatures.get(0).getRow() + (i - offset);
                int creep_col = creatures.get(0).getCol() - (i - offset);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
    }

    public void transformToYanxing(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 7;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for(; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for(; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2;
            int scorp_col = (ground.getGroundLength() / 2 + 1) + INIT_NUM / 2;

            if(scorp_row >= ground.getGroundLength() || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToYanxing Exception");
                return;
            }

            if(ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i <= INIT_NUM / 2; i++) {
                int creep_row = creatures.get(0).getRow() - i;
                int creep_col = creatures.get(0).getCol() + i;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = INIT_NUM / 2 + 1; i < creatures.size(); i++) {
                int offset = INIT_NUM / 2;
                int creep_row = creatures.get(0).getRow() + (i - offset);
                int creep_col = creatures.get(0).getCol() - (i - offset);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
    }

    public void transformToChonge(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 7;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for(; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for(; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2 - INIT_NUM / 2 + 1;
            int scorp_col = ground.getGroundLength() / 2 + 1;

            if(scorp_row <= 0 || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            if(ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i < INIT_NUM / 2; i++) {
                int creep_row = creatures.get(0).getRow() + 2 * i;
                int creep_col = creatures.get(0).getCol();
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = INIT_NUM / 2; i < creatures.size(); i++) {
                int offset = INIT_NUM / 2;
                int creep_row = creatures.get(0).getRow() - 1 + 2 * (i - offset);
                int creep_col = creatures.get(0).getCol() + 1;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
    }

    public void transformToChangshe(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 7;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for(; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for(; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2 - INIT_NUM / 2;
            int scorp_col = ground.getGroundLength() / 2 + 1;

            if(scorp_row <= 0 || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            if(ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i < INIT_NUM; i++) {
                int creep_row = creatures.get(0).getRow() + i;
                int creep_col = creatures.get(0).getCol();
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
        else { // 葫芦娃
            int HULUWA_NUM = 7;

            int red_row = ground.getGroundLength() / 2 - HULUWA_NUM / 2;
            int red_col = ground.getGroundLength() / 2 - 1;

            if(red_row <= 0 || red_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("huluwa1: ");
            creatures.get(0).moveto(red_row, red_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 1);

            for(int i = 1; i < HULUWA_NUM; i++) {
                int hulu_row = creatures.get(0).getRow() + i;
                int hulu_col = creatures.get(0).getCol();
                if(!(creatures.get(i).getCol() == creatures.get(0).getCol() && (creatures.get(i).getRow() >= creatures.get(0).getRow() && creatures.get(i).getRow() < hulu_row)))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("Huluwa" + (i + 1) + ": ");
                creatures.get(i).moveto(hulu_row, hulu_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 1);
            }
        }
    }

    public void transformToYulin(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 10;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for(; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for(; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2;
            int scorp_col = ground.getGroundLength() / 2 + 1;

            if(scorp_row >= ground.getGroundLength() || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            if(ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i < 2; i++) {
                int offset = 1;
                int creep_row = creatures.get(0).getRow() - 1 + 2 * (i - offset);
                int creep_col = creatures.get(0).getCol() + 1;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 2; i < 5; i++) {
                int offset = 2;
                int creep_row = creatures.get(0).getRow() - 2 + 2 * (i - offset);
                int creep_col = creatures.get(0).getCol() + 2;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(),creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 5; i < INIT_NUM - 1; i++) {
                int offset = 5;
                int creep_row = creatures.get(0).getRow() - 3 + 2 * (i - offset);
                int creep_col = creatures.get(0).getCol() + 3;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = INIT_NUM - 1; i < INIT_NUM; i++) {
                int creep_row = creatures.get(0).getRow();
                int creep_col = creatures.get(0).getCol() + 4;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
    }

    public void transformToFangyuan(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 8;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for(; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for(; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2;
            int scorp_col = ground.getGroundLength() / 2 + 1;

            if(scorp_row <= 0 || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            if(ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i < 3; i++) {
                int co = (int)Math.pow(-1, i + 1);
                int creep_row = creatures.get(0).getRow() - co;
                int creep_col = creatures.get(0).getCol() + 1;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 3; i < 5; i++) {
                int co = (int)Math.pow(-1, i + 1);
                int creep_row = creatures.get(0).getRow() - co * 2;
                int creep_col = creatures.get(0).getCol() + 2;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 5; i < 7; i++) {
                int co = (int)Math.pow(-1, i + 1);
                int creep_row = creatures.get(0).getRow() - co;
                int creep_col = creatures.get(0).getCol() + 3;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 7; i < 8; i++) {
                int creep_row = creatures.get(0).getRow();
                int creep_col = creatures.get(0).getCol() + 4;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 8; i < INIT_NUM; i++) {
                int offset = 7;
                int creep_row = creatures.get(0).getRow() + (i - offset);
                int creep_col = creatures.get(0).getCol() + 4;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
    }

    public void transformToYanyue(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 19;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for(; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for(; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2;
            int scorp_col = ground.getGroundLength() / 2 + 4;

            if(scorp_row <= 0 || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            if(ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i < 3; i++) {
                int co = (int)Math.pow(-1, i + 1);
                int creep_row = creatures.get(0).getRow() + co;
                int creep_col = creatures.get(0).getCol();
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 3; i < 6; i++) {
                int offset_row = 1;
                int offset_col = 2;
                int creep_row = creatures.get(0).getRow() - (i - offset_row);
                int creep_col = creatures.get(0).getCol() - (i - offset_col);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 6; i < 9; i++) {
                int offset_row = 4;
                int offset_col = 5;
                int creep_row = creatures.get(0).getRow() + (i - offset_row);
                int creep_col = creatures.get(0).getCol() - (i - offset_col);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 9; i < 12; i++) {
                int offset = 9;
                int creep_row = creatures.get(0).getRow() - 1 + (i - offset);
                int creep_col = creatures.get(0).getCol() + 2;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 12; i < 14; i++) {
                int offset_row = 10;
                int offset_col = 13;
                int creep_row = creatures.get(0).getRow() - (i - offset_row);
                int creep_col = creatures.get(0).getCol() - (i - offset_col);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 14; i < 16; i++) {
                int offset_row = 12;
                int offset_col = 15;
                int creep_row = creatures.get(0).getRow() + (i - offset_row);
                int creep_col = creatures.get(0).getCol() - (i - offset_col);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 16; i < INIT_NUM; i++) {
                int offset = 16;
                int creep_row = creatures.get(0).getRow() - 1 + (i - offset);
                int creep_col = creatures.get(0).getCol() + 4;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
    }

    public void transformToFengshi(ArrayList<Creature>creatures, Ground ground) {
        int INIT_NUM = 12;
        if(creatures.get(0).getClass().getName().equals("Scorpion")) {
            Generate gen = new Generate();
            for (; creatures.size() > INIT_NUM; ) {
                ground.setVal(creatures.get(INIT_NUM).getRow(), creatures.get(INIT_NUM).getCol(), 0);
                creatures.remove(INIT_NUM);
            }
            for (; creatures.size() < INIT_NUM; )
                creatures.add(gen.genCreep(ground));
            int scorp_row = ground.getGroundLength() / 2;
            int scorp_col = ground.getGroundLength() / 2 + 1;

            if (scorp_row <= 0 || scorp_col >= ground.getGroundLength()) {
                System.out.println("Formation.transformToHeyi Exception");
                return;
            }

            if (ground.getVal(creatures.get(0).getRow(), creatures.get(0).getCol()) == 2)
                ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 0);
            System.out.print("scorpion: ");
            creatures.get(0).moveto(scorp_row, scorp_col);
            ground.setVal(creatures.get(0).getRow(), creatures.get(0).getCol(), 2);

            for(int i = 1; i < 6; i++) {
                int creep_row = creatures.get(0).getRow();
                int creep_col = creatures.get(0).getCol() + i;
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 6; i < 9; i++) {
                int offset = 5;
                int creep_row = creatures.get(0).getRow() - (i - offset);
                int creep_col = creatures.get(0).getCol() + (i - offset);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
            for(int i = 9; i < INIT_NUM; i++) {
                int offset = 8;
                int creep_row = creatures.get(0).getRow() + (i - offset);
                int creep_col = creatures.get(0).getCol() + (i - offset);
                if(!isHere(creatures.get(i), i, creatures))
                    ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 0);
                System.out.print("creep" + i + ": ");
                creatures.get(i).moveto(creep_row, creep_col);
                ground.setVal(creatures.get(i).getRow(), creatures.get(i).getCol(), 3);
            }
        }
    }
}

class Display {
    void displayGround(Ground ground) {
        for(int i = 0; i < ground.getGroundLength(); i++) {
            for (int j = 0; j < ground.getGroundLength() - 1; j++) {
                if(ground.getVal(i, j) == 0)
                    System.out.print("_  ");
                else
                    System.out.print(ground.getVal(i, j) + "  ");
            }
            if(ground.getVal(i, ground.getGroundLength() - 1) == 0)
                System.out.print("_\n");
            else
                System.out.print(ground.getVal(i, ground.getGroundLength() - 1) + "\n");
        }
        System.out.println();
    }
}

public class Huluwav2 {
    public static void main(String[] args) throws IOException {
        int GROUND_LENGTH = 21;
        Ground ground = new Ground(GROUND_LENGTH); // 1葫芦娃 2蝎子精 3小喽啰 4爷爷 5蛇精
        ArrayList<Creature>hulus = new ArrayList<Creature>();
        ArrayList<Creature>creeps = new ArrayList<Creature>(); // creeps[0] == scorp


        /* 爷爷固定生成 */
        Grandpa grandpa = new Grandpa(ground.getGroundLength() / 2, 0);
        ground.setVal(ground.getGroundLength() / 2, 0, 4);

        /* 蛇精固定生成 */
        Snake snake = new Snake(ground.getGroundLength() / 2, ground.getGroundLength() - 1);
        ground.setVal(ground.getGroundLength() / 2, ground.getGroundLength() - 1, 5);

        Generate gen = new Generate();
        /* 葫芦娃随机生成 */
        int HULU_NUM = 7;
        for(int i = 0; i < HULU_NUM; i++) {
            Color tmp = Color.red;
            switch (i) {
                case 1:
                    tmp = Color.orange;
                    break;
                case 2:
                    tmp = Color.yellow;
                    break;
                case 3:
                    tmp = Color.green;
                    break;
                case 4:
                    tmp = Color.cyan;
                    break;
                case 5:
                    tmp = Color.blue;
                    break;
                case 6:
                    tmp = Color.purple;
                    break;
                default:
                    tmp = Color.red;
            }
            hulus.add(gen.genHulu(tmp, ground));
        }

        /* 蝎子精随机生成 */
        Scorpion scorp = new Scorpion();
        for(;;) {
            int row = (int) (Math.random() * ground.getGroundLength());
            int col = (int) (((ground.getGroundLength() - 1) / 2 + 1) + Math.random() * (ground.getGroundLength() - ((ground.getGroundLength() - 1) / 2 + 1)));
            if (ground.getVal(row, col) == 0) {
                ground.setVal(row, col, 2);
                scorp.setRow(row);
                scorp.setCol(col);
                break;
            }
        }
        creeps.add(scorp);

        /* 小喽啰随机生成 */
        int CREEP_INIT_NUM = 6;
        for(int i = 0; i < CREEP_INIT_NUM; i++)
            creeps.add(gen.genCreep(ground));

        Formation form = new Formation();
        Display display = new Display();

        /* initial */
        display.displayGround(ground);

        /* Step2 */
        form.transformToChangshe(hulus, ground);
        display.displayGround(ground);

        /* Step3-6 */
        try(FileReader frCreepForm = new FileReader("CreepFormation.txt");
            BufferedReader brCreepForm = new BufferedReader(frCreepForm);
        ) {
            String str_transformTo = "transformTo";
            String line;
            while((line = brCreepForm.readLine()) != null) {
                String methodName = str_transformTo + line;
                Class formationClass = Formation.class;
                Method method = formationClass.getMethod(methodName, creeps.getClass(), ground.getClass());
                method.invoke(form, creeps, ground);
                display.displayGround(ground);
            }
        }
        catch(IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /* Output Test */
        /*hulus.forEach(x -> System.out.println(x.getClass().getName()));
        creeps.forEach(x -> System.out.println(x.getClass().getName()));
        */
    }

}
