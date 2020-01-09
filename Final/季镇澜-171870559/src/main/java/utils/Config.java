package utils;

import record.Record;

import java.util.ArrayList;

public class Config {
    public static int WIDTH;
    public static int HEIGHT;
    public static int ROW;
    public static int COL;
    public static int SIZE;
    public static int LIFE;
    public static int HULU_FORCE;
    public static int LOULUO_FORCE;
    public static int SCORPION_FORCE;
    public static int LEADER_FORCE;
    public static int GENERAL_SPEED;
    public static int HULU_SPEED;
    public static ArrayList<Record> RECORDS;

    static {
        WIDTH = 800;
        HEIGHT = 600;
        ROW = 16;
        COL = 12;
        SIZE = 50;
        LIFE = 10;
        HULU_FORCE = 4;
        LOULUO_FORCE = 2;
        SCORPION_FORCE = 5;
        LEADER_FORCE = 1;
        GENERAL_SPEED = 1;
        HULU_SPEED = 2;
    }

    Config() {
    }
}
