package war;

import creature.HuluBros;
import creature.Monsters;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import location.Position;
import record.Recorder;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum STATE{PLAY,END};

public class BattleField extends Parent {
    public final static int FIELD_WIDTH = 15;
    public final static int FIELD_HEIGHT = 8;

    //public Image backGround;
    public Formation f;
    public HuluBros hb;
    public Monsters ms;
    public STATE state;

    public ArrayList<Position> posList;
    public ArrayList<ImageView> posViewList;
    public ArrayList<ImageView> creatureViewList;

    public ExecutorService execService = null;

    private static class SingletonHolder{
        private static BattleField battleField = new BattleField();
    }

    public static BattleField getInstance(){
        return SingletonHolder.battleField;
    }

    private BattleField(){
        super();
        f = new Formation();
        hb = new HuluBros();
        ms = new Monsters(f.mCount);
        state = STATE.END;

        setBackGround("posField4.png");

        f.setFormation(hb,posList);
        f.setFormation(ms,posList);

        hb.loadIcons();
        ms.loadIcons();
        creatureViewList = new ArrayList<ImageView>();
        creatureViewList.addAll(hb.getImageView());
        creatureViewList.addAll(ms.getImageView());

        super.getChildren().addAll(creatureViewList);
        hb.print();
        ms.print();
    }

    public void reStart(){
        if(execService != null) {
            try {
                execService.shutdownNow();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        super.getChildren().removeAll();
        f = new Formation();
        hb = new HuluBros();
        ms = new Monsters(f.mCount);
        state = STATE.END;

        setBackGround("posField4.png");
        f.setFormation(hb,posList);
        f.setFormation(ms,posList);
        hb.loadIcons();
        ms.loadIcons();
        //imgViewIndex = new int[1+7+2+f.mCount];
        creatureViewList = new ArrayList<ImageView>();
        creatureViewList.addAll(hb.getImageView());
        creatureViewList.addAll(ms.getImageView());
        super.getChildren().addAll(creatureViewList);
    }

    public static int getIndex(Position pos){//pos必须是在posList中，计算pos在posList中的坐标
        //1,2 -> 31
        //return (pos.y*FIELD_WIDTH + pos.x);
        return (pos.x*FIELD_WIDTH + pos.y);
    }

    public static int getIndex(int x, int y){
        return (x*FIELD_WIDTH + y);
    }

    /*public void keyCodeHandle(KeyCode keyCode){
        switch (keyCode){
            case SPACE:
                setPlay();
                play();
                //System.out.println("VK_SPACE");
                break;
            case R:
                //restartGame();
                reStart();
                break;
            case L:
                //highlightReplay();
                break;
        }
    }*/

    public void modifyView(){
        creatureViewList.get(Index.getInstance().getViewIndex(0)).setY(hb.grandPa.pos.x*Position.P_WIDTH);
        creatureViewList.get(Index.getInstance().getViewIndex(0)).setX(hb.grandPa.pos.y*Position.P_HEIGHT);
        if(!hb.grandPa.isAlive()){
            creatureViewList.get(Index.getInstance().getViewIndex(0)).setImage(new Image("tomb2.png"));
        }
        System.out.println("grandpaView");
        for(int i=1; i<=7; i++){
            creatureViewList.get(Index.getInstance().getViewIndex(i)).setY(hb.hList.get(i-1).pos.x*Position.P_WIDTH);
            creatureViewList.get(Index.getInstance().getViewIndex(i)).setX(hb.hList.get(i-1).pos.y*Position.P_HEIGHT);
            if(!hb.hList.get(i-1).isAlive()){
                creatureViewList.get(Index.getInstance().getViewIndex(i)).setImage(new Image("tomb2.png"));
            }
        }
        System.out.println("huluView");
        creatureViewList.get(Index.getInstance().getViewIndex((int)'$')).setY(ms.SnakeSpirit.pos.x*Position.P_WIDTH);
        creatureViewList.get(Index.getInstance().getViewIndex((int)'$')).setX(ms.SnakeSpirit.pos.y*Position.P_HEIGHT);
        if(!ms.SnakeSpirit.isAlive()){
            creatureViewList.get(Index.getInstance().getViewIndex((int)'$')).setImage(new Image("tomb2.png"));
        }
        creatureViewList.get(Index.getInstance().getViewIndex((int)'#')).setY(ms.ScorpionSpirit.pos.x*Position.P_WIDTH);
        creatureViewList.get(Index.getInstance().getViewIndex((int)'#')).setX(ms.ScorpionSpirit.pos.y*Position.P_HEIGHT);
        if(!ms.ScorpionSpirit.isAlive()){
            creatureViewList.get(Index.getInstance().getViewIndex((int)'#')).setImage(new Image("tomb2.png"));
        }
        for(int i=0; i<f.mCount; i++){
            creatureViewList.get(Index.getInstance().getViewIndex((int)'A'+i)).setY(ms.mList.get(i).pos.x*Position.P_WIDTH);
            creatureViewList.get(Index.getInstance().getViewIndex((int)'A'+i)).setX(ms.mList.get(i).pos.y*Position.P_HEIGHT);
            if(!ms.mList.get(i).isAlive()){
                creatureViewList.get(Index.getInstance().getViewIndex((int)'A'+i)).setImage(new Image("tomb2.png"));
            }
        }
        System.out.println("frogsView");
    }

    /*public static void genDiffRandom(ArrayList<Integer> rand,int length, int lower,int upper){
        assert rand!=null;
        assert (upper-lower+1)>=length;
        //for(int i=0; i<)
        Random r = new Random();
        int count = 0;
        do{
            int num = r.nextInt(upper+1);
            //int idx = rand.indexOf(num);
            if(rand.contains(num) || num<lower){
                continue;
            }
            rand.add(num);
            count++;
        }while (count < length);
    }*/

    public void setPlay() {
        this.state = STATE.PLAY;
    }

    public void setEnd() {
        this.state = STATE.END;
    }

    public Boolean isPlay(){
        return state==STATE.PLAY;
    }

    public Boolean isGameOver(){
        return (hb.isAllDead()|ms.isAllDead());
    }

    public void play(){
        execService = Executors.newCachedThreadPool();
        /*TODO*/
        try{
            //for(Creature ct: )
            execService.execute(hb.grandPa);
            for(int i=0;i<7;i++) {
                execService.execute(hb.hList.get(i));
            }
            execService.execute(ms.SnakeSpirit);
            execService.execute(ms.ScorpionSpirit);
            for(int i=0; i<ms.monsterCount; i++){
                execService.execute(ms.mList.get(i));
            }
            execService.execute(Recorder.getInstance());
            execService.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Node> getChildren(){
        return super.getChildren();
    }

    /*@Override
    public ObservableList<Node> getChildrenUnmodifiable() {
        return super.getChildrenUnmodifiable();
    }*/

    private void setBackGround(String fieldImagePath){
        posList = new ArrayList<Position>();
        posViewList = new ArrayList<ImageView>();

        for(int i=0; i<FIELD_HEIGHT; i++){
            for(int j=0; j<FIELD_WIDTH; j++){
                posList.add(new Position(i,j));
            }
        }

        assert posList!=null;
        //Image image = new Image("posField4.png");
        Image image = new Image(fieldImagePath);
        for(int i=0; i<FIELD_HEIGHT; i++){
            for(int j=0; j<FIELD_WIDTH; j++){
                ImageView iv = new ImageView(image);
                iv.setX(j*Position.P_WIDTH);
                iv.setY(i*Position.P_HEIGHT);
                posViewList.add(iv);
            }
        }
        super.getChildren().addAll(posViewList);
    }

    void printPosList(){
        for(Position pos: posList){
            System.out.println(pos.toString());
        }
    }

    /*public static void main(String []args){
        ArrayList<Integer> rand = new ArrayList<Integer>();
        genDiffRandom(rand,8,2,9);
        for(Integer i: rand){
            System.out.println(i);
        }
    }*/
}
