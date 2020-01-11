package Record;

import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Battle.*;
import Battle.Position;
import Creature.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Record {
    private static String filename;

    public static void newFile(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        filename =  System.getProperty("user.dir") + "\\\\" + simpleDateFormat.format(date) + ".xml";
    }

    public static void initFile(CalabashBros calabashBros, Grandpa grandpa, Scorpion scorpion, Snake snake){
        System.out.println(filename);
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("root");
        Element initElement = rootElement.addElement("init");
        Position<?> position = null;
        Element element = initElement.addElement("AllCreaturesNum");
        element.addAttribute("num", String.valueOf(Fight.getAllCreatureNum()));
        for (int i = 0; i < CalabashBros.numOfCalabash; i++){
            element = initElement.addElement("CalabashBro");
            position = Battle.getPositionByInstance(calabashBros.getCalBro(i));
            element.addAttribute("HP", String.valueOf(calabashBros.getCalBro(i).getHp()));
            element.addAttribute("baseDamage", String.valueOf(calabashBros.getCalBro(i).getBaseDamage()));
            element.addAttribute("turn", String.valueOf(calabashBros.getCalBro(i).getTurn()));
            assert position != null;
            element.addAttribute("X", String.valueOf(position.getX()));
            element.addAttribute("Y", String.valueOf(position.getY()));
        }
        element = initElement.addElement("Grandpa");
        position = Battle.getPositionByInstance(grandpa);
        element.addAttribute("HP", String.valueOf(grandpa.getHp()));
        element.addAttribute("baseDamage", String.valueOf(grandpa.getBaseDamage()));
        element.addAttribute("turn", String.valueOf(grandpa.getTurn()));
        element.addAttribute("X", String.valueOf(position.getX()));
        element.addAttribute("Y", String.valueOf(position.getY()));

        element = initElement.addElement("Snake");
        position = Battle.getPositionByInstance(snake);
        element.addAttribute("HP", String.valueOf(snake.getHp()));
        element.addAttribute("baseDamage", String.valueOf(snake.getBaseDamage()));
        element.addAttribute("turn", String.valueOf(snake.getTurn()));
        element.addAttribute("X", String.valueOf(position.getX()));
        element.addAttribute("Y", String.valueOf(position.getY()));

        element = initElement.addElement("Scorpion");
        position = Battle.getPositionByInstance(scorpion);
        element.addAttribute("HP", String.valueOf(scorpion.getHp()));
        element.addAttribute("baseDamage", String.valueOf(scorpion.getBaseDamage()));
        element.addAttribute("turn", String.valueOf(scorpion.getTurn()));
        element.addAttribute("X", String.valueOf(position.getX()));
        element.addAttribute("Y", String.valueOf(position.getY()));

        List<Minions> minionsList = scorpion.getMinionsList();
        for (Minions minions : minionsList) {
            element = initElement.addElement("Minion");
            position = Battle.getPositionByInstance(minions);
            element.addAttribute("HP", String.valueOf(minions.getHp()));
            element.addAttribute("baseDamage", String.valueOf(minions.getBaseDamage()));
            element.addAttribute("turn", String.valueOf(minions.getTurn()));
            element.addAttribute("X", String.valueOf(position.getX()));
            element.addAttribute("Y", String.valueOf(position.getY()));
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        File file = new File(filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            XMLWriter xmlWriter = new XMLWriter(fileOutputStream, format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    actionType:move, attack
    x1, y1: source position
    x2, y2: destination position
     */

    public static void writeMessage(String actionType, int x1, int y1, int x2, int y2){
        SAXReader saxReader = new SAXReader();
        File file = new File(filename);
        try {
            Document document = saxReader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement(actionType);
            round.addAttribute("x1", String.valueOf(x1));
            round.addAttribute("y1", String.valueOf(y1));
            round.addAttribute("x2", String.valueOf(x2));
            round.addAttribute("y2", String.valueOf(y2));
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            XMLWriter xmlWriter = new XMLWriter(fileOutputStream, outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();
            fileOutputStream.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
