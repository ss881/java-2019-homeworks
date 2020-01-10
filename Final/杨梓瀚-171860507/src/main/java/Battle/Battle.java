package Battle;

import Creature.*;
import Formation.Formation;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
描述二维空间内的位置信息
 */

public class Battle {
    private static final int N = 12;    //Y

    private static final int M = 13;       //X

    private static ArrayList<Position<? extends Creature>>positionArrayList = new ArrayList<>(Collections.nCopies(N * M, null));

    public static boolean moveTo(Position<?> curPosition, int x, int y){
        int x1 = curPosition.getX(), y1 = curPosition.getY();
        if (getPosition(x, y) != null)
            return false;
        positionArrayList.set(x1 * N + y1, null);
        positionArrayList.set(x * N + y, curPosition);
        curPosition.setX(x);
        curPosition.setY(y);
        return true;
    }

    public static void setPosition(Position<?> p){
        int x = p.getX(), y = p.getY();
        positionArrayList.set(x * N + y, p);
        Creature creature = p.getHolder();
        creature.getImageView().setLayoutX(y * Fight.BLOCKPIXELY);
        creature.getImageView().setLayoutY(x * Fight.BLOCKPIXELX);
    }

    public static Position<?> getPosition(int x, int y){
        return positionArrayList.get(x * N + y);
    }

    /*
    return true if p1.distance < p2.distance
     */
    public static boolean cmpDistance(Position<?> myPosition, Position<?> p1, Position<?> p2){
        if (myPosition ==null || p1 == null || p2 == null){
            //System.out.println("111");
            return false;
        }
        int x = myPosition.getX(), y = myPosition.getY();
        int x1 = p1.getX(), y1 = p1.getY();
        int x2 = p2.getX(), y2 = p2.getY();
        return (Math.abs(x1 - x) + Math.abs(y1 - y)) < (Math.abs(x2 - x) + Math.abs(y2 - y));
    }

    public static Position<?> getPositionByInstance(Creature creature){
        for (Position<?> position : positionArrayList){
            if (position != null && position.getHolder() == creature)
                return position;
        }
        System.out.println("no position " + creature.toString());
        return null;
    }

    public static Position<?> findEnemy(Creature creature){
        Position<?> myPosition = getPositionByInstance(creature);
        assert myPosition != null;
        Position<?> position = null;
        for (int i = 0; i < M;i++){
            for (int j = 0; j < N; j++){
                if (getPosition(i, j) != null && creature.isEnemy(getPosition(i, j).getHolder()) && getPosition(i, j).getHolder().isCreatureAlive()){
                    if (position == null)
                        position = getPosition(i, j);
                    else{
                        if (!cmpDistance(myPosition, position, getPosition(i, j)))
                            position = getPosition(i, j);
                    }
                }
            }
        }
        assert position != null;
        return position;
    }

    public static int getN(){
        return N;
    }

    public static int getM(){
        return M;
    }

    public static void clearBattle(){
        for (int i = 0; i < N * M; i++){
            positionArrayList.set(i, null);
        }
    }
}
