package com.company;

public class Creature<T extends Integer> {
    protected Position<T> position;
    protected String name;
    protected Ground ground;

    public Creature(Position<T> position, String name, Ground ground) {
        this.position = position;
        this.name = name;
        this.ground = ground;
        ground.addCreation(this);
        System.out.println(name + ":我在" + position);
    }

    public String toString() { return name;}
    public void setPosition(Position<T> position) { this.position = position;}
    public Position getPosition() { return this.position;}
    public boolean goSomewhere(Integer targetx, Integer targety) {
        if (targetx == position.getX() && targety == position.getY()) {
            System.out.println(toString() + ":" + position + "->" + position);
            return true;
        }
        Position<Integer>[] pathnode = ground.searchPath(this, targetx, targety);
        if (pathnode == null || pathnode.length <= 0) return false;
        String path = this.toString();
        path += ":" + position;
        for (int i = 0; i < pathnode.length; i++) {
            path += "->" + pathnode[i];
        }
        System.out.println(path);
        position.setPosition((T)targetx, (T)targety);
        return true;
    }
}
