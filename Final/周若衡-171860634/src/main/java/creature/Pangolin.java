package creature;

import behave.BulletBehave;
import playrecord.Record;
import main.Main;
import util.DirectionVector;
import util.ImageType;

public class Pangolin extends GoodCreature{

    public Pangolin(int x, int y, ImageType image, int hp, boolean alive, int battle, String name, int speed, int id) {
        super(x,y,image,hp,alive,battle,name,speed,id);
    }

    @Override
    public Object clone() {
        Pangolin temp = null;
        temp = (Pangolin) super.clone();	//浅复制
        return temp;
    }

    @Override
    public  void run(){
        while(Main.chessBoard.isOver()==false) {
            if (this.alive == false)
                break;

            try {
                Thread.sleep(speed);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(Main.random.nextDouble()<0.1) {
                DirectionVector v = new DirectionVector(40, 0);
                int d_x = Main.chessBoard.START_X + (y + 1) * Main.chessBoard.CellXSize;
                int d_y = Main.chessBoard.START_Y + x * Main.chessBoard.CellYSize + Main.chessBoard.CellYSize / 2;
                Bullet bullet = new Bullet(0, 0, ImageType.BULLET, "bullet", v, d_x, d_y);
                long curTime = System.currentTimeMillis();
                Record command = new Record(this, new BulletBehave((int) (curTime - Main.chessBoard.startTime), bullet));
                synchronized (Main.chessBoard.getRecordAdminister().getCommands()) {
                    Main.chessBoard.getRecordAdminister().addOne(command);
                }
                Main.chessBoard.getBulletAdminister().addBullet(bullet);
                Main.chessBoard.paint();
            }
        }
    }
}
