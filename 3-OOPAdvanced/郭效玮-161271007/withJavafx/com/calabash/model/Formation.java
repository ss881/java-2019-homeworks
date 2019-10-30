package com.calabash.model;

public enum Formation {
	fmt1("∫◊“Ì",6,new int[]{1,2,3,-1,-2,-3},new int[]{-1,-2,-3,-1,-2,-3}),
	fmt2("—„––",6,new int[]{1,2,3,-1,-2,-3},new int[]{-1,-2,-3,1,2,3}),
	fmt3("∫·ÈÓ",6,new int[]{-1,0,-1,-1,0,-1},new int[]{-1,-2,-3,1,2,3}),
	fmt4("≥§…ﬂ",6,new int[]{0,0,0,0,0,0},new int[]{1,2,3,4,5,6}),
	fmt5("∑Ω√≈",7,new int[]{1,2,3,1,2,3,4},new int[]{-1,-2,-1,1,2,1,0}),
	fmt6("∑Ê ∏",7,new int[]{1,2,0,0,0,-1,-2},new int[]{1,2,1,2,3,1,2}),
	fmt7("”„¡€",10,new int[]{0,0,1,1,2,3,-1,-1,-2,-3},new int[]{-2,2,-1,1,0,1,-1,1,0,1}),
	fmt8("Ÿ»‘¬",20,new int[]{-2,-2,-2,-1,-1,-1,-1,-1,0,0,0,0,0,0,1,1,1,1,2,2},new int[]{-1,0,1,-2,-1,0,1,2,-3,-2,-1,1,2,3,-4,-3,3,4,-4,4});
	private int formationSize;
	private int xOffset[];
	private int yOffset[];
	private String formationName;
	private Formation(String formationName,int size,int[] xOffset,int[] yOffset) {
		this.formationName=formationName;
		this.formationSize=size;
		this.xOffset=xOffset;
		this.yOffset=yOffset;
	}
	
	public String getFormationName() {
		return formationName;
	}
	public int getFormationSize() {
		return formationSize;
	}
	public int getxOffset(int i) {
		return yOffset[i];
	}
	public int getyOffset(int i) {
		return xOffset[i];
	}
	public static Formation getFormation(int i) {
		switch (i) {
		case 1:
			return fmt1;
		case 2:
			return fmt2;
		case 3:
			return fmt3;
		case 4:
			return fmt4;
		case 5:
			return fmt5;
		case 6:
			return fmt6;
		case 7:
			return fmt7;
		case 8:
			return fmt8;
		}
		return fmt1;
	}
}
