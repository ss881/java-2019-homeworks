package BattleField;

import javafx.scene.image.*;
import Position.Position;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import Creature.Creature;
import Creature.Grandpa;
import Creature.Huluwa;
import Creature.Louluo;
import Creature.Snake;
import Creature.Xiezijing;
import Formation.Formation;

public class BattleField {
	public final static int Width = 16;
	public final static int Height = 11;
	public final Container[][] board;
	
	//战场上的生物
	private ArrayList<Huluwa> huluwa;
    private ArrayList<Louluo> louluo;
    private Grandpa grandpa;
    private Snake snake;
    private Xiezijing xiezijing;
	
	public BattleField() {
		board = new Container[Height][Width];
		for(int i=0; i<Height; i++) {
			for(int j=0; j<Width; j++) {
				board[i][j] = new Container();
			}
		}
		
		huluwa = new ArrayList<>();
		louluo = new ArrayList<>();
		
		for(int i=1;i<=7;i++) {
			huluwa.add(new Huluwa(i));
			//huluwa.get(i-1).setView();
		}
		
		grandpa = new Grandpa(new Position(5,1));
		snake = new Snake(new Position(5,14));
		xiezijing = new Xiezijing();
	}
	
	public BattleField(ArrayList<Huluwa> huluwa, ArrayList<Louluo> louluo, Grandpa grandpa, Snake snake, Xiezijing xiezijing) {
		board = new Container[Height][Width];
		for(int i=0; i<Height; i++) {
			for(int j=0; j<Width; j++) {
				board[i][j] = new Container();
			}
		}	
		this.huluwa = huluwa;
		this.louluo = louluo;
		this.grandpa = grandpa;
		this.snake = snake;
		this.xiezijing = xiezijing;
	}

	
	public void initBattleField(String formation) {
	        clearBattleField();
	   	                
	        //放置葫芦娃 一定是长蛇
	        Formation huluwaformation = new Formation("长蛇");
	        huluwaformation.leader = new Position(2, 1);
	        for(Huluwa temp : huluwa) {
	        	temp.live();
	        }
	        
	        for(int i = 0; i < 7; i ++) {
	            huluwa.get(i).setPosition(new Position(huluwaformation.leader.getX() + huluwaformation.pos.get(i).getX(),
	                    huluwaformation.leader.getY() + huluwaformation.pos.get(i).getY()));
	            board[huluwa.get(i).getPosition().getX()][huluwa.get(i).getPosition().getY()].setCreature(huluwa.get(i));
	        }
	        
	        //放置妖精
	        xiezijing.setFormation(formation);
	        Formation evilformation = xiezijing.getFormation(); //获取妖精的阵型
	        xiezijing.live();
	        evilformation.leader = new Position(2, 8);
	        louluo.clear();
	        
	        for (int i = 0; i < evilformation.pos.size() - 1; i++) {
	        	louluo.add(new Louluo());
	        	//louluo.get(i).setView();
	        }
	        
	        for(Louluo temp : louluo) {
	        	temp.live();
	        }

	        xiezijing.setPosition(new Position(evilformation.leader.getX() + evilformation.pos.get(0).getX(), evilformation.leader.getY() + evilformation.pos.get(0).getY()));
	        board[xiezijing.getPosition().getX()][xiezijing.getPosition().getY()].setCreature(xiezijing); 
	        
	        for (int i = 1; i < evilformation.pos.size(); i++)
	            louluo.get(i-1).setPosition(new Position(evilformation.leader.getX() + evilformation.pos.get(i).getX(),
	            		evilformation.leader.getY() + evilformation.pos.get(i).getY()));
	        for (Louluo s : louluo) {
	            board[s.getPosition().getX()][s.getPosition().getY()].setCreature(s);
	        }
	        
	        //放置爷爷和蛇精
	        board[grandpa.getPosition().getX()][grandpa.getPosition().getY()].setCreature(grandpa);
	        board[snake.getPosition().getX()][snake.getPosition().getY()].setCreature(snake);
	        //qualified = true;
	    }
	
	public void allSetView() {
		for(int i=0;i<7;i++) {
			huluwa.get(i).setView();
		}
		for(int i=0;i<xiezijing.getFormation().pos.size()-1;i++) {
			louluo.get(i).setView();
		}
		xiezijing.setView();
		snake.setView();
		grandpa.setView();
	}
	
	public Image getBG() {
		return new Image("bg.jpeg", 1000, 650, false, true);
	}
	
	void clearBattleField() {
        for(int i = 0; i < Height; i ++)
            for(int j = 0; j < Width; j ++)
                board[i][j].setNull();
    }
	
	public boolean posQualified(Position p) {
	     return (p.getX() >= 0 && p.getX() < Height && p.getY() >= 0 && p.getY() < Width);
	}
	
	public boolean emptyplace(Position p) {
        return posQualified(p) && board[p.getX()][p.getY()].getCreature() == null;
    }
	
	public Creature getLiving(Position p) {
		if(p == null) return null;
        Creature b = board[p.getX()][p.getY()].getCreature();
        
        if(b != null && b.isAlive()) return b;
        else return null;
	}
	
	public Position isBeside(Position p) {
		if(isOpposite(p, p.up())) return p.up();
        else if(isOpposite(p, p.down())) return p.down();
        else if(isOpposite(p, p.left())) return p.left();
        else if(isOpposite(p, p.right())) return p.right();
        else return null;
	}
	
	public boolean isOpposite(Position a, Position b) {
        if(posQualified(a) && posQualified(b)) {
            if(getLiving(a) != null && getLiving(b) != null) {
                return ((getLiving(a).getJustice() && !getLiving(b).getJustice() ) ||
                        (!getLiving(a).getJustice() && getLiving(b).getJustice()));
            }
        }
        return false;
    }
	
	private Position getRandomPos(Position p) {
        Vector<Position> v = new Vector<>();
        if(emptyplace(p.up())) v.add(p.up());
        if(emptyplace(p.down())) v.add(p.down());
        if(emptyplace(p.right())) v.add(p.right());
        if(emptyplace(p.left())) v.add(p.left());
        Random rand = new Random();
        if(v.size() <= 0) return p;
        return v.get(rand.nextInt(v.size()));
    }
	
	//找到从a到b的可能下一步
	public synchronized Position findNext(Position a, Position b) {
        Vector<Position> v = new Vector<>();
        if((emptyplace(a.right()) && b.getY()>a.getY())) v.add(a.right());
        else if((emptyplace(a.up()) && b.getX()<a.getX())) v.add(a.up());
        else if((emptyplace(a.left()) && b.getY()<a.getY())) v.add(a.left());
        else if((emptyplace(a.down()) && b.getX()>a.getX())) v.add(a.down());
        Random rand = new Random();
        if(v.size() <= 0 ) { return getRandomPos(a); }
        return v.get(rand.nextInt(v.size()));
    }

	//读取函数
	public Grandpa getGrandpa() {return grandpa;}
	public Snake getSnake() {return snake;}
	public ArrayList<Huluwa> getHuluwa(){return huluwa;}
	public ArrayList<Louluo> getLouluo(){return louluo;}
	public Xiezijing getXiezijing() {return xiezijing;}
	
}
