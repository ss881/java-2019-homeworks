package sample;

import java.util.Random;

public class Attribute {
    public final static int
        HEALTH = 0, ATTACK = 1, SPEED = 2, INTELLECT = 3, CONSTITUTION = 4;
    public final static int TOTAL_NUMBER = 5;
    private int[] _attributes;
    public Attribute(Random rand) {
        _attributes = new int[TOTAL_NUMBER];
        for (int i = 1; i < TOTAL_NUMBER; i++)
            _attributes[i] = rand.nextInt(100) + 1;
        _attributes[HEALTH] = _attributes[CONSTITUTION];
    }
    public Attribute(Attribute attribute) {
        _attributes = new int[TOTAL_NUMBER];
        for (int i = 0; i < TOTAL_NUMBER; i++)
            _attributes[i] = attribute._attributes[i];
    }

    public int getAttribute(int type) {
        if (type > -1 && type < TOTAL_NUMBER)
            return _attributes[type];
        else return -1;
    }
    public void setAttribute(int type, int num) {
        if (type > -1 && type < TOTAL_NUMBER)
            _attributes[type] = num;
    }
    public void changeAttribute(int type, int change) {
        if (type > -1 && type < TOTAL_NUMBER) {
            _attributes[type] += change;
            if (_attributes[type] <= 0) {
                if (type != HEALTH)
                    _attributes[type] = 1;
            }
        }
        if (type == HEALTH)
            if (_attributes[type] < 0)
                _attributes[type] = 0;
    }
}
