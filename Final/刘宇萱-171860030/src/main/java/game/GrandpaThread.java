package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GrandpaThread extends Thread{
	private boolean alive = true;
	Grandpa grandpa;
	
	public GrandpaThread(int x, int y) {
		grandpa = new Grandpa(x, y);
	}
	
	@Override
	public void run() {
        while (alive) {
        	alive = Formation.nextStep(grandpa.mycoordinate);
        	
        	synchronized(Lock.lock) {
        		try {
        			Lock.lock.wait();
        		}
        		catch(InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
        }
        //System.out.println("grandpa end");
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
