package com.nju.cwl;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 注释类 版本：1.0
 * 
 * @作者 ： 我喜欢你家孩子呀~ 
 * 联系方式： 1019070879@qq.com 
 * 修改日期: 2019年9月25日 
 * 时间: 下午11:26:46
 */

public class Role implements Runnable
{
	public static final int imgHeight = Formation.M;
	public static final int imgWidth = Formation.M; // 统一单位。

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
		return "Role [lock=" + lock + ", srcX=" + srcX + ", srcY=" + srcY + ", hasDone=" + hasDone + ", imgURL="
				+ imgURL + ", srcNode=" + srcNode + ", destNode=" + destNode + ", speed=" + speed + ", name=" + name
				+ ", isAlive=" + isAlive + ", maxHP=" + maxHP + ", maxMP=" + maxMP + ", nowHP=" + nowHP + ", nowMP="
				+ nowMP + ", everyMP=" + everyMP + "]";
	}

	Lock lock = new ReentrantLock();
   
	Rectangle HP;
	Rectangle MP;
	
	public String info_url;
	public String people_url;

	/** 角色在画布上的位置 */
	public AtomicInteger srcX = new AtomicInteger(0);
	public AtomicInteger srcY = new AtomicInteger(0 + Formation.top);
	public AtomicBoolean hasDone = new AtomicBoolean(false);
	/** 人物UI地址 */
	public String imgURL;
	/** 任务UI */
	public ImageView imageView;
	/** 角色需要移动到的目的地 */

	public Node srcNode = new Node(0, 0 + Formation.top); // 默认原地出生
	public Node destNode;

	public void setSrcNode(Node srcNode) /** 优先使用这个 */
	{
		this.srcNode.setX(srcNode.getX());
		this.srcNode.setY(srcNode.getY());
		srcX.set(srcNode.getX());
		srcY.set(srcNode.getY());
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

	public void newImg(double fitHeight, double fitWidth, double layoutX, double layoutY)
	{
		imageView = new ImageView(imgURL);
		imageView.setFitHeight(fitHeight);
		imageView.setFitWidth(fitWidth);
		imageView.setLayoutX(layoutX);
		imageView.setLayoutY(layoutY);
	}
	
	public void initHP()
	{
		this.HP = new Rectangle(this.srcX.get(), this.srcY.get()-0.05*Formation.M, 0.9*Formation.M, 0.025*Formation.M);
		this.HP.setStroke(Color.RED);
		this.HP.setFill(Color.ORANGERED);
	}
	public void initMP()
	{
		this.MP = new Rectangle(this.srcX.get(), this.srcY.get()-0.025*Formation.M, 0.9*Formation.M, 0.025*Formation.M);
		this.MP.setStroke(Color.BLUE);
		this.MP.setFill(Color.BLUEVIOLET);
	}
	
	public ImageView getImg()
	{
		return imageView;
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

//	public void move(double sx, double sy, double dx, double dy)
//	{
//		lock.lock();
//
//		lock.unlock();
//	}
	
	/**
	 * 暂时不需要多线程，bug没调好
	 */
//
//	public void moveImage(double x, double y)
//	{
//		imageView.setLayoutX(x);
//		imageView.setLayoutY(y);
//
//	}
	
	/**
	 * 暂时不需要多线程，bug没调好
	 */
//	public void moveTo()
//	{
//		double dx, dy;
//		// double realSpeed = (double) speed/36;
//		dx = (destNode.x - srcX.get());
//		dy = (destNode.y - srcY.get());
//		if (destNode.x == srcX.get() && destNode.y == srcY.get())
//		{
//			return;
//		}
//		double distance = Math.sqrt(dx * dx + dy * dy); // 直线距离！
//		System.out.println("distance = " + distance);
//		System.out.println("dx : dy =  " + dx + " : " + dy);
//		System.out.println("speed = " + speed);
//		int timeConsume = (int) distance / speed;
//		dx /= timeConsume;
//		dy /= timeConsume;
//		double curx = srcX.get();
//		double cury = srcY.get();
//		for (int i = 0; i < timeConsume; ++i)
//		{
//			// 每一帧移动一次
//			curx += dx;
//			cury += dy;
//			srcX.set((int) curx);
//			srcY.set((int) cury);
//			imageView.setLayoutX(curx);
//			imageView.setLayoutY(cury);
//
//			try
//			{
//				Thread.sleep(1000 / 36);
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//				// TODO: handle exception
//			}
//
//		}
//		// cwl_debug_test
//		System.out.println(this.name + " :移动成功");
//		srcX.set((int) destNode.x);
//		srcY.set((int) destNode.y);
//		hasDone.set(true); // 已经执行了
//	}

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

	public void setNowMP(int nowMP)
	{
		this.nowMP = nowMP;
	}

	/** 初始化属性 */
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_url)
	{
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
	
	}

	@Override
	public void run()
	{
		// TODO 自动生成的方法存根
		
	}

	/**
	 * 暂时不需要多线程，bug没调好
	 */
//	public void moveTo1()
//	{
//		PathTransition pt = new PathTransition(Duration.millis(1000),
//				new Line(srcX.get(), srcY.get(), destNode.x, destNode.y), imageView);
//		pt.play();
//	}
//
//	public void MOVE()
//	{
//		System.out.println(Thread.currentThread().getName());
//		// TODO 自动生成的方法存根
//		while (true)
//		{
//			while (!hasDone.get())
//			{
//				moveTo();
//				break;
//			}
//			break;
//		}
//	}

	/**
	 * 暂时不需要多线程，bug没调好
	 */
	
	
//	@Override
//	public void run()
//	{
//		System.out.println(Thread.currentThread().getName());
//		// TODO 自动生成的方法存根
//		while (true)
//		{
//			while (!hasDone.get())
//			{
//				lock.lock();
//				moveTo();
//				lock.unlock();
//				break;
//			}
//			break;
//		}
//	}

}
