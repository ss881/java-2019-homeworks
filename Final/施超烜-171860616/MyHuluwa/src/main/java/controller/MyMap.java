package controller;


import creature.Creature;
import javafx.scene.canvas.Canvas;
import static java.lang.System.exit;

public class MyMap{
    private int x;
    private int y;
    private boolean start;
    private Position[][] map;

    public MyMap(int x,int y){
        map = new Position[x][y];
        this.x = x;
        this.y = y;
        for(int i =0;i<x;i++){
            for(int j = 0; j < y;j++){
                map[i][j] = new Position(i,j);
            }
        }
        start = false;
    }

    public void putCreature(Creature creature, int i, int j) {//把生物creature放置在（i,j）的位置上
        if(i < 0 || i > 9 || j < 0 || j > 19){
            System.err.println("坐标错误!");
            exit(-1);
        }
        map[i][j].setCreature(creature);
    }

    public void moveCreatureTo(Creature creature,int i,int j){//把生物creature移动到（i,j）的位置上
        if(i < 0 || i > 9 || j < 0 || j > 19){
            System.err.println("坐标错误!");
            exit(-1);
        }
        int x = creature.getI();
        int y = creature.getJ();
        if(x == -1 && y == -1){//creature未被放置在游戏地图上
            map[i][j].setCreature(creature);
        }
        else if(x != i || y != j){
            if(!map[i][j].isEmpty()){//战斗没开始可以交换位置
                if(!start) map[i][j].swapCreatureWith(map[x][y]);
            }else{
                map[i][j].moveCreatureFrom(map[x][y]);
            }
        }
    }

    public void showCreature(int i, int j,Canvas canvas){
        map[i][j].showCreature(canvas);
    }

    public void setStart() {
        start = true; //战斗开始，不能随便交换位置
    }

    public Creature checkEnemy(int i,int j){ //根据位置查询周围有没有敌人,检查四面和左右角是否有敌人
        if(i < 0 || i > 9 || j < 0 || j > 19) return null;
        Creature res = null;
        boolean hasEnemy = false; //一开始没有敌人
        boolean tempNature;
        if(this.map[i][j].getCreature()!=null)
            tempNature = this.map[i][j].getCreature().getNature();
        else return null;
        for(int x = i - 1; x <=  i + 1; x ++) {
            for(int y = j - 1; y <= j + 1; y ++){
                if(x < 0 || x > 9 || y < 0 || y > 19)
                {
                    continue;
                }
                Creature t = this.map[x][y].getCreature();
                if(t == null ) continue;
                if(!t.isAlive()) continue;
                if(t.getNature() != tempNature) {
                    hasEnemy = true;
                    res = t;
                    break;
                }
            }
        }
        return res;
    }

    public void remove(int i, int j) {
        this.map[i][j].clear();
    }

    public boolean isGoodAlive() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 20; j++) {
                if(!this.map[i][j].isEmpty()){ //有生物体存在
                    Creature temp = this.map[i][j].getCreature();//得到引用
                    if(temp.isAlive() && temp.getNature() == true) {//正义
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isBadAlive() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 20; j++) {
                if(!this.map[i][j].isEmpty()){ //有生物体存在
                    Creature temp = this.map[i][j].getCreature();//得到引用
                    if(temp.isAlive() && temp.getNature() == false){//邪恶
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void killAll() {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                this.map[i][j].kill();
            }
        }
    }

    public Creature getCreature(int i,int j){
        return this.map[i][j].getCreature(); //返回
    }

    public int[][] initMap(Creature[] creatures) {
        int [][]init = new int[10][20];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++) {
                Creature t = this.map[i][j].getCreature();
                if (t == null) init[i][j] = -1;
                else {
                    for(int k = 0; k < creatures.length; k++){
                        if(creatures[k] == t)
                        {
                            init[i][j] = k;
                        }
                    }
                }
            }
        }
        return init; //返回初始化
    }
}

