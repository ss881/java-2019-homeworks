package creature;

import javafx.scene.image.Image;

public class First extends CalabashBros {
    private static Image liveImage = new Image("brother1.png");
    public Image getLiveImage(){return liveImage;}
    public First(){
        super(1);
    }
}
