package Control;

import BattleField.*;
import Position.Position;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.util.*;

public class GUIControl{

    public Pane pane = new Pane();
    public Timeline timeline = new Timeline(); 
    private Text title;

    private ArrayList<ImageView> crossArray = new ArrayList<>();
    private final ImageView cross = new ImageView(new Image("fight.png", 50, 50, false, true));

    Timer refreshTimer = new Timer(); //刷新屏幕的线程

    private BattleField battlefield;
    private VBox navigation; 
    
    GUIControl(BattleField battlefield, VBox navigation) {
        this.battlefield = battlefield;
        this.navigation = navigation;
    }
    
    public synchronized void refresh() {
        pane.getChildren().clear();
        pane.getChildren().addAll(title, navigation);
        
        for(int i = 0; i < BattleField.Height; i++) {
            for(int j = 0; j < BattleField.Width; j++) {
                if(battlefield.board[i][j].getCreature() != null) {
                    ImageView view = battlefield.board[i][j].getCreature().getImage();
                    view.setX(j * 50 + 100);
                    view.setY(i * 50 + 100);
                    pane.getChildren().add(view);
                }
            }
        }
        for(ImageView view : crossArray)
            pane.getChildren().add(view);
        crossArray.clear();
    }

    //创建时间线
    private Timeline createTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);

        KeyFrame begin = new KeyFrame(Duration.ZERO);
        KeyFrame end = new KeyFrame(Duration.minutes(4));
        timeline.getKeyFrames().addAll(begin, end);
        return timeline;
    }

    private Text createTitle() {
        Text title = new Text("葫芦娃大战妖精");
        title.setX(350.0);
        title.setY(50.0);
        title.setCache(true);
        title.setFont(Font.font("Verdana", 40));
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(3);
        title.setFill(Color.SNOW);
        return title;
    }

    public Parent createContent() {
        timeline = createTimeline();
        title = createTitle();
        pane.setPrefSize(1000, 650);
        pane.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        pane.getChildren().addAll(title, navigation);
        pane.setBackground(new Background(new BackgroundImage(battlefield.getBG(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        				   BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        //pane.getStylesheets().add("Style.css");
        return pane;
    }

    void addCross(Position start, Position neighbor) {
        if (neighbor.getX() == start.getX()) {
            cross.setY(start.getX() * 50 + 100);
            cross.setX((neighbor.getY() + start.getY()) * 25 + 100);
        } else {
            cross.setX(start.getY() * 50 + 100);
            cross.setY((neighbor.getX() + start.getX()) * 25 + 100);
        }
        crossArray.add(cross);
    }
}