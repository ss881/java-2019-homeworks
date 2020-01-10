package Creature;

import javafx.scene.image.*;
import javafx.animation.*;
import javafx.util.Duration;

public class Louluo extends Creature{
	public Louluo() {
		super("喽啰",false);
		this.alive=true;
		
		//this.view = new ImageView(new Image("ll.jpg", 50, 50, false, true));
        //this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
	}
	@Override
	public void setView() {
		this.view = new ImageView(new Image("ll.jpg", 50, 50, false, true));
        this.dead = new ImageView(new Image("dead.jpg", 50, 50, false, false));
	}
}
