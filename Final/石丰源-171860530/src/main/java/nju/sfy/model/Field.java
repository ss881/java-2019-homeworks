package nju.sfy.model;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import nju.sfy.model.creature.Calabash;
import nju.sfy.model.creature.Grandpa;
import nju.sfy.model.creature.monster.Heeler;
import nju.sfy.model.creature.monster.Scorpion;
import nju.sfy.model.creature.monster.Snake;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Field {
    //private static int N = 12;
    private int i = 0;
    private final int N = 10;
    private Tile [][] tiles;
    private Canvas canvas;

    private ArrayList<Calabash> calabashes;
    private Grandpa grandpa;
    private ArrayList<Heeler> heelers;
    private Snake snake;
    private Scorpion scorpion;
    private int numOfHeelers;

    private boolean running = false;
    private boolean replayMode = false;
    private boolean completed = false;
    private boolean goodWin = false;
    private boolean badWin = false;

    private ArrayList<Thread> threads = null;

    public Field(){ //for test
        tiles = new Tile[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j] = new Tile(i, j);
            }
        }
    }
    public Field(Canvas canvas, ArrayList<Calabash> calabashes, Grandpa grandpa, ArrayList<Heeler> heelers, Snake snake, Scorpion scorpion, ArrayList<Thread> threads) throws MalformedURLException {
        this.canvas = canvas;
        this.calabashes = calabashes;
        this.grandpa = grandpa;
        this.heelers = heelers;
        this.snake = snake;
        this.scorpion = scorpion;
        this.threads = threads;

        String path = "src/main/resources/";
        File file1 = new File(path + "tile1.png");
        Image image1 = new Image(file1.toURI().toURL().toString());
        File file2 = new File(path + "tile2.png");
        Image image2 = new Image(file2.toURI().toURL().toString());

        tiles = new Tile[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j] = new Tile(i, j);
                tiles[i][j].setCanvas(canvas);
                if((i + j) % 2 == 0){
                    tiles[i][j].setImage(image1);
                }
                else{
                    tiles[i][j].setImage(image2);
                }
            }
        }
    }

    public boolean isBadWin() {
        return badWin;
    }
    public boolean isGoodWin() {
        return goodWin;
    }
    public boolean isReplayMode() {
        return replayMode;
    }
    public void setReplayMode(boolean replayMode){
        this.replayMode = replayMode;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running){
        this.running = running;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    public void setNumOfHeelers(int num){
        numOfHeelers = num;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    public Canvas getCanvas() {
        return canvas;
    }
    public int getN(){
        return N;
    }
    public Tile[][] getTiles(){
        return tiles;
    }
    public void show(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j].show();
            }
            System.out.println();
        }
    }
    public /*synchronized */void draw() {            //必须是synchronized
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j].draw();
            }
        }
        isGameOver();
        /*
        Platform.runLater(() -> {
                WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
                i = i + 1;
                String path = "record/" +  i + ".png";
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        */
        /*
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);

        if(file != null){
            try {
                WritableImage writableImage = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                Logger.getLogger(JavaFX_DrawOnCanvas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
        /*
        File file = new File("replay.png");
        if(!file.exists()){
            file.createNewFile();
        }
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        WritableImage snapshot = canvas.snapshot(new SnapshotParameters(), writableImage);
        ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
         */
    }
    private void isGameOver(){
        goodWin = true;
        badWin = true;
        for(int i = 0; i < calabashes.size(); i++){
            if(!calabashes.get(i).isDead())
                badWin = false;
        }
        if(!grandpa.isDead()){
            badWin = false;
        }
        for(int i = 0; i < numOfHeelers; i++){
            if(!heelers.get(i).isDead()){
                goodWin = false;
            }
        }
        if(!snake.isDead()){
            goodWin = false;
        }
        if(!scorpion.isDead()){
            goodWin = false;
        }
        if(goodWin || badWin){
            System.out.println("游戏结束");
            canvas.getGraphicsContext2D().setStroke(Color.BLACK);
            canvas.getGraphicsContext2D().strokeText("游戏结束!!!", canvas.getWidth() / 2, canvas.getHeight() / 2);
            running = false;
            completed = true;
            for(Thread t : threads){
                t.interrupt();
            }
        }
    }
    public void clear(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j].clear();
            }
        }
    }
    public boolean isInField(int x, int y){
        return (x >= 0 && x < N && y >= 0 && y < N);
    }
}

