package creature;

import javafx.scene.image.Image;

public class Scorpion extends Bad {
    protected static Image liveImage = new Image("Scorpion.png");
    public Scorpion(){
    }
    public Image getLiveImage(){
        return liveImage;
    }
}
