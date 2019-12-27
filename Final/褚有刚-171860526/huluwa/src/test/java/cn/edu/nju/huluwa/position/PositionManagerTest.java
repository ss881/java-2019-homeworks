package cn.edu.nju.huluwa.position;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PositionManagerTest {

    PositionManager manager = new PositionManager();
    @Test
    public void getPositions() {
        Position[][] positions = manager.getPositions();
        for(int i = 0; i < positions.length; i++) {
            for(int j = 0; j < positions[i].length; j++) {
                assertTrue(positions[i][j].getPrevY() == null
                        || positions[i][j].getPrevY() == positions[i-1][j]);
                assertTrue(positions[i][j].getPrevX() == null
                        || positions[i][j].getPrevX() == positions[i][j-1]);
                assertTrue(positions[i][j].getNextY() == null
                        || positions[i][j].getNextY() == positions[i+1][j]);
                assertTrue(positions[i][j].getNextX() == null
                        || positions[i][j].getNextX() == positions[i][j+1]);
            }
        }
    }

    @Test
    public void getPosition() {
        Random random = new Random();
        for(int i = 0; i < 10000; i++) {
            manager.getPosition(random.nextInt(), random.nextInt());
        }
    }
}