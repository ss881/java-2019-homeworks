package com.calabashbro;

public abstract class Unit {
	String unitName;

	public Unit() {

	}

	public Unit(String unitName) {
		this.unitName = unitName;
	}

	@Override
	public String toString() {
		return "|"+ unitName + "|";
	}
	
}
