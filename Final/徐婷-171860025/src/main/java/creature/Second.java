package creature;


import javafx.scene.image.Image;

public class Second extends CalabashBros {
    private static Image liveImage = new Image("brother2.png");
    public Image getLiveImage(){return liveImage;}
    public Second(){
        super(2);
    }
}
