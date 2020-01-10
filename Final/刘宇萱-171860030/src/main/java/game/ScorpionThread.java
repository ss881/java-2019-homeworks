package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ScorpionThread extends Thread {
	private boolean alive = true;
	Scorpion scorpion;
	
	public ScorpionThread(int x, int y) {
		scorpion = new Scorpion(x, y);
	}

	
	@Override
	public void run() {
        while (alive) {
        	alive = Formation.nextStep(scorpion.mycoordinate);
    	
            synchronized(Lock.lock) {
            	try {
            		Lock.lock.wait();
            	}
            	catch(InterruptedException e) {
            		e.printStackTrace();
            	}
            }
        }
        //System.out.println("scorp end");
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
