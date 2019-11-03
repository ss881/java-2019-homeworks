package com.nju.cwl;



//这个是 妖怪们的属性值

/** 这个是大佬！！ */
class Boss extends Role
{
	@Override
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_info)
	{
		// TODO 自动生成的方法存根
		this.srcNode.setX(9 * Formation.M);
		this.srcNode.setY(0 + Formation.top);
		super.setCharacters(name, maxHP, maxMP, nowHP, nowMP, everyMP, destNode, imgURL, info_url, people_info);
	}

	public Boss()
	{
		setCharacters("Boss", 5000, 1000, 5000, 100, 100, new Node(9 * Formation.M, 0), "file:images/Boss.gif",
				"file:images/boss_info.png", "file:images/boss.png");
		this.newImg(1 * Formation.M, 1 * Formation.M, 9 * Formation.M, 0 + Formation.top);
		this.srcX.set(srcNode.getX());
		this.srcY.set(srcNode.getY());
		initHP();
		initMP();
	}
}

/** 这个是小兵 */
class Frog extends Role
{
	@Override
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_url)
	{
		this.srcNode.setX(9 * Formation.M);
		this.srcNode.setY(0 + Formation.top);
		// TODO 自动生成的方法存根
		super.setCharacters(name, maxHP, maxMP, nowHP, nowMP, everyMP, destNode, imgURL, info_url, people_url);

	}

	public Frog(int i)
	{
		setCharacters("Frog" + (i + 1), 500, 100, 500, 100, 10, new Node(9 * Formation.M, 0), "file:images/Frog.gif",
				"file:images/frog_info.png", "file:images/frog.png");
		this.newImg(1 * Formation.M, 1 * Formation.M, 9 * Formation.M, 0 + Formation.top);
		this.srcX.set(srcNode.getX());
		this.srcY.set(srcNode.getY());
		initHP();
		initMP();
	}
}

class Snake extends Role
{

	@Override
	protected void setCharacters(String name, int maxHP, int maxMP, int nowHP, int nowMP, int everyMP, Node destNode,
			String imgURL, String info_url, String people_url)
	{
		this.srcNode.setX(9 * Formation.M);
		this.srcNode.setY(0 + Formation.top);
		// TODO 自动生成的方法存根
		super.setCharacters(name, maxHP, maxMP, nowHP, nowMP, everyMP, destNode, imgURL, info_url, people_url);
	}

	public Snake()
	{
		setCharacters("Snake", 1000, 1000, 1000, 1000, 100, new Node(9 * Formation.M, 0), "file:images/Snake.gif",
				"file:images/snake_info.png", "file:images/snake.png");
		this.newImg(1 * Formation.M, 1 * Formation.M, 9 * Formation.M, 0 + Formation.top);
		this.srcX.set(srcNode.getX());
		this.srcY.set(srcNode.getY());
		initHP();
		initMP();
	}
}
