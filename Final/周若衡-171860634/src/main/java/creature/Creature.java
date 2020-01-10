package creature;

import chessboard.Cell;
import gui.MyTextArea;
import behave.AttackBehave;
import playrecord.Record;
import behave.MoveBehave;
import main.Main;
import chessboard.ChessBoard;
import util.DirectionVector;
import util.ImageType;

public class Creature extends Being implements Runnable{
    //需要添加更多属性
    protected int hp; //生命值
    protected boolean alive;  //是否存活
    protected int battle; //攻击值
    protected int numDisappear=2; //在死亡状态下需要攻击多少次墓碑才会消失
    protected int fullHp;   //满血时的hp
    protected int speed;    //移动速度
    protected int id;       //每一个生物都有一个独特的id作为主属性标识
    public int getId(){return id;}
    public void setSleepTime(int time){speed=time;}
    public int getSleepTime(){return speed;}
    public Creature(int x, int y, ImageType image, int hp, boolean alive, int battle, String name, int speed, int id) {
        super(x,y,image,name);
        this.hp=hp;
        this.alive=alive;
        this.battle=battle;
        this.fullHp=hp;
        this.speed=speed;
        this.id=id;
    }
    @Override
    public Object clone() {
        Creature temp = null;
        temp = (Creature) super.clone();
        return temp;
    }

    public int getHp(){return hp;}
    public int getFullHp(){return fullHp;}
    public int getBattle(){return battle;}
    public boolean isAlive(){return alive;}

    public void setHp(int hp){this.hp=hp;}
    public void setBattle(int battle){this.battle=battle;}
    public void setAlive(boolean alive){this.alive=alive;}

    public void move(int dx, int dy, ChessBoard board, MyTextArea textArea, boolean isAddCommand) {
        synchronized(board) {
            synchronized (this) {
                if (isAlive() == false)
                    return;
            }
            if(board.canPutCreature(x+dx,y+dy)==false)
                return;
            else {
                if(textArea!=null && isAddCommand==true) {
                    long curTime = System.currentTimeMillis();
                    //准备记录信息，写入数组之中
                    Record command = new Record(this, new MoveBehave((int) (curTime - Main.chessBoard.startTime), dx, dy));
                    synchronized (Main.chessBoard.getRecordAdminister().getCommands()) { //对共享变量commands加锁
                        Main.chessBoard.getRecordAdminister().addOne(command);
                    }
                }
                if(textArea!=null )
                    textArea.appendTextArea(getName()+"移动到("+(x+dx)+","+(y+dy)+")\n");
                Being being=board.getSquare(x,y).getBeing();
                board.getSquare(x+dx,y+dy).setBeing(being);
                board.getSquare(x,y).setBeing(null);
                this.setX(x+dx);
                this.setY(y+dy);
            }
        }
    }

    public void attack(Creature c, ChessBoard board, MyTextArea textArea, boolean isAddCommand){
        synchronized(board){
            synchronized (this) {
                if (isAlive() == false)
                    return;
            }
            synchronized (c) {
                if (Math.abs(c.getX() - getX()) > 1)
                    return;
                if (Math.abs(c.getY() - getY()) > 1)
                    return;
                if (c.isAlive() == false) { //敌方已经死亡 只是一个墓碑
                    if (c.numDisappear > 0) {
                        //--------->
                        if (textArea != null && isAddCommand==true) {
                            long curTime = System.currentTimeMillis();
                            //准备记录信息，写入数组之中
                            Record command = new Record(this, new AttackBehave((int) (curTime - Main.chessBoard.startTime), c));
                            synchronized (Main.chessBoard.getRecordAdminister().getCommands()) {
                                Main.chessBoard.getRecordAdminister().addOne(command);
                            }
                        }
                        c.numDisappear--;
                        if (textArea != null)
                            textArea.appendTextArea(this.getName() + "攻击" + c.getName() + "的墓碑\n");
                        if (c.numDisappear == 0) {
                            if (textArea != null)
                                textArea.appendTextArea(c.getName() + "的墓碑被摧毁\n");
                            board.setSquare(new Cell(c.getX(), c.getY(), null));
                        }
                    }
                    return;
                }
                int result = c.getHp() - this.getBattle();
                if (textArea != null && isAddCommand==true) {
                    long curTime = System.currentTimeMillis();
                    //准备记录信息，写入数组之中
                    Record command = new Record(this, new AttackBehave((int) (curTime - Main.chessBoard.startTime), c));
                    synchronized (Main.chessBoard.getRecordAdminister().getCommands()) {
                        Main.chessBoard.getRecordAdminister().addOne(command);
                    }
                }
                if (result <= 0) {
                    if (textArea != null)
                        textArea.appendTextArea(this.getName() + "攻击" + c.getName() + ",造成其" + c.getHp() + "点伤害，死亡\n");
                    c.setAlive(false);
                    c.setHp(0);
                } else {
                    if (textArea != null)
                        textArea.appendTextArea(this.getName() + "攻击" + c.getName() + ",造成其" + this.getBattle() + "点伤害\n");
                    c.setHp(result);
                }
            }
        }
    }

    @Override
    public void run() {
        while(Main.chessBoard.isOver()==false){
            if(this.alive==false)
                break;

            try {
                Thread.sleep(speed);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DirectionVector d= ChessBoard.directions.get(Main.random.nextInt(ChessBoard.directions.size()));
            Creature enemy=Main.chessBoard.getChessBoard().getEnemy(this);
            if(enemy!=null){
                this.attack(enemy,Main.chessBoard.getChessBoard(),Main.textArea,true);
                Main.chessBoard.paint();
            }
            else{

                DirectionVector v=null;
                if(this instanceof Elder){
                    if(Main.random.nextDouble()<=0.5){
                        Elder t=(Elder)this;
                        if(t.callMonster(Main.chessBoard.getChessBoard(),Main.textArea,true))
                            continue;
                    }
                }


                this.move((int)d.getX(),(int)d.getY(),Main.chessBoard.getChessBoard(),Main.textArea,true);
                Main.chessBoard.paint();
            }
        }

    }
}
