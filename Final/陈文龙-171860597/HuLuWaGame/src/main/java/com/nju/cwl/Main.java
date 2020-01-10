package com.nju.cwl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application
{

	private Button 读取文件button;
	private Button 开始游戏button;

	private ChoiceBox<String> 记录选择框 = new ChoiceBox();
	private ChoiceBox<String> 分辨率选择框 = new ChoiceBox();

	private Label logoLabel;
	private Label 记录选择Label;
	private Label 分辨率选择Label;
	File 分辨率文件 = new File("src/main/resources/分辨率文件/分辨率.txt");

	File file = new File("src/main/resources/战斗记录文件/");
	String[] files = file.list();
	Pane pane = new Pane();
	Scene scene;
	ImageView 背景图片;

	public void 初始化() throws IOException
	{
		List<String> 分辨率数组 = Files.readAllLines(Paths.get("src/main/resources/分辨率文件/分辨率.txt"));

		读取文件button = new Button("读取文件");
		读取文件button.setId("button_read");
		开始游戏button = new Button("开始游戏");
		开始游戏button.setId("button_start");
		开始游戏button.setLayoutX(Formation.M * 1);
		开始游戏button.setLayoutY(Formation.M * 2);
		读取文件button.setLayoutX(Formation.M * 1);
		读取文件button.setLayoutY(Formation.M * 3);
		开始游戏button.setId("startGame");
		读取文件button.setId("readFile");
		logoLabel = new Label("葫芦娃大战妖精");
		logoLabel.setFont(new Font(STYLESHEET_CASPIAN, 57.0));
		logoLabel.setLayoutX(Formation.M * 1);
		logoLabel.setLayoutY(Formation.M * 0.5);
		logoLabel.setId("logo");
		记录选择Label = new Label("请选择您的游戏记录：");
		记录选择Label.setTextFill(Color.RED);
		记录选择Label.setLayoutX(Formation.M * 2.5);
		记录选择Label.setLayoutY(Formation.M * 2.7);
		记录选择Label.setId("JLlabel");

		分辨率选择Label = new Label("请选择您的屏幕分辨率：");
		分辨率选择Label.setTextFill(Color.RED);
		分辨率选择Label.setLayoutX(Formation.M * 2.5);
		分辨率选择Label.setLayoutY(Formation.M * 1.7);
		分辨率选择Label.setId("FBlabel");

		分辨率选择框.getItems().addAll(分辨率数组);
		分辨率选择框.setId("F_choiceBox");
		分辨率选择框.getSelectionModel().select(2);/* 因为最多人用的就是 1980 X 1080 分辨率的屏幕 */
		分辨率选择框.setLayoutX(Formation.M * 2.5);
		分辨率选择框.setLayoutY(Formation.M * 2);

		记录选择框.getItems().addAll(files);
		记录选择框.setId("top_choiceBox");
		记录选择框.getSelectionModel().selectFirst();// 默认选中第一个
		记录选择框.setLayoutX(Formation.M * 2.5);
		记录选择框.setLayoutY(Formation.M * 3);

		背景图片 = new ImageView("file:src/main/resources/images/CCC.png");
		背景图片.setFitHeight(Formation.MainScene高度);
		背景图片.setFitWidth(Formation.MainScene宽度);
		背景图片.setLayoutX(0.0);
		背景图片.setLayoutY(0.0);
		pane.getChildren().add(背景图片);
		pane.getChildren().addAll(分辨率选择Label, 分辨率选择框, 开始游戏button, 读取文件button, logoLabel, 记录选择Label, 记录选择框);

//		分辨率选择框.getSelectionModel().getSelectedItem();/* 选择框的主角是这个getSelectionModel这个API，切记切记 */

		开始游戏button.setOnAction((a1) -> {
			try
			{
				进入开始界面();
			} catch (IOException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		});

		读取文件button.setOnAction((a1) -> {
			try
			{
				进入自动播放界面();
			} catch (IOException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		try
		{
			// FXCollections.observableArrayList(files)
			初始化();

			System.out.println(记录选择框.getSelectionModel().getSelectedItem());

			scene = new Scene(pane, Formation.MainScene宽度, Formation.MainScene高度);
			scene.getStylesheets().add("file:src/main/resources/CSS/Font.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	public void 进入开始界面() throws IOException
	{
		主界面.游戏开始 = false;
		new 主界面().Start(分辨率选择框.getSelectionModel().getSelectedItem());
	}

	public void 进入自动播放界面() throws IOException
	{
		System.out.println("进入main");
		System.out.println(记录选择框);
		System.out.println(记录选择框.getSelectionModel().getSelectedItem());
		String file = 记录选择框.getSelectionModel().getSelectedItem();
//		System.out.println();
		System.out.println(file);
//		自动播放界面.游戏开始 = false;
		new 自动播放界面().Start(file, 分辨率选择框.getSelectionModel().getSelectedItem());
	}

	public static void main(String[] args)
	{
		launch(args);
	}
	
	
}
