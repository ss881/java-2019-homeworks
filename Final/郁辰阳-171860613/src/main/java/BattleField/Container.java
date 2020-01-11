package BattleField;

import Creature.Creature;

//战场单个位置

public class Container {
	private Creature creature;
	
	Container(){};
	public void setCreature(Creature creature) {
		if(isEmpty())
			this.creature = creature;
	}
	public void setNull() {
		this.creature=null;
	}
	public Creature getCreature() {
		return this.creature;
	}
	public boolean isEmpty() {
		return this.creature==null;
	}
}
