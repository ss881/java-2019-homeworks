package huluwa;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import huluwa.position.Position;
import java.util.*;
import static java.util.Collections.sort;
public class BattleMap extends Parent {
    private int width;
    private int height;
    private Map<Position, Creature> characterList;
    private List<ImageView> attackAnimations;
    BattleMap(int w,int h){
        this.width = w;
        this.height = h;
        Platform.runLater(()-> resize(width,height));
        characterList = new HashMap<>();
        attackAnimations = new ArrayList<>();
    }
    public synchronized void clear(){
        characterList.clear();
        attackAnimations.clear();
        getChildren().clear();
    }
    private synchronized void resetLayer(){
        ArrayList<Position> keyList = new ArrayList<>(characterList.keySet());
        sort(keyList);
        for (Position key : keyList) {
            if (characterList.get(key) != null){
                ImageView i = characterList.get(key).getIcon();
                Platform.runLater(i::toFront);
            }
        }
        for(ImageView i : attackAnimations){
            Platform.runLater(i::toFront);
        }
    }
    public synchronized boolean isFree(Position p){
        int x = p.getX();
        int y = p.getY();
        if(x >Position.sizeX() || y > Position.sizeY() || x <0||y<0)
            return false;
        return !characterList.containsKey(p);
    }
    public synchronized void add(Creature c, Position p){
        characterList.put(p,c);
        int x = p.getXOnMap();
        int y = p.getYOnMap();
        c.getIcon().setX(x- (int)(1.0*c.width/2));
        c.getIcon().setY(y-c.height);
        ImageView i = c.getIcon();
        Platform.runLater(()->getChildren().add(i));
        resetLayer();
    }
    public synchronized void remove(Creature c){
        if(!characterList.containsValue(c)){
            return;
        }
        characterList.remove(c.getPosition());
        Platform.runLater(()->{getChildren().remove(c.getIcon());});
    }
}
