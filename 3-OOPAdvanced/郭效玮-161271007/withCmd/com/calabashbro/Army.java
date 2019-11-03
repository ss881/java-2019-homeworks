package com.calabashbro;

public class Army {
	private Unit captain;
	private Unit[] soiders;
	private Formation formation;

	public Army() {
		// TODO 自动生成的构造函数存根
	}

	public Army(Unit captain) {
		this.captain = captain;
		formation=null;
	}

	public Army(Unit captain, Formation formation) {
		this.formation=formation;
		this.captain = captain;
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
		if(i>=soiders.length)
			i=soiders.length-1;
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
		if(formation==null)
			return false;
		return true;
	}
}
