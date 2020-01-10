package com.nju.cwl;

/**
 * 
 * 版本：1.1
 * @作者 ：  我喜欢你家孩子呀~
 * 联系方式： 1019070879@qq.com
 * 修改日期: 2019年9月25日   
 * 时间: 下午11:26:46
 */

/** 阵型类 */
public class Formation
{
	/*
	 * 第一个节点是Boss 的站位，其他的节点是青蛙站位点！
	 * 
	 */
	public static int M = 90; // 设置坐标单位；
	public static int top = 1 * M;
	public static int left = 1 * M;
	public static int time = 3000; // 设置时间
	public static int right_distance = 2 * M;
	public static double HP宽度 = 0.9 * M;
	public static double HP高度 = 0.05 * Formation.M;
	public static double MP宽度 = 0.9 * M;
	public static double MP高度 = 0.05 * Formation.M;
	public static int scene宽度 = 10 * M + right_distance;
	public static int scene高度 = 10 * M;
	public static int MainScene宽度 = 7 * M;
	public static int MainScene高度 = 4 * M;

	public static void 重新设置单位()
	{
		top = 1 * M;
		left = 1 * M;
		time = 3000; // 设置时间
		right_distance = 2 * M;
		HP宽度 = 0.9 * M;
		HP高度 = 0.05 * Formation.M;
		MP宽度 = 0.9 * M;
		MP高度 = 0.05 * Formation.M;
		scene宽度 = 10 * M + right_distance;
		scene高度 = 10 * M;
		MainScene宽度 = 7 * M;
		MainScene高度 = 4 * M;
	}

	/* 长 12 宽 10 */
	/* 所以决定命运的是 宽 不是 高 */

	public int monsterNum;
	public Node[] formationPos;
}

class FengShi extends Formation
{
	public FengShi()
	{
		monsterNum = 15; // 15个小兵
		formationPos = new Node[] { new Node(3 * M, 4 * M + top), new Node(4 * M, 3 * M + top),
				new Node(4 * M, 4 * M + top), new Node(4 * M, 5 * M + top), new Node(5 * M, 2 * M + top),
				new Node(5 * M, 3 * M + top), new Node(5 * M, 4 * M + top), new Node(5 * M, 5 * M + top),
				new Node(5 * M, 6 * M + top), new Node(6 * M, 1 * M + top), new Node(6 * M, 4 * M + top),
				new Node(6 * M, 7 * M + top), new Node(7 * M, 0 * M + top), new Node(7 * M, 4 * M + top),
				new Node(7 * M, 8 * M + top), new Node(8 * M, 4 * M + top), new Node(9 * M, 4 * M + top) };

	}
}

/** 偃月阵 */
class YanYue extends Formation
{
	public YanYue()
	{
		monsterNum = 15; // 15个小兵
		formationPos = new Node[] { new Node(4 * M, 4 * M + top), new Node(4 * M, 3 * M + top),
				new Node(4 * M, 5 * M + top), new Node(5 * M, 2 * M + top), new Node(5 * M, 3 * M + top),
				new Node(5 * M, 4 * M + top), new Node(5 * M, 5 * M + top), new Node(5 * M, 6 * M + top),
				new Node(6 * M, 1 * M + top), new Node(6 * M, 2 * M + top), new Node(6 * M, 6 * M + top),
				new Node(6 * M, 7 * M + top), new Node(7 * M, 0 * M + top), new Node(7 * M, 1 * M + top),
				new Node(7 * M, 7 * M + top), new Node(7 * M, 8 * M + top), new Node(9 * M, 4 * M + top) };
	}
}

/** 鱼鳞阵 */
class YanXing extends Formation
{
	public YanXing()
	{
		monsterNum = 15; // 15个小兵
		formationPos = new Node[] { new Node(6 * M, 5 * M + top), new Node(2 * M, 6 * M + top),
				new Node(2 * M, 7 * M + top), new Node(3 * M, 5 * M + top), new Node(3 * M, 6 * M + top),
				new Node(4 * M, 4 * M + top), new Node(4 * M, 5 * M + top), new Node(5 * M, 3 * M + top),
				new Node(5 * M, 4 * M + top), new Node(6 * M, 2 * M + top), new Node(6 * M, 3 * M + top),
				new Node(7 * M, 1 * M + top), new Node(7 * M, 2 * M + top), new Node(8 * M, 0 * M + top),
				new Node(8 * M, 1 * M + top), new Node(9 * M, 0 * M + top), new Node(9 * M, 4 * M + top)

		};
	}
}

/** 鱼鳞阵 */
class YuLin extends Formation
{
	public YuLin()
	{
		monsterNum = 15; // 15个小兵
		formationPos = new Node[] { new Node(4 * M, 4 * M + top), new Node(4 * M, 3 * M + top),
				new Node(4 * M, 5 * M + top), new Node(5 * M, 2 * M + top), new Node(5 * M, 6 * M + top),
				new Node(6 * M, 1 * M + top), new Node(6 * M, 3 * M + top), new Node(6 * M, 4 * M + top),
				new Node(6 * M, 5 * M + top), new Node(6 * M, 7 * M + top), new Node(7 * M, 2 * M + top),
				new Node(7 * M, 3 * M + top), new Node(7 * M, 5 * M + top), new Node(7 * M, 6 * M + top),
				new Node(8 * M, 2 * M + top), new Node(8 * M, 6 * M + top), new Node(9 * M, 4 * M + top)

		};
	}
}

/** 方円阵 */
class FangYuan extends Formation
{
	public FangYuan()
	{
		monsterNum = 15; // 15个小兵
		formationPos = new Node[] { new Node(6 * M, 4 * M + top), new Node(2 * M, 4 * M + top),
				new Node(3 * M, 3 * M + top), new Node(3 * M, 5 * M + top), new Node(4 * M, 2 * M + top),
				new Node(4 * M, 6 * M + top), new Node(5 * M, 1 * M + top), new Node(5 * M, 7 * M + top),
				new Node(6 * M, 0 * M + top), new Node(6 * M, 8 * M + top), new Node(7 * M, 1 * M + top),
				new Node(7 * M, 7 * M + top), new Node(8 * M, 2 * M + top), new Node(8 * M, 6 * M + top),
				new Node(9 * M, 3 * M + top), new Node(9 * M, 5 * M + top), new Node(9 * M, 4 * M + top) };
	}
}

/** 衝軛阵 */
class ChongE extends Formation
{
	public ChongE()
	{
		monsterNum = 15; // 15个小兵
		formationPos = new Node[] { new Node(6 * M, 4 * M + top), new Node(2 * M, 3 * M + top),
				new Node(2 * M, 5 * M + top), new Node(2 * M, 7 * M + top), new Node(3 * M, 2 * M + top),
				new Node(3 * M, 4 * M + top), new Node(3 * M, 6 * M + top), new Node(4 * M, 1 * M + top),
				new Node(4 * M, 3 * M + top), new Node(4 * M, 5 * M + top), new Node(5 * M, 0 * M + top),
				new Node(5 * M, 2 * M + top), new Node(5 * M, 4 * M + top), new Node(8 * M, 4 * M + top),
				new Node(9 * M, 3 * M + top), new Node(9 * M, 5 * M + top), new Node(9 * M, 4 * M + top) };
	}
}

/** 三角阵 */
class SanJiao extends Formation
{
	public SanJiao()
	{
		monsterNum = 15; // 15个小兵
		formationPos = new Node[] { new Node(5 * M, 4 * M + top), new Node(2 * M, 4 * M + top),
				new Node(3 * M, 3 * M + top), new Node(3 * M, 4 * M + top), new Node(3 * M, 5 * M + top),
				new Node(4 * M, 2 * M + top), new Node(4 * M, 3 * M + top), new Node(4 * M, 4 * M + top),
				new Node(4 * M, 5 * M + top), new Node(4 * M, 6 * M + top), new Node(5 * M, 1 * M + top),
				new Node(5 * M, 2 * M + top), new Node(5 * M, 3 * M + top), new Node(5 * M, 5 * M + top),
				new Node(5 * M, 6 * M + top), new Node(5 * M, 7 * M + top), new Node(6 * M, 4 * M + top) };
	}
}
