package com.nju.cwl;


/**
 * 
 *  游戏名： 葫芦娃大战妖精 
 *  版本：1.1
 * @作者 ：  我喜欢你家孩子呀~
 * 联系方式： 1019070879@qq.com
 * 修改日期: 2019年9月27日   
 * 时间: 下午3:35:22
 */
/** 坐标节点类 （x,y) */
class Node
{
	int x;
	int y;

	@Override
	public String toString()
	{
		return "Node [x=" + x + ", y=" + y + "]";
	}

	public Node(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

}

/** 葫芦娃兄弟们属性的枚举类型 */
enum HuLuWaCharacter
{
	NO_1, No_2, No_3, NO_4, NO_5, NO_6, NO_7;
	static protected String[] names = { "老大", "老二", "老三", "老四", "老五", "老六", "老七" };
	static protected String[] colors = { "红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色" };
	static protected String[] imgURL = { "file:images/Brother1_1.gif", "file:images/Brother2_1.gif",
			"file:images/Brother3_1.gif", "file:images/Brother4_1.gif", "file:images/Brother5_1.gif",
			"file:images/Brother6_1.gif", "file:images/Brother7_1.gif" };
	static protected String[] infoURLs = { "file:images/brother1_info.png", "file:images/brother2_info.png",
			"file:images/brother3_info.png", "file:images/brother4_info.png", "file:images/brother5_info.png",
			"file:images/brother6_info.png", "file:images/brother7_info.png" };

	static protected String[] peopleURLs = { "file:images/brother1.png", "file:images/brother2.png",
			"file:images/brother3.png", "file:images/brother4.png", "file:images/brother5.png",
			"file:images/brother6.png", "file:images/brother7.png" };

	static protected Node[] destNode = { new Node(1 * Formation.M, 1 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 2 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 3 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 4 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 5 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 6 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 7 * Formation.M + Formation.top) };
	static protected boolean[] isremote = { false, true, true, false, false, false, false }; // 规定葫芦娃的远程攻击属性
	static protected int[] maxHP = { 1000, 1000, 1000, 1000, 1000, 1000, 1000 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] maxMP = { 100, 100, 100, 100, 100, 100, 100 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] nowHP = { 1000, 1000, 1000, 1000, 1000, 1000, 1000 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] nowMP = { 100, 100, 100, 100, 100, 100, 100 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] regularcost = { 10, 10, 5, 20, 20, 30, 10 };
	static protected int[] everyMPcost = { 20, 40, 20, 40, 30, 20, 20 };
	static protected int[] ZXCcost = { 90, 70, 80, 90, 90, 60, 50 };

	public String getPeopleUrl(int index)
	{
		return peopleURLs[index];
	}

	public String getInfoURL(int index)
	{
		return infoURLs[index];
	}

	public String getImgURL(int index)
	{
		return imgURL[index];
	}

	public String getName(int index)
	{
		return names[index];
	}

	public String getColor(int index)
	{
		return colors[index];
	}

	public boolean IsRemote(int index)
	{
		return isremote[index];
	}

	public int getMaxHp(int index)
	{
		return maxHP[index];
	}

	public int getMaxMp(int index)
	{
		return maxMP[index];
	}

	public int getHP(int index)
	{
		return nowHP[index];
	}

	public int getMP(int index)
	{
		return nowMP[index];
	}

	public int getregCost(int index)
	{
		return regularcost[index];
	}

	public int getEveryMpCost(int index)
	{
		return everyMPcost[index];
	}

	public int getZXCCost(int index)
	{
		return ZXCcost[index];
	}

	public Node getDestNode(int index)
	{
		return destNode[index];
	}
}

class HuLuWa extends Role
{
	private static int id;// 每个葫芦娃都有唯一一个id
	private HuLuWaCharacter character;

	/**
	 * 葫芦娃们的属性
	 * 
	 * @throws java.lang.Exception
	 */
	public HuLuWa(int id)
	{

		this.character = HuLuWaCharacter.values()[HuLuWa.id];
		setCharacters(character.getName(id), character.getMaxHp(id), character.getMaxMp(id), character.getHP(id),
				character.getMP(id), character.getEveryMpCost(id), character.getDestNode(id), character.getImgURL(id),
				character.getInfoURL(id), character.getPeopleUrl(id));

		/** fitH fitW layX layY */
		this.newImg(1 * Formation.M, 1 * Formation.M, 0, 0 + Formation.top);
		/** Debug */
		System.out.println(id + " " + " " + character.getName(id) + " " + character.getImgURL(id) + " "
				+ character.getDestNode(id));
		System.out.println(this.imageView.getFitHeight() + " : " + this.imageView.getFitWidth());
		this.srcNode.setX(srcX.get());
		this.srcNode.setY(srcY.get());
		initHP();
		initMP();

	}
}

class GrandPa extends Role
{

	public GrandPa()
	{
		this.people_url = "file:images/grandpa.png";
		this.info_url = "file:images/grandpa_info.png";
		this.imgURL= "file:images/Grandpa.gif";
		setCharacters("爷爷", 1000, 100, 1000, 100, 10, new Node(0, 3 * Formation.M + Formation.top),
				imgURL, info_url, people_url);
		this.newImg(1 * Formation.M, 1 * Formation.M, 0, 0 + Formation.top);
		/** 爷爷 */
		System.out.println("爷爷：" + this.imageView.getFitHeight() + " : " + this.imageView.getFitWidth());
		this.srcNode.setX(srcX.get());
		this.srcNode.setY(srcY.get());
		initHP();
		initMP();

	}
}

class Butterfly extends Role
{
	public Butterfly()
	{
		this.people_url = "file:images/butterfly.png";
		this.info_url = "file:images/butterfly.png";
		this.imgURL= "file:images/Butterfly.gif";
		setCharacters("小蝴蝶", 1000, 100, 1000, 100, 10, new Node(0, 4 * Formation.M + Formation.top),
				imgURL, info_url, people_url);
		this.newImg(1 * Formation.M, 1 * Formation.M, 0, 0 + Formation.top);
		System.out.println("小蝴蝶：" + this.imageView.getFitHeight() + " : " + this.imageView.getFitWidth());
		this.srcNode.setX(srcX.get());
		this.srcNode.setY(srcY.get());
		initHP();
		initMP();

	}
}
