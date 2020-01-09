//package main.java;

class Point{
    private int x,y;
    public Point(){
        x=-1;
        y=-1;
    }
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
public class Life extends Thread{
    private int hp,atk,speed;
    private boolean Dead;
    private Point place;
    private Point destination;
    //private Vector<Point>path;
    private String name;
    public Life(int hp, int atk, int speed, Point place,String name) {
        this.hp = hp;
        this.atk = atk;
        this.speed = speed;
        this.place=place;
        this.name=name;
        destination=new Point(0,0);
    }

    public void beAttack(int atk){
        hp-=atk;
        if(hp<=0)
            Dead=true;
    }
    public int getAtk() {
        return atk;
    }
    public String getLifeName(){return name;}
    public boolean isDead() {
        return Dead;
    }

    public Point getPlace() {
        return place;
    }
    public void setPlace(Point place){this.place=place;}
    public Point getDestination() {
        return destination;
    }
    public void setDestination(Point destination){
        this.destination=destination;
    }
    @Override
    public void run(){
        Long  start=System.currentTimeMillis();
        Long  end=System.currentTimeMillis();


        while(!Dead){
            if(Thread.currentThread().isInterrupted())
                break;
            if(Math.abs(start-end)>=800*speed) {
                if(destination!=null) {
                    Point temp = Game.lifeMove(destination, place);
                    if (temp.getY()==destination.getY()&&temp.getX()==destination.getX()) {
                        Game.lifeAttack(destination, place);
                    }


                }
                start=System.currentTimeMillis();
                end=System.currentTimeMillis();
            }
            else{
                end=System.currentTimeMillis();
            }

        }
    }

}
