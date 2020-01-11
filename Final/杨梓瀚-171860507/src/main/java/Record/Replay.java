package Record;

import Battle.*;
import Creature.*;
import UI.GameController;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.misc.GC;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class Replay implements Runnable{
    private File file;

    private SAXReader saxReader;

    private Document document;

    public Replay(File file){
        this.file = file;
    }

    public void replay(){
        Battle.clearBattle();
        try {
            saxReader = new SAXReader();
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        List<Element> elements = document.getRootElement().elements();
        Fight fight = new Fight();
        int calabashIndex = 0;
        CalabashBros calabashBros = fight.getCalabashBros();
        for (Element element : elements){
            if (element.getName().equals("init")){
                List<Element> elements1 = element.elements();
                for (Element element1 : elements1){
                    if (element1.getName().equals("AllCreaturesNum")){
                        Fight.setNum(Integer.parseInt(element1.attributeValue("num")));
                    }else{
                        int hp = Integer.parseInt(element1.attributeValue("HP"));
                        int bd = Integer.parseInt(element1.attributeValue("baseDamage"));
                        int turn = Integer.parseInt(element1.attributeValue("turn"));
                        int x = Integer.parseInt(element1.attributeValue("X"));
                        int y = Integer.parseInt(element1.attributeValue("Y"));
                        switch (element1.getName()) {
                            case "CalabashBro":
                                CalabashBro calabashBro = calabashBros.getCalBro(calabashIndex++);
                                calabashBro.setHpAndBD(hp, bd);
                                calabashBro.setTurn(turn);
                                Battle.setPosition(new Position<CalabashBro>(calabashBro, x, y));
                                //pane.getChildren().add(calabashBro.getImageView());
                                GameController.addImageView(calabashBro.getImageView());
                                break;
                            case "Scorpion": {
                                Scorpion scorpion = fight.getScorpion();
                                Battle.setPosition(new Position<Scorpion>(scorpion, x, y));
                                scorpion.setTurn(turn);
                                scorpion.setHpAndBD(hp, bd);
                                //pane.getChildren().add(scorpion.getImageView());
                                GameController.addImageView(scorpion.getImageView());
                                break;
                            }
                            case "Grandpa":
                                Grandpa grandpa = fight.getGrandpa();
                                Battle.setPosition(new Position<Grandpa>(grandpa, x, y));
                                grandpa.setTurn(turn);
                                grandpa.setHpAndBD(hp, bd);
                                //pane.getChildren().add(grandpa.getImageView());
                                GameController.addImageView(grandpa.getImageView());
                                break;
                            case "Minion": {
                                Scorpion scorpion = fight.getScorpion();
                                scorpion.addMinions(x, y, hp, bd, turn);
                                List<Minions> minionsList = scorpion.getMinionsList();
                                /*Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        pane.getChildren().add(minionsList.get(minionsList.size() - 1).getImageView());
                                    }
                                });*/
                                GameController.addImageView(minionsList.get(minionsList.size() - 1).getImageView());
                                break;
                            }
                            case "Snake": {
                                Snake snake = fight.getSnake();
                                Battle.setPosition(new Position<Snake>(snake, x, y));
                                snake.setTurn(turn);
                                snake.setHpAndBD(hp, bd);
                                GameController.addImageView(snake.getImageView());
                                break;
                            }
                        }

                    }
                }
            }else{
                int x1 = Integer.parseInt(element.attributeValue("x1"));
                int y1 = Integer.parseInt(element.attributeValue("y1"));
                int x2 = Integer.parseInt(element.attributeValue("x2"));
                int y2 = Integer.parseInt(element.attributeValue("y2"));
                Position<?> position = Battle.getPosition(x1, y1);
                if (position == null){
                    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                }
                assert position != null;
                Creature creature = position.getHolder();
                System.out.println(creature.toString());
                if (element.getName().equals("Move")){
                    Battle.moveTo(position, x1 + x2, y1 + y2);
                    creature.move(x2, y2);
                }else if (element.getName().equals("Attack")){
                    creature.attack(x2, y2);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Fight.cheer();
    }

    @Override
    public void run() {
        replay();
    }
}
