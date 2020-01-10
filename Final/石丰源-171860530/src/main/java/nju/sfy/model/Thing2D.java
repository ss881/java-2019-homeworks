package nju.sfy.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import nju.sfy.model.creature.Creature;
import nju.sfy.model.creature.Grandpa;

public class Thing2D {
    protected Image image = null;
    protected Image image2 = null;
    protected String name = null;
    protected Tile tile = null;
    protected  Field field = null;
    private final int OFFSET = 50;
    private final int SIZE = 70;
    protected Canvas canvas = null;
    protected TextArea textArea = null;

    public Thing2D(){ }
    public Thing2D(String name){
        this.name = name;
    }
    public Thing2D(String name, Image image){
        this.name = name;
        this.image = image;
    }
    public String getName(){
        return name;
    }
    public void setTile(Tile tile){
        this.tile = tile;
    }
    public Tile getTile(){
        return tile;
    }
    public void setField(Field field){
        this.field = field;
    }
    public Field getField(){
        return field;
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
    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }
    public void setTextArea(TextArea textArea){
        this.textArea = textArea;
    }
    public void show(){
        System.out.printf("%8s", name);
    }
    public void draw() {
        if(tile != null){
            int x = tile.getX();
            int y = tile.getY();
            //棋盘左上角(50, 50),每个格子大小70*70
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(image, OFFSET + SIZE * x, OFFSET + SIZE * y);
            if(this instanceof Creature){
                Creature c = (Creature) this;
                gc.setFill(Color.RED);
                gc.fillRect(OFFSET + SIZE * x + 10, OFFSET + SIZE * y, 50 * (1.0 * c.getHealth() / 100), 3);
                gc.setStroke(Color.WHITE);
                gc.strokeRect(OFFSET + SIZE * x + 10, OFFSET + SIZE * y, 50, 3);
            }
        }
        else{
            //会打印名字
            System.out.println(name);
        }
    }
}