package finalproject.fxview;

import finalproject.creatures.Creature;
import finalproject.utils.GameLogger;
import finalproject.utils.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ResultDialogController {
    @FXML
    Label result;

    public void showResult(Creature.Side winningSide) {
        Logger logger = GameLogger.getInstance(null);
        String logInfo;
        if (logger != null) {
            logInfo = "Result saved in " + logger.getCurrentLogFileName();
        } else {
            logInfo = "Rewind end";
        }
        switch (winningSide) {
            case CalabashSide: result.setText("Calabash side win!\n" + logInfo); break;
            case GoblinSide: result.setText("Goblin side win!\n" + logInfo); break;
        }
    }
}
