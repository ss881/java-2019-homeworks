import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import Control.GameLogic;

public class Main extends Application{
	 private GameLogic Mycontrol = new GameLogic();
	    @Override
	    public void start(Stage startStage) {
	    	startStage.setTitle("葫芦娃大战妖精");
	    	startStage.setResizable(false);
	    	startStage.getIcons().add(new Image("icon.jpg"));
	    	startStage.setScene(new Scene(Mycontrol.guiControl.createContent()));
	    	startStage.setOnCloseRequest((WindowEvent e) -> System.exit(0));
	    	startStage.show();
	        Mycontrol.initGame("鹤翼");
	    }

	    public static void main(String[] args) {
	        launch(args);
	    	//String temp = System.getProperty("user.dir");
	    	//System.out.println(temp);
	    }
}
