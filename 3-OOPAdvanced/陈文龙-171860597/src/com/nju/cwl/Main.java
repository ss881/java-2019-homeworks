package com.nju.cwl;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 *  游戏名： 葫芦娃大战妖精 
 *  版本：1.1
 * @作者 ：  我喜欢你家孩子呀~
 * 联系方式： 1019070879@qq.com
 * 修改日期: 2019年9月27日   
 * 时间: 下午3:35:22
 */
public class Main extends Application
{
	
	boolean hlwBlocked= false;
	boolean isMonster = false; 
	boolean isSelected = false;
	Label tip = new Label("⚠️：点击方阵展示，也可点击角色随意移动！⚠️");
	public static final int HUMAN = 1;
	public static final int MONSTER = 2;
	public int humanOrMonster = 1;
	Role moveRole = null;
	boolean isMoved = false;
	int theRealX = -1;
	int theRealY = -1;
	Text textHP = new Text();
	Label labelMP = new Label();
	ImageView info_bottom = new ImageView("file:images/info_buttom.jpeg");
	ImageView info_mid = new ImageView("file:images/info_mid1.jpeg");
	ImageView info = new ImageView("file:images/brother1_info.png");
	ImageView people = new ImageView("file:images/brother1.png");
	Ellipse myEllipse;
	Pane pane = new Pane();
	private double posX;
	private double posY;
	ArrayList<Role> humanList = new ArrayList<>();
	ArrayList<Role> monsterList = new ArrayList<>();
	Formation[] formations = new Formation[7]; // 四个阵型
	Thread[] threads = null;
	String[] backgrounds = { "file:images/background1.jpg", "file:images/background2.jpg",
			"file:images/background3.jpg", "file:images/background4.jpg", "file:images/background5.jpg",
			"file:images/background6.jpg", "file:images/background7.jpg", "file:images/background8.jpg", };
	Random random = new Random();

	public void initImage()
	{
		info_bottom.setFitHeight(5 * Formation.M);
		info_bottom.setFitWidth(Formation.right_distance);
		info_bottom.setLayoutX(10 * Formation.M);
		info_bottom.setLayoutY(5 * Formation.M);

		info_mid.setFitHeight(2 * Formation.M);
		info_mid.setFitWidth(Formation.right_distance);
		info_mid.setLayoutX(10 * Formation.M);
		info_mid.setLayoutY(3 * Formation.M);

		info.setFitHeight(2 * Formation.M);
		info.setFitWidth(Formation.right_distance);
		info.setLayoutX(10 * Formation.M);
		info.setLayoutY(1 * Formation.M);

		people.setFitHeight(2 * Formation.M);
		people.setFitWidth(Formation.right_distance);
		people.setLayoutX(10 * Formation.M);
		people.setLayoutY(3 * Formation.M);

		textHP.setLayoutX(10 * Formation.M);
		textHP.setLayoutY(5.25 * Formation.M);

		textHP.setText("Name: " + humanList.get(0).name + "\n" + "HP: " + humanList.get(0).nowHP + "\n" + "MP: "
				+ humanList.get(0).nowMP + "\nMaxHP: " + humanList.get(0).maxHP + "\nMaxMP:" + humanList.get(0).maxMP);
		textHP.setFont(Font.font("Tahoma", 25));
		textHP.setFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new Stop[] { new Stop(0, Color.AQUA),
				new Stop(0.3f, Color.RED), new Stop(0.5f, Color.BLUE), new Stop(0.8f, Color.YELLOW) }));
		textHP.setStrokeWidth(1);
		textHP.setStroke(Color.BLACK);
		tip.setLayoutX(3 * Formation.M);
		tip.setLayoutY(0.5 * Formation.M);
		tip.setId("tip");
		tip.setFont(Font.font("Tahoma", 25));
		tip.setTextFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new Stop[] { new Stop(0, Color.AQUA),
				new Stop(0.3f, Color.RED), new Stop(0.5f, Color.BLUE), new Stop(0.8f, Color.YELLOW) }));
//				tip.setStrokeWidth(1);
//				tip.setStyle(Color.BLACK);
	}

	public void addHuman()
	{
		int i;
		for (i = 0; i < 7; ++i)
		{
			humanList.add(new HuLuWa(i));
			pane.getChildren().add(humanList.get(i).getImg());
			pane.getChildren().add(humanList.get(i).HP);
			pane.getChildren().add(humanList.get(i).MP);
		}
		humanList.add(new GrandPa()); // 再添加一个爷爷
		int grandpa = i++;
		pane.getChildren().add(humanList.get(grandpa).getImg());
		pane.getChildren().add(humanList.get(grandpa).HP);
		pane.getChildren().add(humanList.get(grandpa).MP);
		humanList.add(new Butterfly());// 再添加一个小蝴蝶
		int butterfly = i++;
		pane.getChildren().add(humanList.get(butterfly).getImg());
		pane.getChildren().add(humanList.get(butterfly).HP);
		pane.getChildren().add(humanList.get(butterfly).MP);
	}

	public void addMonster()
	{
		int index = 0;
		monsterList.add(new Boss());
		pane.getChildren().add(monsterList.get(index).imageView);
		pane.getChildren().add(monsterList.get(index).HP);
		pane.getChildren().add(monsterList.get(index).MP);
		index++;
		for (int i = 0; i < 15; ++i)
		{
			monsterList.add(new Frog(i));
			pane.getChildren().add(monsterList.get(index + i).imageView);
			pane.getChildren().add(monsterList.get(index + i).HP);
			pane.getChildren().add(monsterList.get(index + i).MP);
		}
		monsterList.add(new Snake());
		pane.getChildren().add(monsterList.get(16).imageView);
		pane.getChildren().add(monsterList.get(16).HP);
		pane.getChildren().add(monsterList.get(16).MP);

	}

	public void addFormation()
	{
		formations[0] = new YanXing();
		formations[1] = new YuLin();
		formations[2] = new YanYue();
		formations[3] = new FengShi();
		formations[4] = new FangYuan();
		formations[5] = new ChongE();
		formations[6] = new SanJiao();
	}

	public void show(int index)
	{
		for (int i = 0; i < formations[index].formationPos.length; ++i)
		{
			if (isBlocked(formations[index].formationPos[i].x/Formation.M, formations[index].formationPos[i].y/Formation.M))
			{
				if (hlwBlocked)
				{
					return;
				}
			}
		}
		for (int i = 0; i < monsterList.size(); ++i)
		{
			if (monsterList.get(i).srcX.get() == formations[index].formationPos[i].x
					&& monsterList.get(i).srcY.get() == formations[index].formationPos[i].y)
			{
				continue;
			}
			if (isBlocked(formations[index].formationPos[i].x / Formation.M,
					formations[index].formationPos[i].y / Formation.M))
			{
				if (!isMonster)
				{
					continue;
				}
				
			}

			PathTransition pt = new PathTransition(Duration.millis(Formation.time),
					new Line(monsterList.get(i).srcX.get() - 9 * Formation.M + 0.5 * Formation.M,
							monsterList.get(i).srcY.get() - 0.5 * Formation.M,
							formations[index].formationPos[i].x - 9 * Formation.M + 0.5 * Formation.M,
							formations[index].formationPos[i].y - 0.5 * Formation.M),
					monsterList.get(i).getImg());
			pt.play();
			PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
					new Line(monsterList.get(i).srcX.get() + 0.5 * Formation.M,
							monsterList.get(i).srcY.get() - 0.05 * Formation.M,
							formations[index].formationPos[i].x + 0.5 * Formation.M,
							formations[index].formationPos[i].y - 0.05 * Formation.M),
					monsterList.get(i).HP);
			ptHP.play();
			PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
					new Line(monsterList.get(i).srcX.get() + 0.5 * Formation.M,
							monsterList.get(i).srcY.get() - 0.025 * Formation.M,
							formations[index].formationPos[i].x + 0.5 * Formation.M,
							formations[index].formationPos[i].y - 0.025 * Formation.M),
					monsterList.get(i).MP);
			ptMP.play();
			// cwl_debug_test
			System.out.println(
					monsterList.get(i).getImg().getLayoutX() + " : " + monsterList.get(i).getImg().getLayoutY());
			monsterList.get(i).setSrcNode(formations[index].formationPos[i]);

		}
	}

	public void show() throws InterruptedException
	{
		for (int i = 0; i < humanList.size(); ++i)
		{

			humanList.get(i).imageView.setLayoutX(humanList.get(i).destNode.x);
			humanList.get(i).imageView.setLayoutY(humanList.get(i).destNode.y);
		}
		// cwl_debug_test
		System.out.println(monsterList.get(0).imageView.getLayoutX() + " " + monsterList.get(0).imageView.getLayoutY());
		System.out.println("妖精排放：");
		System.out.println(monsterList.size());
		System.out.println(formations[0].formationPos.length);

		// cwl_debug_test
		for (int i = 0; i < 17; ++i)
		{
			System.out.println(formations[0].formationPos[i]);
		}
		// cwl_debug_test
		System.out.println("====================================");
		for (int i = 0; i < monsterList.size(); ++i)
		{
			System.out.println(monsterList.get(i).srcNode);
		}
		// cwl_debug_test
		for (int i = 0; i < monsterList.size(); ++i)
		{
			System.out.println(monsterList.get(i).name);
		}
		// cwl_debug_test
		for (int i = 0; i < monsterList.size(); ++i)
		{
			System.out.println(monsterList.get(i).imgURL);
		}
		System.out.println(formations[0].formationPos.length);
		System.out.println("============human========================");
		System.out.println(humanList.size());
		// cwl_debug_test
		for (int i = 0; i < humanList.size(); ++i)
		{
			System.out.println(
					humanList.get(i).imageView.getFitHeight() + " : " + humanList.get(i).imageView.getFitWidth());
			System.out
					.println(humanList.get(i).imageView.getLayoutX() + " : " + humanList.get(i).imageView.getLayoutY());
		}
		// cwl_debug_test
		System.out.println("==========monster===================");
		for (int i = 0; i < monsterList.size(); ++i)
		{
			System.out.println(
					monsterList.get(i).imageView.getFitHeight() + " : " + monsterList.get(i).imageView.getFitWidth());
		}
		// cwl_debug_test
		System.out.println("====================================");

	}

	public void show1() throws InterruptedException
	{
		// cwl_debug_test
		for (int i = 0; i < humanList.size(); ++i)
		{
			System.out.println(humanList.get(i));
		}
		for (int i = 0; i < humanList.size(); ++i)
		{
			if (humanList.get(i).srcX.get() == humanList.get(i).destNode.x
					&& humanList.get(i).srcY.get() == humanList.get(i).destNode.y)
			{
				continue;
			}
			if (isBlocked(humanList.get(i).destNode.x /Formation.M, humanList.get(i).destNode.y/Formation.M))
			{
				continue;
			}
			PathTransition pt = new PathTransition(Duration.millis(Formation.time),
					new Line(humanList.get(i).srcX.get() + 0.5 * Formation.M,
							humanList.get(i).srcY.get() - 0.5 * Formation.M,
							humanList.get(i).destNode.x + 0.5 * Formation.M,
							humanList.get(i).destNode.y - 0.5 * Formation.M),
					humanList.get(i).getImg());
			pt.play();
			PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
					new Line(humanList.get(i).srcX.get() + 0.5 * Formation.M,
							humanList.get(i).srcY.get() - 0.05 * Formation.M,
							humanList.get(i).destNode.x + 0.5 * Formation.M,
							humanList.get(i).destNode.y - 0.05 * Formation.M),
					humanList.get(i).HP);
			ptHP.play();
			PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
					new Line(humanList.get(i).srcX.get() + 0.5 * Formation.M,
							humanList.get(i).srcY.get() - 0.025 * Formation.M,
							humanList.get(i).destNode.x + 0.5 * Formation.M,
							humanList.get(i).destNode.y - 0.025 * Formation.M),
					humanList.get(i).MP);
			ptMP.play();

			// cwl_debug_test
			System.out.println(humanList.get(i).name + "从 ： " + (humanList.get(i).srcX.get()) + ","
					+ (humanList.get(i).srcY.get()));
			// cwl_debug_test
			System.out.println("--->" + (humanList.get(i).destNode.x) + "," + (humanList.get(i).destNode.y));

			// cwl_debug_test
			System.out.println(humanList.get(i).name + "HP ： " + (humanList.get(i).HP.getLayoutX()) + ","
					+ (humanList.get(i).HP.getLayoutY()));
			// cwl_debug_test
			System.out.println(
					"--->" + (humanList.get(i).destNode.x) + "," + (humanList.get(i).destNode.y - 0.1 * Formation.M));
			// cwl_debug_test
			System.out.println(humanList.get(i).name + "MP ： " + (humanList.get(i).MP.getLayoutX()) + ","
					+ (humanList.get(i).MP.getLayoutY()));
			// cwl_debug_test
			System.out.println(
					"--->" + (humanList.get(i).destNode.x) + "," + (humanList.get(i).destNode.y - 0.05 * Formation.M));
			humanList.get(i).srcX.set(humanList.get(i).destNode.x);
			humanList.get(i).srcY.set(humanList.get(i).destNode.y);
			humanList.get(i).srcNode.setX(humanList.get(i).srcX.get());
			humanList.get(i).srcNode.setY(humanList.get(i).srcY.get());

		}

	}

	public void humanMoveTo(Role role, int destX, int destY)
	{

		// cwl_debug_test
		System.out.println("进入到move方法： " + "destX ： destY = " + destX + ":" + destY);
		if (role.srcX.get() == destX * Formation.M && role.srcY.get() == destY * Formation.M)
		{
			return;
		}
		PathTransition pt = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcX.get() + 0.5 * Formation.M, role.srcY.get() - 0.5 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.5 * Formation.M),
				role.getImg());
		pt.play();

		PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcX.get() + 0.5 * Formation.M, role.srcY.get() - 0.05 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.05 * Formation.M),
				role.HP);
		ptHP.play();
		PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcX.get() + 0.5 * Formation.M, role.srcY.get() - 0.025 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.025 * Formation.M),
				role.MP);
		ptMP.play();

		// cwl_debug_test
		System.out.println(role.name + "从 ： " + (role.srcX.get()) + "," + (role.srcY.get()));
		// cwl_debug_test
		System.out.println("--->" + (destX * Formation.M) + "," + (destY * Formation.M));

		role.srcX.set(destX * Formation.M);
		role.srcY.set(destY * Formation.M);
		role.srcNode.setX(role.srcX.get());
		role.srcNode.setY(role.srcY.get());
		people.setImage(new Image(role.people_url));
		info_mid.setImage(new Image("file:images/info_mid1.jpeg"));
		isSelected = false;

	}

	public void monsterMoveTo(Role role, int destX, int destY)
	{
		// cwl_debug_test
		System.out.println("进入到move方法： " + "destX ： destY = " + destX + ":" + destY);
		if (role.srcX.get() == destX * Formation.M && role.srcY.get() == destY * Formation.M)
		{
			return;
		}
		PathTransition pt = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcX.get() - 9 * Formation.M + 0.5 * Formation.M, role.srcY.get() - 0.5 * Formation.M,
						destX * Formation.M - 9 * Formation.M + 0.5 * Formation.M,
						destY * Formation.M - 0.5 * Formation.M),
				role.getImg());
		pt.play();

		PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcX.get() + 0.5 * Formation.M, role.srcY.get() - 0.05 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.05 * Formation.M),
				role.HP);
		ptHP.play();
		PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcX.get() + 0.5 * Formation.M, role.srcY.get() - 0.025 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.025 * Formation.M),
				role.MP);
		ptMP.play();
		// cwl_debug_test
		System.out.println(role.name + "从 ： " + (role.srcX.get()) + "," + (role.srcY.get()));
		// cwl_debug_test
		System.out.println("--->" + (destX * Formation.M) + "," + (destY * Formation.M));

		role.srcX.set(destX * Formation.M);
		role.srcY.set(destY * Formation.M);
		role.srcNode.setX(role.srcX.get());
		role.srcNode.setY(role.srcY.get());
		people.setImage(new Image(role.people_url));
		info_mid.setImage(new Image("file:images/info_mid1.jpeg"));
		isSelected = false;
	}

	public boolean isBlocked(int x, int y)
	{
		for (int i = 0; i < humanList.size(); ++i)
		{
			if (humanList.get(i).srcX.get() == x * Formation.M && humanList.get(i).srcY.get() == (y) * Formation.M)
			{
				hlwBlocked = true;
				isMonster =false;
				return true;
			}
		}

		for (int i = 0; i < monsterList.size(); ++i)
		{
			if (monsterList.get(i).srcX.get() == x * Formation.M && monsterList.get(i).srcY.get() == (y) * Formation.M)
			{
				hlwBlocked = false;
				isMonster = true;
				return true;
			}
		}
		return false;
	}

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			myEllipse = new Ellipse(Formation.M, Formation.M / 2);
			myEllipse.setRadiusX(0.5 * Formation.M);
			myEllipse.setRadiusY(0.25 * Formation.M);
			myEllipse.setCenterX(5 * Formation.M);
			myEllipse.setCenterY(5 * Formation.M);
			pane.getChildren().add(myEllipse);
			pane.setOnMouseMoved((a1) -> {
				myEllipse.setRadiusX(0.5 * Formation.M);
				myEllipse.setRadiusY(0.25 * Formation.M);
				myEllipse.setCenterX(0.5 * Formation.M);
				myEllipse.setCenterY(0.25 * Formation.M);
			});

			ImageView background = new ImageView(backgrounds[7]);
			background.setFitHeight(10 * Formation.M);
			background.setFitWidth(10 * Formation.M);
			background.setLayoutX(0.0);
			background.setLayoutY(0.0);
			pane.getChildren().add(background);
			// cwl_debug_test
			System.out.println(background.getFitHeight() + " : " + background.getFitWidth());

			addFormation();
			addHuman();
			addMonster();
			myEllipse = new Ellipse(Formation.M, Formation.M / 2);
			myEllipse.setFill(Color.color(0.95, 0.95, 0, 0.5));
			myEllipse.setRadiusX(0.5 * Formation.M);
			myEllipse.setRadiusY(0.25 * Formation.M);
			myEllipse.setCenterX(5 * Formation.M);
			myEllipse.setCenterY(5 * Formation.M);
			pane.getChildren().add(myEllipse);
			pane.setOnMouseClicked((a1) -> {
				double x = a1.getX();
				double y = a1.getY();
				if (x > 10 * Formation.M)
				{
					return;
				}
				System.out.println("x = " + x + " y = " + y);
				int realx = (int) (x / (int) Formation.M);
				int realy = (int) (y / (int) Formation.M);
				System.out.println(" realx = " + realx + " readly = " + realy);
				if (isMoved)
				{
					if (realx == theRealX && realy == theRealY)
					{
						return;
					}
					if (!isBlocked(realx, realy))
					{
						if (humanOrMonster == HUMAN)
						{
							humanMoveTo(moveRole, realx, realy);
						} else if (humanOrMonster == MONSTER)
						{
							monsterMoveTo(moveRole, realx, realy);
						}
						myEllipse.setFill(new Color(0.95, 0.95, 0, 0.5));
						moveRole = null;
						theRealX = -1;
						theRealY = -1;
						isMoved = false;
						return;
					}

				}
				/** get the real X an Y */
				for (int i = 0; i < humanList.size(); ++i)
				{
					if (humanList.get(i).srcX.get() == realx * Formation.M
							&& humanList.get(i).srcY.get() == (realy) * Formation.M)
					{
						moveRole = humanList.get(i);
						// cwl_debug_test
						System.out.println("选中了：" + humanList.get(i).name);
						// cwl_debug_test
						System.out.println("realX : realY = " + realx + ":" + realy);
						people.setImage(new Image(humanList.get(i).imgURL));
						info_mid.setImage(new Image("file:images/info_mid.jpeg"));
						isSelected = true;
						theRealX = realx;
						theRealY = realy;
						isMoved = true;
						humanOrMonster = HUMAN;
						break;
					}
				}

				for (int i = 0; i < monsterList.size(); ++i)
				{
					if (monsterList.get(i).srcX.get() == realx * Formation.M
							&& monsterList.get(i).srcY.get() == (realy) * Formation.M)
					{
						moveRole = monsterList.get(i);
						// cwl_debug_test
						System.out.println("选中了：" + monsterList.get(i).name);
						// cwl_debug_test
						System.out.println("realX : realY = " + realx + ":" + realy);
						people.setImage(new Image(monsterList.get(i).imgURL));
						info_mid.setImage(new Image("file:images/info_mid.jpeg"));
						isSelected = true;
						theRealX = realx;
						theRealY = realy;
						isMoved = true;
						humanOrMonster = MONSTER;
						break;
					}
				}
				if (isMoved)
				{
					myEllipse.setFill(new Color(0.95, 0, 0, 0.5));
				}

			});
			pane.setOnMouseMoved((a1) -> {
				double x = a1.getX();
				double y = a1.getY();
				if (x > 10 * Formation.M)
				{
					return;
				}
				int realx = (int) (x / (int) Formation.M);
				int realy = (int) (y / (int) Formation.M);

				/** 通过获得的坐标计算出在哪个块中 */
				myEllipse.setRadiusX(0.5 * Formation.M);
				myEllipse.setRadiusY(0.25 * Formation.M);
				myEllipse.setCenterX((realx + 0.5) * Formation.M);
				myEllipse.setCenterY((realy + 0.5 + 0.25) * Formation.M);
				if (isSelected)
				{
					return;
				}
				for (int i = 0; i < humanList.size(); ++i)
				{
					if (humanList.get(i).srcX.get() == realx * Formation.M
							&& humanList.get(i).srcY.get() == (realy) * Formation.M)
					{
						System.out.println(humanList.get(i).name + ": " + humanList.get(i).srcNode);
						info.setImage(new Image(humanList.get(i).info_url));
						people.setImage(new Image(humanList.get(i).people_url));
						textHP.setText("Name: " + humanList.get(i).name + "\n" + "HP: " + humanList.get(i).nowHP + "\n"
								+ "MP: " + humanList.get(i).nowMP + "\nMaxHP: " + humanList.get(i).maxHP + "\nMaxMP:"
								+ humanList.get(i).maxMP);
					}
				}

				for (int i = 0; i < monsterList.size(); ++i)
				{
					if (monsterList.get(i).srcX.get() == realx * Formation.M
							&& monsterList.get(i).srcY.get() == (realy) * Formation.M)
					{
						System.out.println(monsterList.get(i).name + monsterList.get(i).srcNode);
						info.setImage(new Image(monsterList.get(i).info_url));
						people.setImage(new Image(monsterList.get(i).people_url));
						textHP.setText("Name: " + monsterList.get(i).name + "\n" + "HP: " + monsterList.get(i).nowHP
								+ "\n" + "MP: " + monsterList.get(i).nowMP + "\nmaxHP: " + monsterList.get(i).maxHP
								+ "\nmaxMP: " + monsterList.get(i).maxMP);
					}
				}
			});

			Scene scene = new Scene(pane, 10 * Formation.M + Formation.right_distance, 10 * Formation.M);
			scene.getStylesheets().add("file:CSS/Font.css");
			pane.setOnMousePressed((a1) -> {
				posX = a1.getSceneX();
				posY = a1.getSceneY();
			});

			pane.setOnMouseDragged((a1) -> {
				primaryStage.setX(a1.getScreenX() - posX);
				primaryStage.setY(a1.getScreenY() - posY);
			});
			Button button1 = new Button("方阵1");
			button1.setLayoutX(1.5 * Formation.M);
			button1.setLayoutY(0);
			Button button2 = new Button("方阵2");
			button2.setLayoutX(2.2 * Formation.M);
			button2.setLayoutY(0);
			Button button3 = new Button("方阵3");
			button3.setLayoutX(2.9 * Formation.M);
			button3.setLayoutY(0);
			Button button4 = new Button("方阵4");
			button4.setLayoutX(3.6 * Formation.M);
			button4.setLayoutY(0);
			Button button5 = new Button("方阵5");
			button5.setLayoutX(4.3 * Formation.M);
			button5.setLayoutY(0);
			Button button6 = new Button("方阵6");
			button6.setLayoutX(5.0 * Formation.M);
			button6.setLayoutY(0);
			Button button7 = new Button("方阵7");
			button7.setLayoutX(5.7 * Formation.M);
			button7.setLayoutY(0);
			Button button8 = new Button("葫芦娃归位");
			button8.setLayoutX(6.4 * Formation.M);
			button8.setLayoutY(0);
			Button button9 = new Button("加速");
			button9.setLayoutX(0 * Formation.M);
			button9.setLayoutY(0);
			Button button10 = new Button("减速");
			button10.setLayoutX(0.7 * Formation.M);
			button10.setLayoutY(0);
			Button button11 = new Button("换背景");
			button11.setLayoutX(8 * Formation.M);
			button11.setLayoutY(0);
			button11.setId("button11");
			Label label = new Label();
			label.setLayoutX(0 * Formation.M);
			label.setLayoutY(0.5 * Formation.M);
			label.setId("rate_label");
			label.setText("移动速度: " + (20 - (Formation.time / 1000.0) * 2));

			button1.setOnAction((a1) -> {
				show(0);
			});
			button2.setOnAction((a1) -> {
				show(1);
			});
			button3.setOnAction((a1) -> {
				show(2);
			});
			button4.setOnAction((a1) -> {
				show(3);
			});
			button5.setOnAction((a1) -> {
				show(4);
			});
			button6.setOnAction((a1) -> {
				show(5);
			});
			button7.setOnAction((a1) -> {
				show(6);
			});
			button9.setOnAction((a1) -> {
				if (Formation.time >= 1000)
				{
					Formation.time -= 500;
					label.setText("移动速度: " + (20 - (Formation.time / 1000.0) * 2));
				}
			});
			button10.setOnAction((a1) -> {
				if (Formation.time <= 9000)
				{
					Formation.time += 500;
					label.setText("移动速度: " + (20 - (Formation.time / 1000.0) * 2));

				}
			});
			button8.setOnAction((a1) -> {
				try
				{
					show1();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			});
			button11.setOnAction((a1) -> {
				String url = backgrounds[random.nextInt(8)];
				String urlt = background.getImage().getUrl();
				while (url.equals(urlt))
				{
					url = backgrounds[random.nextInt(8)];
				}
				background.setImage(new Image(url));
			});
			pane.getChildren().add(button1);
			pane.getChildren().add(button2);
			pane.getChildren().add(button3);
			pane.getChildren().add(button4);
			pane.getChildren().add(button5);
			pane.getChildren().add(button6);
			pane.getChildren().add(button7);
			pane.getChildren().add(button8);
			pane.getChildren().add(button9);
			pane.getChildren().add(button10);
			pane.getChildren().add(button11);
			pane.getChildren().add(label);

			ImageView info_picture = new ImageView("file:images/info.png");
			info_picture.setFitHeight(Formation.M);
			info_picture.setFitWidth(Formation.right_distance);
			info_picture.setLayoutX(10 * Formation.M);
			info_picture.setLayoutY(0);
			pane.getChildren().add(info_picture);
			initImage();
			pane.getChildren().add(info_mid);
			pane.getChildren().add(info_bottom);
			pane.getChildren().add(info);
			pane.getChildren().add(people);
			pane.getChildren().add(textHP);
			pane.getChildren().add(tip);
			primaryStage.setTitle("Test");
			primaryStage.setScene(scene);
			primaryStage.show();
			show1();
			show(6);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
