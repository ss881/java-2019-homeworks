package com.calabash.view;

import com.calabash.MainCalabash;

import com.calabash.model.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;

public class BattlegroundOverviewController {
	@FXML
	private TableView<Formation> formationTable;
	@FXML
	private TableColumn<Formation, String> formationColumn;
	@FXML
	private GridPane battleground;
	
	private ImageView[][] battlegroundBuffers;
	
	private Battleground battlegroundModel;

	
	public static final int groundSize=15;
	
	public static final int imgSize=60;
	
	public static final String imgLocation="com/calabash/view/img/";
	
	public BattlegroundOverviewController() {
		
	}
	@FXML
	private void initialize() {
		initBattlegroundModel();
		formationColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getFormationName()));
		battlegroundBuffers=new ImageView[groundSize][groundSize];
		for (int i = 0; i < groundSize; i++) {
			for (int j = 0; j < groundSize; j++) {
				battlegroundBuffers[i][j]=new ImageView();
				battlegroundBuffers[i][j].setPreserveRatio(true);
				battlegroundBuffers[i][j].setFitHeight(imgSize);
				battleground.add(battlegroundBuffers[i][j], i, j);
			}
		}
		formationTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->showBattleground(newValue));
	}
	
	public void showBattleground(Formation formation) {
		for (int i = 0; i < groundSize; i++) {
			for (int j = 0; j < groundSize; j++) {
				battlegroundBuffers[i][j].setImage(null);
			}
		}
		
		battlegroundModel.getArmy(3).setFormation(formation);
		for (Army army : battlegroundModel.getGroundArmys()) {
			int xPos=army.getxPos();
			int yPos=army.getyPos();
			battlegroundBuffers[xPos][yPos].setImage(new Image(army.getCaptain().getImgUrl()));
			if (!army.hasSoiders()) {
				continue;
			}
			for (int i = 0; i < army.getArmySize(); i++) {
				battlegroundBuffers[xPos+army.getFormation().getxOffset(i)][yPos+army.getFormation().getyOffset(i)].setImage(new Image(army.getSoider(i).getImgUrl()));
			}
		}
	}
	public void setMainCalabash(MainCalabash mainCalabash) {
		formationTable.setItems(mainCalabash.getFormations());
	}
	public void initBattlegroundModel() {
		battlegroundModel=new Battleground();
		
		Army calabashArmy=new Army(Formation.fmt4,3,3);
		Army grandfaArmy=new Army(1,1);
		Army snakeArmy=new Army(1,13);
		Army scorpionArmy=new Army(Formation.fmt1,7,10);
		
		Calabash calabash1=new Calabash("Calabash1.jpg");
		Calabash calabash2=new Calabash("Calabash2.jpg");
		Calabash calabash3=new Calabash("Calabash3.jpg");
		Calabash calabash4=new Calabash("Calabash4.jpg");
		Calabash calabash5=new Calabash("Calabash5.jpg");
		Calabash calabash6=new Calabash("Calabash6.jpg");
		Calabash calabash7=new Calabash("Calabash7.jpg");
		Calabash grandfa=new Calabash("Grandfa.jpg");
		
		Monster scorpion=new Monster("scorpion.jpg");
		Monster snake=new Monster("Snake.jpg");
		Monster smallPotato=new Monster("smallPotato.jpg");
		
		calabashArmy.setCaptain(calabash1);
		grandfaArmy.setCaptain(grandfa);
		snakeArmy.setCaptain(snake);
		scorpionArmy.setCaptain(scorpion);
		
		calabashArmy.setSoiders(new Unit[]{calabash2,calabash3,calabash4,calabash5,calabash6,calabash7});
		scorpionArmy.setSoiders(new Unit[]{smallPotato});
		
		battlegroundModel.addArmy(grandfaArmy);
		battlegroundModel.addArmy(calabashArmy);
		battlegroundModel.addArmy(snakeArmy);
		battlegroundModel.addArmy(scorpionArmy);
	}
}
