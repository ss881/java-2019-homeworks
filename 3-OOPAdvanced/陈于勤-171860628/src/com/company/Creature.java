package com.company;
import java.util.*;

public class Creature {
    private int addrx;
    private int addry;
    private String name;
    protected Map map;

    public Creature(int x, int y, String name, Map map) {
        addrx = x;
        addry = y;
        this.name = new String(name);
        this.map = map;
        if (map != null) map.addCreation(this);
        System.out.println(name + ":我在(" + x + "," + y + ")");
    }

    public String toString() { return new String(name); }
    public int getAddressx() { return addrx; }
    public int getAddressy() { return addry; }
    public boolean changeAddress(int x, int y) {
        if (map != null) {
            boolean ret = map.changeAddress(this, x, y);
            if (ret == false) return false;
        }
        addrx = x;
        addry = y;
        return true;
    }
    public boolean goSomewhere(int targetx, int targety) {
        if (targetx == getAddressx() && targety == getAddressy()) {
            System.out.println(toString() + ":(" + targetx + "," + targety + ")");
            return true;
        }
        int[][] pathnode = map.searchPath(this, targetx, targety);
        if (pathnode == null || pathnode.length <= 0) return false;
        int startx = this.getAddressx(), starty = this.getAddressy();
        String path = this.toString();
        path += ":("+startx+","+starty+")";
        for (int i = 0; i < pathnode.length; i++) {
            path += "->(" + pathnode[i][0] + "," + pathnode[i][1] + ")";
        }
        System.out.println(path);
        changeAddress(targetx, targety);
        return true;
    }
}
