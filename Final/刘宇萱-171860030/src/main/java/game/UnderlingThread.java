package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class UnderlingThread extends Thread {
	private boolean alive = true;
	Underling underling;
	//private int a;
	
	public UnderlingThread(int x, int y) {
		underling = new Underling(x, y);
		
		/*Random r = new Random();
		a = r.nextInt(100);*/
	}

	
	
	@Override
	public void run() {
        while (alive) {
            alive = Formation.nextStep(underling.mycoordinate);
        	
        	synchronized(Lock.lock) {
        		try {
        			Lock.lock.wait();
        		}
        		catch(InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
        }
        //System.out.println("under end");
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
