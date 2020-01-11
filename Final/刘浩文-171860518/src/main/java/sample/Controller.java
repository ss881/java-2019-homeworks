package main.java.sample;

import java.io.File;

public class Controller {
    private Thread[] huluboys;
    private Thread[] imps;
    private Thread grandpa,snake,scorpion;

    public Thread replayer;
    public Controller(boolean isReplay)
    {
        if(isReplay) return;
        new Creature();
        huluboys=new Thread[7];
        huluboys[0]=new Thread(new Creature(0,1,0,'A',true,50,20,10),"Lao Da");
        huluboys[1]=new Thread(new Creature(0,2,1,'B',true,50,20,10),"Lao Er");
        huluboys[2]=new Thread(new Creature(0,3,2,'C',true,50,20,10),"Lao San");
        huluboys[3]=new Thread(new Creature(0,4,3,'D',true,50,20,10),"Lao Si");
        huluboys[4]=new Thread(new Creature(0,5,4,'E',true,50,20,10),"Lao Wu");
        huluboys[5]=new Thread(new Creature(0,6,5,'F',true,50,20,10),"Lao Liu");
        huluboys[6]=new Thread(new Creature(0,7,6,'G',true,50,20,10),"Lao Qi");
        grandpa=new Thread(new Creature(0,9,7,'O',true,100,0,15),"Grandpa");

        imps=new Thread[5];
        imps[0]=new Thread(new Creature(2,1,8,'J',false,30,30,5),"Imps");
        imps[1]=new Thread(new Creature(2,2,9,'K',false,30,30,5),"Imps");
        imps[2]=new Thread(new Creature(2,3,10,'L',false,30,30,5),"Imps");
        imps[3]=new Thread(new Creature(2,4,11,'M',false,30,30,5),"Imps");
        imps[4]=new Thread(new Creature(2,5,12,'N',false,30,30,5),"Imps");

        snake=new Thread(new Creature(13,4,13,'S',false,70,10,10),"Snake");
        scorpion=new Thread(new Creature(12,5,14,'X',false,100,30,0),"Scorpion");
    }
    public void gameStart()
    {
        for(int i=0;i<7;i++) huluboys[i].start();
        for(int i=0;i<5;i++) imps[i].start();

        grandpa.start();
        snake.start();
        scorpion.start();

    }
    public void gameReplay(File gamelog,MyObserver o)
    {
        replayer=new Thread(new Replayer(gamelog,o),"Replayer");
        replayer.start();
    }
}
