package creature;


import javafx.scene.image.Image;

public class Sixth extends CalabashBros {
    private static Image liveImage = new Image("brother6.png");
    public Image getLiveImage(){return liveImage;}
    public Sixth(){
        super(6);
    }
}
