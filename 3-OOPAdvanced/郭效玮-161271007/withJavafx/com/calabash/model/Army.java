package com.calabash.model;

public class Army {
	private Unit captain = null;
	private Unit[] soiders = null;
	private Formation formation = null;
	private int xPos;
	private int yPos;

	public Army() {
		// TODO 自动生成的构造函数存根
	}

	public Army(int xPos, int yPos) {
		this.xPos=xPos;
		this.yPos=yPos;
	}

	public Army(Formation formation, int xPos, int yPos) {
		this.formation = formation;
		this.xPos=xPos;
		this.yPos=yPos;
	}

	public int getArmySize() {
		return formation.getFormationSize();
	}

	public Unit getCaptain() {
		return captain;
	}

	public void setCaptain(Unit captain) {
		this.captain = captain;
	}

	public Unit getSoider(int i) {
		if (i >= soiders.length)
			i = soiders.length - 1;
		return soiders[i];
	}

	public void setSoiders(Unit[] soiders) {
		this.soiders = soiders;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public boolean hasSoiders() {
		if (formation == null)
			return false;
		return true;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}
	
}
