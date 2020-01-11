package space;


import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Bullet {
    public Box box;
    public Bullet(){
        box=new Box(0.3,0.1,0.1);
        box.setMaterial(new PhongMaterial(Color.BLUE));
        box.setVisible(false);
        box.setTranslateY(-1);
    }
}
