package chessboard;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import record.CreatureInfo;
import record.FrameInfo;
import chessman.creature.Creature;
import chessman.creature.CreatureState;
import chessman.creature.Pawn;
import chessman.creature.Justice;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BattleField implements Constants{
    private Grid[][] grids;
    public final int N;
    public final int M;

    private ExecutorService battleEventThreadPool;

    public BattleField(int r, int c) {
        N = r;
        M = c;
        grids = new Grid[r][c];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grids[i][j] = new Grid();
            }
        }
    }

    //only here can others change field chessmen allocation
    public boolean setCreatrue(Creature creature, int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M)
            return false;
        if (grids[x][y].setCreature(creature)) {
            creature.setPosition(x, y);
            return true;
        } else {
            return false;
        }
    }

    //instead of showMap()
    @Override
    public String toString() {
        String ret = "BattleField\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grids[i][j].existCreature()) {
                    ret += (grids[i][j].getCreatrue().getName() + "\t");
                } else {
                    ret += "*\t";
                }
            }
            ret += "\n";
        }
        return ret;
    }

    public void clearAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grids[i][j].clearCreature();
            }
        }
    }

    public void clearCreature(int x, int y) {
        grids[x][y].clearCreature();
    }

    public boolean existGoodCreature(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        } else {
            if (grids[x][y].existCreature() && grids[x][y].getCreatrue() instanceof Justice) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean existBadCreature(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        } else {
            if (grids[x][y].existCreature() && grids[x][y].getCreatrue() instanceof Pawn) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void guiDisplay(Canvas canvas, ArrayList<FrameInfo> frameInfos) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        FrameInfo frameInfo = new FrameInfo();

        /* paint creatures*/
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grids[i][j].existCreature()) {
                    Creature creature = grids[i][j].getCreatrue();
                    Image image = creature.getImage();
                    gc.drawImage(image, j* GRIDWIDTH, i* GRIDHEIGHT, PicLength, PicLength);

                    CreatureInfo creatureInfo = new CreatureInfo(creature.getName(), i, j);
                    creatureInfo.setState(creature.getState());

                    if (creature.getState() != CreatureState.DEAD) {
                        gc.setLineWidth(0);
                        double pct = creature.getHPPCT();
                        creatureInfo.setHpPCT(pct);
                        gc.setFill(Color.GREEN);
                        gc.fillRect(j* GRIDWIDTH, i* GRIDHEIGHT -3, PicLength*pct, 5);
                        gc.setFill(Color.RED);
                        gc.fillRect(j* GRIDWIDTH +PicLength*pct, i* GRIDHEIGHT -3, PicLength*(1-pct), 5);
                    }

                    /* add record per frame */
                    frameInfo.creatureInfos.add(creatureInfo);
                }
            }
        }


        if (frameInfos != null) {
            frameInfos.add(frameInfo);
        }
    }

    public Creature getCreature(int x, int y) {
        return grids[x][y].getCreatrue();
    }

    public void setEventThreadPool(ExecutorService battleEventThreadPool) {
        this.battleEventThreadPool = battleEventThreadPool;
    }

    public void createBattleEvent(Creature cala, Creature mons) {
        battleEventThreadPool.execute(new BattleEvent(cala, mons));
    }

}