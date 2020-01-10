package sample;

import java.util.Random;

public class Judge {
    public final static int
        FAIL = 0, SUCCESS = 1, HARD_SUCCESS = 2,
        EXTREME_SUCCESS = 3, BIG_SUCCESS = 4, SUPPER_SUCCESS = 5;
    static public int judge(int i, Random r) {
        int end = r.nextInt(100) + 1;
        if (end == 1) return SUPPER_SUCCESS;
        else if (end <= 5) return BIG_SUCCESS;
        else if (end >= 96) return FAIL;
        else if (end <= (i + 4) / 5) return EXTREME_SUCCESS;
        else if (end <= i / 2) return HARD_SUCCESS;
        else if (end <= i) return SUCCESS;
        else return FAIL;
    }
}
