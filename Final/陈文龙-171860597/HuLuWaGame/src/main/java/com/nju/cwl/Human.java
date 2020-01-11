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

	String 物理攻击URL = "file:attack/brother1.gif";
	static protected String[] names = { "老大", "老二", "老三", "老四", "老五", "老六", "老七" };
	static protected String[] colors = { "红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色" };
	static protected String[] 动图URL = { "file:src/main/resources/images/Brother1_1.gif", "file:src/main/resources/images/Brother2_1.gif",
			"file:src/main/resources/images/Brother3_1.gif", "file:src/main/resources/images/Brother4_1.gif", "file:src/main/resources/images/Brother5_1.gif",
			"file:src/main/resources/images/Brother6_1.gif", "file:src/main/resources/images/Brother7_1.gif" };
	static protected String[] imgURL = { "file:src/main/resources/images/brother1.png", "file:src/main/resources/images/brother2.png",
			"file:src/main/resources/images/brother3.png", "file:src/main/resources/images/brother4.png", "file:src/main/resources/images/brother5.png",
			"file:src/main/resources/images/brother6.png", "file:src/main/resources/images/brother7.png" };
	static protected String[] infoURLs = { "file:src/main/resources/images/brother1_info.png", "file:src/main/resources/images/brother2_info.png",
			"file:src/main/resources/images/brother3_info.png", "file:src/main/resources/images/brother4_info.png", "file:src/main/resources/images/brother5_info.png",
			"file:src/main/resources/images/brother6_info.png", "file:src/main/resources/images/brother7_info.png" };
	static protected String[] 法术攻击URL = { "file:src/main/resources/attack/brotherAll.gif", "file:src/main/resources/attack/老二法攻.gif",
			"file:src/main/resources/attack/brother3.gif", "file:src/main/resources/attack/brother4.gif", "file:src/main/resources/attack/brother5.gif",
			"file:src/main/resources/attack/brother6.gif", "file:src/main/resources/attack/brother7.gif", };

	static protected int[] 物理攻击距离 = { 2, 3, 3, 3, 3, 3, 4 };
	static protected int[] 法术攻击距离 = { 3, 4, 4, 4, 4, 4, 6 };
	static protected int[] 每次可移动距离 = { 4, 4, 4, 4, 4, 4, 4 };

	static protected int[] 物理攻击伤害数组 = { 500, 300, 300, 300, 300, 300, 200 };
	static protected int[] 法术攻击伤害数组 = { 1000, 400, 400, 400, 400, 400, 800 };
	static protected int[] 法术消耗MP = { 30, 25, 25, 25, 25, 25, 10 };
	static protected String[] peopleURLs = { "file:src/main/resources/images/brother1.png", "file:src/main/resources/images/brother2.png",
			"file:src/main/resources/images/brother3.png", "file:src/main/resources/images/brother4.png", "file:src/main/resources/images/brother5.png",
			"file:src/main/resources/images/brother6.png", "file:src/main/resources/images/brother7.png" };

	static protected Node[] destNode = { new Node(1 * Formation.M, 1 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 2 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 3 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 4 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 5 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 6 * Formation.M + Formation.top),
			new Node(1 * Formation.M, 7 * Formation.M + Formation.top) };
	static protected boolean[] isremote = { false, true, true, false, false, false, false }; // 规定葫芦娃的远程攻击属性
	static protected int[] maxHP = { 5000, 4000, 4000, 4000, 4000, 4000, 2000 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] maxMP = { 90, 100, 100, 100, 100, 100, 100 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] nowHP = { 5000, 4000, 4000, 4000, 4000, 4000, 2000 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] nowMP = { 90, 100, 100, 100, 100, 100, 100 }; // 每种葫芦娃的属性值保存在这四个数组中
	static protected int[] regularcost = { 10, 10, 5, 20, 20, 30, 10 };
	static protected int[] everyMPcost = { 20, 40, 20, 40, 30, 20, 20 };
	static protected int[] ZXCcost = { 90, 70, 80, 90, 90, 60, 50 };

	public int get每次可移动距离(int index)
	{
		return 每次可移动距离[index];
	}

	public int get物理攻击距离(int index)
	{
		return 物理攻击距离[index];
	}

	public int get法术攻击距离(int index)
	{
		return 法术攻击距离[index];
	}

	public int get物理伤害(int index)
	{
		return 物理攻击伤害数组[index];
	}

	public int get法术伤害(int index)
	{
		return 法术攻击伤害数组[index];
	}

	public int get法术消耗MP(int index)
	{
		return 法术消耗MP[index];
	}

	public String get法术攻击URL(int index)
	{
		return 法术攻击URL[index];
	}

	public String get动图URL(int index)
	{
		return 动图URL[index];
	}

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

class 葫芦小金刚 extends Role
{
	public 葫芦小金刚()
	{
		this.物理攻击距离 = 100;
		this.法术攻击距离 = 100;
		this.每次可移动距离 = 100;
		this.物理攻击伤害 = 500;
		this.法术攻击伤害 = 500;
		this.法术攻击消耗的MP = 10;
		this.people_url = "file:src/main/resources/images/MAX.gif";
		this.info_url = "file:src/main/resources/images/MAX.gif";
		this.imgURL = "file:src/main/resources/images/MAX.gif";
		this.动图URL = "file:src/main/resources/images/MAX.gif";
		this.物理攻击URL = "file:src/main/resources/attack/MAXATTACK.gif";
		this.法术攻击URL = "file:src/main/resources/attack/MAXATTACK.gif";
		setCharacters("小金刚", 5000, 1000, 5000, 1000, 10, new Node(0, 3 * Formation.M + Formation.top), imgURL, info_url,
				people_url, 动图URL, 物理攻击URL, 法术攻击URL);
		this.newImg(1 * Formation.M, 1 * Formation.M, 0, 0 + Formation.top);
		/** 爷爷 */
		System.out.println("小金刚：" + this.imageView.getFitHeight() + " : " + this.imageView.getFitWidth());
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
				character.getInfoURL(id), character.getPeopleUrl(id), character.get动图URL(id), character.物理攻击URL,
				character.get法术攻击URL(id));

		this.物理攻击距离 = character.get物理攻击距离(id);
		this.法术攻击距离 = character.get法术攻击距离(id);
		this.每次可移动距离 = character.get每次可移动距离(id);
		this.每局可执行次数 = 2;
		this.物理攻击伤害 = character.get物理伤害(id);
		this.法术攻击伤害 = character.get法术伤害(id);
		this.法术攻击消耗的MP = character.get法术消耗MP(id);
		/** fitH fitW layX layY */
		this.newImg(1 * Formation.M, 1 * Formation.M, 0, 0 + Formation.top);/* 前两个参数是图片的长宽， 后边两个是图片安放的坐标 */
		/** Debug */
		System.out.println(id + " " + " " + character.getName(id) + " " + character.getImgURL(id) + " "
				+ character.getDestNode(id));
		System.out.println(this.imageView.getFitHeight() + " : " + this.imageView.getFitWidth());
		this.原来物理伤害 = this.物理攻击伤害;
	}
}


@Annotations
(HP值 = 3000, 
MP值 = 100, 
name = "grandpa", 
每局可执行次数 = 1, 
法术伤害 = 300, 
法术攻击距离 = 7, 
物理伤害 = 200, 
物理攻击距离 = 4, 
移动距离 = 5)
class GrandPa extends Role
{
	public boolean 爷爷法术群攻 = false;
	public boolean 爷爷物理群攻 = false;
	public boolean 爷爷攻击属性加成 = false;

	public int 爷爷发动技能增加的攻击值 = 400;
	public int 爷爷攻击属性加成技能消耗MP = 20;
	public int 爷爷法术群攻消耗技能 = 20;

	public GrandPa()
	{
		this.物理攻击距离 = 4;
		this.法术攻击距离 = 7;
		this.每次可移动距离 = 5;
		this.物理攻击伤害 = 200;
		this.法术攻击伤害 = 300;
		this.法术攻击消耗的MP = 10;
		this.people_url = "file:src/main/resources/images/grandpa.png";
		this.info_url = "file:src/main/resources/images/grandpa_info.png";
		this.imgURL = "file:src/main/resources/images/grandpa.png";
		this.动图URL = "file:src/main/resources/images/Grandpa.gif";
		this.法术攻击URL = "file:src/main/resources/attack/grandpa.gif";
		this.物理攻击URL = "file:src/main/resources/attack/1.gif";
		setCharacters("grandpa", 3000, 100, 3000, 100, 10, new Node(0, 3 * Formation.M + Formation.top), imgURL,
				info_url, people_url, 动图URL, 物理攻击URL, 法术攻击URL);
		this.newImg(1 * Formation.M, 1 * Formation.M, 0, 0 + Formation.top);
		this.原来物理伤害 = this.物理攻击伤害;
		/** 爷爷 */
		System.out.println("爷爷：" + this.imageView.getFitHeight() + " : " + this.imageView.getFitWidth());
	}
}

@Annotations(
HP值 = 3000, 
MP值 = 100, 
name = "butterfly", 
每局可执行次数 = 1, 
法术伤害 = 400, 
法术攻击距离 = 8, 
物理伤害 = 300, 
物理攻击距离 = 5,
移动距离 = 5)
class Butterfly extends Role
{

	public Butterfly()
	{
		this.物理攻击距离 = 5;
		this.法术攻击距离 = 8;
		this.每次可移动距离 = 5;
		this.物理攻击伤害 = 300;
		this.法术攻击伤害 = 400;
		this.法术攻击消耗的MP = 20;
		this.people_url = "file:src/main/resources/images/butterfly.png";
		this.info_url = "file:src/main/resources/images/butterfly.png";
		this.imgURL = "file:src/main/resources/images/butterfly.png";
		this.动图URL = "file:src/main/resources/images/Butterfly.gif";
		this.法术攻击URL = "file:src/main/resources/attack/butterfly.gif";
		this.物理攻击URL = "file:src/main/resources/attack/2.gif";
		setCharacters("butterfly", 3000, 100, 3000, 100, 10, new Node(0, 4 * Formation.M + Formation.top), imgURL,
				info_url, people_url, 动图URL, 物理攻击URL, 法术攻击URL);
		this.newImg(1 * Formation.M, 1 * Formation.M, 0, 0 + Formation.top);
		this.原来物理伤害 = this.物理攻击伤害;
		System.out.println("小蝴蝶：" + this.imageView.getFitHeight() + " : " + this.imageView.getFitWidth());
	}
}
