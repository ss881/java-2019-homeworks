package main.java.sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements MyObserver{

    Controller Ctrl;
    Pane sp;
    ImageView grid;
    Scene scene;
    List<ImageView> imageList;
    File gameLog;
    @Override
    public void init() throws Exception {
        super.init();
        sp=new Pane();
        imageList=new ArrayList<>();
        grid=new ImageView("/main/resources/grid.png");
        scene = new Scene(sp, 600, 600);

        System.out.println("Inside init() method! Perform necessary initializations here.");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        grid.setFitHeight(600);
        grid.setFitWidth(600);
        sp.getChildren().addAll(grid);
        
        ImageView[] hulu=new ImageView[7];
        for(int i=0;i<7;i++)
        {
            hulu[i]=new ImageView("/main/resources/hulu.png");
            hulu[i].setFitWidth(40);
            hulu[i].setFitHeight(40);
            hulu[i].setX(0);
            hulu[i].setY(40*(i+1));
            imageList.add(hulu[i]);
            sp.getChildren().add(hulu[i]);
        }
        
        ImageView grandpa=new ImageView("/main/resources/grandp.png");
        grandpa.setFitHeight(40);
        grandpa.setFitWidth(40);
        grandpa.setX(0);
        grandpa.setY(40*9);
        imageList.add(grandpa);
        sp.getChildren().add(grandpa);

        ImageView[] imps=new ImageView[5];
        for(int i=0;i<5;i++)
        {
            imps[i]=new ImageView("/main/resources/imp.png");
            imps[i].setFitWidth(40);
            imps[i].setFitHeight(40);
            imps[i].setX(80);
            imps[i].setY(40*(i+1));
            imageList.add(imps[i]);
            sp.getChildren().add(imps[i]);
        }

        ImageView snake=new ImageView("/main/resources/snake.png");
        snake.setFitHeight(40);
        snake.setFitWidth(40);
        snake.setX(520);
        snake.setY(160);
        imageList.add(snake);
        sp.getChildren().add(snake);

        ImageView scorpion=new ImageView("/main/resources/scorp.png");
        scorpion.setFitHeight(40);
        scorpion.setFitWidth(40);
        scorpion.setX(480);
        scorpion.setY(200);
        imageList.add(scorpion);
        sp.getChildren().add(scorpion);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                //start war

                Creature register=new Creature(-1,-1,-1,'R',true,-1,-1,-1);
                register.registerObserver(this);

                gameLog=new File("1.gamelog");
                int fileNum=1;
                while(gameLog.exists())
                {
                    fileNum++;
                    gameLog=new File((fileNum+"") +".gamelog");
                }
                try {
                    gameLog.createNewFile();
                } catch (IOException ex) {
                    System.out.println("File not exist!");
                    ex.printStackTrace();
                }
                Ctrl=new Controller(false);
                Ctrl.gameStart();
                //TODO smooth
                //System.out.println("Space key pressed");
            }
            else if(e.getCode()==KeyCode.L)
            {
                //save file

                Replayer register=new Replayer(gameLog,this);
                Ctrl=new Controller(true);
                FileChooser fc = new FileChooser();
                fc.setTitle("Open one gamelog");
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HuluWars Gamelog", "*.gamelog"));
                gameLog = fc.showOpenDialog(null);
                if(gameLog!=null)
                {
                   Ctrl.gameReplay(gameLog,this);
                }
                //System.out.println("L");
            }
        });
        primaryStage.setTitle("HuluBoys War by Liu Haowen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Inside stop() method! Destroy resources. Perform Cleanup.");
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void routeUpdate(RouteNotifier rn) {
        ImageView whoIsMoving =imageList.get(rn.id);
        whoIsMoving.setX(rn.tarX*40);
        whoIsMoving.setY(rn.tarY*40);
    }
    @Override
    public void battleUpdate(BattleNotifier bn) {
        ImageView whoIsKilled=imageList.get(bn.killed_id);
        whoIsKilled.setX(-40);
        whoIsKilled.setY(-40);//TEMP TODO
    }

    @Override
    public void gameLogPrint(int type, int a, int b, int c, int d) throws IOException {
        if(type == 0)//move a==id b,c==tarX,Y
        {
            String input = "MOV " + a + " " + b + " " + c + '\n';
            FileWriter fileWriter = new FileWriter(gameLog.getName(), true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(input);
            bufferWriter.close();
        }
        else if(type == 1)//kill a=killerid b=killed id
        {
            String input = "KIL " + a + " " + b + '\n';
            FileWriter fileWriter = new FileWriter(gameLog.getName(), true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(input);
            bufferWriter.close();
        }
    }
}
