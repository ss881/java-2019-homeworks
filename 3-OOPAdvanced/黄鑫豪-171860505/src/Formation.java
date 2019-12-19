public class Formation {
    static public Position[] longsnake() {
        int[][] tmp = {
                {1},
                {1},
                {1},
                {1},
                {1},
                {1},
                {1}
        };
        return count(7, 1, tmp);
    }

    static public Position[] get(int p) {
        switch (p) {
            case 1:
                return birdwing();
            case 2:
                return sharparrow();
            default:
                return birdwing();
        }
    }

    static public Position[] birdwing() {
        int[][] tmp = {
                {1,0,0,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1,0},
                {0,0,1,0,0,0,1,0,0},
                {0,0,0,1,0,1,0,0,0},
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        };
        return count(6, 9, tmp);
    }

    static public Position[] sharparrow() {
        int[][] tmp = {
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,1,1,1,0,0,0},
                {0,0,1,0,1,0,1,0,0},
                {0,1,0,0,1,0,0,1,0},
                {1,0,0,0,1,0,0,0,1},
                {0,0,0,0,1,0,0,0,0}
        };
        return count(6, 9, tmp);
    }

    public static Position[] count(int n, int m, int[][] form){
        int p = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (form[i][j] == 1)
                    p++;

        Position[] pos = new Position[p];
        int x = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++){
                if (form[i][j] == 1) {
                    pos[x] = new Position(i, j);
                    x++;
                }
            }
        return pos;
    }
}
