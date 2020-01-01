package creature;

import javafx.scene.image.Image;

public class Lackey extends Bad {

    private static Image liveImage = new Image("lackey.png");

    public Lackey(){
    }
    public Image getLiveImage(){
        return liveImage;
    }
}
