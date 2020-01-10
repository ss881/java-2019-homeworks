package cn.mhj.Reply;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Battlefield;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Recorder extends XMLFormat {
  private static final String FILE_PATH = "rep/";
  private Document document;
  private Element root;

  static {
    File folder = new File(FILE_PATH);
    if (!folder.exists() && !folder.isDirectory()) {
      folder.mkdirs();
    }
  }

  public Recorder() {
    super();
    document = DocumentHelper.createDocument();
    root = document.addElement(title);
  }

  public void initRecorder(Battlefield battlefield) {
    for (int i = 0; i < FightCreature.getFightCreatureNum(); i++) {
      Element creatureElement = root.addElement(creature + i);
      creatureElement.addAttribute(
          initialPositionX,
          String.valueOf(battlefield.getAllCreatures().get(i).getPosition().getX()));
      creatureElement.addAttribute(
          initialPositionY,
          String.valueOf(battlefield.getAllCreatures().get(i).getPosition().getY()));
    }
  }

  public void addRecord(FightCreature fightCreature, int roundNum) {
    Element element = root.element(creature + fightCreature.getCreatureID());
    Element roundElement = element.addElement(round + roundNum);
    roundElement.addAttribute(alive, String.valueOf(fightCreature.isAlive()));
    roundElement.addAttribute(positionX, String.valueOf(fightCreature.getPosition().getX()));
    roundElement.addAttribute(positionY, String.valueOf(fightCreature.getPosition().getY()));
  }

  public void fixReply(String winner) {
    root.addAttribute(roundNum, String.valueOf(FightCreature.getRoundNum()));
    List<Element> creaturesRecord = root.elements();
    List<Element> creaturesRecordNeedFix;
    if (winner.equals("妖精")) {
      creaturesRecordNeedFix = creaturesRecord.subList(0, 7);
    } else {
      creaturesRecordNeedFix = creaturesRecord.subList(7, creaturesRecord.size());
    }
    for (Element creatureRecord : creaturesRecordNeedFix) {
      List<Element> roundElementList = creatureRecord.elements();
      Element lastRoundRecord = roundElementList.get(roundElementList.size() - 1);
      if (Boolean.parseBoolean(lastRoundRecord.attributeValue(alive))) {
        lastRoundRecord.attribute(alive).setValue(String.valueOf(false));
      }
    }
  }

  public void writeRecord(String winner) {
    try {
      fixReply(winner);
      Date now = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
      File file = new File(FILE_PATH + dateFormat.format(now) + ".rep");
      file.createNewFile();
      XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
      FileOutputStream fos = new FileOutputStream(file);
      writer.setOutputStream(fos);
      writer.write(document);
      writer.close();
      System.out.println("save rep success");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
