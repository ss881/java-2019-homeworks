package gui;

import administer.*;
import creature.*;
import formation.Formation;
import formation.FormationFactory;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Main;
import playrecord.Record;
import playrecord.PlayRecorder;
import util.GameStatus;

import java.io.File;
import java.util.ArrayList;

import static util.GameStatus.*;
import static util.GameStatus.GAME_OVER;

public class ChessBoard extends Canvas {
    private GameStatus statusType =READY;
    public GameStatus getGameStatus(){return statusType;}
    public void setGameStatus(GameStatus g){ statusType =g; }
    public long startTime;
    private boolean isSavePlay=false;
    private File recordFile =null;

    public static final int START_X =200;
    public static final int START_Y =80;
    public static final int CellXSize =70;
    public static final int CellYSize =90;
    public static final int ImageWidth=60;
    public static final int ImageHeight=80;
    public static final int BulletWidth=30;
    public static final int BulletHeight=30;

    public static final int row=5;
    public static final int column=10;

    private GraphicsContext g =getGraphicsContext2D();

    private static Image imageBackground=new Image("/background.jpg", Main.BOARD_WIDTH,Main.BOARD_HEIGHT,true,false);
    private static Image imageElder=new Image("/Elder.png",ImageWidth,ImageHeight,true,true);
    private static Image imageBro1 =new Image("/calabashbrother1.png",ImageWidth, ImageHeight,true,true);
    private static Image imageBro2 =new Image("/calabashbrother2.png",ImageWidth, ImageHeight,true,true);
    private static Image imageBro3 =new Image("/calabashbrother3.png",ImageWidth, ImageHeight,true,true);
    private static Image imageBro4 =new Image("/calabashbrother4.png",ImageWidth, ImageHeight,true,true);
    private static Image imageBro5 =new Image("/calabashbrother5.png",ImageWidth, ImageHeight,true,true);
    private static Image imageBro6 =new Image("/calabashbrother6.png",ImageWidth, ImageHeight,true,true);
    private static Image imageBro7 =new Image("/calabashbrother7.png",ImageWidth, ImageHeight,true,true);
    private static Image imageGoblin =new Image("/goblin.png",ImageWidth, ImageHeight,true,true);
    private static Image imageScorpion =new Image("/scorpion.png",ImageWidth, ImageHeight,true,true);
    private static Image imageSnake=new Image("/snake.png",ImageWidth, ImageHeight,true,true);
    private static Image imageGoodDeath =new Image("/goodDeath.png",ImageWidth, ImageHeight,true,true);
    private static Image imageBadDeath =new Image("/badDeath.png",ImageWidth, ImageHeight,true,true);
    private static Image imageGameOver=new Image("/gameover.png",Main.BOARD_WIDTH/3,Main.BOARD_HEIGHT/3,true,true);
    private static Image imageBullet=new Image("/bullet.png",BulletWidth,BulletHeight,true,true);
    private static Image imagePangolin=new Image("/pangolin.png",ImageWidth, ImageHeight,true,true);

    private chessboard.ChessBoard chessBoard =new chessboard.ChessBoard(row,column);
    private GoodCreatureAdminister goodCreatureAdminister =new GoodCreatureAdminister();
    private BadCreatureAdminister badCreatureAdminister =new BadCreatureAdminister();
    private FormationFactory formationFactory=new FormationFactory();
    private ThreadAdminister threadAdminister=new ThreadAdminister();
    private RecordAdminister recordAdminister =new RecordAdminister();
    private FormationAdminister formationAdminister=new FormationAdminister();

    private BulletAdminister bulletAdminister=new BulletAdminister();
    private Thread threadBullet=new Thread(bulletAdminister);


    public RecordAdminister getRecordAdminister(){return recordAdminister;}
    public FormationAdminister getFormationAdminister(){return formationAdminister;}
    public BulletAdminister getBulletAdminister(){return bulletAdminister;}

    public void setIsSavePlay(boolean s){
        isSavePlay=s;
    }
    public boolean isSavePlay(){return isSavePlay;}
    public void setPlayBackFile(File f){
        recordFile =f;
    }

    public ChessBoard(double width, double height){
        super(width,height);
        getReady();
        paint();
    }

    public void getReady(){
        recordAdminister.clearAll();
        chessBoard.clear();
        goodCreatureAdminister.initial();
        badCreatureAdminister.initial();
        threadAdminister.clearAll();
        bulletAdminister.clear();

        int index1=formationAdminister.getBadFormationIndex();
        int index2=formationAdminister.getGoodFormationIndex();

        Formation formation;
        formation=formationFactory.create(formationAdminister.getBadFormationIndex(), formationAdminister.getArg1BadFormation(index1), formationAdminister.getArg2BadFormation(index1),-1);
        ArrayList<Goblin> goblins = badCreatureAdminister.getGoblins();
        ArrayList<Creature> badCreatures=new ArrayList<>();
        for(Goblin m:goblins)
            badCreatures.add(m);
        badCreatures.add(badCreatureAdminister.getScorpion());
        badCreatures.add((badCreatureAdminister.getSnake()));
        formation.pubFormationOnBoard(chessBoard, badCreatures);
        formation = formationFactory.create(formationAdminister.getGoodFormationIndex(), formationAdminister.getArg1GoodFormation(index2),formationAdminister.getArg2GoodFormation(index2) ,1);
        ArrayList<CalabashBrother> brothers = goodCreatureAdminister.getHuLuWas();
        ArrayList<Creature> goodones=new ArrayList<>();
        for(CalabashBrother h:brothers)
            goodones.add(h);
        goodones.add(goodCreatureAdminister.getElder());
        formation.pubFormationOnBoard(chessBoard, goodones);
        for(Creature c:goodones)
            threadAdminister.addOne(new Thread(c));
        for(Creature c:badCreatures)
            threadAdminister.addOne(new Thread(c));
        paint();
    }

    public void allMoveUp(){
        startTime=System.currentTimeMillis();
        threadAdminister.startAll();
        threadBullet=new Thread(bulletAdminister);
        threadBullet.start();
    }

    public boolean isOver(){
        if(goodCreatureAdminister.isAllDie()|| badCreatureAdminister.isAllDie()){
            return true;
        }
        else{
            return false;
        }
    }

    private void myPaint(){
        g.clearRect(0,0,getWidth(),getHeight());
        g.drawImage(imageBackground,0,0);
        synchronized (bulletAdminister.getBullets()){
            for(int i=0;i<bulletAdminister.getBullets().size();i++){
                Bullet b=bulletAdminister.getBullets().get(i);
                g.drawImage(imageBullet, b.getD_x(), b.getD_y());
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                Being temp= chessBoard.getSquare(i,j).getBeing();
                if(temp!=null){
                    Image var=null;
                    switch (temp.getStyleImage()){
                        case BROTHER_ONE:var= imageBro1;break;
                        case BROTHER_TWO:var= imageBro2;break;
                        case BROTHER_THREE:var= imageBro3;break;
                        case BROTHER_FOUR:var= imageBro4;break;
                        case BROTHER_FIVE:var= imageBro5;break;
                        case BROTHER_SIX:var= imageBro6;break;
                        case BROTHER_SEVEN:var= imageBro7;break;
                        case ELDER:var= imageElder;break;
                        case GOBLIN:var= imageGoblin;break;
                        case SCORPION:var= imageScorpion;break;
                        case SNAKE:var=imageSnake;break;
                        case PEA_POD:var= imagePangolin;break;
                        default:assert(1==0);break;
                    }

                    if(temp instanceof BadCreature){
                        BadCreature badtemp=(BadCreature)temp;
                        if(badtemp.isAlive()==false){
                            var= imageBadDeath;
                        }
                    }
                    if(temp instanceof GoodCreature){
                        GoodCreature goodtemp=(GoodCreature)temp;
                        if(goodtemp.isAlive()==false){
                            var= imageGoodDeath;
                        }
                    }
                    double opacity = 1;
                    if(var!= imageBadDeath && var!= imageGoodDeath){
                        Creature creaturetemp=(Creature)temp;
                        opacity=(double)creaturetemp.getHp()/creaturetemp.getFullHp();
                        if(opacity<=0.3)
                            opacity=0.3;
                    }
                    g.setGlobalAlpha(opacity);
                    g.drawImage(var, START_X +j* CellXSize, START_Y +i* CellYSize);
                    g.setGlobalAlpha(1);

                    //画血条
                    if(temp instanceof Creature){
                        Creature creature=(Creature)temp;
                        if(creature.isAlive()==true){
                            g.setFill(Color.RED);
                            double rate=creature.getHp()/((double)creature.getFullHp());
                            g.fillRect(START_X +j* CellXSize + CellXSize /2, START_Y +i* CellYSize,(int)(rate*(CellXSize /3)),8);
                            g.setFill(Color.BLACK);
                            g.strokeRect(START_X +j* CellXSize + CellXSize /2, START_Y +i* CellYSize, CellXSize /3,8);
                        }
                    }
                }
            }
        }
        if(goodCreatureAdminister.isAllDie()|| badCreatureAdminister.isAllDie()) {
            synchronized (statusType) {
                if (statusType == RANDOM) {
                    statusType = GAME_OVER;
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    g.drawImage(imageGameOver, START_X + 3 * CellXSize, START_Y + CellYSize / 4);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    statusType = GAME_OVER;
                } else if (statusType == PLAY_RECORD) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    g.drawImage(imageGameOver, START_X + 3 * CellXSize, START_Y + CellYSize / 4);
                    statusType = GAME_OVER;
                } else if (statusType == GAME_OVER) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    g.drawImage(imageGameOver, START_X + 3 * CellXSize, START_Y + CellYSize / 4);
                }
            }
        }
    }
    public void paint(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myPaint();
            }
        });
    }
    public chessboard.ChessBoard getChessBoard(){
        return chessBoard;
    }
    public void gameSuspend(){
        threadAdminister.interruptAll();
    }

    public void saveFile(File file){
        if(isSavePlay==true && file!=null){
            ArrayList<Integer> index=new ArrayList<>();
            index.add(formationAdminister.getGoodFormationIndex());
            index.add(formationAdminister.getBadFormationIndex());
            PlayRecorder.writeRecords(recordAdminister.getCommands(),index,file);
        }
        isSavePlay=false;
        recordFile =null;
    }

    public void playRecord(File file){
        ArrayList<Integer> index=new ArrayList<>();
        ArrayList<Record> recordsTmp=new ArrayList<>();
        PlayRecorder.readRecords(recordsTmp,index,file);
        formationAdminister.setGoodFormationIndex(index.get(0));
        formationAdminister.setBadFormationIndex(index.get(1));
        getReady();
        threadBullet=new Thread(bulletAdminister);
        threadBullet.start();
        recordAdminister.setCommands(recordsTmp);
        System.err.println("playback commands size:"+recordsTmp.size());
        PlayRecorder p=new PlayRecorder(recordAdminister.getCommands(), chessBoard);
        Thread t=new Thread(p);
        t.start();
    }
}
