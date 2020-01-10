package Creature;

import javafx.scene.image.*;
import javafx.animation.*;
import javafx.util.Duration;
import Position.Position;

public class Snake extends Creature{
	public Snake(Position position){
		super("蛇精",false);
		this.position=position;
		this.alive=true;
		
		//this.view = new ImageView(new Image("sj.jpg", 50, 50, false, true));
        //this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
	}
	@Override
	public void setView() {
        this.view = new ImageView(new Image("sj.jpg", 50, 50, false, true));
        this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
    }
}
