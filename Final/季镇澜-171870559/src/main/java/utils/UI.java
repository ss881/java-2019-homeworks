package utils;

import battlefield.BattleField;
import creature.Creature;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static utils.Config.*;

public class UI {
    private Scene scene;
    private Canvas canvas;
    private BattleField battleField;

    UI(BattleField battleField) {
        this.battleField = battleField;
        Pane root = new Pane();

        BackgroundImage imbk = new BackgroundImage(new Image("image/background.png"), null, null, null, null);
        Background background = new Background(imbk);
        root.setBackground(background);

        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }

    public void clear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH, HEIGHT);
    }

    void paintLines(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.rgb(0,0,0));
        gc.setLineWidth(1);
        for (int i = 0; i <= WIDTH; i += 50) {
            gc.beginPath();
            gc.moveTo(i, 0);
            gc.lineTo(i, HEIGHT);
            gc.stroke();
        }
        for (int i = 0; i <= HEIGHT; i += 50) {
            gc.beginPath();
            gc.moveTo(0, i);
            gc.lineTo(WIDTH, i);
            gc.stroke();
        }
    }

    public void paint(){
        paintLines();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(!battleField.theField[i][j].isEmpty()){
                    Creature creature=battleField.theField[i][j].getCreature();
                    gc.drawImage(creature.getImage(),i*SIZE,j*SIZE,SIZE,SIZE);
                    if (creature.isBattle){
                        gc.setLineWidth(3);
                        gc.setStroke(Color.rgb(255,0,0));
                        gc.strokeRect(i*SIZE,j*SIZE,SIZE,SIZE);
                    }
                }
            }
        }
    }

    public void repaint(){
        clear();
        paint();
    }

    public void initPaint(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(new Image("image/start.png"),300,200,200,200);
    }

    public void endPaint(Image image){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image,300,200,200,200);
    }
}
