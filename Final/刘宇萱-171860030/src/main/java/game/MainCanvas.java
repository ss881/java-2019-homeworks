package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
 
public class MainCanvas extends Canvas {
	private GraphicsContext gcontext;
	
	static final int ROW = 5;
    static final int COLUMN = 9;
    static final int UNDERLING_NUM = 8;
    static final int CALA_NUM = 7;
    static final int FORM_NUM = 3;
    static final int X = 110; 
    static final int Y = 76;
    static final int DX = 119; 
    static final int DY = 118;
    
    static final long sleeptime = 1000;
    
    //private ArrayList<Role> roles = new ArrayList<Role>();
    private Formation form = new Formation();
    private static int num1;
    private static int num2;
 
	private boolean isrunning = true;
	//public static boolean start = false;
	
	private ArrayList<CalabashBrotherThread> cbthreads = new ArrayList<CalabashBrotherThread>();
	private ArrayList<UnderlingThread> unthreads = new ArrayList<UnderlingThread>();
	private GrandpaThread grthread;
	private ScorpionThread scthread;
	private SnakeThread snthread;
	
	private Image imagecala1 = new Image(getClass().getResourceAsStream("picture/cala1.png"));
	private Image imagecala2 = new Image(getClass().getResourceAsStream("picture/cala2.png"));
	private Image imagecala3 = new Image(getClass().getResourceAsStream("picture/cala3.png"));
	private Image imagecala4 = new Image(getClass().getResourceAsStream("picture/cala4.png"));
	private Image imagecala5 = new Image(getClass().getResourceAsStream("picture/cala5.png"));
	private Image imagecala6 = new Image(getClass().getResourceAsStream("picture/cala6.png"));
	private Image imagecala7 = new Image(getClass().getResourceAsStream("picture/cala7.png"));
	private Image imagegrandpa = new Image(getClass().getResourceAsStream("picture/grandpa.png"));
	private Image imagescorpion = new Image(getClass().getResourceAsStream("picture/scorpion.png"));
	private Image imagesnake = new Image(getClass().getResourceAsStream("picture/snake.png"));
	private Image imageunderling = new Image(getClass().getResourceAsStream("picture/underling.png"));
	
	/*public static boolean load = false;
	public static boolean start = false;
	public static boolean drawnemu = true;*/
	
	
	// 主线程
	private Thread mainthread;
	
	
	
	MainCanvas(double width, double height) {
		super(width, height);
        
    	gcontext = getGraphicsContext2D();
     
    	//mainthread.start();
    }

    
    
 
	public void drawFightingScene() {
		
		synchronized(Formation.class) {
			gcontext.save();
		
			gcontext.clearRect(0, 0, 1200, 800);
			
			//gcontext.drawImage(fightingscene, 0, 0);
			
			if(cbthreads.get(0).alive()) gcontext.drawImage(imagecala1, Y + cbthreads.get(0).calabashbrother.getCoordinate().getY() * DY, X + cbthreads.get(0).calabashbrother.getCoordinate().getX() * DX);
			if(cbthreads.get(1).alive()) gcontext.drawImage(imagecala2, Y + cbthreads.get(1).calabashbrother.getCoordinate().getY() * DY, X + cbthreads.get(1).calabashbrother.getCoordinate().getX() * DX);
			if(cbthreads.get(2).alive()) gcontext.drawImage(imagecala3, Y + cbthreads.get(2).calabashbrother.getCoordinate().getY() * DY, X + cbthreads.get(2).calabashbrother.getCoordinate().getX() * DX);
			if(cbthreads.get(3).alive()) gcontext.drawImage(imagecala4, Y + cbthreads.get(3).calabashbrother.getCoordinate().getY() * DY, X + cbthreads.get(3).calabashbrother.getCoordinate().getX() * DX);
			if(cbthreads.get(4).alive()) gcontext.drawImage(imagecala5, Y + cbthreads.get(4).calabashbrother.getCoordinate().getY() * DY, X + cbthreads.get(4).calabashbrother.getCoordinate().getX() * DX);
			if(cbthreads.get(5).alive()) gcontext.drawImage(imagecala6, Y + cbthreads.get(5).calabashbrother.getCoordinate().getY() * DY, X + cbthreads.get(5).calabashbrother.getCoordinate().getX() * DX);
			if(cbthreads.get(6).alive()) gcontext.drawImage(imagecala7, Y + cbthreads.get(6).calabashbrother.getCoordinate().getY() * DY, X + cbthreads.get(6).calabashbrother.getCoordinate().getX() * DX);
		
			for(int i = 0; i < UNDERLING_NUM; i++) {
				if(unthreads.get(i).alive()) gcontext.drawImage(imageunderling, Y + unthreads.get(i).underling.getCoordinate().getY() * DY, X + unthreads.get(i).underling.getCoordinate().getX() * DX);
			}
		
			if(grthread.alive()) gcontext.drawImage(imagegrandpa, Y + grthread.grandpa.getCoordinate().getY() * DY, X + grthread.grandpa.getCoordinate().getX() * DX);
			if(scthread.alive()) gcontext.drawImage(imagescorpion, Y + scthread.scorpion.getCoordinate().getY() * DY, X + scthread.scorpion.getCoordinate().getX() * DX);
			if(snthread.alive()) gcontext.drawImage(imagesnake, Y + snthread.snake.getCoordinate().getY() * DY, X + snthread.snake.getCoordinate().getX() * DX);
		
			gcontext.restore();
		
			
		}
	}
	
	public void killAll() {
		//System.out.println("killall");
		for(int i = 0; i < CALA_NUM; i++) {
			cbthreads.get(i).killed();
		}
		
		for(int i = 0; i < UNDERLING_NUM; i++) {
			unthreads.get(i).killed();
		}
		
		grthread.killed();
		scthread.killed();
		snthread.killed();
	}
	
	public static void num1Dec() {
		num1--;
	}
	
	public static void num2Dec() {
		num2--;
	}
	
	public void gameStart() {
		mainthread = new Thread(new Runnable() {
			 
			@Override
			public void run() {
				//System.out.println("executing");
				
				Random r = new Random();
		        int ci = r.nextInt(FORM_NUM);
		        //System.out.println(ci);
		        form.setCalabashForm(ci);
		        int ui = r.nextInt(FORM_NUM);
		        //System.out.println(ui);
		        form.setUnderlingForm(ui);
		        form.setCurForm(ci, ui);
		        
		        cbthreads.clear();
		        cbthreads.add(new CalabashBrotherThread(Color.RED, 1, form.getCalaCoordinate(0).getX(), form.getCalaCoordinate(0).getY()));
		        cbthreads.add(new CalabashBrotherThread(Color.ORANGE, 2, form.getCalaCoordinate(1).getX(), form.getCalaCoordinate(1).getY()));
		        cbthreads.add(new CalabashBrotherThread(Color.YELLOW, 3, form.getCalaCoordinate(2).getX(), form.getCalaCoordinate(2).getY()));
		        cbthreads.add(new CalabashBrotherThread(Color.GREEN, 4, form.getCalaCoordinate(3).getX(), form.getCalaCoordinate(3).getY()));
		        cbthreads.add(new CalabashBrotherThread(Color.SYAN, 5, form.getCalaCoordinate(4).getX(), form.getCalaCoordinate(4).getY()));
		        cbthreads.add(new CalabashBrotherThread(Color.BLUE, 6, form.getCalaCoordinate(5).getX(), form.getCalaCoordinate(5).getY()));
		        cbthreads.add(new CalabashBrotherThread(Color.PURPLE, 7, form.getCalaCoordinate(6).getX(), form.getCalaCoordinate(6).getY()));
		        grthread = new GrandpaThread(form.getCalaCoordinate(7).getX(), form.getCalaCoordinate(7).getY());
				
		        unthreads.clear();
		        for(int i = 0; i < UNDERLING_NUM; i++) {
		        	unthreads.add(new UnderlingThread(form.getUnderlingCoordinate(i).getX(), form.getUnderlingCoordinate(i).getY()));
		        }
		        scthread = new ScorpionThread(0, 8);
		    	snthread = new SnakeThread(4, 8);
		    	
		    	num1 = CALA_NUM + 1;
		        num2 = UNDERLING_NUM + 2;
				
		    	//初始场景
		        Platform.runLater(new Runnable() {
					 
					@Override
					public void run() {
						drawFightingScene();
					}
				});
		        
		        Date date = new Date();
		        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		        //System.out.println(str);
		        
		        /*try {
		        	File f = new File(simpleDateFormat.format(date)+ "_record.txt");
		        	f.createNewFile();
		        	fileWritter = new FileWriter(f.getName(), true);
		            bufferWritter = new BufferedWriter(fileWritter);
		        }
		        catch(FileNotFoundException e) {
		            e.printStackTrace();
		        }
		        catch(IOException e) {
		        	e.printStackTrace();
		        }*/
				
				
				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				for(int i = 0; i < CALA_NUM; i++) {
					cbthreads.get(i).start();
				}
				for(int i = 0; i < UNDERLING_NUM; i++) {
					unthreads.get(i).start();
				}
				grthread.start();
				scthread.start();
				snthread.start();
				
				//战斗过程
				isrunning = true;
				
				while (isrunning) {
					synchronized (Formation.class) {
						//System.out.println("num1: " + num1 + ", num2 : " + num2);
						if(num1 == 0 || num2 == 0) {
							killAll();
							synchronized(Lock.lock) {
								Lock.lock.notifyAll();
								
							}
							
							try {
								Thread.sleep(sleeptime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}
						
						Platform.runLater(new Runnable() {
						 
							@Override
							public void run() {
								drawFightingScene();
							}
						});
						
						/*try {
							bufferWritter.write("C");
							for(int i = 0; i < MainCanvas.ROW; i++) {
								for(int j = 0; j < MainCanvas.COLUMN; j++) {
									bufferWritter.write(Formation.curform[i][j]);
								}
							}
							
						}
						catch(IOException e) {
							e.printStackTrace();
						}*/
					}
					
					synchronized(Lock.lock) {
						Lock.lock.notifyAll();
					}
					
					try {
						Thread.sleep(sleeptime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				
				/*try {
					bufferWritter.write("E");
					bufferWritter.close();
					fileWritter.close();
				}
				catch (IOException e) {
	                e.printStackTrace();
	            }*/
			}
		});
		
		mainthread.start();
	}
}