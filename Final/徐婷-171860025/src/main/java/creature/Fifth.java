package creature;


import javafx.scene.image.Image;

public class Fifth extends CalabashBros {
    private static Image liveImage = new Image("brother5.png");
    public Image getLiveImage(){return liveImage;}
    public Fifth(){
        super(5);
    }
}
