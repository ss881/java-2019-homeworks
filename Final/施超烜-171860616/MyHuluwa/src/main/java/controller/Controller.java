package controller;

import history.Action;
import history.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import thread.UpdateThread;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private Canvas backGround;
    @FXML private Button changeGoodFormation;
    @FXML private Button changeBadFormation;
    @FXML private Button startNewGame;
    @FXML private Button readHistory;

    private Battle battle;
    private UpdateThread updateThread;
    private Init init;
    private ArrayList<Action> actions;
    private boolean ready;
    private boolean reviewing;
    private Writer writer;
    private FileReader reader;

    public void initialize(URL location, ResourceBundle resources) {
        init=null;
        actions=new ArrayList<Action>();
        ready=false;
        reviewing=false;
        backGround.getGraphicsContext2D().drawImage(new Image(this.getClass().getResourceAsStream("/pic/bg.jpg")),0,0,1000,500);
        initForBattle();
    }

    private void initForBattle(){
        if(ready)
            return;
        else{
            ready=true;
            reviewing=false;
        }
        battle=new Battle(backGround,actions);
        battle.initGrandfather();
        battle.initMonster();
        battle.initBrothers();
        battle.changeFormation(false,FormationName.HEYI);
        battle.printMap();
    }

    public void changeGoodFormation(ActionEvent event){
        if(reviewing||!(ready)) return;
        Random random=new Random();
        int index=random.nextInt(8);
        battle.changeFormation(true,FormationName.values()[index]);
        backGround.getGraphicsContext2D().clearRect(0,0,1000,500);
        backGround.getGraphicsContext2D().drawImage(new Image(this.getClass().getResourceAsStream("/pic/bg.jpg")),0,0,1000,500);
        backGround.getGraphicsContext2D().setStroke(Color.WHITE);
        for(int i=0;i<=10;i++)
            backGround.getGraphicsContext2D().strokeLine(0,i*50,1000,i*50);
        for(int j=0;j<=20;j++)
            backGround.getGraphicsContext2D().strokeLine(j*50,0,j*50,500);
        battle.printMap();
    }

    public void changeBadFormation(ActionEvent event){
        if(reviewing||!(ready)) return;
        Random random=new Random();
        int index=random.nextInt(8);
        battle.changeFormation(false,FormationName.values()[index]);
        backGround.getGraphicsContext2D().clearRect(0,0,1000,500);
        backGround.getGraphicsContext2D().setStroke(Color.WHITE);
        for(int i=0;i<=10;i++)
            backGround.getGraphicsContext2D().strokeLine(0,i*50,1000,i*50);
        for(int j=0;j<=20;j++)
            backGround.getGraphicsContext2D().strokeLine(j*50,0,j*50,500);
        battle.printMap();
    }

    public void startNewGame(ActionEvent event){
        initForBattle();
        if(!(ready)||reviewing) return;
        Date date=new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String currentTime=time.format(date);
        File saveFile=new File("src/main/resources/history/"+currentTime+".txt");
        try{
            saveFile.createNewFile();
            writer=new FileWriter(saveFile,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        init=new Init(battle.getInitMap());
        int[][] map=init.getMap();
        for(int i=0;i<10;i++){
            String line="";
            for(int j=0;j<20;j++){
                System.out.println(map[i][j]);
                line+=Integer.toString(map[i][j])+" ";
            }
            try {
                writer.write(line);
                writer.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateThread=new UpdateThread(backGround,battle,writer,actions,false,reader);
        updateThread.start();
        ready=false;
    }
    public void readHistory(ActionEvent event){
        if(!reviewing)
            reviewing=true;
        else
            return;
        FileChooser fileChooser=new FileChooser();
        File historyFile=null;
        try {
            historyFile=fileChooser.showOpenDialog(null);
        } catch (NullPointerException e) {
            System.out.println("未选择文件!");
            return;
        }
        try{
            reader=new FileReader(historyFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("未选择文件!");
            return;
        }
        battle=new Battle(backGround,actions);
        battle.setMapStart();
        updateThread=new UpdateThread(backGround,battle,writer,actions,true,reader);
        updateThread.start();
        reviewing=false;
    }
}
