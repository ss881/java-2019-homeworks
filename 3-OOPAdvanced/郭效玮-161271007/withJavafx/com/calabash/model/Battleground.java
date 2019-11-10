package com.calabash.model;

import java.util.Vector;

public class Battleground {
	public static final int battlegroundSize = 15;
	private Vector<Army> groundArmys = new Vector<>();

	public Battleground() {

	}

	public void addArmy(Army army) {
		groundArmys.add(army);
	}

	public void delArmy(int index) {
		groundArmys.remove(index);
	}

	public Vector<Army> getGroundArmys() {
		return groundArmys;
	}

	public Army getArmy(int index) {
		return groundArmys.elementAt(index);
	}

}
