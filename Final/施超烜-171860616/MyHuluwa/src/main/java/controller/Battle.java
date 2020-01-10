package controller;

import creature.*;
import history.Action;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import thread.*;

public class Battle {
    private Creature[] creatures;
    private CalabashBrother[] brothers;
    private Grandfather grandfather;
    private SnakeEssence snake;
    private ScorpionEssence scorpion;
    private Wannabe[] wannabes;
    private MyMap map;
    private Formation formation;
    private CreatureThread[] threads;
    private Canvas myCanvas;

    public Battle(Canvas canvas, ArrayList<Action> actions){
        this.creatures = new Creature[17]; //17生物体
        threads = new CreatureThread[17]; //为17个线程
        map = new MyMap(10,20); //创造一个10行20列的战场
        formation = new Formation();
        //创建线程
        //葫芦娃
        brothers = new CalabashBrother[7];
        this.myCanvas = canvas;
        for(int i  = 0; i < 7;i++) {
            brothers[i] = new CalabashBrother(i,-1,-1);
            threads[i] = new CreatureThread(brothers[i],map,myCanvas,actions); //线程
            creatures[i] = brothers[i];
        }
        //爷爷
        grandfather = new Grandfather();
        threads[7] = new CreatureThread(grandfather,map,myCanvas,actions);
        creatures[7] = grandfather;
        //蛇精
        snake = new SnakeEssence();
        threads[8] = new CreatureThread(snake,map,myCanvas,actions);
        creatures[8] = snake;
        //蝎子精
        scorpion = new ScorpionEssence();
        threads[9] = new CreatureThread(scorpion,map,myCanvas,actions);
        creatures[9] = scorpion;
        //小喽啰
        wannabes = new Wannabe[7];
        for(int i = 0; i < 7; i++) {
            wannabes[i] = new Wannabe();
            threads[10 + i] = new CreatureThread(wannabes[i],map,myCanvas,actions);
            creatures[i + 10] = wannabes[i];
        }
    }
    //初始化放置葫芦娃
    public void initBrothers() {
        Random rand =new Random();
        for(int i = 0; i  < 7;i++){//7次随机抽取打乱顺序
            int k = rand.nextInt(7);
            CalabashBrother temp = brothers[k];
            brothers[k] = brothers[i];
            brothers[i] = temp;
        }
        //将葫芦娃放置在战场上
        for(int i = 0; i <7;i++){
            map.putCreature(brothers[i],i+1,5);
        }
    }
    //初始化爷爷
    public void initGrandfather( )
    {
        map.putCreature(grandfather,5,0);
    }
    //初始化蛇精
    public void initMonster( )
    {
        map.putCreature(snake,5,19);
    }

    //改变阵型
    public void changeFormation(boolean t,FormationName name) {
        int index = name.ordinal();
        if(!t)
            formation.changeMonsterFormation(index,scorpion,wannabes,map); //改变小怪阵型
        else
            formation.changeCalabashFormation(index,this.brothers,map);
    }
    //打印
    public void printMap() {
        this.myCanvas.getGraphicsContext2D().clearRect(0, 0, 1100, 600); //清空画布
        this.myCanvas.getGraphicsContext2D().drawImage(new Image(this.getClass().getResourceAsStream("/pic/bg.jpg")),0,0,1000,500);
        //绘制生物
        for(int i = 0; i< 10 ;i++){
            for(int j = 0; j < 20;j++)
            {
                map.showCreature(i,j,this.myCanvas);
            }
        }
    }

    public void startFight(){
        this.map.setStart();
        for(int i = 0; i < 17 ; i++){
            this.threads[i].start();
        }
    }

    public void setMapStart(){
        this.map.setStart();
    }

    public MyMap getMap(){
        return this.map;
    }

    public void closeAll() {
        this.map.killAll();
    }

    public int[][] getInitMap(){
        return this.map.initMap(this.creatures);
    }

    public void initMap(int [][]map){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20;j++){
                if(map[i][j] != -1) {
                    int num = map[i][j];
                    if (num <= 6)
                        this.map.moveCreatureTo(brothers[num], i, j); //移动
                    else if (num == 7)
                        this.map.moveCreatureTo(this.grandfather, i, j);
                    else if (num == 8)
                        this.map.moveCreatureTo(this.snake, i, j);
                    else if (num == 9)
                        this.map.moveCreatureTo(this.scorpion, i, j);
                    else if (num < 17)
                        this.map.moveCreatureTo(this.wannabes[num - 10], i, j);
                }
            }
        }
    }
}
