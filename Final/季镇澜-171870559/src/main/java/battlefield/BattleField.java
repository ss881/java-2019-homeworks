package battlefield;


import static utils.Config.COL;
import static utils.Config.ROW;

public class BattleField {
    public Position[][] theField;

    public BattleField() {
        theField = new Position[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                theField[i][j] = new Position(i, j);
            }
        }
    }

    public void clear() {
        for (Position[] line : theField)
            for (Position p : line)
                p.clear();

    }
}
