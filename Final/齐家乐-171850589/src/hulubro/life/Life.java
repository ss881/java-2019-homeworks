package hulubro.life;

import hulubro.map.Grid;
import hulubro.map.Map;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Random;

public abstract class Life implements Runnable, Serializable {
    public Map map;
    public String name;
    public Grid grid;
    public Image image;
    public Team team;
    public boolean alive;

    Life(Map map){
        this.map=map;
        alive=true;
        int health = 100;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public synchronized void run() {
        while (!Thread.interrupted()) {
            Random r = new Random();
            if (!alive) {
                Thread.currentThread().interrupt();
                return;
            } else {
                int[] target = map.findEnemy(grid.x, grid.y, team);
                if (target[0] == grid.x && target[1] == grid.y) {
                    map.finish = true;
                    return;
                }
                int dis = map.distance(grid.x, grid.y, target[0], target[1]);
                if (dis >= 2) {
                    int x, y;
                    boolean rule=r.nextBoolean();
                    if(rule) {
                        if (abs(grid.x - target[0]) > abs(grid.y - target[1])) {
                            x = grid.x + sign(target[0] - grid.x);
                            y = grid.x;
                        } else {
                            y = grid.y + sign(target[1] - grid.y);
                            x = grid.x;
                        }
                    }else{
                        if(r.nextBoolean()){
                            y=grid.y;
                            if(r.nextBoolean()){
                                x=grid.x+1;
                            }else{
                                x=grid.x-1;
                            }
                        }else {
                            x=grid.x;
                            if(r.nextBoolean()){
                                y=grid.y+1;
                            }else{
                                y=grid.y-1;
                            }

                        }
                    }
                    map.move(grid.x, grid.y, x, y);
                    System.out.println("move from "+grid.x+","+grid.y+" to "+x+","+y);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    boolean keepalive=r.nextBoolean();
                    if(!keepalive){
                        alive=false;
                        grid.setName("死");
                        map.move(grid.x,grid.y,grid.x,grid.y);
                    }
                }
            }

        }
    }

    private int abs(int x) {
        return x > 0 ? x : -x;
    }

    private int sign(int x) {
        return x > 0 ? 1 : -1;
    }
}
