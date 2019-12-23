package nju.sfy.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import nju.sfy.model.creature.Calabash;

public class Tile{
    private Image image;
    private Image image2 = null;
    private Thing2D holder;
    private Canvas canvas;
    private int x;
    private int y;
    private final int OFFSET = 50;
    private final int SIZE = 70;
    public Tile(){
        holder = null;
        x = -1;
        y = -1;
    }
    public Tile(int x, int y){
        holder = null;
        this.x = x;
        this.y = y;
    }
    public Canvas getCanvas() {
        return canvas;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage(){
        return image;
    }
    public void setImage2(Image image2){
        this.image2 = image2;
    }
    public Image getImage2(){
        return image2;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public synchronized void setHolder(Thing2D t){
        holder = t;
    }
    public Thing2D getHolder(){
        return holder;
    }
    public void removeHolder(){
        holder = null;
    }
    public void show(){
        if(holder != null)
            holder.show();
        else{
            System.out.printf("%8s", " ");
        }
    }
    public void draw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image,OFFSET + SIZE * x, OFFSET + SIZE * y);
        if(image2 != null){
            gc.drawImage(image2,OFFSET + SIZE * x, OFFSET + SIZE * y);
        }
        if(holder != null){
            holder.draw();
        }
    }
    public void clear(){
        holder = null;
    }
    public boolean hasHolder(){
        return (holder != null);
    }
}