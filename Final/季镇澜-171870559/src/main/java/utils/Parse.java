package utils;

import battlefield.BattleField;
import battlefield.Position;
import creature.Creature;
import javafx.scene.image.Image;
import record.Record;

import java.util.concurrent.TimeUnit;

import static utils.Config.RECORDS;

public class Parse implements Runnable {
    private BattleField battleField;
    private UI ui;
    private Image endImage;

    Parse(BattleField battleField, UI ui) {
        this.battleField = battleField;
        this.ui = ui;
    }

    void act(){
        for (Record record : RECORDS) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (record.winTeam.equals("Good")) {
                endImage = new Image("image/win.png");
                ui.endPaint(endImage);
                break;
            } else if (record.winTeam.equals("Evil")) {
                endImage = new Image("image/lost.png");
                ui.endPaint(endImage);
                break;
            } else {
                if (record.action.equals("Move")) {
                    Position temp = battleField.theField[record.preX][record.preY].getCreature().getPosition();
                    battleField.theField[record.preX][record.preY].getCreature().setPosition(record.objX, record.objY);
                    battleField.theField[record.preX][record.preY].clear();
                    ui.repaint();
                } else if (record.action.equals("Battle")) {
                    Creature own=battleField.theField[record.preX][record.preY].getCreature();
                    Creature enemy=battleField.theField[record.objX][record.objY].getCreature();
                    own.isBattle = true;
                    enemy.isBattle = true;
                    ui.repaint();
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    own.isBattle = false;
                    enemy.isBattle = false;

                    if(record.ownLive==0){
                        own.goDead();
                    }
                    if(record.enemyLive==0){
                        enemy.goDead();
                    }
                    ui.repaint();
                }
            }
        }
    }

    @Override
    public void run() {
        act();
    }
}
