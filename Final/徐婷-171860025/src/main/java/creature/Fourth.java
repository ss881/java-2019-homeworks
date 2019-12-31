package creature;


import javafx.scene.image.Image;

public class Fourth extends CalabashBros {
    private static Image liveImage = new Image("brother4.png");
    public Image getLiveImage(){return liveImage;}
    public Fourth(){
        super(4);
    }
}
