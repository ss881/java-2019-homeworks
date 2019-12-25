package thread;

import controller.MyMap;
import controller.Battle;
import creature.Creature;
import history.Action;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;
import java.util.ArrayList;

public class UpdateThread  extends Thread{
    private Canvas canvas;
    private MyMap map; //
    private Battle battle;
    private boolean finished;
    private boolean winner;
    //private Image winnerImage; //显示胜利者
    private Image winImage;//显示胜利图案
    private Writer writer;
    private ArrayList<Action> actions; //保存的动作
    private int updateNum = 0; //为0
    private boolean reloaded;
    private int lineNum;
    private int readNum;
    private FileReader reader;

    public  UpdateThread(Canvas canvas,Battle battle,Writer writer,ArrayList<Action> actions,boolean reloaded,FileReader reader){
        this.canvas = canvas;
        this.battle = battle;
        this.finished = false; //判断
        this.map = battle.getMap();
        //this.winnerImage = null;
        this.winImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/胜利.png")).toString(), 800, 400, false, false);
        this.writer = writer; //输出文件引用
        this.reader = reader; //输入文件
        this.actions = actions;
        this.actions.clear(); //清空操作
        this.reloaded = reloaded;
    }

    @Override
    public void run() {
        //播放BGM
        Media media = new Media(this.getClass().getClassLoader().getResource(new String("music/battle.mp3")).toString());
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        mp.setCycleCount(100); //一百次
        mp.play();

        if (!this.reloaded){ //战斗
            this.battle.startFight();//开始战斗，让葫芦娃动起来了
            while (true){
                this.check(); //进行检查判断是否是应该结束
                if (!this.finished){
                    this.showBattleMap(); //刷新
                    this.save(); //保存
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    this.showBattleMap();
                    this.save(); //保存
                    this.canvas.getGraphicsContext2D().drawImage(this.winImage, 100, 50);
                    System.out.println("游戏结束!");
                    this.battle.closeAll();
                    mp.stop();
                    try{
                        if(this.winner)
                            this.writer.write("calabashBrothers win");
                        else
                            this.writer.write("monsters win");
                        this.writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
        else{//回放
            this.map.setStart();
            BufferedReader bf = new BufferedReader(this.reader); //
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.clear();
            while (true){
                String temp = "";
                try{
                    if((temp = bf.readLine()) == null)
                        break;
                }catch(IOException e) {
                    e.printStackTrace();
                }
                arrayList.add(temp);
            }
            //开始处理
            lineNum = 0;
            readNum = arrayList.size();//
            int[][] pos_start = new int[10][20];
            while (lineNum < 10){
                String[] t = arrayList.get(lineNum).split(" ");
                for (int j = 0; j < 20; j++){
                    pos_start[lineNum][j] = Integer.parseInt(t[j]); //
                    System.out.print(pos_start[lineNum][j]);
                }
                System.out.println();
                lineNum++;
            }
            this.battle.initMap(pos_start);
            this.showBattleMap();
            while (true){
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                this.review(arrayList);
                if (lineNum >= readNum){
                    System.out.println("回放结束");
                    this.canvas.getGraphicsContext2D().drawImage(this.winImage, 100, 50);
                    mp.stop();
                    break;
                }
            }
        }
    }

    private void save(){
        synchronized (this.map){
            try{
                this.writer.write(Integer.toString(updateNum)+"\r\n");
                updateNum++;
            }catch (IOException e){
                e.printStackTrace();
            }
            for (int i = 0; i < this.actions.size(); i++) {
                int type = this.actions.get(i).getType();
                int x_start = this.actions.get(i).getStartX();
                int y_start = this.actions.get(i).getStartY();
                int x_end = this.actions.get(i).getEndX();
                int y_end = this.actions.get(i).getEndY();
                String temp = type + " " + x_start + " " + y_start + " " + x_end + " " + y_end;
                try {
                    this.writer.write(temp);
                    this.writer.write("\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            actions.clear(); //每一回合都清空一次
        }
    }

    private void check(){
        boolean isGoodAlive;
        boolean isBadAlive;
        synchronized (this.map){ //上锁，其他进程不能访问，通过其获得状态
            isGoodAlive = this.map.isGoodAlive(); //葫芦娃是否活着
            isBadAlive = this.map.isBadAlive(); //怪物是否活着
        }
        if(!isBadAlive) {
            this.finished = true;
            this.winner = true;
            System.out.println("葫芦娃获得胜利！");
            this.winImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/胜利.png")).toString(), 800, 400, false, false);
        }
        if(!isGoodAlive){
            this.finished = true;
            this.winner = false;
            System.out.println("妖怪获得胜利");
            this.winImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/失败.png")).toString(), 800, 400, false, false);
        }
    }

    private void showBattleMap(){
        synchronized (this.map) {
            this.canvas.getGraphicsContext2D().clearRect(0, 0, 1100, 600); //清空画布
            this.canvas.getGraphicsContext2D().drawImage(new Image(this.getClass().getResourceAsStream("/pic/bg.jpg")),0,0,1000,500);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++) {
                    this.map.showCreature(i, j, this.canvas);
                }
            }
        }
    }

    private void review(ArrayList<String> arrayList) {
        MyMap map =this.battle.getMap();
        while(lineNum < readNum){
            String[] t = arrayList.get(lineNum).split(" ");
            if (t.length == 1){
                System.out.println("回合数"+t[0]);
                lineNum++;
                this.showBattleMap();
                return;
            }
            else if(t.length == 5){ //为action
                lineNum++;
                System.out.println(this.lineNum);
                int type = Integer.parseInt(t[0]);
                int []start_pos = {Integer.parseInt(t[1]),Integer.parseInt(t[2])};
                int []end_pos = {Integer.parseInt(t[3]),Integer.parseInt(t[4])};
                if(type == 0){//移动
                    Creature creature = map.getCreature(start_pos[0], start_pos[1]);
                    if (creature != null) {
                        System.out.println();
                        map.moveCreatureTo(creature, end_pos[0], end_pos[1]);
                    }
                    else System.err.println("移动错误");
                }
                else if (type == 1){//攻击
                    Creature creature = map.getCreature(start_pos[0], start_pos[1]);
                    Creature enemy = map.getCreature(end_pos[0], end_pos[1]);
                    if (creature != null && enemy != null) {
                        creature.attack(enemy, this.canvas);
                    }
                    else System.err.println("攻击错误");
                }
                else if(type == 2){//技能
                    Creature creature = map.getCreature(start_pos[0], start_pos[1]);
                    if(creature != null){
                        if(creature.isAlive()){
                            creature.skill(map,this.canvas); //使用技能
                        }
                    }
                    else System.err.println("技能错误");
                }
            }
            else if(t.length == 2){
                lineNum++;
                System.out.println(t[0]);
                if(t[0].equals("calabashBrothers")) {
                    this.finished = true;
                    this.winner = true;
                    System.out.println("葫芦娃获得胜利！");
                    this.winImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/胜利.png")).toString(), 800, 400, false, false);
                }
                else if(t[0].equals("monster")){
                    this.finished = true;
                    this.winner = false;
                    System.out.println("妖怪获得胜利");
                    this.winImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/失败.png")).toString(), 800, 400, false, false);
                }
            }
        }
    }

}
