package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeThread extends Thread {
	private boolean alive = true;
	Snake snake;
	
	public SnakeThread(int x, int y) {
		snake = new Snake(x, y);
	}

	
	@Override
	public void run() {
        while (alive) {
        	alive = Formation.nextStep(snake.mycoordinate);
        	
        	synchronized(Lock.lock) {
        		try {
        			Lock.lock.wait();
        		}
        		catch(InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
        }
        //System.out.println("snake end");
        MainCanvas.num2Dec();
    }
	
	
	public void killed() {
		synchronized(Formation.class) {
			alive = false;
		}
	}
	
	public boolean alive() {
		synchronized(Formation.class) {
			return alive;
		}
	}
}
