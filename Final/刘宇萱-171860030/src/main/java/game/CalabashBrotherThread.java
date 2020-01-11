package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CalabashBrotherThread extends Thread {
	private boolean alive = true;
	CalabashBrother calabashbrother;
	
	public CalabashBrotherThread(Color c, int r, int x, int y) {
		calabashbrother = new CalabashBrother(c, r, x, y);
	}
	
	@Override
	public void run() {
        while (alive) {
        	alive = Formation.nextStep(calabashbrother.mycoordinate);
        	
        	synchronized(Lock.lock) {
        		try {
        			Lock.lock.wait();
        		}
        		catch(InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
        }
        //System.out.println("cala end");
        MainCanvas.num1Dec();
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
