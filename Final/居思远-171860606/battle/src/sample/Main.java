package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import Map.Map;
import Creature.*;
import Formation.*;
import GameStart.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Application {

    static public int status = 0;
    static public boolean pause = false;
    static public Map map=new Map();
    static public Hulu hulu[]=new Hulu[7];
    static public Grandpa grandpa;
    static public Snake snake;
    static public Scorpion scorpion;
    static public Monster monster[]=new Monster[7];
    GameStart startgg = new GameStart();
    Circle formation;
    static Pane root = new Pane();
    static Image image[]=new Image[18];
    static public int dieX[]=new int[30];
    static public int dieY[]=new int[30];
    static public int dieNumber = 0;
    static ImageView imageView[][]=new ImageView[15][15];
    static public Thread t[] = new Thread[17];
    static Button btn[] = new Button[15];

    static Label label1 = new Label("葫芦娃阵营胜利！");
    static Label label2 = new Label("蛇精阵营胜利！");

    @Override
    public void start(Stage primaryStage) throws Exception{

        prepare();
        /*
        btn1.setOnAction(oa->{
            System.out.println("????????????");
        });
        btn1.setLayoutX(0);
        btn1.setLayoutY(0);
        root.getChildren().add(btn1);
        */
        root.setPrefHeight(750);
        root.setPrefWidth(750);


        // 将pane加入到Scen中
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("葫芦娃vs蛇精");
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);

    }

    public void prepare()
    {
        for (int jk = 0; jk < 15; jk++) {
            for (int jt = 0; jt < 15; jt++)
                Main.map.setMap(jk, jt, -1);
        }
        for(int jk=0;jk<15;jk++) {
            for (int jt = 0; jt < 15; jt++) {
                imageView[jk][jt] = new ImageView();
                imageView[jk][jt].setLayoutX(jk*50);
                imageView[jk][jt].setLayoutY(jt*50);
                root.getChildren().add(imageView[jk][jt]);
            }
        }

        label1.setVisible(false);
        label1.setLayoutX(325);
        label1.setLayoutY(325);
        label1.setScaleX(5);
        label1.setScaleY(5);
        root.getChildren().add(label1);

        label2.setVisible(false);
        label2.setLayoutX(325);
        label2.setLayoutY(325);
        label2.setScaleX(5);
        label2.setScaleY(5);
        root.getChildren().add(label2);

        btn[1]=new Button("重置棋盘！");
        btn[1].setVisible(false);
        btn[1].setOnAction(oa->{
            dieNumber=0;
            startgg.gameStart(hulu,grandpa,snake,scorpion,monster,formation);
            for(int jk=0;jk<7;jk++)
            {
                t[jk]=new Thread(hulu[jk]);
            }
            t[7]=new Thread(grandpa);
            for(int jk=0;jk<7;jk++)
            {
                t[jk+8]=new Thread(monster[jk]);
            }
            t[15]=new Thread(scorpion);
            t[16]=new Thread(snake);

            Creature.resetCreature();

            drawByMap();
            label1.setVisible(false);
            label2.setVisible(false);
            btn[0].setVisible(true);
            btn[1].setVisible(false);
            for(int jk=2;jk<=7;jk++)
                btn[jk].setVisible(true);
        });
        btn[1].setLayoutX(500);
        btn[1].setLayoutY(700);
        root.getChildren().add(btn[1]);

        btn[0]=new Button("开始游戏！");
        btn[0].setOnAction(oa->{
            btn[0].setVisible(false);
            btn[8].setVisible(true);
            for(int jk=2;jk<=7;jk++)
                btn[jk].setVisible(false);
            Main.status =1;
            for(int jk=0;jk<17;jk++)
            {
                t[jk].start();
            }
        });
        btn[0].setLayoutX(325);
        btn[0].setLayoutY(700);
        root.getChildren().add(btn[0]);

        btn[2]=new Button("长蛇阵");
        btn[2].setOnAction(oa->{
            huluLongSnake();
        });
        btn[2].setLayoutX(0);
        btn[2].setLayoutY(0);
        root.getChildren().add(btn[2]);

        btn[3]=new Button("长蛇阵");
        btn[3].setOnAction(oa->{
            monsterLongSnake();
        });
        btn[3].setLayoutX(700);
        btn[3].setLayoutY(0);
        root.getChildren().add(btn[3]);



        btn[4]=new Button("方圆阵");
        btn[4].setOnAction(oa->{
            huluCircle();
        });
        btn[4].setLayoutX(0);
        btn[4].setLayoutY(25);
        root.getChildren().add(btn[4]);

        btn[5]=new Button("方圆阵");
        btn[5].setOnAction(oa->{
            monsterCircle();
        });
        btn[5].setLayoutX(700);
        btn[5].setLayoutY(25);
        root.getChildren().add(btn[5]);


        btn[6]=new Button("锋矢阵");
        btn[6].setOnAction(oa->{
            huluArrow();
        });
        btn[6].setLayoutX(0);
        btn[6].setLayoutY(50);
        root.getChildren().add(btn[6]);

        btn[7]=new Button("锋矢阵");
        btn[7].setOnAction(oa->{
            monsterArrow();
        });
        btn[7].setLayoutX(700);
        btn[7].setLayoutY(50);
        root.getChildren().add(btn[7]);

        btn[8]=new Button("暂停");
        btn[8].setOnAction(oa->{
            btn[8].setVisible(false);
            btn[9].setVisible(true);
            pause=true;
        });
        btn[8].setLayoutX(325);
        btn[8].setLayoutY(25);
        btn[8].setVisible(false);
        root.getChildren().add(btn[8]);

        btn[9]=new Button("继续");
        btn[9].setOnAction(oa->{
            pause=false;
            btn[9].setVisible(false);
            btn[8].setVisible(true);
        });
        btn[9].setLayoutX(325);
        btn[9].setLayoutY(25);
        btn[9].setVisible(false);
        root.getChildren().add(btn[9]);

        image[0] = new Image("file:墓碑.png");
        image[1] = new Image("file:老大.png");
        image[2] = new Image("file:老二.png");
        image[3] = new Image("file:老三.png");
        image[4] = new Image("file:老四.png");
        image[5] = new Image("file:老五.png");
        image[6] = new Image("file:老六.png");
        image[7] = new Image("file:老七.png");
        image[8] = new Image("file:喽啰.png");
        image[9] = new Image("file:爷爷.png");
        image[10] = new Image("file:蝎子精.png");
        image[11] = new Image("file:蛇精.png");
        image[12] = new Image("file:背景.png");

        hulu[0]=new Hulu(1,1,"大娃","红");
        hulu[1]=new Hulu(2,2,"二娃","橙");
        hulu[2]=new Hulu(3,3,"三娃","黄");
        hulu[3]=new Hulu(4,4,"四娃","绿");
        hulu[4]=new Hulu(5,5,"五娃","青");
        hulu[5]=new Hulu(6,6,"六娃","蓝");
        hulu[6]=new Hulu(7,7,"七娃","紫");     //id 1-7,葫芦娃
        grandpa = new Grandpa(8);       //id = 8，老爷爷
        for(int jk=0;jk<7;jk++)
            monster[jk] = new Monster(jk+9);                   //id 9-15,小怪
        scorpion = new Scorpion(16);     //id = 16，蝎子精
        snake = new Snake(17);       //id = 17，蛇精

        for(int jk=0;jk<7;jk++)
        {
            t[jk]=new Thread(hulu[jk]);
        }
        t[7]=new Thread(grandpa);
        for(int jk=0;jk<7;jk++)
        {
            t[jk+8]=new Thread(monster[jk]);
        }
        t[15]=new Thread(scorpion);
        t[16]=new Thread(snake);

        Circle formation = new Circle(hulu[0]);
        startgg.gameStart(hulu,grandpa,snake,scorpion,monster,formation);

        drawByMap();
    }

    static public void drawByMap()
    {

        for(int jk=0;jk<15;jk++)
        {
            for(int jt=0;jt<15;jt++)
            {
                if(Main.map.getContent(jk,jt)==-1)
                {
                    imageView[jk][jt].setImage(image[12]);
                }
                else if(Main.map.getContent(jk,jt)>=1 && Main.map.getContent(jk,jt)<= 7)
                {
                    imageView[jk][jt].setImage(image[Main.map.getContent(jk,jt)]);
                }
                else if(Main.map.getContent(jk,jt)>=9 && Main.map.getContent(jk,jt)<= 15)
                {
                    imageView[jk][jt].setImage(image[8]);
                }
                else if(Main.map.getContent(jk,jt)==8)
                {
                    imageView[jk][jt].setImage(image[9]);
                }
                else if(Main.map.getContent(jk,jt)==16)
                {
                    imageView[jk][jt].setImage(image[10]);
                }
                else if(Main.map.getContent(jk,jt)==17)
                {
                    imageView[jk][jt].setImage(image[11]);
                }

                else
                    imageView[jk][jt].setImage(image[0]);
            }
        }

        for(int jk=0;jk<dieNumber;jk++)
        {
            if(Main.map.getContent(dieX[jk],dieY[jk])==-1)
                imageView[dieX[jk]][dieY[jk]].setImage(image[0]);
        }
    }



    public void huluCircle()
    {
        Circle c=new Circle(hulu[0]);
        c.lay(hulu[0]);
        drawByMap();
    }

    public void monsterCircle()
    {
        Circle c=new Circle(monster[0]);
        c.lay(monster[0]);
        drawByMap();
    }

    public void huluLongSnake()
    {
        LongSnake c=new LongSnake(hulu[0]);
        c.lay(hulu[0]);
        drawByMap();
    }

    public void monsterLongSnake()
    {
        LongSnake c=new LongSnake(monster[0]);
        c.lay(monster[0]);
        drawByMap();
    }

    public void huluArrow()
    {
        Arrow c=new Arrow(hulu[0]);
        c.lay(hulu[0]);
        drawByMap();
    }

    public void monsterArrow()
    {
        Arrow c=new Arrow(monster[0]);
        c.lay(monster[0]);
        drawByMap();
    }

    static public void endBattle(int camp)
    {
        btn[1].setVisible(true);
        btn[8].setVisible(false);
        if(camp==0)
            label1.setVisible(true);
        else
            label2.setVisible(true);

    }



}

