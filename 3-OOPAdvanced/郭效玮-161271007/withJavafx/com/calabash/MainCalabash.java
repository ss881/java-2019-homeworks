package com.calabash;

import java.io.IOException;

import com.calabash.model.Formation;
import com.calabash.view.BattlegroundOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainCalabash extends Application {
	
	public static final int formationNum=8;
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private ObservableList<Formation> formations=FXCollections.observableArrayList();
	public MainCalabash() {
		for (int i = 1; i <= formationNum; i++) {
			formations.add(Formation.getFormation(i));
		}
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
		this.primaryStage=primaryStage;
		primaryStage.setTitle("Calabash");
		
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(MainCalabash.class.getResource("view/BattlegroundOverview.fxml"));
		rootLayout=(AnchorPane) loader.load();
		
		Scene scene=new Scene(rootLayout);
//		scene.getStylesheets().add(MainCalabash.class.getResource("view/Calabash.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		BattlegroundOverviewController battlegroundOverviewController=loader.getController();
		battlegroundOverviewController.setMainCalabash(this);
	}
	
	

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public ObservableList<Formation> getFormations() {
		return formations;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
