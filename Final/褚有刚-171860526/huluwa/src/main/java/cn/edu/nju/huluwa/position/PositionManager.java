package cn.edu.nju.huluwa.position;

import static cn.edu.nju.huluwa.Config.*;

public class PositionManager {
    private Position[][] positions;

    public PositionManager() {
        positions = new Position[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                positions[i][j] = new Position(i, j);
            }
        }
        // construct connection
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL - 1; j++) {
                positions[i][j].setNextX(positions[i][j + 1]);
            }
        }
        for (int i = 0; i < ROW - 1; i++) {
            for (int j = 0; j < COL; j++) {
                positions[i][j].setNextY(positions[i + 1][j]);
            }
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 1; j < COL; j++) {
                positions[i][j].setPrevX(positions[i][j - 1]);
            }
        }
        for (int i = 1; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                positions[i][j].setPrevY(positions[i - 1][j]);
            }
        }
    }

    public Position[][] getPositions() {
        return positions;
    }

    public Position getPosition(int i, int j) {
        if(inSpace(i, j)) return positions[i][j];
        return null;
    }

    public boolean inSpace(int i, int j) {
        return i >= 0 && i < ROW && j >= 0 && j < COL;
    }
}
