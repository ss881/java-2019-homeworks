package Creature;

import javafx.scene.image.*;
import javafx.animation.*;
import java.util.Timer;
import Position.Position;

public abstract class Creature {
	protected String name;
	protected Position position;
	protected boolean justice;
	
	ImageView view;
	ImageView dead;
	
	public Timer timer = new Timer();
	
	boolean alive;
	
	Creature(){ }
	Creature(String name){
		this.name=name;
	}
	Creature(String name, boolean justice){
		this.name=name;
		this.justice=justice;
	}
	
	public void setPosition(Position position) {
		this.position=position;
	}
	public ImageView getImage() {
		return alive? view: dead;
	}
	public boolean isAlive() {return alive;}
	public Position getPosition() {return position;}
	public boolean getJustice() {return justice;}
	public void death() {this.alive=false;}
	public void live() {this.alive=true;}
	public abstract void setView();
}
