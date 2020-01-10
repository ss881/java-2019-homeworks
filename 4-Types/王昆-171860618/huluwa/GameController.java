package huluwa;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import huluwa.position.Position;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class GameController extends Parent {
    private static GameController controller;
    static GameController newInstance(int width,int height){
        controller = new GameController(width,height);
        return controller;
    }
    private BattleMap battleMap;
    private Position basePosition1;
    private Position basePosition2;
    private Button btnGForm;
    private Button btnBForm;
    private int formPos1 = 1,formPos2 =1;
    private GameController(int width, int height){
        int xMax = 18,yMax = 9;
        battleMap = new BattleMap(width,height);
        Creature.map = battleMap;
        Position.setMapSize(width,height);
        Position.setMaxGrid(xMax,yMax);
        basePosition1 = new Position(0,Position.sizeY()/2);
        basePosition2 = new Position(Position.sizeX(),Position.sizeY()/2);
        ImageView back = new ImageView(new Image("bin/ground.jpg",width,height,false,false));
        back.setLayoutX(0);
        back.setLayoutY(0);
        btnGForm = new Button("改变葫芦娃阵型");
        btnGForm.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{this.changeGoodForm();});
        btnBForm = new Button("改变妖精阵型");
        btnBForm.setLayoutX(width - 110);
        btnBForm.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{this.changeBadForm();});
        getChildren().add(back);
        getChildren().add(btnBForm);
        getChildren().add(btnGForm);
        getChildren().add(battleMap);

    }
    public void reset(){
        btnGForm.setVisible(true);
        btnBForm.setVisible(true);
        battleMap.clear();
        setArmy();
        setFormation();
    }
    private void setFormation(){
        try{
            List<Position> huluFormation = new Formation(basePosition1,GoodCreature.aliveNumber,1).getFormation(formPos2);
            List<Position> monsterFormation = new Formation(basePosition2,BadCreature.aliveNumber,-1).getFormation(formPos1);
            GoodCreature.setFormation(huluFormation);
            BadCreature.setFormation(monsterFormation);
           //System.out.println("设置阵型成功" + GoodCreature.aliveNumber +BadCreature.aliveNumber);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void setArmy(){
        try{
            GoodCreature.addArmy(new HuluWa("DaWa", "bin/huluwa1.png"));
            GoodCreature.addArmy(new HuluWa("ErWa", "bin/huluwa2.png"));
            GoodCreature.addArmy(new HuluWa("SanWa", "bin/huluwa3.png"));
            GoodCreature.addArmy(new HuluWa("SiWa", "bin/huluwa4.png"));
            GoodCreature.addArmy(new HuluWa("WuWa", "bin/huluwa5.png"));
            GoodCreature.addArmy(new HuluWa("LiuWa", "bin/huluwa6.png"));
            GoodCreature.addArmy(new HuluWa("QiWa", "bin/huluwa7.png"));
            GoodCreature.addArmy(new Grandpa());
            BadCreature.addArmy(new Scorpion());
            BadCreature.addArmy(new Snake());
            for(int i=0;i<6;i++){
                BadCreature.addArmy(new Follower(i));
            }
            //System.out.println("设置军队成功" + formPos2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void changeGoodForm(){
        try{
            formPos2 = (formPos2+1)%Formation.TOTOAL;
            List<Position> huluFormation = new Formation(basePosition1,GoodCreature.aliveNumber,1).getFormation(formPos2);
            GoodCreature.setFormation(huluFormation);
            //System.out.println("改变阵型成功" + formPos2);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    private void changeBadForm(){
        try{
            formPos1 = (formPos1+1)%Formation.TOTOAL;
            List<Position> monsterFormation = new Formation(basePosition2,BadCreature.aliveNumber,-1).getFormation(formPos1);
            BadCreature.setFormation(monsterFormation);
            //System.out.println("改变阵型成功" + formPos1);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
