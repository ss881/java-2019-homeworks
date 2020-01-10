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
    public synchronized Creature getClosestEnemy(Position p, Class type){
        Creature res = null;
        int distance = Integer.MAX_VALUE;
        for(Map.Entry<Position, Creature> entry : characterList.entrySet()){
            if(type.isInstance(entry.getValue()) && entry.getValue().isAlive()){
                Position tempPosition = entry.getKey();
                int tmp_distance = tempPosition.getX()+tempPosition.getY()-p.getX()-p.getY();
                if(tmp_distance < distance){
                    res = entry.getValue();
                    distance = tmp_distance;
                }
            }
        }
        return res;
    }
    private void removeAttackAnimation(ImageView i){
        Platform.runLater(()-> getChildren().remove(i));
        attackAnimations.remove(i);
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
            //System.out.println("Remove Fail:" + c + " " + c.getPosition());
            return;
        }
        characterList.remove(c.getPosition());
        if(GameController.currentCondition() == Condition.READY){
            Platform.runLater(()->{getChildren().remove(c.getIcon());});
            return;
        }
        synchronized(c){
            ImageView getAttack = new ImageView(new Image("bin/attack.png",350,
                    140,false,false));
            int x = (int)(c.getIcon().getX() + (c.getWidth() - 70)/2);
            int y = (int)(c.getIcon().getY() + (c.getHeight() - 70)/2);
            getAttack.setX(x);
            getAttack.setY(y);
            attackAnimations.add(getAttack);
            Timeline getAttackAnimation =  new Timeline();
            getAttackAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,new KeyValue(getAttack.viewportProperty(),
                            new Rectangle2D(0,0,70,70)),
                            new KeyValue(getAttack.opacityProperty(),1)),
                    new KeyFrame(Duration.millis(100),new KeyValue(getAttack.viewportProperty(),
                            new Rectangle2D(70,0,70,70))),
                    new KeyFrame(Duration.millis(200),new KeyValue(getAttack.viewportProperty(),
                            new Rectangle2D(140,0,70,70))),
                    new KeyFrame(Duration.millis(300),new KeyValue(getAttack.viewportProperty(),
                            new Rectangle2D(210,0,70,70))),
                    new KeyFrame(Duration.millis(400),new KeyValue(getAttack.viewportProperty(),
                            new Rectangle2D(280,0,70,70))),
                    new KeyFrame(Duration.millis(500),new KeyValue(getAttack.viewportProperty(),
                            new Rectangle2D(0,0,70,70)))
            );
            getAttackAnimation.setCycleCount(1);
            getAttackAnimation.setOnFinished(event -> {
                removeAttackAnimation(getAttack);
                c.getIcon().setImage(new Image("bin/mubei.png"));
                c.getIcon().setFitHeight(80);
                c.getIcon().setFitWidth(50);
                c.getIcon().setX(c.getPosition().getXOnMap()-25);
                c.getIcon().setY(c.getPosition().getYOnMap()-80);
                c.getIcon().toBack();
            });
            Platform.runLater(()->{
                getChildren().add(getAttack);
                getAttack.toFront();
                getAttackAnimation.play();
            });
            if(GameController.currentCondition() == Condition.RUNNING)
                GameController.getInstance().writeRecords(System.currentTimeMillis(),"remove " + c);
        }
    }
    public synchronized boolean move(Creature c, Position p){
        if(!isFree(p) || characterList.get(c.getPosition()) != c)
            return  false;
        Position pre_pos = c.getPosition();
        characterList.remove(pre_pos);
        characterList.put(p,c);
        if(GameController.currentCondition()== Condition.RUNNING)
            GameController.getInstance().writeRecords(System.currentTimeMillis(),"move " + c + " " + p);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(c.speed),
                new KeyValue(c.getIcon().xProperty(), p.getXOnMap()-(int)(1.0*c.width/2)),
                new KeyValue(c.getIcon().yProperty(), p.getYOnMap()-c.height)));
        timeline.setOnFinished(t -> c.moveDone());
        Platform.runLater(timeline::play);
        resetLayer();
        return true;
    }
}
