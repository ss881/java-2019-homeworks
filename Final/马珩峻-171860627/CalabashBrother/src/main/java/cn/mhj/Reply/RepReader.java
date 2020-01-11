package cn.mhj.Reply;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Enum.GameStatus;
import cn.mhj.Field.Battlefield;
import cn.mhj.Field.Position;
import java.io.File;
import java.util.List;
import java.util.Vector;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class RepReader extends XMLFormat implements Runnable {
  private Document document;
  private File file;
  private Battlefield battlefield;
  private Element root;

  public RepReader(File file, Battlefield battlefield) throws DocumentException {
    this.file = file;
    this.battlefield = battlefield;
    SAXReader reader = new SAXReader();
    document = reader.read(file);
    root = document.getRootElement();
  }

  public void initCreature() {
    List<Element> elementList = root.elements();
    Vector<FightCreature> allCreatures = battlefield.getAllCreatures();
    for (int i = 0; i < FightCreature.getFightCreatureNum(); i++) {
      Element element = elementList.get(i);
      int initX = Integer.parseInt(element.attributeValue(initialPositionX));
      int initY = Integer.parseInt(element.attributeValue(initialPositionY));
      allCreatures.get(i).init(new Position(initX, initY));
    }
  }

  public void readReply() throws InterruptedException {
    int roundNumber = Integer.parseInt(root.attributeValue(roundNum));
    Vector<FightCreature> allCreatures = battlefield.getAllCreatures();
    for (int i = 0; i < roundNumber; i++) {
      for (int j = 0; j < FightCreature.getFightCreatureNum(); j++) {
        Element element = root.element(creature + j).element(round + i);
        if (element != null) {
          int x = Integer.parseInt(element.attributeValue(positionX));
          int y = Integer.parseInt(element.attributeValue(positionY));
          boolean isAlive = Boolean.parseBoolean(element.attributeValue(alive));
          if (!isAlive) {
            allCreatures.get(j).beKilled();
          }
          allCreatures.get(j).moveTo(new Position(x, y));
        }
      }
      Thread.sleep(50);
    }
    battlefield.getBattlefieldController().setGameStatus(GameStatus.END);
    System.out.println("reply finish");
  }

  @Override
  public void run() {
    try {
      readReply();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
