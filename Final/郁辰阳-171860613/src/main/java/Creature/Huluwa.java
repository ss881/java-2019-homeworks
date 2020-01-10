package Creature;

import javafx.animation.RotateTransition;
import javafx.util.Duration;

import javafx.scene.image.*;


public class Huluwa extends Creature{
	private int rank;
	private String color;
	
	public Huluwa(int rank) {
		this.justice=true;
		this.rank=rank;
		this.alive=true;
		switch(rank) {
		case 1: this.color="red"; this.name="老大"; break;
		case 2: this.color="orange"; this.name="老二"; break;
		case 3: this.color="yellow"; this.name="老三"; break;
		case 4: this.color="green"; this.name="老四"; break;
		case 5: this.color="cyan"; this.name="老五"; break;
		case 6: this.color="blue"; this.name="老六"; break;
		case 7: this.color="violet"; this.name="老七"; break;
		default: System.out.println("illegal rank"); break;
		}
	}
	
	public String getColor() {return color;}
	public int getRank() {return rank;}
	@Override
	public void setView() {
		this.view = new ImageView(new Image(String.format("%d.jpg", rank),50,50,false,true));
		this.dead = new ImageView(new Image("dead.jpg",50,50,false,false));
	}
}
