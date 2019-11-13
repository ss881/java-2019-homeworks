package com.swt.view.draw;

import com.swt.model.advance.SmallEnemy;
import com.swt.model.controlled.EnemyTeam;
import com.swt.model.controlled.NMap;
import javafx.scene.canvas.GraphicsContext;

public class DrawEnemyTeam extends DrawBase {
    private EnemyTeam enemyTeam;
    public DrawEnemyTeam(NMap nMap, GraphicsContext graphicsContext, EnemyTeam enemyTeam) {
        super(nMap, graphicsContext);
        this.enemyTeam = enemyTeam;
    }

    @Override
    public void draw() {
        drawPic(enemyTeam.getScorpion().getPicture());
        for(SmallEnemy smallEnemy: enemyTeam.getSmallEnemyList()){
            drawPic(smallEnemy.getPicture());
        }
        drawPic(enemyTeam.getSnake().getPicture());
        drawPic(enemyTeam.getSnake().getOrderPicture());
    }
}
