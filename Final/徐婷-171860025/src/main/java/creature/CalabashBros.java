package creature;

import javafx.scene.image.Image;

public abstract class CalabashBros extends Good {
    protected int rank;
    public abstract Image getLiveImage();
    public CalabashBros(int rank){
        this.rank = rank;
    }
}
