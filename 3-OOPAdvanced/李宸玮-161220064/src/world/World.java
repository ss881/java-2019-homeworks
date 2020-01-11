package world;

import creature.Calabash;
import creature.*;
import formation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World extends Application{
    private static BattleField space=new BattleField();

    public static void bubbleSort(creature.Calabash BrothersArray[]){
        creature.Calabash temp=new Calabash(8);
        for(int i=0;i<7;i++) {
            for(int j=i+1;j<7;j++) {
                if(BrothersArray[i].order>BrothersArray[j].order){
                    temp=BrothersArray[i];
                    BrothersArray[i]=BrothersArray[j];
                    BrothersArray[j]=temp;
                }
            }
        }
    }//对胡芦娃进行排序
    public static World ground;
    public static void main(String[] args){
        Calabash[] HuluBrothers=new Calabash[7];
        Lackeys[] LackeysCrowds=new Lackeys[7];//创建小喽啰群和葫芦娃群
        Creature[] characters=new Creature[16];
        Snake snake=new Snake();
        Grandfather grandfather=new Grandfather();//人物构建已完成
        grandfather.setImage("pics/Huluwa/grandfather .png");
        snake.setImage("pics/Huluwa/snake.png");
        grandfather.setPreImage("(G)");
        snake.setPreImage("(S)");
        List list=new ArrayList<Calabash>();
        for(int i=0;i<7;i++){
            Calabash hulu=new Calabash(i+1);//产生老大-老7
            int help=i+1;
            String path="pics/Huluwa/"+help+".png";
            hulu.setPreImage("("+help+")");
            hulu.setImage(path);
            HuluBrothers[i]=hulu;
            list.add(HuluBrothers[i]);
            Lackeys lackeys=new Lackeys();
            lackeys.setPreImage("(*)");
            lackeys.setImage("pics/Huluwa/monster.png");
            LackeysCrowds[i]=lackeys;
        }
        Collections.shuffle(list);//利用java中的shuffle初始化乱序葫芦兄弟
        //第一次布阵
        list.toArray(HuluBrothers);
        ChangShe changshe=new ChangShe();
        HeYi heyi=new HeYi();
        YanXing yanxing=new YanXing();
        FengShi fengShi=new FengShi();
        changshe.setChangshe(HuluBrothers);
        //  changshe.setChangshe(LackeysCrowds);//布阵
        //Round 1
        System.out.println("---------------------------Round 1-----------------------------");
        heyi.setHeYi(LackeysCrowds);
        int huluxpos=4;
        int huluypos=3;
        int lackeysxpos=1;//确定其放置的位置
        int lackeysypos=13;
        for(int i=0;i<7;i++){
            HuluBrothers[i].move(huluxpos,huluypos);
            LackeysCrowds[i].move(lackeysxpos,lackeysypos);
        }
        grandfather.moveto(huluxpos-1,huluypos);
        snake.moveto(lackeysxpos,lackeysypos);
        for(int i=0;i<7;i++){
            characters[i]=HuluBrothers[i];
        }
        for(int i=7;i<14;i++){
            characters[i]=LackeysCrowds[i-7];
        }
        characters[14]=grandfather;
        characters[15]=snake;
        space=new BattleField();
        space.setMap(characters);
        space.printMap();
        System.out.println("---------------------------Round 2-----------------------------");
        bubbleSort(HuluBrothers);
        changshe.setChangshe(HuluBrothers);
        yanxing.setYanXing(LackeysCrowds);
        huluxpos=4;
        huluypos=3;
        lackeysxpos=1;//确定其放置的位置
        lackeysypos=13;
        for(int i=0;i<7;i++){
            HuluBrothers[i].move(huluxpos,huluypos);
            LackeysCrowds[i].move(lackeysxpos,lackeysypos);
        }
        grandfather.moveto(huluxpos-1,huluypos);
        snake.moveto(lackeysxpos+4,lackeysypos+5);
        for(int i=0;i<7;i++){
            characters[i]=HuluBrothers[i];
        }
        for(int i=7;i<14;i++){
            characters[i]=LackeysCrowds[i-7];
        }
        characters[14]=grandfather;
        characters[15]=snake;
        space.clearMap();
        space.setMap(characters);
        space.printMap();
        launch(args);
    }//世界的入口

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            primaryStage.setTitle("葫芦娃大战妖精");
            StackPane root =new StackPane();
            Scene scene=new Scene(root,1200,691);
            MyCanvas.drawCanvas(root);
            //  MainCanvas mainCanvas=new MainCanvas(640,480);
            // root.getChildren().add(mainCanvas);
            scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch(Exception e){
            e.printStackTrace();
        }//通过css文件设置背景
    }
}
