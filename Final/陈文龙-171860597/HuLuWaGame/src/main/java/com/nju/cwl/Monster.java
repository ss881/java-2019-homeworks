package com.nju.cwl;

//这个是 妖怪们的属性值

/** 这个是大佬！！ */


@Annotations(
HP值 = 10000, 
MP值 = 200, 
name = "Boss", 
每局可执行次数 = 2, 
法术伤害 = 1000, 
法术攻击距离 = 6, 
物理伤害 = 600, 
物理攻击距离 = 4,
移动距离 = 4)
class Boss extends Role
{
	@Override
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_info, String 动图URL, String 物理攻击URL, String 法术攻击URL)
	{
		// TODO 自动生成的方法存根
		this.srcNode.setX(9 * Formation.M);
		this.srcNode.setY(0 + Formation.top);
		super.setCharacters(name, maxHP, maxMP, nowHP, nowMP, everyMP, destNode, imgURL, info_url, people_info, 动图URL,
				物理攻击URL, 法术攻击URL);
	}

	public Boss()
	{
		setCharacters("Boss", 10000, 200, 10000, 200, 100, new Node(9 * Formation.M, 0), "file:src/main/resources/images/boss.png",
				"file:src/main/resources/images/boss_info.png", "file:src/main/resources/images/boss.png", "file:src/main/resources/images/Boss.gif", "file:src/main/resources/attack/大王物攻.gif",
				"file:src/main/resources/attack/bossW.gif");
		this.newImg(1 * Formation.M, 1 * Formation.M, 9 * Formation.M, 0 + Formation.top);
		this.每次可移动距离 = 4;
		this.物理攻击距离 = 4;
		this.法术攻击距离 = 6;
		this.物理攻击伤害 = 600;
		this.法术攻击伤害 = 1000;
		this.法术攻击消耗的MP = 20;
		this.每局可执行次数 = 2;
	}
}

/** 这个是小兵 */
@Annotations(
HP值 = 1000, 
MP值 = 100, 
name = "Frog", 
每局可执行次数 = 1, 
法术伤害 = 450, 
法术攻击距离 = 6, 
物理伤害 = 400, 
物理攻击距离 = 4,
移动距离 = 4)
class Frog extends Role
{
	@Override
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_url, String 动图URL, String 物理攻击URL, String 法术攻击URL)
	{

		this.物理攻击伤害 = 400;
		this.法术攻击伤害 = 450;
		this.法术攻击消耗的MP = 30;
		this.每次可移动距离 = 4;
		this.物理攻击距离 = 4;
		this.法术攻击距离 = 6;

		this.srcNode.setX(9 * Formation.M);
		this.srcNode.setY(0 + Formation.top);
		// TODO 自动生成的方法存根
		super.setCharacters(name, maxHP, maxMP, nowHP, nowMP, everyMP, destNode, imgURL, info_url, people_url, 动图URL,
				物理攻击URL, 法术攻击URL);

	}

	public Frog(int i)
	{
		setCharacters("Frog" + (i + 1), 1000, 100, 1000, 100, 10, new Node(9 * Formation.M, 0), "file:src/main/resources/images/frog.png",
				"file:src/main/resources/images/frog_info.png", "file:src/main/resources/images/frog.png", "file:src/main/resources/images/Frog.gif", "file:src/main/resources/attack/frog0.gif",
				"file:src/main/resources/attack/bossF1.gif");
		this.newImg(1 * Formation.M, 1 * Formation.M, 9 * Formation.M, 0 + Formation.top);
	}
}
@Annotations(
HP值 = 10000, 
MP值 = 100, 
name = "Snake", 
每局可执行次数 = 1, 
法术伤害 = 400, 
法术攻击距离 = 6, 
物理伤害 = 300, 
物理攻击距离 = 4,
移动距离 = 4)
class Snake extends Role
{

	@Override
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_url, String 动图URL, String 物理攻击URL, String 法术攻击URL)
	{
		this.srcNode.setX(9 * Formation.M);
		this.srcNode.setY(0 + Formation.top);
		// TODO 自动生成的方法存根
		super.setCharacters(name, maxHP, maxMP, nowHP, nowMP, everyMP, destNode, imgURL, info_url, people_url, 动图URL,
				物理攻击URL, 法术攻击URL);
	}

	public Snake()
	{
		setCharacters("Snake", 10000, 90, 10000, 90, 100, new Node(9 * Formation.M, 0), "file:src/main/resources/images/snake.png",
				"file:src/main/resources/images/snake_info.png", "file:src/main/resources/images/snake.png", "file:src/main/resources/images/Snake.gif", "file:src/main/resources/attack/蛇精法攻.gif",
				"file:src/main/resources/attack/蛇精法攻1.gif");
		this.newImg(1 * Formation.M, 1 * Formation.M, 9 * Formation.M, 0 + Formation.top);
		this.物理攻击伤害 = 400;
		this.法术攻击伤害 = 700;
		this.法术攻击消耗的MP = 30;
		this.每次可移动距离 = 4;
		this.物理攻击距离 = 4;
		this.法术攻击距离 = 6;
		this.每局可执行次数 = 2;
	}
}
