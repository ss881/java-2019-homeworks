package creature;

import javafx.scene.image.Image;
import location.Position;
import war.BattleField;

import java.util.Random;

/*
* DIR
* */
enum DIRECTION{UP,DOWN,LEFT,RIGHT,LEFT_UP,RIGHT_UP,LEFT_DOWN,RIGHT_DOWN};

public class Creature{

    public Image icon;
    public Position pos;
    //private String iconPath;
    private int healthPoint;
    private int manaPoint;
    public int id;
    //boolean state;
    //public Creature()

    public Creature(Position p,int HP,int MP,int i){
        pos = p;
        //iconPath = path;
        icon = null;
        healthPoint = HP;
        manaPoint = MP;
        id = i;
    }

    public Boolean isAlive(){
        return healthPoint>0;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void loadIcon(String iconPath){
        icon = new Image(iconPath);
        //ImageView iconView = new ImageView(icon);
    }

    public void setPosition(int x, int y){
        Position nextPos = BattleField.getInstance().posList.get(BattleField.getIndex(x,y));
        nextPos.holder = this;
        pos.holder = null;
        pos = nextPos;
    }

    public Boolean isValidMove(DIRECTION direction, Position newPos){
        switch (direction){
            case UP:{
                newPos.x -= 1;
                break;
            }
            case DOWN:{
                newPos.x += 1;
                break;
            }
            case LEFT:{
                newPos.y -= 1;
                break;
            }
            case RIGHT:{
                newPos.y += 1;
                break;
            }
            case LEFT_UP:{
                newPos.y -= 1;
                newPos.x -= 1;
                break;
            }
            case LEFT_DOWN:{
                newPos.x += 1;
                newPos.y -= 1;
                break;
            }
            case RIGHT_UP:{
                newPos.x -= 1;
                newPos.y += 1;
                break;
            }
            case RIGHT_DOWN:{
                newPos.x += 1;
                newPos.y += 1;
                break;
            }
        }
        return ((newPos.x >= 0 && newPos.x < BattleField.FIELD_HEIGHT) &&
                (newPos.y >= 0 && newPos.y < BattleField.FIELD_WIDTH));
    }

    public final DIRECTION randomDirection(){
        Random rand = new Random();
        DIRECTION direction = DIRECTION.values()[rand.nextInt(8)];
        return  direction;
    }

    @Override
    public String toString() {
        //return super.toString();
        Integer hp = new Integer(healthPoint);
        Integer mp = new Integer(manaPoint);
        return pos.toString()+" HP="+hp.toString()+" MP="+mp.toString();
    }

    /*@Override
    public void run(){
        System.out.println("Creature:  RUN!");
    }*/
}
