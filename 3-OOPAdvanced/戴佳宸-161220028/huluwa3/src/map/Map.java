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

public class Map extends JFrame {
    private Position[][] pos;
    private dawa one;
    private erwa two;
    private sanwa three;
    private siwa four;
    private wuwa five;
    private liuwa six;
    private qiwa seven;
    private xiaobing[] xbings;
    private xiezijing boss;
    private shejing sjing;
    private yeye grandpa;
    public JLabel[][] lab;
    public Map(){
    	lab=new JLabel[11][11];
		pos = new Position[11][11];
		Container container=getContentPane();
		setLayout(new GridLayout(11,11));
		one = new dawa();
		two = new erwa();
		three=new sanwa();
		four = new siwa();
		five = new wuwa();
		six = new liuwa();
		seven = new qiwa();
		xbings = new xiaobing[10];
		for (int i = 0; i < xbings.length; i++) {
			xbings[i] = new xiaobing();
		}
		boss = new xiezijing();
		sjing = new shejing();
		grandpa = new yeye();
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				pos[i][j] = new Position(i, j);
				lab[i][j]=new JLabel();
				
				if(i==0&&j==7)
				{JButton bianzhen=new JButton("鹤翼阵");
				bianzhen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						henge_end();
						yanxing_end();
						fangzhen_end();
						fengshi_end();
						heyi();
					}
				});
				container.add(bianzhen);
				}
				else if(i==0&&j==8)
				{JButton bianzhen=new JButton("雁行阵");
				bianzhen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						henge_end();
						heyi_end();
						fangzhen_end();
						fengshi_end();
						yanxing();
					}
				});
				container.add(bianzhen);
				}
				else if(i==0&&j==9)
				{JButton bianzhen=new JButton("衡轭阵");
				bianzhen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						heyi_end();
						yanxing_end();
						fangzhen_end();
						fengshi_end();
						henge();
					}
				});
				container.add(bianzhen);
				}
				else if(i==0&&j==10)
				{JButton bianzhen=new JButton("方円阵");
				bianzhen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						henge_end();
						yanxing_end();
						heyi_end();
						fengshi_end();
						fangzhen();
					}
				});
				container.add(bianzhen);
				}
				else if(i==1&&j==7)
				{JButton bianzhen=new JButton("锋矢阵");
				bianzhen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						henge_end();
						yanxing_end();
						fangzhen_end();
						heyi_end();
						fengshi();
					}
				});
				container.add(bianzhen);
				}
				else
				{JLabel jb=new JLabel();
			jb.setBorder(BorderFactory.createLineBorder(Color.red));
			jb.setOpaque(true); 
			jb.setSize(100, 100);
			container.add(jb);
			lab[i][j]=jb;}
			}
		}
		setSize(1100, 1100);
		setTitle("葫芦娃大战蝎子精");
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Position[][] getPos(){
        return pos;
    }

    public dawa getdawa(){
        return one;
    }

    public yeye getGrandpa(){
        return grandpa;
    }

    public erwa geterwa(){
        return two;
    }

    public sanwa getsanwa(){
        return three;
    }

    public siwa getsiwa(){
        return four;
    }

    public wuwa getwuwa(){
        return five;
    }

    public liuwa getliuwa(){
        return six;
    }

    public qiwa getqiwa(){
        return seven;
    }

    public shejing getshejing(){
        return sjing;
    }

    public xiaobing[] getxiaobing(){
        return xbings;
    }

    public xiezijing getxiezijing(){
        return boss;
    }

    public void init(){
    	this.getGrandpa().setwa(this, 10, 3);
    	this.getshejing().setwa(this, 10, 7);
    	this.getdawa().setwa(this, 2, 2);
    	this.geterwa().setwa(this, 3, 2);
    	this.getsanwa().setwa(this, 4, 2);
    	this.getsiwa().setwa(this, 5, 2);
    	this.getwuwa().setwa(this, 6, 2);
    	this.getliuwa().setwa(this, 7, 2);
    	this.getqiwa().setwa(this, 8, 2);
    	this.getxiezijing().setwa(this, 5, 5);
    }

    public void heyi(){
    	this.getxiaobing()[0].setwa(this, 3, 6);
    	this.getxiaobing()[1].setwa(this, 4, 6);
    	this.getxiaobing()[2].setwa(this, 5, 7);
    	this.getxiaobing()[3].setwa(this, 6, 8);
    	this.getxiaobing()[4].setwa(this, 5, 9);
    	this.getxiaobing()[5].setwa(this, 4, 10);
    	this.getxiaobing()[6].setwa(this, 3, 10);
    }

    public void yanxing(){
    	this.getxiaobing()[0].setwa(this, 8, 4);
    	this.getxiaobing()[1].setwa(this, 7, 5);
    	this.getxiaobing()[2].setwa(this, 6, 6);
    	this.getxiaobing()[3].setwa(this, 5, 7);
    	this.getxiaobing()[4].setwa(this, 4, 8);
    	this.getxiaobing()[5].setwa(this, 3, 9);
    	this.getxiaobing()[6].setwa(this, 2, 10);
    }

    public void yanxing_end(){
       	this.getxiaobing()[0].removebing(this, 8, 4);
    	this.getxiaobing()[1].removebing(this, 7, 5);
    	this.getxiaobing()[2].removebing(this, 6, 6);
    	this.getxiaobing()[3].removebing(this, 5, 7);
    	this.getxiaobing()[4].removebing(this, 4, 8);
    	this.getxiaobing()[5].removebing(this, 3, 9);
    	this.getxiaobing()[6].removebing(this, 2, 10);
    }

    public void heyi_end(){
    	this.getxiaobing()[0].removebing(this, 3, 6);
    	this.getxiaobing()[1].removebing(this, 4, 6);
    	this.getxiaobing()[2].removebing(this, 5, 7);
    	this.getxiaobing()[3].removebing(this, 6, 8);
    	this.getxiaobing()[4].removebing(this, 5, 9);
    	this.getxiaobing()[5].removebing(this, 4, 10);
    	this.getxiaobing()[6].removebing(this, 3, 10);
    }

    public void henge(){
    	this.getxiaobing()[0].setwa(this, 2, 8);
    	this.getxiaobing()[1].setwa(this, 3, 7);
    	this.getxiaobing()[2].setwa(this, 4, 8);
    	this.getxiaobing()[3].setwa(this, 5, 7);
    	this.getxiaobing()[4].setwa(this, 6, 8);
    	this.getxiaobing()[5].setwa(this, 7, 7);
    	this.getxiaobing()[6].setwa(this, 8, 8);
    }

    public void henge_end(){
    	this.getxiaobing()[0].removebing(this, 2, 8);
    	this.getxiaobing()[1].removebing(this, 3, 7);
    	this.getxiaobing()[2].removebing(this, 4, 8);
    	this.getxiaobing()[3].removebing(this, 5, 7);
    	this.getxiaobing()[4].removebing(this, 6, 8);
    	this.getxiaobing()[5].removebing(this, 7, 7);
    	this.getxiaobing()[6].removebing(this, 8, 8);
    }

    public void fangzhen(){
    	this.getxiaobing()[0].setwa(this, 3, 8);
    	this.getxiaobing()[1].setwa(this, 4, 7);
    	this.getxiaobing()[2].setwa(this, 5, 6);
    	this.getxiaobing()[3].setwa(this, 6, 7);
    	this.getxiaobing()[4].setwa(this, 7, 8);
    	this.getxiaobing()[5].setwa(this, 6, 9);
    	this.getxiaobing()[6].setwa(this, 5, 10);
    	this.getxiaobing()[7].setwa(this, 4, 9);
    }

    public void fangzhen_end(){
    	this.getxiaobing()[0].removebing(this, 3, 8);
    	this.getxiaobing()[1].removebing(this, 4, 7);
    	this.getxiaobing()[2].removebing(this, 5, 6);
    	this.getxiaobing()[3].removebing(this, 6, 7);
    	this.getxiaobing()[4].removebing(this, 7, 8);
    	this.getxiaobing()[5].removebing(this, 6, 9);
    	this.getxiaobing()[6].removebing(this, 5, 10);
    	this.getxiaobing()[7].removebing(this, 4, 9);
    }

    public void fengshi(){
    	this.getxiaobing()[0].setwa(this, 2, 8);
    	this.getxiaobing()[1].setwa(this, 3, 7);
    	this.getxiaobing()[2].setwa(this, 3, 8);
    	this.getxiaobing()[3].setwa(this, 3, 9);
    	this.getxiaobing()[4].setwa(this, 4, 6);
    	this.getxiaobing()[5].setwa(this, 4, 8);
    	this.getxiaobing()[6].setwa(this, 4, 10);
    	this.getxiaobing()[7].setwa(this, 5, 8);
    	this.getxiaobing()[8].setwa(this, 6, 8);
    	this.getxiaobing()[9].setwa(this, 7, 8);
    }

    public void fengshi_end(){
    	this.getxiaobing()[0].removebing(this, 2, 8);
    	this.getxiaobing()[1].removebing(this, 3, 7);
    	this.getxiaobing()[2].removebing(this, 3, 8);
    	this.getxiaobing()[3].removebing(this, 3, 9);
    	this.getxiaobing()[4].removebing(this, 4, 6);
    	this.getxiaobing()[5].removebing(this, 4, 8);
    	this.getxiaobing()[6].removebing(this, 4, 10);
    	this.getxiaobing()[7].removebing(this, 5, 8);
    	this.getxiaobing()[8].removebing(this, 6, 8);
    	this.getxiaobing()[9].removebing(this, 7, 8);
    }

    public static void main(String[] args){
    	Map map=new Map();
    	map.init();
    }

}
