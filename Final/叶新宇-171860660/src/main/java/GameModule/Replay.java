package GameModule;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Replay {
    private List<Unit> units = new ArrayList<>();
    private List<String> text = new ArrayList<>();
    private static final Image background = new Image("background.png");

    public void addElement(Unit e) {
        units.add(e);
    }
    public void addText(String str) {
        text.add(str);
    }

    public void display(GraphicsContext gc, TextArea textArea) {
        gc.drawImage(background,0,0,960,540);
        for(Unit e : units) {
            gc.drawImage(e.getImage(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
            if(e.isAlive()) {
                gc.setFill(Color.RED);
                gc.fillRect(e.getX() + 5, e.getY(),50,5);
                gc.setFill(Color.color(0,1.0,0.3));
                float ratio = e.getHpRatio();
                gc.fillRect(e.getX() + 5, e.getY(),50 * ratio,5);
            }
        }
        for(String i : text)
            textArea.appendText(i + "\n");
    }
}
