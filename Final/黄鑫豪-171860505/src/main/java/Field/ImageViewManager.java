package Field;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewManager {
    private ImageView iv;
    private int x, y;

    public ImageViewManager(int x, int y, Image image){
        this.x = x;
        this.y = y;

        iv = new ImageView();
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        iv.setImage(image);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public ImageView getIV(){
        return iv;
    }
}
