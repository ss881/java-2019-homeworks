package Command;

import Creature.*;
import Field.Field;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Command {
    Field field;        //场地
    Gourd []gourds;     //葫芦娃
    Old old;            //老爷爷
    Scorpion scorpion;  //蝎子精
    Minions []minions;  //小喽啰
    Snake snake;        //蛇精
    private Image[] T;
    private Image[] DT;
    private Image[] F;
    private Image[] DF;
    ArrayList<Thread> goodThread;
    ArrayList<Thread> evilThread;

    private void Creat(){
        field=new Field();

        gourds=new Gourd[7];
        old=new Old();

        scorpion=new Scorpion();
        minions=new Minions[6];
        snake=new Snake();
    }
    private void LongSnake(){
        gourds[0]=new Gourd();
        gourds[1]=new Gourd();
        gourds[2]=new Gourd();
        gourds[3]=new Gourd();
        gourds[4]=new Gourd();
        gourds[5]=new Gourd();
        gourds[6]=new Gourd();
        gourds[0].initPos(5,2,field);
        for(int i=1;i<7;i++){
            gourds[i].LookForward(gourds[0],i);
        }
    }
    private void CraneWing(){
        scorpion.initPos(15,5,field);
        minions[0]=new Minions();
        minions[1]=new Minions();
        minions[2]=new Minions();
        minions[3]=new Minions();
        minions[4]=new Minions();
        minions[5]=new Minions();
        for(int i=0;i<6;i++){
            minions[i].CraneWing(scorpion,i);
        }
    }
    private void InitPos(){
        LongSnake();
        CraneWing();
        old.initPos(7,5,field);
        snake.initPos(13,5,field);
    }
    private void InitFace()throws FileNotFoundException {
        try {
            T=new Image[8];
            T[0]=new Image(new FileInputStream("resources/1.png"));
            T[1]=new Image(new FileInputStream("resources/2.png"));
            T[2]=new Image(new FileInputStream("resources/3.png"));
            T[3]=new Image(new FileInputStream("resources/4.png"));
            T[4]=new Image(new FileInputStream("resources/5.png"));
            T[5]=new Image(new FileInputStream("resources/6.png"));
            T[6]=new Image(new FileInputStream("resources/7.png"));
            T[7]=new Image(new FileInputStream("resources/Old.png"));

            F=new Image[3];
            F[0]=new Image(new FileInputStream("resources/Xie.png"));
            F[1]=new Image(new FileInputStream("resources/Snake.png"));
            F[2]=new Image(new FileInputStream("resources/Little.png"));

            DT=new Image[8];
            DT[0]=new Image(new FileInputStream("resources/D1.png"));
            DT[1]=new Image(new FileInputStream("resources/D2.png"));
            DT[2]=new Image(new FileInputStream("resources/D3.png"));
            DT[3]=new Image(new FileInputStream("resources/D4.png"));
            DT[4]=new Image(new FileInputStream("resources/D5.png"));
            DT[5]=new Image(new FileInputStream("resources/D6.png"));
            DT[6]=new Image(new FileInputStream("resources/D7.png"));
            DT[7]=new Image(new FileInputStream("resources/DOld.png"));

            DF=new Image[3];
            DF[0]=new Image(new FileInputStream("resources/DXie.png"));
            DF[1]=new Image(new FileInputStream("resources/DSnake.png"));
            DF[2]=new Image(new FileInputStream("resources/DLittle.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        gourds[0].setFaceName("resources/1.png","resources/D1.png");
        gourds[1].setFaceName("resources/2.png","resources/D2.png");
        gourds[2].setFaceName("resources/3.png","resources/D3.png");
        gourds[3].setFaceName("resources/4.png","resources/D4.png");
        gourds[4].setFaceName("resources/5.png","resources/D5.png");
        gourds[5].setFaceName("resources/6.png","resources/D6.png");
        gourds[6].setFaceName("resources/7.png","resources/D7.png");
        old.setFaceName("resources/Old.png","resources/DOld.png");
        for(int i=0;i<6;i++) {
            minions[i].setFaceName("resources/Little.png","resources/DLittle.png");
        }
        scorpion.setFaceName("resources/Xie.png","resources/DXie.png");
        snake.setFaceName("resources/Snake.png","resources/DSnake.png");

        for(int i=0;i<7;i++){
            gourds[i].setFace(T[i],DT[i]);
        }
        old.setFace(T[7],DT[7]);
        for(int i=0;i<6;i++){
            minions[i].setFace(F[2],DF[2]);
        }
        scorpion.setFace(F[0],DF[0]);
        snake.setFace(F[1],DF[1]);
    }
    private void InitThread(){
        goodThread=new ArrayList<Thread>(8);
        evilThread=new ArrayList<Thread>(8);

        for(int i=0;i<7;i++)
            goodThread.add(new Thread(gourds[i]));
        goodThread.add(new Thread(old));

        for(int i=0;i<6;i++)
            evilThread.add(new Thread(minions[i]));
        evilThread.add(new Thread(scorpion));
        evilThread.add(new Thread(snake));
    }
    public void Init() throws FileNotFoundException {
        Creat();
        InitPos();
        InitFace();
        InitThread();
    }
    public void Start() throws FileNotFoundException, InterruptedException {
        goodThread.forEach(Thread::start);
        evilThread.forEach(Thread::start);
        //goodThread.get(0).start();
    }
    public boolean isEnd(){
      boolean T=false;
      boolean F=false;
      for(int i=0;i<7;i++)
              T=T|gourds[i].isAlive();
      T=T|old.isAlive();

      for(int i=0;i<6;i++)
          F=F|minions[i].isAlive();
      F=F|scorpion.isAlive();
      F=F|snake.isAlive();
      if(!(T&F)){
          for(int i=0;i<8;i++){
              if(!goodThread.get(i).isInterrupted()){
                  goodThread.get(i).stop();
              }
              if(!evilThread.get(i).isInterrupted()){
                  evilThread.get(i).stop();
              }
          }
      }
      return !(T&F);

    }
    public Field getField(){
        return field;
    }
}
