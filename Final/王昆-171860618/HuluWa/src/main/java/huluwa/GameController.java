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
    private static long time = 0;
    private static PrintWriter writer;
    static GameController getInstance(){
        return controller;
    }
    static void crashed(){
        if(controller!=null){
            controller.end();
            controller.reset();
        }
    }
    static GameController newInstance(int width,int height){
        controller = new GameController(width,height);
        return controller;
    }
    public static Condition currentCondition(){
        if(controller!=null){
            return controller.condition;
        }else{
            return Condition.WRONG;
        }
    }
    private BattleMap battleMap;
    private Position basePosition1;
    private Position basePosition2;
    private Condition condition;
    private ExecutorService exec;
    private ImageView winnerSlogan;
    private ImageView replaying;
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
        replaying = new ImageView(new Image("bin/replay.png"));
        replaying.setFitWidth(300);
        replaying.setFitHeight(80);
        replaying.setX(width/2 -100);
        replaying.setY(50);
        back.setLayoutX(0);
        back.setLayoutY(0);
        btnGForm = new Button("改变葫芦娃阵型");
        btnGForm.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{this.changeGoodForm();});
        btnBForm = new Button("改变妖精阵型");
        btnBForm.setLayoutX(width - 110);
        btnBForm.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{this.changeBadForm();});
        getChildren().add(back);
        getChildren().add(replaying);
        getChildren().add(btnBForm);
        getChildren().add(btnGForm);
        getChildren().add(battleMap);
        replaying.setVisible(false);

    }
    public void replay(File file){
        if(condition == Condition.END){
            if(winnerSlogan!=null)
                getChildren().remove(winnerSlogan);
            setArmy();
        }
        battleMap.clear();
        condition = Condition.REPLAY;
        btnGForm.setVisible(false);
        btnBForm.setVisible(false);
        replaying.setVisible(true);
        new RecordPlayer(file).start();
    }
    public void Check(){
        System.out.println(GoodCreature.aliveNumber + " " +BadCreature.aliveNumber);
        if(controller != null && controller.condition!=Condition.END){
            if(GoodCreature.aliveNumber == 0){
                Win(BadCreature.class);
                System.out.println("Monster win");
                controller.end();
            }
            else if(BadCreature.aliveNumber == 0){
                Win(GoodCreature.class);
                System.out.println("Huluwa win");
                controller.end();
            }
        }
    }
    public void Win(Class T){
        winnerSlogan = new ImageView();
        if(T ==GoodCreature.class)
            winnerSlogan.setImage(new Image("bin/goodwin.png"));
        else
            winnerSlogan.setImage(new Image("bin/badwin.png"));

        int mid = (basePosition1.getXOnMap()+basePosition2.getXOnMap())/2;
        winnerSlogan.setFitWidth(500);
        winnerSlogan.setFitHeight(80);
        winnerSlogan.setX(mid-200);
        winnerSlogan.setY(350);
        Platform.runLater(()-> getChildren().add(winnerSlogan));
    }
    public void writeRecords(long currentTime,String record){
        writer.println(currentTime - time +" " +record);
        //System.out.println(currentTime - time +" " +record);
    }
    public void end(){
        condition = Condition.END;
        for(GoodCreature goodCreature : GoodCreature.army) {
            goodCreature.alive = false;
        }
        for(BadCreature badCreature : BadCreature.army) {
            badCreature.alive = false;
        }
        replaying.setVisible(false);
        GoodCreature.army.clear();
        BadCreature.army.clear();
        GoodCreature.aliveNumber = BadCreature.aliveNumber = 0;
        if(exec !=null)
            exec.shutdown();
        if(writer != null)
            writer.close();
    }
    public void reset(){
        if(winnerSlogan!=null)
            getChildren().remove(winnerSlogan);
        btnGForm.setVisible(true);
        btnBForm.setVisible(true);
        battleMap.clear();
        setArmy();
        setFormation();
        condition = Condition.READY;
    }
    public void begin(){
        try{
            btnGForm.setVisible(false);
            btnBForm.setVisible(false);
            String filename = "records/records";
            File dir = new File(filename);
            if(!dir.exists()){
                new File("records").mkdirs();
            }
            String t =new SimpleDateFormat("ddHHmmss").format(new Date());
            writer = new PrintWriter(filename + t +".txt");
            this.condition = Condition.RUNNING;
            time = System.currentTimeMillis();
            exec= Executors.newCachedThreadPool();
            for(int i=0;i<GoodCreature.army.size();i++){
                exec.execute(GoodCreature.army.get(i));
            }
            for(int i=0;i<BadCreature.army.size();i++){
                exec.execute(BadCreature.army.get(i));
            }
        }catch(IOException e){
            e.printStackTrace();
            crashed();
        }
        //GoodCreature.start();
        //BadCreature.start();
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
            crashed();
        }
    }
    private void setArmy(){
        try{
            GoodCreature.addArmy(new HuluWa("DaWa","bin/huluwa1.png"));
            GoodCreature.addArmy(new HuluWa("ErWa","bin/huluwa2.png"));
            GoodCreature.addArmy(new HuluWa("SanWa","bin/huluwa3.png"));
            GoodCreature.addArmy(new HuluWa("SiWa","bin/huluwa4.png"));
            GoodCreature.addArmy(new HuluWa("WuWa","bin/huluwa5.png"));
            GoodCreature.addArmy(new HuluWa("LiuWa","bin/huluwa6.png"));
            GoodCreature.addArmy(new HuluWa("QiWa","bin/huluwa7.png"));
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
