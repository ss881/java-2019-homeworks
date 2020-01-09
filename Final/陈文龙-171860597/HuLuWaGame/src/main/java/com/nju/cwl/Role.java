package com.nju.cwl;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * 注释类 版本：1.0
 * 
 * @作者 ： 我喜欢你家孩子呀~ 联系方式： 1019070879@qq.com 修改日期: 2019年9月25日 时间: 下午11:26:46
 */

public class Role implements Runnable
{
	public static final int imgHeight = Formation.M;
	public static final int imgWidth = Formation.M; // 统一单位。
	public int 原来物理伤害;

	public Role()
	{
		// TODO 自动生成的构造函数存根
		maxHP = 0;
		maxMP = 0;
		nowHP = 0;
		nowMP = 0;
		everyMP = 0;
		speed = 0;
		name = "";
	}

	@Override
	public String toString()
	{
		return "Role [lock=" + lock + ", srcX=" + ", srcY=" + ", hasDone=" + hasDone + ", imgURL=" + imgURL
				+ ", srcNode=" + srcNode + ", destNode=" + destNode + ", speed=" + speed + ", name=" + name
				+ ", isAlive=" + isAlive + ", maxHP=" + maxHP + ", maxMP=" + maxMP + ", nowHP=" + nowHP + ", nowMP="
				+ nowMP + ", everyMP=" + everyMP + "]";
	}

	Lock lock = new ReentrantLock();

	Rectangle HP;
	Rectangle MP;
	int 物理攻击伤害;
	int 法术攻击伤害;
	int 法术攻击消耗的MP;
	int 每次可移动距离;
	int 物理攻击距离;
	int 法术攻击距离;

	public String info_url;
	public String people_url;

	/** 角色在画布上的位置 */
//	public AtomicInteger srcX = new AtomicInteger(0);
//	public AtomicInteger srcY = new AtomicInteger(0 + Formation.top);
	public AtomicBoolean hasDone = new AtomicBoolean(false);
	/** 人物UI地址 */
	public String imgURL;

	public String 动图URL;

	/** 任务UI */
	public ImageView imageView;
	/** 角色需要移动到的目的地 */

	/**
	 * 节点都有一个默认的出生地，所以一开始就有srcNode，根本不需要重新赋值！
	 */
	public Node srcNode = new Node(0, 0 + Formation.top); // 默认原地出生

	public Node destNode;

	public Node realNode = new Node(0, 0);

	public void setSrcNode(Node srcNode) /** 优先使用这个 */
	{
		this.srcNode.setX(srcNode.getX());
		this.srcNode.setY(srcNode.getY());

	}

	public void setDestNode(Node destNode)
	{
		this.destNode.setX(destNode.getX());
		this.destNode.setY(destNode.getY());
	}

	/** 角色的而移动速度 */
	public int speed = 5;
	/** 角色的姓名 */
	public String name;
	/** 角色是否还活着 */
	public boolean isAlive = true;

	/** 角色的生命体征 */
	public int maxHP;
	public int maxMP;
	public int nowHP;
	public int nowMP;
	public int everyMP; // 每次技能消耗的法术值

	ImageView 物理攻击;
	ImageView 法术攻击;
	String 物理攻击URL;
	String 法术攻击URL;

	public int 每局可执行次数 = 1;

	public void newImg(double fitHeight, double fitWidth, double layoutX, double layoutY)
	{
		imageView = new ImageView(imgURL);
		imageView.setFitHeight(fitHeight);
		imageView.setFitWidth(fitWidth);
		imageView.setLayoutX(layoutX);
		imageView.setLayoutY(layoutY);
	}

	/* 初始化HP矩形 */
	public void initHP()
	{
		this.HP = new Rectangle(this.srcNode.getX(), this.srcNode.getY() - Formation.MP高度 - Formation.HP高度,
				Formation.HP宽度, Formation.HP高度);
		this.HP.setStroke(Color.RED);
		this.HP.setFill(Color.RED);
	}

	/* 初始化MP矩形 */
	public void initMP()
	{
		this.MP = new Rectangle(this.srcNode.getX(), this.srcNode.getY() - Formation.MP高度, Formation.MP宽度,
				Formation.MP高度);
		this.MP.setStroke(Color.BLUE);
		this.MP.setFill(Color.BLUEVIOLET);
	}

	public ImageView getImg()
	{
		return imageView;
	}

	public Rectangle getHP()
	{
		return HP;
	}

	public Rectangle getMP()
	{
		return MP;
	}

	public Role(int srcX, int srcY, int destX, int destY)
	{
		/**
		 * 角色登场时候的坐标
		 */

	}

	/**
	 * 暂时不需要多线程，bug没调好
	 */

	/**
	 * 暂时不需要多线程，bug没调好
	 */

	/**
	 * 暂时不需要多线程，bug没调好
	 */

	public int getNowHP()
	{
		return nowHP;
	}

	public void setNowHP(int nowHP)
	{
		this.nowHP = nowHP;
	}

	public int getNowMP()
	{
		return nowMP;
	}

	public void 更新真实坐标()
	{
		realNode.setX(srcNode.getX() / Formation.M);
		realNode.setY(srcNode.getY() / Formation.M);
	}

	public void setNowMP(int nowMP)
	{
		this.nowMP = nowMP;
	}

	/** 初始化属性 */
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_url, String 动图URL, String 物理攻击URL, String 法术攻击URL)
	{
		/* 不需要设置srcNode是因为一开始就对srcNode已经初始化过一次了， */
		/* 因为一开是的角色们都在起始点，所有得经过一次归为操作之后srcNode才能获得他的暂时的值 */
		this.name = name;
		this.maxHP = maxHP;
		this.maxMP = maxMP;
		this.nowHP = nowHP;
		this.nowMP = nowMP;
		this.destNode = destNode;
		this.everyMP = everyMP;
		this.imgURL = imgURL;
		this.speed = 5;
		this.info_url = info_url;
		this.people_url = people_url;
		this.动图URL = 动图URL;
		this.物理攻击URL = 物理攻击URL;
		this.法术攻击URL = 法术攻击URL;
		initHP();
		initMP();
		更新真实坐标();
	}

	@Override
	public void run()
	{
		// TODO 自动生成的方法存根
		System.out.println("====================================");
		System.out.println("现场启动");
		System.out.println("====================================");
	}

	/* 移动人类节点 */
	public void 人类移动到目的点(Role role, int destX, int destY) throws InterruptedException
	{

		// cwl_debug_test
		System.out.println("进入到move方法： " + "destX ： destY = " + destX + ":" + destY);
		if (role.srcNode.getX() == destX * Formation.M && role.srcNode.getY() == destY * Formation.M)
		{
			return;
		}
		PathTransition pt = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * Formation.M, role.srcNode.getY() - 0.5 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.5 * Formation.M),
				role.getImg());
		pt.play();

		PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * Formation.M, role.srcNode.getY() - 0.05 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.05 * Formation.M),
				role.HP);
		ptHP.play();
		PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * Formation.M, role.srcNode.getY() - 0.025 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.025 * Formation.M),
				role.MP);
		ptMP.play();

		// cwl_debug_test
		System.out.println(role.name + "从 ： " + (role.srcNode.getX()) + "," + (role.srcNode.getY()));
		// cwl_debug_test
		System.out.println("--->" + (destX * Formation.M) + "," + (destY * Formation.M));

		role.srcNode.setX(destX * Formation.M);
		role.srcNode.setY(destY * Formation.M);
		/* 每次移动节点之后都更新真实坐标! */
		role.更新真实坐标();
		System.out.println(role.name + " :  真实坐标更新为 : " + role.realNode);

	}

	/* 移动妖怪节点 */
	public void 妖怪移动到目的点(Role role, int destX, int destY)
	{
		// cwl_debug_test
		System.out.println("进入到move方法： " + "destX ： destY = " + destX + ":" + destY);
		if (role.srcNode.getX() == destX * Formation.M && role.srcNode.getY() == destY * Formation.M)
		{
			return;
		}
		PathTransition pt = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() - 9 * Formation.M + 0.5 * Formation.M,
						role.srcNode.getY() - 0.5 * Formation.M,
						destX * Formation.M - 9 * Formation.M + 0.5 * Formation.M,
						destY * Formation.M - 0.5 * Formation.M),
				role.getImg());
		pt.play();

		PathTransition ptHP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * Formation.M, role.srcNode.getY() - 0.05 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.05 * Formation.M),
				role.HP);
		ptHP.play();
		PathTransition ptMP = new PathTransition(Duration.millis(Formation.time),
				new Line(role.srcNode.getX() + 0.5 * Formation.M, role.srcNode.getY() - 0.025 * Formation.M,
						destX * Formation.M + 0.5 * Formation.M, destY * Formation.M - 0.025 * Formation.M),
				role.MP);
		ptMP.play();
		// cwl_debug_test
		System.out.println(role.name + "从 ： " + (role.srcNode.getX()) + "," + (role.srcNode.getY()));
		// cwl_debug_test
		System.out.println("--->" + (destX * Formation.M) + "," + (destY * Formation.M));
		role.srcNode.setX(destX * Formation.M);
		role.srcNode.setY(destY * Formation.M);
		role.更新真实坐标();
		System.out.println(role.name + " :  真实坐标更新为 : " + role.realNode);
	}

	/**
	 * 暂时不需要多线程，bug没调好
	 */

	/**
	 * 暂时不需要多线程，bug没调好
	 */

}
