package Main;

import Command.Command;
import Creature.Creature;
import Field.Field;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainCanvas extends Canvas {
    private GraphicsContext gc;
    private Image BK;
    Command command;
    AnimationTimer timer;
    AnimationTimer loader;
    Field field;
    String filename;
    FileOutputStream file;
    OutputStreamWriter writer;
    BufferedWriter bufferedWriter;
    FileInputStream inputStream;
    InputStreamReader reader;
    BufferedReader bufferedReader;

    MainCanvas() throws FileNotFoundException {
        super(800, 400);
        try {
            BK = new Image(new FileInputStream("resources/background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        gc = getGraphicsContext2D();
        command=new Command();
        drawBackground();
    }
    public void Init(){
           timer=new AnimationTimer() {
           @Override
           public void handle(long now) {
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               if(command.isEnd()){
                   try {
                       bufferedWriter.write("Fin");
                       bufferedWriter.newLine();
                       drawField();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   System.out.println("GAME OVER!");
                   try {
                       bufferedWriter.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   stop();
               }
               else{
                   try {
                       bufferedWriter.write("Con");
                       bufferedWriter.newLine();
                       drawField();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
       };
       loader=new AnimationTimer(){
           @Override
           public void handle(long now) {
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
                drawBackground();
                String str;
                boolean flag=true;
                try {
                    if (((str=bufferedReader.readLine())!=null)) {
                        //System.out.println(str);
                        if(str.equals("Con")){
                            str=bufferedReader.readLine();
                        }
                        else{
                            str=bufferedReader.readLine();
                            flag=false;
                        }

                        String[] subStr=str.split(" ");
                        for(int i=0;i<subStr.length;i+=3){
                            int x=TranslateXY(Integer.parseInt(subStr[i]));
                            int y=TranslateXY(Integer.parseInt(subStr[i+1]));
                            String fileName=subStr[i+2];
                            //System.out.println(fileName);
                            gc.drawImage(new Image(new FileInputStream(fileName)),x,y);
                        }
                        if(!flag) {
                            System.out.println("Replay Over!");
                            stop();
                        }
                    }
                } catch (IOException e) {
                       e.printStackTrace();
                }
           }
       };
    }
    public void replay(File file) throws FileNotFoundException {
        System.out.println("Replay!");
        inputStream=new FileInputStream(file);
        reader=new InputStreamReader(inputStream);
        bufferedReader= new BufferedReader(reader);
        Init();
        loader.start();
    }
    public void newGame() throws IOException {
        System.out.println("NEW GAME!");
        Init();
        command.Init();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String time=dateTime.format(formatter);
        filename ="record\\"+time+".txt";
        file=new FileOutputStream(filename);
        writer=new OutputStreamWriter(file);
        bufferedWriter= new BufferedWriter(writer);
        bufferedWriter.write("Con");
        bufferedWriter.newLine();
        drawField();
    }
    public void Start() throws FileNotFoundException, InterruptedException {
        timer.start();
        command.Start();
    }
    private void drawField() throws IOException {
        drawBackground();
        field=command.getField();
        for(int i=0;i<20;i++)
            for(int j=0;j<10;j++){
                Creature creature=field.getCreature(i,j);
                if(creature!=null) {
                    drawCreature(creature);
                    int x=creature.getX();
                    int y=creature.getY();
                    String faceName=creature.getFaceName();
                    bufferedWriter.write(String.valueOf(x));
                    bufferedWriter.write(" ");
                    bufferedWriter.write(String.valueOf(y));
                    bufferedWriter.write(" ");
                    bufferedWriter.write(faceName);
                    bufferedWriter.write(" ");
                }
            }
        bufferedWriter.newLine();
    }
    private void drawBackground() {
        gc.drawImage(BK, 0, 0);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.save();
        gc.strokeLine(0, 0, 800, 0);
        gc.strokeLine(0, 40, 800, 40);
        gc.strokeLine(0, 80, 800, 80);
        gc.strokeLine(0, 120, 800, 120);
        gc.strokeLine(0, 160, 800, 160);
        gc.strokeLine(0, 200, 800, 200);
        gc.strokeLine(0, 240, 800, 240);
        gc.strokeLine(0, 280, 800, 280);
        gc.strokeLine(0, 320, 800, 320);
        gc.strokeLine(0, 360, 800, 360);
        gc.strokeLine(0, 400, 800, 400);

        gc.strokeLine(0, 0, 0, 400);
        gc.strokeLine(40, 0, 40, 400);
        gc.strokeLine(80, 0, 80, 400);
        gc.strokeLine(120, 0, 120, 400);
        gc.strokeLine(160, 0, 160, 400);
        gc.strokeLine(200, 0, 200, 400);
        gc.strokeLine(240, 0, 240, 400);
        gc.strokeLine(280, 0, 280, 400);
        gc.strokeLine(320, 0, 320, 400);
        gc.strokeLine(360, 0, 360, 400);
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeLine(400, 0, 400, 400);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(440, 0, 440, 400);
        gc.strokeLine(480, 0, 480, 400);
        gc.strokeLine(520, 0, 520, 400);
        gc.strokeLine(560, 0, 560, 400);
        gc.strokeLine(600, 0, 600, 400);
        gc.strokeLine(640, 0, 640, 400);
        gc.strokeLine(680, 0, 680, 400);
        gc.strokeLine(720, 0, 720, 400);
        gc.strokeLine(760, 0, 760, 400);
        gc.strokeLine(800, 0, 800, 400);
    }
    private void drawCreature(Creature creature){
        int x=TranslateXY(creature.getX());
        int y=TranslateXY(creature.getY());
        gc.drawImage(creature.getFace(),x,y);
        if(!creature.isAlive()){
            field.setNull(creature.getX(),creature.getY());
        }
    }
    private int TranslateXY(int xy){
        return xy*40;
    }
}
