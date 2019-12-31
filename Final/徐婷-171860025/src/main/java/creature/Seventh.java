package creature;


import javafx.scene.image.Image;

public class Seventh extends CalabashBros {
    private static Image liveImage = new Image("brother7.png");
    public Image getLiveImage(){return liveImage;}
    public Seventh(){
        super(7);
    }
}
