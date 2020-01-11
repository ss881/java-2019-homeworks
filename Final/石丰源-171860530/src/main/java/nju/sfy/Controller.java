package nju.sfy;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nju.sfy.model.Field;
import nju.sfy.model.creature.Calabash;
import nju.sfy.model.creature.Grandpa;
import nju.sfy.model.creature.monster.Heeler;
import nju.sfy.model.creature.monster.Scorpion;
import nju.sfy.model.creature.monster.Snake;
import nju.sfy.model.dictionary.ColorDictionary;
import nju.sfy.model.dictionary.NameDictionary;
import nju.sfy.model.formation.Formation;
import nju.sfy.model.formation.FormationChangshe;
import nju.sfy.model.record.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Controller {
    private Field field;
    private ArrayList<Calabash> calabashes;
    private Grandpa grandpa;
    private ArrayList<Heeler> heelers;
    private Snake snake;
    private Scorpion scorpion;
    private Formation goodTeam;
    private Formation badTeam;

    private ArrayList<Thread> threads = new ArrayList<Thread>();

    private Canvas canvas;
    private TextArea textArea;
    private GridPane gridPane;
    private Stage primaryStage;

    public Controller(Canvas canvas, TextArea textArea, GridPane gridPane, Stage primaryStage) throws MalformedURLException {
        this.canvas = canvas;
        this.textArea = textArea;
        this.gridPane = gridPane;
        this.primaryStage = primaryStage;

        init();
    }
    public boolean isReplayMode() {
        return field.isReplayMode();
    }
    public void setReplayMode(boolean replayMode){
        field.setReplayMode(replayMode);
    }
    public boolean isRunning() {
        return field.isRunning();
    }
    public void setRunning(boolean running){
        field.setRunning(running);
    }
    public boolean isCompleted(){
        return field.isCompleted();
    }
    public void setCompleted(boolean completed){
        field.setCompleted(completed);
    }
    public Field getField(){
        return field;
    }

    public void init() throws MalformedURLException {
        calabashes = new ArrayList<>();
        for(int i = 1; i <= 7; i++){
            calabashes.add(new Calabash(NameDictionary.getName(i), ColorDictionary.getColor(i), i));
        }

        String path = "src/main/resources/";
        for(int i = 1; i <= 7; i++){
            String imagePath = path + i + ".png";
            File file = new File(imagePath);
            Image  image = new Image(file.toURI().toURL().toString());
            String imagePath2 = path + i + "0.png";
            File file2 = new File(imagePath2);
            Image  image2 = new Image(file2.toURI().toURL().toString());
            calabashes.get(i - 1).setImage(image);
            calabashes.get(i - 1).setImage2(image2);
            calabashes.get(i - 1).setCanvas(canvas);
            calabashes.get(i-1).setTextArea(textArea);
        }

        grandpa = new Grandpa();
        String imagePath = path + "yeye.png";
        File file = new File(imagePath);
        Image  image = new Image(file.toURI().toURL().toString());
        String imagePath2 = path + "yeye0.png";
        File file2 = new File(imagePath2);
        Image  image2 = new Image(file2.toURI().toURL().toString());
        grandpa.setImage(image);
        grandpa.setImage2(image2);
        grandpa.setCanvas(canvas);
        grandpa.setTextArea(textArea);


        heelers = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            heelers.add(new Heeler());
        }
        for(int i = 0; i < 30; i++){
            imagePath = path + "hamajing.png";
            file = new File(imagePath);
            image = new Image(file.toURI().toURL().toString());
            imagePath2 = path + "hamajing0.png";
            file2 = new File(imagePath2);
            image2 = new Image(file2.toURI().toURL().toString());
            heelers.get(i).setImage(image);
            heelers.get(i).setImage2(image2);
            heelers.get(i).setCanvas(canvas);
            heelers.get(i).setTextArea(textArea);
        }


        snake = new Snake();
        imagePath = path + "shejing1.png";
        file = new File(imagePath);
        image = new Image(file.toURI().toURL().toString());
        imagePath2 = path + "shejing10.png";
        file2 = new File(imagePath2);
        image2 = new Image(file2.toURI().toURL().toString());
        snake.setImage(image);
        snake.setImage2(image2);
        snake.setCanvas(canvas);
        snake.setTextArea(textArea);

        scorpion = new Scorpion();
        imagePath = path + "xiezijing.png";
        file = new File(imagePath);
        image = new Image(file.toURI().toURL().toString());
        imagePath2 = path + "xiezijing0.png";
        file2 = new File(imagePath2);
        image2 = new Image(file2.toURI().toURL().toString());
        scorpion.setImage(image);
        scorpion.setImage2(image2);
        scorpion.setCanvas(canvas);
        scorpion.setTextArea(textArea);

        field = new Field(canvas, calabashes, grandpa, heelers, snake, scorpion, threads);

        String className = "FormationYulin";
        try {
            Class<?> FormationClass = Class.forName("nju.sfy.model.formation." + className);
            Formation f = (Formation) FormationClass.getDeclaredConstructor().newInstance();

            goodTeam = new FormationChangshe();
            goodTeam.setField(field);
            goodTeam.setTeam(calabashes);
            goodTeam.setCheeuper(grandpa);
            goodTeam.setLeader(null);

            badTeam = f;
            badTeam.setField(field);
            badTeam.setTeam(heelers);
            badTeam.setCheeuper(snake);
            badTeam.setLeader(scorpion);

            field.setNumOfHeelers(badTeam.getNumberOfMembers());
            changeFormation();
        }
        catch (ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void play() throws MalformedURLException, AWTException {
        textArea.appendText("Game Start!!!\n");

        for(int i = 1; i <= 7; i++){
            threads.add(new Thread(calabashes.get(i - 1)));
        }
        threads.add(new Thread(grandpa));
        threads.add(new Thread(snake));
        threads.add(new Thread(scorpion));
        for(int i = 0; i < badTeam.getNumberOfMembers(); i++){
           threads.add(new Thread(heelers.get(i)));
        }

        threads.add(new Thread(grandpa));

        threads.add(new Thread(new ScreenRecorder(gridPane, field, primaryStage)));

        for(int i = 0; i < calabashes.size(); i++){
            Calabash c = calabashes.get(i);
            textArea.appendText(c.getName() + " is created at (" + c.getTile().getX() + "," + c.getTile().getY() + ")\n");
        }
        textArea.appendText(grandpa.getName() + " is created at (" + grandpa.getTile().getX() + "," + grandpa.getTile().getY() + ")\n");

        textArea.appendText(snake.getName() + " is created at (" + snake.getTile().getX() + "," + snake.getTile().getY() + ")\n");
        textArea.appendText(scorpion.getName() + " is created at (" + scorpion.getTile().getX() + "," + scorpion.getTile().getY() + ")\n");
        for(int i = 0; i < badTeam.getNumberOfMembers(); i++){
            Heeler h = heelers.get(i);
            textArea.appendText(h.getName() + " is created at (" + h.getTile().getX() + "," + h.getTile().getY() + ")\n");
        }
        draw();

        for(Thread t : threads){
            t.start();
        }
    }
    private void show(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        field.show();
        grandpa.cheerUp();
        snake.cheerUp();
    }
    private void draw() throws MalformedURLException {
        field.draw();
    }
    private void changeFormation() throws ClassNotFoundException {
        field.clear();
        goodTeam.arrangePosition();
        badTeam.arrangePosition();
    }
}
