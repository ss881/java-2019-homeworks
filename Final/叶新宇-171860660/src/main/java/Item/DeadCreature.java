package Item;
import Property.Identity;
import javafx.scene.image.Image;

public class DeadCreature extends Creature{
    private int leftTime = 2000;
    private Image tomb = new Image("tomb.png");
    public DeadCreature() {
        id = Identity.DEAD;
    }
    public Image getImage() {
        return tomb ;
    }
    public boolean isTimeout() {
        return (leftTime == 0);
    }
    public void decreaseTime() {
        leftTime -= 50;
    }

    @Override
    public String getStatus() {
        return "T " + getPosition().toString();
    }
}
