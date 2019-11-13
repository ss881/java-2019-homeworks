package com.calabashbro;

public class Battleground {
	private int groundSize;
	private Unit[][] ground;

	public Battleground(int groundSize) {
		this.groundSize = groundSize;
		ground = new Unit[groundSize][groundSize];
		for (int i = 0; i < groundSize; i++)
			for (int j = 0; j < groundSize; j++)
				ground[i][j] = new GroundUnit();
	}

	public void addArmy(Army army, int xPos, int yPos) {
		setUnit(army.getCaptain(), xPos, yPos);
		if(!army.hasSoiders())
			return;
		for (int i = 0; i < army.getArmySize(); i++) {
			setUnit(army.getSoider(i), xPos+army.getFormation().getxOffset(i), yPos+army.getFormation().getyOffset(i));
		}
	}
	public void delArmy(Army army, int xPos, int yPos) {
		setUnit(new GroundUnit(), xPos, yPos);
		if(!army.hasSoiders())
			return;
		for (int i = 0; i < army.getArmySize(); i++) {
			setUnit(new GroundUnit(), xPos+army.getFormation().getxOffset(i), yPos+army.getFormation().getyOffset(i));
		}
	}
	@Override
	public String toString() {
		String str=new String("");
		for(int i=0;i<groundSize;i++)
			str+="！！！！！！";
		str+="\n";
		for(int i=0;i<groundSize;i++)
		{	
			for(int j=0;j<groundSize;j++)
				str+=getUnit(i, j);
			str+="\n";
			for(int k=0;k<groundSize;k++)
				str+="！！！！！！";
			str+="\n";
		}
		str+="\n\n\n\n\n\n\n\n\n\n\n\n";
		return str;
	}

	public Unit getUnit(int i,int j) {
		return ground[i][j];
	}

	public void setUnit(Unit unit,int i,int j) {
		ground[i][j]=unit;
	}
	
}
