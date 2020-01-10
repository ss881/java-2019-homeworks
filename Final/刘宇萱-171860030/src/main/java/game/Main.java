package game;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		
		Image myimage = new Image(getClass().getResourceAsStream("picture/bg1.png"));
        ImageView imageview = new ImageView(myimage);
        root.getChildren().add(imageview);
        
        MainCanvas maincanvas = new MainCanvas(1200, 800);
        
        /*maincanvas.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
		    public void handle(KeyEvent event) {
				System.out.print("handle");
				if(!MainCanvas.start) {
					if(event.getCode() == KeyCode.SPACE){
						System.out.print("start");
						MainCanvas.start = true;
			            
						maincanvas.mainthread.start();
			        }
			        else if (event.getCode() == KeyCode.L) {
			        	MainCanvas.start = true;
			        }
				}
				
		    }
		});*/
        
		root.getChildren().add(maincanvas);
		
		Button startbutton = new Button("¿ªÊ¼ÓÎÏ·");
		startbutton.setOnAction((ActionEvent e) -> {
			maincanvas.gameStart();
		});
		root.getChildren().add(startbutton);
		AnchorPane.setTopAnchor(startbutton, 750.0);
		AnchorPane.setLeftAnchor(startbutton, 100.0);
        
		Scene scene = new Scene(root, 1200, 800);
        //scene.setFill(pattern);
        
        primaryStage.setTitle("Calabashbrothers vs Goblins");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
