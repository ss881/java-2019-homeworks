package com.nju.cwl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.PathTransition;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * 游戏名： 葫芦娃大战妖精 版本：3.0 
 * 2019年 12月娥27日（测试多开进程，同时回收）
 * 1、在读取文件播放时同时尝试打开多个文件，同步进行4个战斗记录场景的播放。
 * 2、发现在本机电脑的配置限制下，同时播放四个战斗文件已经有点很卡顿，在慢速条件下勉强能够播放完。
 * 3、发现在2 - 3 个战斗场景同时播放的时候，画面的流产度是比较可以的。就是上升到4 的时候开始有点吃不消了~
 * 
 * 
 * 2019年 12月23日 （进一步完善体验）
 * 一、新增功能：实现了5种界面的分辨率：囊括了从800分辨率到4K的分辨率所有各种场景屏幕分辨率。
 * 在这五种分辨率下，我都试验了游戏能正常运行，字体演示正常，动画正常，提示正常。 所有节点都可以根据开头的分辨率进行放大缩小，很人性化。
 * 二、新增功能：实现了记录的保存，文件名是游戏结束的时间，在选择记录的时候非常明显地看得出来究竟是在哪一刻打的游戏，很人性化。
 * 三、新增功能：实现了背景音乐的开关播放，实现了所有技能发射，到技能动画打中人物时播放特定的音效，增强了观赏性。
 * 
 * 
 * 2019年 12月7日 一、重构源代码 二、黑化了妖精队伍 三、扩展了普攻，法攻，群攻，治疗，增加攻击属性技能，每一个基本都有动画，
 * 四、-MP动画，+-HP动画，普攻动画，法攻动画，小兵群攻摆阵动画，大王群攻技能动画，
 * 五、而且每个人的普通gif，法攻gif都是专属的，实时显示鼠标下人物各个属性值和名片介绍， 六、
 * 回合数圆心用黑红颜色显示当前次数，还有强调整游戏执行速度的按钮， 设有加速，减速，速度在1-19这19个选择区间，法阵7个，可供换的背景7个。
 * 
 * 所以每一个角色都有下面的背景渲染： 1、个人的静态图片； 2、角色移动时，自东变成被成动态图片，产生移动的效果。
 * 3、移动到目的地后。又自动从动态图片切换成静态图片， 1-2-3一连贯下来就实现了移动动画。
 * 4、每个人都有自己的物理攻击的动图，是一种发射型的武器gif， 就是说一旦触发物理攻击，玩家当然会先先从静态图片切换到动态图片，顺便会产生一个
 * 物理攻击的动图，从玩家这个角色飞向 被攻击的玩家， 当物理攻击的动图到达了被攻击玩家的身上的时候，这个物理攻击的gif就会消失，这将触发被攻击玩家
 * 减少HP的gif 图片。相应的血条也会减少。 5、每个人都有自己的法术攻击的动图，也是一种发射型的招数gif。
 * 就是说一旦触发法术攻击，玩家当然手先从静态图片，切换到动态图片，而且释放法术的玩家瞬间就会触发减少MP值的gif动图，而且相应的MP条会减少，。而且顺便会产生一个
 * 法术攻击的动图，从玩家这个角色飞向 被攻击的玩家， 当法术攻击的动图到达了被攻击玩家的身上的时候，这个法术攻击的gif就会消失，此外触发被攻击玩家
 * 减少HP的gif 图片。相应的血条也会减少。 6、右边的信息栏的人物肖像图，就是说鼠标移动到这个人物身上的时候，
 * 就会在右边信息栏目显示这个角色的肖像图片。 7、战斗选择框中人物肖像，就是说点击角色进行战斗选择时会弹出这个人物的战斗对话框，这个战斗对话框里面
 * 就会有这个角色的肖像图片。下面时相应的战斗选项的button
 * 补充：因为物攻、法攻、都会有个别角色时群攻选手，，就是说出发的物理攻击和法术攻击都是一个范围的攻击，时很对多个人进行的连贯的动画效果。
 * 8、角色的HP值减少到0之后，这个人物就会被移除，替代他的是一块墓碑！！显示这个人物被kill掉。
 * 
 * 
 * （互打了无数次制定了下面比较合理而且有趣的规则：） 人物介绍，
 * 
 * 2019年 12月6日 鼠标人性化提示处理： 一、人物死亡后墓碑显示。。各种温馨提示的对话框，
 * 二、无法移动的对话框，无法物理攻击的对话框，真正攻击不要软点击的对话框， 三、还有专门的角色有专门的战斗对话框。
 * 四、比如人类阵营中老爷爷时群攻高手，物理攻击和法术攻击都时范围攻击。 五、小蝴蝶时医疗高手，有全体成员回血的技能。
 * 六、爷爷具有增加全体成员物理攻击+400的辅助属性。
 * 七、玩家只要移动鼠标就会发现，如果玩家移动鼠标的时候，鼠标下面有人物，那么右边的信息栏就显示这个人物所有信息，右边的信息栏分为三个层次，
 * 上层有这个人物的名片介绍，中间是他的肖像价绍，下面就是他的此时的所有属性，比如HP MP 法攻 物攻 ，距离，执行次数等等。 所以玩家想查看任何一个玩家，
 * 只需要移动鼠标，就这么简单。 八、此外每一个玩家头上都有两个长方形条条，红色的HP条， 蓝色 的
 * MP条，这个数据量都是和右边的信息栏目中的数据信息是完全吻合的。 此外： 在动画流畅度和尊重物理事实的前提下，
 * 一旦角色A发动了法术攻击，那么这个角色A就会现有一个-MP的动画，会有一个减去相应Mp值的蓝色label在他身上飘过。 此外，只有角色发出的 物理攻击
 * 和法术攻击 的兵器和召唤术动画 打到了预定攻击的玩家身上的时候，被攻击的玩家才会展示-HP的动画，就是在被攻击的玩家身上会有
 * 一个减去相应HP值的label在他身上飘过。
 * 
 * 九、鼠标标下面会有一个椭圆阴影指示当前鼠标在第几个格子。
 * 
 * 进一步人性化处理： 一、我们为椭圆阴影设置了4中不同的颜色。 如果玩家点解移动选项，物理攻击选项， 法术攻击选项， 这个椭圆阴影会变成
 * 各种不同的颜色已作为对 玩家点击鼠标做出的反应，所以专门设置了椭圆阴影的颜色数组对应各种各不同的情况 二、更人性化的处理是： 如果玩家准备移动，
 * 物理攻击，法术攻击对方的时候， 在移动鼠标的时候， 如果这个地方不能到达，就是说移动的距离或者是攻击的距离太远了，那么椭圆的阴影直接变成黑色，
 * 从而非常醒目地提醒 玩家这块格子是不能达到的。 三、每一个不可执行的操作都会以对话框的形式来对玩家进行温馨的提示不能这么做，
 * （这是在鼠标移动时，椭圆变色提醒下，玩家还要执意执行非法操作的话做出的第二重保障。
 * 四、右边的信息栏起始时非常重要的，就是实时显示当前玩家的各种属性，加上鼠标移动提示等等，玩家操作起来还是非常人性化的。
 * 
 * @作者 ： 我喜欢你家孩子呀~ 联系方式： 1019070879@qq.com 修改日期: 2019年12月7️日 时间: 下午3:35:22
 */
public class 自动播放界面
{

	int count = 0;
	/* 所以操作写入文件内部 */
	BufferedReader reader;
	private boolean 是否正在执行动画 = false;
	AtomicBoolean flag = new AtomicBoolean(false);
	Stage stage = new Stage();
	Pane pane = new Pane();
	int num = 0;
//	AtomicInteger num = new AtomicInteger(0);
//	AtomicInteger num = new AtomicInteger(0);
	
	String 击打声2String = "src/main/resources/mp3/击打2.mp3";
	String 击打声1String = "src/main/resources/mp3/击打1.mp3";
	String 呼啸String = "src/main/resources/mp3/呼啸.mp3";
	String 电流声String = "src/main/resources/mp3/电流声.mp3";
	String 全体进攻String = "src/main/resources/mp3/全体进攻.mp3";
	String 重击String = "src/main/resources/mp3/重击.mp3";
	String 飞机投弹声String = "src/main/resources/mp3/飞机投弹声.mp3";
	String 雷电属性蓄力String = "src/main/resources/mp3/雷电属性蓄力.mp3";
	String 爆炸声String = "src/main/resources/mp3/爆炸声.mp3";
	String 刀刺String = "src/main/resources/mp3/刀刺.mp3";
	String 补血String = "src/main/resources/mp3/补血.mp3";
	String 移动String = "src/main/resources/mp3/移动.mp3";
	File f = new File("src/main/resources/mp3/bgm.mp3");
	File 击打效果file;
	Media media = new Media(f.toURI().toString());
	MediaPlayer mp3Player = new MediaPlayer(media);

	Media 击打效果media;
	MediaPlayer 击打效果mediaPlayer;
	ArrayList<ArrayList<String>> 指令数组 = new ArrayList<>();

	public int 小蝴蝶治疗HP量 = 500;
	public int 小蝴蝶治疗技能需要消耗的MP = 20;
	public int 青蛙全体进攻次数 = 1;
	public  boolean 游戏开始 = false;
	private double 鼠标坐标X;
	private double 鼠标坐标Y;
	public final int 人类 = 1;
	public final int 妖怪 = 2;
	public final int 格子内没人 = 0;
	public final int 格子内为人类 = 1;
	public final int 格子内为妖怪 = 2;
	public final int 自由状态时颜色 = 0;
	public final int 移动时颜色 = 1;
	public final int 物理攻击时颜色 = 2;
	public final int 法术攻击时颜色 = 3;

	public final int 取消行动 = -1;
	public final int 移动 = 0;
	public final int 物理攻击 = 1;
	public final int 法术攻击 = 2;

	public boolean 正在攻击 = false;
	public boolean 正在移动 = false;

	public int 人类总步数 = 5;
	public int 妖怪总步数 = 4;
	public int 人类剩余步数 = 人类总步数; /* 默认是人类先手 */
	public int 妖怪剩余步数 = 0; /* 默认时妖怪后手 */

	/**
	 * 游戏规则是得等对方打完三次才能轮到自己进攻
	 */
	public int 玩家的战斗选择 = 取消行动; /* 默认是移动 */
	public int 角色选择操作 = 自由状态时颜色; /* 默认是自由状态 */
	public int 正在执行的玩家 = 人类;

	public boolean 全体进攻 = false;
	public int 全体进攻消耗的MP值 = 100;
	Label 人类步数 = new Label("人类步数");
	Label 妖怪步数 = new Label("妖怪步数");

	Circle[] 人类步数数组 = new Circle[5];
	Circle[] 妖怪步数数组 = new Circle[4];

	Thread[] 人类线程组 = new Thread[9];
	Thread[] 妖怪线程组 = new Thread[17];
	ArrayList<Role> 人类队列 = new ArrayList<>(); /* 人类阵营的队列 */
	ArrayList<Role> 妖怪队列 = new ArrayList<>(); /* 妖怪阵营的队列 */
	ArrayList<ImageView> 墓碑队列 = new ArrayList<ImageView>();/* 管理图中的墓碑队列 */
	Formation[] 阵型队列 = new Formation[7]; /* 阵型队列 */
	String[][] 格子地图 = new String[10][10];/* 用来显示地图上目前所有的角色 */

	Color[] 椭圆颜色数组 = { Color.color(0.95, 0.95, 0, 0.5), Color.color(0.95, 0, 0, 0.5), Color.color(0.95, 0, 0.5, 0.5),
			Color.color(0.95, 0.5, 0.5, 0.5) };

	/**
	 * Color 自由状态时颜色 = 0 Color 移动时颜色 = 1 Color 物体攻击时颜色 = 2 Color 法术攻击时颜色 = 3
	 */

	/**
	 * public static Button 方阵1button = new Button("方阵1");
	public static Button 方阵2button = new Button("方阵2");
	public static Button 方阵3button = new Button("方阵3");
	public static Button 方阵4button = new Button("方阵4");
	public static Button 方阵5button = new Button("方阵5");
	public static Button 方阵6button = new Button("方阵6");
	public static Button 方阵7button = new Button("方阵7");
	public static Button 背景音乐开关button = new Button("背景音乐开/关");
	public static Button 加速button = new Button("加速");
	public static Button 减速button = new Button("减速");
	public static Button 换背景button = new Button("换背景");
	public static Label 移动速度label = new Label();
	 */
	
	Button 方阵1button = new Button("方阵1");
	Button 方阵2button = new Button("方阵2");
	Button 方阵3button = new Button("方阵3");
	Button 方阵4button = new Button("方阵4");
	Button 方阵5button = new Button("方阵5");
	Button 方阵6button = new Button("方阵6");
	Button 方阵7button = new Button("背景音乐开/关");
	Button 背景音乐开关button = new Button("背景音乐开/关");
	Button 加速button = new Button("加速");
	Button 减速button = new Button("减速");
	Button 换背景button = new Button("换背景");
	Label 移动速度label = new Label();

	boolean 轮到葫芦娃执行 = false;
	boolean 轮到妖怪们执行 = false;

	ImageView 背景图片;

	int 真实坐标X = -1; /* 真实坐标X */
	int 真实坐标Y = -1; /* 真实的坐标Y */
	boolean 格子内是否是葫芦娃 = false;
	boolean 格子内是否是妖怪 = false;

	Alert 选中人物时的对话窗口 = new Alert(AlertType.INFORMATION);
	Alert 警告窗口 = new Alert(AlertType.INFORMATION);

	Text 右边信息栏HP文本 = new Text();
	
	/**
	 * public static ImageView 右边人物名片 = new ImageView("file:images/info.png");
public static ImageView 右边底部信息名片 = new ImageView("file:images/info_buttom.jpeg");
public static ImageView 右边中部信息名片 = new ImageView("file:images/info_mid1.jpeg");
public static ImageView 右边人物介绍名片 = new ImageView("file:images/brother1_info.png");
public static ImageView 右边人物肖像名片 = new ImageView("file:images/brother1.png");
	 */
	ImageView 右边人物名片 = new ImageView("file:src/main/resources/images/info.png");
	ImageView 右边底部信息名片 =  new ImageView("file:src/main/resources/images/info_buttom.jpeg");
	ImageView 右边中部信息名片 = new ImageView("file:src/main/resources/images/info_mid1.jpeg");
	ImageView 右边人物介绍名片 = new ImageView("file:src/main/resources/images/brother1_info.png");
	ImageView 右边人物肖像名片 = new ImageView("file:src/main/resources/images/brother1.png");

//	Label 提示标签 = new Label("点击方阵展示，也可点击角色随意移动！");
	Ellipse 格子椭圆阴影; /* 鼠标移动产生的在格子上面投向地面的阴影 */
	Random 随机数换背景 = new Random();
	String[] 背景图片数组 = { "file:src/main/resources/images/background1.jpg", "file:src/main/resources/images/background2.jpg", "file:src/main/resources/images/background3.jpg",
			"file:src/main/resources/images/background4.jpg", "file:src/main/resources/images/background5.jpg", "file:src/main/resources/images/background6.jpg",
			"file:src/main/resources/images/background7.jpg", "file:src/main/resources/images/background8.jpg", };
	boolean 是否被选定 = false;
	public int 鼠标点击的格子内是人还是妖精 = 格子内没人; /* 默认点击时时没人的 */

	Role 正在执行操作的格子中的人物 = null;
	Role 鼠标选中的格子内的人物 = null;

	boolean 节点是否要移动 = false;

	Thread[] threads = null;

	/* 更新格子地图 */

	public int 上次阵型 = 0;

	public void 添加步数()
	{
		添加步数标签();
		添加步数圆圈();
	}

	public void 添加步数圆圈()
	{
		人类步数数组[0] = new Circle(3.75 * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED);
		人类步数数组[1] = new Circle((3.75 + 0.5 + 0.1) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED);
		人类步数数组[2] = new Circle((3.75 + 1 + 0.2) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED);
		人类步数数组[3] = new Circle((3.75 + 1.5 + 0.3) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED);
		人类步数数组[4] = new Circle((3.75 + 2 + 0.4) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED);
//		 人类步数数组 = { new Circle(3.75 * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED),
//				new Circle((3.75 + 0.5 + 0.1) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED),
//				new Circle((3.75 + 1 + 0.2) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED),
//				new Circle((3.75 + 1.5 + 0.3) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED),
//				new Circle((3.75 + 2 + 0.4) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.RED), };

		妖怪步数数组[0] = new Circle(8 * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK);
		妖怪步数数组[1] = new Circle((8 + 0.5 + 0.1) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK);
		妖怪步数数组[2] = new Circle((8 + 1 + 0.2) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK);
		妖怪步数数组[3] = new Circle((8 + 1.5 + 0.3) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK);
//		
//		 妖怪步数数组 = { new Circle(8 * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK),
//				new Circle((8 + 0.5 + 0.1) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK),
//				new Circle((8 + 1 + 0.2) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK),
//				new Circle((8 + 1.5 + 0.3) * Formation.M, 0.75 * Formation.M, 0.25 * Formation.M, Color.BLACK) };

		pane.getChildren().addAll(人类步数数组[0], 人类步数数组[1], 人类步数数组[2], 人类步数数组[3], 人类步数数组[4], 妖怪步数数组[0], 妖怪步数数组[1],
				妖怪步数数组[2], 妖怪步数数组[3]);
	}

	public void 打印玩家战斗选择()
	{
		switch (玩家的战斗选择)
		{
//		public final int 取消行动 = -1;
//		public final int 移动 = 0;
//		public final int 物理攻击 = 1;
//		public final int 法术攻击 = 2;
		case -1:
			System.out.println("玩家的战斗选择是：取消行动");
			break;
		case 0:
			System.out.println("玩家的战斗选择是：移动");
			break;
		case 1:
			System.out.println("玩家的战斗选择是：物理攻击");
			break;
		case 2:
			System.out.println("玩家的战斗选择是：法术攻击");
			break;
		default:
			break;
		}
	}

	public void 添加步数标签()
	{
		人类步数.setMaxSize(Formation.M * 1.2, Formation.M * 0.5);
		人类步数.setMinSize(Formation.M * 1.2, Formation.M * 0.5);
		人类步数.setLayoutX((2 + 0.2) * Formation.M);
		人类步数.setLayoutY(0.5 * Formation.M);
		人类步数.setId("tip");
		人类步数.setFont(Font.font("Tahoma", Formation.M * 27.0 / 90.0));
		人类步数.setTextFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new Stop[] { new Stop(0, Color.AQUA),
				new Stop(0.3f, Color.RED), new Stop(0.5f, Color.BLUE), new Stop(0.8f, Color.YELLOW) }));

		妖怪步数.setMaxSize(Formation.M * 1.2, Formation.M * 0.5);
		妖怪步数.setMinSize(Formation.M * 1.2, Formation.M * 0.5);

		妖怪步数.setLayoutX((6 + 0.5) * Formation.M);
		妖怪步数.setLayoutY(0.5 * Formation.M);
		妖怪步数.setId("tip");
		妖怪步数.setFont(Font.font("Tahoma", Formation.M * 27.0 / 90.0));
		妖怪步数.setTextFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new Stop[] { new Stop(0, Color.AQUA),
				new Stop(0.3f, Color.RED), new Stop(0.5f, Color.BLUE), new Stop(0.8f, Color.YELLOW) }));
		pane.getChildren().addAll(人类步数, 妖怪步数);

	}

	public void 更新格子地图()
	{
		for (int i = 0; i < 格子地图.length; ++i)
		{
			for (int j = 0; j < 格子地图[i].length; ++j)
			{
				格子地图[i][j] = null;
			}
		}
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			格子地图[人类队列.get(i).realNode.y][人类队列.get(i).realNode.x] = 人类队列.get(i).name;
		}
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			格子地图[妖怪队列.get(i).realNode.y][妖怪队列.get(i).realNode.x] = 妖怪队列.get(i).name;
		}
	}

	/* 打印格子地图 */
	public void 打印格子地图()
	{
//		for (int i = 0; i < 格子地图.length; ++i)
//		{
//			for (int j = 0; j < 格子地图[i].length; ++j)
//			{
//				System.out.print(格子地图[i][j] + "\t");
//			}
//			System.out.println();
//		}
	}

	/* 打印当前葫芦娃们的坐标 */
	public void 打印葫芦娃们真实坐标()
	{
//		for (int i = 0; i < 人类队列.size(); ++i)
//		{
//			System.out.println(人类队列.get(i).name + ": " + 人类队列.get(i).realNode);
//		}
	}

	/* 打印当前妖怪们的坐标 */
	public void 打印妖怪们的真实坐标()
	{
//		for (int i = 0; i < 妖怪队列.size(); ++i)
//		{
//			System.out.println(妖怪队列.get(i).name + ":" + 妖怪队列.get(i).realNode);
//		}
	}

	public void 添加右边信息栏()
	{
		添加右边信息名片();
		添加有右边中下的信息名片();
		添加右边人物信息名片();
		pane.getChildren().add(右边人物肖像名片);
		pane.getChildren().add(右边信息栏HP文本);
//		pane.getChildren().add(提示标签);
	}

	public void 添加右边信息名片()
	{

		右边人物名片.setFitHeight(Formation.M);
		右边人物名片.setFitWidth(Formation.right_distance);
		右边人物名片.setLayoutX(10 * Formation.M);
		右边人物名片.setLayoutY(0);
		pane.getChildren().add(右边人物名片);
	}

	public void 添加有右边中下的信息名片()
	{
		右边底部信息名片.setFitHeight(5 * Formation.M);
		右边底部信息名片.setFitWidth(Formation.right_distance);
		右边底部信息名片.setLayoutX(10 * Formation.M);
		右边底部信息名片.setLayoutY(5 * Formation.M);
		右边中部信息名片.setFitHeight(2 * Formation.M);
		右边中部信息名片.setFitWidth(Formation.right_distance);
		右边中部信息名片.setLayoutX(10 * Formation.M);
		右边中部信息名片.setLayoutY(3 * Formation.M);
		右边人物介绍名片.setFitHeight(2 * Formation.M);
		右边人物介绍名片.setFitWidth(Formation.right_distance);
		右边人物介绍名片.setLayoutX(10 * Formation.M);
		右边人物介绍名片.setLayoutY(1 * Formation.M);
		pane.getChildren().add(右边中部信息名片);
		pane.getChildren().add(右边底部信息名片);
		pane.getChildren().add(右边人物介绍名片);
	}

	/* 初始化图片 */
	public void 添加右边人物信息名片()
	{

		右边人物肖像名片.setFitHeight(2 * Formation.M);
		右边人物肖像名片.setFitWidth(Formation.right_distance);
		右边人物肖像名片.setLayoutX(10 * Formation.M);
		右边人物肖像名片.setLayoutY(3 * Formation.M);

		右边信息栏HP文本.setLayoutX(10 * Formation.M);
		右边信息栏HP文本.setLayoutY(5.25 * Formation.M);

		右边信息栏HP文本.setText("Name: " + 人类队列.get(0).name + "\n" + "HP: " + 人类队列.get(0).nowHP + "\n" + "MP: "
				+ 人类队列.get(0).nowMP + "\nMaxHP: " + 人类队列.get(0).maxHP + "\nMaxMP:" + 人类队列.get(0).maxMP);
		右边信息栏HP文本.setFont(Font.font("Tahoma", 25));
		右边信息栏HP文本.setFill(Color.RED);
//		右边信息栏HP文本.setFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new Stop[] { new Stop(0, Color.AQUA),
//				new Stop(0.3f, Color.RED), new Stop(0.5f, Color.BLUE), new Stop(0.8f, Color.YELLOW) }));
		右边信息栏HP文本.setStrokeWidth(1);
		右边信息栏HP文本.setStroke(Color.BLACK);
	}

	public void 添加格子椭圆阴影()
	{
		格子椭圆阴影 = new Ellipse(Formation.M, Formation.M / 2);
		格子椭圆阴影.setFill(椭圆颜色数组[自由状态时颜色]);
		格子椭圆阴影.setRadiusX(0.5 * Formation.M);
		格子椭圆阴影.setRadiusY(0.25 * Formation.M);
		格子椭圆阴影.setCenterX(5 * Formation.M);
		格子椭圆阴影.setCenterY(5 * Formation.M);
		pane.getChildren().add(格子椭圆阴影);
	}

	public void 添加背景图片()
	{
		背景图片 = new ImageView("file:src/main/resources/images/Bb.gif");
		背景图片.setFitHeight(10 * Formation.M);
		背景图片.setFitWidth(10 * Formation.M);
		背景图片.setLayoutX(0.0);
		背景图片.setLayoutY(0.0);
		pane.getChildren().add(背景图片);
	}

	public void 添加移动速度标签()
	{
		移动速度label.setMaxSize(Formation.M * 2, Formation.M * 0.5);
		移动速度label.setMinSize(Formation.M * 2, Formation.M * 0.5);

//		27  ： 90 说明每一个单位 是 27 / 90 
		移动速度label.setFont(new Font(Formation.M * 27.0 / 90.0));

		移动速度label.setLayoutX(0 * Formation.M);
		移动速度label.setLayoutY(0.5 * Formation.M);
		移动速度label.setId("rate_label");
		移动速度label.setText("移动速度: " + (20 - (Formation.time / 1000.0) * 2));
		pane.getChildren().add(移动速度label);
	}

	/* 加载人类阵营 */
	public void 添加人类() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		int i;
		for (i = 0; i < 7; ++i)
		{
			Class<?> obj = Class.forName("com.nju.cwl.HuLuWa");
			/**
			 * 如果使用newInstance（）的话，一方面这个方面是过时的，另一方面要调用这个方法必须是存在默认构造函数 使用指定的构造函数来调用方法更合适
			 */
			@SuppressWarnings("unchecked")
			Constructor<HuLuWa> constructor = (Constructor<HuLuWa>) obj.getDeclaredConstructor(int.class);
			HuLuWa hlw = constructor.newInstance(i);
			人类队列.add(hlw);
			pane.getChildren().add(人类队列.get(i).getImg());
			pane.getChildren().add(人类队列.get(i).HP);
			pane.getChildren().add(人类队列.get(i).MP);
		}
		人类队列.add(new GrandPa()); // 再添加一个爷爷
		int grandpa = i++;

		/* 添加这些节点到场景上 */
		pane.getChildren().add(人类队列.get(grandpa).getImg());
		pane.getChildren().add(人类队列.get(grandpa).HP);
		pane.getChildren().add(人类队列.get(grandpa).MP);
		人类队列.add(new Butterfly());// 再添加一个小蝴蝶
		int butterfly = i++;
		pane.getChildren().add(人类队列.get(butterfly).getImg());
		pane.getChildren().add(人类队列.get(butterfly).HP);
		pane.getChildren().add(人类队列.get(butterfly).MP);
	}

	/* 加载妖怪阵营 */
	public void 添加妖怪() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		int index = 0;
		Class<?> boss = Class.forName("com.nju.cwl.Boss");
		@SuppressWarnings("unchecked")
		Constructor<Boss> constructor = (Constructor<Boss>) boss.getDeclaredConstructor();
		Boss obj = constructor.newInstance();
		妖怪队列.add(obj);
		/* 添加相关节点到场景上面 */
		pane.getChildren().add(妖怪队列.get(index).imageView);
		pane.getChildren().add(妖怪队列.get(index).HP);
		pane.getChildren().add(妖怪队列.get(index).MP);
		index++;
		for (int i = 0; i < 15; ++i)
		{
			妖怪队列.add(new Frog(i));
			/* 添加相关节点到场景上面 */
			pane.getChildren().add(妖怪队列.get(index + i).imageView);
			pane.getChildren().add(妖怪队列.get(index + i).HP);
			pane.getChildren().add(妖怪队列.get(index + i).MP);
		}
		妖怪队列.add(new Snake());
		/* 添加相关节点到场景上面 */
		pane.getChildren().add(妖怪队列.get(16).imageView);
		pane.getChildren().add(妖怪队列.get(16).HP);
		pane.getChildren().add(妖怪队列.get(16).MP);

	}

	public void 添加按钮()
	{
		pane.getChildren().add(方阵1button);
		pane.getChildren().add(方阵2button);
		pane.getChildren().add(方阵3button);
		pane.getChildren().add(方阵4button);
		pane.getChildren().add(方阵5button);
		pane.getChildren().add(方阵6button);
		pane.getChildren().add(方阵7button);
		pane.getChildren().add(背景音乐开关button);
		pane.getChildren().add(加速button);
		pane.getChildren().add(减速button);
		pane.getChildren().add(换背景button);
	}

	/* 加载阵型 */
	public void 添加阵型()
	{
		阵型队列[0] = new YanXing();
		阵型队列[1] = new YuLin();
		阵型队列[2] = new YanYue();
		阵型队列[3] = new FengShi();
		阵型队列[4] = new FangYuan();
		阵型队列[5] = new ChongE();
		阵型队列[6] = new SanJiao();
	}

	/* 根据index参数来展示阵型的函数 */
	
	
	
	public void 展示阵型(int index)
	{

//		if (上次阵型 == index)
//		{
//			弹出警告窗口("现在就是这个阵型，请变换阵型");
//			return;
//		}

		上次阵型 = index;
		if (游戏开始)
		{
			正在移动 = true;
		}

		num = 0;
		count = 0;
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{

			if (游戏开始)
			{
				if (妖怪队列.get(i).name.equals("Boss") || 妖怪队列.get(i).name.equals("Snake"))
				{
					continue;
				}
			}
//			System.out.println(妖怪队列.get(i).srcNode.getX() + "," + 妖怪队列.get(i).srcNode.getY()
//					+ 阵型队列[index].formationPos[i].y + "," + 阵型队列[index].formationPos[i].y);
			if (妖怪队列.get(i).srcNode.getX() == 阵型队列[index].formationPos[i].x
					&& 妖怪队列.get(i).srcNode.getY() == 阵型队列[index].formationPos[i].y)
			{
				continue;/* 本来就在这个位置。所以不需要移动。 */
			}
			if (格子内是否有人(阵型队列[index].formationPos[i].x / Formation.M, 阵型队列[index].formationPos[i].y / Formation.M))
			{
				continue;
			}
			count++;
		}

		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			if (游戏开始)
			{
				if (妖怪队列.get(i).name.equals("Boss") || 妖怪队列.get(i).name.equals("Snake"))
				{
					continue;
				}
			}
			if (妖怪队列.get(i).srcNode.getX() == 阵型队列[index].formationPos[i].x
					&& 妖怪队列.get(i).srcNode.getY() == 阵型队列[index].formationPos[i].y)
			{
				continue;/* 本来就在这个位置。所以不需要移动。 */
			}
			if (格子内是否有人(阵型队列[index].formationPos[i].x / Formation.M, 阵型队列[index].formationPos[i].y / Formation.M))
			{
				continue;
			}

			for (int i1 = 0; i1 < 墓碑队列.size(); ++i1)
			{
				if (((int) 墓碑队列.get(i1).getX()) == 阵型队列[index].formationPos[i].x
						&& (int) 墓碑队列.get(i1).getY() == 阵型队列[index].formationPos[i].y)
				{
					pane.getChildren().remove(墓碑队列.get(i1));
				}
			}

			妖怪队列.get(i).getImg().setImage(new Image(妖怪队列.get(i).动图URL));

			妖怪队列.get(i).getImg().setLayoutX(0);
			妖怪队列.get(i).getImg().setLayoutY(0);
			妖怪队列.get(i).getHP().setLayoutX(0);
			妖怪队列.get(i).getHP().setLayoutY(0);
			妖怪队列.get(i).getMP().setLayoutX(0);
			妖怪队列.get(i).getMP().setLayoutY(0);

			PathTransition pt = new PathTransition(Duration.millis(Formation.time),
					new Line(妖怪队列.get(i).srcNode.getX() + 0.5 * Formation.M,
							妖怪队列.get(i).srcNode.getY() + 0.5 * Formation.M,
							阵型队列[index].formationPos[i].x + 0.5 * Formation.M,
							阵型队列[index].formationPos[i].y + 0.5 * Formation.M),
					妖怪队列.get(i).getImg());
			int XXX = i;
			if (游戏开始)
			{
				是否正在执行动画 = true;
			}
			flag.set(true);
			pt.setOnFinished((a1) -> {
				妖怪队列.get(XXX).getImg().setImage(new Image(妖怪队列.get(XXX).imgURL));
				正在移动 = false;
				num++;
//				System.out.println("num ==" + num);
//				System.out.println(count);
				if (游戏开始)
				{
					是否正在执行动画 = false;
					if (flag.get() && count == num)
					{
						try
						{
							num = 0;
							读取文件播放();
							return;
						} catch (IOException | InterruptedException e)
						{
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}
			});
			/* 移动完当然是恢复静态啦! */
			pt.play();
			PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
					new Line(妖怪队列.get(i).srcNode.getX() + 0.5 * 妖怪队列.get(i).getHP().getWidth(),
							妖怪队列.get(i).srcNode.getY() - Formation.MP高度 - Formation.HP高度,
							阵型队列[index].formationPos[i].x + 0.5 * 妖怪队列.get(i).getHP().getWidth(),
							阵型队列[index].formationPos[i].y - Formation.MP高度 - Formation.HP高度),
					妖怪队列.get(i).HP);
			ptHP.play();
			PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
					new Line(妖怪队列.get(i).srcNode.getX() + 0.5 * 妖怪队列.get(i).getMP().getWidth(),
							妖怪队列.get(i).srcNode.getY() - Formation.MP高度,
							阵型队列[index].formationPos[i].x + 0.5 * 妖怪队列.get(i).getMP().getWidth(),
							阵型队列[index].formationPos[i].y - Formation.MP高度),
					妖怪队列.get(i).MP);
			ptMP.play();
			// cwl_debug_test
//			System.out.println(妖怪队列.get(i).getImg().getLayoutX() + " : " + 妖怪队列.get(i).getImg().getLayoutY());
			妖怪队列.get(i).setSrcNode(阵型队列[index].formationPos[i]);
			妖怪队列.get(i).更新真实坐标();
			更新格子地图();
			打印格子地图();
		}
	}

	public void 添加顶部所有按钮()
	{

		方阵1button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵2button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵3button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵4button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵5button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵6button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵7button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		背景音乐开关button.setMaxSize(15 * Formation.M, 0.4 * Formation.M);
		加速button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		减速button.setMaxSize(0.6 * Formation.M, 0.4 * Formation.M);
		换背景button.setMaxSize(1 * Formation.M, 0.4 * Formation.M);

		方阵1button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵2button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵3button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵4button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵5button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵6button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		方阵7button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		背景音乐开关button.setMinSize(1 * Formation.M, 0.4 * Formation.M);
		加速button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		减速button.setMinSize(0.6 * Formation.M, 0.4 * Formation.M);
		换背景button.setMinSize(1 * Formation.M, 0.4 * Formation.M);

		方阵1button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		方阵2button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		方阵3button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		方阵4button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		方阵5button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		方阵6button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		方阵7button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		背景音乐开关button.setFont(Font.font("Tahoma", Formation.M * 15.0 / 90.0));
		加速button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		减速button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));
		换背景button.setFont(Font.font("Tahoma", Formation.M * 13.0 / 90.0));

		方阵1button.setLayoutX(1.5 * Formation.M);
		方阵1button.setLayoutY(0);
		方阵2button.setLayoutX(2.2 * Formation.M);
		方阵2button.setLayoutY(0);
		方阵3button.setLayoutX(2.9 * Formation.M);
		方阵3button.setLayoutY(0);
		方阵4button.setLayoutX(3.6 * Formation.M);
		方阵4button.setLayoutY(0);
		方阵5button.setLayoutX(4.3 * Formation.M);
		方阵5button.setLayoutY(0);
		方阵6button.setLayoutX(5.0 * Formation.M);
		方阵6button.setLayoutY(0);
		方阵7button.setLayoutX(5.7 * Formation.M);
		方阵7button.setLayoutY(0);
		背景音乐开关button.setLayoutX(6.4 * Formation.M);
		背景音乐开关button.setLayoutY(0);
		加速button.setLayoutX(0 * Formation.M);
		加速button.setLayoutY(0);
		减速button.setLayoutX(0.7 * Formation.M);
		减速button.setLayoutY(0);
		换背景button.setLayoutX(8 * Formation.M);
		换背景button.setLayoutY(0);
		换背景button.setId("button11");
//		方阵1button.setOnAction((a1) -> {
//			展示阵型(0);
//		});
//		方阵2button.setOnAction((a1) -> {
//			展示阵型(1);
//		});
//		方阵3button.setOnAction((a1) -> {
//			展示阵型(2);
//		});
//		方阵4button.setOnAction((a1) -> {
//			展示阵型(3);
//		});
//		方阵5button.setOnAction((a1) -> {
//			展示阵型(4);
//		});
//		方阵6button.setOnAction((a1) -> {
//			展示阵型(5);
//		});
//		方阵7button.setOnAction((a1) -> {
//			展示阵型(6);
//		});
		加速button.setOnAction((a1) -> {
			if (Formation.time >= 1000)
			{
				Formation.time -= 500;
				移动速度label.setText("移动速度: " + (20 - (Formation.time / 1000.0) * 2));
			}
		});
		减速button.setOnAction((a1) -> {
			if (Formation.time <= 9000)
			{
				Formation.time += 500;
				移动速度label.setText("移动速度: " + (20 - (Formation.time / 1000.0) * 2));

			}
		});
//		葫芦娃归位button.setOnAction((a1) -> {
//			try
//			{
//				人类移动到起始点();
//			} catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//		});
//		换背景button.setOnAction((a1) -> {
//			String url = 背景图片数组[随机数换背景.nextInt(8)];
//			String urlt = 背景图片.getImage().getUrl();
//			while (url.equals(urlt))
//			{
//				url = 背景图片数组[随机数换背景.nextInt(8)];
//			}
//			背景图片.setImage(new Image(url));
//		});
	}

	public void 更新可执行步数()
	{
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			人类队列.get(i).物理攻击伤害 = 人类队列.get(i).原来物理伤害;
		}
//		System.out.println("更新可执行步数：人类人数：" + 人类队列.size());
		for (int i = 0; i < 人类队列.size(); ++i)
		{

			if (人类队列.get(i).name.equals("grandpa") || 人类队列.get(i).name.equals("butterfly"))
			{
				人类队列.get(i).每局可执行次数 = 1;
			} else
			{
				人类队列.get(i).每局可执行次数 = 2;
			}

			if (人类队列.size() < 人类总步数)
			{
				人类队列.get(i).每局可执行次数 = 1 + 人类总步数 - 人类队列.size();
			}
		}
//		System.out.println("更新可执行步数：妖怪人数：" + 妖怪队列.size());

		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			if (妖怪队列.get(i).name.equals("Boss") || 妖怪队列.get(i).name.equals("Snake"))
			{
				妖怪队列.get(i).每局可执行次数 = 2;
			} else
			{
				妖怪队列.get(i).每局可执行次数 = 1;
			}
			if (妖怪队列.size() < 妖怪总步数)
			{
				妖怪队列.get(i).每局可执行次数 = 1 + 妖怪总步数 - 妖怪队列.size();
			}

		}
	}

	public void 妖怪步数减一()
	{
		妖怪剩余步数 = 妖怪剩余步数 - 1;
		妖怪步数数组[妖怪剩余步数].setFill(Color.BLACK);
//		System.out.println("妖怪剩余步数-1: 剩余步数： " + 妖怪剩余步数);

		if (妖怪剩余步数 == 0)
		{
			人类剩余步数 = 人类总步数;
			更新可执行步数();
			正在执行的玩家 = 人类;
			for (int i = 0; i < 人类步数数组.length; ++i)
			{
				人类步数数组[i].setFill(Color.RED);
			}
		}
	}

	public void 人类步数减一()
	{
		人类剩余步数 = 人类剩余步数 - 1;
		人类步数数组[人类剩余步数].setFill(Color.BLACK);
//		System.out.println("人类剩余步数-1: 剩余步数： " + 人类剩余步数);
		if (人类剩余步数 == 0)
		{
			妖怪剩余步数 = 妖怪总步数;
			更新可执行步数();
			正在执行的玩家 = 妖怪;
			for (int i = 0; i < 妖怪步数数组.length; ++i)
			{
				妖怪步数数组[i].setFill(Color.RED);
			}
		}
	}

	public void 全体就位()
	{
		for (int i = 0; i < 人类队列.size(); ++i)
		{

			人类队列.get(i).imageView.setLayoutX(人类队列.get(i).destNode.x);
			人类队列.get(i).imageView.setLayoutY(人类队列.get(i).destNode.y);
		}
		// cwl_debug_test
//		System.out.println(妖怪队列.get(0).imageView.getLayoutX() + " " + 妖怪队列.get(0).imageView.getLayoutY());
//		System.out.println("妖精排放：");
//		System.out.println(妖怪队列.size());
//		System.out.println(阵型队列[0].formationPos.length);

		// cwl_debug_test
		for (int i = 0; i < 17; ++i)
		{
//			System.out.println(阵型队列[0].formationPos[i]);
		}
		// cwl_debug_test
//		System.out.println("====================================");
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
//			System.out.println(妖怪队列.get(i).srcNode);
		}
		// cwl_debug_test
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
//			System.out.println(妖怪队列.get(i).name);
		}
		// cwl_debug_test
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
//			System.out.println(妖怪队列.get(i).imgURL);
		}
//		System.out.println(阵型队列[0].formationPos.length);
//		System.out.println("============human========================");
//		System.out.println(人类队列.size());
		// cwl_debug_test
		for (int i = 0; i < 人类队列.size(); ++i)
		{
//			System.out.println(人类队列.get(i).imageView.getFitHeight() + " : " + 人类队列.get(i).imageView.getFitWidth());
//			System.out.println(人类队列.get(i).imageView.getLayoutX() + " : " + 人类队列.get(i).imageView.getLayoutY());
		}
		// cwl_debug_test
//		System.out.println("==========monster===================");
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
//			System.out.println(妖怪队列.get(i).imageView.getFitHeight() + " : " + 妖怪队列.get(i).imageView.getFitWidth());
		}
		// cwl_debug_test
//		System.out.println("====================================");
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			人类线程组[i] = new Thread(人类队列.get(i));
		}
		for (int i = 0; i < 人类线程组.length; ++i)
		{
			人类线程组[i].start();
		}

	}

	/* 展示函数1 */

	public int 鼠标点击下的格子是人还是妖精(int realX, int realY)
	{
		int flag = 格子内没人;
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (人类队列.get(i).srcNode.getX() == realX * Formation.M && 人类队列.get(i).srcNode.getY() == realY * Formation.M)
			{
				if (玩家的战斗选择 == 取消行动)
				{
					正在执行操作的格子中的人物 = 人类队列.get(i);
					鼠标选中的格子内的人物 = 人类队列.get(i);
					flag = 格子内为人类;
					return flag;
				} else
				{
					鼠标选中的格子内的人物 = 人类队列.get(i);
					flag = 格子内为人类;
					return flag;
				}

			}
		}

		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			if (妖怪队列.get(i).srcNode.getX() == realX * Formation.M && 妖怪队列.get(i).srcNode.getY() == realY * Formation.M)
			{
				if (玩家的战斗选择 == 取消行动)
				{
					正在执行操作的格子中的人物 = 妖怪队列.get(i);
					鼠标选中的格子内的人物 = 妖怪队列.get(i);
					flag = 格子内为妖怪;
					return flag;
				} else
				{
					鼠标选中的格子内的人物 = 妖怪队列.get(i);
					flag = 格子内为妖怪;
					return flag;
				}

			}
		}
		return flag;
	}

	/**
	 * 现阶段假设只实现了移动这个功能
	 */

	public void 弹出警告窗口非showAndWait(String 内容)
	{
		警告窗口.setHeaderText(null);
		警告窗口.setContentText(内容);
		DialogPane dialogPane = 警告窗口.getDialogPane();
		dialogPane.getStylesheets().add("file:src/main/resources/CSS/Font.css");
		警告窗口.setOnHidden(evt -> System.out.println("===================================="));
		警告窗口.show();
	}

	public void 弹出警告窗口(String 内容)
	{
		警告窗口.setHeaderText(null);
		警告窗口.setContentText(内容);
		DialogPane dialogPane = 警告窗口.getDialogPane();
		dialogPane.getStylesheets().add("file:src/main/resources/CSS/Font.css");
		警告窗口.showAndWait();

	}

	public void 人类移动到起始点() throws InterruptedException
	{
		// cwl_debug_test
		for (int i = 0; i < 人类队列.size(); ++i)
		{
//			System.out.println(人类队列.get(i));
		}
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (人类队列.get(i).srcNode.getX() == 人类队列.get(i).destNode.x
					&& 人类队列.get(i).srcNode.getY() == 人类队列.get(i).destNode.y)
			{
				continue;
			}
			if (格子内是否有人(人类队列.get(i).destNode.x / Formation.M, 人类队列.get(i).destNode.y / Formation.M))
			{
				continue;
			}
			人类队列.get(i).getImg().setImage(new Image(人类队列.get(i).动图URL));
			人类队列.get(i).getImg().setLayoutX(0);
			人类队列.get(i).getImg().setLayoutY(0);
			人类队列.get(i).getHP().setLayoutX(0);
			人类队列.get(i).getHP().setLayoutY(0);
			人类队列.get(i).getMP().setLayoutX(0);
			人类队列.get(i).getMP().setLayoutY(0);
			PathTransition pt = new PathTransition(Duration.millis(Formation.time),
					new Line(人类队列.get(i).srcNode.getX() + 0.5 * Formation.M,
							人类队列.get(i).srcNode.getY() + 0.5 * Formation.M, 人类队列.get(i).destNode.x + 0.5 * Formation.M,
							人类队列.get(i).destNode.y + 0.5 * Formation.M),
					人类队列.get(i).getImg());
			int XXX = i;
			pt.setOnFinished((a1) -> {
				人类队列.get(XXX).getImg().setImage(new Image(人类队列.get(XXX).imgURL));
			});
			pt.play();
			PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
					new Line(人类队列.get(i).srcNode.getX() + 0.5 * 人类队列.get(i).getHP().getWidth(),
							人类队列.get(i).srcNode.getY() - Formation.MP高度 - Formation.HP高度,
							人类队列.get(i).destNode.x + 0.5 * 人类队列.get(i).getHP().getWidth(),
							人类队列.get(i).destNode.y - Formation.MP高度 - Formation.HP高度),
					人类队列.get(i).HP);
			ptHP.play();
			PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
					new Line(人类队列.get(i).srcNode.getX() + 0.5 * 人类队列.get(i).getMP().getWidth(),
							人类队列.get(i).srcNode.getY() - Formation.MP高度,
							人类队列.get(i).destNode.x + 0.5 * 人类队列.get(i).getMP().getWidth(),
							人类队列.get(i).destNode.y - Formation.MP高度),
					人类队列.get(i).MP);
			if (i == 0)
			{

				ptMP.setOnFinished((a) -> {
					游戏开始 = true;
					try
					{
						num = 0;
						读取文件播放();
					} catch (IOException | InterruptedException e)
					{
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
//				Alert alert = new Alert(Alert.AlertType.INFORMATION);
//			    alert.setTitle("游戏开始对话框");
//			    alert.setHeaderText("游戏开始");
//			    DialogPane dialogPane = alert.getDialogPane();
//				dialogPane.getStylesheets().add("file:CSS/Font2.css");
//			    alert.setContentText("游戏开始！！人类玩家先手！！");
//
//			    alert.setOnHidden(evt -> System.out.println("===================================="));
//			    
//			    alert.show();
				});
			}
			ptMP.play();

			// cwl_debug_test
//			System.out.println(
//					人类队列.get(i).name + "从 ： " + (人类队列.get(i).srcNode.getX()) + "," + (人类队列.get(i).srcNode.getY()));
			// cwl_debug_test
//			System.out.println("--->" + (人类队列.get(i).destNode.x) + "," + (人类队列.get(i).destNode.y));

			// cwl_debug_test
//			System.out.println(
//					人类队列.get(i).name + "HP ： " + (人类队列.get(i).HP.getLayoutX()) + "," + (人类队列.get(i).HP.getLayoutY()));
//			// cwl_debug_test
//			System.out.println("--->" + (人类队列.get(i).destNode.x) + ","
//					+ (人类队列.get(i).destNode.y - Formation.MP高度 - Formation.HP高度));
//			// cwl_debug_test
//			System.out.println(
//					人类队列.get(i).name + "MP ： " + (人类队列.get(i).MP.getLayoutX()) + "," + (人类队列.get(i).MP.getLayoutY()));
//			// cwl_debug_test
//			System.out.println("--->" + (人类队列.get(i).destNode.x) + "," + (人类队列.get(i).destNode.y - 0.05 * Formation.M));

			人类队列.get(i).setSrcNode(人类队列.get(i).destNode);
			人类队列.get(i).更新真实坐标();
			更新格子地图();
			打印格子地图();
		}

	}

	/* 移动人类节点 */
	public void 移动人类到目的点(Role role, int destX, int destY) throws InterruptedException, IOException
	{

		/**
		 * 如果有墓碑请移除
		 */
		if (role.name.equals("Boss") || role.name.equals("Snake") || role.name.indexOf("Frog") != -1)
		{
			更新伤害();
		}

		for (int i = 0; i < 墓碑队列.size(); ++i)
		{
			if (((int) 墓碑队列.get(i).getX()) == destX * Formation.M && (int) 墓碑队列.get(i).getY() == destY * Formation.M)
			{
				pane.getChildren().remove(墓碑队列.get(i));
			}
		}
		正在移动 = true;
		// cwl_debug_test
//		System.out.println("进入到move方法： " + "destX ： destY = " + destX + ":" + destY);
		if (role.srcNode.getX() == destX * Formation.M && role.srcNode.getY() == destY * Formation.M)
		{
			return;
		}

		/* 开始移动前,准备移动的素材 */
		role.getImg().setImage(new Image(role.动图URL));

		role.getImg().setLayoutX(0);
		role.getImg().setLayoutY(0);
		role.getHP().setLayoutX(0);
		role.getHP().setLayoutY(0);
		role.getMP().setLayoutX(0);
		role.getMP().setLayoutY(0);

		PathTransition pt = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * Formation.M, role.srcNode.getY() + 0.5 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M + 0.5 * Formation.M),
				role.getImg());
		是否正在执行动画 = true;
		pt.setOnFinished((a1) -> {
			role.getImg().setImage(new Image(role.imgURL));
			正在移动 = false;
			是否正在执行动画 = false;

			try
			{
				num = 0;
				读取文件播放();
			} catch (IOException | InterruptedException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		});
		/* 移动完当然是恢复静态啦! */
		/**
		 * 发现setOnFInished 是里程碑的事情!!!!
		 */
		pt.play();

		PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * role.getHP().getWidth(),
						role.srcNode.getY() - Formation.MP高度 - Formation.HP高度,
						destX * Formation.M + 0.5 * role.getHP().getWidth(),
						destY * Formation.M - Formation.MP高度 - Formation.MP高度),
				role.HP);
		ptHP.play();
		PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * role.getMP().getWidth(), role.srcNode.getY() - Formation.MP高度,
						destX * Formation.M + 0.5 * role.getMP().getWidth(), destY * Formation.M - Formation.MP高度),
				role.MP);
		ptMP.play();

		// cwl_debug_test
//		System.out.println(role.name + "从 ： " + (role.srcNode.getX()) + "," + (role.srcNode.getY()));
		// cwl_debug_test
//		System.out.println("--->" + (destX * Formation.M) + "," + (destY * Formation.M));

		role.srcNode.setX(destX * Formation.M);
		role.srcNode.setY(destY * Formation.M);
		/* 每次移动节点之后都更新真实坐标! */

		role.更新真实坐标();
		更新格子地图();
		打印格子地图();
		右边人物肖像名片.setImage(new Image(role.people_url));
		右边中部信息名片.setImage(new Image("file:src/main/resources/images/info_mid1.jpeg"));
		是否被选定 = false;
//		System.out.println(role.name + " :  真实坐标更新为 : " + role.realNode);

	}

	/* 移动妖怪节点 */
	public void 移动妖怪到目的点(Role role, int destX, int destY) throws IOException
	{

		if (role.name.equals("Boss") || role.name.equals("Snake") || role.name.indexOf("Frog") != -1)
		{
			更新伤害();
		}

//		 System.out.println("进入移动妖怪");
//		 System.out.println(destX + " : " + destY);
		for (int i = 0; i < 墓碑队列.size(); ++i)
		{
//			System.out.println((int) 墓碑队列.get(i).getX());
//			System.out.println((int) 墓碑队列.get(i).getY());
			if (((int) 墓碑队列.get(i).getX()) == destX * Formation.M && (int) 墓碑队列.get(i).getY() == destY * Formation.M)
			{
				pane.getChildren().remove(墓碑队列.get(i));
			}
		}
		正在移动 = true;
		// cwl_debug_test
//		System.out.println("进入到move方法： " + "destX ： destY = " + destX + ":" + destY);
		if (role.srcNode.getX() == destX * Formation.M && role.srcNode.getY() == destY * Formation.M)
		{
			return;
		}
		/* 开始移动前,准备移动的素材 */
		role.getImg().setImage(new Image(role.动图URL));

		role.getImg().setLayoutX(0);
		role.getImg().setLayoutY(0);
		role.getHP().setLayoutX(0);
		role.getHP().setLayoutY(0);
		role.getMP().setLayoutX(0);
		role.getMP().setLayoutY(0);

		PathTransition pt = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * Formation.M, role.srcNode.getY() + 0.5 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M + 0.5 * Formation.M),
				role.getImg());
		是否正在执行动画 = true;
		pt.setOnFinished((a1) -> {
			role.getImg().setImage(new Image(role.imgURL));
			正在移动 = false;
			是否正在执行动画 = false;
			try
			{
				num = 0;
				读取文件播放();
			} catch (IOException | InterruptedException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		});
		/* 移动完当然是恢复静态啦! */
		pt.play();

		PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * role.getHP().getWidth(),
						role.srcNode.getY() - Formation.MP高度 - Formation.HP高度,
						destX * Formation.M + 0.5 * role.getHP().getWidth(),
						destY * Formation.M - Formation.MP高度 - Formation.HP高度),
				role.HP);
		ptHP.play();
		PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * role.getMP().getWidth(), role.srcNode.getY() - Formation.MP高度,
						destX * Formation.M + 0.5 * role.getMP().getWidth(), destY * Formation.M - Formation.MP高度),
				role.MP);
		ptMP.play();
		// cwl_debug_test
//		System.out.println(role.name + "从 ： " + (role.srcNode.getX()) + "," + (role.srcNode.getY()));
		// cwl_debug_test
//		System.out.println("--->" + (destX * Formation.M) + "," + (destY * Formation.M));

		role.srcNode.setX(destX * Formation.M);
		role.srcNode.setY(destY * Formation.M);
		role.更新真实坐标();
		更新格子地图();
		打印格子地图();
		右边人物肖像名片.setImage(new Image(role.people_url));
		右边中部信息名片.setImage(new Image("file:src/main/resources/images/info_mid1.jpeg"));
		是否被选定 = false;
//		System.out.println(role.name + " :  真实坐标更新为 : " + role.realNode);
	}

	/* 判断该格子是否有人占位 */
	public boolean 格子内是否有人(int x, int y)
	{
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (人类队列.get(i).srcNode.getX() == x * Formation.M && 人类队列.get(i).srcNode.getY() == (y) * Formation.M)
			{
				格子内是否是葫芦娃 = true;
				格子内是否是妖怪 = false;
				return true;
			}
		}

		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			if (妖怪队列.get(i).srcNode.getX() == x * Formation.M && 妖怪队列.get(i).srcNode.getY() == (y) * Formation.M)
			{
				格子内是否是葫芦娃 = false;
				格子内是否是妖怪 = true;
				return true;
			}
		}
		return false;
	}

	public void 妖精全体攻击之后计算葫芦娃阵营伤亡()
	{
		int 减少的值 = 0;
		Role 要被打的人 = null;
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			要被打的人 = null;
			for (int j = 0; j < 人类队列.size(); ++j)
			{
				if (妖怪队列.get(i).realNode.y == 人类队列.get(j).realNode.y
						&& 妖怪队列.get(i).realNode.x > 人类队列.get(j).realNode.x)/* 当然是打最近的人，最近的人才能挡子弹 */
				{
					if (要被打的人 != null)
					{
						if (要被打的人.realNode.x < 人类队列.get(j).realNode.x)
						{
							要被打的人 = 人类队列.get(j);
						}
					} else
					{
						要被打的人 = 人类队列.get(j);
					}
//					int temp = 人类队列.get(j).nowHP;
//					人类队列.get(j).nowHP = 人类队列.get(j).nowHP - 妖怪队列.get(i).物理攻击伤害;
//					减少的值 =  temp - 人类队列.get(j).nowHP ;
//					被攻击玩家HP减少动画(人类队列.get(j),减少的值 );
//					break;/* 打完就结束了啊 */
				}
			}
			if (要被打的人 != null)
			{
				int temp = 要被打的人.nowHP;
				要被打的人.nowHP = 要被打的人.nowHP - 妖怪队列.get(i).物理攻击伤害;
				减少的值 = temp - 要被打的人.nowHP;
				被攻击玩家HP减少动画(要被打的人, 减少的值);
			}
		}

		for (int i = 0; i < 人类队列.size(); ++i)
		{
//			System.out.println("name:" + 人类队列.get(i).name + "  HP: " + 人类队列.get(i).nowHP);
		}
		/**
		 * 检查伤亡，死了的移除
		 */
		ArrayList<Role> tempList = new ArrayList<Role>();
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (人类队列.get(i).nowHP < 0)
			{
				人类队列.get(i).nowHP = 0;
				调整血条(人类队列.get(i));
				pane.getChildren().removeAll(人类队列.get(i).getImg(), 人类队列.get(i).getHP(), 人类队列.get(i).getMP());
				ImageView 墓碑 = new ImageView("file:src/main/resources/images/RIP.png");
				墓碑.setX(人类队列.get(i).realNode.x * Formation.M);
				墓碑.setY(人类队列.get(i).realNode.y * Formation.M);
				墓碑.setFitWidth(Formation.M);
				墓碑.setFitHeight(Formation.M);
				pane.getChildren().add(墓碑);
				墓碑队列.add(墓碑);
				continue;
			}
			tempList.add(人类队列.get(i));
		}
		人类队列 = tempList;
		/* 调整血条 */
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			调整血条(人类队列.get(i));
		}
		是否游戏结束了();
	}

	public void 青蛙全体攻击之后计算葫芦娃阵营伤亡()
	{
		int 减少的值 = 0;
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			if (妖怪队列.get(i).name.equals("Boss") || 妖怪队列.get(i).name.equals("Snake"))
			{
				continue;
			}
			for (int j = 0; j < 人类队列.size(); ++j)
			{
				if (妖怪队列.get(i).realNode.y == 人类队列.get(j).realNode.y && 妖怪队列.get(i).realNode.x > 人类队列.get(j).realNode.x)
				{
					int temp = 人类队列.get(j).nowHP;
					人类队列.get(j).nowHP = 人类队列.get(j).nowHP - 妖怪队列.get(i).物理攻击伤害;
					减少的值 = temp - 人类队列.get(j).nowHP;
					被攻击玩家HP减少动画(人类队列.get(j), 减少的值);
					break;/* 打完就结束了啊 */
				}
			}
		}
		ArrayList<Role> tempList = new ArrayList<Role>();
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (人类队列.get(i).nowHP < 0)
			{
				人类队列.get(i).nowHP = 0;
				调整血条(人类队列.get(i));
				pane.getChildren().removeAll(人类队列.get(i).getImg(), 人类队列.get(i).getHP(), 人类队列.get(i).getMP());
				ImageView 墓碑 = new ImageView("file:src/main/resources/images/RIP.png");
				墓碑.setX(人类队列.get(i).realNode.x * Formation.M);
				墓碑.setY(人类队列.get(i).realNode.y * Formation.M);
				墓碑.setFitWidth(Formation.M);
				墓碑.setFitHeight(Formation.M);
				pane.getChildren().add(墓碑);
				墓碑队列.add(墓碑);
				continue;
			}
			tempList.add(人类队列.get(i));
		}
//		System.out.println("青蛙全体进攻后人类人数：" + tempList.size());
		人类队列 = tempList;
		/* 调整血条 */
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			调整血条(人类队列.get(i));
		}
		是否游戏结束了();
	}

	public void 全体青蛙进攻() throws IOException
	{

		num = 0;
		播放声效(全体进攻String);
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			/* 妖怪队列中的每一个人都要发起物理攻击，不管打中了还是没有打中都好。 */
			if (妖怪队列.get(i).name.equals("Boss") || 妖怪队列.get(i).name.equals("Snake"))
			{
				num++;
				continue;
			}
			int tempRealX = 妖怪队列.get(i).realNode.x;
			int tempRealY = 妖怪队列.get(i).realNode.y;
			/* 轮到 i 妖怪进攻同一条直线的妖怪 */
			妖怪全体攻击时打空地(妖怪队列.get(i), 0, tempRealY);
//				妖怪队列.get(i).每局可执行次数--;
		}
		青蛙全体攻击之后计算葫芦娃阵营伤亡();
//		玩家的战斗选择 = 取消行动;
		格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
	}

	public void 妖怪全体进攻(Role role) throws IOException /* 既然选择了全体进攻，说明鼠标一定该选择了大王BOSS */, InterruptedException
	{

//		
		/* 全体发起物直线的物理攻击！ */

		role.nowMP = role.nowMP - 全体进攻消耗的MP值;
		调整法条(role);
		使用法术的玩家MP减少动画(role, 全体进攻消耗的MP值);
		ArrayList<Integer> indexs = new ArrayList<Integer>();/* 存储下表值 */

		num = 0;
		播放声效(全体进攻String);
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			/* 妖怪队列中的每一个人都要发起物理攻击，不管打中了还是没有打中都好。 */
			int tempRealX = 妖怪队列.get(i).realNode.x;
			int tempRealY = 妖怪队列.get(i).realNode.y;
			/* 轮到 i 妖怪进攻同一条直线的妖怪 */
			妖怪全体攻击时打空地(妖怪队列.get(i), 0, tempRealY);
//			Thread.sleep(10);
			/**
			 * 因为存在bug，我们就模拟这个画面好了，直接计算葫芦娃这边的死亡人数吧。
			 */
		}
		妖精全体攻击之后计算葫芦娃阵营伤亡();
		格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
	}

	public void 妖怪全体攻击时打空地(Role 物体, int destX, int dextY)
	{

//		物体.getImg().setImage(new Image(物体.动图URL));	
		物体.getImg().setImage(new Image("file:src/main/resources/attack/大王物攻.gif"));
		String 攻击动图URL = 物体.法术攻击URL;
		int 物体屏幕坐标X = 物体.realNode.x * Formation.M;
		int 物体屏幕坐标Y = 物体.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = destX * Formation.M;
		int 物体B屏幕坐标Y = dextY * Formation.M + Formation.top;/* 记得加上top */
		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setX(物体屏幕坐标X);
		攻击动图.setY(物体屏幕坐标Y);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体屏幕坐标X + 0.5 * Formation.M), (物体屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);
		正在攻击 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(雷电属性蓄力String);
			pane.getChildren().remove(攻击动图);
			物体.getImg().setImage(new Image(物体.imgURL));
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			num++;
			if (flag.get() && num == (妖怪队列.size()))
			{
				try
				{
					num = 0;
					System.out.println("妖怪全体进攻结束！");
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});

		pt.play();
		/* 因为只是打空气所以没有那么复杂 */

	}

	public void A攻击B(Role 物体A, Role 物体B) throws IOException
	{
		if (物体A.name.equals("Boss") || 物体A.name.equals("Snake") || 物体A.name.indexOf("Frog") != -1)
		{
			更新伤害();
		}
		String 攻击动图URL = 玩家的战斗选择 == 物理攻击 ? 物体A.物理攻击URL : 物体A.法术攻击URL;
		if (玩家的战斗选择 == 法术攻击)
		{
			使用法术的玩家MP减少动画(物体A, 物体A.法术攻击消耗的MP);
		}

		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = 物体B.realNode.x * Formation.M;
		int 物体B屏幕坐标Y = 物体B.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		if (物体A.name.equals("Boss"))
		{
			物体A.getImg().setImage(new Image("file:src/main/resources/attack/大王物攻.gif"));
		} else
			物体A.getImg().setImage(new Image(物体A.动图URL));
		/**
		 * 先飞一下试一试
		 */

		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setX(物体A屏幕坐标X);
		攻击动图.setY(物体A屏幕坐标Y);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);

		正在攻击 = true;
//		System.out.println("玩家的战斗选择： " + 玩家的战斗选择);
		是否正在执行动画 = true;
		打印玩家战斗选择();
		播放声效(呼啸String);
		pt.setOnFinished((a1) -> {
			打印玩家战斗选择();
			if (物体A.name.equals("Boss") || 物体A.name.indexOf("Frog") != -1)
			{
				播放声效(爆炸声String);
			} else
			{
				播放声效(击打声2String);

			}
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
//			System.out.println("玩家的战斗选择： " + 玩家的战斗选择); /* 说明这里 玩家的战斗选择被修改成了 取消行动了。！！bug */
			/* 巨大的BUG */
			/**
			 * 这里发现巨大的BUG
			 */
			if (玩家的战斗选择 == 物理攻击)
			{
				System.out.println(物体A.name + " : 物理攻击 ------>  " + 物体B.name);
				物体B.nowHP = 物体B.nowHP <= 物体A.物理攻击伤害 ? 0 : 物体B.nowHP - 物体A.物理攻击伤害;
				调整血条(物体B);
			} else if (玩家的战斗选择 == 法术攻击)
			{
				System.out.println(物体A.name + " : 法术攻击 ------>  " + 物体B.name);
				物体B.nowHP = 物体B.nowHP <= 物体A.法术攻击伤害 ? 0 : 物体B.nowHP - 物体A.法术攻击伤害;
				调整法条(物体A);
				调整血条(物体B);
			}

			if (物体B.nowHP == 0)
			{
				System.out.println("移除物体：" + 物体B.name + "物体B的HP：" + 物体B.nowHP);
				移除物体(物体B);
			}
			if (玩家的战斗选择 == 物理攻击)
			{
				被攻击玩家HP减少动画(物体B, 物体A.物理攻击伤害);
			} else
			{
				被攻击玩家HP减少动画(物体B, 物体A.法术攻击伤害);
			}

			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			/**
			 * 一定要在动画执行完才能判断是否步数减少，因为 动话里卖弄的代码后面才会窒执行， 动画外面的代码会被执行。
			 */

			try
			{
				num = 0;
				读取文件播放();
			} catch (IOException | InterruptedException e)
			{
				e.printStackTrace();
			}

		});
		pt.play();

		/* 注意！！上面的只是动画，下面的代码比上面的代码更快执行。 */
	}

	public void 被治疗玩家HP增加动画(Role role, int 增加的值)
	{
//		System.out.println("进入HP增加动画");
		int 物体A屏幕坐标X = role.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = role.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		int 动画向上移动的终点坐标X = 物体A屏幕坐标X;
		int 动画向上移动的终点坐标Y = 物体A屏幕坐标Y - Formation.M;

		Label label = new Label("HP + " + 增加的值);
		label.setFont(Font.font("Tahoma", Formation.M * 27.0 / 90.0));
		label.setTextFill(Color.RED);
		label.setLayoutX(0);
		label.setLayoutY(0);
		pane.getChildren().add(label);

		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(动画向上移动的终点坐标X + 0.5 * Formation.M), (动画向上移动的终点坐标Y - 0.5 * Formation.M));

		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, label);
		是否正在执行动画 = true;
		pt.setOnFinished((a1) -> {
			pane.getChildren().remove(label);
			是否正在执行动画 = false;
		});
		pt.play();
	}

	public void 被攻击玩家HP减少动画(Role role, int 减少的值)
	{
//		System.out.println("进入HP减少动画");
		int 物体A屏幕坐标X = role.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = role.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		int 动画向上移动的终点坐标X = 物体A屏幕坐标X;
		int 动画向上移动的终点坐标Y = 物体A屏幕坐标Y - Formation.M;

		Label label = new Label("HP - " + 减少的值);
		label.setFont(Font.font("Tahoma", Formation.M * 27.0 / 90.0));
		label.setTextFill(Color.RED);
		label.setLayoutX(0);
		label.setLayoutY(0);
		pane.getChildren().add(label);

		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(动画向上移动的终点坐标X + 0.5 * Formation.M), (动画向上移动的终点坐标Y - 0.5 * Formation.M));

		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, label);
		是否正在执行动画 = true;
		pt.setOnFinished((a1) -> {
			pane.getChildren().remove(label);
			是否正在执行动画 = false;
		});
		pt.play();
	}

	public void 被治疗玩家攻击力升高动画(Role role, int 爷爷发动技能增加的攻击值)
	{
//		System.out.println("进入HP增加动画");
		int 物体A屏幕坐标X = role.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = role.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		int 动画向上移动的终点坐标X = 物体A屏幕坐标X;
		int 动画向上移动的终点坐标Y = 物体A屏幕坐标Y - Formation.M;

		Label label = new Label("物理攻击 + " + 爷爷发动技能增加的攻击值);
		label.setFont(Font.font("Tahoma", Formation.M * 27.0 / 90.0));
		label.setTextFill(Color.RED);
		label.setLayoutX(0);
		label.setLayoutY(0);
		pane.getChildren().add(label);

		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(动画向上移动的终点坐标X + 0.5 * Formation.M), (动画向上移动的终点坐标Y - 0.5 * Formation.M));

		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, label);
		是否正在执行动画 = true;
		pt.setOnFinished((a1) -> {
			pane.getChildren().remove(label);
			是否正在执行动画 = false;
		});
		pt.play();
	}

	public void 使用法术的玩家MP减少动画(Role role, int 减少的值)
	{
//		System.out.println("进入MP减少动画");

		int 物体A屏幕坐标X = role.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = role.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		int 参数 = 3 * Formation.M;
		int 动画向上移动的终点坐标X = 物体A屏幕坐标X;
		int 动画向上移动的终点坐标Y = 物体A屏幕坐标Y - Formation.M;

		Label label = new Label("MP - " + 减少的值);
		label.setFont(Font.font("Tahoma", Formation.M * 27.0 / 90.0));
		label.setTextFill(Color.BLUE);
		label.setLayoutX(0);
		label.setLayoutY(0);
		pane.getChildren().add(label);

		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(动画向上移动的终点坐标X + 0.5 * Formation.M), (动画向上移动的终点坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, label);
		是否正在执行动画 = true;
		pt.setOnFinished((a1) -> {
			pane.getChildren().remove(label);
			是否正在执行动画 = false;
		});
		pt.play();
	}
	
	int 爷爷攻击属性专用计数变量 = 0;

	public void 爷爷增加攻击属性动图飞向全体成员(GrandPa 物体A, Role 物体B)
	{
		物体A.getImg().setImage(new Image(物体A.动图URL));
		String 攻击动图URL = "file:src/main/resources/attack/3.gif";
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = 物体B.realNode.x * Formation.M;
		int 物体B屏幕坐标Y = 物体B.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		;
		攻击动图.setLayoutX(0);
		;
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);

		是否正在执行动画 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(补血String);
			被治疗玩家攻击力升高动画(物体B, 物体A.爷爷发动技能增加的攻击值);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
//			玩家的战斗选择 = 取消行动;
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			爷爷攻击属性专用计数变量++;
			if (flag.get() && 爷爷攻击属性专用计数变量 == 人类队列.size() - 1)
			{
				try
				{
					爷爷攻击属性专用计数变量=0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}

		});
		pt.play();

	}
	/* 因该学的是小蝴蝶的群攻效果 */

	public void 爷爷攻击属性加成所有成员(GrandPa role)
	{
		// 先判断爷爷的MP值够不够，不够的话估计的再说
		role.nowMP = role.nowMP - role.爷爷攻击属性加成技能消耗MP;
		使用法术的玩家MP减少动画(role, role.爷爷攻击属性加成技能消耗MP);
		调整法条(role);
		num = 0;
		播放声效(呼啸String);
		for (int i = 0; i < 人类队列.size(); ++i)
		{

			if (!人类队列.get(i).name.equals("grandpa"))// 说明就是葫芦娃和小蝴蝶了。
			{
				人类队列.get(i).物理攻击伤害 = 人类队列.get(i).物理攻击伤害 + role.爷爷发动技能增加的攻击值;
				爷爷增加攻击属性动图飞向全体成员(role, 人类队列.get(i));
			}
		}

	}

	public void 小蝴蝶治疗全体成员(Butterfly role)
	{
		int 增加的血量 = 0;
		role.nowMP = role.nowMP - 小蝴蝶治疗技能需要消耗的MP;
		使用法术的玩家MP减少动画(role, role.法术攻击消耗的MP);
		调整法条(role);
		num = 0;

		播放声效(呼啸String);
		System.out.println("小蝴蝶全体治疗：人数：" + (人类队列.size() - 1));
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (!人类队列.get(i).name.equals("butterfly"))// 说明就是葫芦娃和爷爷了。
			{
				int temp = 人类队列.get(i).nowHP;

				人类队列.get(i).nowHP = (人类队列.get(i).nowHP + 小蝴蝶治疗HP量) >= 人类队列.get(i).maxHP ? 人类队列.get(i).maxHP
						: (人类队列.get(i).nowHP + 小蝴蝶治疗HP量);
				增加的血量 = 人类队列.get(i).nowHP - temp;
				/* 数据先行，等动画UI完成之后就出发 事件完成数据在UI画面上的体现。 */
				小蝴蝶治疗动图飞向各个成员(role, 人类队列.get(i), 增加的血量);

				/* 上面的指示演示了动画，动画演示了，相应的数据也该进行变动。 */
			}
		}
	}

	int 小蝴蝶专用计数变量 = 0;

	public void 小蝴蝶治疗动图飞向各个成员(Butterfly 物体A, Role 物体B, int 增加的血量)
	{
		物体A.getImg().setImage(new Image(物体A.动图URL));
		String 攻击动图URL = "file:src/main/resources/attack/butterfly.gif";
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = 物体B.realNode.x * Formation.M;
		int 物体B屏幕坐标Y = 物体B.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setX(物体A屏幕坐标X);
		攻击动图.setY(物体A屏幕坐标Y);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);
		是否正在执行动画 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(补血String);
			调整血条(物体B);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
			被治疗玩家HP增加动画(物体B, 增加的血量);
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			是否正在执行动画 = false;
			小蝴蝶专用计数变量++;

			System.out.println("num=" + num);
			/**
			 * 这里假设没有数量限制,因为发现设置数量限制会有BUG 我尽力了，貌似这个BUG无解。不过基本上不影响体验。
			 * 补充：发现给小蝴蝶安排特定变量就完事，很好解决啊。
			 */
			if (flag.get() && 小蝴蝶专用计数变量 == 人类队列.size() - 1)
			{
				try
				{
					小蝴蝶专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();
		/* 因为只是打空气所以没有那么复杂 */
	}

	int  爷爷法术群攻专用计数变量 =0;
	

	public void 爷爷法术攻击一个空格子(GrandPa 物体A, Node node)
	{
		String 攻击动图URL = 物体A.法术攻击URL;
		/* 得先执行一个减少MP的动画，因为涉及到了多个人 */
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = node.x * Formation.M;
		int 物体B屏幕坐标Y = node.y * Formation.M + Formation.top;/* 记得加上top */
		物体A.getImg().setImage(new Image(物体A.动图URL));
		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		攻击动图.setLayoutY(0);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);
		正在攻击 = true;
		是否正在执行动画 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(爆炸声String);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			爷爷法术群攻专用计数变量++;
			if (flag.get() && 爷爷法术群攻专用计数变量 == count)
			{
				try
				{
					爷爷法术群攻专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();
	
	}

	public void 爷爷法术群攻物体B(GrandPa 物体A, Role 物体B)
	{
		// 别的不说先获取物体B的具体坐标，然后实行围点打圆的群攻
		// 所以还要补充的节点是 8 个

//			人类步数减一();
		物体A.nowMP = 物体A.nowMP - 物体A.法术攻击消耗的MP;
		调整法条(物体A);
		使用法术的玩家MP减少动画(物体A, 物体A.法术攻击消耗的MP);
		Node[] 被群攻的九个格子 = { new Node(物体B.realNode.x, 物体B.realNode.y), new Node(物体B.realNode.x - 1, 物体B.realNode.y - 1),
				new Node(物体B.realNode.x, 物体B.realNode.y - 1), new Node(物体B.realNode.x + 1, 物体B.realNode.y - 1),
				new Node(物体B.realNode.x - 1, 物体B.realNode.y), new Node(物体B.realNode.x + 1, 物体B.realNode.y),
				new Node(物体B.realNode.x - 1, 物体B.realNode.y + 1), new Node(物体B.realNode.x, 物体B.realNode.y + 1),
				new Node(物体B.realNode.x + 1, 物体B.realNode.y + 1) };
//		System.out.println(被群攻的九个格子.length);

		count = 0;
		for (int i = 0; i < 被群攻的九个格子.length; ++i)
		{

			if (被群攻的九个格子[i].x >= 0 && 被群攻的九个格子[i].y >= 0)
			{
				count++;
			}
		}

		num = 0;
		播放声效(呼啸String);

		for (int i = 0; i < 被群攻的九个格子.length; ++i)
		{
			boolean 有妖怪 = false;
			for (int j = 0; j < 妖怪队列.size(); ++j)
			{
				if (妖怪队列.get(j).realNode.x == 被群攻的九个格子[i].x && 妖怪队列.get(j).realNode.y == 被群攻的九个格子[i].y)
				{
					爷爷法术攻击物体B(物体A, 妖怪队列.get(j));
					有妖怪 = true;
					break;
				}
			}

			if (被群攻的九个格子[i].x >= 0 && 被群攻的九个格子[i].y >= 0 && 有妖怪 == false)
			{
				爷爷法术攻击一个空格子(物体A, 被群攻的九个格子[i]);
			}
		}

	}

	public void 爷爷法术攻击物体B(GrandPa 物体A, Role 物体B)
	{
//		System.out.println("进入爷爷群攻B界面");
		String 攻击动图URL = 物体A.法术攻击URL;
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = 物体B.realNode.x * Formation.M;
		int 物体B屏幕坐标Y = 物体B.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		物体A.getImg().setImage(new Image(物体A.动图URL));

		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		攻击动图.setLayoutX(0);
		;
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);

		正在攻击 = true;
		是否正在执行动画 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(爆炸声String);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
			System.out.println(物体A.name + " : 法术攻击 ------>  " + 物体B.name);
			物体B.nowHP = 物体B.nowHP <= 物体A.法术攻击伤害 ? 0 : 物体B.nowHP - 物体A.法术攻击伤害;
			调整法条(物体A);
			调整血条(物体B);

			if (物体B.nowHP == 0)
			{
				System.out.println("物体B的HP：" + 物体B.nowHP);
				移除物体(物体B);
			}
			被攻击玩家HP减少动画(物体B, 物体A.法术攻击伤害);

			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			爷爷法术群攻专用计数变量++;
			if (flag.get() && 爷爷法术群攻专用计数变量 == count)
			{
				try
				{
					爷爷法术群攻专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();
	}

	int 蛇精法术群攻专用计数变量 = 0;

	public void 蛇精法术攻击一个空格子(Snake 物体A, Node node)
		{
			String 攻击动图URL = 物体A.法术攻击URL;
			/* 得先执行一个减少MP的动画，因为涉及到了多个人 */
			int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
			int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
			int 物体B屏幕坐标X = node.x * Formation.M;
			int 物体B屏幕坐标Y = node.y * Formation.M + Formation.top;/* 记得加上top */
			物体A.getImg().setImage(new Image(物体A.动图URL));
			ImageView 攻击动图 = new ImageView(攻击动图URL);
			攻击动图.setFitHeight(Formation.M);
			攻击动图.setFitWidth(Formation.M);
			攻击动图.setLayoutX(0);
			攻击动图.setLayoutY(0);
			pane.getChildren().add(攻击动图);
			Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
					(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
			PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);
			正在攻击 = true;
			是否正在执行动画 = true;
			flag.set(true);
			pt.setOnFinished((a1) -> {
				播放声效(爆炸声String);
				pane.getChildren().remove(攻击动图);
				物体A.getImg().setImage(new Image(物体A.imgURL));
	//			玩家的战斗选择 = 取消行动;
				格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
				正在攻击 = false;
				蛇精法术群攻专用计数变量++;
				if (flag.get() && count == 蛇精法术群攻专用计数变量)
				{
					try
					{
						蛇精法术群攻专用计数变量 = 0;
						num = 0;
						读取文件播放();
					} catch (IOException | InterruptedException e)
					{
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			});
			pt.play();
	
		}

	public void 蛇精法术群攻物体B(Snake 物体A, Role 物体B)
	{
//			妖怪步数减一();
		// 别的不说先获取物体B的具体坐标，然后实行围点打圆的群攻
		// 所以还要补充的节点是 8 个
		物体A.nowMP = 物体A.nowMP - 物体A.法术攻击消耗的MP;
		调整法条(物体A);
		使用法术的玩家MP减少动画(物体A, 物体A.法术攻击消耗的MP);
		Node[] 被群攻的五个格子 = { new Node(物体B.realNode.x, 物体B.realNode.y), new Node(物体B.realNode.x, 物体B.realNode.y - 1),
				new Node(物体B.realNode.x - 1, 物体B.realNode.y), new Node(物体B.realNode.x + 1, 物体B.realNode.y),
				new Node(物体B.realNode.x, 物体B.realNode.y + 1) };
//		System.out.println(被群攻的五个格子.length);
		count = 0;
		for (int i = 0; i < 被群攻的五个格子.length; ++i)
		{

			if (被群攻的五个格子[i].x >= 0 && 被群攻的五个格子[i].y >= 0)
			{
				count++;
			}
		}
		num = 0;
		播放声效(飞机投弹声String);
		for (int i = 0; i < 被群攻的五个格子.length; ++i)
		{
			boolean 有妖怪 = false;
			for (int j = 0; j < 人类队列.size(); ++j)
			{
				if (人类队列.get(j).realNode.x == 被群攻的五个格子[i].x && 人类队列.get(j).realNode.y == 被群攻的五个格子[i].y)
				{
					蛇精法术攻击物体B(物体A, 人类队列.get(j));
					有妖怪 = true;
					break;
				}
			}

			if (被群攻的五个格子[i].x >= 0 && 被群攻的五个格子[i].y >= 0 && 有妖怪 == false)
			{
				蛇精法术攻击一个空格子(物体A, 被群攻的五个格子[i]);
			}
		}

	}

	public void 蛇精法术攻击物体B(Snake 物体A, Role 物体B)
	{
//		System.out.println("进入爷爷群攻B界面");
		String 攻击动图URL = 物体A.法术攻击URL;
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = 物体B.realNode.x * Formation.M;
		int 物体B屏幕坐标Y = 物体B.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		物体A.getImg().setImage(new Image(物体A.动图URL));

		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		攻击动图.setLayoutX(0);
		;
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);

		正在攻击 = true;
		是否正在执行动画 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(爆炸声String);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
			System.out.println(物体A.name + " : 法术攻击 ------>  " + 物体B.name);
			物体B.nowHP = 物体B.nowHP <= 物体A.法术攻击伤害 ? 0 : 物体B.nowHP - 物体A.法术攻击伤害;
			调整法条(物体A);
			调整血条(物体B);

			if (物体B.nowHP == 0)
			{
				System.out.println("物体B的HP：" + 物体B.nowHP);
				移除物体(物体B);
			}
			被攻击玩家HP减少动画(物体B, 物体A.法术攻击伤害);

			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			蛇精法术群攻专用计数变量++;
			if (flag.get() && count == 蛇精法术群攻专用计数变量)
			{
				try
				{
					蛇精法术群攻专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();
	}

	int 爷爷物理群攻专用计数变量 = 0;
	
	public void 爷爷物理群群攻物体B(GrandPa 物体A, Role 物体B)
	{
//			人类步数减一();
		// 别的不说先获取物体B的具体坐标，然后实行围点打圆的群攻
		// 所以还要补充的节点是 8 个
		物体A.每局可执行次数--;
		Node[] 被群攻的九个格子 = { 物体B.realNode, new Node(物体B.realNode.x - 1, 物体B.realNode.y - 1),
				new Node(物体B.realNode.x, 物体B.realNode.y - 1), new Node(物体B.realNode.x + 1, 物体B.realNode.y - 1),
				new Node(物体B.realNode.x - 1, 物体B.realNode.y), new Node(物体B.realNode.x + 1, 物体B.realNode.y),
				new Node(物体B.realNode.x - 1, 物体B.realNode.y + 1), new Node(物体B.realNode.x, 物体B.realNode.y + 1),
				new Node(物体B.realNode.x + 1, 物体B.realNode.y + 1) };

		count = 0;
		for (int i = 0; i < 被群攻的九个格子.length; ++i)
		{

			if (被群攻的九个格子[i].x >= 0 && 被群攻的九个格子[i].y >= 0)
			{
				count++;
			}
		}
		num = 0;
		播放声效(呼啸String);
		for (int i = 0; i < 被群攻的九个格子.length; ++i)
		{
			boolean 有妖怪 = false;
			for (int j = 0; j < 妖怪队列.size(); ++j)
			{
				if (妖怪队列.get(j).realNode.x == 被群攻的九个格子[i].x && 妖怪队列.get(j).realNode.y == 被群攻的九个格子[i].y)
				{
					爷爷物理攻击物体B(物体A, 妖怪队列.get(j));
					有妖怪 = true;
					break;
				}
			}

			if (被群攻的九个格子[i].x >= 0 && 被群攻的九个格子[i].y >= 0 && 有妖怪 == false)
			{
				爷爷物理攻击一个空格子(物体A, 被群攻的九个格子[i]);
			}
		}

	}

	public void 爷爷物理攻击一个空格子(GrandPa 物体A, Node node)
	{
		String 攻击动图URL = 物体A.物理攻击URL;
		/* 得先执行一个减少MP的动画，因为涉及到了多个人 */
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = node.x * Formation.M;
		int 物体B屏幕坐标Y = node.y * Formation.M + Formation.top;/* 记得加上top */
		物体A.getImg().setImage(new Image(物体A.动图URL));
		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		攻击动图.setLayoutY(0);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);
		正在攻击 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(爆炸声String);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			爷爷物理群攻专用计数变量++;
			if (flag.get() && 爷爷物理群攻专用计数变量 == count)
			{
				try
				{
					爷爷物理群攻专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();

	}

	public void 爷爷物理攻击物体B(GrandPa 物体A, Role 物体B)
	{
		String 攻击动图URL = 物体A.物理攻击URL;
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = 物体B.realNode.x * Formation.M;
		int 物体B屏幕坐标Y = 物体B.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		物体A.getImg().setImage(new Image(物体A.动图URL));

		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		攻击动图.setLayoutY(0);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);

		正在攻击 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(爆炸声String);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
			System.out.println(物体A.name + " : 物理攻击 ------>  " + 物体B.name);
			物体B.nowHP = 物体B.nowHP <= 物体A.物理攻击伤害 ? 0 : 物体B.nowHP - 物体A.物理攻击伤害;
			调整法条(物体A);
			调整血条(物体B);

			if (物体B.nowHP == 0)
			{
				System.out.println("物体B的HP：" + 物体B.nowHP);
				移除物体(物体B);
			}
			被攻击玩家HP减少动画(物体B, 物体A.物理攻击伤害);

//			玩家的战斗选择 = 取消行动;
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			爷爷物理群攻专用计数变量++;
			if (flag.get() && 爷爷物理群攻专用计数变量 == count)
			{
				try
				{
					爷爷物理群攻专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();
	}

	int 蛇精物理群攻专用计数变量 = 0;
	
	public void 蛇精物理群群攻物体B(Snake 物体A, Role 物体B)
	{

//		妖怪步数减一();
//		别的不说先获取物体B的具体坐标，然后实行围点打圆的群攻
//		所以还要补充的节点是 8 个  
		物体A.每局可执行次数--;
		Node[] 被群攻的五个格子 = { 物体B.realNode, new Node(物体B.realNode.x, 物体B.realNode.y - 1),
				new Node(物体B.realNode.x - 1, 物体B.realNode.y), new Node(物体B.realNode.x + 1, 物体B.realNode.y),
				new Node(物体B.realNode.x, 物体B.realNode.y + 1) };

		count = 0;
		for (int i = 0; i < 被群攻的五个格子.length; ++i)
		{
			if (被群攻的五个格子[i].x >= 0 && 被群攻的五个格子[i].y >= 0)
			{
				count++;
			}
		}

		num = 0;
		播放声效(飞机投弹声String);
		for (int i = 0; i < 被群攻的五个格子.length; ++i)
		{
			boolean 有妖怪 = false;
			for (int j = 0; j < 人类队列.size(); ++j)
			{
				if (人类队列.get(j).realNode.x == 被群攻的五个格子[i].x && 人类队列.get(j).realNode.y == 被群攻的五个格子[i].y)
				{
					蛇精物理攻击物体B(物体A, 人类队列.get(j));
					有妖怪 = true;
					break;
				}
			}

			if (被群攻的五个格子[i].x >= 0 && 被群攻的五个格子[i].y >= 0 && 有妖怪 == false)
			{
				蛇精物理攻击一个空格子(物体A, 被群攻的五个格子[i]);
			}
		}

	}

	public void 蛇精物理攻击一个空格子(Snake 物体A, Node node)
	{
		String 攻击动图URL = 物体A.物理攻击URL;
		/* 得先执行一个减少MP的动画，因为涉及到了多个人 */
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = node.x * Formation.M;
		int 物体B屏幕坐标Y = node.y * Formation.M + Formation.top;/* 记得加上top */
		物体A.getImg().setImage(new Image(物体A.动图URL));
		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		攻击动图.setLayoutY(0);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);
		正在攻击 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(爆炸声String);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
//			玩家的战斗选择 = 取消行动;
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			蛇精物理群攻专用计数变量++;
			if (flag.get() && 蛇精物理群攻专用计数变量 == count)
			{
				try
				{
					蛇精物理群攻专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();

	}

	public void 蛇精物理攻击物体B(Snake 物体A, Role 物体B)
	{
		String 攻击动图URL = 物体A.物理攻击URL;
		int 物体A屏幕坐标X = 物体A.realNode.x * Formation.M;
		int 物体A屏幕坐标Y = 物体A.realNode.y * Formation.M + Formation.top;/* 记得加上top */
		int 物体B屏幕坐标X = 物体B.realNode.x * Formation.M;
		int 物体B屏幕坐标Y = 物体B.realNode.y * Formation.M + Formation.top;/* 记得加上top */

		物体A.getImg().setImage(new Image(物体A.动图URL));

		ImageView 攻击动图 = new ImageView(攻击动图URL);
		攻击动图.setFitHeight(Formation.M);
		攻击动图.setFitWidth(Formation.M);
		攻击动图.setLayoutX(0);
		攻击动图.setLayoutY(0);
		pane.getChildren().add(攻击动图);
		Line line = new Line((物体A屏幕坐标X + 0.5 * Formation.M), (物体A屏幕坐标Y - 0.5 * Formation.M),
				(物体B屏幕坐标X + 0.5 * Formation.M), (物体B屏幕坐标Y - 0.5 * Formation.M));
		PathTransition pt = new PathTransition(Duration.millis(Formation.time), line, 攻击动图);

		正在攻击 = true;
		flag.set(true);
		pt.setOnFinished((a1) -> {
			播放声效(爆炸声String);
			pane.getChildren().remove(攻击动图);
			物体A.getImg().setImage(new Image(物体A.imgURL));
			System.out.println(物体A.name + " : 物理攻击 ------>  " + 物体B.name);
			物体B.nowHP = 物体B.nowHP <= 物体A.物理攻击伤害 ? 0 : 物体B.nowHP - 物体A.物理攻击伤害;
			调整法条(物体A);
			调整血条(物体B);

			if (物体B.nowHP == 0)
			{
				System.out.println("物体B的HP：" + 物体B.nowHP);
				移除物体(物体B);
			}
			被攻击玩家HP减少动画(物体B, 物体A.物理攻击伤害);

//			玩家的战斗选择 = 取消行动;
			格子椭圆阴影.setFill(椭圆颜色数组[玩家的战斗选择 + 1]);
			正在攻击 = false;
			蛇精物理群攻专用计数变量++;
			if (flag.get() && 蛇精物理群攻专用计数变量 == count)
			{
				try
				{
					蛇精物理群攻专用计数变量 = 0;
					num = 0;
					读取文件播放();
				} catch (IOException | InterruptedException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		pt.play();
	}

	public void 移除物体(Role role)
	{
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (role == 人类队列.get(i))
			{
				人类队列.remove(i);
				break;
			}
		}
		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			if (role == 妖怪队列.get(i))
			{
				妖怪队列.remove(i);
				break;
			}
		}

		ImageView 墓碑 = new ImageView("file:src/main/resources/images/RIP.png");
		墓碑.setX(role.realNode.x * Formation.M);
		墓碑.setY(role.realNode.y * Formation.M);
		墓碑.setFitWidth(Formation.M);
		墓碑.setFitHeight(Formation.M);

		pane.getChildren().removeAll(role.getImg(), role.getHP(), role.getMP());
		pane.getChildren().add(墓碑);
		墓碑队列.add(墓碑);
		是否游戏结束了();
	}

	public void 调整血条(Role role)
	{
		double 比例 = role.nowHP * 1.0 / role.maxHP * 1.0;
		role.HP.setWidth(比例 * Formation.HP宽度);

	}

	public void 调整法条(Role role)
	{
		double 比例 = role.nowMP * 1.0 / role.maxMP * 1.0;
		role.MP.setWidth(比例 * Formation.MP宽度);
		if (role.nowMP == 0)
		{
			pane.getChildren().remove(role.getMP());
		}
	}

	/* 只有成功该函数才能使用法术攻击。 */

	public void show() throws InterruptedException
	{
		全体就位();

	}

	public Role 查找对象(String name)
	{
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			if (人类队列.get(i).name.equals(name))
			{
				return 人类队列.get(i);
			}
		}

		for (int i = 0; i < 妖怪队列.size(); ++i)
		{
			if (妖怪队列.get(i).name.equals(name))
			{
				return 妖怪队列.get(i);
			}
		}
		return null;

	}

	public void Start(String fileString, String 分辨率) throws IOException
	{
		/* 解析屏幕分辨率 */
		/* 获取屏幕的宽 */
		int 屏幕的宽 = Integer.parseInt(分辨率.split(" ")[2]);

		/* 调节屏幕大小 */
		Formation.M = 屏幕的宽 / 12; // 宽的最大值是10个。。但是考虑到太大，我觉得除以14为秒
		Formation.重新设置单位();
		String file = "src/main/resources/战斗记录文件/" + fileString;
		reader = new BufferedReader(new FileReader(file));
		String temp;
		while ((temp = reader.readLine()) != null)
		{
			/* 对面对string进行拆封 */
			ArrayList<String> tempList = new ArrayList<>();
			String[] tempStr = temp.split(" ");
			for (int i = 0; i < tempStr.length; ++i)
			{
				tempList.add(tempStr[i]);
			}
			指令数组.add(tempList);
		}
		reader.close();
		/* 打印一下指令 */
		for (int i = 0; i < 指令数组.size(); ++i)
		{
			for (int j = 0; j < 指令数组.get(i).size(); ++j)
			{
				System.out.print(指令数组.get(i).get(j));
			}
			System.out.println();
		}
		mp3Player.setCycleCount(10000);/* 循环播放到地球毁灭为止 */

		mp3Player.play();
		try
		{
			添加背景图片();
			添加步数();
			添加阵型();
			添加人类();
			添加妖怪();
			添加格子椭圆阴影();
			添加移动速度标签();
			添加顶部所有按钮();
			添加按钮();
			添加右边信息栏();
			// cwl_debug_test
			System.out.println(背景图片.getFitHeight() + " : " + 背景图片.getFitWidth());

			Scene scene = new Scene(pane, Formation.scene宽度 - Formation.M * 2, Formation.scene高度);
			scene.getStylesheets().add("file:src/main/resources/CSS/Font.css");
			/* 下面的的做法是让鼠标点按就能拖动整个画面移动 */

			stage.setTitle("Test");
			stage.setScene(scene);
			stage.show();
			人类移动到起始点();
			展示阵型(4);
			打印妖怪们的真实坐标();
			打印葫芦娃们真实坐标();
			打印格子地图();

			/**
			 * 下面就是正式的回放动画过程了
			 */

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	int 摆阵执行次数 = 0;

	private void 读取文件播放() throws IOException, InterruptedException
	{
		flag.set(false);
		if (游戏开始)
		{
			num = 0;
			System.out.println("进入读取文件播放");
			for (int i = 0; i < 指令数组.size(); ++i) /* 有多少行指令就执行多少次 */
			{

				for (int j = 0; j < 指令数组.get(i).size(); ++j)
				{
					System.out.print(指令数组.get(i).get(j));
				}
				System.out.println();

				switch (指令数组.get(i).get(0))
				{
				case "法术攻击":

					Role 攻击者A = 查找对象(指令数组.get(i).get(1));
					Role 被攻击者B = 查找对象(指令数组.get(i).get(2));
					if (攻击者A.name.equals("grandpa"))
					{
						爷爷法术群攻物体B((GrandPa) 攻击者A, 被攻击者B);
					} else if (攻击者A.name.equals("Snake"))
					{
						更新伤害(); /* 只要是妖怪阵营先手就得先更新一下伤害值 */
						蛇精法术群攻物体B((Snake) 攻击者A, 被攻击者B);
					} else
					{
						玩家的战斗选择 = 法术攻击;
						A攻击B(攻击者A, 被攻击者B);
					}

					break;
				case "物理攻击":
					Role 攻击者A1 = 查找对象(指令数组.get(i).get(1));
					Role 被攻击者B1 = 查找对象(指令数组.get(i).get(2));
					if (攻击者A1.name.equals("grandpa"))
					{
						爷爷物理群群攻物体B((GrandPa) 攻击者A1, 被攻击者B1);
					} else if (攻击者A1.name.equals("Snake"))
					{
						更新伤害(); /* 只要是妖怪阵营先手就得先更新一下伤害值 */
						蛇精物理群群攻物体B((Snake) 攻击者A1, 被攻击者B1);
					} else
					{
						玩家的战斗选择 = 物理攻击;
						A攻击B(攻击者A1, 被攻击者B1);
					}
					break;
				case "妖怪全体进攻":
					更新伤害(); /* 只要是妖怪阵营先手就得先更新一下伤害值 */
					妖怪全体进攻(查找对象("Boss"));
					break;
				case "青蛙全体进攻":
					System.out.println("读取文件进入青蛙全进攻");
					num = 0;
					更新伤害(); /* 只要是妖怪阵营先手就得先更新一下伤害值 */
					全体青蛙进攻();
					break;
				case "摆阵":

					更新伤害(); /* 只要是妖怪阵营先手就得先更新一下伤害值 */
					展示阵型(Integer.parseInt(指令数组.get(i).get(1)));

					指令数组.remove(i);
					return;
				case "治疗全体":
					System.out.println("读取文件进入治疗全体");
					num = 0;
					小蝴蝶治疗全体成员((Butterfly) 查找对象("butterfly"));

					break;
				case "移动":
					Role 移动者 = 查找对象(指令数组.get(i).get(1));
					if (移动者.name.equals("Snake") || 移动者.name.equals("Boss") || 移动者.name.indexOf("Frog") != -1)
					{
						移动妖怪到目的点(移动者, Integer.parseInt(指令数组.get(i).get(2)), Integer.parseInt(指令数组.get(i).get(3)));
					} else
					{
						移动人类到目的点(移动者, Integer.parseInt(指令数组.get(i).get(2)), Integer.parseInt(指令数组.get(i).get(3)));
						移动者.每局可执行次数--;
					}
					break;
				case "攻击属性加成":
					Role role = 查找对象(指令数组.get(i).get(1));
					爷爷攻击属性加成所有成员((GrandPa) role);
					break;
				default:
					break;

				}

				指令数组.remove(i);
				return;
			}

		}
	}

	private void 更新伤害()
	{
		for (int i = 0; i < 人类队列.size(); ++i)
		{
			人类队列.get(i).物理攻击伤害 = 人类队列.get(i).原来物理伤害;
		}

	}

	public void 弹出人类胜利窗口()
	{

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("祝贺！");

		alert.setHeaderText("人类玩家胜利方：");
		ImageView 人物 = new ImageView("file:src/main/resources/images/grandpa.png");
		ImageView 胜利 = new ImageView("file:src/main/resources/images/胜利.png");
		alert.setGraphic(胜利);
		alert.setGraphic(人物);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add("file:src/main/resources/CSS/Font.css");
		alert.setOnHidden(evt -> {

			Stage temp = (Stage) pane.getScene().getWindow();
			temp.close(); // 这样就强制关闭窗口
		});
		alert.show();

	}

	public void 弹出妖怪胜利窗口()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("祝贺！");

		alert.setHeaderText("妖怪玩家胜利方：");
		ImageView 人物 = new ImageView("file:src/main/resources/images/snake.png");
		ImageView 胜利 = new ImageView("file:src/main/resources/images/胜利.png");
		alert.setGraphic(胜利);
		alert.setGraphic(人物);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add("file:src/main/resources/CSS/Font.css");
		alert.setOnHidden(evt -> {

			Stage temp = (Stage) pane.getScene().getWindow();
			temp.close(); // 这样就强制关闭窗口
		});
		alert.show();
	}

	public void 是否游戏结束了()
	{
		if (人类队列.size() == 0)
		{
			mp3Player.stop();
			弹出妖怪胜利窗口();
		} else if (妖怪队列.size() == 0)
		{
			mp3Player.stop();
			弹出人类胜利窗口();
		}
	}

	public void 播放声效(String string)
	{
		击打效果file = new File(string);
		击打效果media = new Media(击打效果file.toURI().toString());
		击打效果mediaPlayer = new MediaPlayer(击打效果media);
		击打效果mediaPlayer.setStopTime(new Duration(Formation.time));
		击打效果mediaPlayer.play();
	}

	/* 加载舞台，显示场景函数 */

}
