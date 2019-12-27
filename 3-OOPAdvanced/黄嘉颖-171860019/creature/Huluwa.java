package creature;

import javafx.scene.image.*;

public class Huluwa extends Creature{
    private int rank;
    private String color;

    public Huluwa(int rank, String color, String name,Image picture, String picturePath){
        this.rank=rank;
        this.color=color;
        this.picture=picture;
        this.picturePath=picturePath;
        this.name=name;
    }

    public int getRank() {
        return rank;
    }
}
