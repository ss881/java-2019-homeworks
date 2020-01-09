//package main.java;

public class GameStateCheck extends Thread {
    @Override
    public void run(){
        Long  start=System.currentTimeMillis();
        Long  end=System.currentTimeMillis();
        while(true){
            if(Thread.currentThread().isInterrupted())
                break;
            if(Math.abs(start-end)>=100) {
                Game.checkState();
                start=System.currentTimeMillis();
                end=System.currentTimeMillis();
            }
            else{
                end=System.currentTimeMillis();
            }
        }
    }
}
