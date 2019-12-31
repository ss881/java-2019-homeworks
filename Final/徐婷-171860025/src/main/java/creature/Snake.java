package creature;

import javafx.scene.image.Image;

public class Snake extends Bad{
    protected static Image liveImage = new Image("Snake.png");
    public Snake(){
        x = 7;
        y = 8;
    }
    public Image getLiveImage(){
        return liveImage;
    }
}
