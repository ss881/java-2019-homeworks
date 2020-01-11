package map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import creature.*;
public class Position extends JFrame{
    private int x;
    private int y;
    private Creature creature;
    private JLabel jf;
    
    public Position(int x, int y){
		this.x = x;
		this.y =y;
		this.creature = null; 
		this.jf=null;
    }

    public void setCreature(Map map,Creature creature){
		if (this.creature != null) {
			this.creature = null;
		}
		this.creature = creature;
		this.jf=map.lab[x][y];
		ImageIcon image = new ImageIcon(this.creature.geturl());
		image.setImage(image.getImage().getScaledInstance(this.jf.getWidth(), this.jf.getHeight(),Image.SCALE_DEFAULT ));
		this.jf.setIcon(image);
		this.jf.setHorizontalAlignment(0);
    }

    public void removeCrearure(Map map){
    	this.jf=map.lab[x][y];
    	this.jf.setIcon(null);
    	this.creature = null; 
    }

}
