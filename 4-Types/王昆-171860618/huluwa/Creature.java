package huluwa;

import javafx.application.Platform;
import javafx.scene.image.*;
import huluwa.position.Position;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class Creature{
    protected ImageView icon;
    protected Position position;
    protected int height;
    protected int width;
    protected int speed;
    protected int range;
    int attack;
    protected String name;
    static BattleMap map;
    @Override
    public String toString(){
        return this.name;
    }
    public ImageView getIcon(){
        return this.icon;
    }
    public Position getPosition() {
        return position;
    }
    public void joinMap(Position p){
        if(map.isFree(p)){
            this.position = p;
            map.add(this,this.position);
        }else{
            System.out.println(this + "无法加入");
        }
    }
 }

class GoodCreature extends Creature{
    protected static List<GoodCreature> army = new ArrayList<>();
    protected static int aliveNumber = 0;
    public static void setFormation(List<Position> f) throws Exception{
        if(f==null || aliveNumber!= f.size())
            throw new Exception("错误的阵型信息");
        for(GoodCreature c:army){
            map.remove(c);
        }
        for(int i=0;i<aliveNumber;i++){
            Position p = f.get(i);
            army.get(i).joinMap(p);
        }
    }
    public static void addArmy(Creature c) throws Exception{
        if(c instanceof GoodCreature){
            army.add((GoodCreature) c);
            aliveNumber++;
        }
        else
            throw new Exception("添加了错误的生物种类");
    }
}
 class BadCreature extends Creature{
    static List<BadCreature> army= new ArrayList<>();
    static int aliveNumber = 0;
    public static void setFormation(List<Position> f) throws Exception{
         if(f==null || aliveNumber!= f.size())
             throw new Exception("错误的阵型信息");
        for(BadCreature c:army){
            map.remove(c);
        }
         for(int i=0;i<aliveNumber;i++){
             Position p = f.get(i);
             army.get(i).joinMap(p);
         }
     }
     public static void addArmy(Creature c) throws Exception{
         if(c instanceof BadCreature){
             army.add((BadCreature) c);
             aliveNumber++;
         }
         else
             throw new Exception("添加了错误的生物种类");
     }
}
 class HuluWa extends GoodCreature{
     HuluWa(String n, String url){
         this.name = n;
         this.width = 75;
         this.speed = 1200;
         this.height = 105;
         this.range = 1;
         this.attack = 7;
         this.icon = new ImageView(url);
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Grandpa extends GoodCreature{
     Grandpa(){
         this.name = "GrandPa";
         this.width = 75;
         this.speed = 1500;
         this.range = 2;
         this.height = 100;
         this.attack = 4;
         this.icon = new ImageView("bin/laoyeye.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Scorpion extends BadCreature{
     Scorpion(){
         this.name = "Scorpion";
         this.width = 115;
         this.height = 120;
         this.speed = 1000;
         this.range = 1;
         this.attack = 10;
         this.icon = new ImageView("bin/xiezi.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Snake extends BadCreature{
     Snake(){
         this.name = "Snake";
         this.width = 100;
         this.height = 120;
         this.speed = 800;
         this.range = 2;
         this.attack = 5;
         this.icon = new ImageView("bin/shejing.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Follower extends BadCreature{
     Follower(int i){
         this.name = "Follower-"+i;
         this.width = 80;
         this.height = 105;
         this.speed = 1200;
         this.range = 1;
         this.attack = 3;
         this.icon = new ImageView("bin/wugong.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 