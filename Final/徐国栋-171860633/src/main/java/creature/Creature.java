package creature;

import annotation.Description;
import javafx.scene.image.Image;
import objinfo.XPoint2D;
import objinfo.BioAppearance;

import gamectrl.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Description(comment = "实现生物类，主动寻找攻击目标")
public class Creature implements Runnable, MainConfig { // 生物类，包含人物的基本属性，用到外观类
    private int id; // 生物的全局唯一编号，避免重复
    private String name = new String(); // 生物的名称，允许重复
    Random r=new Random();
    public static MainControl ctrl;

    public BioAppearance apperrance; // 生物的外观，包含颜色
    private XPoint2D position = new XPoint2D(0, 0);
    public boolean isAlive=true,isEvil;

    public void setApperrance(Image l,Image r){
        apperrance=new BioAppearance(l,r);
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public Creature() {
        apperrance = new BioAppearance();
    }

    public int getId() {
        return id;
    }

    public XPoint2D getPosition() {
        XPoint2D result = new XPoint2D(position.getX(), position.getY());
        return result;
    }

    public void show(){
        XPoint2D pos=getPosition();
        //System.out.println("pos="+pos.toString());
        int x=pos.getX()*ctrl.step,
                y=pos.getY()*ctrl.step,
                w=ctrl.step;
        //System.out.println(getName()+" at "+pos.toString());
        ctrl.gc.drawImage(getImage(),x,y,w,w);
    }

    private Image getImage(){
        return apperrance.img;
    }

    public void setPosition(XPoint2D _position) {
        position.setX(_position.getX());
        position.setY(_position.getY());
    }

    protected void setId(int _id) {
        id = _id;
    }

    boolean isVaildPos(int x,int y){
        if(x>=0&&y>=0&&x<ctrl.col&&y<ctrl.row){
            return true;
        }
        return false;
    }

    public void move(){
        //System.out.println("move:"+getName());
        int oldX=getPosition().getX(),oldY=getPosition().getY();
        int newX = oldX;// - 1 + r.nextInt(3);
        int newY = oldY;// - 1 + r.nextInt(3);
        boolean EnemyFound=false;
        // 进行横纵向观察，如有敌人，定向冲锋
        Creature target;
        for(int i=0;i<oldX;i++){
            target=ctrl.map.getCreature(i,oldY);
            if (target!=null && target.isEvil!=isEvil){
                newX=oldX-1;
                newY=oldY;
                EnemyFound=true;
                break;
            }
        }
        if (!EnemyFound) {
            for (int i = 0; i < oldY; i++) {
                target = ctrl.map.getCreature(oldX, i);
                if (target != null && target.isEvil != isEvil) {
                    newX = oldX;
                    newY = oldY - 1;
                    EnemyFound = true;
                    break;
                }
            }
        }
        if(!EnemyFound){
            for(int i=oldX+1;i<ctrl.col;i++){
                target=ctrl.map.getCreature(i,oldY);
                if (target!=null && target.isEvil!=isEvil){
                    newX=oldX+1;
                    newY=oldY;
                    EnemyFound=true;
                    break;
                }
            }
        }
        if(!EnemyFound){
            for(int i=oldY+1;i<ctrl.row;i++){
                target=ctrl.map.getCreature(oldX,i);
                if (target!=null && target.isEvil!=isEvil){
                    newX=oldX;
                    newY=oldY+1;
                    EnemyFound=true;
                    break;
                }
            }
        }
        //  为了营造混乱气氛，即使葫芦娃看到敌人，也有1/5的概率乱跑
        if(r.nextInt(5)==0||!EnemyFound){
            newX = oldX - 1 + r.nextInt(3);
            newY = oldY - 1 + r.nextInt(3);
        }
        //  如果没有观察到敌人，随机选择行进方向
        for (int i = 0; i < 10; i++) {
            if (!isVaildPos(newX, newY)) {
                newX = oldX - 1 + r.nextInt(3);
                newY = oldY - 1 + r.nextInt(3);
            }else{
                break;
            }
        }
        if(!isVaildPos(newX,newY)){
            return;
        }
        target=ctrl.map.getCreature(newX, newY);
        if (target == null){
            ctrl.map.remove(this);
            //System.out.println(getName()+" at "+position.toString());
            int x0=position.getX();
            setPosition(new XPoint2D(newX, newY));
            //System.out.println(getName()+" move to "+position.toString());
            int x1=position.getX();
            if(x1>x0){//控制图片方向
                tunrRight();
            }else{
                turnLeft();
            }
            ctrl.map.acceptMove(this);
        }else{
            //System.out.println("but quit move2:"+getName());
        }
    }

    public void suffer(){
        isAlive=false;
        ctrl.map.remove(this);
        setApperrance(new Image("images/die.png"),new Image("images/die.png"));
    }

    public boolean attack(Creature target){
        if (target == null) {
            return false;
        }
        if (this.isAlive && target.isAlive &&this.isEvil != target.isEvil) {
            int win = r.nextInt(100);
            if (win < 50) {
                suffer();
            } else {
                target.suffer();
            }
            return true;
        }
        return false;
    }

    private void fight(){
        int x=position.getX(),y=position.getY();
        int w=ctrl.col,h=ctrl.row;
        if(x+1<w)
            if(attack(ctrl.map.getCreature(x+1,y))){return;}
        if(y+1<h)
            if(attack(ctrl.map.getCreature(x,y+1))){return;}
        if(y-1>=0)
            if(attack(ctrl.map.getCreature(x,y-1))){return;}
        if(x-1>=0)
            if(attack(ctrl.map.getCreature(x-1,y))){return;}
    }

    private boolean allowMove(){
        int x=position.getX(),y=position.getY();
        int w=ctrl.col,h=ctrl.row;
        boolean hasCreature=false;
        Creature target;
        if (x + 1 < w) {
            target = ctrl.map.getCreature(x + 1, y);
            if (target != null && isEvil!=target.isEvil) {
                hasCreature = true;
            }
        }
        if (y + 1 < h) {
            target = ctrl.map.getCreature(x, y + 1);
            if (target != null&& isEvil!=target.isEvil) {
                hasCreature = true;
            }
        }
        if (x - 1 >= 0) {
            target = ctrl.map.getCreature(x - 1, y);
            if (target != null&& isEvil!=target.isEvil) {
                hasCreature = true;
            }
        }
        if (y - 1 >= 0) {
            target = ctrl.map.getCreature(x, y - 1);
            if (target != null&& isEvil!=target.isEvil) {
                hasCreature = true;
            }
        }
        return !hasCreature;
    }

    public boolean goon(){
        synchronized (Ground2D.class) {
            if (!isAlive) {
                return false;
            }
            if (allowMove()) {
                move();
            } else {
                synchronized (Creature.class) {
                    fight();
                }
            }
            return true;
        }
    }

    public boolean isLeft;
    private void turnLeft(){
        isLeft=true;
        apperrance.img=apperrance.left;
    }

    private void tunrRight(){
        isLeft=false;
        apperrance.img=apperrance.right;
    }
    @Override
    public void run() {
        while(true){
            //System.out.println(getName()+" is going on...");
            if(ctrl.gameOver || !isAlive){
                break;
            }
            else if(!ctrl.isPaused){
                goon();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(MainConfig.CREATURE_INTERVAL);
            } catch (Exception e) {
                ;
            }
        }
    }
}