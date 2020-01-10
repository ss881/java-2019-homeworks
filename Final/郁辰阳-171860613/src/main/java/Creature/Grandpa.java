package Creature;

import Position.Position;
import javafx.animation.*;
import javafx.scene.image.*;
import javafx.util.Duration;

public class Grandpa extends Creature{
	public Grandpa(Position position){
		super("爷爷",true);
		this.position=position;
		this.alive=true;
		
		//this.view = new ImageView(new Image("lyy.jpg", 50, 50, false, true));
	    //this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
	}
	@Override
	public void setView() {
        this.view = new ImageView(new Image("lyy.jpg", 50, 50, false, true));
        this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
    }
	
}
