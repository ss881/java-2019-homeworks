package Creature;

import javafx.scene.image.*;
import Formation.Formation;

public class Xiezijing extends Creature{
	private Formation formation;
	
	public Xiezijing() {
		super("蝎子精",false);
		this.alive=true;
		
		//this.view = new ImageView(new Image("xzj.jpg", 50, 50, false, true));
		//this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
	}
	
	public void setFormation(String name) {
		this.formation = new Formation(name);
	}
	public Formation getFormation() { return this.formation; }
	@Override
	public void setView() {
		this.view = new ImageView(new Image("xzj.jpg", 50, 50, false, true));
		this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
	}
}
