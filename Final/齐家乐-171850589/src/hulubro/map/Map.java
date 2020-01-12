package hulubro.map;

import hulubro.formation.Formation;
import hulubro.gui.ImageShow;
import hulubro.gui.Layout;
import hulubro.life.Life;
import hulubro.life.Team;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Map {
    public int win;
    public boolean finish;
    private Grid[][] grids;
    int N=18,M=11 ;
    private Layout layout;
    private ArrayList<ImageShow> imageShows;

    private ArrayList<Move> logs;

    public Map() {

    }

    public Grid[][] getGrids() {
        return grids;
    }

    public Map(Layout layout) {
        this.logs=new ArrayList<Move>();
        grids=new Grid[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid(i,j);
            }
        }
        this.layout=layout;
        finish =false;
        imageShows=new ArrayList<ImageShow>();
    }

    public void clear(){
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid(i,j);
            }
        }
    }

    public void LifeIn(Formation formation, int N, int M){
        if(M+formation.get_M()>this.M||N+formation.get_N()>this.N){
            System.err.println("Out of range!");
            assert(false);
        }
        for(int i=0;i<formation.get_M();i++){
            for(int j=0;j<formation.get_N();j++){
                if(!formation.is_empty(i, j)){
                    assert(grids[i+M][j+N].empty());
                    grids[i+M][j+N].LifeIn(formation.get_M_N(i, j));
                }
            }
        }
    }

    public void LifeIn(Life life, int N, int M){
        if(M>this.M||N>this.N){
            System.err.println("Out of range!");
            assert(false);
        }
        grids[M][N].LifeIn(life);
    }
    public void PrintMap(){
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(grids[i][j].empty()){
                    System.out.print("口");
                }else{
                    System.out.print(grids[i][j].life.name);
                }
            }
            System.out.println("");
        }
    }
    
    private synchronized void computeImageShow(){
        for (int i = 0; i < grids.length; i++) {
            Grid[] gridline = grids[i];
            for (int i1 = 0; i1 < gridline.length; i1++) {
                Grid grid = gridline[i1];
                if (!grid.empty()) {
                    Image image = new Image(Map.class.getResourceAsStream("../pic/墓碑.png"));
                    if (grid.life.alive) {
                        image = grid.life.image;
                    }
                    ImageShow imageShow = new ImageShow(image,i , i1);
                    imageShows.add(imageShow);
                }
            }
        }
    }
    
    public ArrayList<ImageShow> getImageShows(){
        computeImageShow();
        return imageShows;
    }


    public synchronized int[] findEnemy(int x, int y, Team team) {
        int min =1000;
        int[] enemy={x,y};
        for (Grid[] gridline : grids) {
            for (Grid grid : gridline) {
                if (!grid.empty() && grid.life.team != team && grid.life.alive) {
                    int dis = distance(x, y, grid.x, grid.y);
                    if (dis < min) {
                        min = dis;
                        enemy[0] = grid.x;
                        enemy[1] = grid.y;
                    }
                }
            }
        }
        return enemy;
    }
    public int distance(int x, int y, int x1, int y1) {
        int a=x>x1?x-x1:x1-x;
        int b=y>y1?y-y1:y1-y;
        return a+b;
    }

    public boolean empty(int i, int j) {
        if(i>=0&&i<M&&j>=0&&j<N){
            return grids[i][j].empty();
        }
        else return false;
    }

    public synchronized void move(int x, int y, int x1, int y1){
        Life life=grids[x][y].get_life();
        if (empty(x1,y1)) {
            life= grids[x][y].LifeOut();
            life.grid = grids[x1][y1];
            grids[x1][y1].LifeIn(life);
            Life finalLife1 = life;
            logs.add(new Move(x,y,x1,y1,finalLife1.alive));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> layout.repaint(x, y, x1, y1, finalLife1,true));
        }
        if(!life.alive){
            Life finalLife = life;
            logs.add(new Move(x,y,x1,y1,finalLife.alive));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> layout.repaint(x, y, x1, y1, finalLife,true));
        }
    }

    public void save(File file) throws IOException{
        FileOutputStream FOS = new FileOutputStream(file);
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(logs);
        OOS.close();
    }

}
