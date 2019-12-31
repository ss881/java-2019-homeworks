package creature;


import javafx.scene.image.Image;

public class Third extends CalabashBros {
    private static Image liveImage = new Image("brother3.png");
    public Image getLiveImage(){return liveImage;}
    public Third(){
        super(3);
    }
}
